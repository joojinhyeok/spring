package org.scoula.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.board.domain.BoardVO;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.mapper.BoardMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

@Service    // @Component + 서비스역할의 클래스라는 것이 스프링에 등록
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    // 전처리해서 dao의 메서드를 불러서 db처리해달라고 해야함.
    private final BoardMapper boardMapper;

    @Override
    public List<BoardDTO> getList() {
        List<BoardVO> list = boardMapper.getList();
        // vo vo vo vo vo vo
        // DTO DTO DTO DTO DTO
        return boardMapper.getList().stream()
                .map(BoardDTO::of)
                // BoardDTO의 스트림
                .toList();
    }

    @Override
    public BoardDTO get(Long no) {
        log.info("get......" + no);

        BoardVO vo = boardMapper.get(no);               // DB에서 VO 조회
        BoardDTO dto = BoardDTO.of(vo);                 // VO → DTO 변환

        return Optional.ofNullable(dto)                 // null 안전성 처리
                .orElseThrow(NoSuchElementException::new);  // 없으면 예외 발생
    }

    @Override
    public void create(BoardDTO board) {

        // 전달받은 BoardDTO를 vo로 변환
        BoardVO boardVo = board.toVo();

        // db로 전달
        boardMapper.create(boardVo);

        // Vo에 생성된 pk 값을 DTO에 전달 -> 후속작업
        board.setNo(boardVo.getNo());
    }

    @Override
    public boolean update(BoardDTO board) {
        // 요청보낸사람 -> 게시글의 주인인지 확인하는 로직

        int result = boardMapper.update(board.toVo());

        return result == 1;
    }

    @Override
    public boolean delete(Long no) {

        int result = boardMapper.delete(no);

        return result == 1;
    }
}
