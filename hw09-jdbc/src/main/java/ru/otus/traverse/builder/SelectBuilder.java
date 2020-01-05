package ru.otus.traverse.builder;

public class SelectBuilder extends AbstractStatementBuilder {

    @Override
    public String build() {
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
