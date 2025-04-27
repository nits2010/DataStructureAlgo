package DataStructureAlgo.Java.LeetCode2025.ProblemSet.List._148;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.templates.ListNode;


/**
 * Author: Nitin Gupta
 * Date: 4/19/2025
 * Question Title: 148. Sort List
 * Link: https://leetcode.com/problems/sort-list/description/
 * Description: Given the head of a linked list, return the list after sorting it in ascending order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [4,2,1,3]
 * Output: [1,2,3,4]
 * Example 2:
 * <p>
 * <p>
 * Input: head = [-1,5,3,4,0]
 * Output: [-1,0,3,4,5]
 * Example 3:
 * <p>
 * Input: head = []
 * Output: []
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @LinkedList
 * @TwoPointers
 * @DivideandConquer
 * @Sorting
 * @MergeSort <p><p>
 * Company Tags
 * -----
 * @Facebook
 * @Microsoft
 * @Amazon
 * @ByteDance
 * @Adobe
 * @Baidu
 * @Google
 * @VMware <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class SortList_148 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new Integer[]{4, 2, 1, 3}, new Integer[]{1, 2, 3, 4}));
        tests.add(test(new Integer[]{-1, 5, 3, 4, 0}, new Integer[]{-1, 0, 3, 4, 5}));
        tests.add(test(new Integer[]{}, new Integer[]{}));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(Integer[] nodes, Integer[] expected) {

        CommonMethods.printTest(new String[]{"nodes", "Expected"}, true, nodes, expected);
        ListNode head = ListBuilder.arrayToSinglyList(nodes);
        ListNode expectedList = ListBuilder.arrayToSinglyList(expected);

        ListNode output;
        boolean pass, finalPass = true;

        Solution solution = new Solution();
        output = solution.sortList(head);
        pass = CommonMethods.compareResultOutCome(output, expectedList, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class Solution {
        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null)
                return head;

            ListNode middle = middle(head);

            //detach both lists
            ListNode right = middle.next;
            middle.next = null;

            ListNode leftList = sortList(head);
            ListNode rightList = sortList(right);
            return mergeIterative(leftList, rightList);

        }

        private ListNode middle(ListNode head) {

            ListNode slow = head;
            ListNode fast = head.next;

            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }
            return slow;
        }

        private ListNode merge(ListNode a, ListNode b) {
            if (a == null)
                return b;

            if (b == null)
                return a;

            ListNode result;
            if (a.val <= b.val) {
                result = a;
                result.next = merge(a.next, b);
            } else {
                result = b;
                result.next = merge(a, b.next);
            }
            return result;
        }

        private ListNode mergeIterative(ListNode a, ListNode b) {
            if (a == null)
                return b;

            if (b == null)
                return a;

            ListNode dummy = new ListNode(10000000);
            ListNode current = dummy;

            while (a != null && b != null) {

                if (a.val <= b.val) {
                    current.next = a;
                    a = a.next;
                } else {
                    current.next = b;
                    b = b.next;
                }
                current = current.next;
            }

            current.next = a == null ? b : a;
            return dummy.next;
        }
    }

}
