package com.myone.foodiesapi.services;

import com.myone.foodiesapi.io.UserRequest;
import com.myone.foodiesapi.io.UserResponce;

public interface UserService {
     UserResponce registerUser(UserRequest request);
     String findByUserId();
}
