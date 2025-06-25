package org.scoula.security.account.mapper;


import org.scoula.security.account.domain.MemberVO;

public interface UserDetailsMapper {

    // username: PK(기본키)
    MemberVO get(String username);
}
