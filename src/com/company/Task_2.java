package com.company;

//Сортировка пузырьком

import java.util.Arrays;

public class Task_2 {

    public static void main(String args[]) {

        int[] array = {56, 62, 48, 85, 23, 75, 153, 43, 15, 78, 16, 17};


        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;


            for (int i = 1; i < array.length; i++) {
                if (array[i] < array[i - 1]) {
                    int temp = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = temp;
                    isSorted = false;
                }
            }
        }
        for (int a : array) {
            System.out.println(a);
        }
    }
}
