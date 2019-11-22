package ru.otus.hw04;

public class Main {

    public static void main(String[] args) {
        new TestLogging().calculation(6);
        new TestLogging().calculation(6,"second");
        new TestLogging().calculation();
        new TestLogging().calculation("another case");
        new TestLogging().calculation(6);
        new TestLogging().calculation(6,"second");
        new TestLogging().calculation();
        new TestLogging().calculation("another case");
    }
}
