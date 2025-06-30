package org.scoula.security.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.scoula.security.account.dto.UserInfoDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResultDTO {
    private String token;        // JWT 인증 토큰
    private UserInfoDTO user;    // 사용자 기본 정보
}