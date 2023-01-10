package com.lullaby.study.hexagonalkata.utils;

import java.util.Objects;

public record EntityMetaData(String tableName, String idColumn) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityMetaData that = (EntityMetaData) o;
        return Objects.equals(tableName, that.tableName) && Objects.equals(idColumn, that.idColumn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableName, idColumn);
    }
}
