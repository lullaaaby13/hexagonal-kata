package com.lullaby.study.hexagonalkata.interfaces.auth;


import jakarta.validation.constraints.Size;

public class SignUpRequest {
    @Size(min = 4, max = 20)
    private final String account;
    @Size(min = 4, max = 20)
    private final String password;
    @Size(min = 4, max = 20)
    private final String nickname;

    public SignUpRequest(String account, String password, String nickname) {
        this.account = account;
        this.password = password;
        this.nickname = nickname;
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
}
