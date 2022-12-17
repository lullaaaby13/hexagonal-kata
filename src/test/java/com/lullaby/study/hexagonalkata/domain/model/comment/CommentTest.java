package com.lullaby.study.hexagonalkata.domain.model.comment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.lullaby.study.hexagonalkata.domain.model.member.MemberMock.멤버_1;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("도메인 > 댓글")
class CommentTest {

    @DisplayName("정상적인 파라미터로 댓글 생성에 성공 한다.")
    @Test
    void success_write_comment() {
        Comment comment = Comment.write(1L, 멤버_1, "comment");
        assertNotNull(comment.getArticleId());
        assertNotNull(comment.getContent());
        assertNotNull(comment.getWriter());
        assertNotNull(comment.getCreatedAt());
        assertNotNull(comment.getUpdatedAt());
    }

    @DisplayName("게시글 아이디를 입력 하지 않으면 댓글 작성에 실패 한다.")
    @Test
    void fail_write_comment_1() {
        assertThrows(CommentFieldInvalidException.class, () -> Comment.write(null, 멤버_1, "comment"));
    }

    @DisplayName("작성자 아이디를 입력 하지 않으면 댓글 작성에 실패 한다.")
    @Test
    void fail_write_comment_2() {
        assertThrows(CommentFieldInvalidException.class, () -> Comment.write(1L, null, "comment"));
    }

    @DisplayName("댓글 본문을 입력 하지 않으면 댓글 작성에 실패 한다.")
    @Test
    void fail_write_comment_3() {
        assertThrows(CommentFieldInvalidException.class, () -> Comment.write(1L, 멤버_1, null));
        assertThrows(CommentFieldInvalidException.class, () -> Comment.write(1L, 멤버_1, ""));
        assertThrows(CommentFieldInvalidException.class, () -> Comment.write(1L, 멤버_1, "  "));
    }

}
