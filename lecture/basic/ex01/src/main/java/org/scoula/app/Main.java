package org.scoula.app;


import org.scoula.config.ProjectConfig;
import org.scoula.domain.Parrot;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        /*
        * ApplicationContext란?
        * - Spring 컨테이너의 대표 인터페이스
        * - 쉽게 말해 Bean 저장소
        * - Spring이 관리하는 객체(Bean)들을 이 안에 담아두고 꺼내 쓸 수 있게 해줌
        *
        * AnnotationconfigapplicationContext란?
        * - ApplicationContext의 구현 클래스 중 하나
        * - Java 기반 설정 클래스를 읽어서 Bean을 등록하는 컨테이너
        * */

        // 전체 흐름
        // 1. 설정 클래스를 읽어서
        // 2. @Configuration 붙은 클래스(ProjectConfig.class)를 설정 파일로 인식하고
        // 3. @Bean이 붙은 메서드를 Spring이 실행해서 객체를 생성함
        // 4. 생성된 객체를 Bean으로 등록하고, 메서드 이름을 Bean으로 사용함
        ApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        // 5. getBean(타입)으로 Parrot 타입의 Bean을 가져옴
        Parrot p = context.getBean(Parrot.class);

        // 6. 추출된 Parrot의 name 출력
        System.out.println(p.getName());


    }
}