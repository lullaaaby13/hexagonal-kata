package com.lullaby.study.hexagonalkata.domain.model.comment;

import java.util.Optional;

public interface CommentRepository {

    Optional<Comment> find(Long commentId);

    Comment save(Comment comment);

}
