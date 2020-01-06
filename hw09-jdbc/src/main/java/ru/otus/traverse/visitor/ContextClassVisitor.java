package ru.otus.traverse.visitor;

import ru.otus.traverse.builder.ClassContext;
import ru.otus.traverse.builder.ClassContextImpl;
import ru.otus.traverse.type.TraversedClass;
import ru.otus.traverse.type.TraversedField;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ContextClassVisitor implements ClassVisitor {

    private ClassContext classContext;

    @Override
    public void visit(TraversedClass traversedClass) throws IllegalAccessException {
        Class<?> clazz = traversedClass.getClazz();
        classContext = new ClassContextImpl();
        classContext.addTableName(clazz.getSimpleName().toLowerCase());
        Field[] fields = clazz.getDeclaredFields();

        for (Field field :
                fields) {
            if (isNotStatic(field)) {
                new TraversedField(field).accept(new ContextFieldVisitor(this));
            }
        }

    }

    private boolean isNotStatic(Field field) {
        return !Modifier.isStatic(field.getModifiers());
    }

    public ClassContext getClassContext() {
        return classContext;
    }

}
