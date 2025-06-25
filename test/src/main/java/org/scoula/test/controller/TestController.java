package org.scoula.test.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // 싱글톤 + 컨트롤러로 스프링 등록(주소 ->> 함수정의한 것이 핸들러매퍼에 등록이됨)
@RequestMapping("/test")    // /test/아래로 주소가 붙음 ex. /test/delete , /test/insert...
@Log4j2
public class TestController {

    @RequestMapping("") // test --> get/post/delete/put 상관없을 때
    public String test(){
        log.info("TestController =======");
        return "test";  // view파일 이름 프론트컨트롤러에서 리턴
    }

    @GetMapping("/insert")
    public String insert(){
        log.info("TestController2 =====");
        return "insert";
    }
}
