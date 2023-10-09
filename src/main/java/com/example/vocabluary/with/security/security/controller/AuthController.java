package com.example.vocabluary.with.security.security.controller;

import com.example.vocabluary.with.security.security.model.AuthenticationResponse;
import com.example.vocabluary.with.security.security.model.AuthenticationRequest;
import com.example.vocabluary.with.security.security.model.RegisterRequest;
import com.example.vocabluary.with.security.security.service.AuthenticationService;
import com.example.vocabluary.with.security.user.repository.RoleRepository;
import com.example.vocabluary.with.security.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("test successful!");
    }

    //we don't want to send password in url, that is why we are using Post
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest ){

        String response = authenticationService.register(registerRequest);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest loginRequest){


        return ResponseEntity.ok(authenticationService.login(loginRequest));
    }
}
