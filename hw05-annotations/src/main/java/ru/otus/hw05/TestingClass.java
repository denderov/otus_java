package ru.otus.hw05;

import ru.otus.hw05.annotation.After;
import ru.otus.hw05.annotation.Before;
import ru.otus.hw05.annotation.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TestingClass {

    private String className;
    private int count = 0;
    private int passed = 0;
    private List<Method> testingMethods = new ArrayList<>();
    private List<Method> beforeMethods = new ArrayList<>();
    private List<Method> afterMethods = new ArrayList<>();

    TestingClass(String className) {
        this.className = className;
    }

    void run() {

        Class classForTesting;
        try {
            classForTesting = Class.forName(className);

            fillMethodCollections(classForTesting);

            for (Method method : testingMethods) {

                Object instanceOfClassForTesting = getInstance(classForTesting);

                invokeMethod(method, instanceOfClassForTesting);

            }
        } catch (ClassNotFoundException e) {
            System.out.printf("Class %s not found: %s %n", className, e.getCause());
        }

        System.out.printf("%nResult: Total count: %d, Passed: %d, Failed: %d%n", count, passed, count - passed);

    }

    private Object getInstance(Class classForTesting) {
        Object instanceOfClassForTesting = null;
        try {
            instanceOfClassForTesting = classForTesting.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            System.out.printf("Class %s cannot be instantiated: %s %n", classForTesting.getName(), e.getCause());
        }
        return instanceOfClassForTesting;
    }

    private void invokeMethod(Method method, Object instanceOfClassForTesting) {

        try {

            try {
                invokeMethodsFromCollection(instanceOfClassForTesting, beforeMethods);
                method.invoke(instanceOfClassForTesting);
            } finally {
                invokeMethodsFromCollection(instanceOfClassForTesting, afterMethods);
            }

            System.out.printf("%s - Test '%s' - passed %n", ++count, method.getName());
            passed++;

        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.printf("%s - Test '%s' - failed: %s %n", ++count, method.getName(), e.getCause());
        }

    }

    private void invokeMethodsFromCollection(Object instanceOfClassForTesting, Collection<Method> methodCollection) throws IllegalAccessException, InvocationTargetException {
        for (Method method : methodCollection) {
            method.invoke(instanceOfClassForTesting);
        }
    }

    private void fillMethodCollections(Class classForTesting) {
        for (Method method : classForTesting.getDeclaredMethods()) {
            for (Annotation annotation : method.getDeclaredAnnotations()) {

                if (annotation instanceof Test) {
                    testingMethods.add(method);

                } else if (annotation instanceof Before) {
                    beforeMethods.add(method);

                } else if (annotation instanceof After) {
                    afterMethods.add(method);

                }

            }
        }
    }


}
