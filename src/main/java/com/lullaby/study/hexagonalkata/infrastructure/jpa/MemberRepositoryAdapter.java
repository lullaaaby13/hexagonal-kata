package com.lullaby.study.hexagonalkata.infrastructure.jpa;

import com.lullaby.study.hexagonalkata.domain.model.member.Member;
import com.lullaby.study.hexagonalkata.domain.model.member.MemberRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//@Repository
public class MemberRepositoryAdapter implements MemberRepository {

    @Override
    public Optional<Member> find(Long memberId) {
        return Optional.empty();
    }

    @Override
    public Optional<Member> findByAccount(String account) {
        return Optional.empty();
    }

    @Override
    public void save(Member member) {

    }

}
