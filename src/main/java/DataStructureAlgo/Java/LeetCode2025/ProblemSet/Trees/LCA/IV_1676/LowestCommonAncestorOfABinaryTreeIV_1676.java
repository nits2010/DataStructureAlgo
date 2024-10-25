package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees.LCA.IV_1676;

import DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees.LCA.I_236.LowestCommonAncestorOfABinaryTree_236;
import DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees.LCA.II_1644.LowestCommonAncestorOfABinaryTreeII_1644;
import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.TreeBuilder;
import DataStructureAlgo.Java.helpers.templates.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Author: Nitin Gupta
 * Date:17/08/24
 * Question Category: 1676. Lowest Common Ancestor of a Binary Tree IV [medium]
 * Description: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iv/description/
 * https://leetcode.ca/all/1676.html
 * <p>
 * Given the root of a binary tree and an array of TreeNode objects nodes, return the lowest common ancestor (LCA) of all the nodes in nodes. All the nodes will exist in the tree, and all values of the tree's nodes are unique.
 * <p>
 * Extending the definition of LCA on Wikipedia: "The lowest common ancestor of n nodes p1, p2, ..., pn in a binary tree T is the lowest node that has every pi as a descendant (where we allow a node to be a descendant of itself) for every valid i". A descendant of a node x is a node y that is on the path from node x to some leaf node.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], nodes = [4,7]
 * Output: 2
 * Explanation: The lowest common ancestor of nodes 4 and 7 is node 2.
 * Example 2:
 * <p>
 * <p>
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], nodes = [1]
 * Output: 1
 * Explanation: The lowest common ancestor of a single node is the node itself.
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], nodes = [7,6,2,4]
 * Output: 5
 * Explanation: The lowest common ancestor of the nodes 7, 6, 2, and 4 is node 5.
 * Example 4:
 * <p>
 * <p>
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], nodes = [0,1,2,3,4,5,6,7,8]
 * Output: 3
 * Explanation: The lowest common ancestor of all the nodes is the root node.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [1, 104].
 * -109 <= Node.val <= 109
 * All Node.val are unique.
 * All nodes[i] will exist in the tree.
 * All nodes[i] are distinct.
 * <p>
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link LowestCommonAncestorOfABinaryTree_236}
 * {@link LowestCommonAncestorOfABinaryTreeII_1644}
 * {@link DataStructureAlgo.Java.LeetCode2025.medium.Tree.LCA.III.LowestCommonAncestorOfABinaryTreeIII_1650}
 * <p>
 * Advance Version Of {@link LowestCommonAncestorOfABinaryTree_236}
 * <p>
 * Tags
 * -----
 *
 * @medium
 * @Tree
 * @Depth-FirstSearch
 * @BinaryTree
 * @PremimumQuestion
 * @LeetCodeLockedProblem <p>
 * <p>
 * Company Tags
 * -----
 * @Amazon
 * @Editorial <a href="https://leetcode.ca/2020-07-02-1676-Lowest-Common-Ancestor-of-a-Binary-Tree-IV">...</a>
 */

public class LowestCommonAncestorOfABinaryTreeIV_1676 {


