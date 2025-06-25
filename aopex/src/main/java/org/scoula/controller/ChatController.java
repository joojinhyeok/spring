package org.scoula.controller;

import lombok.extern.log4j.Log4j2;
import org.scoula.domain.ChatMessage;
import org.scoula.domain.GreetingMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@Log4j2
public class ChatController {

    // client -> "/app/hello"

    /*
    * 클라이언트가 "/app/hello" 발행
    * -> greeting 핸들러메서드가 수신해 처리
    * 처리결과를 "topic/greetings" 토픽의 모든 구독자에게 전송
    * */

    @MessageMapping("/hello")   // 이 주소로 메시지를 보냈을 때
    @SendTo("/topic/greetings") // 이 이름의 방에 구독한 브라우저에게 이 정보를 보냄.
    // 채팅방에 들어갔을 때 홍길동님 입장하셨습니다.를 보내기 위한 메서드
    public GreetingMessage greeting(GreetingMessage message) throws Exception {
        log.info("greeting: " + message);
        return message;
    }




    @MessageMapping("/chat")
    @SendTo("/topic/chat")
    // 메시지를 보냈을 때 받기 위한 메서드
    public ChatMessage chat(ChatMessage message) throws Exception {
        log.info("chat received: " + message);
        return message;
    }
}