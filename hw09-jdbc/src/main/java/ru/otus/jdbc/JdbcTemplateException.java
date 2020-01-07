package ru.otus.jdbc;

public class JdbcTemplateException extends Throwable {
    public JdbcTemplateException(Exception ex) {
        super(ex);
    }
}
