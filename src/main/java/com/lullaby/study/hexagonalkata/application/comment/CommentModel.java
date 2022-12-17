package com.lullaby.study.hexagonalkata.application.comment;

import com.lullaby.study.hexagonalkata.domain.model.comment.Comment;

import java.time.LocalDateTime;

public class CommentModel {
    private Long id;
    private Long articleId;
    private String content;
    private String writer;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CommentModel(Long id, Long articleId, String content, String writer, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.articleId = articleId;
        this.content = content;
        this.writer = writer;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public CommentModel(Comment comment) {
        this.id = comment.getId();
        this.articleId = comment.getArticle().getId();
        this.content = comment.getContent();
        this.writer = comment.getWriter().getNickname();
        this.createdAt = comment.getCreatedAt();
        this.updatedAt = comment.getUpdatedAt();
    }

    public Long getId() {
        return id;
    }

    public Long getArticleId() {
        return articleId;
    }

    public String getContent() {
        return content;
    }

    public String getWriter() {
        return writer;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
