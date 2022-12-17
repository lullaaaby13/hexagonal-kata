package com.lullaby.study.hexagonalkata.interfaces.article;

import com.lullaby.study.hexagonalkata.application.article.ArticleService;
import com.lullaby.study.hexagonalkata.application.article.WriteArticleCommand;
import com.lullaby.study.hexagonalkata.security.AuthenticatedUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("article")
@RestController
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public ResponseEntity<?> getArticles(@AuthenticationPrincipal AuthenticatedUser user) {

        System.out.println("username: " + user.getUsername());

        return ResponseEntity.ok(null);
    }

    @PostMapping
    public ResponseEntity<?> writeArticle(@AuthenticationPrincipal AuthenticatedUser user, @Validated @RequestBody WriteArticleRequest request) {
        WriteArticleCommand command = new WriteArticleCommand(request.getTitle(), request.getContent());
        Long articleId = articleService.write(user.getUserId(), command);
        return ResponseEntity.status(HttpStatus.CREATED).body(articleId);
    }

}
