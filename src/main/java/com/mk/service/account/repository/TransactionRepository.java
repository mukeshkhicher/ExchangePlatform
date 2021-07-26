package com.mk.service.account.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.mk.service.account.domain.Transaction;

public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Long>{

}
