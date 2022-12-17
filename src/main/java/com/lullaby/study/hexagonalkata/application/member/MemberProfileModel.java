package com.lullaby.study.hexagonalkata.application.member;

import com.lullaby.study.hexagonalkata.domain.model.member.Member;

public class MemberProfileModel {
    private String account;
    private String nickname;
    private Integer point;

    public MemberProfileModel(Member member, Integer point) {
        this.account = member.getAccount();
        this.nickname = member.getNickname();
        this.point = point;
    }

    public String getAccount() {
        return account;
    }

    public String getNickname() {
        return nickname;
    }

    public Integer getPoint() {
        return point;
    }
}
