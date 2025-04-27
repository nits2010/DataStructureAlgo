package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch.ThreeSum._923;

import DataStructureAlgo.Java.LeetCode.pair.element.problems.twoSum.TwoSum2Sum;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 4/4/2025
 * Question Title: 923. 3Sum With Multiplicity
 * Link: https://leetcode.com/problems/3sum-with-multiplicity/description/
 * Description: Given an integer array arr, and an integer target, return the number of tuples i, j, k such that i < j < k and arr[i] + arr[j] + arr[k] == target.
 * <p>
 * As the answer can be very large, return it modulo 109 + 7.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [1,1,2,2,3,3,4,4,5,5], target = 8
 * Output: 20
 * Explanation:
 * Enumerating by the values (arr[i], arr[j], arr[k]):
 * (1, 2, 5) occurs 8 times;
 * (1, 3, 4) occurs 8 times;
 * (2, 2, 4) occurs 2 times;
 * (2, 3, 3) occurs 2 times.
 * Example 2:
 * <p>
 * Input: arr = [1,1,2,2,2,2], target = 5
 * Output: 12
 * Explanation:
 * arr[i] = 1, arr[j] = arr[k] = 2 occurs 12 times:
 * We choose one 1 from [1,1] in 2 ways,
 * and two 2s from [2,2,2,2] in 6 ways.
 * Example 3:
 * <p>
 * Input: arr = [2,1,3], target = 6
 * Output: 1
 * Explanation: (1, 2, 3) occured one time in the array so we return 1.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 3 <= arr.length <= 3000
 * 0 <= arr[i] <= 100
 * 0 <= target <= 300
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.pair.element.problems.threeSum.ThreeSum3SumMultiplicity}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 * @medium
 * @Array
 * @HashTable
 * @TwoPointers
 * @Sorting
 * @Counting <p><p>
 * Company Tags
 * -----
 * @Quora <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class Three3SumWithMultiplicity_923 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{1, 1, 2, 2, 3, 3, 4, 4, 5, 5}, 8, 20));
        tests.add(test(new int[]{1, 1, 2, 2, 2, 2}, 5, 12));
        tests.add(test(new int[]{2, 1, 3}, 6, 1));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] grid, int target, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"Grid", "Expected"}, true, grid, target, expected);

        int output = 0;
        boolean pass, finalPass = true;

        SolutionSorting solutionSorting = new SolutionSorting();
        output = solutionSorting.threeSumMulti(grid, target);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Sorting", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        SolutionCounting solutionCounting = new SolutionCounting();
        output = solutionCounting.threeSumMulti(grid, target);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Counting", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }


    static class SolutionSorting {
        //O(n^2)
        public int threeSumMulti(int[] arr, int target) {
            if (arr == null || arr.length == 0)
                return 0;
            long counts = 0;

            Arrays.sort(arr);
            int n = arr.length;
            for (int i = 0; i < n - 1; i++) {

                int a = arr[i];

                int j = i + 1;
                int k = n - 1;

                while (j < k) {
                    int b = arr[j];
                    int c = arr[k];

                    int sum = a + b + c;

                    if (sum < target)
                        j++;
                    else if (sum > target)
                        k--;
                    else {
                        if (b != c) {
                            int countJ = 1;
                            int countK = 1;

                            //count duplicates
                            while (j < k && b == arr[++j])
                                countJ++;
                            while (k > j && c == arr[--k])
                                countK++;

                            counts += ((long) countJ * countK);

                        } else {

                            // there are nC2 ways of selecting 2 out of n duplicates..so n(n-1)/2
                            int count = k - j + 1;
                            counts += ((long) count * (count - 1)) / 2;
                            break; //since all the elements are same
                        }

                    }
                }

            }

            return (int) (counts % (1e9 + 7));

        }
    }


    /**
     * In the above solution, once we found that sum = target, we need to calculate all the duplicate elements for j and k.
     * That introduces an extra loop [ though that loop minimizes the j and k while loop run time. ]
     * <p>
     * But what if we already know how many of them are there after each i'th element.
     * sum = nums[i] + nums[j] + nums[k]
     * <p>
     * If we know how many nums[j] + nums[k] are present, then we can directly add those contributions to our solution.
     * <p>
     * This then reduce to {@link TwoSum2Sum} because we need to find all nums[i] + {nums[j] + nums[k]} where {x} denotes the number of times x occurred
     * <p>
     * In order to compute them, we can assume and fix an ith element and try to find if there is a sum present in our set which is nums[j] + nums[k] = target - {nums[i]}
     * if there is a sum present in the set, we can increment the count of that sum by its frequency.
     * However, if there is not, then with respect to this ith element, we have not found any sum present in the set which is nums[j] + nums[k] = target - {nums[i]}
     * <p>
     * To build the sum, we can consider all the elements from the left side of the ith element and sum them up and hold its frequent
     * Algorithm:
     * compute nums[j] + nums[k] count after each i'th element
     */
    static class SolutionCounting {
        public int threeSumMulti(int[] arr, int target) {
            if (arr == null || arr.length == 0)
                return 0;
            long counts = 0;
            //sum vs. frequency of that sum
            HashMap<Integer, Integer> map = new HashMap<>();

            for (int i = 0; i < arr.length; i++) {

                //is there any sum present in the set which is target - a[i]
                counts += map.getOrDefault(target - arr[i], 0);

                //now build up the sum for future i'th element including current arr[i]
                for (int j = 0; j < i; j++)
                    map.put(arr[i] + arr[j], map.getOrDefault(arr[i] + arr[j], 0) + 1);
            }
            return (int) (counts % (1e9 + 7));
        }
    }

}
