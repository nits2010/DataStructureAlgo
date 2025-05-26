package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._786;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/26/2025
 * Question Title: 786. K-th Smallest Prime Fraction
 * Link: https://leetcode.com/problems/k-th-smallest-prime-fraction/description/
 * Description:
 * 1 <= arr[i] <= 3 * 104
 * arr[0] == 1
 * arr[i] is a prime number for i > 0.
 * All the numbers of arr are unique and sorted in strictly increasing order.
 * 1 <= k <= arr.length * (arr.length - 1) / 2
 * <p>
 * <p>
 * Follow up: Can you solve the problem with better than O(n2) complexity?
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar  {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow._719.FindKthSmallestPairDistance_719}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @TwoPointers
 * @BinarySearch
 * @Sorting
 * @Heap(PriorityQueue) <p><p>
 * Company Tags
 * -----
 * @Pony.ai
 * <p>
 * -----
 * @Editorial https://leetcode.com/problems/k-th-smallest-prime-fraction/solutions/6783349/simplest-intuitive-approach-with-derivat-o1hv <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class KthSmallestPrimeFraction_786 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{1, 2, 3, 5}, 3, new int[]{2, 5}));
        tests.add(test(new int[]{1, 7}, 1, new int[]{1, 7}));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int k, int[] expected) {
        //add print here
        CommonMethods.printTest(new String[]{"nums", "k", "Expected"}, true, nums, expected);

        int[] output;
        boolean pass, finalPass = true;

        output = new Solution_UsingPQ().kthSmallestPrimeFraction(nums, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"PQ", "Pass"}, false, output, pass ? "PASS" : "FAIL");


        output = new SolutionBinarySearch().kthSmallestPrimeFraction(nums, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"BS", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }


    /**
     * Similar to {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow._719.FindKthSmallestPairDistance_719}, we can apply binary search in this problem as well.
     * <p>
     * Key Observations:
     * All elements in the array are prime numbers, and we are investigating fractions of the form arr[i] / arr[j] where i < j.
     * <p>
     * As a result, all such fractions will lie strictly within the range [0, 1).
     * <p>
     * This allows us to perform binary search on the fractional value space, i.e., on the interval [0, 1).
     * <p>
     * Approach:
     * Binary Search on Fraction Values:
     * <p>
     * Initialize low = 0.0 and high = 1.0.
     * <p>
     * While high - low > ε (a small precision threshold), perform the following:
     * <p>
     * Compute mid = (low + high) / 2.
     * <p>
     * Count the number of fractions arr[i]/arr[j] such that the value is ≤ mid.
     * <p>
     * While counting, also keep track of the maximum fraction ≤ mid and the corresponding numerator and denominator (arr[i] and arr[j]).
     * <p>
     * If the count is less than k, that means the desired fraction is larger, so set low = mid.
     * <p>
     * If the count is greater than or equal to k, it means the desired fraction is smaller or equal, so set high = mid and record the best candidate.
     * <p>
     * Return Result:
     * <p>
     * Since we need to return the actual fraction as a pair of array elements, not just the value, we track and return the fraction closest to mid and ≤ mid, along with its corresponding (arr[i], arr[j]).
     * <p>
     * Let’s break it down:
     * <p>
     * ✅ Binary Search Iterations:
     * You're binary searching over a fraction value range between 0.0 and 1.0
     * <p>
     * Precision up to ε = 1e-9 typically
     * <p>
     * So number of iterations = log2(1 / ε) ≈ 30–40 iterations
     * <p>
     * ✅ Work Per Iteration:
     * For each i, you walk through possible j (typically via two pointers), so O(n) time
     * <p>
     * Therefore:
     * <p>
     * Overall Time Complexity = O(n × log(1/ε))
     * Where:
     * <p>
     * n = arr.length
     * <p>
     * ε = precision, e.g., 1e-9, so log(1/ε) ≈ 30–40
     * <p>
     * In practice: This is about 30 * n, which is very fast for n ≤ 1000
     */

    static class SolutionBinarySearch {
        public int[] kthSmallestPrimeFraction(int[] arr, int k) {
            int n = arr.length;

            double low = 0, high = 1.0;
            int[] result = new int[2];
            while (low < high) {
                double mid = (low + high) / 2.0;

                int[] counts = countFractions(arr, k, mid);

                int fractionCount = counts[0];
                int numerator = counts[1];
                int denominator = counts[2];

                if (fractionCount == k) {
                    result[0] = arr[numerator];
                    result[1] = arr[denominator];
                    break;
                }

                if (fractionCount < k) {
                    // Not enough fractions are <= mid, so we need a larger fraction.
                    low = mid;
                } else {
                    high = mid; // Search in the lower half
                }
            }

            return result;

        }

        /**
         * arr[i] / arr[j] <= mid
         * =>  arr[i] <= mid * arr[j] i.e. we need to find the 'j' for this 'i' where this condition satisfy.
         * <p>
         * <p>
         * 1. **If the numerator increases (while the denominator remains fixed), the fraction increases.**
         * <p>
         * `(arr[i + 1] / arr[j]) > (arr[i] / arr[j])`
         * <p>
         * 2. **If the denominator increases (while the numerator remains fixed), the fraction decreases.**
         * <p>
         * `(arr[i] / arr[j + 1]) < (arr[i] / arr[j])`
         */
        private int[] countFractions(int[] arr, int k, double targetFraction) {
            int totalFractions = 0;
            int numeratorIndex = 0, denominatorIndex = 1;

            double maxFractionSoFar = 0.0; // Keep track of the largest fraction found so far that is <= targetFraction

            int i = 0; // Pointer for numerator (arr[i])
            for (int j = 1; j < arr.length; j++) { // Pointer for denominator (arr[j])

                // arr[i] / arr[j] <= mid ; increase numerator
                while (i < j && arr[i] <= arr[j] * targetFraction) {

                    // If this fraction is larger than our current maxFractionSoFar, update it
                    if (((double) arr[i] / arr[j]) > maxFractionSoFar) {
                        maxFractionSoFar = (double) arr[i] / arr[j];
                        numeratorIndex = i;
                        denominatorIndex = j;
                    }
                    i++;
                }

                // All fractions arr[0]/arr[j], arr[1]/arr[j], ..., arr[i-1]/arr[j] are <= targetFraction
                // So, add 'i' to the total count.
                totalFractions += i;

            }

            return new int[]{totalFractions, numeratorIndex, denominatorIndex};

        }
    }


    /**
     * <p>
     * Let’s explore some key insights into how fractions behave. Suppose we have three elements `a`, `b`, and `c`, such that:
     * <p>
     * ```
     * a < b < c
     * a = 1, b = 2, c = 3
     * ```
     * <p>
     * We define the following fractions:
     * <p>
     * ```
     * x = a / b = 1 / 2 = 0.5
     * y = a / c = 1 / 3 = 0.33
     * z = b / c = 2 / 3 = 0.66
     * ```
     * <p>
     * From these values, we observe the following relationships:
     * <p>
     * `x > y` → (a / b) > (a / c)
     * `x < z` → (a / b) < (b / c)
     * Therefore, (a / c) < (a / b) < (b / c)
     * <p>
     * These observations reveal two important properties of fractions:
     * <p>
     * 1. **If the numerator increases (while the denominator remains fixed), the fraction increases.**
     * <p>
     * `(arr[i + 1] / arr[j]) > (arr[i] / arr[j])`
     * <p>
     * 2. **If the denominator increases (while the numerator remains fixed), the fraction decreases.**
     * <p>
     * `(arr[i] / arr[j + 1]) < (arr[i] / arr[j])`
     * <p>
     * ### Key Insight:
     * <p>
     * If we **fix the denominator**, then the fractions are in **increasing order** as the numerator increases.
     * If we **fix the numerator**, then the fractions are in **decreasing order** as the denominator increases.
     * <p>
     * ---
     * <p>
     * ### Application: Finding the Kth Smallest Fraction
     * <p>
     * This ordering property allows us to use a **min-heap (priority queue)** to efficiently extract the Kth smallest fraction.
     * <p>
     * #### Steps:
     * <p>
     * 1. **Initialize the Min-Heap:**
     * Push the smallest possible fractions for each column (i.e., with the smallest numerator and varying denominators):
     * <p>
     * ```
     * arr[0]/arr[1], arr[0]/arr[2], ..., arr[0]/arr[n-1]
     * ```
     * <p>
     * Each element in the heap is a tuple (i, j), representing the fraction `arr[i]/arr[j]`.
     * <p>
     * 2. **Iteratively extract the smallest fraction:**
     * <p>
     * Pop the smallest fraction `(i, j)` from the heap.
     * The next smallest fraction with the same denominator (`j`) would be `arr[i+1]/arr[j]`.
     * If `i + 1 < j`, push `(i + 1, j)` into the heap.
     * <p>
     * Repeat this process `k` times to find the Kth smallest fraction.
     * <p>
     * <p>
     * Space : O(n)
     * Time : O(n) + O(k * log(n))
     */

    static class Solution_UsingPQ {
        public int[] kthSmallestPrimeFraction(int[] arr, int k) {
            int n = arr.length;

            //Pq keeps only index, however built using fractions
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Double.compare(
                    ((double) arr[a[0]] / arr[a[1]]),
                    ((double) arr[b[0]] / arr[b[1]])));

            //push all the elements with numerator as arr[0]
            for (int i = 1; i < n; i++) {
                pq.offer(new int[]{0, i});
            }

            //extract k-1 times ( 0-index-based equivalent to k)
            int count = 1;
            while (count < k) {
                int[] pop = pq.poll();

                int i = pop[0], j = pop[1];

                // Push the next fraction with same denominator and next numerator
                if (i + 1 < j) {
                    pq.offer(new int[]{i + 1, j});
                }

                count++;
            }

            int[] pop = pq.poll();
            return new int[]{arr[pop[0]], arr[pop[1]]};

        }

    }
}
