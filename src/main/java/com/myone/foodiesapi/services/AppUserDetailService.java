package com.myone.foodiesapi.services;

import java.util.Collections;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.myone.foodiesapi.entity.UserEntity;
import com.myone.foodiesapi.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Authenticating user: {}", username);

        UserEntity user = userRepository.findByEmail(username)
                .orElseThrow(() -> {
                    log.warn("User not found: {}", username);
                    return new UsernameNotFoundException("User not found with username: " + username);
                });

        return new User(
                user.getEmail(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}