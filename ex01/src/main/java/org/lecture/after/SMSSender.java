package org.lecture.after;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary // 동일한 타입의 여러 Bean 중 우선 주입됨
public class SMSSender extends EmailSender{

    // 문자 보내는 기능이라고 가정
    @Override
    public void send(String message){
        System.out.println("SMS 발송: " + message);
    }
}
