package ru.otus.traverse.visitor;

import ru.otus.api.model.Id;
import ru.otus.traverse.builder.ClassContext;
import ru.otus.traverse.type.TraversedField;

import java.lang.reflect.Field;
import java.util.Objects;

public class ContextFieldVisitor implements FieldVisitor {

    private final ContextClassVisitor contextClassVisitor;

    ContextFieldVisitor(ContextClassVisitor contextClassVisitor) {
        this.contextClassVisitor = contextClassVisitor;
    }

    @Override
    public void visit(TraversedField traversedField) throws IllegalAccessException {
        ClassContext classContext = contextClassVisitor.getClassContext();
        Field field = traversedField.getField();
        String fieldName = traversedField.getFieldName();
        Object parentObject = traversedField.getParentObject();
        field.setAccessible(true);

        if (Objects.isNull(field.getAnnotation(Id.class))) {
            classContext.addField(field);
        } else {
            classContext.addIdField(field);
        }

    }
}
