package com.project.fitstore.dtos.payment;

import com.project.fitstore.domain.payment.Payment;

import java.util.List;

public record GetAllPaymentsResponse(List<GetPaymentResponse> payments) {
    public static GetAllPaymentsResponse from(List<Payment> payments){
        return new GetAllPaymentsResponse(payments.stream().map(GetPaymentResponse::from).toList());
    }
}
