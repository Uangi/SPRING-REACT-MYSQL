package com.jyp.boardback.provider;

import java.util.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtProvider {
    private String secretKey = "S3cr3tk3y";

    public String create(String email) {
        Date expiredDate = Date.from(Instant.now().plus(1,ChronoUnit.HOURS));

        String jwt = Jwts.builder()
        .signWith(SignatureAlgorithm.ES256, secretKey)
        .setSubject(email).setIssuedAt(new Date()).setExpiration(expiredDate)
        .compact();

        return jwt;
    }

    public String validate(String jwt) {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(secretKey)
            .parseClaimsJwt(jwt).getBody();
        }   catch (Exception excetpion) {
                excetpion.printStackTrace();
                return null;
        }
        return claims.getSubject();
    }
}
