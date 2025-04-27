package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees._653;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.templates.TreeNode;
import DataStructureAlgo.Java.nonleetcode.Tree.traversal.TreeTraversalRecursive;


/**
 * Author: Nitin Gupta
 * Date: 4/2/2025
 * Question Title: 653. Two Sum IV - Input is a BST
 * Link: https://leetcode.com/problems/two-sum-iv-input-is-a-bst/description/
 * Description: Given the root of a binary search tree and an integer k, return true if there exist two elements in the BST such that their sum is equal to k, or false otherwise.
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [5,3,6,2,4,null,7], k = 9
 * Output: true
 * Example 2:
 * <p>
 * <p>
 * Input: root = [5,3,6,2,4,null,7], k = 28
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [1, 104].
 * -104 <= Node.val <= 104
 * root is guaranteed to be a valid binary search tree.
 * -105 <= k <= 105
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @easy
 * @HashTable
 * @TwoPointers
 * @Tree
 * @Depth-FirstSearch
 * @Breadth-FirstSearch
 * @BinarySearchTree
 * @BinaryTree <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @Facebook
 * @Google
 * @Microsoft
 * @Samsung
 * @Snapchat <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class TwoSumIVInputIsABST_653 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new Integer[]{5, 3, 6, 2, 4, null, 7}, 9, true));
        tests.add(test(new Integer[]{5, 3, 6, 2, 4, null, 7}, 28, false));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(Integer[] nodes, int target, boolean expected) {
        //add print here
        CommonMethods.printTest(new String[]{"Nodes", "target", "Expected"}, true, nodes, target, expected);
        TreeNode root = TreeBuilder.buildTreeFromLevelOrder(nodes);
        System.out.println("Level order traversal : " + TreeTraversalRecursive.levelOrderWithNull(root));

        boolean output;
        boolean pass, finalPass = true;

        SolutionToArray solutionToArray = new SolutionToArray();
        SolutionUsingHashSet solutionUsingHashSet = new SolutionUsingHashSet();
        SolutionUsingSearch solutionUsingSearch = new SolutionUsingSearch();

        output = solutionToArray.findTarget(root, target);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Using Array", "Pass"}, false, output, (pass ? "Yes" : "No"));


        output = solutionUsingHashSet.findTarget(root, target);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Using HashSet", "Pass"}, false, output, (pass ? "Yes" : "No"));


        output = solutionUsingSearch.findTarget(root, target);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Using Search", "Pass"}, false, output, (pass ? "Yes" : "No"));


        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class SolutionUsingHashSet {
        public boolean findTarget(TreeNode root, int k) {
            if (root == null) return false;
            Set<Integer> set = new HashSet<>();

            return findTarget(root, k, set);
        }

        private boolean findTarget(TreeNode root, int k, Set<Integer> set) {
            if (root == null)
                return false;

            if (set.contains(k - root.val))
                return true;

            set.add(root.val);
            return findTarget(root.left, k, set) || findTarget(root.right, k, set);
        }
    }

    static class SolutionToArray {
        public boolean findTarget(TreeNode root, int k) {
            if (root == null) return false;
            List<Integer> list = new ArrayList<>();
            fill(root, list);

            return findTarget(root, k, list);
        }

        void fill(TreeNode root, List<Integer> list) {
            if (root == null)
                return;
            fill(root.left, list);
            list.add(root.val);
            fill(root.right, list);

        }

        private boolean findTarget(TreeNode root, int k, List<Integer> list) {
            int i = 0, j = list.size() - 1;

            while (i < j) {
                if (list.get(i) + list.get(j) == k)
                    return true;
                else if (list.get(i) + list.get(j) < k)
                    i++;
                else
                    j--;
            }
            return false;
        }
    }


    static class SolutionUsingSearch {
        public boolean findTarget(TreeNode root, int k) {
            if (root == null) return false;

            //three-way search,
            // with curent node value
            // with left node value
            // with right ndoe value

            return findTarget(root, root, k);


        }

        private boolean findTarget(TreeNode root, TreeNode curr, int k) {
            if (curr == null)
                return false;

            return search(root, curr, k - curr.val) || findTarget(root, curr.left, k) || findTarget(root, curr.right, k);
        }

        //find diff in the tree headed as root.
        private boolean search(TreeNode root, TreeNode curr, int diff) {
            if (root == null)
                return false;
            if (root != curr && root.val == diff)
                return true;

            if (diff < root.val)
                return search(root.left, curr, diff);
            else
                return search(root.right, curr, diff);
        }

    }
}
