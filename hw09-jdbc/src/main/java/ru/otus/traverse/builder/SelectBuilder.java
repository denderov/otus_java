package ru.otus.traverse.builder;

import java.util.Set;
import java.util.stream.Collectors;

public class SelectBuilder implements Strategy {

    @Override
    public String execute(ClassContext context) {
        String tableName = context.getClassName();
        Set<String> columns  = context.getFields()
                .stream()
                .map(field->field.getName())
                .collect(Collectors.toSet());
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
