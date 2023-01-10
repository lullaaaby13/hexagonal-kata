package com.lullaby.study.hexagonalkata.utils;

import com.google.common.base.CaseFormat;
import jakarta.persistence.*;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.*;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class DatabaseCleanUp implements InitializingBean {
    @PersistenceContext
    protected EntityManager entityManager;
    private final Set<EntityMetaData> entityMetaData = new HashSet<>();
    private final Map<String, EntityMetaData> entityMap = new HashMap<>();
    private final CleanUpStrategy cleanUpStrategy = new H2CleanUp();

    @Override
    public void afterPropertiesSet() {
        entityMap.clear();

        List<EntityType<?>> entityTypes = entityManager.getMetamodel().getEntities().stream()
                .filter(it -> it.getJavaType().getAnnotation(Entity.class) != null)
                .toList();

        for (EntityType<?> entityType : entityTypes) {
            String tableName = findTableName(entityType);
            String idColumn = findIdColumn(entityType);
            this.entityMetaData.add(new EntityMetaData(tableName, idColumn));
            this.entityMap.put(tableName, new EntityMetaData(tableName, idColumn));
        }

    }

    private String findTableName(EntityType<?> entityType) {

        Table tableAnnotation = entityType.getJavaType().getAnnotation(Table.class);

        if (nonNull(tableAnnotation)) {
            return tableAnnotation.name();
        }

        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, entityType.getName());
    }
    private String findIdColumn(EntityType<?> entityType) {
        Field idField = Arrays.stream(entityType.getJavaType().getDeclaredFields())
                .filter(field -> nonNull(field.getAnnotation(Id.class)))
                .findFirst()
                .orElse(null);

        if (isNull(idField)) {
            throw new RuntimeException("ID 컬럼을 찾을 수 없습니다.");
        }

        Column column = idField.getAnnotation(Column.class);

        if (nonNull(column)) {
            return column.name();
        }

        return idField.getName();
    }



    @Transactional
    public void cleanUp() {
        this.cleanUpStrategy.clean(entityManager, entityMap);
    }

}
