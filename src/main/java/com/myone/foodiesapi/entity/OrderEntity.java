package com.myone.foodiesapi.entity;

import com.myone.foodiesapi.io.OrderItem;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.List;

@Data
@Builder
@Document(collection = "orders")
public class OrderEntity {
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
    private String razorpaySignature;
    private String razorpaymentId;
    private String orderStatus;

}
