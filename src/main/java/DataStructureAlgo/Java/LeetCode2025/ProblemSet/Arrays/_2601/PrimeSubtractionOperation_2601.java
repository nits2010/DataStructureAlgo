package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays._2601;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date: 11/11/2024
 * Question Category:2601. Prime Subtraction Operation
 * Description:
 * You are given a 0-indexed integer array nums of length n.
 * <p>
 * You can perform the following operation as many times as you want:
 * <p>
 * Pick an index i that you haven’t picked before, and pick a prime p strictly less than nums[i], then subtract p from nums[i].
 * Return true if you can make nums a strictly increasing array using the above operation and false otherwise.
 * <p>
 * A strictly increasing array is an array whose each element is strictly greater than its preceding element.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [4,9,6,10]
 * Output: true
 * Explanation: In the first operation: Pick i = 0 and p = 3, and then subtract 3 from nums[0], so that nums becomes [1,9,6,10].
 * In the second operation: i = 1, p = 7, subtract 7 from nums[1], so nums becomes equal to [1,2,6,10].
 * After the second operation, nums is sorted in strictly increasing order, so the answer is true.
 * Example 2:
 * <p>
 * Input: nums = [6,8,11,12]
 * Output: true
 * Explanation: Initially nums is sorted in strictly increasing order, so we don't need to make any operations.
 * Example 3:
 * <p>
 * Input: nums = [5,8,3]
 * Output: false
 * Explanation: It can be proven that there is no way to perform operations to make nums sorted in strictly increasing order, so the answer is false.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 1000
 * nums.length == n
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
 * @medium
 * @Array
 * @Math
 * @BinarySearch
 * @Greedy
 * @NumberTheory <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSoltuion
 */
public class PrimeSubtractionOperation_2601 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        //add tests cases here
        tests.add(test(new int[]{998,2}, true));
        tests.add(test(new int[]{5, 9, 8}, true));
        tests.add(test(new int[]{5, 7, 3}, false));
        tests.add(test(new int[]{5, 2, 3}, false));
        tests.add(test(new int[]{4, 9, 6, 10}, true));
        tests.add(test(new int[]{6, 8, 11, 12}, true));
        tests.add(test(new int[]{5, 8, 3}, false));
        tests.add(test(new int[]{5, 8, 3}, false));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, boolean expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"Num", "Expected"}, true, nums, expected);

        boolean output;
        boolean pass, finalPass = true;
        Solution solution = new Solution();
        output = solution.primeSubOperation(nums);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"Output", "Pass"}, false, output, pass ? "Pass" : "Fail");


        return finalPass;

    }


    static class Solution {
        public boolean primeSubOperation(int[] nums) {

            boolean[] prime = primeNumbers(Arrays.stream(nums).max().getAsInt());
            // System.out.println(Arrays.toString(notPrime));

            for (int i = nums.length - 2; i >= 0; i--) {

                // if they are not strictly increasing
                if (nums[i] >= nums[i + 1]) {

                    // we need to fix nums[i], to fix it, we need a prime number that is less than nums[i] but greater than nums[i] - nums[i+1]
                    //this is because to make nums[i] < nums[i+1] => nums[i] - nums[i+1] < 0, hence if prime < nums[i] and prime > nums[i] - nums[i+1]
                    //then nums[i] - prime < nums[i+1]

                    //example [9,6]
                    // nums[0] = 9, nums[1] = 6
                    //nums[0] - nums[1] = 3, means if we subtract '3' from nums[0] then it will be 9-3 = 6 which makes them same [6,6] to make it increasing
                    // nums[0] < 6 hence, if we find a prime number that is greater than 3 but less than 9 than we can make it increasing, like prime= 7
                    // [9-7, 6] = [2,6]
                    int num = nums[i] - nums[i + 1] ;
                    int primeN = smallestPrimeLessThan(num, nums[i]-1, prime);
                    if (primeN == -1 || primeN == num)
                        return false;

                    nums[i] -= primeN;
                }

            }

            return true;

        }

        private int smallestPrimeLessThan(int minPrimeNeeded, int upperBound, boolean[] prime) {
            for (int i = minPrimeNeeded+1; i <= upperBound; i++) {
                if (prime[i]) {
                    return i;
                }
            }
            return -1; // No prime found in the range
        }
//        private int smallestPrimeLessThan(int l, int r, boolean[] prime) {
//
//            int result = -1;
//            while (l <= r) {
//                int mid = (l + r) >> 1;
//
//                if (prime[mid]) {
//                    result = mid;
//                    r = mid - 1;
//                } else {
//                    l = mid + 1;
//                }
//            }
//            return result;
//
//        }

        // n = 1000
        private boolean[] primeNumbers(int max) {
            boolean[] prime = new boolean[max + 1];
            Arrays.fill(prime, true);
            prime[0] = prime[1] = false;

            for (int i = 2; i * i <= max; i++) {
                if (prime[i]) {
                    for (int j = i * i; j <= max; j += i) {
                        prime[j] = false;
                    }
                }
            }
            return prime;
        }
    }
}
