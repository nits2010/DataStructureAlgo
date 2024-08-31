package DataStructureAlgo.Java.LeetCode2025.easy.Heap_PriorityQueue;

import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.templates.MinHeap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Author: Nitin Gupta
 * Date: 8/31/2024
 * Question Category: 703. Kth Largest Element in a Stream
 * Description: https://leetcode.com/problems/kth-largest-element-in-a-stream/description/
 * You are part of a university admissions office and need to keep track of the kth highest test score from applicants in real-time. This helps to determine cut-off marks for interviews and admissions dynamically as new applicants submit their scores.
 * <p>
 * You are tasked to implement a class which, for a given integer k, maintains a stream of test scores and continuously returns the kth highest test score after a new score has been submitted. More specifically, we are looking for the kth highest score in the sorted list of all scores.
 * <p>
 * Implement the KthLargest class:
 * <p>
 * KthLargest(int k, int[] nums) Initializes the object with the integer k and the stream of test scores nums.
 * int add(int val) Adds a new test score val to the stream and returns the element representing the kth largest element in the pool of test scores so far.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input:
 * ["KthLargest", "add", "add", "add", "add", "add"]
 * [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
 * <p>
 * Output: [null, 4, 5, 5, 8, 8]
 * <p>
 * Explanation:
 * <p>
 * KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
 * kthLargest.add(3); // return 4
 * kthLargest.add(5); // return 5
 * kthLargest.add(10); // return 5
 * kthLargest.add(9); // return 8
 * kthLargest.add(4); // return 8
 * <p>
 * Example 2:
 * <p>
 * Input:
 * ["KthLargest", "add", "add", "add", "add"]
 * [[4, [7, 7, 7, 7, 8, 3]], [2], [10], [9], [9]]
 * <p>
 * Output: [null, 7, 7, 7, 8]
 * <p>
 * Explanation:
 * <p>
 * KthLargest kthLargest = new KthLargest(4, [7, 7, 7, 7, 8, 3]);
 * kthLargest.add(2); // return 7
 * kthLargest.add(10); // return 7
 * kthLargest.add(9); // return 7
 * kthLargest.add(9); // return 8
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= nums.length <= 104
 * 1 <= k <= nums.length + 1
 * -104 <= nums[i] <= 104
 * -104 <= val <= 104
 * At most 104 calls will be made to add.
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 *
 * @easy
 * @Tree
 * @Design
 * @BinarySearchTree
 * @Heap(PriorityQueue)
 * @BinaryTree
 * @DataStream <p>
 * <p>
 * Company Tags
 * -----
 * @Amazon
 * @Google
 * @Adobe
 * @Facebook
 * @LinkedIn
 * @Editorial
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
