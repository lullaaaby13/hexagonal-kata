package com.lullaby.study.hexagonalkata.acceptance.auth;

import com.lullaby.study.hexagonalkata.acceptance.AcceptanceTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.http.HttpStatus;

import static com.lullaby.study.hexagonalkata.acceptance.auth.AuthAcceptanceHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("인증 & 회원 기능 인수 테스트")
public class AuthAcceptanceTest extends AcceptanceTest {

    @DisplayName("회원 가입에 성공 한다.")
    @Test
    void success_sign_up() {
        // Given & When
        ExtractableResponse<Response> response = 회원_가입_요청(사용자_1);

        // Then
        assertEquals(HttpStatus.CREATED.value(), response.statusCode());
    }

    @DisplayName("계정명 길이가 올바르지 않은 경우 가입에 실패 한다.")
    @ParameterizedTest
    @ValueSource(strings = {"abc", "tooooooooooooooo_long"})
    void fail_sign_up_1(String account) {
        // Given
        SignUpRequestBody signUpRequestBody = new SignUpRequestBody(account, "1234", "abcdef");
        // When
        ExtractableResponse<Response> response = 회원_가입_요청(signUpRequestBody);
        // Then
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.statusCode());
    }

    @DisplayName("패스워드 길이가 올바르지 않은 경우 가입에 실패 한다.")
    @ParameterizedTest
    @ValueSource(strings = {"abc", "tooooooooooooooo_long"})
    void fail_sign_up_2(String password) {
        // Given
        SignUpRequestBody signUpRequestBody = new SignUpRequestBody("abcdef", password, "abcdef");
        // When
        ExtractableResponse<Response> response = 회원_가입_요청(signUpRequestBody);
        // Then
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.statusCode());
    }

    @DisplayName("닉네임 길이가 올바르지 않은 경우 가입에 실패 한다.")
    @ParameterizedTest
    @ValueSource(strings = {"abc", "tooooooooooooooo_long"})
    void fail_sign_up_3(String nickname) {
        // Given
        SignUpRequestBody signUpRequestBody = new SignUpRequestBody("abcdef", "1234", nickname);
        // When
        ExtractableResponse<Response> response = 회원_가입_요청(signUpRequestBody);
        // Then
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.statusCode());
    }

    @DisplayName("이미 존재하는 계정의 경우 가입에 실패 한다.")
    @Test
    void fail_sign_up_4() {
        // Given
        회원_가입_요청(사용자_1);
        // When
        ExtractableResponse<Response> response = 회원_가입_요청(사용자_1);
        // Then
        assertEquals(HttpStatus.CONFLICT.value(), response.statusCode());
    }


    @DisplayName("올바른 계정명과 비밀번호로 로그인 시 성공 한다.")
    @Test
    void success_sign_in() {
        // Given
        회원_가입_요청(사용자_1);
        // When
        ExtractableResponse<Response> response = 로그인_요청(사용자_1);
        // Then
        assertEquals(HttpStatus.OK.value(), response.statusCode());
        JsonPath jsonPath = response.body().jsonPath();
        assertNotNull(jsonPath.get("accessToken"));
        assertNotNull(jsonPath.get("refreshToken"));
    }


    @DisplayName("계정명을 입력 하지 않으면 로그인에 실패 한다.")
    @Test
    void fail_sign_in_3() {
        // Given
        회원_가입_요청(사용자_1);
        // When
        ExtractableResponse<Response> response = 로그인_요청(new SignInRequestBody(null, "1234"));
        // Then
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.statusCode());
    }

    @DisplayName("비밀번호를 입력 하지 않으면 로그인에 실패 한다.")
    @Test
    void fail_sign_in_4() {
        // Given
        회원_가입_요청(사용자_1);
        // When
        ExtractableResponse<Response> response = 로그인_요청(new SignInRequestBody(사용자_1.account(), null));
        // Then
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.statusCode());
    }

    @DisplayName("비밀번호가 틀리면 로그인에 실패 한다.")
    @Test
    void fail_sign_in_1() {
        // Given
        회원_가입_요청(사용자_1);
        // When
        ExtractableResponse<Response> response = 로그인_요청(new SignInRequestBody(사용자_1.account(), "wrong_password"));
        // Then
        assertEquals(HttpStatus.FORBIDDEN.value(), response.statusCode());
    }

    @DisplayName("존재 하지 않는 계정으로 로그인 시도 시 로그인에 실패 한다.")
    @Test
    void fail_sign_in_2() {
        // Given
        회원_가입_요청(사용자_1);
        // When
        ExtractableResponse<Response> response = 로그인_요청(new SignInRequestBody("not_exist", 사용자_1.password()));
        // Then
        assertEquals(HttpStatus.FORBIDDEN.value(), response.statusCode());
    }

}
