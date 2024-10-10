package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees;

import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.TreeBuilder;
import DataStructureAlgo.Java.helpers.templates.TreeNodeWithRandom;
import DataStructureAlgo.Java.nonleetcode.Tree.traversal.TreeTraversalRecursive;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Nitin Gupta
 * Date: 9/16/2024
 * Question Category: [easy | medium | hard ]
 * Description:
 * https://leetcode.com/problems/clone-binary-tree-with-random-pointer/description/
 * https://leetcode.ca/2019-12-24-1485-Clone-Binary-Tree-With-Random-Pointer/#google_vignette
 * A binary tree is given such that each node contains an additional random pointer which could point to any node in the tree or null.
 * <p>
 * Return a deep copy of the tree.
 * <p>
 * The tree is represented in the same input/output way as normal binary trees where each node is represented as a pair of [val, random_index] where:
 * <p>
 * val: an integer representing Node.val
 * random_index: the index of the node (in the input) where the random pointer points to, or null if it does not point to any node.
 * You will be given the tree in class Node and you should return the cloned tree in class NodeCopy. NodeCopy class is just a clone of Node class with the same attributes and constructors.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [[1,null],null,[4,3],[7,0]]
 * Output: [[1,null],null,[4,3],[7,0]]
 * Explanation: The original binary tree is [1,null,4,7].
 * The random pointer of node one is null, so it is represented as [1, null].
 * The random pointer of node 4 is node 7, so it is represented as [4, 3] where 3 is the index of node 7 in the array representing the tree.
 * The random pointer of node 7 is node 1, so it is represented as [7, 0] where 0 is the index of node 1 in the array representing the tree.
 * Example 2:
 * <p>
 * <p>
 * Input: root = [[1,4],null,[1,0],null,[1,5],[1,5]]
 * Output: [[1,4],null,[1,0],null,[1,5],[1,5]]
 * Explanation: The random pointer of a node can be the node itself.
 * Example 3:
 * <p>
 * <p>
 * Input: root = [[1,6],[2,5],[3,4],[4,3],[5,2],[6,1],[7,0]]
 * Output: [[1,6],[2,5],[3,4],[4,3],[5,2],[6,1],[7,0]]
 * Example 4:
 * <p>
 * Input: root = []
 * Output: []
 * Example 5:
 * <p>
 * Input: root = [[1,null],null,[2,null],null,[1,null]]
 * Output: [[1,null],null,[2,null],null,[1,null]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 1000].
 * Each node's value is between [1, 10^6].
 * <p>
 * <p>
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.nonleetcode.Tree.CloneTreeRandomPointer}
 * Similar {@link}
 * extension {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @LeetCodeLockedProblem
 * @PremimumQuestion <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @Editorial
 */
public class CloneBinaryTreeWithRandomPointer_1485 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(
                List.of(new Integer[]{1, 2, 3, 4}, new Integer[]{2, 4, 6, 3}, new Integer[]{4, null, null, 2}, new Integer[]{6, null, null, null}, new Integer[]{3, null, 5, null}, new Integer[]{5, null, null, 1}));

        test &= test(
                List.of(new Integer[]{1, 2, 3, null}, new Integer[]{2, 4, 6, null}, new Integer[]{4, null, null, null}, new Integer[]{6, null, null, null}, new Integer[]{3, null, 5, null}, new Integer[]{5, null, null, null}));

        test &= test(
                List.of(new Integer[]{1, 2, 3, 1}, new Integer[]{2, 4, 6, 2}, new Integer[]{4, null, null, 4}, new Integer[]{6, null, null, 6}, new Integer[]{3, null, 5, 3}, new Integer[]{5, null, null, 5}));

        CommonMethods.printResult(test);
    }

    private static boolean test(List<Integer[]> inputTree) {
        System.out.println("-------------------------------------------");
        TreeNodeWithRandom root = TreeBuilder.createBinaryTreeWithRandom(inputTree);

        List<Integer> levelOrderTraversal = TreeTraversalRecursive.levelOrder(root);
        System.out.println("Original Tree Level Order Traversal: " + levelOrderTraversal);

        SolutionUsingMap solutionUsingMap = new SolutionUsingMap();
        TreeNodeWithRandom clone = solutionUsingMap.copyRandomBinaryTree(root);
        List<Integer> levelOrderTraversalUsingMap = TreeTraversalRecursive.levelOrder(clone);
        boolean usingMapIsSame = TreeBuilder.isSameTreeWithRandom(root, clone);
        System.out.println("Clone Tree Level Order Traversal:    " + levelOrderTraversalUsingMap + " Result : " + (usingMapIsSame ? "Pass" : "Failed"));


        SolutionUsingLeftRightShuffle solutionUsingLeftRightShuffle = new SolutionUsingLeftRightShuffle();
        TreeNodeWithRandom clone2 = solutionUsingLeftRightShuffle.copyRandomBinaryTree(root);
        List<Integer> levelOrderTraversalUsingShuffle = TreeTraversalRecursive.levelOrder(clone2);
        boolean usingLeftRightShuffleIsSame = TreeBuilder.isSameTreeWithRandom(root, clone2);
        System.out.println("Clone Tree Level Order Traversal:    " + levelOrderTraversalUsingShuffle + " Result : " + (usingLeftRightShuffleIsSame ? "Pass" : "Failed"));



        return usingMapIsSame && usingLeftRightShuffleIsSame;


    }


    /**
     * Create a map of the original node vs. clone node. Do the same for left, right and random node.
     * if a node already exists in a map, return.
     */
    static class SolutionUsingMap {
        Map<TreeNodeWithRandom, TreeNodeWithRandom> originalToCLone = new HashMap<>();

        public TreeNodeWithRandom copyRandomBinaryTree(TreeNodeWithRandom root) {
            if (root == null)
                return null;

            if (originalToCLone.containsKey(root))
                return originalToCLone.get(root);

            TreeNodeWithRandom clone = new TreeNodeWithRandom(root.val);
            originalToCLone.put(root, clone);

            clone.left = copyRandomBinaryTree((TreeNodeWithRandom) root.left);
            clone.right = copyRandomBinaryTree((TreeNodeWithRandom) root.right);
            clone.random = copyRandomBinaryTree((TreeNodeWithRandom) root.random);

            return clone;
        }
    }

    /**
     * {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.List.CopyListRandomPointer3PhaseSol2}
     */
    static class SolutionUsingLeftRightShuffle {
        public TreeNodeWithRandom copyRandomBinaryTree(TreeNodeWithRandom root) {
            if (root == null)
                return null;

            TreeNodeWithRandom clone = cloneLeftRight(root);
            cloneRandom(root);
            restoreTree(root);
            return clone;
        }

        /**
         * Create a Clone node and insert between original root and its left subtree.
         * However, create a right subtree of clone as we go
         *
         * @param root
         * @return TreeNodeWithRandom
         */
        private TreeNodeWithRandom cloneLeftRight(TreeNodeWithRandom root) {
            if (root == null)
                return null;

            //build a clone node
            TreeNodeWithRandom clone = new TreeNodeWithRandom(root.val);

            //cache left subTree of an original tree
            TreeNodeWithRandom originalLeft = (TreeNodeWithRandom) root.left;

            //insert clone between original root and its left subtree

            //1. set clone to original tree node left
            root.left = clone;

            //2. set original left to a clone left
            clone.left = originalLeft;

            //do the same for all left subTree
            if (originalLeft != null)
                originalLeft.left = cloneLeftRight(originalLeft);

            //clone right subtree as it is
            clone.right = cloneLeftRight((TreeNodeWithRandom) root.right);

            //return clone
            return clone;

        }


        /**
         * Attach random of original to clone.
         * The random node, if it exists, will have a left node, which is a clone of it.
         *
         * @param root
         */
        private void cloneRandom(TreeNodeWithRandom root) {
            if (root == null)
                return;

            //get clone node
            TreeNodeWithRandom clone = (TreeNodeWithRandom) root.left;

            // if random exists
            if (root.random != null && clone != null) {
                clone.random = root.random.left;
            }

            //do for left and right subtree
            if(root.left!=null)
                cloneRandom((TreeNodeWithRandom) root.left.left);
            cloneRandom((TreeNodeWithRandom) root.right);
        }

        /**
         * Restore the original tree. We need to detach only clone node from left subtree
         */
        private void restoreTree(TreeNodeWithRandom root) {
            if (root == null)
                return;

            //if root.left exists, that must be the clone node, detach it
            if (root.left != null) {
                //restore original tree left
                TreeNodeWithRandom clone = (TreeNodeWithRandom) root.left;
                TreeNodeWithRandom originalLeft = (TreeNodeWithRandom) clone.left;
                TreeNodeWithRandom cloneLeft = originalLeft == null ? null : (TreeNodeWithRandom) originalLeft.left;

                root.left = originalLeft;
                clone.left = cloneLeft;
            }
            restoreTree((TreeNodeWithRandom) root.left);
            restoreTree((TreeNodeWithRandom) root.right);
        }

    }

}
