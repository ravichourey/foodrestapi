package com.myone.foodiesapi.controller;

import com.myone.foodiesapi.io.AuthenticationRequest;
import com.myone.foodiesapi.io.AuthenticationResponse;
import com.myone.foodiesapi.services.AppUserDetailService;
import com.myone.foodiesapi.utills.JwtUtil;
import lombok.AllArgsConstructor;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AppUserDetailService userDetailService;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest request) {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
            final UserDetails  userDetails = userDetailService.loadUserByUsername(request.getEmail());
            final String jwtToken = jwtUtil.createToken(new java.util.HashMap<>(), userDetails.getUsername());
            return new AuthenticationResponse(request.getEmail(),jwtToken);
    }

}
