package org.scoula.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.service.BoardService;
import org.scoula.common.util.UploadFiles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

@Log4j2
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService service;

//    public BoardController(BoardService service) {
//        this.service = service;
//    }

    @GetMapping("/list") //board/list
    public void list(Model model) {
        //db에서 가지고 온 것 있어야함.
        //Controller --> Service --> dao
        log.info("===============> BoardController /list");
        model.addAttribute("list", service.getList());
        //요청한 주소와 views의 호출할 파일명이 같으면 return안해도됨.
    }


    @GetMapping({"/get", "/update"})
    public void get(@RequestParam("no") Long no, Model model) {
        log.info("/get or update");
        model.addAttribute("board", service.get(no));
        /* url에 따라 jsp 파일을 "board/get" 또는 "board/update" 반환 */
    }

    @GetMapping("/create")
    public void create() {
        log.info("CREATE");
    }

    @PostMapping("/create")
    public String create(BoardDTO board) {
        log.info("create: " + board);
        service.create(board);
        return "redirect:/board/list";

    }

    @PostMapping("/update")
    public String update(BoardDTO board) {
        log.info("update:" + board);
        service.update(board);

        return "redirect:/board/list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("no") Long no) {

        log.info("delete..." + no);
        service.delete(no);
        return "redirect:/board/list";
    }

    @GetMapping("/download/{no}")
    @ResponseBody
    public void download(@PathVariable("no") Long no, HttpServletResponse response) throws Exception {

        // 첨부파일 조회
        BoardAttachmentVO attachmentVO = service.getAttachment(no);

        File file = new File(attachmentVO.getPath());

        UploadFiles.download(response, file, attachmentVO.getFilename());
    }
}
