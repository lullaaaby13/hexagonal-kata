package com.lullaby.study.hexagonalkata.domain.model.member;

public class Member {

    private Long id;

    private String account;

    private String password;

    private String nickname;

    private Integer point;

    private Member() {}

    public static Member join(String account, String password, String nickname) {
        Member member = new Member();
        member.account = account;
        member.password = password;
        member.nickname = nickname;
        member.point = 0;

        member.validate();

        return member;
    }

    private void validate() {
        if (this.account.length() < 4 || this.account.length() > 20) {
            throw new MemberFieldInvalidException();
        }

        if (this.password.length() < 4 || this.password.length() > 20) {
            throw new MemberFieldInvalidException();
        }

        if (this.nickname.length() < 4 || this.nickname.length() > 20) {
            throw new MemberFieldInvalidException();
        }
    }

    public Long getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public Integer getPoint() {
        return point;
    }

    // TODO 테스트 개발 끝나면 지워야 됨
    public void setId(Long id) {
        this.id = id;
    }
}
