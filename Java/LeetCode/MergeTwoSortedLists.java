package Java.LeetCode;

import Java.helpers.GenericPrinter;
import Java.LeetCode.listToBST.ListBuilder;
import Java.LeetCode.templates.DoublyListNode;
import Java.LeetCode.templates.ListNode;

/**
 * Author: Nitin Gupta
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

    public static void main(String[] args) {
        test(new Integer[]{1, 2, 4}, new Integer[]{1, 3, 4}, new Integer[]{1, 1, 2, 3, 4, 4});
        test(new Integer[]{1, 2, 4, 6, 9}, new Integer[]{1, 3, 4}, new Integer[]{1, 1, 2, 3, 4, 4, 6, 9});
    }

    private static void test(Integer[] l1, Integer[] l2, Integer[] expected) {
        testSingly(ListBuilder.arrayToSinglyList(l1), ListBuilder.arrayToSinglyList(l2), ListBuilder.arrayToSinglyList(expected));
        testDoubly(ListBuilder.arrayToDoublyListNode(l1), ListBuilder.arrayToDoublyListNode(l2), ListBuilder.arrayToDoublyListNode(expected));
    }

    private static void testSingly(ListNode l1, ListNode l2, ListNode expected) {
        System.out.println("\nSingly \n  L1 :" + GenericPrinter.print(l1) + " L2 :" + GenericPrinter.print(l1) + " expected :" + GenericPrinter.print(expected));

        MergeTwoSortedSinglyLists singlyLists = new MergeTwoSortedSinglyLists();
        System.out.println(" Recursive :" + GenericPrinter.print(singlyLists.mergeTwoListsRecursive(ListBuilder.copyOf(l1), ListBuilder.copyOf(l2))));
        System.out.println(" Iterative :" + GenericPrinter.print(singlyLists.mergeTwoListsIterative(ListBuilder.copyOf(l1), ListBuilder.copyOf(l2))));
    }

    private static void testDoubly(DoublyListNode l1, DoublyListNode l2, DoublyListNode expected) {
        System.out.println("\nDoubly \n L1 :" + GenericPrinter.print(l1) + " L2 :" + GenericPrinter.print(l1) + " expected :" + GenericPrinter.print(expected));

        MergeTwoSortedDoublyLists doublyLists = new MergeTwoSortedDoublyLists();
        System.out.println(" Recursive :" + GenericPrinter.print(doublyLists.mergeTwoListsRecursive(ListBuilder.copyOf(l1), ListBuilder.copyOf(l2))));
        System.out.println(" Iterative :" + GenericPrinter.print(doublyLists.mergeTwoListsIterative(ListBuilder.copyOf(l1), ListBuilder.copyOf(l2))));

    }
}

class MergeTwoSortedSinglyLists {

    //Recursive
    public ListNode mergeTwoListsRecursive(ListNode l1, ListNode l2) {

        if (l1 == null)
            return l2;

        if (l2 == null)
            return l1;

        ListNode result = null;

        if (l1.val <= l2.val) {
            result = l1;
            result.next = mergeTwoListsRecursive(l1.next, l2);
        } else {
            result = l2;
            result.next = mergeTwoListsRecursive(l1, l2.next);
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


class MergeTwoSortedDoublyLists {

    //Recursive
    public DoublyListNode mergeTwoListsRecursive(DoublyListNode l1, DoublyListNode l2) {

        if (l1 == null)
            return l2;

        if (l2 == null)
            return l1;

        DoublyListNode result = null;

        if (l1.val <= l2.val) {
            result = l1;
            result.next = mergeTwoListsRecursive(l1.next, l2);
            result.next.prev = result;
        } else {
            result = l2;
            result.next = mergeTwoListsRecursive(l1, l2.next);
            result.next.prev = result;
        }
        return result;

    }

    //Iterative
    public DoublyListNode mergeTwoListsIterative(DoublyListNode l1, DoublyListNode l2) {
        DoublyListNode h = new DoublyListNode(0);
        DoublyListNode ans = h;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                h.next = l1;
                h.next.prev = h;
                h = h.next;
                l1 = l1.next;
            } else {
                h.next = l2;
                h.next.prev = h;
                h = h.next;
                l2 = l2.next;
            }
        }
        if (l1 == null) {
            h.next = l2;
            h.next.prev = h;
        }

        if (l2 == null) {
            h.next = l1;
            h.next.prev = h;
        }
        return ans.next;
    }
}