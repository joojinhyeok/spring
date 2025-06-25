package org.lecture.di;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClassA {

    private final ClassB classB;

    @Autowired  // 생성자 주입
    public ClassA(ClassB classB){
        this.classB = classB;
    }

    public void doSomething(){
        System.out.println("클래스 A 동작");
        classB.doSomething();
    }

}
