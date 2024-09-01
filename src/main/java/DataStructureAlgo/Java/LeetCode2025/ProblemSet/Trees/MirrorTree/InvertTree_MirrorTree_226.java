package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees.MirrorTree;

import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.TreeBuilder;
import DataStructureAlgo.Java.helpers.templates.TreeNode;
import DataStructureAlgo.Java.nonleetcode.Tree.traversal.TreeTraversalRecursive;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date:16/08/24
 * Question Category: 226. Invert Binary Tree
 * Description: https://leetcode.com/problems/invert-binary-tree/description/
 * <p>
 * Given the root of a binary tree, invert the tree, and return its root.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [4,2,7,1,3,6,9]
 * Output: [4,7,2,9,6,3,1]
 * Example 2:
 * <p>
 * <p>
 * Input: root = [2,1,3]
 * Output: [2,3,1]
 * Example 3:
 * <p>
 * Input: root = []
 * Output: []
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
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
 * @easy
 * @Tree
 * @Depth-FirstSearch
 * @Breadth-FirstSearch
 * @BinaryTree <p>
 * Company Tags
 * -----
 * @Amazon
 * @Facebook
 * @Google
 * @Uber
 * @Apple
 * @Editorial
 */

public class InvertTree_MirrorTree_226 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new Integer[]{4, 2, 7, 1, 3, 6, 9}, new Integer[]{4, 7, 2, 9, 6, 3, 1});
        System.out.println(test ? "\nAll passed" : "\nSomething Failed");
    }

    private static boolean test(Integer[] tree, Integer[] expected) {
        System.out.println("-------------------------");
        System.out.println("Input 1 " + Arrays.toString(tree) + "\nExpected 2 " + Arrays.toString(expected));
        final TreeNode root = TreeBuilder.buildTreeFromLevelOrder(tree);
        final TreeNode expectedTreeRot = TreeBuilder.buildTreeFromLevelOrder(expected);

        System.out.println("Tree {pre-order} : " + TreeTraversalRecursive.preOrder(root));
        System.out.println("Expected Tree  {pre-order} : " + TreeTraversalRecursive.preOrder(expectedTreeRot));

        SolutionRecursive solutionRecursive = new SolutionRecursive();
        TreeNode resultTreeRecursive = solutionRecursive.invertTree(root);
        System.out.println("Result Tree Recursive {pre-order} : " + TreeTraversalRecursive.preOrder(resultTreeRecursive));
        boolean testResultRecursive = CommonMethods.isSameTree(expectedTreeRot, resultTreeRecursive);
        System.out.println("Test Result Recursive : " + testResultRecursive);
        return testResultRecursive;

    }


    static class SolutionRecursive {
        public TreeNode invertTree(TreeNode root) {
            if (root == null)
                return root;

            invertTree(root.left);
            invertTree(root.right);

            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;

            return root;
        }
    }
}
