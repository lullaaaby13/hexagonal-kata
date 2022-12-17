package com.lullaby.study.hexagonalkata.interfaces.comment;

import com.lullaby.study.hexagonalkata.application.comment.CommentService;
import com.lullaby.study.hexagonalkata.application.comment.WriteCommentCommand;
import com.lullaby.study.hexagonalkata.security.AuthenticatedUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("comments")
@RestController
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<?> write(@AuthenticationPrincipal AuthenticatedUser user, @Validated @RequestBody WriteCommentRequest request) {
        WriteCommentCommand command = new WriteCommentCommand(request.getArticleId(), request.getContent());
        Long commentId = commentService.write(user.getUserId(), command);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentId);
    }

}
