package org.scoula.app;

import org.scoula.config.ProjectConfig;
import org.scoula.domain.Parrot;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {

    /*
    * IOC 컨테이너의 장점
    * 1. 의존성 자동 주입(DI) -> 필요한 객체를 자동으로 연결해줌
    * 2. 낮은 결합도 -> 클래스간 강한 연결없이 유연한 구조
    * 3. 객체 생명주기 자동 관리 -> 싱글톤
    * 4. 테스트 용이
    * */

    public static void main(String[] args) {
        // 스프링을 쓰지 않으면 필요할 때 new 키워드 써서 개발자가 객체를 생성했음
        // 프로그래머가 객체를 생성하는 경우
        Parrot p = new Parrot();
        p.setName("Test");
        System.out.println(p.getName());
        System.out.println(p);// 주소1

        // 스프링이 객체를 생성한 것을 가지고 와서 사용해보자
        ApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        Parrot p2 = context.getBean(Parrot.class); // Parrot 클래스로 만들어진 객체 get

        System.out.println(p2.getName());

        System.out.println(p2); // 주소2

        Parrot p3 = context.getBean(Parrot.class);  // Parrot 클래스로 만들어진 객체 get

        System.out.println(p3); // 주소3
    }
}