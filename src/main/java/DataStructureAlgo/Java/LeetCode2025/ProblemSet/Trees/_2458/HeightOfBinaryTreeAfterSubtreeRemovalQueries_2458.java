package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees._2458;

import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.TreeBuilder;
import DataStructureAlgo.Java.helpers.templates.TreeNode;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 10/27/2024
 * Question Category: 2458. Height of Binary Tree After Subtree Removal Queries
 * Description: https://leetcode.com/problems/height-of-binary-tree-after-subtree-removal-queries/description/?envType=daily-question&envId=2024-10-26
 * You are given the root of a binary tree with n nodes. Each node is assigned a unique value from 1 to n. You are also given an array queries of size m.
 * <p>
 * You have to perform m independent queries on the tree where in the ith query you do the following:
 * <p>
 * Remove the subtree rooted at the node with the value queries[i] from the tree. It is guaranteed that queries[i] will not be equal to the value of the root.
 * Return an array answer of size m where answer[i] is the height of the tree after performing the ith query.
 * <p>
 * Note:
 * <p>
 * The queries are independent, so the tree returns to its initial state after each query.
 * The height of a tree is the number of edges in the longest simple path from the root to some node in the tree.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [1,3,4,2,null,6,5,null,null,null,null,null,7], queries = [4]
 * Output: [2]
 * Explanation: The diagram above shows the tree after removing the subtree rooted at node with value 4.
 * The height of the tree is 2 (The path 1 -> 3 -> 2).
 * Example 2:
 * <p>
 * <p>
 * Input: root = [5,8,9,2,1,3,7,4,6], queries = [3,2,4,8]
 * Output: [3,2,3,2]
 * Explanation: We have the following queries:
 * - Removing the subtree rooted at node with value 3. The height of the tree becomes 3 (The path 5 -> 8 -> 2 -> 4).
 * - Removing the subtree rooted at node with value 2. The height of the tree becomes 2 (The path 5 -> 8 -> 1).
 * - Removing the subtree rooted at node with value 4. The height of the tree becomes 3 (The path 5 -> 8 -> 2 -> 6).
 * - Removing the subtree rooted at node with value 8. The height of the tree becomes 2 (The path 5 -> 9 -> 3).
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is n.
 * 2 <= n <= 105
 * 1 <= Node.val <= n
 * All the values in the tree are unique.
 * m == queries.length
 * 1 <= m <= min(n, 104)
 * 1 <= queries[i] <= n
 * queries[i] != root.val
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @Hard
 * @Array
 * @Tree
 * @Depth-FirstSearch
 * @Breadth-FirstSearch
 * @BinaryTree <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSoltuion {@link Solution_TwoLargestCousins_LevelHeight} {@link Solution_TwoTraversal_2}
 */
