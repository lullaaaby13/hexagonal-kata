package com.lullaby.study.hexagonalkata.interfaces.article;

import com.lullaby.study.hexagonalkata.application.article.ArticleModel;
import com.lullaby.study.hexagonalkata.application.article.ArticleService;
import com.lullaby.study.hexagonalkata.application.article.WriteArticleCommand;
import com.lullaby.study.hexagonalkata.interfaces.PagingResponse;
import com.lullaby.study.hexagonalkata.security.AuthenticatedUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("articles")
@RestController
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public ResponseEntity<?> getArticles(
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "10", required = false) Integer size
    ) {
        List<ArticleModel> articles = articleService.getArticles(page, size);
        PagingResponse<ArticleModel> response = new PagingResponse<>(articles, page, size);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> writeArticle(@AuthenticationPrincipal AuthenticatedUser user, @Validated @RequestBody WriteArticleRequest request) {
        WriteArticleCommand command = new WriteArticleCommand(request.getTitle(), request.getContent());
        Long articleId = articleService.write(user.getUserId(), command);
        return ResponseEntity.status(HttpStatus.CREATED).body(articleId);
    }

}
