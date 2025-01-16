package com.OnlineFoodOrdering.service;

import com.OnlineFoodOrdering.Response.PaymentResponse;
import com.OnlineFoodOrdering.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentService{

    public PaymentResponse createPaymentLink(Order order);
}
