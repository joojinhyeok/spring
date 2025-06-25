package org.scoula.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Log4j2
@RequestMapping("/exception")
public class ExceptionTestController {

    // http://localhost:8080/exception/test?age=-1
    @GetMapping("/test")
    public String test(@RequestParam int age){
        
        
        
        if (age < 0){
            throw new IllegalArgumentException("나이는 0보다 커야합니다");
        }
        return "ok";
    }

    /*
    * @ExceptionHandler
    * - Spring MVC에서 발생한 예외를 잡아서, 개발자가 직접 처리할 수 있게 메서드에 붙이는 어노테이션
    * 
    * 동작
    * 1. 클라이언트가 컨트롤러에 요청을 보냄
    * 2. 컨트롤러 내부에서 처리중 예외 발생
    * 3. Spring이 @ExceptionHandler를 찾음(해당하는 예외 클래스)
    *   -> 1. 예외가 발생한 컨트롤러 내부에서 찾음
    *   -> 2. 없으면 @ControllerAdvice 어노테이션이 붙은 클래스를 찾는다.
    * 4. 매핑된 메서드 실행 -> 에러페이지를 반환 (or JSON 응답 반환)
    * */

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgument(IllegalArgumentException ex, Model model){

        log.error("IllegalArgumentException 발생 ===========> {}", ex);

        model.addAttribute("errorMessage", ex.getMessage());

        return "illegal_error";
    }
}