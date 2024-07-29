package DataStructureAlgo.Java.LeetCode2025.medium;

import DataStructureAlgo.Java.LeetCode.listToBST.ListBuilder;
import DataStructureAlgo.Java.LeetCode.templates.ListNode;
import DataStructureAlgo.Java.helpers.GenericPrinter;

/**
 * Author: Nitin Gupta
 * Date: 2024-07-28
 * Description: https://leetcode.com/problems/swap-nodes-in-pairs
 * 24. Swap Nodes in Pairs [medium] | Pairwise Swap Nodes of a given Linked List
 * Given a linked list, swap every two adjacent nodes and return its head.
 * You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4]
 * Output: [2,1,4,3]
 * Example 2:
 *
 * Input: head = []
 * Output: []
 * Example 3:
 *
 * Input: head = [1]
 * Output: [1]
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [0, 100].
 * 0 <= Node.val <= 100
 *
 * * aka
 *  * 1. reverse linked list pairwise
 *  * 1. reverse linked list in group of 2 nodes
 */
public class SwapNodesInPairs {

    public static void main(String[] args) {
        test(new Integer[]{1, 2, 3, 4, 5}, new Integer[]{2, 1, 4, 3, 5});
        test(new Integer[]{1, 2, 3, 4, 5,6,7,8,9,10}, new Integer[]{2, 1, 4, 3, 6, 5, 8, 7, 10,9});
        test(new Integer[]{1, 2, 3, 4}, new Integer[]{2, 1, 4, 3});
        test(new Integer[]{1}, new Integer[]{1});
        test(new Integer[]{1, 2}, new Integer[]{2, 1});
    }


    private static void test(Integer[] elements,Integer[] expected ) {
        Solution solution = new Solution();
        ListNode originalList = ListBuilder.arrayToSinglyList(elements);
        ListNode expectedList = ListBuilder.arrayToSinglyList(expected);
        ListNode output =  solution.swapPairs(originalList);
        System.out.println("\n Input :"+GenericPrinter.print(originalList) + "\nexpected :" + GenericPrinter.print(expectedList));
        System.out.println(" Output :"+GenericPrinter.print(output) + " Result match : "+GenericPrinter.equalsValues(expectedList,output));




    }

    static class Solution {
        /**
         * O(n)/O(n)
         * <a href="https://leetcode.com/problems/swap-nodes-in-pairs/submissions/1336122021/">...</a>
         * @param head
         * @return
         */
        public ListNode swapPairs(ListNode head) {

            if (head == null || head.next == null)
                return head;

            ListNode next = head.next;
            head.next = next.next;
            next.next = head;
            head.next = swapPairs(head.next);
            return next;

        }
    }
}
