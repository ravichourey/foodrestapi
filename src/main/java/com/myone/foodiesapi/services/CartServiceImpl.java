package com.myone.foodiesapi.services;

import com.myone.foodiesapi.entity.CartsEntity;
import com.myone.foodiesapi.io.CartRequest;
import com.myone.foodiesapi.io.CartResponse;
import com.myone.foodiesapi.repository.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService{
    private final CartRepository cartRepository;
    private final UserService userService;


    @Override
    public CartResponse addToCart(CartRequest request) {
        String loggedInUserId =  userService.findByUserId() ;
        Optional<CartsEntity> cartsEntityOptional = cartRepository.findByUserId(loggedInUserId);
        CartsEntity cart =   cartsEntityOptional.orElseGet(() -> new CartsEntity(loggedInUserId, new HashMap<>()));
        Map<String, Integer>  cartItems  = cart.getItems();
        cartItems.put(request.getFoodId(), cartItems.getOrDefault(request.getFoodId() ,0) +1);
        cart.setItems(cartItems);
        cart = cartRepository.save(cart);
        return convertToResponse(cart);
    }

    @Override
    public CartResponse getCart() {
        String loggedInUserId =  userService.findByUserId() ;
        CartsEntity cartsEntity = cartRepository.findByUserId(loggedInUserId)
                .orElse(new CartsEntity(null, loggedInUserId, new HashMap<>() ));

        return convertToResponse(cartsEntity);
    }

    @Override
    public void clearCart() {
        String loggedInUserId = userService.findByUserId();
        cartRepository.deleteByUserId(loggedInUserId);
    }

    @Override
    public CartResponse removeFromCart(CartRequest request) {
        String loggedInUserId =  userService.findByUserId();
        CartsEntity entity = cartRepository.findByUserId((loggedInUserId))
                 .orElseThrow(() -> new RuntimeException("Cart is not found"));
        Map<String, Integer> cartItems =  entity.getItems();
        if(cartItems.containsKey(request.getFoodId())){
           int currentQty = cartItems.get(request.getFoodId());
           if(currentQty > 0 ){
               cartItems.put(request.getFoodId(), currentQty -1);
           }
           else {
               cartItems.remove(request.getFoodId());
           }

        }
        entity =cartRepository.save(entity);
        return  convertToResponse(entity);
    }


    private CartResponse convertToResponse(CartsEntity cartsEntity){
        return CartResponse.builder()
               .id(cartsEntity.getId())
               .userId(cartsEntity.getUserId())
               .items(cartsEntity.getItems())
               .build();
    }
}
