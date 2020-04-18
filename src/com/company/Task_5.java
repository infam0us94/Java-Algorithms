package com.company;

// Алгоритмы поиска в строке наивный, Кнутта-Морриса-Пратта

import java.util.ArrayList;
import java.util.Arrays;

public class Task_5 {

    public static void main(String args[]) {

        String text = "мама мыла раму ";
        String sample = "мыла";

        System.out.println(Arrays.toString(searchNaive(text,sample).toArray()));

        System.out.println(Arrays.toString(KMPSearch(text, sample).toArray()));
    }

    static ArrayList<Integer> searchNaive(String text, String sample) {

        ArrayList<Integer> foundPosition = new ArrayList<>();
        for (int i = 0; i < text.length(); i++) {
            int j = 0;
            while (j < sample.length() && i + j < text.length() && sample.charAt(j) == text.charAt(i + j)) {
                j++;
            }
            if (j == sample.length()) {
                foundPosition.add(i);
            }
        }
        return foundPosition;
    }


    static int[] prefixFunction(String sample) {
        int[] values = new int[sample.length()];
        for (int i = 1; i < sample.length(); i++) {
            int j = 0;
            while (i + j < sample.length() && sample.charAt(j) == sample.charAt(i + j)) {

                values[i + j] = Math.max(values[i + j], j + 1);
                j++;
            }
        }
        return values;
    }

    public static ArrayList<Integer> KMPSearch(String text, String sample) {

        ArrayList<Integer> found = new ArrayList<>();

        int[] prefixFunc = prefixFunction(sample);

        int i = 0;
        int j = 0;

        while (i < text.length()) {
            if (sample.charAt(j) == text.charAt(i)) {
                j++;
                i++;
            }
            if (j == sample.length()) {
                found.add(i - j);
                j = prefixFunc[j - 1];
            } else if (i < text.length() && sample.charAt(j) != text.charAt(i)) {
                if (j != 0) {
                    j = prefixFunc[j - 1];
                } else {
                    i = i + 1;
                }
            }
        }
        return found;
    }
}
