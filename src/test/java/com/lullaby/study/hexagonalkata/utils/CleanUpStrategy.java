package com.lullaby.study.hexagonalkata.utils;

import jakarta.persistence.EntityManager;

import java.util.Map;

public interface CleanUpStrategy {

    void clean(EntityManager entityManager, Map<String, EntityMetaData> entityMetaData);
}
