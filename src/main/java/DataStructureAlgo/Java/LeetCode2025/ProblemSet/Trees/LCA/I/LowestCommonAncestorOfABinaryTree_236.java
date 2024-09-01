package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees.LCA.I;

import DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees.BinaryTreePathToANode;
import DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees.BinaryTreePaths_257;
import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.TreeBuilder;
import DataStructureAlgo.Java.helpers.templates.TreeNode;
import DataStructureAlgo.Java.nonleetcode.Tree.traversal.TreeTraversalRecursive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * Author: Nitin Gupta
 * Date:16/08/24
 * Question Category: 236. Lowest Common Ancestor of a Binary Tree
 * Description: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/
 * <p>
 * <p>
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * <p>
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
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
 * Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
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
 * p and q will exist in the tree.
 * <p>
 * <p>
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.LowestCommonAncestor}
 * Similar {@link}
 * extension  {@link BinaryTreePaths_257} {@link BinaryTreePathToANode}
 * <p>
 * Tags
 * -----
 * @medium
 * @Tree
 * @Depth-FirstSearch
 * @BinaryTree
 *
 *
 * Company Tags
 * -----
 * @Facebook
 * @Amazon
 * @LinkedIn
 * @Microsoft
 * @Google
 *
 *
 * @Editorial <a href="https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/solutions/5644286/3-solution-improve-thinking-skills-beats-100">...</a>
 */

