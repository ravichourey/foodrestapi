package com.myone.foodiesapi.services;

import com.myone.foodiesapi.entity.OrderEntity;
import com.myone.foodiesapi.io.OrderRequest;
import com.myone.foodiesapi.io.OrderResponse;
import com.myone.foodiesapi.repository.CartRepository;
import com.myone.foodiesapi.repository.OrderRepo;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Value;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service

public class OrderServiceImpl  implements   OrderService {
    @Autowired
    private  OrderRepo orderRepo;
    @Autowired
    private  UserService userService;
    @Autowired
     private CartRepository cartRepository;

    @Value("${razorpay_key}")
    private String RAZORPAY_KEY;
    @Value("${razorpay_secret}")
    private String RAZORPAY_SECRET;
    @Override
   public OrderResponse createOrderWithPayment(OrderRequest request) throws RazorpayException
    {

    OrderEntity newOrder = convertToEntity(request);
    newOrder = orderRepo.save(newOrder);

    // create razorpay payment order
        RazorpayClient razorpayClient = new RazorpayClient(RAZORPAY_KEY,RAZORPAY_SECRET);
        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", (int)(newOrder.getAmount() * 100));
        orderRequest.put("currency", "INR");
        orderRequest.put("payment_capture", 1);
        Order razorpayOrder = razorpayClient.orders.create(orderRequest);
        newOrder.setRazorpayOrderId(razorpayOrder.get("id"));
        String  loggedInUserId = userService.findByUserId();
        newOrder.setUserId(loggedInUserId);
         newOrder = orderRepo.save(newOrder);
         return convertToResponse(newOrder);

    }

    @Override
    public void verifyPayment(Map<String, String> paymentData, String status) {
    String razorpayOrderId = paymentData.get("razorpay_order_id");
                 OrderEntity exitingOrder =   orderRepo.findByRazorpayOrderId(razorpayOrderId)
                .orElseThrow(() -> new RuntimeException("Order not found") );
                 exitingOrder.setPaymentStatus(status);
                 exitingOrder.setRazorpaySignature("razorpay_signature");
                 exitingOrder.setRazorpaymentId(paymentData.get("razorpay_payment_id"));
                    orderRepo.save(exitingOrder);
                    if("paid".equalsIgnoreCase(status)){
                        cartRepository.deleteByUserId(exitingOrder.getUserId());
                    }
    }

    @Override
    public List<OrderResponse> getUserOrder() {
        String loggedInUserId =  userService.findByUserId() ;
        List<OrderEntity> list = orderRepo.findByUserId(loggedInUserId);
        return list.stream().map(entity -> convertToResponse(entity)).collect(Collectors.toList());
    }

    @Override
    public void removeOrder(String orderId) {
    orderRepo.deleteById(orderId);
    }

    @Override
    public List<OrderResponse> getOrderOfAllUser() {
        List<OrderEntity> list = orderRepo.findAll();
        return list.stream().map(entity -> convertToResponse(entity)).collect(Collectors.toList());
    }

    @Override
    public void updateOrderStatus(String orderId, String status) {
       OrderEntity entity = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
                    entity.setOrderStatus(status);
                     orderRepo.save(entity);
    }

    private OrderResponse convertToResponse(OrderEntity newOrder) {
      return   OrderResponse.builder()
                .id(newOrder.getId())
                .amount(newOrder.getAmount())
                .userAddress(newOrder.getUserAddress())
                .userId(newOrder.getUserId())
                .razorpayOrderId(newOrder.getRazorpayOrderId())
                .orderStatus(newOrder.getOrderStatus())
                .paymentStatus(newOrder.getPaymentStatus())
                .email(newOrder.getEmail())
                .phoneNo(newOrder.getPhoneNo())
              .orderItems(newOrder.getOrderItems())
                .build();
    }

    private OrderEntity convertToEntity(OrderRequest request) {
        return OrderEntity.builder()
            .userAddress(request.getUserAddress())
            .amount(request.getAmount())
            .orderItems(request.getOrderItems())
            .email(request.getEmail())
            .phoneNo(request.getPhoneNo())
            .orderStatus(request.getOrderStatus())
            .build();
    }
}