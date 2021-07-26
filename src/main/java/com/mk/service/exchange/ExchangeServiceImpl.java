package com.mk.service.exchange;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.mk.common.constants.Constants;
import com.mk.service.account.AccountService;
import com.mk.service.account.domain.Transaction;
import com.mk.service.account.exception.InsufficientBalanceException;
import com.mk.service.exchange.controller.dto.ExchangeRequest;
import com.mk.service.exchange.domain.Exchange;
import com.mk.service.exchange.repository.ExchangeRepository;
import com.mk.service.product.ProductService;

@Service
public class ExchangeServiceImpl implements ExchangeService{
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private ExchangeRepository exchangeRepository;
	@Autowired
	private ProductService productService;
	
	@Override
	public Page<Exchange> getExchange(Long receiverId, String status, Pageable page) {
		return exchangeRepository.findByReceiverIdAndStatus(receiverId, status, page);
	}
	
	@Override
	public Exchange intiate(ExchangeRequest exchangeRequest) {
		Assert.notNull(exchangeRequest.getReceiverId(), "Exchange ID can not be null");
		Assert.notNull(exchangeRequest.getRequesterId(), "Exchange ID can not be null");
		Assert.notNull(exchangeRequest.getRequestedProductId(), "Exchange ID can not be null");
		Exchange exchange = new Exchange();
		exchange.setRequesterId(exchangeRequest.getRequesterId());
		exchange.setRequestedProductId(exchangeRequest.getRequestedProductId());
		exchange.setReceiverId(exchangeRequest.getReceiverId());
		exchange.setStatus(Constants.EXCHANGE_STATUS_REQUESTED);
		return exchangeRepository.save(exchange);
	}
	
	@Override
	@Transactional
	public Exchange approve(Long exchangeId, Long exchangeProductId) throws InsufficientBalanceException {
		Assert.notNull(exchangeId, "Exchange ID can not be null");
		Optional<Exchange> optionalExchange = exchangeRepository.findById(exchangeId);
		Assert.isTrue(optionalExchange.isPresent(),"Invalid exchange Id");
		Exchange exchange = optionalExchange.get();
		Transaction requesterTransaction = null;
		Transaction receiverTransaction = null;
		if(exchangeProductId == null) {
			exchange.setBorrow(true);
			Map<Long,Transaction> txMap = accountService.transfer(exchange.getRequesterId(),exchange.getReceiverId(),new BigDecimal(1));
			requesterTransaction = txMap.get(exchange.getRequesterId());
			receiverTransaction = txMap.get(exchange.getReceiverId());
			
		}else{
			exchange.setExchangeProductId(exchangeProductId);
			productService.markNotAvailable(exchangeProductId);
			requesterTransaction = accountService.credit(exchange.getRequesterId(),new BigDecimal(1));
			receiverTransaction = accountService.credit(exchange.getReceiverId(),new BigDecimal(1));
		}
		productService.markNotAvailable(exchange.getRequestedProductId());
		exchange.setRequesterTransactionId(requesterTransaction.getId());
		exchange.setReceiverTransactionId(receiverTransaction.getId());
		exchange.setStatus(Constants.EXCHANGE_STATUS_COMPLETED);
		return exchangeRepository.save(exchange);
	}
}
