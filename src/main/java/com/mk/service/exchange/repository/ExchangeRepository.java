package com.mk.service.exchange.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.mk.service.exchange.domain.Exchange;

public interface ExchangeRepository extends CrudRepository<Exchange, Long>{
	Page<Exchange> findByReceiverIdAndStatus(Long receiverId, String status, Pageable page);
}
