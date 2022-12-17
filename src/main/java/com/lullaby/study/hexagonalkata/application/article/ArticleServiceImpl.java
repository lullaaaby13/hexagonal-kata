package com.lullaby.study.hexagonalkata.application.article;

import com.lullaby.study.hexagonalkata.domain.model.article.Article;
import com.lullaby.study.hexagonalkata.domain.model.article.ArticleRepository;
import com.lullaby.study.hexagonalkata.domain.model.member.Member;
import com.lullaby.study.hexagonalkata.domain.model.member.MemberNotFoundException;
import com.lullaby.study.hexagonalkata.domain.model.member.MemberRepository;
import com.lullaby.study.hexagonalkata.domain.model.pointhistory.PointAction;
import com.lullaby.study.hexagonalkata.domain.model.pointhistory.PointHistory;
import com.lullaby.study.hexagonalkata.domain.model.pointhistory.PointHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService{

    private final MemberRepository memberRepository;
    private final ArticleRepository articleRepository;
    private final PointHistoryRepository pointHistoryRepository;

    public ArticleServiceImpl(MemberRepository memberRepository, ArticleRepository articleRepository, PointHistoryRepository pointHistoryRepository) {
        this.memberRepository = memberRepository;
        this.articleRepository = articleRepository;
        this.pointHistoryRepository = pointHistoryRepository;
    }

    @Override
    public List<ArticleModel> getArticles(int page, int size) {
        return this.articleRepository.findAll(page, size).stream()
                .map(ArticleModel::new)
                .toList();
    }

    @Override
    public Long write(Long writer, WriteArticleCommand command) {

        Member member = memberRepository.find(writer)
                .orElseThrow(MemberNotFoundException::new);

        Article article = Article.write(member, command.title(), command.content());
        articleRepository.save(article);

        PointHistory pointHistory = PointHistory.create(member, PointAction.WRITE_ARTICLE);
        pointHistoryRepository.save(pointHistory);

        return article.getId();
    }

}
