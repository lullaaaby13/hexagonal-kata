package com.lullaby.study.hexagonalkata.acceptance;

import com.lullaby.study.hexagonalkata.AcceptanceTest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.function.Function;

import static com.lullaby.study.hexagonalkata.acceptance.ArticleAcceptanceTest.게시글_1;
import static com.lullaby.study.hexagonalkata.acceptance.ArticleAcceptanceTest.게시글_작성;
import static com.lullaby.study.hexagonalkata.acceptance.AuthUtils.사용자_1;
import static com.lullaby.study.hexagonalkata.acceptance.AuthUtils.회원_가입_후_인증_토큰_가져오기;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("인수 테스트 > 댓글")
public class CommentAcceptanceTest extends AcceptanceTest {

    @Test
    void success_write_comment() {
        // Given
        String accessToken = 회원_가입_후_인증_토큰_가져오기(사용자_1);
        Long articleId = 게시글_작성(accessToken, 게시글_1)
                .body().as(Long.class);
        // When
        ExtractableResponse<Response> response = 댓글_작성(accessToken, 댓글_1.apply(articleId));
        // Then
        assertEquals(HttpStatus.CREATED.value(), response.statusCode());
    }

    public record WriteCommentRequestBody(Long articleId, String content) {}

    public static final Function<Long, WriteCommentRequestBody> 댓글_1 = articleId -> new WriteCommentRequestBody(articleId, "comment");

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
}
