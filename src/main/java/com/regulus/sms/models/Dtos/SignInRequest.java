package com.regulus.sms.models.Dtos;

import lombok.Data;

@Data
public class SignInRequest {

    private String email;

    private String password;
}
