package org.scoula.config;

import org.scoula.domain.Parrot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
* @Configuration
* - 스프링 설정 클래스라는 것을 의미
* - Spring이 이 클래스를 보고 Bean 등록 정보를 포함하고 있다고 인식
* - "이 클래스 안에 Bean 등록할게 관리해줘" 라는 의미
* */
@Configuration  
public class ProjectConfig {

    // Parrot의 리턴 객체를 Bean으로 등록
    @Bean
    public Parrot parrot(){
        Parrot p = new Parrot();
        p.setName("Koko");
        return p;
    }
}
