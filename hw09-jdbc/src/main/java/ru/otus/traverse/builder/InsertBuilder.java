package ru.otus.traverse.builder;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class InsertBuilder implements Strategy {

    @Override
    public String execute(ClassContext context) {
        String tableName = context.getClassName();
        List<String> columns  = context.getFields()
                .stream()
                .sorted(Comparator.comparing(Field::getName))
                .map(Field::getName)
                .collect(Collectors.toList());

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
