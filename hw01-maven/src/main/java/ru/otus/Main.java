package ru.otus;

//import com.google.common.collect.Iterables;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;

import java.util.ArrayList;
import java.util.List;
//import java.util.Objects;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int count;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter sentence:");
        String sentence = scanner.nextLine();
        List<String> wordsFromSentence = getListOfWordsFromSentence(sentence);
        System.out.println(wordsFromSentence);
    }

    static List<String> getListOfWordsFromSentence(String sentence) {
        return Splitter.on(" ").omitEmptyStrings().splitToList(sentence);
    }

}
