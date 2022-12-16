package com.lullaby.study.hexagonalkata.acceptance;

import com.lullaby.study.hexagonalkata.AcceptanceTest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("회원 기능 테스트")
public class AuthAcceptanceTest extends AcceptanceTest {

    @DisplayName("회원 가입에 성공 한다.")
    @Test
    void success_sign_up() {
        record RequestBody(String account, String password, String nickname) {}

        ExtractableResponse<Response> extract = RestAssured
                .given()
                    .log().all()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(new RequestBody("test_user", "1234", "lullaby"))
                .when()
                    .post("/auth/sign-up")
                .then()
                    .log().all()
                .extract();

        assertEquals(201, extract.statusCode());
    }

    @DisplayName("계정명이 4글자 이하면 가입 실패")
    @Test
    void fail_sign_up_1() {

    }

    @DisplayName("계정명이 4글자 이하면 가입 실패")
    @Test
    void fail_sign_up_2() {

    }

}
