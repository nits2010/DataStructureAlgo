package DataStructureAlgo.Java;

import DataStructureAlgo.Java.LeetCode.templates.ListNode;

/**
 * Author: Nitin Gupta
 * Date: 8/1/2024
 * Question Category: [easy | medium | hard ]
 * Description:
 *
 * <p>
 *
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
 * @easy @medium @hard
 * <p>
 * Company Tags
 * -----
 */
public class KthNodeFromEndOfLinkedList {

    public ListNode kthNodeFromEndFromLinkedList(ListNode head, int k) {
        ListNode fast = head;
        ListNode slow = head;

        while(fast!=null && k>0){
            fast = fast.next;
            k--;
        }

        if(fast == null && k>0) //k is greater than length of list
            return null;

        while (fast!=null){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public ListNode prevOfKthNodeFromEndFromLinkedList(ListNode head, int k) {
        ListNode fast = head;
        ListNode slow = head;
        ListNode prev = null;

        while(fast!=null && k>0){
            fast = fast.next;
            k--;
        }

        if(fast == null && k>0) //k is greater than length of list
            return null;

        while (fast!=null){
            prev = slow;
            slow = slow.next;
            fast = fast.next;
        }
        return prev;
    }
}
