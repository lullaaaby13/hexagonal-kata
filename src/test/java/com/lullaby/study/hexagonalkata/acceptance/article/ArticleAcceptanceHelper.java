package com.lullaby.study.hexagonalkata.acceptance.article;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

import static java.lang.String.format;

public class ArticleAcceptanceHelper {

    public static final CreateArticleRequestBody 게시글_작성_요청_1 = new CreateArticleRequestBody("title", "content");

    public static ExtractableResponse<Response> 게시글_작성(String accessToken, CreateArticleRequestBody requestBody) {
        return RestAssured
                .given()
                .log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", format("Bearer %s", accessToken))
                .body(requestBody)
                .when()
                .post("/articles")
                .then()
                .log().all()
                .extract();
    }

    public static record CreateArticleRequestBody(String title, String content) {}
}
