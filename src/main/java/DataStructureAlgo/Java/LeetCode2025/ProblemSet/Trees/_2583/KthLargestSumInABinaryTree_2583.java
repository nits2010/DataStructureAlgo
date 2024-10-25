package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees._2583;

import DataStructureAlgo.Java.LeetCode2025.ProblemSet.Heap._245.KthLargestElementInAnArray_245;
import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.TreeBuilder;
import DataStructureAlgo.Java.helpers.templates.TreeNode;
import DataStructureAlgo.Java.nonleetcode.Tree.traversal.TreeTraversalRecursive;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

/**
 * Author: Nitin Gupta
 * Date:22/10/24
 * Question Category: 2583. Kth Largest Sum in a Binary Tree
 * Description: https://leetcode.com/problems/kth-largest-sum-in-a-binary-tree/description/
 * You are given the root of a binary tree and a positive integer k.
 * <p>
 * The level sum in the tree is the sum of the values of the nodes that are on the same level.
 * <p>
 * Return the kth largest level sum in the tree (not necessarily distinct). If there are fewer than k levels in the tree, return -1.
 * <p>
 * Note that two nodes are on the same level if they have the same distance from the root.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [5,8,9,2,1,3,7,4,6], k = 2
 * Output: 13
 * Explanation: The level sums are the following:
 * - Level 1: 5.
 * - Level 2: 8 + 9 = 17.
 * - Level 3: 2 + 1 + 3 + 7 = 13.
 * - Level 4: 4 + 6 = 10.
 * The 2nd largest level sum is 13.
 * Example 2:
 * <p>
 * <p>
 * Input: root = [1,2,null,3], k = 1
 * Output: 3
 * Explanation: The largest level sum is 3.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is n.
 * 2 <= n <= 105
 * 1 <= Node.val <= 106
 * 1 <= k <= n
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link KthLargestElementInAnArray_245}
 * <p>
 * Tags
 * -----
 *
 * @Tree
 * @Breadth-FirstSearch
 * @Sorting
 * @BinaryTree <p>
 * Company Tags
 * -----
 * @Editorial
 */

public class KthLargestSumInABinaryTree_2583 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new Integer[]{5, 8, 9, 2, 1, 3, 7, 4, 6}, 2, 13);
        test &= test(new Integer[]{1, 2, null, 3}, 1, 3);

        CommonMethods.printResult(test);
    }

    private static boolean test(Integer[] nums, int k, long expected) {
        CommonMethods.print(new String[]{"Input", "K", "Expected" }, true, nums, k, expected);

        TreeNode root = TreeBuilder.buildTreeFromLevelOrder(nums);
        System.out.println("Level order traversal : " + TreeTraversalRecursive.levelOrderWithNull(root));

        long output;
        boolean pass, finalPass = true;

        Solution_PQ solutionPQ = new Solution_PQ();
        Solution_UsingList solutionUsingList = new Solution_UsingList();

        output = solutionPQ.kthLargestLevelSum(root, k);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.print(new String[]{"Using PQ",  "Pass"}, false, output, (pass ? "Yes" : "No"));


        output = solutionUsingList.kthLargestLevelSum(root, k);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.print(new String[]{"Using List Quick Select",  "Pass"}, false, output, (pass ? "Yes" : "No"));

        return finalPass;


    }


    /**
     * This problem is extension of {@link KthLargestElementInAnArray_245}. Post finding level sum of all level, we need to find the kth Largest element.
     */
    static class Solution_UsingList {
        public long kthLargestLevelSum(TreeNode root, int k) {
            if (root == null || k == 0)
                return -1;

            List<Long> levelsSum = getLevelSum(root);
            if (levelsSum.size() < k)
                return -1;
            return kthLargestQuickSelect(levelsSum, k);

        }

        private List<Long> getLevelSum(TreeNode root) {
            final Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            List<Long> levelsSum = new ArrayList<>();

            while (!queue.isEmpty()) {
                int size = queue.size();

                long sum = 0;
                for (int i = 0; i < size; i++) {
                    TreeNode temp = queue.poll();
                    sum += temp.val;

                    if (temp.left != null)
                        queue.offer(temp.left);
                    if (temp.right != null)
                        queue.offer(temp.right);
                }

                levelsSum.add(sum);
            }
            return levelsSum;
        }


        /**
         * {@link KthLargestElementInAnArray_245.SolutionUsingQuickSelect}
         */
        Random random = new Random();

        private long kthLargestQuickSelect(List<Long> levelsSum, int kth) {
            int n = levelsSum.size();
            int k = n - kth; // kth largest is n-kth smallest

            int l = 0, r = n - 1;

            while (l < r) {

                int partitionIndex = _2WayPartition(levelsSum, l, r);

                if (partitionIndex < k)
                    l = partitionIndex + 1;
                else if (partitionIndex > k)
                    r = partitionIndex - 1;
                else
                    break;
            }
            return levelsSum.get(k);
        }

        /**
         * num = [ 4 , 5 , 3 , 8 , 1 ] low = 0, high = 4,
         * pivot = 4, boundary = 0, l = 1
         * nums[1] <= pivot -> 5 <= 4 -> false; l++ = 2
         * nums[2] <= pivot -> 3 <= 4 -> true; boundary = 1, swap (1,2) => [4,3,5,8,1], l++ -> l = 3
         * nums[3] <= pivot -> 8<= 4 -> false; l++ = 4
         * nums[4] <= pivot -> 1 <= 4 -> true; boundary = 2, swap (2,4) => [4,3,1, 8, 5], l++ -> l = 5
         * <p>
         * swap(low, boundary) => [1,3,4,8,5]
         */
        private int _2WayPartition(List<Long> levelsSum, int low, int high) {

            //choose random index between [low,high]
            int randomIndex = low + random.nextInt(high - low + 1);

            //swap it with low, to make low as pivot
            swap(low, randomIndex, levelsSum);

            long pivot = levelsSum.get(low);
            int l = low + 1;
            int boundary = low;

            //scan
            while (l <= high) {

                //if current element is lesser than pivot then move it
                if (levelsSum.get(l) <= pivot) {
                    //move boundary
                    ++boundary;
                    //swap
                    swap(l, boundary, levelsSum);
                }

                //move l
                l++;
            }
            swap(low, boundary, levelsSum);
            return boundary;
        }

        void swap(int a, int b, List<Long> sum) {
            long temp = sum.get(a);
            sum.set(a, sum.get(b));
            sum.set(b, temp);
        }
    }


    /**
     * Using Priority Queue {@link KthLargestElementInAnArray_245.SolutionUsingPriorityQueue}
     */
    static class Solution_PQ {
        public long kthLargestLevelSum(TreeNode root, int k) {
            if (root == null || k == 0)
                return -1;

            //PQ to make data in sorted order, min-heap of size k
            final PriorityQueue<Long> pq = new PriorityQueue<>(Comparator.comparing(Long::longValue));
            final Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            while (!queue.isEmpty()) {
                int size = queue.size();

                long sum = 0;
                for (int i = 0; i < size; i++) {
                    TreeNode temp = queue.poll();
                    sum += temp.val;

                    if (temp.left != null)
                        queue.offer(temp.left);
                    if (temp.right != null)
                        queue.offer(temp.right);
                }

                if (pq.size() < k) {
                    pq.offer(sum);
                } else {
                    if (pq.peek() < sum) {
                        pq.poll();
                        pq.offer(sum);
                    }
                }
            }
            if (pq.isEmpty() || pq.size() < k)
                return -1;
            return pq.poll();

        }


    }
}
