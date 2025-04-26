package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch.ThreeSum._15;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 4/1/2025
 * Question Title: 15. 3Sum
 * Link:
 * Description: Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 * <p>
 * Notice that the solution set must not contain duplicate triplets.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 * Explanation:
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
 * The distinct triplets are [-1,0,1] and [-1,-1,2].
 * Notice that the order of the output and the order of the triplets does not matter.
 * Example 2:
 * <p>
 * Input: nums = [0,1,1]
 * Output: []
 * Explanation: The only possible triplet does not sum up to 0.
 * Example 3:
 * <p>
 * Input: nums = [0,0,0]
 * Output: [[0,0,0]]
 * Explanation: The only possible triplet sums up to 0.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 3 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 * <p>
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.pair.element.problems.threeSum.ThreeSum3Sum}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @TwoPointers
 * @Sorting <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @Facebook
 * @Microsoft
 * @Apple
 * @Adobe
 * @AkunaCapital
 * @Alibaba
 * @Baidu
 * @Bloomberg
 * @ByteDance
 * @Cisco
 * @Citadel
 * @Coursera
 * @DoorDash
 * @eBay
 * @Expedia
 * @Facebook
 * @GoldmanSachs
 * @Google
 * @Groupon
 * @IBM
 * @Mathworks
 * @Microsoft
 * @Oracle
 * @Paypal
 * @Postmates
 * @Qualtrics
 * @Quora
 * @Salesforce
 * @Snapchat
 * @Splunk
 * @Square
 * @Tencent
 * @Uber
 * @Visa
 * @WalmartLabs
 * @WorksApplications
 * @Yahoo
 * @Yandex <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class Three3Sum_15 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{-1, 0, 1, 2, -1, -4}, List.of(List.of(-1, -1, 2), List.of(-1, 0, 1))));
        tests.add(test(new int[]{3, 0, -2, -1, 1, 2}, List.of(List.of(-2, -1, 3), List.of(-2, 0, 2), List.of(-1, 0, 1))));
        tests.add(test(new int[]{0, 1, 1}, List.of()));
        tests.add(test(new int[]{0, 0, 0}, List.of(List.of(0, 0, 0))));
        //add tests cases here
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] grid, List<List<Integer>> expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"Grid", "Expected"}, true, grid, expected);

        List<List<Integer>> output;
        boolean pass, finalPass = true;

        ThreeSum3SumSorting threeSum3SumSorting = new ThreeSum3SumSorting();
        ThreeSum3SumSortingOptimized threeSum3SumSortingOptimized = new ThreeSum3SumSortingOptimized();
        output = threeSum3SumSorting.threeSum(grid);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;


        output = threeSum3SumSortingOptimized.threeSum(grid);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTestOutcome(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }


    static class ThreeSum3SumSorting {

        public List<List<Integer>> threeSum(int[] nums) {

            if (nums == null || nums.length == 0)
                return Collections.EMPTY_LIST;


            return threeSum(nums, 0);
        }

        /**
         * Return the triplet whose sum is = target
         * <p>
         * [-1, 0, 1, 2, -1, -4]
         * <p>
         * Sorted-> [-4,-1,-1,0,1,2]
         * <p>
         * Runtime: 175 ms, faster than 22.39% of Java online submissions for 3Sum.
         * Memory Usage: 48.7 MB, less than 65.72% of Java online submissions for 3Sum.
         *
         * @param nums
         * @param target
         * @return
         */
        private List<List<Integer>> threeSum(int[] nums, int target) {


            /**
             * to avoid duplicate triplet
             */
            final Set<List<Integer>> solution = new HashSet<>();


            Arrays.sort(nums);

            for (int i = 0; i < nums.length - 1; i++) {


                int j = i + 1;
                int k = nums.length - 1;

                while (j < k) {

                    int sum = nums[i] + nums[j] + nums[k];

                    if (sum == target) {
                        solution.add(Arrays.asList(nums[i], nums[j], nums[k]));
                        j++;
                        k--;
                    } else if (sum > target)
                        k--;
                    else
                        j++;
                }

            }

            return new ArrayList<>(solution);
        }
    }


    static class ThreeSum3SumSortingOptimized {

        public List<List<Integer>> threeSum(int[] nums) {

            if (nums == null || nums.length == 0)
                return Collections.EMPTY_LIST;


            return threeSum(nums, 0);
        }

        /**
         * Return the triplet whose sum is = target
         * <p>
         * [-1, 0, 1, 2, -1, -4]
         * <p>
         * Sorted-> [-4,-1,-1,0,1,2]
         * <p>
         * Runtime: 26 ms, faster than 97.35% of Java online submissions for 3Sum.
         * Memory Usage: 45.5 MB, less than 96.47% of Java online submissions for 3Sum.
         *
         * @param nums
         * @param target
         * @return
         */
        private List<List<Integer>> threeSum(int[] nums, int target) {


            final List<List<Integer>> solution = new ArrayList<>();

            Arrays.sort(nums);

            for (int i = 0; i < nums.length - 2; i++) {

                int a = nums[i];
                if (a > 0) break; //This improves the performance by 50%


                // Avoid duplicates
                if (i > 0 && a == nums[i - 1])
                    continue;

                int j = i + 1;
                int k = nums.length - 1;

                while (j < k) {

                    int b = nums[j];
                    int c = nums[k];


                    int sum = a + b + c;

                    if (sum == target) {
                        solution.add(Arrays.asList(nums[i], nums[j], nums[k]));

                        //As the elements are getting sorted, that brings all the duplicate pair together
                        // skip all duplicates
                        while (j < k && b == nums[++j]) ;

                        while (k > j && c == nums[--k]) ;


                    } else if (sum > target)
                        k--;
                    else
                        j++;
                }

            }

            return solution;
        }
    }
}


