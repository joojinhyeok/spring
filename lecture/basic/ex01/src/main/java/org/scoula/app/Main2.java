package org.scoula.app;


import org.scoula.config.ProjectConfig2;
import org.scoula.domain.Parrot;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main2 {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig2.class);

        // 이름이 "miki"인 Bean 가져오기
        Parrot p = context.getBean("miki", Parrot.class);

        System.out.println(p.getName());


    }
}