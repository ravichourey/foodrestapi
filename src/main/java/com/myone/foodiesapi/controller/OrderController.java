package com.myone.foodiesapi.controller;

import com.myone.foodiesapi.io.OrderRequest;
import com.myone.foodiesapi.io.OrderResponse;
import com.myone.foodiesapi.services.OrderService;
import com.razorpay.RazorpayException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse createOrderWithPayment(@RequestBody OrderRequest request) throws RazorpayException {
        OrderResponse response =  orderService.createOrderWithPayment(request);
        return response;
    }


    @PostMapping("/verify")
    public void verifyPayment(@RequestBody Map<String, String> paymentData) {
        orderService.verifyPayment(paymentData, "paid");
    }
    @GetMapping
    public List<OrderResponse> getOrders(){
        return orderService.getUserOrder();
    }
    @DeleteMapping("/{orderid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable String orderId){
        orderService.removeOrder(orderId);
    }
    // admin panel
    @GetMapping("/all")
    public List<OrderResponse> getOrederOfAllUser(){
    return orderService.getOrderOfAllUser();
    }
    // admin panel
    @PatchMapping("/status/{orderId}")
    public void updateOrderStatus(@PathVariable String orderId , @RequestParam  String status){
        orderService.updateOrderStatus(orderId,status);
    }
}
