package org.scoula.board.mapper;

import org.scoula.board.domain.BoardVO;

import java.util.List;

public interface BoardMapper {

    // 게시판 목록 얻기
    List<BoardVO>getList();

    // 게시판 글 하나 얻기
    public BoardVO get(long no);
}
