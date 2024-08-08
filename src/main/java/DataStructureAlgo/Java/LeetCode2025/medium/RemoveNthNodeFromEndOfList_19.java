package DataStructureAlgo.Java.LeetCode2025.medium;

import DataStructureAlgo.Java.LeetCode.listToBST.ListBuilder;
import DataStructureAlgo.Java.LeetCode.templates.ListNode;
import DataStructureAlgo.Java.helpers.GenericPrinter;

/**
 * Author: Nitin Gupta
 * Date:  2024-07-28
 * Question Category: 19. Remove Nth Node From End of List [ medium ]
 * Description:https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/
 *
 * <p>
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
 * <p>
 * File reference
 * -----------
 *
 * Duplicate  {@link DataStructureAlgo.Java.LeetCode.RemoveNthNodeFromEnd}
 * Similar {@link}
 * extension {@link SwappingNodesInALinkedList_1721 #kthNodeFromEndFromLinkedList}
 *
 * Tags
 * -----
 * @medium
 *
 * Company Tags
 * -----
 *
 * @Editorial <a href="https://leetcode.com/problems/remove-nth-node-from-end-of-list/solutions/5565232/multiple-solutions-easy-to-understand/">...</a>
 */

public class RemoveNthNodeFromEndOfList_19 {

    public static void main(String[] args) {
        boolean testResult = true;
        testResult &= test(new Integer[]{1, 2, 3, 4, 5}, 2, new Integer[]{1, 2, 3, 5});
        testResult &= test(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 5, new Integer[]{1, 2, 3, 4, 5, 7, 8, 9, 10});
        testResult &= test(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 1, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        testResult &= test(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 11, null);
        testResult &= test(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 0, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        testResult &= test(new Integer[]{1}, 1, new Integer[]{});
        testResult &= test(new Integer[]{1,2}, 2, new Integer[]{2});
        testResult &= test(new Integer[]{1,2}, 1, new Integer[]{1});

        System.out.println("\nTest result =" + (testResult ? "Pass" : "Fail"));

    }


    private static boolean test(Integer[] elements, int k, Integer[] expected) {
        ListNode originalList = ListBuilder.arrayToSinglyList(elements);
        ListNode expectedList = ListBuilder.arrayToSinglyList(expected);
        System.out.println("\n Input :" + GenericPrinter.print(originalList) + " k = " + k + "\nexpected :" + GenericPrinter.print(expectedList));

        RemoveNthNodeFromEndOfListSimplified.Solution solution = new RemoveNthNodeFromEndOfListSimplified.Solution();
        ListNode output = solution.removeNthFromEnd(originalList, k);
        boolean result = false;
        System.out.println(" Output :" + GenericPrinter.print(output) + " Result match : " + (result = GenericPrinter.equalsValues(expectedList, output)));
        return result;


    }
}
class RemoveNthNodeFromEndOfList{

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
            while (kthFromEnd != null && k > 0) {
                kthFromEnd = kthFromEnd.next;
                k--;
            }

            //if k > listSize
            if (kthFromEnd == null && k == 0)
                return null;

            //if head node itself is kthFromEnd.{k = n }
            if (kthFromEnd == null || kthFromEnd.next == null)
                return head.next;


            //get kth node from end
            ListNode temp = head;
            ListNode prev = null;
            while (kthFromEnd.next != null) {
                prev = temp;
                temp = temp.next;
                kthFromEnd = kthFromEnd.next;
            }
            kthFromEnd = temp;

            //attach
            prev.next =  kthFromEnd.next;

            //detach
            kthFromEnd.next = null;



            return head;
        }

    }
}

class RemoveNthNodeFromEndOfListSimplified {

    static class Solution {

        public ListNode removeNthFromEnd(ListNode head, int k) {

            if (head == null || k <= 0)
                return head;

            //if one node in list and k = 1
            if(head.next == null && k == 1)
                return null;

            ListNode prevOfKthNodeFromTheEndOfLinkedList = prevOfKthNodeFromTheEndOfLinkedList(head, k);


            if(prevOfKthNodeFromTheEndOfLinkedList == null) // means k is multiple of list length, k = n
                return head.next;// as kth element from the end will be head itself
            // and prev to head is null, since we need to delete kth element, hence sending head back will delete it

            if(prevOfKthNodeFromTheEndOfLinkedList.val == -1) // k was greater than list length
                return null;

            prevOfKthNodeFromTheEndOfLinkedList.next = prevOfKthNodeFromTheEndOfLinkedList.next.next;
            return head;

        }
        private ListNode prevOfKthNodeFromTheEndOfLinkedList(ListNode head, int k) {
            ListNode fast = head;
            ListNode slow = head;

            while(fast!=null && k>0){
                fast = fast.next;
                k--;
            }

            if(k>0)
                return new ListNode(-1);

//            if(k == 0 && fast == null) { // k was equal to list length
//                return null; // as kth element from the end will be head itself
//                // and prev to head is null, since we need to delete kth element, hence sending head back will delete it
//            }
            ListNode prev = null;
            while(fast!=null){
                fast = fast.next;
                prev = slow;
                slow = slow.next;
            }
            return prev;
        }
    }
}

