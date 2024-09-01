package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees.pathSum;

import DataStructureAlgo.Java.helpers.TreeBuilder;
import DataStructureAlgo.Java.helpers.templates.TreeNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Nitin Gupta
 * Date: 8/14/2024
 * Question Category: 437. Path Sum III
 * Description: https://leetcode.com/problems/path-sum-iii/description/
 * Given the root of a binary tree and an integer targetSum, return the number of paths where the sum of the values along the path equals targetSum.
 * <p>
 * The path does not need to start or end at the root or a leaf, but it must go downwards (i.e., traveling only from parent nodes to child nodes).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * Output: 3
 * Explanation: The paths that sum to 8 are shown.
 * Example 2:
 * <p>
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * Output: 3
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 1000].
 * -109 <= Node.val <= 109
 * -1000 <= targetSum <= 1000
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
 * @Tree
 * @Depth-FirstSearch
 * @BinaryTree <p>
 * <p>
 * Company Tags
 * -----
 * @Facebook
 * @tiktok
 * @Google
 * @DoorDash
 *
 *
 * @Editorial <a href="https://leetcode.com/problems/path-sum-iii/solutions/5683406/most-elaborated-solution-build-logic-2-solution-with-optimization">...</a>
 * <a href="https://www.geeksforgeeks.org/prefix-sum-array-implementation-applications-competitive-programming/">...</a>
 */
public class PathSumIII_437 {

    public static void main(String[] args) {
        boolean test = true;

        test &= test(new Integer[]{10, 5, -3, 3, 2, null, 11, 3, -2, null, 1}, 8, 3);
        test &= test(new Integer[]{5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1}, 22, 3);
        test &= test(new Integer[]{1000000000, 1000000000, null, 294967296, null, 1000000000, null, 1000000000, null, 1000000000}, 0, 0);

        System.out.println(test ? "\nAll Passed" : "\nSomething Failed");
    }

    private static boolean test(Integer[] input, int targetSum, int expected) {
        System.out.println("-----------------------");
        System.out.println("Input :" + Arrays.toString(input) + " targetSum : " + targetSum);
        System.out.println("expected :" + expected);


        TreeNode root = TreeBuilder.buildTreeFromLevelOrder(input);

        SolutionThreeFold solutionThreeFold = new SolutionThreeFold();
        SolutionOneFold solutionOneFold = new SolutionOneFold();

        int outputThreeFold = solutionThreeFold.pathSum(root, targetSum);
        boolean resultThreeFold = expected == outputThreeFold;
        System.out.println("\nOutputThreeFold : " + outputThreeFold + " resultThreeFold : " + resultThreeFold);

        int outputOneFold = solutionOneFold.pathSum(root, targetSum);
        boolean resultOneFold = expected == outputOneFold;
        System.out.println("\n OutputOneFold : " + outputOneFold + " resultOneFold : " + resultOneFold);
        return resultThreeFold && resultOneFold;

    }


    static class SolutionThreeFold {
        int paths = 0;

        public int pathSum(TreeNode root, int targetSum) {

            if (root == null)
                return 0;

            //dfs including current root.
            dfsCurrent(root, targetSum, 0);

            //dfs excluding current root on the left side
            pathSum(root.left, targetSum);

            //dfs excluding current root on the right side
            pathSum(root.right, targetSum);
            return paths;
        }

        private void dfsCurrent(TreeNode root, int targetSum, long sum) {
            if (root == null)
                return;
            //include current root and check for the sum
            if (sum + root.val == targetSum)
                paths++;

            //include current root and go left and right
            dfsCurrent(root.left, targetSum, sum + root.val);
            dfsCurrent(root.right, targetSum, sum + root.val);
        }


    }

    static class SolutionOneFold {


        public int pathSum(TreeNode root, int targetSum) {
            if (root == null)
                return 0;

            final Map<Long, Integer> sumVsCountMap = new HashMap<>();
            sumVsCountMap.put(0L, 1); // 0 is the sum of considering nothing, and we can always 0 sum 1 time.

            return dfs(root, targetSum, 0, sumVsCountMap);
        }

        private int dfs(TreeNode root, int targetSum, long currentSum, Map<Long, Integer> sumVsCountMap) {
            if (root == null)
                return 0;

            //add current node in sum
            currentSum = currentSum + root.val;
            int count = 0 ;

            //check if the current sum - targetSum is present in the map, 0 means nothing found so far
            int countAgainstTargetSum = sumVsCountMap.getOrDefault(currentSum - targetSum, 0);

            //add this to our total path, note, if the target sum has not found, countAgainstTargetSum = 0
            count += countAgainstTargetSum;

            //update the map against the current sum, maybe we have seen it more than one time
            sumVsCountMap.put(currentSum, sumVsCountMap.getOrDefault(currentSum, 0) + 1);

            //apply the same in left side and ride side
            int leftSidePaths = dfs(root.left, targetSum, currentSum, sumVsCountMap);
            int rightSidePaths = dfs(root.right, targetSum, currentSum, sumVsCountMap);

            //add both the paths in final
            count += leftSidePaths + rightSidePaths;

            //remove the current sum from our map
            sumVsCountMap.put(currentSum, sumVsCountMap.get(currentSum) - 1);

            return count;

        }
    }
}
