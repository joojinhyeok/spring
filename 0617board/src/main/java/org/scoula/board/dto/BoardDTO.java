package org.scoula.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.domain.BoardVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
* DTO
* - 클라이언트와 서버간에 데이터를 주고 받을 때 사용
* */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //build가 쉽게 해당 객체로 만들수있게 어노테이션!
public class BoardDTO {
    private Long no;
    private String title;
    private String content;
    private String writer;
    private Date regDate;
    private Date updateDate;

    // db에서 읽어온 첨부파일 정보를 저장
    private List<BoardAttachmentVO> attaches;

    // 사용자가 업로드한 실제 파일들
    // db에 직접 저장되는게 아니라 백엔드 파일 저장 처리할 때 사용
    private List<MultipartFile> files = new ArrayList<>();

    //vo --> dto (of())
    //public static BoardDTO of(BoardVO vo){
//        BoardDTO dto = new BoardDTO();
//        dto.setNo(vo.getNo());
//        dto.setTitle(vo.getTitle());
//        dto.setContent(vo.getContent());
//        dto.setWriter(vo.getWriter());
//        dto.setRegDate(vo.getRegDate());
        //BoardDTO dto = new BoardDTO(vo.getNo(), vo.getTitle(), vo.getContent());

        //복잡하지도 않고, 순서도 상관없이 값을 넣어서 객체를 만들고자 하는 방법
        //Builder객체 이용

        // VO  DTO 변환(of 메서드)
        public static BoardDTO of(BoardVO vo) {
            return vo == null ? null : BoardDTO.builder()
                    .no(vo.getNo())
                    .title(vo.getTitle())
                    .content(vo.getContent())
                    .writer(vo.getWriter())
                    .regDate(vo.getRegDate())
                    .updateDate(vo.getUpdateDate())
                    .attaches(vo.getAttaches())
                    .build();
        }
        // DTO  VO 변환
        public BoardVO toVo() {
            return BoardVO.builder()
                    .no(no)
                    .title(title)
                    .content(content)
                    .writer(writer)
                    .regDate(regDate)
                    .updateDate(updateDate)
                    .attaches(attaches)
                    .build();
        }
    }

