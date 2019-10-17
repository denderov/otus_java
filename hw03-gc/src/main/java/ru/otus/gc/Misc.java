package ru.otus.gc;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Misc {

    private static Logger logger = Logger.getLogger(Misc.class.getName());

    public static void main(String[] args) throws InterruptedException {
//        logExamples();
        for (int i = 0; i < 1000000; i++) {
            Integer[] integers = new Integer[1000];
            Arrays.fill(integers,i);
            Thread.sleep(100);
            System.out.println(i);
        }
    }

    private static void logExamples() {
        logger.info("main method started");
        System.out.println("Ordinal message");
        System.err.println("Error message");
        logger.log(Level.WARNING,"warning");
    }

}
