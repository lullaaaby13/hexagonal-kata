package com.lullaby.study.hexagonalkata.application.comment;

import com.lullaby.study.hexagonalkata.application.article.ArticleService;
import com.lullaby.study.hexagonalkata.infrastructure.inmemory.ArticleInmemoryRepository;
import com.lullaby.study.hexagonalkata.infrastructure.inmemory.CommentInmemoryRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommentServiceImplTest {

    CommentService commentService = new CommentServiceImpl(new ArticleInmemoryRepository(), new CommentInmemoryRepository());


    @Test
    void success_write_comment() {
        // Given
    }
}
