package ru.otus.traverse.builder;

import java.util.Set;
import java.util.stream.Collectors;

public class InsertBuilder implements Strategy {

    @Override
    public String execute(ClassContext context) {
        String tableName = context.getClassName();
        Set<String> columns  = context.getFields()
                .stream()
                .map(field->field.getName())
                .collect(Collectors.toSet());

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
