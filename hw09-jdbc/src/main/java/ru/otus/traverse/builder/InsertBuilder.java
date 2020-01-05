package ru.otus.traverse.builder;

import java.util.stream.Collectors;

public class InsertBuilder extends AbstractStatementBuilder {

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
