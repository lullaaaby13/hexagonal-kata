package com.lullaby.study.hexagonalkata.interfaces.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class WriteCommentRequest {
    @NotNull
    private Long articleId;
    @NotBlank
    private String content;

    public Long getArticleId() {
        return articleId;
    }

    public String getContent() {
        return content;
    }
}
