package com.lullaby.study.hexagonalkata.acceptance.auth;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

public class AuthAcceptanceHelper {

    public static TestMember 사용자_1 = new TestMember("test_user_1", "1234", "apple");
    public static TestMember 사용자_2 = new TestMember("test_user_2", "1234", "banana");

    public static ExtractableResponse<Response> 회원_가입_요청(SignUpRequestBody requestBody) {
        return RestAssured
                .given()
                .log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(requestBody)
                .when()
                .post("/auth/sign-up")
                .then()
                .log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 회원_가입_요청(TestMember testMember) {
        return RestAssured
                .given()
                .log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(new SignUpRequestBody(testMember.account, testMember.password, testMember.nickname))
                .when()
                .post("/auth/sign-up")
                .then()
                .log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 로그인_요청(SignInRequestBody requestBody) {
        return RestAssured
                .given()
                .log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(requestBody)
                .when()
                .post("/auth/sign-in")
                .then()
                .log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 로그인_요청(TestMember testMember) {
        return RestAssured
                .given()
                .log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(new SignInRequestBody(testMember.account, testMember.password))
                .when()
                .post("/auth/sign-in")
                .then()
                .log().all()
                .extract();
    }

    public static String 회원_가입_후_인증_토큰_가져오기(TestMember testMember) {
        회원_가입_요청(testMember);
        return 로그인_요청(testMember)
                .body()
                .jsonPath()
                .get("accessToken");
    }

    public static record SignUpRequestBody(String account, String password, String nickname) {}
    public static record SignInRequestBody(String account, String password) {}

    public static record TestMember(String account, String password, String nickname) {}

}
