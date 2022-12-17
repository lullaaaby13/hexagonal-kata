package com.lullaby.study.hexagonalkata.application.article;

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

}
