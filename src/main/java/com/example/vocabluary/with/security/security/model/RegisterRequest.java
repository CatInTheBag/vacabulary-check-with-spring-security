package com.example.vocabluary.with.security.security.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    //TODO: add email
    //TODO: add method toDTO to return only the data needed
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
