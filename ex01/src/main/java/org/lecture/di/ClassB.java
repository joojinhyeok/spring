package org.lecture.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClassB {

    private final ClassA classA;

    @Autowired
    public ClassB(ClassA classA){
        this.classA = classA;
    }


    public void doSomething() {

        System.out.println("클래스 B 동작");
        classA.doSomething();
    }
}
