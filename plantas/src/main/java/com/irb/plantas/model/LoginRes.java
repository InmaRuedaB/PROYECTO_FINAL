package com.irb.plantas.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRes {
  private String accessToken;
  private String refreshToken;
  private String tokenType = "Bearer";
  private String username;
}
