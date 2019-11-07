package ru.otus.gc;

import java.util.ArrayList;
import java.util.List;

public class Benchmark implements BenchmarkMBean {

    private int size;
    private int i;

    public Benchmark(int size) {
        this.size = size;
    }


    void run() throws InterruptedException {
//        command line argument for remote
//        -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005

        List<String> stringList = new ArrayList<>();

        String stringPrefix = "String object number ";

        while (true) {
            for (i = 0; i < size; i++) {
                stringList.add(stringPrefix + i);
            }
            clearHalfTail(stringList);
            Thread.sleep(1000);
            System.out.println("Size of list is " + stringList.size());
        }
    }

    private void clearHalfTail(List<String> stringList) {
        stringList.subList(size / 2, size).clear();
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
        System.out.println("New size is " + size);
    }
}