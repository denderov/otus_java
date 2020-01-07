package ru.otus.traverse.builder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClassContextImpl implements ClassContext {

    private String className;
    private List<Field> fields = new ArrayList<>();

    private Field idField;

    private Strategy strategy;

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public List<Field> getFields() {
        return fields;
    }

    @Override
    public Field getIdField() {
        return idField;
    }

    @Override
    public ClassContext addTableName(String tableName) {
        if (Objects.isNull(this.className)) {
            this.className = tableName;
        } else {
            throw new RuntimeException("Unexpected error");
        }
        return this;
    }

    @Override
    public ClassContext addField(Field field) {
        fields.add(field);
        return this;
    }

    @Override
    public ClassContext addIdField(Field idField) {
        if (Objects.isNull(this.idField)) {
            this.idField = idField;
        } else {
            throw new RuntimeException("Only one id column is supported");
        }
        return this;
    }

    @Override
    public ClassContext setStrategy(Strategy strategy) {
        this.strategy = strategy;
        return this;
    }

    @Override
    public <T> T build() {
        return strategy.execute(this);
    }
}
