package com.myone.foodiesapi.io;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderRequest {

    private String userAddress;
    private List<OrderItem> orderItems;
    private double amount;
    private String email;
    private  String phoneNo;
    private String orderStatus;


}

