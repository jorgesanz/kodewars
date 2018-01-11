package added;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

/**
 * Created by jorge on 14/02/17.
 * Write a method, that gets an array of integer-numbers and return an array of the averages of each integer-number and his follower, if there is one.


 *
 */
public class Kata {

    public static double[] averages(int[] numbers) {
        int arraySize = getArraySize(numbers);

        double[] results = new double[arraySize];

        if (arraySize == 0) return results;

        for (int i = 0; i < arraySize; i++) {
            results[i] = average(numbers[i], numbers[i + 1]);
        }
        return results;
    }

    private static double average(int number1, int number2) {
        return ((double) number1 + (double) number2) / 2d;
    }

    private static int getArraySize(int[] numbers) {
        if (numbers == null || numbers.length < 2) {
            return 0;
        } else {
            return numbers.length - 1;
        }
    }


    public static void main(String[] args) {
        assertEquals(Arrays.toString(new double[]{2, 2, 2, 2}), Arrays.toString(Kata.averages(new int[]{2, 2, 2, 2, 2})));
        assertEquals(Arrays.toString(new double[]{0, 0, 0, 0}), Arrays.toString(Kata.averages(new int[]{2, -2, 2, -2, 2})));
        assertEquals(Arrays.toString(new double[]{2, 4, 3, -4.5}), Arrays.toString(Kata.averages(new int[]{1, 3, 5, 1, -10})));


    }
}
