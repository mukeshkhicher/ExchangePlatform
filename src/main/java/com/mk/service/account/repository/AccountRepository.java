package com.mk.service.account.repository;

import org.springframework.data.repository.CrudRepository;

import com.mk.service.account.domain.RewardPointAccount;

public interface AccountRepository extends CrudRepository<RewardPointAccount, Long>{
	RewardPointAccount findByUserId(Long userId);
}
