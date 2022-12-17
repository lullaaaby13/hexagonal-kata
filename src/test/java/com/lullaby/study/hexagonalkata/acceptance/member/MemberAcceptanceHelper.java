package com.lullaby.study.hexagonalkata.acceptance.member;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

import static java.lang.String.format;

public class MemberAcceptanceHelper {

    public static ExtractableResponse<Response> 회원_프로필_조회(String accessToken) {
        return RestAssured
                .given()
                .log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", format("Bearer %s", accessToken))
                .when()
                .get("/members/profile")
                .then()
                .log().all()
                .extract();
    }
}
