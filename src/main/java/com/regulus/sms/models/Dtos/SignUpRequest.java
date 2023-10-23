package com.regulus.sms.models.Dtos;

import jakarta.persistence.Column;;
import lombok.Data;


@Data
public class SignUpRequest {

    private String username;

    @Column(unique = true)
    private String email;

    private String password;

    private String firstName;

    private String lastName;


}
