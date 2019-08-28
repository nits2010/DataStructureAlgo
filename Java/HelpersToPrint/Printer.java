package Java.HelpersToPrint;

import java.util.Arrays;
import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-24
 * Description:
 */
public class Printer {

    public static void print(final int a[]) {
        System.out.println();
        System.out.print("[ ");
        Arrays.stream(a).forEach(i -> System.out.print(i + " "));
        System.out.print("]");
        System.out.println();

    }

    public static void print(final int a[][]) {
        System.out.println();

        for (int i = 0; i < a.length; i++) {
            System.out.println();
            Arrays.stream(a[i]).forEach(x -> System.out.print(x + " "));
        }
        System.out.println();
    }


    public static void print(final char a[][]) {
        System.out.println();

        for (int i = 0; i < a.length; i++) {
            System.out.println();
            for (int j = 0; j < a[0].length; j++) {
                System.out.print(a[i][j] + " ");
            }

        }
        System.out.println();
    }

    public static void print(final List<int[]> list) {

        System.out.println();
        for (int i[] : list) {

            System.out.print(" ( ");
            for (int x = 0; x < i.length; x++) {
                System.out.print(i[x]);
                if (x != i.length - 1)
                    System.out.print(" , ");
            }
            System.out.print(" ) ");
        }
    }

    public static String toString(int[] nums) {
        if (null == nums || nums.length == 0)
            return null;

        StringBuilder result = new StringBuilder("[");

        for (int i = 0; i < nums.length; i++) {
            result.append(nums[i]);
            result.append(",");
        }
        result.setCharAt(result.length() - 1, ']');

        return result.toString();
    }


    public static String toString(double[] nums) {
        if (null == nums || nums.length == 0)
            return null;

        StringBuilder result = new StringBuilder("[");

        for (int i = 0; i < nums.length; i++) {
            result.append(nums[i]);
            result.append(",");
        }
        result.setCharAt(result.length() - 1, ']');

        return result.toString();
    }

    public static String toString(char[] nums) {
        if (null == nums || nums.length == 0)
            return null;

        StringBuilder result = new StringBuilder("[");

        for (int i = 0; i < nums.length; i++) {
            result.append(nums[i]);
            result.append(",");
        }
        result.setCharAt(result.length() - 1, ']');

        return result.toString();
    }

    public static String toString(int[][] nums) {
        if (null == nums || nums.length == 0)
            return null;

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {


            result.append(toString(nums[i]));
            result.setCharAt(result.length() - 1, ']');
            result.append("\n");
        }


        return result.toString();

    }


    public static String toString(char[][] nums) {
        if (null == nums || nums.length == 0)
            return null;

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {


            result.append(toString(nums[i]));
            result.setCharAt(result.length() - 1, ']');
            result.append("\n");
        }


        return result.toString();

    }

    public static String toString(final List<int[]> list) {
        if (null == list || list.size() == 0)
            return null;

        StringBuilder result = new StringBuilder();
        for (int[] x : list) {
            result.append(toString(x) + ",");
        }

        return result.toString().substring(0, result.length() - 1);
    }

    public static int[] copyOf(int[] input) {
        int[] copy = new int[input.length];

        for (int i = 0; i < input.length; i++)
            copy[i] = input[i];

        return copy;
    }

    public static int[][] copyOf(int[][] input) {
        int[][] copy = new int[input.length][input[0].length];

        for (int i = 0; i < input.length; i++)
            for (int j = 0; j < input[0].length; j++)
                copy[i][j] = input[i][j];

        return copy;
    }

    public static char[] copyOf(char[] input) {
        char[] copy = new char[input.length];

        for (int i = 0; i < input.length; i++)
            copy[i] = input[i];

        return copy;
    }


    public static char[][] copyOf(char[][] input) {
        char[][] copy = new char[input.length][input[0].length];

        for (int i = 0; i < input.length; i++)
            for (int j = 0; j < input[0].length; j++)
                copy[i][j] = input[i][j];

        return copy;

    }


    public static <T> T[] copyOf(T[] input) {

        if (input == null || input.length == 0)
            return input;
        int n = input.length;
        return Arrays.copyOf(input, input.length);
    }


    public static void main(String[] args) {

        int x[] = {1, 2, 3};
        System.out.println(toString(copyOf(x)));

        int x2[][] = {{1, 2, 3}, {4, 5, 6}};
        int x2d[][] = copyOf(x2);
        System.out.println(x2 == x2d);

        System.out.println(toString(new int[]{1, 3, 4}));
        System.out.println(toString(new int[][]{{1, 3, 4}, {7, 8, 9}, {10, 11, 12}}));
        System.out.println(toString(new char[]{'a', 'b', 'c'}));
        System.out.println(toString(new char[][]{{'a', 'b', 'c'}, {'x', 'y', 'z'}, {'t', 'y', 'p'}}));
    }
}
