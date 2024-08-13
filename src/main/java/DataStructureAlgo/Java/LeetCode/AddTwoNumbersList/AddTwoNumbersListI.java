package DataStructureAlgo.Java.LeetCode.AddTwoNumbersList;

import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.ListBuilder;
import  DataStructureAlgo.Java.helpers.templates.ListNode;

/**
 * Author: Nitin Gupta
 * Date: 2019-09-11
 * Description: https://leetcode.com/problems/add-two-numbers/
 * 2. Add Two Numbers
 * <p>
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * Example:
 * <p>
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 */
public class AddTwoNumbersListI {

    public static void main(String[] args) {
        test(ListBuilder.arrayToSinglyList(new Integer[]{9}), ListBuilder.arrayToSinglyList(new Integer[]{9, 9, 9}), ListBuilder.arrayToSinglyList(new Integer[]{8, 0, 0, 1}));
        test(ListBuilder.arrayToSinglyList(new Integer[]{2, 4, 3}), ListBuilder.arrayToSinglyList(new Integer[]{5, 6, 4}), ListBuilder.arrayToSinglyList(new Integer[]{7, 0, 8}));
        test(ListBuilder.arrayToSinglyList(new Integer[]{9, 9, 9}), ListBuilder.arrayToSinglyList(new Integer[]{9, 9, 9}), ListBuilder.arrayToSinglyList(new Integer[]{8, 9, 9, 1}));
        test(ListBuilder.arrayToSinglyList(new Integer[]{9, 9, 9}), ListBuilder.arrayToSinglyList(new Integer[]{9, 9}), ListBuilder.arrayToSinglyList(new Integer[]{8, 9, 0, 1}));
        test(ListBuilder.arrayToSinglyList(new Integer[]{9, 9, 9}), ListBuilder.arrayToSinglyList(new Integer[]{9, 9, 9, 9}), ListBuilder.arrayToSinglyList(new Integer[]{8, 9, 9, 0, 1}));
    }

    private static void test(ListNode l1, ListNode l2, ListNode expected) {
        System.out.println("\n L1 :" + CommonMethods.print(l1) + "\n L2 :" + CommonMethods.print(l2) + "\n Expected :" + CommonMethods.print(expected));
        System.out.println("\n Obtained :" + CommonMethods.print(addTwoNumbers(l1, l2)));
    }


    /**
     * Runtime: 2 ms, faster than 77.67% of Java online submissions for Add Two Numbers.
     * Memory Usage: 44.2 MB, less than 86.83% of Java online submissions for Add Two Numbers.
     *
     * @param l1
     * @param l2
     * @return
     */

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        if (l1 == null)
            return l2;

        if (l2 == null)
            return l1;

        int carry = 0;
        ListNode sumHead = new ListNode(-1);
        ListNode current = sumHead;

        while (l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;

            int sum = x + y + carry;


            if (sum >= 10) {
                carry = 1;
                sum -= 10;
            }else
                carry = 0;

            current.next = new ListNode(sum);
            current = current.next;

            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        if (carry != 0)
            current.next = new ListNode(carry);
        return sumHead.next;
    }


}
