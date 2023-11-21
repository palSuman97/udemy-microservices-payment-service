package com.codeandlearn.PaymentService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeandlearn.PaymentService.model.PaymentRequest;
import com.codeandlearn.PaymentService.model.PaymentResponse;
import com.codeandlearn.PaymentService.service.PaymentService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/payment")
@Log4j2
public class PaymentController {

	@Autowired
	PaymentService paymentService;
	
	@PostMapping
	public long doPayment(@RequestBody PaymentRequest paymentRequest) {
		
		return paymentService.doPayment(paymentRequest);
	}
	
	@GetMapping("/{orderId}")
	public ResponseEntity<PaymentResponse> getPaymentDetails(@PathVariable("orderId") long orderId) {
		
		log.info("===orderId=={} ", orderId);
		
		return paymentService.getPaymentDetails(orderId);
	}
	
	
}
