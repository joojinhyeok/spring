package org.scoula.board.service;

import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.dto.BoardDTO;

import java.util.List;

public interface BoardService {
    public List<BoardDTO> getList();
    public BoardDTO get(Long no);
    public void create(BoardDTO board);
    public boolean update(BoardDTO board);
    public boolean delete(Long no);

    // 첨부파일 단일 조회
    BoardAttachmentVO getAttachment(Long no);

    // 첨부파일 삭제
    boolean deleteAttachment(Long no);
}