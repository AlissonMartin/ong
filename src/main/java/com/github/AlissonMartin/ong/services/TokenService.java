package com.github.AlissonMartin.ong.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.github.AlissonMartin.ong.dtos.LoginRequestDTO;
import com.github.AlissonMartin.ong.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

  @Value("${api.security.token.secret}")
  private String secret;

  public Instant generateExpirationDate() {
    return LocalDateTime.now().plusHours(24).toInstant(ZoneOffset.of("-3"));
  }


  public String generateToken(User user) {

    Algorithm algorithm = Algorithm.HMAC256(secret);

    String token = JWT.create().withSubject(user.getEmail()).withExpiresAt(this.generateExpirationDate()).sign(algorithm);

    return token;
  }

  public String validateToken(String token) {

    Algorithm algorithm = Algorithm.HMAC256(secret);

    try {
      return JWT.require(algorithm).build().verify(token).getSubject();
    } catch (JWTVerificationException exception) {
      return null;
    }
  }
}
