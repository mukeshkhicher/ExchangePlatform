package com.mk.service.exchange;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mk.service.account.exception.InsufficientBalanceException;
import com.mk.service.exchange.controller.dto.ExchangeRequest;
import com.mk.service.exchange.domain.Exchange;

public interface ExchangeService {
	public Exchange intiate(ExchangeRequest exchangeRequest);
	public Page<Exchange> getExchange(Long receiverId, String status, Pageable page);
	public Exchange approve(Long exchangeId, Long exchangeProductId) throws InsufficientBalanceException;
}
