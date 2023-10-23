package com.regulus.sms.models.Dtos;

import com.regulus.sms.models.entities.User;
import lombok.Data;

@Data
public class JwtAuthResponse {

    private User user;

    private String token;

    private String refreshToken;

}
