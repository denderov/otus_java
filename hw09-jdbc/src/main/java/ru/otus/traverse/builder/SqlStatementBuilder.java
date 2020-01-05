package ru.otus.traverse.builder;

public interface SqlStatementBuilder {

    SqlStatementBuilder addTableName(String tableName);

    SqlStatementBuilder addColumnName(String name);

    SqlStatementBuilder addIdColumnName(String name);

    String build();
}
