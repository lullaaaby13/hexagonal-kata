package com.lullaby.study.hexagonalkata.domain.model.article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {

    Optional<Article> find(Long articleId);

    List<Article> findByWriter(Long writer);

    List<Article> findAll(int page, int size);

    void save(Article article);
}
