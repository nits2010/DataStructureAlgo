package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees.LCA.III_1650;

import DataStructureAlgo.Java.LeetCode2025.ProblemSet.List._160.IntersectionOfTwoLinkedLists_160;
import DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees.LCA.I_236.LowestCommonAncestorOfABinaryTree_236;
import DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees.LCA.II_1644.LowestCommonAncestorOfABinaryTreeII_1644;
import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.TreeBuilder;
import DataStructureAlgo.Java.helpers.templates.TreeNode;
import DataStructureAlgo.Java.helpers.templates.TreeNodeWithParent;
import DataStructureAlgo.Java.nonleetcode.Tree.traversal.TreeTraversalRecursive;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Author: Nitin Gupta
 * Date:16/08/24
 * Question Category: 1650. Lowest Common Ancestor of a Binary Tree III
 * Description: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iii/description/
 * https://leetcode.ca/all/1650.html
 * <p>
 * Given two nodes of a binary tree p and q, return their lowest common ancestor (LCA).
 * <p>
 * Each node will have a reference to its parent node. The definition for Node is below:
 * <p>
 * class Node {
 * public int val;
 * public Node left;
 * public Node right;
 * public Node parent;
 * }
 * According to the definition of LCA on Wikipedia: "The lowest common ancestor of two nodes p and q in a tree T is the lowest node that has both p and q as descendants (where we allow a node to be a descendant of itself)."
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 * Example 2:
 * <p>
 * <p>
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5 since a node can be a descendant of itself according to the LCA definition.
 * Example 3:
 * <p>
 * Input: root = [1,2], p = 1, q = 2
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [2, 105].
 * -109 <= Node.val <= 109
 * All Node.val are unique.
 * p != q
 * p and q exist in the tree.
 * <p>
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link LowestCommonAncestorOfABinaryTree_236} {@link LowestCommonAncestorOfABinaryTreeII_1644}
 * <p>
 * Tags
 * -----
 * @medium
 * @Tree
 * @Depth-FirstSearch
 * @BinaryTree
 * @PremimumQuestion
 * @LeetCodeLockedProblem
 *
 *
 * Company Tags
 * -----
 * @Facebook
 * @LinkedIn
 * @Microsoft
 *
 *
 * @Editorial <a href="https://leetcode.ca/2020-06-06-1650-Lowest-Common-Ancestor-of-a-Binary-Tree-III">...</a>
 */

public class LowestCommonAncestorOfABinaryTreeIII_1650 {


