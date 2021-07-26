package com.mk.service.exchange.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BorrowRequest {
	private Long fistBorrowLenderId;
	private Long firstBorrowLendeeId;
	private Long firstBorrowProductId;
	private String firstBorrowStatus;
	private Long secondBorrowLenderId;
	private Long secondBorrowLendeeId;
	private Long secondBorrowProductId;
	private String secondBorrowStatus;
}
