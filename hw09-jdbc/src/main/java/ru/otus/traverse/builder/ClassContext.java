package ru.otus.traverse.builder;

import java.lang.reflect.Field;
import java.util.List;

public interface ClassContext {

    String getClassName();

    List<Field> getFields();

    Field getIdField();

    ClassContext addTableName(String tableName);

    ClassContext addField(Field field);

    ClassContext addIdField(Field idField);

    ClassContext setStrategy(Strategy strategy);

    <T> T build();
}
