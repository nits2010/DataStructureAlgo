package DataStructureAlgo.Java.LeetCode2025.medium.Tree.LCA;

import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.TreeBuilder;
import DataStructureAlgo.Java.helpers.templates.TreeNode;
import DataStructureAlgo.Java.nonleetcode.Tree.traversal.TreeTraversalRecursive;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date:16/08/24
 * Question Category: 1644. Lowest Common Ancestor of a Binary Tree II
 * Description: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-ii/description/
 * Given the root of a binary tree, return the lowest common ancestor (LCA) of two given nodes, p and q. If either node p or q does not exist in the tree, return null. All values of the nodes in the tree are unique.
 * <p>
 * According to the definition of LCA on Wikipedia: "The lowest common ancestor of two nodes p and q in a binary tree T is the lowest node that has both p and q as descendants (where we allow a node to be a descendant of itself)". A descendant of a node x is a node y that is on the path from node x to some leaf node.
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
 * <p>
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5. A node can be a descendant of itself according to the definition of LCA.
 * Example 3:
 * <p>
 * <p>
 * <p>
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 10
 * Output: null
 * Explanation: Node 10 does not exist in the tree, so return null.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [1, 104].
 * -109 <= Node.val <= 109
 * All Node.val are unique.
 * p != q
 * <p>
 * <p>
 * Follow up: Can you find the LCA traversing the tree, without checking nodes existence?
 * <p>
 * <p>
 * File reference
 * -----------
 * Duplicate {@link }
 * Similar {@link}
 * extension  {@link LowestCommonAncestorOfABinaryTree_236}
 * <p>
 * <p>
 * Tags
 * -----
 *
 * @medium
 * @Tree
 * @Depth-FirstSearch
 * @BinaryTree
 * @PremimumQuestion
 * @LeetCodeLockedProblem
 *
 * Company Tags
 * -----
 * @LinkedIn
 * @Microsoft
 * @Editorial https://leetcode.ca/2020-05-31-1644-Lowest-Common-Ancestor-of-a-Binary-Tree-II/
 */

public class LowestCommonAncestorOfABinaryTreeII_1644 {

    public static void main(String[] args) {

        System.out.println("\n========================================================");
        System.out.println("\n============ Version 2 Testing ================");
        System.out.println("\n========================================================");
        boolean version2 = Version2.test();

        System.out.println("\n========================================================");

        System.out.println(" version2 -> " + (version2 ? "All passed" : "Something Failed"));

        System.out.println("\n========================================================");
    }

