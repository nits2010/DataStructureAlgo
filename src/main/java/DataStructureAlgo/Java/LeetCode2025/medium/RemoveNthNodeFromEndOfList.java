package DataStructureAlgo.Java.LeetCode2025.medium;

import DataStructureAlgo.Java.LeetCode.listToBST.ListBuilder;
import DataStructureAlgo.Java.LeetCode.templates.ListNode;
import DataStructureAlgo.Java.helpers.GenericPrinter;

/**
 * Author: Nitin Gupta
 * Date: 2024-07-28
 * Description: https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/
 * 19. Remove Nth Node From End of List [medium]
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 * Example 2:
 * <p>
 * Input: head = [1], n = 1
 * Output: []
 * Example 3:
 * <p>
 * Input: head = [1,2], n = 1
 * Output: [1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is sz.
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.RemoveNthNodeFromEnd}
 * Extension of {@link SwappingNodesInALinkedList.Solution #kthNodeFromEndFromLinkedList}
 */
public class RemoveNthNodeFromEndOfList {

    public static void main(String[] args) {
        boolean testResult = true;
        testResult &= test(new Integer[]{1, 2, 3, 4, 5}, 2, new Integer[]{1, 2, 3, 5});
        testResult &= test(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 5, new Integer[]{1, 2, 3, 4, 5, 7, 8, 9, 10});
        testResult &= test(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 1, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        testResult &= test(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 11, null);
        testResult &= test(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 0, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        testResult &= test(new Integer[]{1}, 1, new Integer[]{});

        System.out.println("\nTest result =" + (testResult ? "Pass" : "Fail"));

    }


    private static boolean test(Integer[] elements, int k, Integer[] expected) {
        ListNode originalList = ListBuilder.arrayToSinglyList(elements);
        ListNode expectedList = ListBuilder.arrayToSinglyList(expected);
        System.out.println("\n Input :" + GenericPrinter.print(originalList) + " k = " + k + "\nexpected :" + GenericPrinter.print(expectedList));

        Solution solution = new Solution();
        ListNode output = solution.removeNthFromEnd(originalList, k);
        boolean result = false;
        System.out.println(" Output :" + GenericPrinter.print(output) + " Result match : " + (result = GenericPrinter.equalsValues(expectedList, output)));
        return result;


    }

    static class Solution {
        /**
         * O(n)/O(1)
         * https://leetcode.com/problems/remove-nth-node-from-end-of-list/submissions/1336195096/
         * @param head
         * @param k
         * @return
         */
        public ListNode removeNthFromEnd(ListNode head, int k) {
            if (head == null || k <= 0)
                return head;

            k = k - 1; //first node counted


            ListNode kthFromEnd = head;
            ListNode prev = head;
            while (kthFromEnd != null && k > 0) {
                prev = kthFromEnd;
                kthFromEnd = kthFromEnd.next;
                k--;
            }

            //if k > listSize
            if (kthFromEnd == null && k == 0)
                return null;

            //if head node itself is kthnodefromEnd.{only 1 node in list and k =1 }
            if (kthFromEnd == null || kthFromEnd.next == null)
                return head.next;


            //get kth node from end
            ListNode temp = head;
            while (kthFromEnd.next != null) {
                prev = temp;
                temp = temp.next;
                kthFromEnd = kthFromEnd.next;
            }
            kthFromEnd = temp;

            //if we went beyond list
            if (kthFromEnd == null)
                return null;

            ListNode next = kthFromEnd.next;

            //detach
            kthFromEnd.next = null;
            prev.next = next;

            return head;
        }
    }
}
