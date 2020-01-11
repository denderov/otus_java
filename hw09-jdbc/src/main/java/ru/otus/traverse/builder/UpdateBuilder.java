package ru.otus.traverse.builder;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class UpdateBuilder implements StatementBulder {


    @Override
    public String execute(ClassContext context) {
        String tableName = context.getClassName();
        List<String> columns  = context.getFields()
                .stream()
                .sorted(Comparator.comparing(Field::getName))
                .map(Field::getName)
                .collect(Collectors.toList());
        String idColumnName = context.getIdField().getName();

        return String.format("update %s set %s where %s = ?"
                ,tableName
                ,columns.stream()
                        .map(column->column + " = ?")
                        .collect(Collectors.joining(", "))
                ,idColumnName);
    }
}
