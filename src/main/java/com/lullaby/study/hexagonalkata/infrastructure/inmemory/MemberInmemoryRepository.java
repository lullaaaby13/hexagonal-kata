package com.lullaby.study.hexagonalkata.infrastructure.inmemory;

import com.lullaby.study.hexagonalkata.domain.model.member.Member;
import com.lullaby.study.hexagonalkata.domain.model.member.MemberRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Repository
public class MemberInmemoryRepository implements MemberRepository {

    private final Map<Long, Member> map = new HashMap<>();

    @Override
    public Optional<Member> find(Long memberId) {
        if (map.containsKey(memberId)) {
            return Optional.of(map.get(memberId));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Member> findByAccount(String account) {
        return map.values().stream()
                .filter(it -> it.getAccount().equals(account))
                .findFirst();
    }
    @Override
    public void save(Member member) {
        if (member.getId() == null ) {
            member.setId(new Random().nextLong());
        }
        this.map.put(member.getId(), member);
    }

    // TODO 테스트 끝나면 지워야 됨
    public void clean() {
        this.map.clear();
    }

}
