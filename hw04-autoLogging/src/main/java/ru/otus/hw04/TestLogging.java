package ru.otus.hw04;

public class TestLogging {

    @Log
    public void calculation(int i) {
        System.out.println("one int parameter");
    }

    @Log
    public void calculation(int i, String y) {
        System.out.println("two parameters");
    }

    @Log
    public void calculation() {
        System.out.println("none parameters");
    }

    public void calculation(String i) {
        System.out.println("not for logging");
    }
}
