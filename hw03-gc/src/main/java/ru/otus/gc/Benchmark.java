package ru.otus.gc;

import java.util.Arrays;

public class Benchmark implements BenchmarkMBean{

    private int size;
    private int i;

    public Benchmark(int size) {
        this.size = size;
    }


    void run() throws InterruptedException {
//        command line argument for remote
//        -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005
        for (i = 0; i < 100; i++) {
            String[] strings = new String[size];
            for (int i = 0, len = strings.length; i < len; i++)
                strings[i] = new String();
            Thread.sleep(10);
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getI() {
        return i;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
        System.out.println("New size is "+size);
    }
}