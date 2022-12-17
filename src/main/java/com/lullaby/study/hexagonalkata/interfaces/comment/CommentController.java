package com.lullaby.study.hexagonalkata.interfaces.comment;

import com.lullaby.study.hexagonalkata.application.comment.CommentModel;
import com.lullaby.study.hexagonalkata.application.comment.CommentService;
import com.lullaby.study.hexagonalkata.application.comment.WriteCommentCommand;
import com.lullaby.study.hexagonalkata.interfaces.PagingResponse;
import com.lullaby.study.hexagonalkata.security.AuthenticatedUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("comments")
@RestController
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<?> getComments(@RequestParam("articleId") Long articleId) {
        List<CommentModel> comments = this.commentService.getComments(articleId);
        return ResponseEntity.ok().body(comments);
    }
    @PostMapping
    public ResponseEntity<?> write(@AuthenticationPrincipal AuthenticatedUser user, @Validated @RequestBody WriteCommentRequest request) {
        WriteCommentCommand command = new WriteCommentCommand(request.getArticleId(), request.getContent());
        Long commentId = commentService.write(user.getUserId(), command);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentId);
    }

}
