package com.company;

//Поиск минимального элемента в массиве

public class Task_1 {

    public static void main(String[] args) {
        int[] array = {86, 47, 20, 48, 16, 97, 35, 78, 75, 46, 86, 19};

        int minValue = array[0];
        int minIndex = 0;

        for (int i = 1; i < array.length; i++) {
            if (array[i] < minValue) {
                minValue = array[i];
                minIndex = i;
            }
        }
        System.out.println(minValue);
        System.out.println(minIndex);
    }
}
