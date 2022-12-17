package com.lullaby.study.hexagonalkata.domain.model.comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    Optional<Comment> find(Long commentId);

    List<Comment> findByArticleId(Long articleId);
    Comment save(Comment comment);

}
