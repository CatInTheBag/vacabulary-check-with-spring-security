package com.example.vocabluary.with.security.security.service;

import com.example.vocabluary.with.security.security.jwt.JWTService;
import com.example.vocabluary.with.security.security.model.AuthenticationRequest;
import com.example.vocabluary.with.security.security.model.AuthenticationResponse;
import com.example.vocabluary.with.security.security.model.RegisterRequest;
import com.example.vocabluary.with.security.user.model.RoleEnum;
import com.example.vocabluary.with.security.user.model.UserEntity;
import com.example.vocabluary.with.security.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    public String register(RegisterRequest registerRequest) {

        if(checkIfAlreadyPresent(registerRequest.getEmail())){
            return "User already created";
        }

        var user = UserEntity.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(RoleEnum.USER)
                .build();

        userRepository.save(user);

        return "user crated";
    }

    public AuthenticationResponse login(AuthenticationRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
        var user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow();

/*
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));

        //like cookie, when the user is logged in, there is no need to log in again,
        SecurityContextHolder.getContext().setAuthentication(authentication);

*/

        return generateToken(user);
    }

    private AuthenticationResponse generateToken(UserEntity userEntity) {
        var jwtToken = jwtService.generateToken(userEntity);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public boolean checkIfAlreadyPresent(String email) {
        return userRepository.existsByEmail(email);
    }
}
