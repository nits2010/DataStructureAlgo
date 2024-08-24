package DataStructureAlgo.Java.LeetCode2025.easy.Tree;

import DataStructureAlgo.Java.helpers.TreeBuilder;
import DataStructureAlgo.Java.helpers.templates.TreeNode;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 8/24/2024
 * Question Category: 501. Find Mode in Binary Search Tree
 * Description: https://leetcode.com/problems/find-mode-in-binary-search-tree/description/
 * Given the root of a binary search tree (BST) with duplicates, return all the mode(s) (i.e., the most frequently occurred element) in it.
 * <p>
 * If the tree has more than one mode, return them in any order.
 * <p>
 * Assume a BST is defined as follows:
 * <p>
 * The left subtree of a node contains only nodes with keys less than or equal to the node's key.
 * The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [1,null,2,2]
 * Output: [2]
 * Example 2:
 * <p>
 * Input: root = [0]
 * Output: [0]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [1, 104].
 * -105 <= Node.val <= 105
 * <p>
 * <p>
 * Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).
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
 * @easy
 * @Tree
 * @Depth-FirstSearch
 * @BinarySearchTree
 * @BinaryTree <p>
 * <p>
 * Company Tags
 * -----
 * @Google
 * @Editorial
 */
public class FindModeInBinarySearchTree_501 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new Integer[]{1, null, 2, 2}, new int[]{2});
        test &= test(new Integer[]{0}, new int[]{0});
        System.out.println("===========================");
        System.out.println(test ? "All passed" : "Something Failed");
    }

    private static boolean test(Integer[] input, int[] expected) {
        System.out.println("------------------------------");
        System.out.println("Input : " + Arrays.toString(input) + "\nexpected : " + Arrays.toString(expected));
        TreeNode root = TreeBuilder.buildTreeFromLevelOrder(input);

        SolutionWithExtraSpace solutionWithExtraSpaec = new SolutionWithExtraSpace();
        SolutionWithoutExtraSpace solutionWithoutExtraSpaec = new SolutionWithoutExtraSpace();


        int[] outputWithExtraSpace = solutionWithExtraSpaec.findMode(root);
        boolean testResultWithExtraSpace = Arrays.equals(outputWithExtraSpace, expected);
        System.out.println("outputWithExtraSpace : " + Arrays.toString(outputWithExtraSpace) + " Test Result " + (testResultWithExtraSpace ? "Passed" : "Failed"));


        int[] outputWithoutExtraSpace = solutionWithoutExtraSpaec.findMode(root);
        boolean testResultWithoutExtraSpace = Arrays.equals(outputWithoutExtraSpace, expected);
        System.out.println("outputWithoutExtraSpace : " + Arrays.toString(outputWithoutExtraSpace) + " Test Result " + (testResultWithoutExtraSpace ? "Passed" : "Failed"));
        return testResultWithExtraSpace && testResultWithoutExtraSpace;
    }

    /**
     * T/S: O(n)/O(n)
     * Simply cache all elements and keep their count in hashmap. Search those elements that have maximum count and return
     */
    static class SolutionWithExtraSpace {
        public int[] findMode(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            if (root == null)
                return new int[]{};

            Map<Integer, Integer> countMap = new HashMap<>();
            int[] maxCount = {0};
            dfs(root, countMap, maxCount);

            for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
                if (entry.getValue() == maxCount[0]) {
                    list.add(entry.getKey());
                }
            }
            return list.stream().mapToInt(Integer::intValue).toArray();
        }

        private void dfs(TreeNode root, Map<Integer, Integer> countMap, int[] maxCount) {
            if (root == null)
                return;

            countMap.put(root.val, countMap.getOrDefault(root.val, 0) + 1);
            maxCount[0] = Math.max(maxCount[0], countMap.get(root.val));
            dfs(root.left, countMap, maxCount);
            dfs(root.right, countMap, maxCount);

        }
    }

    /**
     * Since the tree is BST, we can take advantage of inorder traversal. Inorder traversal will build the list in ascending order.
     * Hence, all the same elements will be placed next to each other. The occurrence of those elements can be counted using a simple two pointer counting method.
     * Where the first pointer will point the first occurrence while other pointing to the last occurrence and also keep the currentCount. If currentCount greater then max,
     * then we can update our result otherwise ignore the current element.
     * Since there can be multiple modes, hence we need to maintain the list of such maximum occurring elements.
     * <p>
     * Algo:
     * 1. Traverse the tree in in-order fashion
     * 2. keep checking the last element in in-order traversal is the same as current.
     * 2.1 if they are same, increase the count, and consider it as an output list,
     * ***** if it beats the maximum count so far. Create a new list and Update the maximum count as well.
     * ***** if it does not beat the maximum count so far, then ignore it.
     * ***** if its same as maximum counts so far, append it to the list.
     * 2.2 if they are different, reset the count to 1 and consider it as an output list, apply the same logic as above.
     *
     *
     * T/S: O(n)/O(1):  (Assume that the implicit stack space incurred due to recursion does not count).
     */
    static class SolutionWithoutExtraSpace {

        int currentCount = 0;
        int maxCount = 0;

        TreeNode prev = null;

        List<Integer> outputList;

        public int[] findMode(TreeNode root) {
            if (root == null)
                return new int[]{};

            dfsInorder(root);
            int []out = new int[outputList.size()];
            for(int i=0; i<outputList.size(); i++)
                out[i] = outputList.get(i);

            return out;
        }

        private void dfsInorder(TreeNode root) {

            if (root == null)
                return;

            dfsInorder(root.left);
            currentCount = prev == null ? 1 : (prev.val == root.val ? currentCount + 1 : 1);
            if (currentCount > maxCount) {
                outputList = new ArrayList<>();
                outputList.add(root.val);
                maxCount = currentCount;
            } else if (currentCount == maxCount) {
                outputList.add(root.val);
            }
            prev = root;
            dfsInorder(root.right);
        }


    }
}
