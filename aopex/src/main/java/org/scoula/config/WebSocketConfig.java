package org.scoula.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


/*
* STOMP 메시지 흐름
* 1. 클라이언트 연결 -> ws://localhost:8080/chat-app (소켓연결)
* 2. 토픽 구독 -> "/topic/?"
* 3. 메시지 발행 -> "/app/?"
* 4. 서버에서 처리 -> Controller @MessageMapping("/?")이 붙은 핸들러 메서드 실행
* 5. 브로드캐스트(구독자들한테 전송) @SendTo("/topic/?") ?을 구독한 모든 구독자에게 전송
* */
@Configuration
@EnableWebSocketMessageBroker // WebSocket 활성화, 메시지브로커(STOMP) 기능 활성화
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 구독시 사용할 토픽 접두어
        config.enableSimpleBroker("/topic");
        // 클라이언트가 발행 시 사용해야하는 접두어
        config.setApplicationDestinationPrefixes("/app");
    }


    // STOMP 엔드포인트
    // 클라이언트가 WebSocket 연결을 시작할때 사용하는 URL(HTTP 핸드쉐이크)
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat-app") // 접속 엔드포인트, ws://localhost:8080/chat-app
                .setAllowedOrigins("*");
                // CORS 허용 모든 origin
        // 운영시 구체적인 도메인 지정을 하는게 좋음
        // .setAllowedOrigins("https://[도메인]") ex) "https://naver.com"
        // .withSockJS() // websocke을 지원하지 않는 브라우저 대응

    }


}
