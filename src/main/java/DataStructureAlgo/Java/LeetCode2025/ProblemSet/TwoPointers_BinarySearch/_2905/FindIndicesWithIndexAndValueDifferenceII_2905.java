package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._2905;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/10/2025
 * Question Title: 2905. Find Indices With Index and Value Difference II
 * Link: https://leetcode.com/problems/find-indices-with-index-and-value-difference-ii/description/
 * Description: You are given a 0-indexed integer array nums having length n, an integer indexDifference, and an integer valueDifference.
 * <p>
 * Your task is to find two indices i and j, both in the range [0, n - 1], that satisfy the following conditions:
 * <p>
 * abs(i - j) >= indexDifference, and
 * abs(nums[i] - nums[j]) >= valueDifference
 * Return an integer array answer, where answer = [i, j] if there are two such indices, and answer = [-1, -1] otherwise. If there are multiple choices for the two indices, return any of them.
 * <p>
 * Note: i and j may be equal.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [5,1,4,1], indexDifference = 2, valueDifference = 4
 * Output: [0,3]
 * Explanation: In this example, i = 0 and j = 3 can be selected.
 * abs(0 - 3) >= 2 and abs(nums[0] - nums[3]) >= 4.
 * Hence, a valid answer is [0,3].
 * [3,0] is also a valid answer.
 * Example 2:
 * <p>
 * Input: nums = [2,1], indexDifference = 0, valueDifference = 0
 * Output: [0,0]
 * Explanation: In this example, i = 0 and j = 0 can be selected.
 * abs(0 - 0) >= 0 and abs(nums[0] - nums[0]) >= 0.
 * Hence, a valid answer is [0,0].
 * Other valid answers are [0,1], [1,0], and [1,1].
 * Example 3:
 * <p>
 * Input: nums = [1,2,3], indexDifference = 2, valueDifference = 4
 * Output: [-1,-1]
 * Explanation: In this example, it can be shown that it is impossible to find two indices that satisfy both conditions.
 * Hence, [-1,-1] is returned.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n == nums.length <= 105
 * 0 <= nums[i] <= 109
 * 0 <= indexDifference <= 105
 * 0 <= valueDifference <= 109
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._2903.FindIndicesWithIndexAndValueDifferenceI_2903}
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @TwoPointers <p><p>
 * Company Tags
 * -----
 * @Google
 * @Amazon <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class FindIndicesWithIndexAndValueDifferenceII_2905 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();

        tests.add(test(new int[]{5, 1, 4, 1}, 2, 4, new int[]{0, 3}));
        tests.add(test(new int[]{2, 1}, 0, 0, new int[]{0, 0}));
        tests.add(test(new int[]{1, 2, 3}, 2, 4, new int[]{-1, -1}));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int indexDifference, int valueDifference, int[] expected) {
        //add print here
        CommonMethods.printTest(new String[]{"nums", "indexDifference", "valueDifference", "Expected"}, true, nums, indexDifference, valueDifference, expected);

        int[] output;
        boolean pass, finalPass = true;

        output = new Solution_TreeSet().findIndices(nums, indexDifference, valueDifference);
        pass = CommonMethods.compareResultOutCome(output, expected, true) || diffValidation(output, nums, valueDifference);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"TreeSet", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = new Solution_PastSlidingWindow().findIndices(nums, indexDifference, valueDifference);
        pass = CommonMethods.compareResultOutCome(output, expected, true) || diffValidation(output, nums, valueDifference);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"PastSlidingWindow", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }
    private static boolean diffValidation(int[] output, int[] nums, int v) {
        return Math.abs(nums[output[0]] - nums[output[1]]) >= v ;
    }

    // Given: nums = [5,1,3,2,8,6,4], indexDiff = 2, valueDiff = 3
    // Goal: Find if there exists a pair (i, j) such that:
    //   - abs(i - j) <= indexDiff
    //   - abs(nums[i] - nums[j]) <= valueDiff

    // Let's consider the set and form potential pairs based on indexDiff:

    // Window of indexDiff = 2 gives us these pairs:
    // (5,3), (5,2), (5,8), (5,6), (5,4)
    //        (1,2), (1,8), (1,6), (1,4)
    // ...
    //                             (8,4)

    // Observation: The second numbers in these pairs form a consistent sliding window:

    // 5 -> [3,2,8,6,4]
    // 1 -> [2,8,6,4]
    // 3 -> [8,6,4]
    // 2 -> [6,4]
    // 8 -> [4]

    // To check if any such pair meets the valueDiff condition,
    // we just need to check whether there exists a number in the window such that:
    // abs(currentNum - windowNum) <= valueDiff
    // That translates to: currentNum - valueDiff <= windowNum <= currentNum + valueDiff

    // So we can dynamically check if any number in the current sliding window falls within this range.

    // To efficiently maintain this window and perform range queries (i.e., floor/ceiling),
    // we can use a TreeSet which provides log(n) operations for insert, delete, and lookup.

    // The window is controlled by maintaining only the last `indexDiff` elements.

    // Time Complexity: O(n * log(n)) — due to TreeSet operations
    // Space Complexity: O(n) — for the TreeSet used in maintaining the sliding window

    static class Solution_TreeSet {
        public int[] findIndices(int[] nums, int d, int v) {
            int n = nums.length;

            TreeSet<int[]> set = new TreeSet<>((a, b) -> {
                if (a[0] != b[0])
                    return Integer.compare(a[0], b[0]);
                return Integer.compare(a[1], b[1]);
            });

            for (int i = d; i < n; i++)
                set.add(new int[]{nums[i], i});

            //O(n) * O(log(n)) = O(n * log(n))
            for (int i = 0; i < n - d; i++) {
                int current = nums[i];
                int[] min = set.first();
                int[] max = set.last();

                if (Math.abs(current - min[0]) >= v) {
                    return new int[]{i, min[1]};
                }

                if (Math.abs(current - max[0]) >= v) {
                    return new int[]{i, max[1]};
                }

                //exclude it from window : (log (n-d)) -> O(logn)
                set.remove(new int[]{nums[i + d], i + d});

            }
            return new int[]{-1, -1};
        }
    }

    /**
     * on clear observation of above code, we can see that, we are trying to find the min,max for each nums[i] ahead, i.e. [i+d, i+d+1, i+d+2....]
     * hence it requires pre-processing and putting them in TreeSet.
     * What if we start trying to find the min,max in reverse way ?
     * for nums[i], its [i-d, i-d-1, i-d-2.... 0]
     * now, finding the min, max of that window would be easy as we a simple track and the index of those will always be liner and fall into [0, i-d]
     * O(n) / O(1)
     */

    static class Solution_PastSlidingWindow {
        public int[] findIndices(int[] nums, int d, int v) {
            int n = nums.length;

            int minIndex = 0, maxIndex = 0;

            for (int i = d; i < n; i++) {
                int current = nums[i];

                //find the min and max index,  [i-d, i-d-1, i-d-2.... 0]
                if (nums[i - d] < nums[minIndex])
                    minIndex = i - d;

                if (nums[i - d] > nums[maxIndex])
                    maxIndex = i - d;

                if (Math.abs(current - nums[minIndex]) >= v) {
                    return new int[]{i, minIndex};
                }

                if (Math.abs(current - nums[maxIndex]) >= v) {
                    return new int[]{i, maxIndex};
                }

            }
            return new int[]{-1, -1};

        }
    }
}
