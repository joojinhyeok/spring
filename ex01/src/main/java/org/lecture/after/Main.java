package org.lecture.after;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        // AppConfig의 @componentScan이 동작
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        NotiService service = context.getBean(NotiService.class);

        service.notify("스프링 DI 예제");

    }
}