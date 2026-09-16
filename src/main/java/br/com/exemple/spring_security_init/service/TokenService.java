package br.com.exemple.spring_security_init.service;

import br.com.exemple.spring_security_init.infra.security.UserSecurity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    
    @Value("${api.security.token.secret}")
    private String secretApiKey;
    private final static String API_NAME = "myAPI";
    
    private Algorithm getAlgorithm() throws IllegalArgumentException{
        return Algorithm.HMAC256(secretApiKey);
    }
    
    public String generate(UserSecurity user) throws JWTCreationException, IllegalArgumentException {
        return JWT.create()
                  .withIssuer(API_NAME)
                  .withSubject(user.getUsername())
                  .withExpiresAt(this.getExpirationTime())
                  .sign(this.getAlgorithm());
    }
    
    public String validate(String token) throws JWTVerificationException, IllegalArgumentException {
        return JWT.require(this.getAlgorithm())
                  .withIssuer(API_NAME)
                  .build()
                  .verify(token)
                  .getSubject();
    }
    
    private Instant getExpirationTime() {
        return LocalDateTime
                .now()
                .plusHours(2)
                .toInstant(ZoneOffset.of("-03:00"));
    }
}
