package ru.otus.traverse.builder;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class InsertBuilder implements StatementBulder {

    @Override
    public String execute(ClassContext context) {
        String tableName = context.getClassName();
        List<String> columns  = context.getFields()
                .stream()
                .sorted(Comparator.comparing(Field::getName))
                .map(Field::getName)
                .collect(Collectors.toList());

        return String.format("insert into %s (%s) values (%s)"
                ,tableName
                ,String.join(",", columns)
                ,columns.stream()
                        .map(column->"?")
                        .collect(Collectors.joining(",")));
    }
}
