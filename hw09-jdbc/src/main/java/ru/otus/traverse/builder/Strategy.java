package ru.otus.traverse.builder;

public interface Strategy {

    <T> T execute(ClassContext context);
}
