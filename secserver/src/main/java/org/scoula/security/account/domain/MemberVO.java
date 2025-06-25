package org.scoula.security.account.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO {
    private String username;
    private String password;
    private String email;
    private Date regDate;
    private Date updateDate;

    // Auto테이블의 role(auth컬럼)이 여러개가 필요
    // member의 usernmae과 auth는 다 1:다
    private List<AuthVO> authList;
}
