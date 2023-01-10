package com.lullaby.study.hexagonalkata.utils;

import jakarta.persistence.EntityManager;

import java.util.Map;

import static java.lang.String.format;

public class H2CleanUp implements CleanUpStrategy{
    @Override
    public void clean(EntityManager entityManager, Map<String, EntityMetaData> entityMetaData) {

        entityManager.flush();
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();
        

        for (EntityMetaData metaData : entityMetaData.values()) {
            entityManager.createNativeQuery(format("TRUNCATE TABLE %s", metaData.tableName())).executeUpdate();
            entityManager.createNativeQuery(format("ALTER TABLE %s ALTER COLUMN %s RESTART WITH 1", metaData.tableName(), metaData.idColumn())).executeUpdate();
        }
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
    }
}
