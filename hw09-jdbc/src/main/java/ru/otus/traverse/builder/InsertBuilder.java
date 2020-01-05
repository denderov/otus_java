package ru.otus.traverse.builder;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class InsertBuilder implements SqlStatementBuilder {

    private String tableName;

    private Set<String> columns = new LinkedHashSet<>();

    private String idColumnName;

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
    public String build() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("insert into ")
                .append(tableName)
                .append("(")
                .append(String.join(",", columns))
                .append(") values (")
                .append(columns.stream()
                        .map(column->"?")
                        .collect(Collectors.joining(",")))
                .append(")");
        return stringBuilder.toString();
    }
}
