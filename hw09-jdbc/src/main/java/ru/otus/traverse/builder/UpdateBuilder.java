package ru.otus.traverse.builder;

import java.util.Set;
import java.util.stream.Collectors;

public class UpdateBuilder implements Strategy {


    @Override
    public String execute(ClassContext context) {
        String tableName = context.getClassName();
        Set<String> columns  = context.getFields()
                .stream()
                .map(field->field.getName())
                .collect(Collectors.toSet());
        String idColumnName = context.getIdField().getName();

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
