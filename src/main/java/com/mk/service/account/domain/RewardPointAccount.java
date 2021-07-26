package com.mk.service.account.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;

import com.mk.common.repository.BaseEntity;

@Entity
public class RewardPointAccount extends BaseEntity{
	private Long userId;
	private BigDecimal balance;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public void credit(BigDecimal points) {
		this.balance = this.balance.add(points);
	}
	public void debit(BigDecimal points) {
		this.balance = this.balance.subtract(points);
	}
}
