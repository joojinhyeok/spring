package org.scoula.advice;


import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect // 우리가 관심사(advice) 들을 모아놓은 클래스라는것을 의미
@Log4j2
@Component
public class LogAdvice {

    // 어떤 타겟이 되는 클래스의 메서드를 호출해서 실행할 때
    // 어떤 부가적인 기능을 넣을지 충고해주는 클래스
    // --> 클래스 이름 위에 @Aspect이라고 해주어야함
    @Before("execution(* org.scoula.sample.service.SampleService*.*(..))")
    public void logBefore() {
        log.info("==================================");
    }

    // 부가적인 기능을 넣을 타겟이 되는 SampleService클래스 안에 있는
    // 조인포인트가 되는 모든 메서드를 호출해서 실행하기 전에(이벤트라고 인식함)
    // logBefore()메서드를 먼저 호출해라! 설정
    // --> 이벤트가 발생하면 aop프락시 객체가 logBefore()메서드를 먼저 호출함
    // 스프링에서 사용된 설계 기법 3가지 --> 24~26정도로 설계 패턴을 정리
    // 프락시(대리인) 설계 패턴(design pattern)
    // 싱글톤 디자인 패턴
    // 팩토리 디자인 패턴


    @Before("execution(* org.scoula.sample.service.SampleService*.doAdd(String, String)) && args(str1, str2)")
    public void logBeforeWithParam(String str1, String str2) {
        log.info("str1:" + str1);
        log.info("str2:" + str2);
    }

    @AfterThrowing(pointcut = "execution(* org.scoula.sample.service.SampleService*.*(..))", throwing="exception")
    public void logException(Exception exception) {
        log.info("Exception...!!!!");
        log.info("exception: " + exception);
    }

    @Around("execution(* org.scoula.sample.service.SampleService*.*(..))")
    public Object logTime(ProceedingJoinPoint pjp) {
        long start = System.currentTimeMillis();

        log.info("Target: " + pjp.getTarget());
        log.info("Param: " + Arrays.toString(pjp.getArgs()));

        Object result = null;
        try {
            result = pjp.proceed(); // 실제 메서드 호출
        } catch(Throwable e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();

        log.info("TIME: " + (end - start));

        return result;
    }
}