    //Refer this version for easy code
    static class Version2 {
        public static boolean test() {
            System.out.println("-----------testNullNode--------------");
            boolean testNullNode = true;
            testNullNode &= testNullNode(new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4}, 5, 10, null);
            testNullNode &= testNullNode(new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4}, 5, 4, 5);
            testNullNode &= testNullNode(new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4}, 5, 1, 3);
            testNullNode &= testNullNode(new Integer[]{1, 2}, 1, 2, 1);
            System.out.println(" testNullNode -> " + (testNullNode ? "All passed" : "Something Failed"));


            System.out.println("----------testValidNonExistenceNode---------------");
            boolean testValidNonExistenceNode = true;
            testValidNonExistenceNode &= testValidNonExistenceNode(new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4}, 5, 10, null);
            testValidNonExistenceNode &= testValidNonExistenceNode(new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4}, 5, 14, null);
            testValidNonExistenceNode &= testValidNonExistenceNode(new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4}, 5, 13, null);
            testValidNonExistenceNode &= testValidNonExistenceNode(new Integer[]{1, 2}, 1, 2, null);
            System.out.println(" testValidNonExistenceNode -> " + (testValidNonExistenceNode ? "All passed" : "Something Failed"));

            return testValidNonExistenceNode && testNullNode;
        }

        private static boolean testValidNonExistenceNode(Integer[] tree, Integer p, Integer q, Integer expected) {

            System.out.println("Input " + Arrays.toString(tree) + " P : " + p + " Q :" + q);

            final TreeNode root = TreeBuilder.buildTreeFromLevelOrder(tree);
            final TreeNode pNode = CommonMethods.searchNodeByValue(root, p);
            final TreeNode qNode = CommonMethods.searchNodeByValue(root, q);
            final TreeNode expectedNode = CommonMethods.searchNodeByValue(root, expected);
            TreeNode tempQNode = new TreeNode(q);
            System.out.println("Tree {pre-order} : " + TreeTraversalRecursive.preOrder(root) + " p : " + pNode + " q: " + qNode + " expected :" + expectedNode);

            LowestCommonAncestorOfABinaryTreeII_1644_Version2.SolutionRecursiveByNodeExistence solutionRecursive = new LowestCommonAncestorOfABinaryTreeII_1644_Version2.SolutionRecursiveByNodeExistence();

            TreeNode resultTreeRecursiveValidNonExistenceNode = solutionRecursive.lowestCommonAncestor(root, pNode, tempQNode);
            boolean testResultRecursiveValidNonExistenceNode = resultTreeRecursiveValidNonExistenceNode == expectedNode;
            System.out.println("\nresultTreeRecursiveValidNonExistenceNode : " + resultTreeRecursiveValidNonExistenceNode + " testResultRecursiveValidNonExistenceNode : " + testResultRecursiveValidNonExistenceNode);

            LowestCommonAncestorOfABinaryTreeII_1644_Version2.SolutionRecursiveWithoutNodeExistence solutionRecursiveWithoutNodeExistence = new LowestCommonAncestorOfABinaryTreeII_1644_Version2.SolutionRecursiveWithoutNodeExistence();
            TreeNode resultRecursiveWithoutNodeExistence = solutionRecursiveWithoutNodeExistence.lowestCommonAncestor(root, pNode, tempQNode);
            boolean testResultRecursiveWithoutNodeExistence = resultRecursiveWithoutNodeExistence == expectedNode;
            System.out.println("\nresultRecursiveWithoutNodeExistence : " + resultRecursiveWithoutNodeExistence + " testResultRecursiveWithoutNodeExistence : " + testResultRecursiveWithoutNodeExistence);

            boolean result = testResultRecursiveValidNonExistenceNode && testResultRecursiveWithoutNodeExistence;
            System.out.println("\n Recursive ------- " + (result ? "Pass" : "Fail"));

            System.out.println("-------------------------");
            return result;
        }

        private static boolean testNullNode(Integer[] tree, Integer p, Integer q, Integer expected) {

            System.out.println("Input " + Arrays.toString(tree) + " P : " + p + " Q :" + q);

            final TreeNode root = TreeBuilder.buildTreeFromLevelOrder(tree);
            final TreeNode pNode = CommonMethods.searchNodeByValue(root, p);
            final TreeNode qNode = CommonMethods.searchNodeByValue(root, q);
            final TreeNode expectedNode = CommonMethods.searchNodeByValue(root, expected);

            System.out.println("Tree {pre-order} : " + TreeTraversalRecursive.preOrder(root) + " p : " + pNode + " q: " + qNode + " expected :" + expectedNode);

            LowestCommonAncestorOfABinaryTreeII_1644_Version2.SolutionRecursiveByNodeExistence solutionRecursive = new LowestCommonAncestorOfABinaryTreeII_1644_Version2.SolutionRecursiveByNodeExistence();


            TreeNode resultTreeRecursive = solutionRecursive.lowestCommonAncestor(root, pNode, qNode);
            boolean testResultRecursive = resultTreeRecursive == expectedNode ;
            System.out.println("\nresultTreeRecursive : " + resultTreeRecursive + " testResultRecursive : " + testResultRecursive);

            LowestCommonAncestorOfABinaryTreeII_1644_Version2.SolutionRecursiveWithoutNodeExistence solutionRecursiveWithoutNodeExistence = new LowestCommonAncestorOfABinaryTreeII_1644_Version2.SolutionRecursiveWithoutNodeExistence();
            TreeNode resultRecursiveWithoutNodeExistence = solutionRecursiveWithoutNodeExistence.lowestCommonAncestor(root, pNode, qNode);
            boolean testResultRecursiveWithoutNodeExistence = resultRecursiveWithoutNodeExistence == expectedNode;
            System.out.println("\nresultRecursiveWithoutNodeExistence : " + resultRecursiveWithoutNodeExistence + " testResultRecursiveWithoutNodeExistence : " + testResultRecursiveWithoutNodeExistence);

            boolean result = testResultRecursive && testResultRecursiveWithoutNodeExistence;
            System.out.println("\n Recursive ------- " + (result ? "Pass" : "Fail"));

            System.out.println("-------------------------");
            return result;

        }
    }

}

/**
 * https://leetcode.ca/2020-05-31-1644-Lowest-Common-Ancestor-of-a-Binary-Tree-II/
 */
class LowestCommonAncestorOfABinaryTreeII_1644_Version2 {
    static class SolutionRecursiveByNodeExistence {

        /**
         * O(2n)/O(n)
         */
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null)
                return null;

            //checking nodes existence
            if (searchNodeExistence(root, p) == null ||
                    searchNodeExistence(root, q) == null)
                return null;

            return lowestCommonAncestorDFS(root, p, q);

        }

        public TreeNode searchNodeExistence(TreeNode root, TreeNode value) {
            if (root == null || value == null)
                return null;

            if (root == value)
                return root;

            TreeNode left = searchNodeExistence(root.left, value);
            TreeNode right = searchNodeExistence(root.right, value);

            return left == null ? right : left;

        }

        public TreeNode lowestCommonAncestorDFS(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null)
                return null;

            if (root == p || root == q)
                return root;

            TreeNode left = lowestCommonAncestorDFS(root.left, p, q);
            TreeNode right = lowestCommonAncestorDFS(root.right, p, q);

            if (left != null && right != null)
                return root;

            return left == null ? right : left;
        }

    }

    /**
     * O(n)/O(n)
     */
    static class SolutionRecursiveWithoutNodeExistence {

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            int[] count = {0};

            TreeNode lca = lowestCommonAncestorDFS(root, p, q, count);

            if (count[0] != 2)
                return null;

            return lca;
        }

        private TreeNode lowestCommonAncestorDFS(TreeNode root, TreeNode p, TreeNode q, int[] count) {
            if (root == null)
                return null;



            TreeNode left = lowestCommonAncestorDFS(root.left, p, q,count);
            TreeNode right = lowestCommonAncestorDFS(root.right, p, q,count);

            //This has to be in postorder to touch all nodes.
            if (root == p || root == q) {
                count[0]++;
                return root;
            }

            if (left != null && right != null)
                return root;

            return left == null ? right : left;
        }
    }
}