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

import static com.lullaby.study.hexagonalkata.acceptance.AuthUtils.*;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("게시글")
public class ArticleAcceptanceTest extends AcceptanceTest {

    @DisplayName("게시글 작성을 성공 한다.")
    @Test
    void success_write_article() {
        // Given
        String accessToken = 회원_가입_후_인증_토큰_가져오기(사용자_1);
        // When
        ExtractableResponse<Response> response = 게시글_작성(accessToken, 게시글_1);
        // Then
        assertEquals(HttpStatus.CREATED.value(), response.statusCode());
        Long articleId = response.body().as(Long.class);
        assertNotNull(articleId);
    }

    @DisplayName("제목을 입력 하지 않으면 게시글 작성에 실패 한다.")
    @Test
    void fail_write_article_1() {
        // Given
        String accessToken = 회원_가입_후_인증_토큰_가져오기(사용자_1);
        // When
        ExtractableResponse<Response> response = 게시글_작성(accessToken, new CreateArticleRequestBody("title", null));
        // Then
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.statusCode());
    }

    @DisplayName("본문을 입력 하지 않으면 게시글 작성에 실패 한다.")
    @Test
    void fail_write_article_2() {
        // Given
        String accessToken = 회원_가입_후_인증_토큰_가져오기(사용자_1);
        // When
        ExtractableResponse<Response> response = 게시글_작성(accessToken, new CreateArticleRequestBody(null, "content"));

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.statusCode());
    }

    public static record CreateArticleRequestBody(String title, String content) {}
    public static final CreateArticleRequestBody 게시글_1 = new CreateArticleRequestBody("title", "content");
    public static ExtractableResponse<Response> 게시글_작성(String accessToken, CreateArticleRequestBody requestBody) {
        return RestAssured
                .given()
                .log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", format("Bearer %s", accessToken))
                .body(requestBody)
                .when()
                .post("/article")
                .then()
                .log().all()
                .extract();
    }
}