    public static void main(String[] args) {
        boolean test = true;
        test &= test(new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4}, 5, 10, null);
        test &= test(new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4}, 5, 4, 5);
        test &= test(new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4}, 5, 1, 3);
        test &= test(new Integer[]{1, 2}, 1, 2, 1);

        System.out.println("========================================");
        System.out.println("========================================");

        System.out.println((test ? "All passed" : "Something Failed"));
    }

    private static boolean test(Integer[] tree, int p, int q, Integer expected) {
        System.out.println("========================================");
        System.out.println("Input " + Arrays.toString(tree) + " P : " + p + " Q :" + q);

        final TreeNodeWithParent root = TreeBuilder.buildTreeFromLevelOrderWithParent(tree);
        final TreeNodeWithParent pNode = (TreeNodeWithParent) CommonMethods.searchNodeByValue(root, p);
        final TreeNodeWithParent qNode = (TreeNodeWithParent) CommonMethods.searchNodeByValue(root, q);
        final TreeNodeWithParent expectedNode = (TreeNodeWithParent) CommonMethods.searchNodeByValue(root, expected);

        System.out.println("Tree {pre-order} : " + TreeTraversalRecursive.preOrder(root) + " p : " + pNode + " q: " + qNode + " expected :" + expectedNode);

        SolutionUsingSet solutionUsingSet = new SolutionUsingSet();
        TreeNode lcaUsingSet = solutionUsingSet.lowestCommonAncestor(pNode, qNode);
        boolean testResultUsingSet = lcaUsingSet == expectedNode;
        System.out.println("Using Set : " + lcaUsingSet + " expected :" + expectedNode + " Test Passed :" + (testResultUsingSet ? "Passed" : "Failed"));


        SolutionTwoPointer solutionUsingTwoPointer = new SolutionTwoPointer();
        TreeNode lcaUsingTwoPointer = solutionUsingTwoPointer.lowestCommonAncestor(pNode, qNode);
        boolean testResultUsingTwoPointer = lcaUsingTwoPointer == expectedNode;
        System.out.println("Using Two Pointer : " + lcaUsingTwoPointer + " expected :" + expectedNode + " Test Passed :" + (testResultUsingTwoPointer ? "Passed" : "Failed"));


        SolutionTwoPointerImproved solutionUsingTwoPointerImroved = new SolutionTwoPointerImproved();
        TreeNode lcaUsingTwoPointerImproved = solutionUsingTwoPointerImroved.lowestCommonAncestor(pNode, qNode);
        boolean testResultUsingTwoPointerImproved = lcaUsingTwoPointerImproved == expectedNode;
        System.out.println("Using Two Pointer Improved : " + lcaUsingTwoPointerImproved + " expected : " + expectedNode + " Test Passed : " + (testResultUsingTwoPointerImproved ? "Passed" : "Failed"));

        boolean finalTestResult = testResultUsingSet && testResultUsingTwoPointer && testResultUsingTwoPointerImproved;
        System.out.println("\nFinal test result " + (finalTestResult ? "Passed" : "Failed"));
        return finalTestResult;

    }


    /**
     * Algo: inspired by this {@link LowestCommonAncestorOfABinaryTree_236.SolutionIterative}
     * This is just extension of that, in the reference problem, we had to find all the parent of the node p and q by traversing tree from top to bottom.
     * However, since with parentNode, we can traverse bottom up.
     * <p>
     * 1. Create a set of node for node p by going upwards till root
     * 2. traverse the other node, and check for parent match.
     * <p>
     * T/S: O(n)/O(n) for set { worst case skewed tree }
     */
    static class SolutionUsingSet {

        /**
         * O(2n)/O(n)
         */
        public TreeNode lowestCommonAncestor(TreeNodeWithParent p, TreeNodeWithParent q) {

            if (p == null || q == null)
                return null;

            if (p == q)
                return p; // if both are same, then return that node as LCA

            TreeNodeWithParent temp = p;
            Set<TreeNodeWithParent> set = new HashSet<>();

            while (temp != null) {
                set.add(temp);
                temp = (TreeNodeWithParent) temp.parent;
            }

            TreeNodeWithParent lca = q;
            while (lca != null && !set.contains(lca)) {
                lca = (TreeNodeWithParent) lca.parent;
            }

            return lca;

        }
    }

    /**
     * Since we have direct pointer to both node and that has parent node, we can  traverse both the pointer up.
     * However, we need to make sure they don't crosse each other, otherwise lca will be lost.
     * <p>
     * This leads to the same problem we solved aka intersection of linked list. {@link  IntersectionOfTwoLinkedLists_160}
     * Approach IntersectionOfTwoLinkedLists
     * 1. Get the length wrt to first head and second head.
     * 2. Get the difference.
     * 3. The bigger list will move ahead by difference
     * 4. Both will run parellely till meet.
     * <p>
     * Approach for Tree
     * 1. Get the depth of both the node wrt root ( aka length )
     * 2. Get the difference of depth
     * 3. The bigger depth node will run up by difference
     * 4. both traverse same till meet.
     * <p>
     * <p>
     * T/S: O(2n)/O(n)
     */
    static class SolutionTwoPointer {

        /**
         * O(2n)/O(n)
         */
        public TreeNode lowestCommonAncestor(TreeNodeWithParent p, TreeNodeWithParent q) {

            if (p == null || q == null)
                return null;

            if (p == q)
                return p; // if both are same, then return that node as LCA

            final int []depths = depthImproved(p, q);
            final int depthP = depths[0];
            final int depthQ = depths[1];

            int depthDiff = Math.abs(depthP - depthQ);

            if (depthP > depthQ) {
                while (depthDiff > 0) {
                    p = (TreeNodeWithParent) p.parent;
                    depthDiff--;
                }
            } else {
                while (depthDiff > 0) {
                    q = (TreeNodeWithParent) q.parent;
                    depthDiff--;
                }
            }

            //now both are at same depth
            while (p != q) {
                p = (TreeNodeWithParent) p.parent;
                q = (TreeNodeWithParent) q.parent;
            }
            return p;


        }

        private int depth(TreeNodeWithParent node) {
            int depth = 0;
            while (node != null) {
                depth++;
                node = (TreeNodeWithParent) node.parent;
            }
            return depth;
        }

        private int[] depthImproved(TreeNodeWithParent p, TreeNodeWithParent q) {
            int depthP = 0;
            int depthQ = 0;

            while (p != null || q != null) {
                if (p != null) {
                    depthP++;
                    p = (TreeNodeWithParent) p.parent;
                }
                if (q != null) {
                    depthQ++;
                    q = (TreeNodeWithParent) q.parent;
                }
            }

            return new int[]{depthP, depthQ};


        }
    }

    /**
     * This is same as {@link SolutionTwoPointer} but with improved code.
     * In above code, we first get the depth of both the node and then traverse the node to get the depth.
     * if you notice, that post finding the depthdiff, we are moving the bigger depth node up so that both node reaches the same depth wrt root.
     *
     * Which is same as the fact that, we move both the node up at the same time, the smaller depth node will reach its parent first then the other.
     * and if we reset the smaller depth node to its original position then the other node would have travelled the depthDiff by then.
     * Which makes both the node at the same position.
     *
     * Algo;
     * 1. Start both the node from its original position till they meet.
     * 2. if a node reaches root first (i.e. no parent) then reset that node to its original position
     * 3. continue the above.
     *
     * T/S: O(n)/O(n)
     *
     */
    static class SolutionTwoPointerImproved {

        /**
         * O(2n)/O(n)
         */
        public TreeNode lowestCommonAncestor(TreeNodeWithParent p, TreeNodeWithParent q) {

            if (p == null || q == null)
                return null;

            if (p == q)
                return p; // if both are same, then return that node as LCA


            //now both are at same depth
            TreeNodeWithParent pTemp = p, qTemp = q;

            while (pTemp != qTemp) {

                pTemp = pTemp.parent == null   //if p reaches its parent first, i.e. p is on shorter depth, reset it to its original position
                        ? p
                        : (TreeNodeWithParent) pTemp.parent;

                qTemp = qTemp.parent == null //if q reaches its parent first, i.e. q is on shorter depth, reset it to its original position
                        ? q
                        : (TreeNodeWithParent) qTemp.parent;

            }
            return pTemp;


        }


    }
}
