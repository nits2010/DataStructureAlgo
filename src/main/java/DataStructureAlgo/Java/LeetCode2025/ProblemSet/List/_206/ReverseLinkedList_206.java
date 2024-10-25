package DataStructureAlgo.Java.LeetCode2025.ProblemSet.List._206;

import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.ListBuilder;
import DataStructureAlgo.Java.helpers.templates.DoublyListNode;
import DataStructureAlgo.Java.helpers.templates.ListNode;



/**
 * Author: Nitin Gupta
 * Date:  2024-07-26
 * Question Category: 206. Reverse Linked List [easy]
 * Description:  https://leetcode.com/problems/reverse-linked-list/
 *
 * Reverse a singly linked list.
 * <p>
 * Example:
 * <p>
 * Input: 1->2->3->4->5->NULL
 * Output: 5->4->3->2->1->NULL
 * Follow up:
 * <p>
 * A linked list can be reversed either iteratively or recursively. Could you implement both?
 * <p>
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 * @easy
 * @LinkedList
 * @Recursion
 *
 * <p>
 * Company Tags
 * -----
 * @Amazon
 * @Microsoft
 * @Apple
 * @Bloomberg
 * @Facebook

 *
 * @Editorial
 */

public class ReverseLinkedList_206 {

    public static void main(String[] args) {
        test(new Integer[]{1, 2, 3, 4, 5});
        test(new Integer[]{1, 2, 3, 4});
        test(new Integer[]{1});
        test(new Integer[]{1, 2});
    }


    private static void test(Integer[] elements) {

        testSingly(ListBuilder.arrayToSinglyList(elements), ListBuilder.arrayToSinglyList(CommonMethods.reverse(elements)));
        testDoubly(ListBuilder.arrayToDoublyListNode(elements), ListBuilder.arrayToDoublyListNode(CommonMethods.reverse(elements)));

    }


    private static void testSingly(ListNode list, ListNode expected) {
        System.out.println("\n Singly Input :" + CommonMethods.print(list) + " expected :" + CommonMethods.print(expected));
        ReverseSinglyLinkedList solution = new ReverseSinglyLinkedList();
        System.out.println(" Recursive :" + CommonMethods.print(solution.reverseListRecursive(ListBuilder.copyOf(list))));
        System.out.println(" Iterative :" + CommonMethods.print(solution.reverseListIterative(ListBuilder.copyOf(list))));
        System.out.println(" Iterative :" + CommonMethods.print(solution.reverseListIterative2(ListBuilder.copyOf(list))));
    }

    private static void testDoubly(DoublyListNode list, DoublyListNode expected) {
        System.out.println("\n Doubly Input :" + CommonMethods.print(list) + " expected :" + CommonMethods.print(expected));

        ReverseDoublyLinkedList solution = new ReverseDoublyLinkedList();
        System.out.println(" Recursive :" + CommonMethods.print(solution.reverseListRecursive(ListBuilder.copyOf(list))));
        System.out.println(" Iterative :" + CommonMethods.print(solution.reverseListIterative(ListBuilder.copyOf(list))));
        System.out.println(" Iterative :" + CommonMethods.print(solution.reverseListIterative2(ListBuilder.copyOf(list))));
    }

}


class ReverseSinglyLinkedList {

    /**
     * // 1-2-3-4; n_1 = 2-3-4 ; head = 1
     * //  2-3-4 ; n_1 = 3-4 ; head = 2
     * // 3-4; n_1 = 4; head= 3
     * // 4 -> return ; newHead = 4, 4->3 { n_1_list.next = head;}
     * // 4-3-2; newHead = 4
     * // 4-3-2-1, newHEad = 4
     */

    ListNode reverseListRecursive(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode n_1_list = head.next;
        head.next = null;

        ListNode newHead = reverseListRecursive(n_1_list);

        n_1_list.next = head;

        return newHead;
    }

    ListNode reverseListIterative(ListNode head) {

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

    ListNode reverseListIterative2(ListNode head) {

        if (null == head || head.next == null)
            return head;

        ListNode current = head;
        ListNode next = current.next;
        ListNode cache;
        current.next = null;

        while (next != null) {
            cache = next.next;
            next.next = current;
            current = next;
            next = cache;
        }

        return current;

    }
}


class ReverseDoublyLinkedList {


    DoublyListNode reverseListRecursive(DoublyListNode head) {
        if (head == null || head.next == null)
            return head;


        DoublyListNode n_1_head = head.next;

        head.next = null;
        n_1_head.prev = null;

        DoublyListNode newHead = reverseListRecursive(n_1_head);

        n_1_head.next = head;
        head.prev = n_1_head;

        return newHead;
    }

    DoublyListNode reverseListIterative(DoublyListNode head) {
        if (head == null || head.next == null)
            return head;

        DoublyListNode current = head;
        DoublyListNode next = head.next;
        current.next = null;
        current.prev = null;


        while (next != null) {
            //swap(next.next, current);
            {
                DoublyListNode temp = next.next;
                next.next = current;
                current.prev = next;
                current = temp;
            }

            //swap(current, next);
            {
                DoublyListNode temp = current;
                current = next;
                next = temp;
            }

        }

        return current;
    }


    DoublyListNode reverseListIterative2(DoublyListNode head) {
        if (head == null || head.next == null)
            return head;

        DoublyListNode current = head;
        DoublyListNode next = head.next;
        current.next = null;
        next.prev = null;


        while (next != null) {
           DoublyListNode cache = next.next;

           next.next = current;
           current.prev = next;
           next.prev = null;
           current = next;
           next = cache;

        }

        return current;
    }
}
