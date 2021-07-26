package com.mk.service.exchange.domain;

import javax.persistence.Entity;

import com.mk.common.repository.BaseEntity;

@Entity
public class Exchange extends BaseEntity{
	private Long requesterId;
	private Long receiverId;
	private Long requestedProductId;
	private Long exchangeProductId;
	private String status;
	private boolean borrow=false;
	private Long requesterTransactionId;
	private Long receiverTransactionId;
	public Long getRequesterId() {
		return requesterId;
	}
	public void setRequesterId(Long requesterId) {
		this.requesterId = requesterId;
	}
	public Long getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}
	public Long getRequestedProductId() {
		return requestedProductId;
	}
	public void setRequestedProductId(Long requestedProductId) {
		this.requestedProductId = requestedProductId;
	}
	public Long getExchangeProductId() {
		return exchangeProductId;
	}
	public void setExchangeProductId(Long exchangeProductId) {
		this.exchangeProductId = exchangeProductId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public boolean isBorrow() {
		return borrow;
	}
	public void setBorrow(boolean borrow) {
		this.borrow = borrow;
	}
	public Long getRequesterTransactionId() {
		return requesterTransactionId;
	}
	public void setRequesterTransactionId(Long requesterTransactionId) {
		this.requesterTransactionId = requesterTransactionId;
	}
	public Long getReceiverTransactionId() {
		return receiverTransactionId;
	}
	public void setReceiverTransactionId(Long receiverTransactionId) {
		this.receiverTransactionId = receiverTransactionId;
	}
}
