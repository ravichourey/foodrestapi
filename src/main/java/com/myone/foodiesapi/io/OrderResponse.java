package com.myone.foodiesapi.io;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;


@Data
@Builder

public class OrderResponse {
    @Id
    private String id;
    private String userId;
    private  String userAddress;
    private  String phoneNo;
    private String email;
    private List<OrderItem> orderItems;
    private double amount;
    private String paymentStatus;
    private String razorpayOrderId;
    private String orderStatus;
    private String currency = "INR";
}
