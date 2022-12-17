package com.lullaby.study.hexagonalkata.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

public class AuthUtils {

    public static SignUpRequestBody USER_1 = new SignUpRequestBody("test_user_1", "1234", "apple");
    public static SignUpRequestBody USER_2 = new SignUpRequestBody("test_user_2", "1234", "banana");

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

    public static record SignUpRequestBody(String account, String password, String nickname) {}

}
