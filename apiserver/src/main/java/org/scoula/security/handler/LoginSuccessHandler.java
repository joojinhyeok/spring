package org.scoula.security.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.security.account.domain.CustomUser;
import org.scoula.security.dto.AuthResultDTO;
import org.scoula.security.dto.UserInfoDTO;
import org.scoula.security.util.JsonResponse;
import org.scoula.security.util.JwtProcessor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@Component
@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtProcessor jwtProcessor;

    private AuthResultDTO makeAuthResult(CustomUser user) {

        // JsonResponse로 보낼 값들 만들어야함
        // 성공했으므로 Authentication객체가 이미 만들어져서
        // SecurityContextHolder에 들어가 있음

        String username = user.getUsername();
        // 토큰 생성
        String token = jwtProcessor.generateToken(username);
        // 토큰 + 사용자 기본 정보(사용자명,...)를 묶어서 AuthResultDTO 구성
        return new AuthResultDTO(token, UserInfoDTO.of(user.getMember()));
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

// Principal
        CustomUser user = (CustomUser) authentication.getPrincipal();
// JSON
        AuthResultDTO result = makeAuthResult(user);
        JsonResponse.send(response, result);
    }
}