    public static void main(String[] args) {
        boolean test = true;
        test &= test(new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4}, new Integer[]{4, 7}, 2);
        test &= test(new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4}, new Integer[]{1}, 1);
        test &= test(new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4}, new Integer[]{7, 6, 2, 4}, 5);
        test &= test(new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4}, new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8}, 3);
        test &= test(new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4}, new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 18}, null);

        System.out.println("=====================");

        System.out.println((test ? "All Passed" : "Failed"));
    }

    private static boolean test(Integer[] tree, Integer[] nodeElements, Integer expected) {
        System.out.println("-----------------------");
        System.out.println("Input :" + Arrays.toString(tree) + " Nodes : " + Arrays.toString(nodeElements) + " Expected : " + expected);

        final TreeNode root = TreeBuilder.buildTreeFromLevelOrder(tree);


        TreeNode[] nodes = CommonMethods.searchNodeByValues(root, nodeElements);
        TreeNode expectedNode = CommonMethods.searchNodeByValue(root, expected);

        SolutionRecursive solutionRecursive = new SolutionRecursive();
        TreeNode lcaUsingRecursive = solutionRecursive.lowestCommonAncestor(root, nodes);
        boolean testUsingRecursive = lcaUsingRecursive == expectedNode;
        System.out.println("LCA Using Recursive App : " + lcaUsingRecursive + " Test LCA Using Recursive : " + (testUsingRecursive ? "Passed" : "Failed"));

        SolutionRecursiveNodeDoesNotExist solutionRecursiveNodeDoesNotExist = new SolutionRecursiveNodeDoesNotExist();
        TreeNode lcaUsingRecursiveNodeDoesNotExist = solutionRecursiveNodeDoesNotExist.lowestCommonAncestor(root, nodes);
        boolean testUsingRecursiveNodeDoesNotExist = lcaUsingRecursiveNodeDoesNotExist == expectedNode;
        System.out.println("LCA Using Recursive App Node does not exits : " + lcaUsingRecursiveNodeDoesNotExist + " Test LCA Using Recursive Node does not exits : " + (testUsingRecursiveNodeDoesNotExist ? "Passed" : "Failed"));


        SolutionUsingParentsMap solutionUsingParentsMap = new SolutionUsingParentsMap();
        TreeNode lcaUsingParentsMap = solutionUsingParentsMap.lowestCommonAncestor(root, nodes);
        boolean testLCAUsingParentsMap = lcaUsingParentsMap == expectedNode;
        System.out.println("LCA Using Parents Map : " + lcaUsingParentsMap + " Test LCA Using Parents Map : " + (testLCAUsingParentsMap ? "Passed" : "Failed"));


        boolean finalTestResult = testUsingRecursive && testLCAUsingParentsMap && testUsingRecursiveNodeDoesNotExist;

        System.out.println("---------");
        System.out.println("Final Test Result : " + (finalTestResult ? "Passed" : "Failed"));
        return finalTestResult;


    }


    /**
     * This is similar, instead of checking for two nodes, here we need to check for multiple nodes. That's all.
     * {@link LowestCommonAncestorOfABinaryTree_236.SolutionRecursive}
     * Instead of checking root == p || root == q and then return root.
     * we'll check does root is present in given nodes or not.
     */
    static class SolutionRecursive {

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
            if (root == null || nodes == null || nodes.length == 0)
                return null;

            Set<TreeNode> set = Arrays.stream(nodes).collect(Collectors.toSet()); // convert nodes to set for faster search
            return lowestCommonAncestor(root, set);
        }

        private TreeNode lowestCommonAncestor(TreeNode root, Set<TreeNode> nodes) {
            if (root == null)
                return null;

            if (nodes.contains(root))
                return root;

            TreeNode left = lowestCommonAncestor(root.left, nodes);
            TreeNode right = lowestCommonAncestor(root.right, nodes);
            if (left != null && right != null)
                return root;
            return left == null ? right : left;
        }
    }

    static class SolutionRecursiveNodeDoesNotExist {

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
            if (root == null || nodes == null || nodes.length == 0)
                return null;
            int[] count = {0};
            Set<TreeNode> set = Arrays.stream(nodes).collect(Collectors.toSet()); // convert nodes to set for faster search
            TreeNode lca = lowestCommonAncestor(root, set, count);

            if(count[0] !=nodes.length)
                return null;
            return lca;
        }

        private TreeNode lowestCommonAncestor(TreeNode root, Set<TreeNode> nodes, int[] count) {
            if (root == null)
                return null;


            TreeNode left = lowestCommonAncestor(root.left, nodes, count);
            TreeNode right = lowestCommonAncestor(root.right, nodes, count);

            if (nodes.contains(root)) {
                count[0]++;
                return root;
            }

            if (left != null && right != null)
                return root;
            return left == null ? right : left;
        }
    }


    /**
     * {@link LowestCommonAncestorOfABinaryTree_236.SolutionUsingParentsMap}
     * 1. Get the parents of all the nodes @nodes
     * 2. Create a set of parent of first node.
     * 3. Start searching the other node from @nodes
     * 3.1 : if the node present in set,
     * 3.1.1 if yes, then check does its parent present in final LCA Set, if yes then skip this node, otherwise add it to lca set.
     * 3.1.2 if no. then get its parent from parents map and repeat
     * <p>
     * 4. Post getting LCA set, we need to reduce it to one node. Start searching each lca node and search its parent in lca set.
     * if present, then skip and it otherwise keep going.
     */
    static class SolutionUsingParentsMap {

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {

            if (root == null || nodes == null || nodes.length == 0)
                return null;

            if (nodes.length == 1)
                return nodes[0]; // Since we are gurrented that each nodes will be in tree.


            Stack<TreeNode> stack = new Stack<>();
            Map<TreeNode, TreeNode> childVsParent = new HashMap<>();
            stack.push(root);
            childVsParent.put(root, null);

            //Pre-Order
            //Step 1: Get the parents of all the nodes @nodes
            while (!stack.isEmpty() && !(isInMap(childVsParent, nodes))) {
                TreeNode current = stack.pop();

                if (current.left != null) {
                    stack.push(current.left);
                    childVsParent.put(current.left, current);
                }

                if (current.right != null) {
                    stack.push(current.right);
                    childVsParent.put(current.right, current);
                }
            }


            //Step 2: Create a set of parent of first node.
            Set<TreeNode> setOfParent = new HashSet<>();
            TreeNode temp = nodes[0];

            while (temp != null) {
                setOfParent.add(temp);
                temp = childVsParent.get(temp);
            }

            //Step 3: if the node present in set,
            Set<TreeNode> lca = new HashSet<>();

            for (int i = 1; i < nodes.length; i++) {

                temp = nodes[i];

                while (temp != null && !setOfParent.contains(temp)) {
                    temp = childVsParent.get(temp);
                }
                lca.add(temp);
            }

            //Set reduce set
            Iterator<TreeNode> iterator = lca.iterator();
            while (iterator.hasNext()) {
                TreeNode potentialLCA = iterator.next();
                TreeNode current = potentialLCA;

                while (current != null && !lca.contains(childVsParent.get(current))) {
                    current = childVsParent.get(current);
                }

                if (current == null)
                    return potentialLCA;

            }

            return null;


        }

        private boolean isInMap(Map<TreeNode, TreeNode> childVsParent, TreeNode[] nodes) {

            for (TreeNode node : nodes) {
                if (!childVsParent.containsKey(node))
                    return false;
            }
            return true;
        }

    }

}
