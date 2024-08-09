package com.sparta.msa_exam.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;

  // 사용자 ID를 받아 JWT 액세스 토큰을 생성하여 응답합니다.
  @GetMapping("/auth/signIn")
  // ResponseEntity<?>: ResponseEntity로 반환을 해주는데 <?> 어떤 타입이 들어오든 신경쓰지 않는다.
  public ResponseEntity<?> createAuthToken(
          @RequestParam("user_id")
          String user_id
  ){
    // {"access_token":"eyJhbGciOi.."}
    return ResponseEntity.ok(new AuthResponse(authService.createAccessToken(user_id)));
  }

  // JWT 액세스 토큰을 포함하는 응답 객체입니다.
  @Data // Getter and Setter
  @AllArgsConstructor
  @NoArgsConstructor
  static class AuthResponse{
    private String access_token;
  }
}
