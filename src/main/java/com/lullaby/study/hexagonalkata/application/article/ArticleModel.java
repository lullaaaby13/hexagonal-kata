package com.lullaby.study.hexagonalkata.application.article;

import com.lullaby.study.hexagonalkata.domain.model.article.Article;

import java.time.LocalDateTime;

public class ArticleModel {
    private Long id;
    private String title;
    private String content;
    private Long writer;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ArticleModel(Long id, String title, String content, Long writer, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ArticleModel(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.writer = article.getWriter().getId();
        this.createdAt = article.getCreatedAt();
        this.updatedAt = article.getUpdatedAt();
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

    public Long getWriter() {
        return writer;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
