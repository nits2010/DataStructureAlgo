package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees.viewOfTree;

import DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees.VerticalOrderTraversalOfABinaryTree_987;
import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.TreeBuilder;
import DataStructureAlgo.Java.helpers.templates.TreeNode;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 8/19/2024
 * Question Category: Top view of a Binary Tree
 * Description: https://takeuforward.org/data-structure/top-view-of-a-binary-tree/
 * <p>
 * Given a Binary Tree, return its Top View. The Top View of a Binary Tree is the set of nodes visible when we see the tree from the top.
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link BinaryTreeRightSideView_199} {@link BinaryTreeLeftSideView}
 * extension {@link VerticalOrderTraversalOfABinaryTree_987}
 * <p>
 * Tags
 * -----
 *
 * @medium
 * @Tree
 * @Depth-FirstSearch
 * @Breadth-FirstSearch
 * @BinaryTree <p>
 * <p>
 * <p>
 * Company Tags
 * -----
 * @Facebook
 * @Amazon
 * @Bloomberg
 * @Qualtrics
 * @Oracle
 * @Editorial
 */
public class BinaryTreeTopView {


    public static void main(String[] args) {
        boolean test = true;
        test &= test(new Integer[]{1, 2, 3, null, 5, null, 4}, List.of(2, 1, 3, 4));
        test &= test(new Integer[]{1, null, 3}, List.of(1, 3));
        test &= test(new Integer[]{1, 2, 3, 4, 10, 9, 11, null, 5, null, null, null, null, null, null, null, 6}, List.of(4, 2, 1, 3, 11));
        test &= test(new Integer[]{2, 7, 5, 2, 6, null, 9, null, null, 5, 11, 4, null}, List.of(2, 7, 2, 5, 9));
        System.out.println("============================");
        System.out.println(test ? " All Passed " : " Something Failed ");
    }


    private static boolean test(Integer[] input, List<Integer> expected) {
        System.out.println("-----------------------");
        System.out.println("Input :" + Arrays.toString(input) + " expected :" + expected);

        TreeNode root = TreeBuilder.buildTreeFromLevelOrder(input);


        SolutionUsingPreOrderTraversal solutionUsingPreOrderTraversal = new SolutionUsingPreOrderTraversal();
        List<Integer> outputPreOrder = solutionUsingPreOrderTraversal.topSideView(root);
        boolean resultUsingPreOrder = CommonMethods.equalsValues(expected, outputPreOrder);
        System.out.println("outputUsingPreOrder : " + outputPreOrder + " resultUsingPreOrder : " + resultUsingPreOrder);

        SolutionUsingLevelOrder solutionUsingLevelOrder = new SolutionUsingLevelOrder();
        List<Integer> outputLevelOrder = solutionUsingLevelOrder.topSideView(root);
        boolean resultUsingLevelOrder = CommonMethods.equalsValues(expected, outputLevelOrder);
        System.out.println("outputLevelOrder : " + outputLevelOrder + " resultUsingLevelOrder : " + resultUsingLevelOrder);

        boolean finalResult = resultUsingPreOrder && resultUsingLevelOrder;
        System.out.println("finalResult : " + (finalResult ? " PASS " : " FAIL "));
        return finalResult;
    }

    static class SolutionUsingLevelOrder {

        static class Pair {
            TreeNode node;
            int column;

            public Pair(TreeNode root, int column) {
                this.node = root;
                this.column = column;
            }
        }
        public List<Integer> topSideView(TreeNode root) {
            List<Integer> topSideView = new LinkedList<>();
            if (root == null)
                return topSideView;

            Queue<Pair> queue = new LinkedList<>();
            queue.offer(new Pair(root, 0));

            /**
             * TreeMap is another implementation of the Map interface in Java.
             * Unlike HashMap and LinkedHashMap, TreeMap does not preserve the insertion order.
             * Instead, it orders its entries based on the natural ordering of the keys, or by a Comparator provided at map creation time.
             * When you iterate over the entries of a TreeMap, they are returned in ascending order of their keys, according to the natural ordering or the Comparator
             */
            Map<Integer, Integer> topViewMap = new TreeMap<>();

            while (!queue.isEmpty()){

                Pair current = queue.poll();

                if(!topViewMap.containsKey(current.column))
                    topViewMap.put(current.column, current.node.val);

                if(current.node.left!=null)
                    queue.offer(new Pair(current.node.left, current.column-1));
                if(current.node.right!=null)
                    queue.offer(new Pair(current.node.right, current.column+1));
            }

            for(Map.Entry<Integer, Integer> entry : topViewMap.entrySet()){
                topSideView.add(entry.getValue());
            }
            return topSideView;


        }
    }
    static class SolutionUsingPreOrderTraversal {
        public List<Integer> topSideView(TreeNode root) {

            List<Integer> topSideView = new LinkedList<>();
            if (root == null)
                return topSideView;

            /**
             * We could use TreeMap instead of HashMap and avoid int[] col
             * TreeMap is another implementation of the Map interface in Java.
             * Unlike HashMap and LinkedHashMap, TreeMap does not preserve the insertion order.
             * Instead, it orders its entries based on the natural ordering of the keys, or by a Comparator provided at map creation time.
             * When you iterate over the entries of a TreeMap, they are returned in ascending order of their keys, according to the natural ordering or the Comparator
             */
            Map<Integer, Integer[]> topViewMap = new HashMap<>();
            int[] col = {Integer.MAX_VALUE, Integer.MIN_VALUE};
            topSideView(root, topViewMap, 0, col, 0);

            for (int i = col[0]; i < col[1] + 1; i++) {
                if (topViewMap.containsKey(i))
                    topSideView.add(topViewMap.get(i)[0]);
            }


            return topSideView;
        }

        private void topSideView(TreeNode root, Map<Integer, Integer[]> topViewMap, int column, int[] col, int level) {
            if (root == null)
                return;

            if (!topViewMap.containsKey(column) || topViewMap.get(column)[1] > level)
                topViewMap.put(column, new Integer[]{root.val, level});


            col[0] = Math.min(column, col[0]);
            col[1] = Math.max(column, col[1]);
            topSideView(root.left, topViewMap, column - 1, col, level+1);
            topSideView(root.right, topViewMap, column + 1, col, level+1);

        }
    }
}
