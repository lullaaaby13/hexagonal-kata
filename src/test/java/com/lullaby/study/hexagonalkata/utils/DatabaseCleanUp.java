package com.lullaby.study.hexagonalkata.utils;

import com.google.common.base.CaseFormat;
import jakarta.persistence.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.lang.String.format;

@Service
public class DatabaseCleanUp implements InitializingBean {

    @PersistenceContext
    protected EntityManager entityManager;

    private List<EntityMetaData> entityMetaData = new ArrayList<>();

    @Override
    public void afterPropertiesSet() {
        this.entityMetaData = entityManager.getMetamodel().getEntities().stream()
                .filter(it -> it.getJavaType().getAnnotation(Entity.class) != null)
                .map(entityClass -> {
                    String classNameToTableName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, entityClass.getName());
                    Table tableAnnotation = entityClass.getJavaType().getAnnotation(Table.class);
                    String tableName = Objects.nonNull(tableAnnotation) ? tableAnnotation.name() : classNameToTableName;

                    String idColumn = Arrays.stream(entityClass.getJavaType().getDeclaredFields())
                            .filter(field -> field.getAnnotation(Id.class) != null)
                            .map(Field::getName)
                            .findFirst()
                            .orElse(null);
                    return new EntityMetaData(tableName, idColumn);
                })
                .toList();
    }

    @Transactional
    public void execute() {



        entityManager.flush();
        // MYSQL, MARIADB
        entityManager.createNativeQuery("SET foreign_key_checks = 0").executeUpdate();
        // 그외
        //entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();

        for (EntityMetaData metaData : entityMetaData) {
            entityManager.createNativeQuery(format("TRUNCATE TABLE %s", metaData.tableName())).executeUpdate();
            if (metaData.idColumn() != null) {
                entityManager.createNativeQuery(format("ALTER TABLE %s AUTO_INCREMENT=1", metaData.tableName())).executeUpdate();
            }
        }

        // MYSQL, MARIADB
        entityManager.createNativeQuery("SET foreign_key_checks = 1").executeUpdate();
        // 그외
        //entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
    }

}
