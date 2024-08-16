package DataStructureAlgo.Java.LeetCode2025.easy.Tree.MirrorTree;

import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.TreeBuilder;
import DataStructureAlgo.Java.helpers.templates.TreeNode;
import DataStructureAlgo.Java.nonleetcode.Tree.traversal.TreeTraversalRecursive;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date:16/08/24
 * Question Category: 101. Symmetric Tree
 * Description: https://leetcode.com/problems/symmetric-tree/description/
 * <p>
 *
 * Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,2,3,4,4,3]
 * Output: true
 * Example 2:
 *
 *
 * Input: root = [1,2,2,null,3,null,3]
 * Output: false
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 1000].
 * -100 <= Node.val <= 100
 *
 *
 * Follow up: Could you solve it both recursively and iteratively?
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link InvertTree_MirrorTree_226}
 * <p>
 * Tags
 * -----
 * @easy
 * @Tree
 * @Depth-FirstSearch
 * @Breadth-FirstSearch
 * @BinaryTree
 *
 *
 * <p>
 * Company Tags
 * -----
 * @Facebook
 * @Amazon
 * @LinkedIn
 * @Google
 * @Microsoft
 *
 *
 * @Editorial
 */

public class SymmetricTree_IsMirrorTree_101 {


    public static void main(String[] args) {
        boolean test = true;
        test &= test(new Integer[]{1,2,2,3,4,4,3}, true);
        test &= test(new Integer[]{1,2,2,null,3,null,3}, false);

        System.out.println(test ? "\nAll passed" : "\nSomething Failed");
    }

    private static boolean test(Integer[] tree, boolean expected) {
        System.out.println("-------------------------");
        System.out.println("Input 1 " + Arrays.toString(tree) + "\nExpected " + expected);
        final TreeNode root = TreeBuilder.buildTreeFromLevelOrder(tree);

        System.out.println("Tree {pre-order} : " + TreeTraversalRecursive.preOrder(root));


        Solution solutionRecursive = new Solution();
        boolean resultTreeRecursive = solutionRecursive.isSymmetric(root);
        System.out.println("Test Result Recursive : " + resultTreeRecursive);
        return resultTreeRecursive == expected;

    }


   static class Solution {
        public boolean isSymmetric(TreeNode root) {
            if(root == null)
                return true;

            return isMirrorTree(root.left, root.right);
        }

        private boolean isMirrorTree(TreeNode root1, TreeNode root2){
            if(root1 == null && root2 == null)
                return true;

            if(root1 == null || root2 == null)
                return false;

            return ( root1.val == root2.val)
                    && ( isMirrorTree(root1.left, root2.right)
                    && isMirrorTree(root1.right, root2.left));
        }
    }
}
