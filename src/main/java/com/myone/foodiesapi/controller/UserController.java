package com.myone.foodiesapi.controller;

import com.myone.foodiesapi.io.UserRequest;
import com.myone.foodiesapi.io.UserResponce;

import com.myone.foodiesapi.services.UserServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")

public class UserController {
    @Autowired
    private final UserServiceImp userServiceImp;


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponce register(@RequestBody UserRequest request){
        return userServiceImp.registerUser(request);

    }
}
