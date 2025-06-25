package org.scoula.domain;

import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
public class Parrot {

    private String name;

    // getter
    public String getName(){
        return name;
    }

    // setter
    public void setName(String name){
        this.name = name;
    }

    @PostConstruct
    public void init() {
        this.name = "Kiki";
    }
}
