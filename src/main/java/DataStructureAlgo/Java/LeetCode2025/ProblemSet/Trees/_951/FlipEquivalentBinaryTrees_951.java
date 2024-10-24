package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees._951;

import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.TreeBuilder;
import DataStructureAlgo.Java.helpers.templates.TreeNode;
import DataStructureAlgo.Java.nonleetcode.Tree.traversal.TreeTraversalRecursive;

/**
 * Author: Nitin Gupta
 * Date:24/10/24
 * Question Category: 951. Flip Equivalent Binary Trees
 * Description: https://leetcode.com/problems/flip-equivalent-binary-trees/description/
 * For a binary tree T, we can define a flip operation as follows: choose any node, and swap the left and right child subtrees.
 * <p>
 * A binary tree X is flip equivalent to a binary tree Y if and only if we can make X equal to Y after some number of flip operations.
 * <p>
 * Given the roots of two binary trees root1 and root2, return true if the two trees are flip equivalent or false otherwise.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Flipped Trees Diagram
 * Input: root1 = [1,2,3,4,5,6,null,null,null,7,8], root2 = [1,3,2,null,6,4,5,null,null,null,null,8,7]
 * Output: true
 * Explanation: We flipped at nodes with values 1, 3, and 5.
 * Example 2:
 * <p>
 * Input: root1 = [], root2 = []
 * Output: true
 * Example 3:
 * <p>
 * Input: root1 = [], root2 = [1]
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in each tree is in the range [0, 100].
 * Each tree will have unique node values in the range [0, 99].
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees.MirrorTree.SymmetricTree_IsMirrorTree_101} {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees.MirrorTree.InvertTree_MirrorTree_226}
 * <p>
 * Tags
 * -----
 *
 * @Tree
 * @Depth-FirstSearch
 * @BinaryTree <p>
 * Company Tags
 * -----
 * @Google
 * @Editorial
 */

public class FlipEquivalentBinaryTrees_951 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new Integer[]{1, 2, 3, 4, 5, 6, null, null, null, 7, 8}, new Integer[]{1, 3, 2, null, 6, 4, 5, null, null, null, null, 8, 7}, true);
        test &= test(new Integer[]{}, new Integer[]{}, true);
        test &= test(new Integer[]{}, new Integer[]{1}, false);
        CommonMethods.printResult(test);
    }

    private static boolean test(Integer[] tree1, Integer[] tree2, boolean expected) {
        CommonMethods.print(new String[]{"Tree 1", "Tree 2", "Expected"}, true, tree1, tree2, expected);

        TreeNode root1 = TreeBuilder.buildTreeFromLevelOrder(tree1);
        TreeNode root2 = TreeBuilder.buildTreeFromLevelOrder(tree2);

        System.out.println("LevelOrderTraversal Of Tree1 :" + TreeTraversalRecursive.levelOrderWithNull(root1));
        System.out.println("LevelOrderTraversal Of Tree2 :" + TreeTraversalRecursive.levelOrderWithNull(root2));

        Solution solution = new Solution();
        boolean output = solution.flipEquiv(root1, root2);
        boolean pass = output == expected;
        CommonMethods.print(new String[]{"Output", "Expected", "Pass"}, false, output, expected, (pass ? "Yes" : "No"));
        return pass;


    }


    static class Solution {
        public boolean flipEquiv(TreeNode root1, TreeNode root2) {
            if (root1 == null && root2 == null)
                return true;

            if (root1 == null || root2 == null)
                return false;

            if (root1.val != root2.val)
                return false;

            //if not flipped
            boolean leftNF = flipEquiv(root1.left, root2.left);
            boolean rightNF = flipEquiv(root1.right, root2.right);

            if (leftNF && rightNF)
                return true;

            //if  flipped
            boolean leftF = flipEquiv(root1.left, root2.right);
            boolean rightF = flipEquiv(root1.right, root2.left);

            return leftF && rightF;
        }
    }
}
