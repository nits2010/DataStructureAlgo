package DataStructureAlgo.Java.LeetCode2025.medium.Tree.flatten.BstToList;

import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.ListBuilder;
import DataStructureAlgo.Java.helpers.templates.TreeNode;
import DataStructureAlgo.Java.nonleetcode.Tree.traversal.TreeTraversalRecursive;

import java.util.Arrays;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 8/24/2024
 * Question Category: [easy | medium | hard ]
 * Description:
 * <p>
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.listToBST.ConvertSortedDoublyListBinarySearchTree}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 * <p>
 * <p>
 * Company Tags
 * -----
 *
 * @Editorial
 */
public class ConvertSortedDoublyLinkedListToBinarySearchTree {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8}, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8});
        test &= test(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        test &= test(new Integer[]{1, 2, 3}, new Integer[]{1, 2, 3});
        test &= test(new Integer[]{1}, new Integer[]{1});
        test &= test(new Integer[]{}, new Integer[]{});
        System.out.println("===========================");
        System.out.println(test ? "All passed" : "Something Failed");
    }

    private static boolean test(Integer[] input, Integer[] expected) {
        System.out.println("-----------------------------------------");
        System.out.println("Input :" + Arrays.toString(input) + "\nExpected :" + Arrays.toString(expected));

        TreeNode head = ListBuilder.arrayToDoublyList(input);

        DLLTOBSTTopDownApproach dlltobstTopDownApproach = new DLLTOBSTTopDownApproach();
        TreeNode root = dlltobstTopDownApproach.dllToBst(ListBuilder.arrayToDoublyList(input));

        List<Integer> inorder = TreeTraversalRecursive.inOrder(root);
        boolean testTop = CommonMethods.equalsValues(inorder, Arrays.asList(expected));
        System.out.println("Output Top down Inorder : " + inorder + " testTop : " + (testTop ? "Passed" : "Failed"));

        DLLTOBSTBottomUpApproach dlltobstBottomUpApproach = new DLLTOBSTBottomUpApproach();
        root = dlltobstBottomUpApproach.dllToBst(ListBuilder.arrayToDoublyList(input));
        inorder = TreeTraversalRecursive.inOrder(root);
        boolean testBottom = CommonMethods.equalsValues(inorder, Arrays.asList(expected));
        System.out.println("Output Bottom Up Inorder : " + inorder + " testBottom : " + (testBottom ? "Passed" : "Failed"));

        return testTop && testBottom;

    }


    /**
     * Algo:
     * 1. Find middle and make it root, then recursively do same for left and right sub-trees
     * <p>
     * O(N log N)/O(N)
     * As it is a balanced binary tree, height will be at most log N. For every level of the BST,
     * we are iterating over the complete linked list to find the middle element; thus, the time complexity will be O(NlogN).
     */
    static class DLLTOBSTTopDownApproach {


        public TreeNode dllToBst(TreeNode head) {
            if (head == null || head.right == null)
                return head;


            TreeNode[] temp = findMiddle(head);
            TreeNode middle = temp[0];
            TreeNode prev = temp[1];


            //detach list in two parts
            if (prev != null) {
                prev.right = null;
                middle.left = null;
            }

            if (middle.right != null)
                middle.right.left = null; //detach middle.right


            //two lists now available, one pointed by head and the other by middle.right
            middle.left = dllToBst(head);
            middle.right = dllToBst(middle.right);


            return middle;


        }


        /**
         * {@link DataStructureAlgo.Java.LeetCode2025.easy.List.MiddleOfTheLinkedList_876}
         *
         * @param head
         * @return
         */
        private TreeNode[] findMiddle(TreeNode head) {
            TreeNode slow = head;
            TreeNode fast = head;
            TreeNode prev = null;

            while (fast != null && fast.right != null) {
                fast = fast.right.right;
                prev = slow;
                slow = slow.right;

            }
            return new TreeNode[]{slow, prev};
        }
    }

    /**
     * Recursively reach the left bottom of the tree and start attaching nodes.
     * 1. Find n ; number of nodes
     * 2. find middle of n and go left and right
     * 3. one we reach only one node, start creating nodes and attach them
     */
    static class DLLTOBSTBottomUpApproach {


        public TreeNode dllToBst(TreeNode head) {
            if (head == null || head.right == null)
                return head;

            int n = length(head);

            return dllToBst(new TreeNode[]{head}, 0, n - 1);
        }

        private TreeNode dllToBst(TreeNode[] head, int start, int end) {
            if (start > end)
                return null;

            int middle = (start + end) / 2;
            TreeNode left = dllToBst(head, start, middle - 1);

            TreeNode parent = head[0];
            parent.left = left;
            head[0] = head[0].right;

            parent.right = dllToBst(head, middle + 1, end);

            return parent;

        }


        private int length(TreeNode head) {
            if (head == null)
                return 0;

            int n = 0;
            while (head != null) {
                n++;
                head = head.right;
            }
            return n;
        }
    }

}
