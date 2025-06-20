package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees.pathSum._666;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Author: Nitin Gupta
 * Date: 8/24/2024
 * Question Category: 666 - Path Sum IV
 * Description: https://leetcode.com/problems/path-sum-iv/ && https://leetcode.ca/2017-09-26-666-Path-Sum-IV/
 * <p>
 * If the depth of a tree is smaller than 5, then this tree can be represented by an array of three-digit integers. For each integer in this array:
 * <p>
 * The hundreds digit represents the depth d of this node where 1 <= d <= 4.
 * The tens digit represents the position p of this node in the level it belongs to where 1 <= p <= 8. The position is the same as that in a full binary tree.
 * The units digit represents the value v of this node where 0 <= v <= 9.
 * Given an array of ascending three-digit integers nums representing a binary tree with a depth smaller than 5, return the sum of all paths from the root towards the leaves.
 * <p>
 * It is guaranteed that the given array represents a valid connected binary tree.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * <p>
 * Input: nums = [113,215,221]
 * Output: 12
 * Explanation: The tree that the list represents is shown.
 * The path sum is (3 + 5) + (3 + 1) = 12.
 * Example 2:
 * <p>
 * <p>
 * <p>
 * Input: nums = [113,221]
 * Output: 4
 * Explanation: The tree that the list represents is shown.
 * The path sum is (3 + 1) = 4.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 15
 * 110 <= nums[i] <= 489
 * nums represents a valid binary tree with depth less than 5.
 * <p>
 * <p>
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 *
 * @medium
 * @BinaryTree
 * @Tree
 * @DepthFirstSearch
 * @LeetCodeLockedProblem
 * @PremimumQuestion <p>
 * <p>
 * Company Tags
 * -----
 * @Alibaba
 * @Editorial
 */
public class PthSumIV_666 {


    public static void main(String[] args) {

        boolean test = true;
        test &= test(new int[]{113, 215, 221}, 12);
        test &= test(new int[]{113, 221}, 4);
        System.out.println((test ? "All passed" : "Something Failed"));
    }

    private static boolean test(int[] nums, int expected) {
        System.out.println("-----------------");
        System.out.println(" Input : " + Arrays.toString(nums) + " expected : " + expected);

        Solution obj = new Solution();
        int actual = obj.pathSum(nums);
        System.out.println(" Output : " + actual + (actual == expected ? " Passed " : " Failed "));
        return actual == expected;
    }

    static class Solution {
        class Pair {
            int depth;
            int position;

            Pair(int depth, int position) {
                this.depth = depth;
                this.position = position;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Pair pair = (Pair) o;
                return depth == pair.depth &&
                        position == pair.position;

            }

            @Override
            public int hashCode() {
                return Objects.hash(depth, position);
            }
        }

        /**
         * https://medium.com/shuwens-leetcode-solution/leetcode-666-path-sum-iv-2fecddf78253
         *
         * @param nums
         * @return
         */

        public int pathSum(int[] nums) {

            if (nums == null)
                return 0;

            final Map<Pair, Integer> depthPositionVsValue = new HashMap<>();
            for (int num : nums) {
                int depth = num / 100;
                int position = (num - depth * 100) / 10;
                int value = num % 10;

                Pair pair = new Pair(depth - 1, position - 1); // to make it 0 based indexed
                depthPositionVsValue.put(pair, value);
            }
            int[] total = {0};
            dfs(depthPositionVsValue, 0, 0, 0, total);
            return total[0];

        }

        private void dfs(Map<Pair, Integer> map, int depth, int pos, int currentSum, int[] total) {

            Integer root = map.get(new Pair(depth, pos));

            if (root == null)
                return;

            Integer leftChild = map.get(new Pair(depth + 1, pos * 2));
            Integer rightChild = map.get(new Pair(depth + 1, pos * 2 + 1));

            //left and right exist, go deep
            if (leftChild != null && rightChild != null) {

                dfs(map, depth + 1, pos * 2, currentSum + root, total);
                dfs(map, depth + 1, pos * 2 + 1, currentSum + root, total);

            } else if (leftChild != null) {

                dfs(map, depth + 1, pos * 2, currentSum + root, total);

            } else if (rightChild != null) {

                dfs(map, depth + 1, pos * 2 + 1, currentSum + root, total);

            } else {
                // leaf node
                currentSum += root;
                total[0] += currentSum;
                return;

            }
        }
    }

}
