package ru.otus.traverse.type;

import ru.otus.traverse.visitor.ClassVisitor;

public class TraversedClass {
    private final Class<?> clazz;

    public TraversedClass(Class<?> clazz) {
        this.clazz = clazz;
    }

    public void accept(ClassVisitor classVisitor) throws IllegalAccessException {
        classVisitor.visit(this);
    }

    public Class<?> getClazz() {
        return clazz;
    }
}
