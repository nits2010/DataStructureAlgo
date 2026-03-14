package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Heap._703;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Author: Nitin Gupta
 * Date: 2026-03-14
 * Question Title: Kth Largest Element In A Stream
 * Link: https://leetcode.com/problems/kth-largest-element-in-astream/
 * Description:
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class KthLargestElementInAStream_703 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[]{4, 5, 8, 2}, 3, new int[]{3, 5, 10, 9, 4}, Arrays.asList(null, 4, 5, 5, 8, 8));
        test &= test(new int[]{7, 7, 7, 7, 8, 3}, 4, new int[]{2, 10, 9, 9}, Arrays.asList(null, 7, 7, 7, 8));

        System.out.println("====================================");
        System.out.println((test ? "All passed" : "Something failed"));
    }

    private static boolean test(int[] nums, int k, int[] input, List<Integer> expected) {
        System.out.println("------------------------------------");
        System.out.println(" Nums : " + Arrays.toString(nums) + " k = " + k + " Stream :" + Arrays.toString(input) + " Expected Output:" + expected);

        KthLargest kthLargest = new KthLargest(k, nums);
        List<Integer> output = new ArrayList<>();
        output.add(null);
        for (int n : input) {
            output.add(kthLargest.add(n));
        }

        boolean testResult = CommonMethods.equalsValues(output, expected);
        System.out.println("Actual Output:" + output + " testResult " + (testResult ? "Pass" : "Fail"));

        return testResult;

    }


    /**
     * maintain a min PQ of size k
     */
    static class KthLargest {

        final PriorityQueue<Integer> pq;
        final int k;

        public KthLargest(int k, int[] nums) {
            pq = new PriorityQueue<>(k);
            this.k = k;
            for (int num : nums) {
                add(num);

            }
        }

        public int add(int val) {
            pq.offer(val);
            if (pq.size() > k)
                pq.poll();
            return pq.peek();
        }
    }


}
