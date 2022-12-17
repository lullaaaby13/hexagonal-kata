package com.lullaby.study.hexagonalkata.infrastructure.inmemory;

import com.lullaby.study.hexagonalkata.domain.model.comment.Comment;
import com.lullaby.study.hexagonalkata.domain.model.comment.CommentRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Repository
public class CommentInmemoryRepository implements CommentRepository {

    private final Map<Long, Comment> map = new HashMap<>();

    @Override
    public Optional<Comment> find(Long commentId) {
        if (map.containsKey(commentId)) {
            return Optional.of(map.get(commentId));
        }
        return Optional.empty();
    }

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() == null ) {
            comment.setId(new Random().nextLong(1L, Integer.MAX_VALUE));
        }
        this.map.put(comment.getId(), comment);
        return comment;
    }

}
