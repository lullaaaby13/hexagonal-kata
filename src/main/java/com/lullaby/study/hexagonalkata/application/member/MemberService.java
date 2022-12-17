package com.lullaby.study.hexagonalkata.application.member;

import com.lullaby.study.hexagonalkata.domain.model.member.Member;
import com.lullaby.study.hexagonalkata.domain.model.member.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void join(JoinCommand command) {
        Optional<Member> member = memberRepository.findByAccount(command.account());

        if (member.isPresent()) {
            throw new AlreadyExistMemberException();
        }
        // TODO 비밀번호 암호화 같은거 해야됨

        Member joinMember = Member.join(command.account(), command.password(), command.nickname());
        memberRepository.save(joinMember);
    }

}
