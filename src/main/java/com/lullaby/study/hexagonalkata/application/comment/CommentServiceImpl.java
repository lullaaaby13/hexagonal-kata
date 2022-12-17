package com.lullaby.study.hexagonalkata.application.comment;

import com.lullaby.study.hexagonalkata.application.article.ArticleNotFoundException;
import com.lullaby.study.hexagonalkata.domain.model.article.Article;
import com.lullaby.study.hexagonalkata.domain.model.article.ArticleRepository;
import com.lullaby.study.hexagonalkata.domain.model.comment.Comment;
import com.lullaby.study.hexagonalkata.domain.model.comment.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService{

    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    public CommentServiceImpl(ArticleRepository articleRepository, CommentRepository commentRepository) {
        this.articleRepository = articleRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public Long write(Long writer, WriteCommentCommand command) {

        Article article = articleRepository.find(command.articleId())
                .orElseThrow(ArticleNotFoundException::new);

        Comment comment = Comment.write(article.getId(), writer, command.content());

        return commentRepository.save(comment).getId();
    }

}
