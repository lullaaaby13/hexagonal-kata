package com.lullaby.study.hexagonalkata.domain.model.article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {

    Optional<Article> find(Long articleId);

    List<Article> findByWriter(Long writer);

    void save(Article article);
}
