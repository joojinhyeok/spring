package org.scoula.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.scoula.ex03.controller")
public class RootConfig {

    // db 사용 --> google mybatis 사용 설정
    // 트랜잭션 설정
}
