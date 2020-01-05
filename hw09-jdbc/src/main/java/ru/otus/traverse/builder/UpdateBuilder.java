package ru.otus.traverse.builder;

import java.util.stream.Collectors;

public class UpdateBuilder extends AbstractStatementBuilder {


    @Override
    public String build() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("update ")
                .append(tableName)
                .append(" set ")
                .append(columns.stream()
                        .map(column->column + " = ?")
                        .collect(Collectors.joining(", ")))
                .append(" where ")
                .append(idColumnName)
                .append(" = ?");
        return stringBuilder.toString();
    }
}
