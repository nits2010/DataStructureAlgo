package Java.LeetCode;

import Java.LeetCode.HelperDatastructure.ListNode;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-12
 * Description:
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
