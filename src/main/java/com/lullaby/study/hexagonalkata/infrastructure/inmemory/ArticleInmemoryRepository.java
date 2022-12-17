package com.lullaby.study.hexagonalkata.infrastructure.inmemory;

import com.lullaby.study.hexagonalkata.domain.model.article.Article;
import com.lullaby.study.hexagonalkata.domain.model.article.ArticleRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ArticleInmemoryRepository implements ArticleRepository {

    private final Map<Long, Article> map = new HashMap<>();

    @Override
    public Optional<Article> find(Long articleId) {
        if (map.containsKey(articleId)) {
            return Optional.of(map.get(articleId));
        }
        return Optional.empty();
    }

    @Override
    public List<Article> findByWriter(Long writer) {
        return map.values().stream()
                .filter(it -> it.getWriter().equals(writer))
                .toList();
    }

    @Override
    public void save(Article article) {
        if (article.getId() == null ) {
            article.setId(new Random().nextLong(1L, Integer.MAX_VALUE));
        }
        this.map.put(article.getId(), article);
    }

}
