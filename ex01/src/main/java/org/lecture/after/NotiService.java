package org.lecture.after;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotiService {

    private final EmailSender emailSender;

    @Autowired
    public NotiService(EmailSender emailSender){
        this.emailSender = emailSender;
    }

    public void notify(String message){
        emailSender.send(message);
    }
}
