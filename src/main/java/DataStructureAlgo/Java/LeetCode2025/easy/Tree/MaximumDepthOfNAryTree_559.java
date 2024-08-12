package DataStructureAlgo.Java.LeetCode2025.easy.Tree;


import DataStructureAlgo.Java.helpers.templates.NArrayTreeNode;
import DataStructureAlgo.Java.helpers.TreeBuilder;
import DataStructureAlgo.Java.nonleetcode.Tree.traversal.TreeTraversalRecursive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;



/**
 * Author: Nitin Gupta
 * Date: 8/12/2024
 * Question Category: 559. Maximum Depth of N-ary Tree
 * Description: https://leetcode.com/problems/maximum-depth-of-n-ary-tree/description/
 *
 * <p>
 * Given a n-ary tree, find its maximum depth.
 *
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 *
 * Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: root = [1,null,3,2,4,null,5,6]
 * Output: 3
 * Example 2:
 *
 *
 *
 * Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * Output: 5
 *
 *
 * Constraints:
 *
 * The total number of nodes is in the range [0, 104].
 * The depth of the n-ary tree is less than or equal to 1000.
 * <p>
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 * @easy
 * @Tree
 * @Depth-FirstSearch
 * @Breadth-FirstSearch
 *
 *
 * <p>
 * Company Tags
 * -----
 */
public class MaximumDepthOfNAryTree_559 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new Integer[]{1, null, 3, 2, 4, null, 5, 6}, 3) ;
        test &= test(new Integer[]{1, null, 2, 3, 4, 5, null, null, 6, 7, null, 8, null, 9, 10, null, null, 11, null, 12, null, 13, null, null, 14}, 5) ;
        System.out.println(test ? "Passed" : "Failed");
    }

    private static boolean test(Integer[] input, int expected) {
        System.out.println("----------------------------------------------------");
        System.out.println("Input: " + Arrays.toString(input) + "\n Expected: " + expected);
        NArrayTreeNode root = TreeBuilder.NaryTree.buildTreeFromLevelOrder(input);
        System.out.println(" level order traversal of n-ary tree :"+ TreeTraversalRecursive.levelOrder(root));

        SolutionUsingLevelOrder solutionUsingLevelOrder = new SolutionUsingLevelOrder();
        SolutionUsingPostorder solutionUsingPostorder = new SolutionUsingPostorder();

        int actualLevelOrder = solutionUsingLevelOrder.maxDepth(root);
        int actualPostOrder = solutionUsingPostorder.maxDepth(root);
        System.out.println("outputLevelOrder: " + actualLevelOrder);
        System.out.println("actualPostOrder: " + actualPostOrder);
        return  (actualLevelOrder == expected) && (expected == actualPostOrder);

    }


    /**
     * {@link DataStructureAlgo.Java.nonleetcode.Tree.traversal.TreeTraversalRecursive#levelOrder(NArrayTreeNode)}
     */
    static class SolutionUsingLevelOrder {

        public int maxDepth(NArrayTreeNode root) {
            if(root == null)
                return 0;
            List<List<Integer>> levelOrder = levelOrder(root);
            return levelOrder.size();
        }

        private  List<List<Integer>> levelOrder(NArrayTreeNode root) {
            List<List<Integer>> levelOrder = new ArrayList<>();
            if(root == null)
                return levelOrder;
            levelOrderUtil(root,levelOrder, 0);
            return levelOrder;
        }

        private void levelOrderUtil(NArrayTreeNode root, List<List<Integer>> levelOrder, int level) {
            if(levelOrder.size() == level)
                levelOrder.add(new LinkedList<>());

            levelOrder.get(level).add(root.val);
            for(NArrayTreeNode child : root.children)
                levelOrderUtil(child, levelOrder, level+1);

        }

    }

    static class SolutionUsingPostorder {

        public int maxDepth(NArrayTreeNode root) {
            if(root == null)
                return 0;

            int maxDepth = 0;
            for(NArrayTreeNode child: root.children)
                maxDepth = Math.max(maxDepth(child), maxDepth); //just like max of a left and right child

            return maxDepth+1;
        }
    }
}
