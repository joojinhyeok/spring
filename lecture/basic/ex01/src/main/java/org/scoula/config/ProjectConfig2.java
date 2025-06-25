package org.scoula.config;


import org.scoula.domain.Parrot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig2 {

    @Bean
    public Parrot parrot1(){
        Parrot p = new Parrot();
        p.setName("Koko");
        return p;
    }

    @Bean(name = "miki")
    public Parrot parrot2(){
        Parrot p = new Parrot();
        p.setName("Miki");
        return p;
    }

    @Bean
    public Parrot parrot3(){
        Parrot p = new Parrot();
        p.setName("Riki");
        return p;
    }
}
