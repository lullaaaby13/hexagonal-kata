package com.lullaby.study.hexagonalkata.acceptance.member;

import com.lullaby.study.hexagonalkata.acceptance.AcceptanceTest;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static com.lullaby.study.hexagonalkata.acceptance.article.ArticleAcceptanceHelper.게시글_작성;
import static com.lullaby.study.hexagonalkata.acceptance.article.ArticleAcceptanceHelper.게시글_작성_요청_1;
import static com.lullaby.study.hexagonalkata.acceptance.auth.AuthAcceptanceHelper.*;
import static com.lullaby.study.hexagonalkata.acceptance.comment.CommentAcceptanceHelper.댓글_작성;
import static com.lullaby.study.hexagonalkata.acceptance.comment.CommentAcceptanceHelper.댓글_작성_요청_1;
import static com.lullaby.study.hexagonalkata.acceptance.member.MemberAcceptanceHelper.회원_프로필_조회;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("인수 테스트 > 회원")
public class MemberAcceptanceTest extends AcceptanceTest {

    @DisplayName("회원이 프로필 조회에 성공 한다.")
    @Test
    void success_get_profile_1() {
        // Given
        String accessToken = 회원_가입_후_인증_토큰_가져오기(사용자_1);
        // When
        ExtractableResponse<Response> response = 회원_프로필_조회(accessToken);
        // Then
        assertEquals(HttpStatus.OK.value(), response.statusCode());

        JsonPath jsonPath = response.body().jsonPath();
        assertEquals(사용자_1.account(), jsonPath.get("account"));
        assertEquals(사용자_1.nickname(), jsonPath.get("nickname"));
        assertNotNull(jsonPath.get("point"));
    }

    @DisplayName("게시글을 작성하면 포인트가 3 증가 한다.")
    @Test
    void success_get_profile_2() {
        // Given
        String accessToken = 회원_가입_후_인증_토큰_가져오기(사용자_1);
        게시글_작성(accessToken, 게시글_작성_요청_1);

        // When
        ExtractableResponse<Response> response = 회원_프로필_조회(accessToken);
        // Then
        assertEquals(HttpStatus.OK.value(), response.statusCode());

        JsonPath jsonPath = response.body().jsonPath();
        assertEquals(사용자_1.account(), jsonPath.get("account"));
        assertEquals(사용자_1.nickname(), jsonPath.get("nickname"));
        assertEquals(3, (int) jsonPath.get("point"));
    }

    @DisplayName("댓글을 작성하면 포인트가 1 증가 한다.")
    @Test
    void success_get_profile_3() {
        // Given
        String accessToken1 = 회원_가입_후_인증_토큰_가져오기(사용자_1);
        Long articleId = 게시글_작성(accessToken1, 게시글_작성_요청_1).body().as(Long.class);

        String accessToken2 = 회원_가입_후_인증_토큰_가져오기(사용자_2);
        댓글_작성(accessToken2, 댓글_작성_요청_1.apply(articleId));

        // When
        ExtractableResponse<Response> response = 회원_프로필_조회(accessToken2);
        // Then
        assertEquals(HttpStatus.OK.value(), response.statusCode());

        JsonPath jsonPath = response.body().jsonPath();
        assertEquals(사용자_2.account(), jsonPath.get("account"));
        assertEquals(사용자_2.nickname(), jsonPath.get("nickname"));
        assertEquals(1, (int) jsonPath.get("point"));
    }

}