public class  LowestCommonAncestorOfABinaryTree_236 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4}, 5, 4, 5);
        test &= test(new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4}, 5, 1, 3);
        test &= test(new Integer[]{1, 2}, 1, 2, 1);
        System.out.println(test ? "\nAll passed" : "\nSomething Failed");
    }

    private static boolean test(Integer[] tree, Integer p, Integer q, Integer expected) {
        System.out.println("-------------------------");
        System.out.println("Input " + Arrays.toString(tree) + " P : " + p + " Q :" + q);

        final TreeNode root = TreeBuilder.buildTreeFromLevelOrder(tree);
        final TreeNode pNode = CommonMethods.searchNodeByValue(root, p);
        final TreeNode qNode = CommonMethods.searchNodeByValue(root, q);
        final TreeNode expectedNode = CommonMethods.searchNodeByValue(root, expected);
        System.out.println("Tree {pre-order} : " + TreeTraversalRecursive.preOrder(root) + " p : " + pNode + " q: " + qNode + " expected :" + expectedNode);

        SolutionRecursive solutionRecursive = new SolutionRecursive();
        TreeNode resultTreeRecursive = solutionRecursive.lowestCommonAncestor(root, pNode, qNode);
        boolean testResultRecursive = resultTreeRecursive == expectedNode;
        System.out.println("resultTreeRecursive : " + resultTreeRecursive + " result : " + testResultRecursive);

        SolutionUsingParentsMap solutionIterative = new SolutionUsingParentsMap();
        TreeNode resultTreeIterative = solutionIterative.lowestCommonAncestor(root, pNode, qNode);
        boolean testResultIterative = resultTreeIterative == expectedNode;
        System.out.println("resultTreeIterative : " + resultTreeIterative + " result : " + testResultIterative);


        SolutionUsingPath iterativeUsingPath = new SolutionUsingPath();
        TreeNode resultTreeUsingPath = iterativeUsingPath.lowestCommonAncestor(root, pNode, qNode);
        boolean testResultUsingPath = resultTreeUsingPath == expectedNode;
        System.out.println("resultTreeUsingPath : " + resultTreeUsingPath + " result : " + testResultUsingPath);

        return testResultRecursive && testResultIterative && testResultUsingPath;

    }


    /**
     * T/S: O(n)/O(n)
     * Algo:
     * 1. Go down the tree recursively
     * 2. if the current root is p or q, that means before we find both the nodes, we found one of them is root itself.
     * This will be our LCA, as we are going down to tree, if either of them is root of either of them, then it will be LCA only.
     * 3. Go left and right
     * 4. Check did we find both p and q at either left or right side. if so root is the LCA (p on left, q on right or vice versa)
     * 5. check did we find either of them, if so, return that root
     */
    static class SolutionRecursive {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null)
                return null;

            if (root == p || root == q)
                return root;

            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);

            if (left != null && right != null)
                return root;

            return left == null ? right : left;
        }
    }

    /**
     * T/S : O(2n)/O(n)
     * This iterative version can be done using any of the traversal, here we are using pre-order traversal.
     * Algo:
     * We need to store all the root of p and Q in the path, so that we can find the LCA. LCA would be the first node common in both the set moving up-words.
     * <p>
     * 1. Go down to tree in pre-order fashion
     * 2. if we find either of them, p or q, then store its parent
     * 3. once we found both of them, we'll skip the loop and find the first common
     * <p>
     * How do we store the parents, we'll store all the parents for each node.
     */
    static class SolutionUsingParentsMap {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null)
                return null;

            if (root == p || root == q)
                return root;

            Stack<TreeNode> stack = new Stack<>();
            Map<TreeNode, TreeNode> childVsParent = new HashMap<>();

            stack.push(root);
            childVsParent.put(root, null);


            /**
             * we don't need to travers the entire tree, we just need to traverse till we find both the node.
             */
            while (!stack.isEmpty() && !(childVsParent.containsKey(p) && childVsParent.containsKey(q))) {

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

            //Now we have all the parents of p and q, we need to find the first common parent
            final Set<TreeNode> setOfParentOfP = new HashSet<>();
            TreeNode lca = p;
            while (lca != null) {
                setOfParentOfP.add(lca);
                lca = childVsParent.get(lca);
            }

            lca = q;
            while (!setOfParentOfP.contains(lca)) {
                lca = childVsParent.get(lca);
            }

            return lca;
        }
    }

    /**
     * T/S : O(3n)/O(n)
     * This is yet another interesting approach. Which is inspired by {@link BinaryTreePaths_257}
     * Algo:
     * We can find the path of p and q from root to p and q. Post that, the first common node b/w path (from backword) is our lca
     * 1. Get the path of p from root.
     * 2. Get the path of q from root.
     * 3. Find the first common node from the front in both the path.
     *
     *  {@link BinaryTreePathToANode}
     */
    static class SolutionUsingPath {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null)
                return null;

            if (root == p || root == q)
                return root;

            final List<TreeNode> pathP = new ArrayList<>();
            final List<TreeNode> pathQ = new ArrayList<>();

            binaryTreePaths(root, p, pathP);
            binaryTreePaths(root, q, pathQ);

            if(pathP.isEmpty() || pathQ.isEmpty())
                return null; //either of them is not present in the tree

            TreeNode lca = null;
            int i = 0;
            int j = 0;

            //Traverse till we hit same parent, these are all the CA (common ancestor), the first node, which is not same, just previous to that is LCA
            while (i<pathP.size() && j<pathQ.size() && pathP.get(i) == pathQ.get(j)){
                i++; j++;
            }


            return pathP.get(i-1);

        }

        /**
         * {@link BinaryTreePathToANode}
         * @param root
         * @param node
         * @param path
         * @return
         */
        private boolean binaryTreePaths(TreeNode root, TreeNode node, List<TreeNode> path) {
            if(root == null)
                return false;

            //add all the node to paht, as they could be participant
            path.add(root);

            //if we hit the node, then we need to discontinue from here
            if( root == node)
                return true;

            //go left and right, if we found node on either side, then we need to stop
            if( (binaryTreePaths(root.left, node, path) || binaryTreePaths(root.right, node, path)))
                return true;

            //if we did not find the node, then current root is not eligible for path.
            path.remove(path.size()-1);
            return false;
        }
    }
}
