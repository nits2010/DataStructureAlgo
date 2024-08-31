package DataStructureAlgo.Java.Sorts.quickSort;

import DataStructureAlgo.Java.Sorts.Sort;
import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date: 8/31/2024
 */
public class Driver {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        test &= test(new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1});
        test &= test(new int[]{1, 7, 5, 8, 6, 3, 9, 4, 3, 2, 10});
        test &= test(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6, 7, 7, 8, 2, 3, 1, 1, 1, 10, 11, 5, 6, 2, 4, 7, 8, 5, 6});
        test &= test(new int[]{3, 2, 1, 5, 6, 4});
        test &= test(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6});

        System.out.println("===================================================");
        System.out.println((test ? "All passed" : "Something failed"));
    }

    private static boolean test(int[] input) {
        System.out.println("-------------------------------------------------");

        System.out.println("Input: " + Arrays.toString(input));
        int[] expected = Arrays.copyOf(input, input.length);
        Arrays.sort(expected);
        System.out.println("Expected: " + Arrays.toString(expected));


        Sort quickSort2WayPartitioningV1 = new QuickSort2WayPartitioningV1();
        int[] outQuickSort2WayPartitioningV1 = quickSort2WayPartitioningV1.sort(input);
        boolean testResultV1 = Arrays.equals(outQuickSort2WayPartitioningV1, expected);
        System.out.println("\nOutput 2-Way V1: " + Arrays.toString(outQuickSort2WayPartitioningV1) + "\n testResultV1: " + (testResultV1 ? "Passed" : "Failed"));

        Sort quickSort2WayPartitioningV2 = new QuickSort2WayPartitioningV2();
        int[] outQuickSort2WayPartitioningV2 = quickSort2WayPartitioningV2.sort(input);
        boolean testResultV2 = Arrays.equals(outQuickSort2WayPartitioningV2, expected);
        System.out.println("\nOutput 2-Way V2: " + Arrays.toString(outQuickSort2WayPartitioningV2) + "\n testResultV2: " + (testResultV2 ? "Passed" : "Failed"));

        Sort quickSort3WayPartitioningV1 = new QuickSort3WayPartitioning();
        int[] outQuickSort3WayPartitioningV1 = quickSort3WayPartitioningV1.sort(input);
        boolean testResult3Way = Arrays.equals(outQuickSort3WayPartitioningV1, expected);
        System.out.println("\nOutput 3-Way: " + Arrays.toString(outQuickSort3WayPartitioningV1) + "\n testResult3Way: " + (testResult3Way ? "Passed" : "Failed"));

        boolean finalResult = testResultV1 && testResultV2 && testResult3Way;
        System.out.println("\n\nFinal Result: " + (finalResult ? "Passed" : "Failed"));
        return finalResult;
    }
}
