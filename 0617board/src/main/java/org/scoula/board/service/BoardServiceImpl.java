package org.scoula.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.domain.BoardVO;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.mapper.BoardMapper;
import org.scoula.common.util.UploadFiles;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
//@Service //@Component + 서비스역할의 클래스라는 것이 스프링에 등록
public class BoardServiceImpl implements BoardService {

    // 파일 저장될 디렉토리 경로
    private final static String BASE_DIR = "c:/upload/board";

    //전처리해서 dao의 메서드를 불러서 db처리해달라고 해야함.
    private final BoardMapper mapper;

    @Override
    public List<BoardDTO> getList() {
        //List<BoardVO> list =  boardMapper.getList();
        //vo, vo, vo, vo, vo, vo
        //dto, dto, dto, dto, dto
        return mapper.getList().stream() // BoardVO의 스트림
                .map(BoardDTO::of)
                // BoardDTO의 스트림
                .toList();
    // List<BoardDTO> 변환
    }

    @Override
    public BoardDTO get(Long no) {
        log.info("get......" + no);

        BoardDTO board = BoardDTO.of(mapper.get(no));
        return Optional
                .ofNullable(board)
                .orElseThrow(NoSuchElementException::new);

    }

    /* create() 메서드 수정 */
    // 게시글 등록 서비스
    @Transactional  // 여러 DB 작업을 하나의 트랜잭션으로 처리
    @Override
    public void create(BoardDTO board) {
        log.info("create......" + board);

        // 1. 게시글 등록
        BoardVO vo = board.toVo();         // DTO → VO 변환
        mapper.create(vo);            // DB에 저장
        board.setNo(vo.getNo());           // 생성된 PK를 DTO에 설정

        // 2. 첨부파일 처리
        List<MultipartFile> files = board.getFiles();
        if (files != null && !files.isEmpty()) {
            upload(vo.getNo(), files);  // 게시글 번호가 필요하므로 게시글 등록 후 처리
        }
    }

    @Override
    public boolean update(BoardDTO board) {
        log.info("update......" + board);

        return mapper.update(board.toVo()) == 1;

    }

    @Override
    public boolean delete(Long no) {
        log.info("delete...." + no);

        return mapper.delete(no) == 1;
    }

    // 첨부파일 단일 조회
    @Override
    public BoardAttachmentVO getAttachment(Long no) {
        return mapper.getAttachment(no);
    }

    // 첨부파일 삭제
    @Override
    public boolean deleteAttachment(Long no) {
        return mapper.deleteAttachment(no) == 1;
    }

    /**
     * 파일 업로드 처리 (private 메서드)
     * @param bno 게시글 번호
     * @param files 업로드할 파일 목록
     */
    private void upload(Long bno, List<MultipartFile> files) {


        for (MultipartFile part : files) {
            // 빈 파일은 건너뛰기
            if (part.isEmpty()) continue;

            try {
                // 파일을 서버에 저장
                String uploadPath = UploadFiles.upload(BASE_DIR, part);

                // 첨부파일 정보를 DB에 저장
                BoardAttachmentVO attach = BoardAttachmentVO.of(part, bno, uploadPath);
                mapper.createAttachment(attach);

            } catch (IOException e) {
                // @Transactional이 감지할 수 있도록 RuntimeException으로 변환
                throw new RuntimeException(e);
            }
        }
    }
}
