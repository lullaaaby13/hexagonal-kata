package com.lullaby.study.hexagonalkata.application.comment;

public interface CommentService {

    Long write(Long writer, WriteCommentCommand command);

}
