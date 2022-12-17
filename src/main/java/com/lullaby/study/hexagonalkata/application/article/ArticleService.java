package com.lullaby.study.hexagonalkata.application.article;

import org.springframework.stereotype.Service;

import java.util.List;

public interface ArticleService {

    List<ArticleModel> getArticles();

    void write(Long writer, WriteArticleCommand command);
}
