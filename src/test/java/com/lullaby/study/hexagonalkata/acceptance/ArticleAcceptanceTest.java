package com.lullaby.study.hexagonalkata.acceptance;

import com.lullaby.study.hexagonalkata.AcceptanceTest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static com.lullaby.study.hexagonalkata.acceptance.AuthUtils.*;
import static java.lang.String.format;

public class ArticleAcceptanceTest extends AcceptanceTest {

    @Test
    void success_write_article() {
        // Given
        String accessToken = 회원_가입_후_인증_토큰_가져오기(사용자_1);
        // When
        ExtractableResponse<Response> response = RestAssured
                .given()
                .log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", format("Bearer %s", accessToken))
                .body(new CreateArticleRequestBody("title", "content"))
                .when()
                .post("/article")
                .then()
                .log().all()
                .extract();

        Assertions.assertEquals(HttpStatus.CREATED.value(), response.statusCode());
    }

    public static record CreateArticleRequestBody(String title, String content) {}
}
