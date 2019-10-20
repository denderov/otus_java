package ru.otus.gc;

import java.util.Arrays;

public class Benchmark {

    private int size;

    public Benchmark(int size) {
        this.size = size;
    }

    void run() throws InterruptedException {
//        command line argument for remote
//        -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005
        for (int i = 0; i < 1000000; i++) {
            Integer[] integers = new Integer[size];
            Arrays.fill(integers, i);
            Thread.sleep(100);
//            System.out.println(i);
        }
    }
}