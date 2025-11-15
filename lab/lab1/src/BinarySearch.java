package src;

import java.util.Scanner;

public class BinarySearch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] array = ArrayUtil.inputArray(scanner);

        System.out.print("Введите число для поиска: ");
        int target = scanner.nextInt();

        selectionSort(array);

        System.out.println("Отсортированный массив:");
        ArrayUtil.printArray(array);

        int iterativeResult = binarySearchIterative(array, target);
        if (iterativeResult == -1) {
            System.out.println("Итеративный поиск: элемент не найден.");
        } else {
            System.out.println("Итеративный поиск: элемент находится на позиции " + (iterativeResult));
        }

        int recursiveResult = binarySearchRecursive(array, target, 0, array.length - 1);
        if (recursiveResult == -1) {
            System.out.println("Рекурсивный поиск: элемент не найден.");
        } else {
            System.out.println("Рекурсивный поиск: элемент находится на позиции " + (recursiveResult));
        }

        scanner.close();
    }

    private static void selectionSort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    private static int binarySearchIterative(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (arr[middle] == target) {
                return middle;
            } else if (arr[middle] < target) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return -1;
    }

    private static int binarySearchRecursive(int[] arr, int target, int left, int right) {
        if (left > right) {
            return -1;
        }
        int middle = left + (right - left) / 2;

        if (arr[middle] == target) {
            return middle;
        } else if (arr[middle] < target) {
            return binarySearchRecursive(arr, target, middle + 1, right);
        } else {
            return binarySearchRecursive(arr, target, left, middle - 1);
        }

    }
}