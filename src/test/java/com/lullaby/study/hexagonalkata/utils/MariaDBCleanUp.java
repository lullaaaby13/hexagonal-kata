package com.lullaby.study.hexagonalkata.utils;

import jakarta.persistence.EntityManager;

import java.util.Map;

import static java.lang.String.format;

public class MariaDBCleanUp implements CleanUpStrategy {

    @Override
    public void clean(EntityManager entityManager, Map<String, EntityMetaData> entityMetaData) {
        entityManager.flush();
        entityManager.createNativeQuery("SET foreign_key_checks = 0").executeUpdate();
        for (EntityMetaData metaData : entityMetaData.values()) {
            entityManager.createNativeQuery(format("TRUNCATE TABLE %s", metaData.tableName())).executeUpdate();
            if (metaData.idColumn() != null) {
                entityManager.createNativeQuery(format("ALTER TABLE %s AUTO_INCREMENT=1", metaData.tableName())).executeUpdate();
            }
        }
        entityManager.createNativeQuery("SET foreign_key_checks = 1").executeUpdate();
    }

}
