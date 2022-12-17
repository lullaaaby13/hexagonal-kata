package com.lullaby.study.hexagonalkata.application.member;

import com.lullaby.study.hexagonalkata.domain.model.member.Member;
import com.lullaby.study.hexagonalkata.domain.model.member.MemberNotFoundException;
import com.lullaby.study.hexagonalkata.domain.model.member.MemberRepository;
import com.lullaby.study.hexagonalkata.domain.model.pointhistory.PointHistory;
import com.lullaby.study.hexagonalkata.domain.model.pointhistory.PointHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    private final PointHistoryRepository pointHistoryRepository;

    public MemberServiceImpl(MemberRepository memberRepository, PointHistoryRepository pointHistoryRepository) {
        this.memberRepository = memberRepository;
        this.pointHistoryRepository = pointHistoryRepository;
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

    @Override
    public MemberProfileModel getProfile(Long memberId) {
        Member member = memberRepository.find(memberId)
                .orElseThrow(MemberNotFoundException::new);
        int point = pointHistoryRepository.findAllByMember(memberId)
                .stream()
                .mapToInt(PointHistory::getAmountOfChange)
                .sum();
        return new MemberProfileModel(member, point);
    }

}
