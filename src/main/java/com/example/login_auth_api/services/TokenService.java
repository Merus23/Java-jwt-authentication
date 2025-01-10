package com.example.login_auth_api.services;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.login_auth_api.models.User;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public class TokenService {

    private final Long validityInMilliseconds = 3600000L;

    @Value("${api.security.token.security}")
    private String key;

    public String generateToken(User user){

        try {
            Date now = new Date();
            Date experiresAt = new Date(now.getTime() + validityInMilliseconds);
            Algorithm algorithm = Algorithm.HMAC256(key);

            return JWT.create().
                    withIssuer("login-auth-api").
                    withIssuedAt(now).
                    withExpiresAt(experiresAt).
                    withClaim("roles", user.getRoles()).
                    withSubject(user.getEmail()).
                    sign(algorithm);
            
        } catch (JWTCreationException exception){
            throw new JWTCreationException("Error to create jwt token", exception);
        }
    }


    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(key);
            return JWT.require(algorithm)
                    .withIssuer("login-auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            return null;
        }
    }
}
