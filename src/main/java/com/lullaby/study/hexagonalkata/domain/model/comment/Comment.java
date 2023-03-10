package com.lullaby.study.hexagonalkata.domain.model.comment;

import com.lullaby.study.hexagonalkata.domain.model.article.Article;
import com.lullaby.study.hexagonalkata.domain.model.member.Member;

import java.time.LocalDateTime;

public class Comment {

    private Long id;
    private Article article;
    private String content;
    private Member writer;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Comment() {}

    public static Comment write(Article article, Member writer, String content) {
        Comment comment = new Comment();
        comment.article = article;
        comment.writer = writer;
        comment.content = content;
        comment.createdAt = LocalDateTime.now();
        comment.updatedAt = LocalDateTime.now();

        comment.validate();

        return comment;
    }

    private void validate() {
        if (this.article == null) {
            throw new CommentFieldInvalidException();
        }
        if (content == null || content.trim().length() == 0) {
            throw new CommentFieldInvalidException();
        }
        if (this.writer == null) {
            throw new CommentFieldInvalidException();
        }
    }

    public Long getId() {
        return id;
    }

    public Article getArticle() {
        return article;
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

    public void setId(Long id) {
        this.id = id;
    }
}
