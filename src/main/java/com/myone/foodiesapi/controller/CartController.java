package com.myone.foodiesapi.controller;

import com.myone.foodiesapi.io.CartRequest;
import com.myone.foodiesapi.io.CartResponse;
import com.myone.foodiesapi.services.CartService;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;


import org.springframework.web.bind.annotation.*;


import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/cart")
    public CartResponse addToCart(@RequestBody CartRequest request){
        String foodId = request.getFoodId();
        if (foodId ==null || foodId.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"FoodId not found");
             }
        return  cartService.addToCart(request);

    }
    @DeleteMapping("/cart")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void clearCart(){
         cartService.clearCart();
    }

    @GetMapping("/cart")
    public CartResponse getCart(){
        return cartService.getCart();
    }

    @PostMapping("/remove")
    public CartResponse removeFromCart(@RequestBody CartRequest request){
        String foodId = request.getFoodId();
        if (foodId ==null || foodId.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"FoodId not found");
        }
       return cartService.removeFromCart(request);
    }
}
