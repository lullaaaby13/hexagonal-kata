package com.lullaby.study.hexagonalkata.interfaces;

import com.lullaby.study.hexagonalkata.security.AuthenticatedUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("article")
@RestController
public class ArticleController {

    @GetMapping
    public ResponseEntity<?> getArticles(@AuthenticationPrincipal AuthenticatedUser user) {

        System.out.println("username: " + user.getUsername());

        return ResponseEntity.ok(null);
    }

}
