package com.lullaby.study.hexagonalkata.interfaces.auth;

import jakarta.validation.constraints.NotBlank;

public class SignInRequest {
    @NotBlank
    String account;
    @NotBlank
    String password;

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

}
