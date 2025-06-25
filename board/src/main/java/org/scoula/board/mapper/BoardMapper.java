package org.scoula.board.mapper;


import org.apache.ibatis.annotations.Select;
import org.scoula.board.domain.BoardVO;

import java.util.List;


// @Repository // 저장(db)
// @Component + db 예외처리
public interface BoardMapper {

    //@Select("select * from tbl_board order by no desc")
    List<BoardVO> getList();

    // 상세검색
    public BoardVO get(Long no);

    // 게시판 글쓰기 ==> vo가 거의 대부분
    public void create(BoardVO board);

    // 게시판 글 업데이트
    public int update(BoardVO boardVO);

    // 게시판 글 삭제
    public int delete(long no);

}