package com.example.vocabluary.with.security.security.model;

import lombok.Data;

//TODO: watch video lombock
@Data
public class AuthenticationRequest {
    private String email;
    private String password;
}
