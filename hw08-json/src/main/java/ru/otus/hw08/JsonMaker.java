package ru.otus.hw08;

import ru.otus.hw08.visitor.JsonElementVisitor;

public class JsonMaker {
    public static String toJson(Object o) throws IllegalAccessException {
        ElementTraverser traverser = new ElementTraverser(o ,new JsonElementVisitor());
        return traverser.traverse().getVisitor().getJson();
    }
}
