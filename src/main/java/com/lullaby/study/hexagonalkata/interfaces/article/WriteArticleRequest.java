package com.lullaby.study.hexagonalkata.interfaces.article;

import jakarta.validation.constraints.NotBlank;

public class WriteArticleRequest {
    @NotBlank
    String title;

    @NotBlank
    String content;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
