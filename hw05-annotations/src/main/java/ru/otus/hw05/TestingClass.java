package ru.otus.hw05;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestingClass {


    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {

        String className = "ru.otus.hw05.ClassForTesting";

        Class classForTesting = Class.forName(className);

        List<Method> testingMethods = new ArrayList<>();
        List<Method> beforeMethods = new ArrayList<>();
        List<Method> afterMethods = new ArrayList<>();

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

        for (Method method : testingMethods) {

            var instanceOfClassForTesting = classForTesting.getDeclaredConstructor().newInstance();

            for (Method beforeMethod : beforeMethods) {
                beforeMethod.invoke(instanceOfClassForTesting);
            }

            method.invoke(instanceOfClassForTesting);

            for (Method afterMethod : afterMethods) {
                afterMethod.invoke(instanceOfClassForTesting);
            }

        }

    }


}
