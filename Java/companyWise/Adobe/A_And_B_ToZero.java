package Java.companyWise.Adobe;

import Java.helpers.GenericPrinter;
import Java.LeetCode.pair.element.problems.aAndBZero.AndSumToZero;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-25
 * Description: https://leetcode.com/discuss/interview-experience/133715/Adobe-MTS-1
 * Finding total number of pair of elements (say, a and b) from elements in an array such that a (AND) b is 0. ( use brute force approach)
 * {@link AndSumToZero}
 *
 *
 */
public class A_And_B_ToZero {

    public static void main(String[] args) {
        test(new int[]{1, 2, 6, 9, 3});
        test(new int[]{1, 2, 6, 9, 3, 0});
    }

    private static void test(int[] nums) {
        System.out.println("\n Input :" + GenericPrinter.toString(nums));

        BruteForce bruteForce = new BruteForce();

        System.out.println("Brute Force :" + GenericPrinter.toString(bruteForce.pairs(nums)));
    }

    /**
     * We evaluate all pairs and see if they obey a&b=0
     * <p>
     * O(n^2)
     */
    private static class BruteForce {

        private List<int[]> pairs(int[] items) {
            if (items == null || items.length == 0)
                return Collections.EMPTY_LIST;

            final Set<int[]> pairs = new HashSet<>();

            for (int i = 0; i < items.length; i++) {

                for (int j = i + 1; j < items.length; j++) {

                    int e1 = items[i];
                    int e2 = items[j];

                    if ((e1 & e2) == 0)
                        pairs.add(new int[]{e1, e2});

                }
            }


            return new ArrayList<>(pairs);
        }
    }
}
