package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees._250;

import DataStructureAlgo.Java.helpers.TreeBuilder;
import DataStructureAlgo.Java.helpers.templates.TreeNode;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date: 8/23/2024
 * Question Category: 250. Count Univalue Subtrees
 * Description: https://leetcode.com/problems/count-univalue-subtrees/description/
 * https://leetcode.ca/all/250.html , https://www.geeksforgeeks.org/find-count-of-singly-subtrees/
 * Given a binary tree, count the number of uni-value subtrees.
 *
 * A Uni-value subtree means all nodes of the subtree have the same value.
 *
 * Example :
 *
 * Input:  root = [5,1,5,5,5,null,5]
 *
 *               5
 *              / \
 *             1   5
 *            / \   \
 *           5   5   5
 *
 * Output: 4
 *
 * Example 2:
 *
 * Input: root = []
 * Output: 0
 * Example 3:
 *
 * Input: root = [5,5,5,5,5,null,5]
 * Output: 6
 *
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 * @medium
 *
 * <p>
 * <p>
 * Company Tags
 * -----
 * @Amazon
 * @Bloomberg
 * @Box
 * @Facebook
 * @Google
 *
 * @Editorial <a href="https://leetcode.ca/2016-08-06-250-Count-Univalue-Subtrees">...</a>
 */
public class CountUnivalueSubtree_250 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new Integer[]{5, 1, 5, 5, 5, null, 5}, 4);
        test &= test(new Integer[]{5, 5, 5, 5, 5, null, 5}, 6);

        System.out.println("========================================================");
        System.out.println("========================================================");
        System.out.println((test ? "All passed" : "Something Failed"));
        System.out.println("========================================================");
        System.out.println("========================================================");
    }

    private static boolean test(Integer[] input, int expected) {
        System.out.println("------------------------------");
        System.out.println("Input : "+ Arrays.toString(input) + "\nexpected : "+ expected);

        Solution solution = new Solution();
        int output = solution.countUnivalSubtrees(TreeBuilder.buildTreeFromLevelOrder(input));

        boolean testResult = expected == output;
        System.out.println("output : "+ output + " testResult "+(testResult ? "Pass" : "Fail"));

        return testResult;
    }

    static class Solution {

        public int countUnivalSubtrees(TreeNode root) {
            if(root == null)
                return 0;

            int []count = {0};
            countUnivalueSubtrees(root, count);
            return count[0];
        }

        /**
         * Algo: Post order traversal
         * 1. Check left subtree is a univalue subtree
         * 2. Check right subtree is a univalue subtree
         * 3. If left and right, subtree are unique subtree ( which could be either leaf nodes or root of subtree ) then check it against current root.
         * 4. if both left and right are unique subtree and root value is same as left and right then increase count by 1
         *
         * Time complexity: O(n),  where n is the number of nodes in given binary tree.
         * Auxiliary Space: O(h), where h is the height of the tree due to recursion call.
         *
         * @param root
         * @param count
         * @return
         */
        private boolean countUnivalueSubtrees(TreeNode root, int [] count){
            if(root == null)
                return true;


           boolean left = countUnivalueSubtrees(root.left, count);
           boolean right = countUnivalueSubtrees(root.right, count);

           if(!left || !right)
               return false;

           int leftValue = root.left == null ? root.val : root.left.val;
           int rightValue = root.right == null ? root.val : root.right.val;

           if(leftValue == rightValue && leftValue == root.val){
               count[0]++;
               return true;
           }

           return false;

        }
    }

}
