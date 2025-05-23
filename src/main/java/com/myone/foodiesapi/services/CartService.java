package com.myone.foodiesapi.services;

import com.myone.foodiesapi.io.CartRequest;
import com.myone.foodiesapi.io.CartResponse;



public interface CartService {
     CartResponse addToCart(CartRequest request);
     CartResponse getCart();
     void clearCart();
     CartResponse removeFromCart(CartRequest request);


}