public class HeightOfBinaryTreeAfterSubtreeRemovalQueries_2458 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new Integer[]{1, 3, 4, 2, null, 6, 5, null, null, null, null, null, 7}, new int[]{4}, new int[]{2});
        test &= test(new Integer[]{5, 8, 9, 2, 1, 3, 7, 4, 6}, new int[]{3, 2, 4, 8}, new int[]{3, 2, 3, 2});
        test &= test(new Integer[]{1, null, 5, 3, null, 2, 4}, new int[]{3, 5, 4, 2, 4}, new int[]{1, 0, 3, 3, 3});
        test &= test(new Integer[]{2, 1, 4, null, null, 3}, new int[]{1, 4, 3, 4}, new int[]{2, 1, 1, 1});
        CommonMethods.printResult(test);
    }

    private static boolean test(Integer[] root, int[] queries, int[] expected) {
        CommonMethods.print(new String[]{"Tree", "Queries", "Expected"}, true, root, queries, expected);
        final TreeNode tree = TreeBuilder.buildTreeFromLevelOrder(root);

        int[] output;
        boolean pass, finalPass = true;

        SolutionTLE solutionTLE = new SolutionTLE();
        output = solutionTLE.treeQueries(tree, queries);
        pass = Arrays.equals(expected, output);
        CommonMethods.print(new String[]{"TLE", "Result"}, false, output, pass ? "Passed" : "Failed");
        finalPass &= pass;

        Solution_TwoTraversal_1 solution_TwoTraversal_1 = new Solution_TwoTraversal_1();
        output = solution_TwoTraversal_1.treeQueries(tree, queries);
        pass = Arrays.equals(expected, output);
        CommonMethods.print(new String[]{"Solution_TwoTraversal_1", "Result"}, false, output, pass ? "Passed" : "Failed");

        finalPass &= pass;

        Solution_TwoTraversal_2 solution_TwoTraversal_2 = new Solution_TwoTraversal_2();
        output = solution_TwoTraversal_2.treeQueries(tree, queries);
        pass = Arrays.equals(expected, output);
        CommonMethods.print(new String[]{"Solution_TwoTraversal_2", "Result"}, false, output, pass ? "Passed" : "Failed");
        finalPass &= pass;


        Solution_TwoLargestCousins_LevelHeight solutionTwoLargestCousinsLevelHeight = new Solution_TwoLargestCousins_LevelHeight();
        output = solutionTwoLargestCousinsLevelHeight.treeQueries(tree, queries);
        pass = Arrays.equals(expected, output);
        CommonMethods.print(new String[]{"TwoLargestCousins", "Result"}, false, output, pass ? "Passed" : "Failed");
        finalPass &= pass;

        return finalPass;

    }

    /**
     * Approach 5: Two Largest Cousins
     * <a href="https://leetcode.com/problems/height-of-binary-tree-after-subtree-removal-queries/editorial/?envType=daily-question&envId=2024-10-26">...</a>
     * Video Tutorial: <a href="https://www.youtube.com/watch?v=EY4bCorJH4g&t=12s">...</a>
     */
    static class Solution_TwoLargestCousins_LevelHeight {

        static class LevelHeight {
            int height;
            int level;

            LevelHeight(int height, int level) {
                this.height = height;
                this.level = level;
            }
        }

        static class LargestTwoCousins {
            int firstLargest;
            int secondLargest;

            int level;

            LargestTwoCousins(int firstLargest, int secondLargest, int level) {
                this.firstLargest = firstLargest;
                this.secondLargest = secondLargest;
                this.level = level;
            }
        }

        public int[] treeQueries(TreeNode root, int[] queries) {

            //This holds the height and level of each node - 1 based
            final Map<Integer, LevelHeight> heightLevel = new HashMap<>();

            //This holds the level of each node and at that level the first and second-largest cousin { the largest subtree }
            final Map<Integer, LargestTwoCousins> largestTwoCousins = new HashMap<>();

            int[] output = new int[queries.length];

            // get Height, level and largest cousins
            dfs(root, heightLevel, largestTwoCousins, 0);

            for (int i = 0; i < queries.length; i++) {
                int node = queries[i];

                //get the level and height of the node
                int level = heightLevel.get(node).level;
                int height = heightLevel.get(node).height;

                //get the largest two cousins
                int firstLargest = largestTwoCousins.get(level).firstLargest;
                int secondLargest = largestTwoCousins.get(level).secondLargest;
                int largetsSubTreeHeight;

                //if this node height is the same as firstLargest, then we need to use secondLargest as the new Height at this level, because it's possible that this deleted node holds the first largest subtree

                largetsSubTreeHeight = firstLargest == height ? secondLargest : firstLargest;

                //now the new height from the root will be level ( depth of this node from root ) + the largest subtree remaining height
                output[i] = level + largetsSubTreeHeight - 1; //subtract 1 ,  0-based indexing
            }

            return output;
        }

        private int dfs(TreeNode root, Map<Integer, LevelHeight> heightLevel, Map<Integer, LargestTwoCousins> largestTwoCousins, int level) {

            if (root == null) {
                return 0;
            }

            int leftHeight = dfs(root.left, heightLevel, largestTwoCousins, level + 1);
            int rightHeight = dfs(root.right, heightLevel, largestTwoCousins, level + 1);

            int height = Math.max(leftHeight, rightHeight) + 1;

            heightLevel.put(root.val, new LevelHeight(height, level));

            LargestTwoCousins largestCousins = largestTwoCousins.get(level);

            if (largestCousins == null) {
                largestTwoCousins.put(level, new LargestTwoCousins(height, 0, level));
            } else {
                //new height > old height
                if (height > largestCousins.firstLargest) {
                    largestCousins.secondLargest = largestCousins.firstLargest;
                    largestCousins.firstLargest = height;
                } else if (height > largestCousins.secondLargest) {
                    largestCousins.secondLargest = height;
                }
            }


            return height;


        }
    }

    /**
     * Approach 2: Single Traversal
     * it is actually two traversals ( 1 for height and one of dfs )
     * https://leetcode.com/problems/height-of-binary-tree-after-subtree-removal-queries/editorial/?envType=daily-question&envId=2024-10-26
     */
    static class Solution_TwoTraversal_2 {
        private final Map<TreeNode, Integer> depthHeight = new HashMap<>();
        private int[] output;

        public int[] treeQueries(TreeNode root, int[] queries) {
            //get the height of each node
            height(root);

            output = new int[depthHeight.size() + 1];

            depthHeight.put(null, 0);

            dfs(root, -1, 0);


            int m = queries.length;
            int[] ans = new int[m];

            for (int i = 0; i < m; ++i) {
                ans[i] = output[queries[i]];
            }
            return ans;
        }

        private void dfs(TreeNode root, int depth, int rest) {
            if (root == null) {
                return;
            }
            output[root.val] = rest; //rest represent the height of this root of this being removed

            //get depth/height of the right subtree and left-subtree height (depth) + 1 for height of this rooted subtree
            dfs(root.left, depth + 1, Math.max(rest, 1 + depth + depthHeight.get(root.right)));
            dfs(root.right, depth + 1, Math.max(rest, 1 + depth + depthHeight.get(root.left)));
        }

        /**
         * 1-based height
         */
        private int height(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int l = height(root.left), r = height(root.right);
            depthHeight.put(root, 1 + Math.max(l, r));
            return depthHeight.get(root);
        }
    }

    /**
     * Approach 1: Left and Right Traversal
     * https://leetcode.com/problems/height-of-binary-tree-after-subtree-removal-queries/editorial/?envType=daily-question&envId=2024-10-26
     */
    static class Solution_TwoTraversal_1 {


        Map<Integer, Integer> maxHeightAfterRemoval = new HashMap<>();
        int currentMaxHeight = 0;

        public int[] treeQueries(TreeNode root, int[] queries) {

            leftRight(root, 0);

            currentMaxHeight = 0;

            rightLeft(root, 0);


            int[] output = new int[queries.length];
            int i = 0;
            for (int q : queries) {
                output[i++] = maxHeightAfterRemoval.get(q);
            }
            return output;

        }

        public void leftRight(TreeNode root, int currentHeight) {
            if (root == null)
                return;

            maxHeightAfterRemoval.put(root.val, currentMaxHeight);
            currentMaxHeight = Math.max(currentMaxHeight, currentHeight); //this will have the height of the tree at the end

            leftRight(root.left, currentHeight + 1);
            leftRight(root.right, currentHeight + 1);
        }

        public void rightLeft(TreeNode root, int currentHeight) {
            if (root == null)
                return;
            maxHeightAfterRemoval.put(root.val, Math.max(maxHeightAfterRemoval.get(root.val), currentMaxHeight));
            currentMaxHeight = Math.max(currentMaxHeight, currentHeight); //this will have the height of the tree at the end
            rightLeft(root.right, currentHeight + 1);
            rightLeft(root.left, currentHeight + 1);


        }


    }


    static class SolutionTLE {
        static class Pair {
            TreeNode parent;
            int height;

            Pair(TreeNode r, int h) {
                this.parent = r;
                this.height = h;
            }

            @Override
            public String toString() {
                if (parent == null)
                    return ":" + height;
                return parent.val + ":" + height;
            }
        }

        /**
         * Time Complexity : O(N+Q*H)
         */
        public int[] treeQueries(TreeNode root, int[] queries) {

            Map<Integer, Pair> map = new HashMap<>();

            //calculate the height of each node and store the parent of it
            //O(N)
            height(root, null, map);

            int[] output = new int[queries.length];

            int i = 0;

            //O(Q*H)
            for (int node : queries) {
                output[i++] = executeQueries(node, map, root);
            }

            return output;
        }

        /**
         * Traverse from the node to upward and calculate the new height
         * Total Time complexity : O(H) where H is the max height of the tree
         */
        private int executeQueries(int node, Map<Integer, Pair> map, TreeNode root) {
            if (root == null)
                return 0;

            //This should not happen if queries are unique and present in the tree
            if (map.get(node) == null)
                return map.get(root.val).height - 1;

            int newHeight = 0;
            //scan from node to root till it root node
            while (root.val != node) {
                TreeNode parent = map.get(node).parent;

                //if the current node is left, child of parent
                if (parent.left != null && parent.left.val == node) {

                    //get the new height of this parent,
                    //this is nothing but making left height either 0 (if this the query node) otherwise the new height and height on the right subtree
                    newHeight = Math.max(newHeight, parent.right == null ? 0 : map.get(parent.right.val).height) + 1; //+1 so that it still keeps height 1 based

                } else if (parent.right != null && parent.right.val == node) {

                    //get the new height of this parent
                    //this is nothing but making right height either 0 (if this the query node) otherwise the new height and height on the left subtree
                    newHeight = Math.max(newHeight, parent.left == null ? 0 : map.get(parent.left.val).height) + 1;  //+1 so that it still keeps height 1 based
                }

//                System.out.println("node :"+node + " parent :"+parent.val + " neightHeight :"+newHeight);

                node = parent.val;

            }

            return newHeight - 1; //to make 0-based index

        }

        /**
         * Store height of the root (1-based) and its parent for each node in map.
         */
        private int height(TreeNode root, TreeNode parent, Map<Integer, Pair> map) {

            if (root == null)
                return 0;

            int height = Math.max(height(root.left, root, map), height(root.right, root, map)) + 1;

            // cache height and parent
            map.put(root.val, new Pair(parent, height));

            return height;

        }

    }
}
