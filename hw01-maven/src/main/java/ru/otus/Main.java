package ru.otus;

import com.google.common.collect.Iterables;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int count;
        List<String> strings = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type count of list's elements:");
        count = scanner.nextInt();
        for (int i = 0; i<count; i++) {
            strings.add(scanner.nextLine());
        }
        Iterables.removeIf(strings, Objects::isNull);//Predicates.isNull()
        System.out.println(strings.size());
    }

}
