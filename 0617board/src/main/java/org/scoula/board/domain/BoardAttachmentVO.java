package org.scoula.board.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.scoula.common.util.UploadFiles;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardAttachmentVO {

    private long no;
    private long bno;
    private long size;
    private String contentType;
    private String path;
    private String filename;
    private Date regDate;

    // of
    // MultipartFile 정보로 BoardAttachmentVO 생성
    // MultipartFile, Board No, 파일 경로 매개변수로 받아옴
    public static BoardAttachmentVO of(MultipartFile part, Long bno, String path){
        return builder()
                .bno(bno)   // 해당하는 게시글 번호
                .filename(part.getOriginalFilename())   // 원본이름
                .path(path) // 저장경로
                .size(part.getSize())   // 파일크기
                .contentType(part.getContentType())
                .build();
    }

    public String getFileSize(){
        return UploadFiles.getFormatSize(size);
    }
}
