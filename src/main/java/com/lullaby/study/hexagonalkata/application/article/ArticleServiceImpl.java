package com.lullaby.study.hexagonalkata.application.article;

import com.lullaby.study.hexagonalkata.domain.model.article.Article;
import com.lullaby.study.hexagonalkata.domain.model.article.ArticleRepository;
import com.lullaby.study.hexagonalkata.domain.model.member.Member;
import com.lullaby.study.hexagonalkata.domain.model.member.MemberNotFoundException;
import com.lullaby.study.hexagonalkata.domain.model.member.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService{

    private final MemberRepository memberRepository;
    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(MemberRepository memberRepository, ArticleRepository articleRepository) {
        this.memberRepository = memberRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public List<ArticleModel> getArticles() {
        return new ArrayList<>();
    }

    @Override
    public Long write(Long writer, WriteArticleCommand command) {
        Member member = memberRepository.find(writer)
                .orElseThrow(MemberNotFoundException::new);
        Article article = Article.write(member, command.title(), command.content());
        articleRepository.save(article);
        return article.getId();
    }

}
