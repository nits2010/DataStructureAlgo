package Java.LeetCode.AddTwoNumbersList;

import Java.helpers.GenericPrinter;
import Java.LeetCode.listToBST.ListBuilder;
import Java.LeetCode.templates.ListNode;

import java.util.Stack;

/**
 * Author: Nitin Gupta
 * Date: 2019-09-11
 * Description: https://leetcode.com/problems/add-two-numbers-ii/
 * 445. Add Two Numbers II
 * You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * Follow up:
 * What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
 * <p>
 * Example:
 * <p>
 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 8 -> 0 -> 7
 */
public class AddTwoNumbersListListII {

    public static void main(String[] args) {
        test(ListBuilder.arrayToSinglyList(new Integer[]{8, 9, 9}), ListBuilder.arrayToSinglyList(new Integer[]{2}), new Integer[]{9, 0, 1});
        test(ListBuilder.arrayToSinglyList(new Integer[]{9, 9, 9, 9}), ListBuilder.arrayToSinglyList(new Integer[]{1, 1, 1, 1}), new Integer[]{1, 1, 1, 1, 0});
        test(ListBuilder.arrayToSinglyList(new Integer[]{7, 2, 4, 3}), ListBuilder.arrayToSinglyList(new Integer[]{5, 6, 4}), new Integer[]{7, 8, 0, 7});
        test(ListBuilder.arrayToSinglyList(new Integer[]{9, 9, 9, 9}), ListBuilder.arrayToSinglyList(new Integer[]{9}), new Integer[]{1, 0, 0, 0, 8});
    }

    private static void test(ListNode l1, ListNode l2, Integer[] expected) {
        System.out.println("\n L1 :" + GenericPrinter.print(l1) + "\n L2 :" + GenericPrinter.print(l2) + "\n Expected :" + GenericPrinter.toString(expected));
        AddTwoNumbersListListIIUsingDiff usingDiff = new AddTwoNumbersListListIIUsingDiff();
        AddTwoNumbersListListIIStack stack = new AddTwoNumbersListListIIStack();
//        System.out.println("\n usingDiff :" + Printer.print(usingDiff.addTwoNumbers(l1, l2)));
        System.out.println("\n Stack :" + GenericPrinter.print(stack.addTwoNumbers(l1, l2)));
    }


}

class AddTwoNumbersListListIIStack {
    /**
     * Algo 1:
     * 1. Push all the nodes in stack of both list in different stack
     * 2. use stack top's as list node from end
     * 3. apply same as {@link AddTwoNumbersListI}
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;

        if (l2 == null)
            return l1;

        ListNode current = null;

        Stack<ListNode> s1 = new Stack<>();
        Stack<ListNode> s2 = new Stack<>();

        //1. Push all the nodes in stack of both list in different stack
        while (l1 != null || l2 != null) {

            if (l1 != null) {
                s1.push(l1);
                l1 = l1.next;
            }

            if (l2 != null) {
                s2.push(l2);
                l2 = l2.next;
            }

        }

        int carry = 0;
        while (!s1.isEmpty() || !s2.isEmpty()) {

            int x = s1.isEmpty() ? 0 : s1.pop().val;
            int y = s2.isEmpty() ? 0 : s2.pop().val;

            int sum = x + y + carry;


            if (sum >= 10) {
                carry = 1;
                sum -= 10;
            } else
                carry = 0;

            ListNode n = new ListNode(sum);
            n.next = current;
            current = n;
        }
        if (carry > 0) {
            ListNode n = new ListNode(carry);
            n.next = current;
            current = n;
        }

        return current;
    }

}




/**
 * Runtime: 3 ms, faster than 68.75% of Java online submissions for Add Two Numbers II.
 * Memory Usage: 45.4 MB, less than 64.71% of Java online submissions for Add Two Numbers II.
 */
class AddTwoNumbersListListIIUsingDiff {
    /**
     * Algo 1:
     * 1. Find the length of both linked list.
     * 2. If length are same, then use {@link AddTwoNumbersListI} but using recursion so that you need to reverse the linked list
     * 3. if length are not same, then move bigger list ahead for the difference, and apply step 2.
     * 4. if carry exist, then propagate carry
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        if (l1 == null)
            return l2;

        if (l2 == null)
            return l1;

        //1. Find the length of both linked list.
        int size[] = size(l1, l2);

        //Keep the head of bigger list
        ListNode head = null;
        ListNode tail = null;

        //3. if length are not same, then move bigger list ahead for the difference, and apply step 2.
        if (size[0] > size[1]) {

            head = l1;
            int d = size[0] - size[1];
            while (d-- > 0) {
                tail = l1;
                l1 = l1.next;
            }
            tail.next = null;

        } else if (size[0] < size[1]) {

            head = l2;
            int d = size[1] - size[0];
            while (d-- > 0) {
                tail = l2;
                l2 = l2.next;
            }
            tail.next = null;
        }


        final ListNode[] current = {null};

        //2. If length are same, then use {@link AddTwoNumbersListI} but using recursion so that you need to reverse the linked list
        int carry = addTwoNumbersSame(l1, l2, current);


        //if size were same, then add this carry node only
        if (size[0] != size[1]) {
            int c[] = {carry};
            propagateCarry(head, c, current);
            carry = c[0];
        }

        if (carry > 0) {
            ListNode node = new ListNode(carry);
            node.next = current[0];
            current[0] = node;
        }
        return current[0];
    }

    private void propagateCarry(ListNode head, int[] carry, ListNode[] current) {

        if (head == null)
            return;

        propagateCarry(head.next, carry, current);


        int x = head.val;
        int sum = x + carry[0];

        if (sum >= 10) {
            carry[0] = 1;
            sum -= 10;
        } else
            carry[0] = 0;
        ListNode n = new ListNode(sum);
        n.next = current[0];
        current[0] = n;

    }


    private int addTwoNumbersSame(ListNode l1, ListNode l2, ListNode[] current) {

        if (l1 == null || l2 == null)
            return 0;

        int carry = addTwoNumbersSame(l1.next, l2.next, current);

        int sum = l1.val + l2.val + carry;

        if (sum >= 10) {
            carry = 1;
            sum -= 10;
        } else
            carry = 0;

        if (current[0] == null) {
            current[0] = new ListNode(sum);
        } else {
            ListNode n = new ListNode(sum);
            n.next = current[0];
            current[0] = n;
        }

        return carry;

    }

    private int[] size(ListNode l1, ListNode l2) {
        int l1Size = 0;
        int l2Size = 0;

        while (l1 != null || l2 != null) {

            if (l1 != null) {
                l1Size++;
                l1 = l1.next;
            }

            if (l2 != null) {
                l2Size++;
                l2 = l2.next;
            }
        }

        return new int[]{l1Size, l2Size};
    }
}