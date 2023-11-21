package com.codeandlearn.PaymentService.service;

import org.springframework.http.ResponseEntity;

import com.codeandlearn.PaymentService.model.PaymentRequest;
import com.codeandlearn.PaymentService.model.PaymentResponse;

public interface PaymentService {

	long doPayment(PaymentRequest paymentRequest);

	ResponseEntity<PaymentResponse> getPaymentDetails(long orderId);

}
