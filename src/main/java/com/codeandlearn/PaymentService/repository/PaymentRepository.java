package com.codeandlearn.PaymentService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.codeandlearn.PaymentService.entity.PaymentDetails;

public interface PaymentRepository extends JpaRepository<PaymentDetails, Long> {

	@Query("from PaymentDetails as p where p.orderId=:orderId")
	PaymentDetails findByOrderId(@Param("orderId") long orderId);
	
}
