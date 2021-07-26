package com.mk.service.account;

import java.math.BigDecimal;
import java.util.Map;

import com.mk.service.account.domain.RewardPointAccount;
import com.mk.service.account.domain.Transaction;
import com.mk.service.account.exception.InsufficientBalanceException;

public interface AccountService{
	RewardPointAccount getAccount(Long userId);
	RewardPointAccount createAccount(Long userId);
	Map<Long, Transaction> transfer(Long fromUserId, Long toUserId, BigDecimal points) throws InsufficientBalanceException;
	Transaction credit(Long toUserid, BigDecimal points);
	Transaction debit(Long fromUserId, BigDecimal points) throws InsufficientBalanceException;
}
