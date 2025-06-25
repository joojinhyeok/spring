package org.scoula.ex03.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequestMapping("/sample") // 공통 URL prefix
public class SampleController {

    @RequestMapping("")
    @ResponseBody
    public String basic() {
        return "ok";
    }


    @RequestMapping(value = "/basic", method = {RequestMethod.GET, RequestMethod.POST}) //  url: /sample/basic
    public void basicGet(){
        log.info("basic get..............");
    }

    @GetMapping("/basicOnlyGet")
    public void basicGet2(){
        log.info("basic get only get...............");
    }

    // http://localhost:8080/sample/ex04?name=aaa&age=11&page=9
/*    @GetMapping("/ex04")
    public String ex04(SampleDTO dto, int page){
        log.info("dto: " + dto);
        log.info("pageL " + page);

        return "sample/ex04";
    }*/

    @GetMapping("/ex04")
    public String ex04(SampleDTO dto, @ModelAttribute("page") int page) {

        log.info("dto: " + dto);
        log.info("page: " + page);
        return "sample/ex04";

    }

    @GetMapping("/ex05")
    public void ex05() {
        log.info("/ex05........");
    }

    @GetMapping("/ex06")
    public String ex06(RedirectAttributes ra) {
        ra.addAttribute("name", "AAA");
        ra.addAttribute("age", 10);
        return "redirect:/sample/ex06-2";
    }

    @GetMapping("/ex06-2")
    public void ex06_2(String name, int age, Model model) {
        
        model.addAttribute("name", name);
        model.addAttribute("age", age);
    }
}
