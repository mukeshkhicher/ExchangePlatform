package com.mk.service.exchange.controller.dto;

import lombok.Getter;

@Getter
public class ExchangeRequest {
	private Long requesterId;
	private Long receiverId;
	private Long requestedProductId;
}
