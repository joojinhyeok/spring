package org.scoula.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Log4j2
@RequestMapping("/example")
public class ExampleController {

    @RequestMapping
    public String hello(Model model){

        log.info("========> hello");

        /*
        * Model
        * - 컨트롤러가 View로 데이터를 전달할 때 사용하는 객체
        * - key-value 쌍으로 데이터를 저장
        * */
        model.addAttribute("message", "신규 핸들러 메서드 호출");
        
        return "page/mappingResult";
    }

    // /example/modify 로 들어오는 요청 처리
    // method를 이용해 특정 HTTP 메서드의 요청에만 처리하도록 제한
//    @RequestMapping(value = "/modify", method = RequestMethod.POST) --> 아래 @PostMapping과 같은 의미
    @PostMapping("modify")
    public String modify(Model model){
        model.addAttribute("message", "신규 핸들러 메서드 호출");

        return "page/mappingResult";
    }


    /*
    * @GetMapping
    * @PostMapping
     * @RequestMapping
     * @DeleteMapping
     * */
    @GetMapping("/delete")
    public String delete(Model model){
        model.addAttribute("message", "삭제용 핸들러 메서드 호출함");

        return "page/mappingResult";
    }

    @GetMapping(value = {"/multi/modify", "/multi/delete"})
    public String modifyAndDelete(Model model){
        model.addAttribute("message", "수정 및 삭제 핸들러 메서드 호출..");

        return "page/mappingResult";
    }

}
