package ru.otus.gc;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Misc {

    private static Logger logger = Logger.getLogger(Misc.class.getName());

    public static void main(String[] args) {
        logExamples();
    }

    private static void logExamples() {
        logger.info("main method started");
        System.out.println("Ordinal message");
        System.err.println("Error message");
        logger.log(Level.WARNING,"warning");
    }

}
