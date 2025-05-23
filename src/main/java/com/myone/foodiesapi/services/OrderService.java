package com.myone.foodiesapi.services;

import com.myone.foodiesapi.io.OrderRequest;
import com.myone.foodiesapi.io.OrderResponse;
import org.springframework.stereotype.Service;
import com.razorpay.RazorpayException;

import java.util.List;
import java.util.Map;

@Service
public interface OrderService {

    OrderResponse createOrderWithPayment(OrderRequest request) throws RazorpayException;
    public void verifyPayment(Map<String , String> paymentData ,String status);
    List<OrderResponse> getUserOrder();
    void removeOrder(String orderId);
    List<OrderResponse>  getOrderOfAllUser();
    void updateOrderStatus(String orderId, String status);
}
