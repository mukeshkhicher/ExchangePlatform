package com.mk.service.account;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.mk.service.account.domain.RewardPointAccount;
import com.mk.service.account.domain.Transaction;
import com.mk.service.account.exception.InsufficientBalanceException;
import com.mk.service.account.repository.AccountRepository;
import com.mk.service.account.repository.TransactionRepository;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	TransactionRepository transactionRepository;
	Map<Long, Object> locks = new HashMap<>();//TODO On multiple instance, need to externalize it 

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public RewardPointAccount createAccount(Long userId) {
		RewardPointAccount account = new RewardPointAccount();
		account.setBalance(BigDecimal.ZERO);
		account.setUserId(userId);
		return accountRepository.save(account);
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Transaction credit(Long toUserid, BigDecimal points) {
		locks.putIfAbsent(toUserid, new Object());
		synchronized (locks.get(toUserid)) {
			RewardPointAccount toUserAcc = this.getAccount(toUserid);
			toUserAcc.credit(points);
			toUserAcc = accountRepository.save(toUserAcc);
			Transaction tx = new Transaction();
			tx.setAccountId(toUserAcc.getId());
			tx.setCredited(points);
			tx.setTransactionDate(new Date());
			return transactionRepository.save(tx);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Transaction debit(Long fromUserId, BigDecimal points) throws InsufficientBalanceException {
		locks.putIfAbsent(fromUserId, new Object());
		synchronized (locks.get(fromUserId)) {
			RewardPointAccount fromUserAcc = this.getAccount(fromUserId);
			if(fromUserAcc.getBalance().compareTo(points) <= 0) throw new InsufficientBalanceException("Insufficent Balance in Account:"+fromUserId);
			fromUserAcc.debit(points);
			fromUserAcc = accountRepository.save(fromUserAcc);
			Transaction tx = new Transaction();
			tx.setAccountId(fromUserAcc.getId());
			tx.setDebited(points);
			tx.setTransactionDate(new Date());
			return transactionRepository.save(tx);
		}
	}

	@Override
	public RewardPointAccount getAccount(Long userId) {
		Assert.notNull(userId, "UserId must not be null");
		return accountRepository.findByUserId(userId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Map<Long,Transaction> transfer(Long fromUserId, Long toUserId, BigDecimal points) throws InsufficientBalanceException {
		locks.putIfAbsent(toUserId, new Object());
		locks.putIfAbsent(fromUserId, new Object());

		Object lock1 = toUserId <= fromUserId ? locks.get(toUserId) : locks.get(fromUserId);
		Object lock2 = toUserId <= fromUserId ? locks.get(fromUserId) : locks.get(toUserId);
		Map<Long,Transaction> response = new HashMap<Long, Transaction>();
		synchronized (lock1) {
			synchronized (lock2) {
				RewardPointAccount fromUserAcc = this.getAccount(fromUserId);
				RewardPointAccount toUserAcc = this.getAccount(toUserId);
				toUserAcc.credit(points);
				toUserAcc = accountRepository.save(toUserAcc);
				if (fromUserAcc.getBalance().compareTo(points) < 0)
					throw new InsufficientBalanceException("Insufficent Balance in Account:" + fromUserId);
				fromUserAcc.debit(points);
				fromUserAcc = accountRepository.save(fromUserAcc);

				Transaction tx1 = new Transaction();
				tx1.setAccountId(toUserAcc.getId());
				tx1.setCredited(points);
				tx1.setTransactionDate(new Date());
				tx1 = transactionRepository.save(tx1);
				response.put(toUserId, tx1);

				Transaction tx2 = new Transaction();
				tx2.setAccountId(fromUserAcc.getId());
				tx2.setDebited(points);
				tx2.setTransactionDate(new Date());
				tx2 =  transactionRepository.save(tx2);
				response.put(fromUserId, tx1);
			}
		}
		return response;
	}

}
