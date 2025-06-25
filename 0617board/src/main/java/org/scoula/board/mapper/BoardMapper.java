package org.scoula.board.mapper;

import org.apache.ibatis.annotations.Select;
import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.domain.BoardVO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository //저장(db)
//@Component + db예외처리
public interface BoardMapper {

    //@Select("select * from tbl_board order by no desc")
    public List<BoardVO> getList();
    public BoardVO get(Long no);
    public void create(BoardVO board);
    public int update(BoardVO board);
    public int delete(Long no);


    // 기존 메서드들...

    /* 첨부파일 관련 메서드 추가 */
    // 첨부파일 등록
    public void createAttachment(BoardAttachmentVO attach);

    // 특정 게시글의 첨부 파일 목록 조회
    public List<BoardAttachmentVO> getAttachmentList(Long bno);

    // 특정 첨부 파일 1개 조회
    public BoardAttachmentVO getAttachment(Long no);

    // 특정 첨부 파일 1개 삭제
    public int deleteAttachment(Long no);


}
