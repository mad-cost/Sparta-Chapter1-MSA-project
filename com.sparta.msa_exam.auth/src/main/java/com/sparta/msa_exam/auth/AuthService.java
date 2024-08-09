package com.sparta.msa_exam.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class AuthService {

  @Value("${spring.application.name}")
  private String issuer; // auth-service

  @Value("${service.jwt.access-expiration}")
  private Long accessExpiration;

  // 생성자 주입 부분을 통하여 디코딩된 secretKey 가져오기
  // SecretKey는 JWT 토큰을 생성하기 위해 서명할 때 또는 서명을 검증할 때 사용.
  private final SecretKey secretKey;

  // 생성자 주입
  public AuthService(
          @Value("${service.jwt.secret-key}")
          String secretKey
  ) {
    // Base64 인코딩된 secretKey를 디코딩
    this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
  }

  // accessToken 생성
  public String createAccessToken(String user_id){
    return Jwts.builder()
            .claim("user_id", user_id)
            // 권한도 넣어줄 수 있다: .claim("role", "ADMIN")
            .issuer(issuer) // 토큰 발급자: auth-service
            .issuedAt(new Date(System.currentTimeMillis())) // 발행 날짜
            .expiration(new Date(System.currentTimeMillis() + accessExpiration)) // 만료 날짜
            .signWith(secretKey, SignatureAlgorithm.HS512) // 디코딩된 secretKey를 사용하여 알고리즘 서명
            .compact();
  }
}
