package com.regulus.sms.services;


import com.regulus.sms.models.Dtos.JwtAuthResponse;
import com.regulus.sms.models.Dtos.RefreshTokenRequest;
import com.regulus.sms.models.Dtos.SignInRequest;
import com.regulus.sms.models.Dtos.SignUpRequest;
import com.regulus.sms.models.entities.User;

public interface AuthService {

    User signUp(SignUpRequest signUpRequest);

    JwtAuthResponse signin(SignInRequest signInRequest);

    JwtAuthResponse refrestoken(RefreshTokenRequest refreshTokenRequest);

}
