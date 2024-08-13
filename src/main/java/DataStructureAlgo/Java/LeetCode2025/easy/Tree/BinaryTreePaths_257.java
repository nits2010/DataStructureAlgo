package DataStructureAlgo.Java.LeetCode2025.easy.Tree;

import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.TreeBuilder;
import DataStructureAlgo.Java.helpers.templates.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 8/13/2024
 * Question Category: 257. Binary Tree Paths
 * Description: https://leetcode.com/problems/binary-tree-paths/description/
 *
 * <p>
 * Given the root of a binary tree, return all root-to-leaf paths in any order.
 * <p>
 * A leaf is a node with no children.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [1,2,3,null,5]
 * Output: ["1->2->5","1->3"]
 * Example 2:
 * <p>
 * Input: root = [1]
 * Output: ["1"]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [1, 100].
 * -100 <= Node.val <= 100
 * <p>
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.tree.AllRootToLeafPathBinaryTree}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 *
 * @easy <p>
 * Company Tags
 * -----
 */
public class BinaryTreePaths_257 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new Integer[]{1, 2, 3, null, 5}, List.of("1->2->5", "1->3"));
        test &= test(new Integer[]{1}, List.of("1"));

        System.out.println(test ? "\n All Passed" : "\n Something Failed");
    }

    private static boolean test(Integer[] input, List<String> expected) {
        System.out.println("-----------------------");
        System.out.println("Input :" + Arrays.toString(input));
        System.out.println("expected :" + expected);
        TreeNode root = TreeBuilder.buildTreeFromLevelOrder(input);

        SolutionRecursive solutionRecursive = new SolutionRecursive();
        SolutionRecursiveSimplified solutionRecursiveSimplified = new SolutionRecursiveSimplified();


        List<String> outputRecursive = solutionRecursive.binaryTreePaths(root);
        List<String> outputRecursiveSimplified = solutionRecursiveSimplified.binaryTreePaths(root);
        System.out.println("outputRecursive : " + outputRecursive);
        System.out.println("outputRecursiveSimplified : " + outputRecursiveSimplified);
        boolean resultRecursive = CommonMethods.equalsValues(outputRecursive, expected);
        boolean resultRecursiveSimplified = CommonMethods.equalsValues(outputRecursiveSimplified, expected);
        System.out.println("resultRecursive | resultRecursiveSimplified : " + resultRecursive + " | " + resultRecursiveSimplified);

        return resultRecursiveSimplified && resultRecursive;

    }


    static class SolutionRecursive {
        public List<String> binaryTreePaths(TreeNode root) {
            List<String> binaryTreePaths = new ArrayList<>();
            if (root == null)
                return binaryTreePaths;

            binaryTreePaths(root, binaryTreePaths, "");
            return binaryTreePaths;
        }

        public void binaryTreePaths(TreeNode root, List<String> paths, String s) {
            if (root == null)
                return;

            if (s.isEmpty())
                s = Integer.toString(root.val);
            else
                s = s + "->" + root.val;

            if (root.left == null && root.right == null)
                paths.add(s);
            else {
                binaryTreePaths(root.left, paths, s);
                binaryTreePaths(root.right, paths, s);
            }

        }
    }

    static class SolutionRecursiveSimplified {
        public List<String> binaryTreePaths(TreeNode root) {
            List<String> binaryTreePaths = new ArrayList<>();
            if (root == null)
                return binaryTreePaths;

            binaryTreePaths(root, binaryTreePaths, "");
            return binaryTreePaths;
        }

        public void binaryTreePaths(TreeNode root, List<String> paths, String s) {
            if (root == null)
                return;

            s += root.val;
            if (root.left == null && root.right == null)
                paths.add(s);
            else {
                s += "->";
                binaryTreePaths(root.left, paths, s);
                binaryTreePaths(root.right, paths, s);
            }

        }
    }
}
