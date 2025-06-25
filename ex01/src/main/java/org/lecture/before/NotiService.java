package org.lecture.before;


public class NotiService {

    /*
    * 문제점
    * 1. 기존 클래스를 직접 열고 수정해야함
    * 2. NotiService의 내부 코드가 변경이 일어난다.
    * */

    // private EmailSender emailSender = new EmailSender();
    private SMSSender smsSender = new SMSSender();

    public void notify(String message){

        smsSender.send(message);
    }
}
