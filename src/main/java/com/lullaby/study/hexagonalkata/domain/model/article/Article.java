package com.lullaby.study.hexagonalkata.domain.model.article;

import com.lullaby.study.hexagonalkata.domain.model.member.Member;

import java.time.LocalDateTime;

public class Article {

    private Long id;
    private String title;
    private String content;
    private Member writer;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Article() {}

    public static Article write(Member writer, String title, String content) {
        Article article = new Article();
        article.title = title;
        article.content = content;
        article.writer = writer;
        article.createdAt = LocalDateTime.now();
        article.updatedAt = LocalDateTime.now();

        article.validate();

        return article;
    }

    private void validate() {
        if (title == null || title.trim().length() == 0) {
            throw new ArticleFieldInvalidException();
        }

        if (content == null || content.trim().length() == 0) {
            throw new ArticleFieldInvalidException();
        }

        if (writer == null) {
            throw new ArticleFieldInvalidException();
        }
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Member getWriter() {
        return writer;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    // TODO 테스트 끝나면 지워야 됨
    public void setId(Long id) {
        this.id = id;
    }
}
