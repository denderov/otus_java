package ru.otus.traverse.builder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClassContextImpl implements ClassContext {

    private String className;
    private List<Field> fields = new ArrayList<>();
    private Field idField;
    private StatementBulder statementBulder;
    private String selectStatement;
    private String insertStatement;
    private String updateStatement;

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
    public String getUpdateStatement() {
        return updateStatement;
    }

    @Override
    public String getInsertStatement() {
        return insertStatement;
    }

    @Override
    public String getSelectStatement() {
        return selectStatement;
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
    public ClassContext setStatementBuilder(StatementBulder statementBulder) {
        this.statementBulder = statementBulder;
        return this;
    }

    @Override
    public String makeStatement() {
        return statementBulder.execute(this);
    }

    @Override
    public void build() {
        this.selectStatement = this.setStatementBuilder(new SelectBuilder()).makeStatement();
        this.insertStatement = this.setStatementBuilder(new InsertBuilder()).makeStatement();
        this.updateStatement = this.setStatementBuilder(new UpdateBuilder()).makeStatement();
    }
}
