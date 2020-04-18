package com.company;

//Поиск второго по минимальности элемента в массиве


public class Task_4 {

    public static void main(String args[]) {

        int[] array = {69, 53, 46, 198, 69, 68, 72, 45, 99, 62, 52, 75, 25};

        int min = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for (int i = 0; i < array.length; i++) {
            if (array[i] < min) {
                min2 = min;
                min = array[i];
            } else if (array[i] < min2 && array[i] != min) {
                min2 = array[i];
            }
        }
        if (min2 != Integer.MAX_VALUE) {
            System.out.println("Второй по минимальности эллемент в массиве " + min2);
        } else {
            System.out.println("Второго по минимальности эллемента нет");
        }
        System.out.println("Минимальный эллемент в массиве " + min);
    }
}
