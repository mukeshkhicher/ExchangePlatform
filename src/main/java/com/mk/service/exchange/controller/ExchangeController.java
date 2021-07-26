package com.mk.service.exchange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mk.service.exchange.ExchangeService;
import com.mk.service.exchange.controller.dto.ExchangeRequest;
import com.mk.service.exchange.domain.Exchange;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/exchange")
public class ExchangeController {

	@Autowired
	private ExchangeService exchangeService;

	@PostMapping
	public ResponseEntity<Exchange> borrow(@RequestBody ExchangeRequest exchangeRequest){
		try {
			return new ResponseEntity<Exchange>(exchangeService.intiate(exchangeRequest), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping
	public ResponseEntity<Page<Exchange>> getRequestedExchange(@RequestParam("receiverId") Long receiverId, @RequestParam("status") String status,Pageable page){
		try {
			return new ResponseEntity<Page<Exchange>>(exchangeService.getExchange(receiverId,status,page), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/approve")
	public ResponseEntity<Exchange> approveExchange(@RequestParam("exhangeId") Long exchangeId, @RequestParam(required = false,value = "exchangeProductId") Long exchangeProductId){
		try {
			return new ResponseEntity<Exchange>(exchangeService.approve(exchangeId,exchangeProductId), HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
