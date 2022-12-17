package com.lullaby.study.hexagonalkata.domain.model.member;

import java.util.Optional;

public interface MemberRepository {

    Optional<Member> find(Long memberId);

    Optional<Member> findByAccount(String account);

    void save(Member member);

}
