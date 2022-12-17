package com.lullaby.study.hexagonalkata.acceptance;

import com.lullaby.study.hexagonalkata.AcceptanceTest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.http.MediaType;

import static com.lullaby.study.hexagonalkata.acceptance.AuthUtils.USER_1;
import static com.lullaby.study.hexagonalkata.acceptance.AuthUtils.회원_가입_요청;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("인증 & 회원 기능 인수 테스트")
public class AuthAcceptanceTest extends AcceptanceTest {

    @DisplayName("회원 가입에 성공 한다.")
    @Test
    void success_sign_up() {
        // Given & When
        ExtractableResponse<Response> response = 회원_가입_요청(USER_1);

        // Then
        assertEquals(201, response.statusCode());
    }

    @DisplayName("계정명 길이가 올바르지 않은 경우 가입에 실패 한다.")
    @ParameterizedTest
    @ValueSource(strings = {"abc", "tooooooooooooooo_long"})
    void fail_sign_up_1(String account) {
        // Given
        AuthUtils.SignUpRequestBody signUpRequestBody = new AuthUtils.SignUpRequestBody(account, "1234", "abcdef");
        // When
        ExtractableResponse<Response> response = 회원_가입_요청(signUpRequestBody);
        // Then
        assertEquals(400, response.statusCode());
    }

    @DisplayName("패스워드 길이가 올바르지 않은 경우 가입에 실패 한다.")
    @ParameterizedTest
    @ValueSource(strings = {"abc", "tooooooooooooooo_long"})
    void fail_sign_up_2(String password) {
        // Given
        AuthUtils.SignUpRequestBody signUpRequestBody = new AuthUtils.SignUpRequestBody("abcdef", password, "abcdef");
        // When
        ExtractableResponse<Response> response = 회원_가입_요청(signUpRequestBody);
        // Then
        assertEquals(400, response.statusCode());
    }

    @DisplayName("닉네임 길이가 올바르지 않은 경우 가입에 실패 한다.")
    @ParameterizedTest
    @ValueSource(strings = {"abc", "tooooooooooooooo_long"})
    void fail_sign_up_3(String nickname) {
        // Given
        AuthUtils.SignUpRequestBody signUpRequestBody = new AuthUtils.SignUpRequestBody("abcdef", "1234", nickname);
        // When
        ExtractableResponse<Response> response = 회원_가입_요청(signUpRequestBody);
        // Then
        assertEquals(400, response.statusCode());
    }

    @DisplayName("이미 존재하는 계정의 경우 가입에 실패 한다.")
    @Test
    void fail_sign_up_4() {
        // Given
        회원_가입_요청(USER_1);
        // When
        ExtractableResponse<Response> response = 회원_가입_요청(USER_1);
        // Then
        assertEquals(409, response.statusCode());
    }



}
