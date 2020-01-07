package ru.otus.traverse.builder;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SelectBuilder implements Strategy {

    @Override
    public String execute(ClassContext context) {
        String tableName = context.getClassName();
        List<String> columns  = context.getFields()
                .stream()
                .sorted(Comparator.comparing(Field::getName))
                .map(Field::getName)
                .collect(Collectors.toList());
        String idColumnName = context.getIdField().getName();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select ")
                .append(String.join(", ", idColumnName, String.join(", ", columns)))
                .append(" from ")
                .append(tableName)
                .append(" where ")
                .append(idColumnName)
                .append(" = ?");
        return stringBuilder.toString();
    }
}
