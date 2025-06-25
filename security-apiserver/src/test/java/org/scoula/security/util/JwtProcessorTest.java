package org.scoula.security.util;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.config.RootConfig;
import org.scoula.security.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class, SecurityConfig.class})
@Log4j2
class JwtProcessorTest {

    @Autowired
    JwtProcessor jwtProcessor;

    @Test
    void generateToken() {

        // 테스트에 사용할 username
        String username = "user0";

        String role = "ROLE_ADMIN";

        String token = jwtProcessor.generateToken(username, role);

        log.info("생성된 토큰: {}", token);
    }

    @Test
    void validateToken_Valid(){
        String username = "testuser";
        String role = "ROLE_ADMIN";

        // 토큰 생성
        String token = jwtProcessor.generateToken(username, role);

        log.info("생성된 토큰: {}", token);
        
        // 토큰 검증
        boolean isValid = jwtProcessor.validateToken(token);

        log.info("검증결과(true/false): {}", isValid);
    }

    /* 5분 경과 후 테스트 (수동으로 만료된 토큰 사용) */
    @Test
    void validateToken_Expired() {
        String expiredToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMCIsImlhdCI6MTc1MDY3ODA2OCwiZXhwIjoxNzUwNjc4MzY4fQ.3a5iLHwO0dvg_QyaYn7ML5yn5kdsxh_uO88L_NQjjhU";


        assertThrows(ExpiredJwtException.class, () -> {
            jwtProcessor.getUsername(expiredToken);  // 만료된 토큰 사용 시 예외 발생
        });

        // 검증 메서드는 예외를 잡아서 false 반환
        boolean isValid = jwtProcessor.validateToken(expiredToken);
        assertFalse(isValid, "만료된 토큰은 무효해야 합니다.");
    }

    @Test
    void validateToken_Invalid() {
        // 잘못된 형식의 토큰
        String invalidToken = "invalid.jwt.token";

        boolean isValid = jwtProcessor.validateToken(invalidToken);

        assertFalse(isValid, "잘못된 형식의 토큰은 무효해야 합니다.");
    }

}