package Java.LeetCode;

import Java.helpers.GenericPrinter;
import Java.LeetCode.templates.ListNode;
import Java.LeetCode.listToBST.ListBuilder;

import java.util.Arrays;
import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-09-02
 * Description: https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 * 19. Remove Nth Node From End of List
 * Given a linked list, remove the n-th node from the end of list and return its head.
 * <p>
 * Example:
 * <p>
 * Given linked list: 1->2->3->4->5, and n = 2.
 * <p>
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Note:
 * <p>
 * Given n will always be valid.
 * <p>
 * Follow up:
 * <p>
 * Could you do this in one pass?
 */
public class RemoveNthNodeFromEnd {

    public static void main(String[] args) {
        test(ListBuilder.arrayToSinglyList(new Integer[]{1, 2, 3, 4, 5}), 2, Arrays.asList(1, 2, 3, 5));
        test(ListBuilder.arrayToSinglyList(new Integer[]{1, 2, 3, 4, 5}), 4, Arrays.asList(1, 3, 4, 5));
        test(ListBuilder.arrayToSinglyList(new Integer[]{1, 2, 3, 4, 5}), 5, Arrays.asList(2, 3, 4, 5));
        test(ListBuilder.arrayToSinglyList(new Integer[]{1, 2, 3, 4, 5}), 1, Arrays.asList(1, 2, 3, 4));
        test(ListBuilder.arrayToSinglyList(new Integer[]{1, 2, 3, 4, 5}), 10, Arrays.asList(1, 2, 3, 4, 5));
        test(ListBuilder.arrayToSinglyList(new Integer[]{1, 2, 3, 4, 5}), 6, Arrays.asList(1, 2, 3, 4, 5));
    }

    private static void test(ListNode head, int n, List<Integer> expected) {
        System.out.println(" \n Input :" + GenericPrinter.print(head) + " n :" + n + " expected :" + expected);
        System.out.println("\n Fast slow :" + GenericPrinter.print(removeNthFromEnd(head, n)));
    }

    /**
     * Algo: Fast Slow pointer concept
     * 1. Use fast slow pointer concept. Push fast pointer to n-1 node ahead from head {head node is also counted as 1 node}
     * 2. Move ahead one by one {this way both slow and fast node are n node away}
     * 3. Once we reach the last node as like fast node, then slow would be pointing to n'th node from end.
     * <p>
     * Single Pass: O(n)
     * <p>
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Remove Nth Node From End of List.
     * Memory Usage: 34.7 MB, less than 100.00% of Java online submissions for Remove Nth Node From End of List.
     *
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {

        if (null == head || n <= 0)
            return head;

        ListNode nthNodeFromEnd = head;
        ListNode previousNthNodeFromEnd = null;
        ListNode fastNode = head;

        //push fast node to n node ahead
        int x = n - 1; //as head is first node
        while (x > 0 && fastNode != null) {
            fastNode = fastNode.next;
            x--;
        }

        //If there is not enough n nodes : Question says that n is always valid. n<=size of list
//        if (fastNode == null && x == 0)
//            return head;

        //if head node is nth node from end
        if (fastNode == null || fastNode.next == null) {
            return head.next;
        }

        //Move both node 1 by 1, when fast is last node, then slow is n'th node from end
        while (fastNode.next != null) {
            fastNode = fastNode.next;
            previousNthNodeFromEnd = nthNodeFromEnd;
            nthNodeFromEnd = nthNodeFromEnd.next;
        }


        //detach the n'th node
        previousNthNodeFromEnd.next = nthNodeFromEnd.next;
        nthNodeFromEnd.next = null;

        return head;


    }
}
