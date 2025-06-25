package org.lecture.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        ClassA classA = context.getBean(ClassA.class);

        System.out.println("어플리케이션 잘 동작중~~~");

        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        if(str.equals("1")){

            classA.doSomething();
        }

        scanner.close();

    }
}
