package org.scoula.security.filter;

import org.scoula.security.dto.LoginDTO;
import org.scoula.security.handler.LoginFailureHandler;
import org.scoula.security.handler.LoginSuccessHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public JwtUsernamePasswordAuthenticationFilter(
            AuthenticationManager authenticationManager,
            LoginSuccessHandler loginSuccessHandler,
            LoginFailureHandler loginFailureHandler) {
        super(authenticationManager);
        setFilterProcessesUrl("/api/auth/login");
        setAuthenticationSuccessHandler(loginSuccessHandler);
        setAuthenticationFailureHandler(loginFailureHandler);

    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        // 1. http body에 들어온 json --> dto(LoginDTO.of())
        LoginDTO loginDTO = LoginDTO.of(request);
        // 인증해달라고 인증매니저에 요청
        // 2. 인증매니저에게 로그인정보를 줄때는 Token객체 만들어서 주어야함
        // 인증정보 Token만들기(<--dto)
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());


        // 3. 인증매니저에 토큰을 주면서 인증해줘라고 요청
        // --> 성공하면 Authentication 객체를 생성해서 리턴해줌
        return getAuthenticationManager().authenticate(authenticationToken);
    }
}
