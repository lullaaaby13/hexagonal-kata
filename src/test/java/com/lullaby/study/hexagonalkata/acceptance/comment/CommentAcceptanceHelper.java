package com.lullaby.study.hexagonalkata.acceptance.comment;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

import java.util.function.Function;

import static java.lang.String.format;

public class CommentAcceptanceHelper {
    public static final Function<Long, WriteCommentRequestBody> 댓글_작성_요청_1 = articleId -> new WriteCommentRequestBody(articleId, "comment");

    public static ExtractableResponse<Response> 댓글_작성(String accessToken, WriteCommentRequestBody requestBody) {
        return RestAssured
                .given()
                .log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", format("Bearer %s", accessToken))
                .body(requestBody)
                .when()
                .post("/comments")
                .then()
                .log().all()
                .extract();
    }

    public record WriteCommentRequestBody(Long articleId, String content) {}
}
