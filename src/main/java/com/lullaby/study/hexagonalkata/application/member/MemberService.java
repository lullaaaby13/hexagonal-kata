package com.lullaby.study.hexagonalkata.application.member;

public interface MemberService {

    void join(JoinCommand command);

    MemberProfileModel getProfile(Long memberId);

}
