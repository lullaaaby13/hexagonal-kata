package com.lullaby.study.hexagonalkata.domain.model.article;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArticleTest {

    @DisplayName("게시글 생성에 성공 한다.")
    @Test
    void success_write_article() {
        Article article = Article.write(1L, "title", "content");
        assertNotNull(article.getTitle());
        assertNotNull(article.getContent());
        assertNotNull(article.getWriter());
        assertNotNull(article.getCreatedAt());
        assertNotNull(article.getUpdatedAt());
    }

    @DisplayName("게시글 작성자를 입력 하지 않으면 게시글 작성에 실패 한다.")
    @Test
    void fail_write_article_1() {
        Assertions.assertThrows(ArticleFieldInvalidException.class, () -> Article.write(null, "title", "content"));
    }

    @DisplayName("게시글 제목을 입력 하지 않으면 게시글 작성에 실패 한다.")
    @Test
    void fail_write_article_2() {
        Assertions.assertThrows(ArticleFieldInvalidException.class, () -> Article.write(1L, null, "content"));
    }

    @DisplayName("게시글 본문을 입력 하지 않으면 게시글 작성에 실패 한다.")
    @Test
    void fail_write_article_3() {
        Assertions.assertThrows(ArticleFieldInvalidException.class, () -> Article.write(1L, "title", null));
    }

}
