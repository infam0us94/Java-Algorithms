package com.company;

// Поиск в массиве: Линейный, двоичный


public class Task_3 {

    public static void main(String args[]) {

        int[] array = {8, 6, 78, 15, 65, 23, 4, 985, 62, 47, 65, 83, 26, 4};

        Sort(array);


        System.out.println(SearchLinear(array, 23));

        System.out.println(searchBinary(array, 65));

    }

    static int SearchLinear(int[] array, int elementToFind) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == elementToFind)
                return i;
        }
        return -1;
    }


    public static int searchBinary(int[] array, int elementToFind) {

        int startIndex = 0;
        int endIndex = array.length - 1;
        int middleIndex;
        while (startIndex <= endIndex) {
            middleIndex = startIndex + (endIndex - startIndex) / 2;

            if (array[middleIndex] == elementToFind) {
                return middleIndex;
            }
            if (array[middleIndex] > elementToFind) {
                endIndex = middleIndex - 1;
            } else {
                startIndex = middleIndex + 1;
            }
        }
        return -1;
    }


    static void Sort(int[] array) {
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;

            for (int i = 1; i < array.length; i++) {
                if (array[i] < array[i - 1]) {
                    int step = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = step;
                    isSorted = false;
                }
            }
        }
    }
}
