package com.lullaby.study.hexagonalkata.acceptance.comment;

import com.lullaby.study.hexagonalkata.acceptance.AcceptanceTest;
import com.lullaby.study.hexagonalkata.acceptance.comment.CommentAcceptanceHelper.WriteCommentRequestBody;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.lullaby.study.hexagonalkata.acceptance.article.ArticleAcceptanceHelper.게시글_작성;
import static com.lullaby.study.hexagonalkata.acceptance.article.ArticleAcceptanceHelper.게시글_작성_요청_1;
import static com.lullaby.study.hexagonalkata.acceptance.auth.AuthAcceptanceHelper.사용자_1;
import static com.lullaby.study.hexagonalkata.acceptance.auth.AuthAcceptanceHelper.회원_가입_후_인증_토큰_가져오기;
import static com.lullaby.study.hexagonalkata.acceptance.comment.CommentAcceptanceHelper.댓글_작성_요청_1;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("인수 테스트 > 댓글")
public class CommentAcceptanceTest extends AcceptanceTest {

    @DisplayName("댓글 작성을 성공 한다.")
    @Test
    void success_write_comment() {
        // Given
        String accessToken = 회원_가입_후_인증_토큰_가져오기(사용자_1);
        Long articleId = 게시글_작성(accessToken, 게시글_작성_요청_1)
                .body().as(Long.class);
        // When
        ExtractableResponse<Response> response = CommentAcceptanceHelper.댓글_작성(accessToken, 댓글_작성_요청_1.apply(articleId));
        // Then
        assertEquals(HttpStatus.CREATED.value(), response.statusCode());
    }

    @DisplayName("댓글 본문을 입력 하지 않으면 댓글 작성에 실패 한다.")
    @Test
    void fail_write_comment_1() {
        // Given
        String accessToken = 회원_가입_후_인증_토큰_가져오기(사용자_1);
        Long articleId = 게시글_작성(accessToken, 게시글_작성_요청_1)
                .body().as(Long.class);
        // When
        ExtractableResponse<Response> response = CommentAcceptanceHelper.댓글_작성(accessToken, new WriteCommentRequestBody(articleId, null));
        // Then
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.statusCode());
    }





}
