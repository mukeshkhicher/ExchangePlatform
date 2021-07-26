package com.mk.service.account.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;

import com.mk.common.repository.BaseEntity;

@Entity
public class Transaction extends BaseEntity{
	private Long accountId;
	private BigDecimal credited;
	private BigDecimal debited;
	private Date transactionDate;
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public BigDecimal getCredited() {
		return credited;
	}
	public void setCredited(BigDecimal credited) {
		this.credited = credited;
	}
	public BigDecimal getDebited() {
		return debited;
	}
	public void setDebited(BigDecimal debited) {
		this.debited = debited;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
}
