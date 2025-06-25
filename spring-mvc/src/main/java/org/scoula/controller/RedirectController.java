package org.scoula.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
public class RedirectController {

    @GetMapping("/result")
    public String resultPage(){
        return "page/mappingResult";
    }


    /*
    * RedirectAttributes
    * - 리다이렉트시 데이터를 임시로 전달할 수 있는 객체
    * - addFlashAttribute: 일회성 데이터 전송(세션에 저장되어 다음요청에만 적용됨)
    * */

    @GetMapping("/register")
    public String registerUser(RedirectAttributes rttr) {

        log.info("회원가입 진행중 .. ");

        rttr.addFlashAttribute("message", "회원가입 성공!");

        return "redirect:/result";
    }
}
