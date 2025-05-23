package com.myone.foodiesapi.io;

import lombok.*;


@Data
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private String email;
    private String token;

}
