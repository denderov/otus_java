package ru.otus.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.jdbc.sessionmanager.SessionManagerJdbc;
import ru.otus.traverse.builder.ClassContext;
import ru.otus.traverse.type.TraversedClass;
import ru.otus.traverse.visitor.ContextClassVisitor;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class JdbcTemplate<T> {

    private static Logger logger = LoggerFactory.getLogger(JdbcTemplate.class);

    private final Class<?> clazz;
    private final SessionManagerJdbc sessionManager;
    private final DbExecutor<T> dbExecutor;
    private final ClassContext classContext;

    public JdbcTemplate(SessionManagerJdbc sessionManager, DbExecutor<T> dbExecutor, Class<T> clazz) throws IllegalAccessException {
        this.sessionManager = sessionManager;
        this.dbExecutor = dbExecutor;
        this.clazz = clazz;
        this.classContext = fillClassContext(clazz);
    }

    private ClassContext fillClassContext(Class<T> clazz) throws IllegalAccessException {
        TraversedClass traversedClass = new TraversedClass(clazz);
        ContextClassVisitor visitor = new ContextClassVisitor();
        traversedClass.accept(visitor);

        return visitor.getClassContext();
    }

    public void create(T objectData) throws JdbcTemplateException {
        try {
            String sql = classContext.getInsertStatement();
            List<Object> fieldValues = classContext.getFields()
                    .stream()
                    .sorted(Comparator.comparing(Field::getName))
                    .map(field -> {
                        try {
                            return field.get(objectData);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        return null;
                    })
                    .collect(Collectors.toList());
            sessionManager.beginSession();
            try {
                Object id = dbExecutor.insertRecord(getConnection(), sql, fieldValues);
                Field idField = classContext.getIdField();
                idField.setAccessible(true);
                idField.set(objectData,convertValue(id,idField.getType()));

                sessionManager.commitSession();
                sessionManager.close();

                logger.info("created object: {}", id);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                sessionManager.rollbackSession();
                sessionManager.close();
                throw new JdbcTemplateException(e);
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new JdbcTemplateException(e);
        }
    }

    private Object convertValue(Object id, Class<?> clazz) {

        return clazz == BigDecimal.class ? BigDecimal.valueOf((Long) id) : id;
    }

    public void update(T objectData) throws JdbcTemplateException {
        try {
            String sql = classContext.getUpdateStatement();
            List<Object> fieldValues = classContext.getFields()
                    .stream()
                    .sorted(Comparator.comparing(Field::getName))
                    .map(field -> {
                        try {
                            return field.get(objectData);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        return null;
                    })
                    .collect(Collectors.toList());
            Object id = classContext.getIdField().get(objectData);
            fieldValues.add(id);
            sessionManager.beginSession();
            try {

                dbExecutor.updateRecord(getConnection(), sql, fieldValues);
                sessionManager.commitSession();
                sessionManager.close();

                logger.info("updated object: {}", id);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                sessionManager.rollbackSession();
                sessionManager.close();
                throw new JdbcTemplateException(e);
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new JdbcTemplateException(e);
        }
    }

    public T load(long id) {
        Optional<T> optionalInstance = Optional.empty();
        try {

            T instance = (T) clazz.getDeclaredConstructor().newInstance();
            String sql = classContext.getSelectStatement();
            System.out.println(sql);
            optionalInstance = dbExecutor.selectRecord(getConnection(), sql, id, resultSet -> {
                try {
                    if (resultSet.next()) {
                        for (Field field : classContext.getFields()) {
                            field.setAccessible(true);
                            field.set(instance, resultSet.getObject(field.getName()));
                        }
                        Field idField = classContext.getIdField();
                        idField.setAccessible(true);
                        idField.set(instance, convertValue(resultSet.getObject(idField.getName()),classContext.getIdField().getType()));
                    }
                } catch (SQLException | IllegalAccessException e) {
                    logger.error(e.getMessage(), e);
                }
                return instance;

            });

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return optionalInstance.orElse(null);
    }

    private Connection getConnection() {
        return sessionManager.getCurrentSession().getConnection();
    }
}
