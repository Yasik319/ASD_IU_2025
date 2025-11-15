package src;

import java.util.Scanner;

public class ArrayMultiply {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите первый массив:");
        int[] firstNumber = ArrayUtil.inputArray(scanner);

        System.out.println("Введите второй массив:");
        int[] secondNumber = ArrayUtil.inputArray(scanner);

        int[] resultArray = multiplyArrays(firstNumber, secondNumber);

        ArrayUtil.printArrayFormatted(firstNumber);
        System.out.print(" * ");
        ArrayUtil.printArrayFormatted(secondNumber);
        System.out.print(" = ");
        ArrayUtil.printArrayFormatted(resultArray);

        scanner.close();
    }

    private static int[] multiplyArrays(int[] num1, int[] num2) {
        int len1 = num1.length;
        int len2 = num2.length;
        int[] result = new int[len1 + len2];

        for (int i = len1 - 1; i >= 0; i--) {
            for (int j = len2 - 1; j >= 0; j--) {
                int product = num1[i] * num2[j];
                int sum = product + result[i + j + 1];

                result[i + j + 1] = sum % 10;
                result[i + j] += sum / 10;
            }
        }

        int startIndex = 0;
        while (startIndex < result.length - 1 && result[startIndex] == 0) {
            startIndex++;
        }

        int[] finalResult = new int[result.length - startIndex];
        System.arraycopy(result, startIndex, finalResult, 0, finalResult.length);

        return finalResult;
    }

}