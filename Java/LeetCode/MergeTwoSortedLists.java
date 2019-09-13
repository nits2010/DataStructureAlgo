package Java.LeetCode;

import Java.LeetCode.templates.ListNode;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-16
 * Description: https://leetcode.com/problems/merge-two-sorted-lists
 * 21. Merge Two Sorted Lists [EASY]
 * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
 * <p>
 * Example:
 * <p>
 * Input: 1->2->4, 1->3->4
 * Output: 1->1->2->3->4->4
 */
public class MergeTwoSortedLists {

    static class MergeTwoSortedSinglyLists {

        //Recursive
       public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

            if (l1 == null)
                return l2;

            if (l2 == null)
                return l1;

            ListNode result = null;

            if (l1.val <= l2.val) {
                result = l1;
                result.next = mergeTwoLists(l1.next, l2);
            } else {
                result = l2;
                result.next = mergeTwoLists(l1, l2.next);
            }
            return result;

        }

        //Iterative
        public ListNode mergeTwoListsIterative(ListNode l1, ListNode l2) {
            ListNode h = new ListNode(0);
            ListNode ans = h;
            while (l1 != null && l2 != null) {
                if (l1.val < l2.val) {
                    h.next = l1;
                    h = h.next;
                    l1 = l1.next;
                } else {
                    h.next = l2;
                    h = h.next;
                    l2 = l2.next;
                }
            }
            if (l1 == null) {
                h.next = l2;
            }
            if (l2 == null) {
                h.next = l1;
            }
            return ans.next;
        }
    }
}
