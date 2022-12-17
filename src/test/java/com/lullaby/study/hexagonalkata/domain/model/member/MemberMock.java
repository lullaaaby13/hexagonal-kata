package com.lullaby.study.hexagonalkata.domain.model.member;

public class MemberMock {
    public static final Member 멤버_1;

    static {
        멤버_1 = Member.join("normal", "1234", "lullaby");
        멤버_1.setId(1L);
    }

}
