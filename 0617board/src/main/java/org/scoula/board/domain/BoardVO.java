package org.scoula.board.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardVO {

    private Long no;          // 게시글 번호 (Primary Key)
    private String title;     // 제목
    private String content;   // 내용
    private String writer;    // 작성자
    private Date regDate;     // 등록일
    private Date updateDate;  // 수정일
    private List<BoardAttachmentVO> attaches; // 첨부파일 목록
}
