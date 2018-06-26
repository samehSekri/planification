package com.wevioo.dto;

import java.io.Serializable;


public class JwtAuthenticationResponseDto implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;

    private final String token;
    private final UserDto user;

    public JwtAuthenticationResponseDto(String token, UserDto user) {
        this.token = token;
        this.user = user;
        //Set the password null of the returned jwtAuthenticationResponseDto
        this.user.setPassword(null);
    }

    public String getToken() {
        return this.token;
    }
    
    public UserDto getUser() {
        return this.user;
    }
    
}
