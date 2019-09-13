package Java.LeetCode;

import Java.LeetCode.templates.ListNode;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-12
 * Description: https://leetcode.com/problems/reverse-linked-list/
 * 206. Reverse Linked List
 * Reverse a singly linked list.
 * <p>
 * Example:
 * <p>
 * Input: 1->2->3->4->5->NULL
 * Output: 5->4->3->2->1->NULL
 * Follow up:
 * <p>
 * A linked list can be reversed either iteratively or recursively. Could you implement both?
 */


public class ReverseLinkedList {

    public ListNode reverseList(ListNode head) {

        return reverseListRecursive(head);

    }

    // 1-2-3-4; n_1 = 2-3-4 ; head = 1
    //  2-3-4 ; n_1 = 3-4 ; head = 2
    // 3-4; n_1 = 4; head= 3
    // 4 -> return ; newHead = 4, 4->3
    // 4-3-2; newHead = 4
    // 4-3-2-1, newHEad = 4
    public ListNode reverseListRecursive(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode n_1_list = head.next;
        head.next = null;

        ListNode newHead = reverseListRecursive(n_1_list);

        n_1_list.next = head;

        return newHead;
    }

    public ListNode reverseListIterative(ListNode head) {

        if (null == head || head.next == null)
            return head;

        ListNode current = head;
        ListNode next = current.next;
        current.next = null;

        while (next != null) {
            //swap(next.next, current);
            {
                ListNode temp = next.next;
                next.next = current;
                current = temp;
            }

            //swap(current, next);
            {
                ListNode temp = current;
                current = next;
                next = temp;
            }
        }
        return current;

    }

}
