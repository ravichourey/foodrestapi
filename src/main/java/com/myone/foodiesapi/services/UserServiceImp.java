package com.myone.foodiesapi.services;

import com.myone.foodiesapi.entity.UserEntity;
import com.myone.foodiesapi.io.UserRequest;
import com.myone.foodiesapi.io.UserResponce;

import com.myone.foodiesapi.repository.UserRepository;
import lombok.AllArgsConstructor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserServiceImp implements UserService{
    
    @Autowired
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationFacade authenticationFacade;


    @Override
    public UserResponce registerUser(UserRequest request) {
        UserEntity newUser = convertToEntity(request);
        newUser = userRepository.save(newUser);
        return convertToResponse(newUser);

    }

    @Override
    public String findByUserId() {
        String loggedInUserEmail = authenticationFacade.getAuthentication().getName();
        UserEntity loggedInUser =   userRepository.findByEmail(loggedInUserEmail).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return loggedInUser.getId();
    }

    private UserEntity convertToEntity(UserRequest request){
         return UserEntity.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .build();

     }
     private UserResponce convertToResponse(UserEntity registerUser){
           return UserResponce.builder()
                .id(registerUser.getId())
                .name(registerUser.getName())
                .email(registerUser.getEmail())
                .build();
     }
}
