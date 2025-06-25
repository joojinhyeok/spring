package org.scoula.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Log4j2
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor    // final field 생성자 자동 생성
public class BoardController {

    // 의존성 주입
    final private BoardService boardService;

    // 목록조회
    @GetMapping("/list")
    public String list(Model model){
        log.info("list 동작");

        List<BoardDTO> boardDTOList = boardService.getList();
        log.info(boardDTOList.get(1));

        // boardDTO 리스트 모델에 담기
        model.addAttribute("list", boardService.getList());
        log.info("boardList 1번째 값: {}", boardDTOList.get(1));

        return "board/list";
    }

    // 상세조회 (단건조회)
    @GetMapping("/get")
    public void get(@RequestParam("no") long no, Model model){
        log.info("/get");
        BoardDTO boardDTO = boardService.get(no);
        log.info("boardService.get() =======> boardDTO: {}", boardDTO);

        model.addAttribute("board", boardDTO);
    }

    // 글등록
    @PostMapping("/create")
    public String create(BoardDTO board){
        log.info("create: " + board);

        boardService.create(board);

        return "redirect:/board/list";
    }

    @PostMapping("/update")
    public String update(BoardDTO board){
        log.info("/update");
        boolean result = boardService.update(board);

        log.info("update 결과: {}", result);

        return "redirect:/board/list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("no") long no){
        log.info("/delete");

        boolean result = boardService.delete(no);

        return "redirect:/board/list";
    }
}
