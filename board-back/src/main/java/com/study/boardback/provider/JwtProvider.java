package com.study.boardback.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//의존성 주입
@Component
public class JwtProvider {

  @Value("${secret-key}")
  private String secretKey;

  public String create(String email) {
    //인증 시간 및 만료시간 설정
    Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));

    //인증객체 생성
    String jwt = Jwts
      .builder()
      .signWith(SignatureAlgorithm.HS256, secretKey) //키 타입 설정
      .setSubject(email)
      .setIssuedAt(new Date())
      .setExpiration(expiredDate) //시간 세팅
      .compact();

    return jwt;
  }

  public String validate(String jwt) {
    Claims claims = null;
    try {
      claims =
        Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
    } catch (Exception exception) {
      exception.printStackTrace();
      return null;
    }
    return claims.getSubject();
  }
}
