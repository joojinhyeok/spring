package org.scoula.config;


import org.scoula.domain.Parrot;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration  // 컨텍스트 파일임을 설정
@ComponentScan(basePackages = "org.scoula.domain")
public class ProjectConfig3 {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig3.class);

        Parrot p1 = context.getBean(Parrot.class);
        System.out.println(p1);
    }
}
