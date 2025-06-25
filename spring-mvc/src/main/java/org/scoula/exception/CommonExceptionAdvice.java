package org.scoula.exception;


import lombok.extern.log4j.Log4j2;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/*
* ControllerAdvice
* - 스프링 MVC에서 전역처리를 하기위해 사용하는 특수 어노테이션
* */
@ControllerAdvice
@Log4j2
public class CommonExceptionAdvice {

    // 모든 종류의 예외를 처리하는 메서드
    @ExceptionHandler(Exception.class)
    public String handleIllegalArgument(Exception ex, Model model){

        log.error("Exception 발생 ===========> {}", ex.getMessage());

        model.addAttribute("exception", ex);

        return "error_page";
    }

    // 404 NOT FOUND 예외처리 메서드
    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle404(NoHandlerFoundException ex, Model model, HttpServletRequest request){

        log.error(ex);

        model.addAttribute("uri", request.getRequestURI());

        return "custom404";
    }
}
