//package com.myone.foodiesapi.services;
//
//
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import com.myone.foodiesapi.utills.JwtUtil;
//
//import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
//public class AuthenticationService {
//
//    private final AuthenticationManager authenticationManager;
//    private final AppUserDetailService userDetailsService;
//    private final JwtUtil jwtUtil;
//
//    public com.myone.foodiesapi.io.AuthenticationResponse authenticate(com.myone.foodiesapi.io.AuthenticationRequest request) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getEmail(),
//                        request.getPassword()
//                )
//        );
//
//        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
//        String token = jwtUtil.generateToken(userDetails);
//
//        return com.myone.foodiesapi.io.AuthenticationResponse.builder()
//                .token(token)
//                .build();
//    }
//
//
//}