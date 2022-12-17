package com.lullaby.study.hexagonalkata.application.comment;

import java.util.List;

public interface CommentService {

    List<CommentModel> getComments(Long articleId);
    Long write(Long writer, WriteCommentCommand command);

}
