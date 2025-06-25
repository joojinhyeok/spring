package org.scoula.board.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.board.domain.BoardVO;
import org.scoula.config.RootConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { RootConfig.class })
@Log4j2
class BoardMapperTest {

    @Autowired
    private BoardMapper mapper;
    // BoardMapper인터페이스를 구현한 클래스의 객체를 mapper 변수에 넣어주세요
    // spring과 mybatis가 환경설정한 것을 기반으로 해서
    // 자동으로 클래스를 만들고 싱글톤도 만들어줌.

//    @Test
//    void getList() {
//        // 단위 테스트
//        List<BoardVO> list = mapper.getList();
//        for(int i = 0; i < list.size(); i++){
//            log.info(list.get(i));
//        }
//    }

//    @Test
//    @DisplayName("BoardMapper의 목록 불러오기")
//    public void getList() {
//        for(BoardVO board : mapper.getList()) {
//            log.info(board);
//        }
//    }

    @Test
    @DisplayName("BoardMapper의 게시글 읽기")
    public void get() {

// 존재하는 게시물 번호로 테스트
        BoardVO board = mapper.get(1L);
        log.info(board);
    }

    @Test
    @DisplayName("BoardMapper의새글작성")
    public void create() {
        BoardVO board = new BoardVO();
        board.setTitle("새로작성하는글");
        board.setContent("새로작성하는내용");
        board.setWriter("user00");

        mapper.create(board);

        log.info(board); //
    }

    @Test
    @DisplayName("BoardMapper의 글 수정")
    public void update() {
        BoardVO board = new BoardVO();
        board.setNo(5L);
        board.setTitle("수정된 제목");
        board.setContent("수정된 내용");
        board.setWriter("user00");

        int count = mapper.update(board);

        assertEquals(1, count);
        log.info("Update Result Int: {}", count);
    }

    @Test
    @DisplayName("BoardMapper의 글 삭제")
    public void delete(){

        int count = mapper.delete(5L);
        assertEquals(1, count);
        log.info("Delete Result Int: {}", count);
    }
}
