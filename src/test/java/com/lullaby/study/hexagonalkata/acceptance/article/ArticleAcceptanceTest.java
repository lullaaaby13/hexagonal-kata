package com.lullaby.study.hexagonalkata.acceptance.article;

import com.lullaby.study.hexagonalkata.acceptance.AcceptanceTest;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static com.lullaby.study.hexagonalkata.acceptance.article.ArticleAcceptanceHelper.게시글_작성;
import static com.lullaby.study.hexagonalkata.acceptance.auth.AuthAcceptanceHelper.*;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("인수 테스트 > 게시글")
public class ArticleAcceptanceTest extends AcceptanceTest {

    @DisplayName("게시글 작성을 성공 한다.")
    @Test
    void success_write_article() {
        // Given
        String accessToken = 회원_가입_후_인증_토큰_가져오기(사용자_1);
        // When
        ExtractableResponse<Response> response = 게시글_작성(accessToken, ArticleAcceptanceHelper.게시글_작성_요청_1);
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
        ExtractableResponse<Response> response = 게시글_작성(accessToken, new ArticleAcceptanceHelper.CreateArticleRequestBody("title", null));
        // Then
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.statusCode());
    }

    @DisplayName("본문을 입력 하지 않으면 게시글 작성에 실패 한다.")
    @Test
    void fail_write_article_2() {
        // Given
        String accessToken = 회원_가입_후_인증_토큰_가져오기(사용자_1);
        // When
        ExtractableResponse<Response> response = 게시글_작성(accessToken, new ArticleAcceptanceHelper.CreateArticleRequestBody(null, "content"));

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.statusCode());
    }

    @DisplayName("게시글을 조회 한다.")
    @Test
    void success_get_articles() {
        // Given
        String accessToken = 회원_가입_후_인증_토큰_가져오기(사용자_1);
        게시글_작성(accessToken, new ArticleAcceptanceHelper.CreateArticleRequestBody("title_1", "content_1"));
        게시글_작성(accessToken, new ArticleAcceptanceHelper.CreateArticleRequestBody("title_2", "content_2"));
        게시글_작성(accessToken, new ArticleAcceptanceHelper.CreateArticleRequestBody("title_3", "content_3"));

        // When
        ExtractableResponse<Response> response = RestAssured
                .given()
                .log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", format("Bearer %s", accessToken))
                .when()
                .get("/articles")
                .then()
                .log().all()
                .extract();

        assertEquals(HttpStatus.OK.value(), response.statusCode());

        JsonPath jsonPath = response.body().jsonPath();
        assertEquals("title_3", jsonPath.get("list[0].title"));
        assertEquals("title_2", jsonPath.get("list[1].title"));
        assertEquals("title_1", jsonPath.get("list[2].title"));
    }
}
