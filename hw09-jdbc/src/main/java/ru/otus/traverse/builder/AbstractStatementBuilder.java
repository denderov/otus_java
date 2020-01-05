package ru.otus.traverse.builder;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public abstract class AbstractStatementBuilder implements SqlStatementBuilder {

    String tableName;

    Set<String> columns = new LinkedHashSet<>();

    String idColumnName;

    @Override
    public SqlStatementBuilder addTableName(String tableName) {
        if (Objects.isNull(this.tableName)) {
            this.tableName = tableName;
        } else {
            throw new RuntimeException("Unexpected error");
        }
        return this;
    }

    @Override
    public SqlStatementBuilder addColumnName(String columnName) {
        columns.add(columnName);
        return this;
    }

    @Override
    public SqlStatementBuilder addIdColumnName(String idColumnName) {
        if (Objects.isNull(this.idColumnName)) {
            this.idColumnName = idColumnName;
        } else {
            throw new RuntimeException("Only one id column is supported");
        }
        return this;
    }

    @Override
    public abstract String build();
}
