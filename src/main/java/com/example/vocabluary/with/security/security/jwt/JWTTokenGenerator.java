package com.example.vocabluary.with.security.security.jwt;

import com.example.vocabluary.with.security.security.util.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTTokenGenerator {

    public String generateToken(Authentication authentication){
        String userName = authentication.getName();
        Date currentDate = new Date();
        Date expDate = new Date(currentDate.getTime() + SecurityConstants.EXPIRATION_TIME_IN_MINUTES);

        String token = Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(new Date())
                .setExpiration(expDate)
                .signWith(SignatureAlgorithm.HS256, SecurityConstants.JWT_SECRET)
                .compact();

        return token;
    }

    public String getUserNameFromJWT(String token){

        Claims claims = Jwts.parser()
                .setSigningKey(SecurityConstants.JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String token){

        try{
            Jwts.parser()
                    .setSigningKey(SecurityConstants.JWT_SECRET)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception ex){
            throw new AuthenticationCredentialsNotFoundException("JWT issue");
        }

    }
}
