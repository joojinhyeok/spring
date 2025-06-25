package org.lecture.after;

import org.springframework.stereotype.Component;

// Email 전송 모듈
@Component
public class EmailSender {

    public void send(String message){
        System.out.println("이메일 발송: " + message);
    }
}
