package com.lullaby.study.hexagonalkata.application.comment;

import com.lullaby.study.hexagonalkata.application.article.ArticleNotFoundException;
import com.lullaby.study.hexagonalkata.domain.model.article.Article;
import com.lullaby.study.hexagonalkata.domain.model.article.ArticleRepository;
import com.lullaby.study.hexagonalkata.domain.model.comment.Comment;
import com.lullaby.study.hexagonalkata.domain.model.comment.CommentRepository;
import com.lullaby.study.hexagonalkata.domain.model.member.Member;
import com.lullaby.study.hexagonalkata.domain.model.member.MemberNotFoundException;
import com.lullaby.study.hexagonalkata.domain.model.member.MemberRepository;
import com.lullaby.study.hexagonalkata.domain.model.pointhistory.PointAction;
import com.lullaby.study.hexagonalkata.domain.model.pointhistory.PointHistory;
import com.lullaby.study.hexagonalkata.domain.model.pointhistory.PointHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    private final MemberRepository memberRepository;
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    private final PointHistoryRepository pointHistoryRepository;

    public CommentServiceImpl(MemberRepository memberRepository, ArticleRepository articleRepository, CommentRepository commentRepository, PointHistoryRepository pointHistoryRepository) {
        this.memberRepository = memberRepository;
        this.articleRepository = articleRepository;
        this.commentRepository = commentRepository;
        this.pointHistoryRepository = pointHistoryRepository;
    }

    @Override
    public List<CommentModel> getComments(Long articleId) {
        return this.commentRepository.findByArticleId(articleId)
                .stream()
                .map(CommentModel::new)
                .toList();
    }

    @Override
    public Long write(Long writer, WriteCommentCommand command) {

        Member member = memberRepository.find(writer)
                .orElseThrow(MemberNotFoundException::new);

        Article article = articleRepository.find(command.articleId())
                .orElseThrow(ArticleNotFoundException::new);

        Comment comment = Comment.write(article, member, command.content());

        PointHistory pointHistory = PointHistory.create(member, PointAction.WRITE_COMMENT);
        pointHistoryRepository.save(pointHistory);

        return commentRepository.save(comment).getId();
    }

}
