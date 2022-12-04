package DataStructureAlgo.Java.companyWise.Google;

import  DataStructureAlgo.Java.helpers.GenericPrinter;
import  DataStructureAlgo.Java.LeetCode.templates.TreapNode;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-29
 * Description:
 * <p>
 * A binary tree has the binary search tree property (BST property) if, for every node, the keys in its left subtree are smaller than its own key, and the keys in its right subtree are larger than its own key. It has the heap property if, for every node, the keys of its children are all smaller than its own key. You are given a set of binary tree nodes (i, j) where each node contains an integer i and an integer j. No two i values are equal and no two j values are equal. We must assemble the nodes into a single binary tree where the i values obey the BST property and the j values obey the heap property. If you pay attention only to the second key in each node, the tree looks like a heap, and if you pay attention only to the first key in each node, it looks like a binary search tree.
 * <p>
 * Example 1:
 * <p>
 * Input: [(1, 6), (3, 7), (2, 4)]
 * Output:
 * <p>
 * * 		(3, 7)
 * * 		/
 * * 	 (1, 6)
 * * 		\
 * * 	  (2, 4)
 * Example 2:
 * <p>
 * Input: [(1, 4), (8, 5), (3, 6), (10, -1), (4, 7)]
 * Output:
 * <p>
 * * 		(4, 7)
 * * 		/    \
 * * 	(3, 6)   (8, 5)
 * * 	 /          \
 * *  (1, 4)       (10, -1)
 * You can assume that a solution always exists.
 */
public class BinarySearchTreeHeap {

    public static void main(String[] args) {
        test(new int[][]{{1, 6}, {3, 7}, {2, 4}}, new int[][]{{3, 7}, {1, 6}, {2, 4}});
        test(new int[][]{{1, 4}, {8, 5}, {3, 6}, {10, -1}, {4, 7}}, new int[][]{{4, 7}, {3, 6}, {8, 5}, {1, 4}, {10, -1}});
    }


    private static List<int[]> levelOrder(TreapNode root) {

        Queue<TreapNode> queue = new LinkedList<>();
        queue.offer(root);

        List<int[]> levelOrder = new ArrayList<>();

        while (!queue.isEmpty()) {

            TreapNode node = queue.poll();

            levelOrder.add(new int[]{node.bstVal, node.heapVal});

            if (node.left != null)
                queue.offer(node.left);

            if (node.right != null)
                queue.offer(node.right);
        }


        return levelOrder;
    }

    private static void test(int[][] input, int[][] expected) {
        System.out.println("Input :" + GenericPrinter.toStringInline(input) + " Expected Output :" + GenericPrinter.toStringInline(expected));
        TreapBSTMaxHeap bstMaxHeap = new TreapBSTMaxHeap();
        TreapBSTMinHeap bstMinHeap = new TreapBSTMinHeap();
        final TreapNode root = bstMaxHeap.buildTreapMaxHeap(input);
        System.out.println(GenericPrinter.toString(levelOrder(root)));
        final TreapNode rootMinHeap = bstMinHeap.buildTreapMinHeap(input);
        System.out.println(GenericPrinter.toString(levelOrder(rootMinHeap)));
    }


}

class TreapBSTMaxHeap {


    /**
     * Idea : Since we need to maintain the max heap property, which means the second element will be always highest to lowest
     * in each level and the first element follow the BST property.
     * <p>
     * Sort the data based on second element, and then keep inserting based on first element following bst property
     *
     * @param nums
     * @return
     */
    public TreapNode buildTreapMaxHeap(int[][] nums) {
        if (nums == null || nums.length == 0)
            return null;

        Arrays.sort(nums, ((o1, o2) -> o2[1] - o1[1]));

        final TreapNode root = new TreapNode(nums[0][0], nums[0][1]);

        for (int i = 1; i < nums.length; i++)
            insert(nums[i], root);

        return root;
    }

    private TreapNode insert(int[] e, final TreapNode root) {

        if (root == null)
            return new TreapNode(e[0], e[1]);

        if (e[0] < root.bstVal)
            root.left = insert(e, root.left);
        else
            root.right = insert(e, root.right);

        return root;

    }
}


class TreapBSTMinHeap {


    /**
     * Idea : Since we need to maintain the min heap property, which means the second element will be always lowest to highest
     * in each level and the first element follow the BST property.
     * <p>
     * Sort the data based on second element, and then keep inserting based on first element following bst property
     *
     * @param nums
     * @return
     */
    public TreapNode buildTreapMinHeap(int[][] nums) {
        if (nums == null || nums.length == 0)
            return null;

        Arrays.sort(nums, Comparator.comparingInt(o -> o[1]));

        final TreapNode root = new TreapNode(nums[0][0], nums[0][1]);

        for (int i = 1; i < nums.length; i++)
            insert(nums[i], root);

        return root;
    }

    private TreapNode insert(int[] e, final TreapNode root) {

        if (root == null)
            return new TreapNode(e[0], e[1]);

        if (e[0] < root.bstVal)
            root.left = insert(e, root.left);
        else
            root.right = insert(e, root.right);

        return root;

    }
}