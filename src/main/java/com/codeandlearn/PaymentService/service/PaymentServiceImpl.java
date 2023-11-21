package com.codeandlearn.PaymentService.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.codeandlearn.PaymentService.entity.PaymentDetails;
import com.codeandlearn.PaymentService.model.PaymentRequest;
import com.codeandlearn.PaymentService.model.PaymentResponse;
import com.codeandlearn.PaymentService.repository.PaymentRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	PaymentRepository paymentRepository;

	@Override
	public long doPayment(PaymentRequest paymentRequest) {
		
		log.info("payment: {}", paymentRequest);
		PaymentDetails paymentDetails= PaymentDetails.builder()
										.paymentDate(Instant.now())
										.paymentMode(paymentRequest.getPaymentMode().name())
										.paymentStatus("SUCCESS")
										.orderId(paymentRequest.getOrderId())
										.referenceNumber(paymentRequest.getReferenceNumber())
										.amount(paymentRequest.getAmount())
										.build();
		paymentRepository.save(paymentDetails);
		
		log.info("Payment completed with Id: {}", paymentDetails.getId());
		
		return paymentDetails.getId();
	}

	@Override
	public ResponseEntity<PaymentResponse> getPaymentDetails(long orderId) {
		
		log.info("===under getPaymentDetails== {}", orderId);
		
		PaymentDetails paymentDetails= paymentRepository.findByOrderId(orderId);
		
		log.info("===paymentDetails== {}", paymentDetails);
		
		PaymentResponse paymentResponse= PaymentResponse.builder()
											.id(paymentDetails.getId())
											.paymentMode(paymentDetails.getPaymentMode())
											.paymentStatus(paymentDetails.getPaymentStatus())
											.referenceNumber(paymentDetails.getReferenceNumber())
											.build();
		
		return new ResponseEntity<PaymentResponse>(paymentResponse, HttpStatus.OK);
	}
	
}
