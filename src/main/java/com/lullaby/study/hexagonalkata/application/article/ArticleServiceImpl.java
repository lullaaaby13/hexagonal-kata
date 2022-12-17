package com.lullaby.study.hexagonalkata.application.article;

import com.lullaby.study.hexagonalkata.domain.model.article.Article;
import com.lullaby.study.hexagonalkata.domain.model.article.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService{

    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public List<ArticleModel> getArticles() {
        return new ArrayList<>();
    }

    @Override
    public void write(Long writer, WriteArticleCommand command) {
        Article article = Article.write(writer, command.title(), command.content());
        articleRepository.save(article);
    }

}
