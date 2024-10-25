package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees._987;

import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.TreeBuilder;
import DataStructureAlgo.Java.helpers.templates.TreeNode;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 8/18/2024
 * Question Category: 987. Vertical Order Traversal of a Binary Tree
 * Description: https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/description/
 * Given the root of a binary tree, calculate the vertical order traversal of the binary tree.
 * <p>
 * For each node at position (row, col), its left and right children will be at positions (row + 1, col - 1) and (row + 1, col + 1) respectively. The root of the tree is at (0, 0).
 * <p>
 * The vertical order traversal of a binary tree is a list of top-to-bottom orderings for each column index starting from the leftmost column and ending on the rightmost column. There may be multiple nodes in the same row and same column. In such a case, sort these nodes by their values.
 * <p>
 * Return the vertical order traversal of the binary tree.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[9],[3,15],[20],[7]]
 * Explanation:
 * Column -1: Only node 9 is in this column.
 * Column 0: Nodes 3 and 15 are in this column in that order from top to bottom.
 * Column 1: Only node 20 is in this column.
 * Column 2: Only node 7 is in this column.
 * Example 2:
 * <p>
 * <p>
 * Input: root = [1,2,3,4,5,6,7]
 * Output: [[4],[2],[1,5,6],[3],[7]]
 * Explanation:
 * Column -2: Only node 4 is in this column.
 * Column -1: Only node 2 is in this column.
 * Column 0: Nodes 1, 5, and 6 are in this column.
 * 1 is at the top, so it comes first.
 * 5 and 6 are at the same position (2, 0), so we order them by their value, 5 before 6.
 * Column 1: Only node 3 is in this column.
 * Column 2: Only node 7 is in this column.
 * Example 3:
 * <p>
 * <p>
 * Input: root = [1,2,3,4,6,5,7]
 * Output: [[4],[2],[1,5,6],[3],[7]]
 * Explanation:
 * This case is the exact same as example 2, but with nodes 5 and 6 swapped.
 * Note that the solution remains the same since 5 and 6 are in the same location and should be ordered by their values.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [1, 1000].
 * 0 <= Node.val <= 1000
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
 * @hard
 * @HashTable
 * @Tree
 * @Depth-FirstSearch
 * @Breadth-FirstSearch
 * @Sorting
 * @BinaryTree <p>
 * <p>
 * Company Tags
 * -----
 * @Facebook
 * @Amazon
 * @Bloomberg
 * @Microsoft
 * @Uber
 *
 *
 * @Editorial <a href="https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/solutions/5657142/easiest-solution-over-leetcode-forum-simple-to-understand">...</a>
 */
public class VerticalOrderTraversalOfABinaryTree_987 {
    public static void main(String[] args) {
        boolean test = true;
        test &= test(new Integer[]{3, 9, 20, null, null, 15, 7}, List.of(List.of(9), List.of(3, 15), List.of(20), List.of(7))); //[[9],[3,15],[20],[7]]
        test &= test(new Integer[]{1, 2, 3, 4, 5, 6, 7}, List.of(List.of(4), List.of(2), List.of(1, 5, 6), List.of(3), List.of(7))); //[[4],[2],[1,5,6],[3],[7]]
        test &= test(new Integer[]{3, 1, 4, 0, 2, 2}, List.of(List.of(0), List.of(1), List.of(3, 2, 2), List.of(4))); //[[0],[1],[3,2,2],[4]]
        System.out.println(test ? " All Passed " : " Something Failed ");
    }

    private static boolean test(Integer[] input, List<List<Integer>> expected) {

        System.out.println("=========================================");
        System.out.println("Input :" + Arrays.toString(input) + "\nExpected :" + expected);

        final TreeNode root = TreeBuilder.buildTreeFromLevelOrder(input);

        Solution solution = new Solution();
        List<List<Integer>> output = solution.verticalTraversal(root);


        boolean testResult = CommonMethods.equalsValues(output, expected);
        System.out.println("Output : " + output + " Test Result " + (testResult ? " Passed " : " Failed "));
        return testResult;

    }


    static class Solution {
        /**
         * Algo:
         * 1. Traverse the tree in any order (here its pre-order).And create a map.
         * Key : column positing or width
         * Value : map of row vs a list of elements in that row
         * 2. Compute current column and row. column = column -1 when go left otherwise column = column+1. row will be row+!
         * 3. For each column, then we need to add in this column, for this row.
         * 4. Also compute the minimum to maximum column.
         * 5. traverse the map, for each row, sort the list and add the final list of this column to the result.
         * <p>
         * T/S : O(n) / O(n)
         *
         * @param root
         * @return
         */
        public List<List<Integer>> verticalTraversal(TreeNode root) {
            List<List<Integer>> verticalTraversal = new LinkedList<>();
            if (root == null)
                return verticalTraversal;

            /**
             * We could use TreeMap instead of HashMap and avoid int[] col
             * TreeMap is another implementation of the Map interface in Java.
             * Unlike HashMap and LinkedHashMap, TreeMap does not preserve the insertion order.
             * Instead, it orders its entries based on the natural ordering of the keys, or by a Comparator provided at map creation time.
             * When you iterate over the entries of a TreeMap, they are returned in ascending order of their keys, according to the natural ordering or the Comparator
             */
            Map<Integer, Map<Integer, List<Integer>>> columnVsElements = new HashMap<>();

            int[] col = {Integer.MAX_VALUE, Integer.MIN_VALUE};

            //T/S : O(n) / O(n)
            int height = verticalTraversal(root, columnVsElements, 0, 0, col);

            //T/S : O(n) / O(n)
            for (int i = col[0]; i <= col[1]; i++) {
                //There may be multiple nodes in the same row and the same column. In such a case, sort these nodes by their values.
                Map<Integer, List<Integer>> current = columnVsElements.get(i);
                List<Integer> elements = new LinkedList<>();

                for (int h = 0; h < height; h++) {

                    List<Integer> element = current.getOrDefault(h, new LinkedList<>());
                    Collections.sort(element);
                    elements.addAll(element);
                }

                verticalTraversal.add(elements);
            }

            return verticalTraversal;
        }

        /**
         * T/S : O(n) / O(n)
         *
         * @param root
         * @param columnVsElements
         * @param column
         * @param row
         * @param col
         */
        public int verticalTraversal(TreeNode root, Map<Integer, Map<Integer, List<Integer>>> columnVsElements, int column, int row, int[] col) {
            if (root == null)
                return 0;

            columnVsElements.computeIfAbsent(column, k -> new HashMap<>());
            columnVsElements.get(column).computeIfAbsent(row, k -> new LinkedList<>());

            columnVsElements.get(column).get(row).add(root.val);


            col[0] = Math.min(column, col[0]);
            col[1] = Math.max(column, col[1]);
            return Math.max(
                    verticalTraversal(root.left, columnVsElements, column - 1, row + 1, col),
                    verticalTraversal(root.right, columnVsElements, column + 1, row + 1, col)) + 1;


        }
    }
}
