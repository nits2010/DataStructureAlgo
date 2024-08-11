package DataStructureAlgo.Java.LeetCode2025.medium.List;

import DataStructureAlgo.Java.LeetCode.listToBST.ListBuilder;
import DataStructureAlgo.Java.LeetCode.templates.ListNode;
import DataStructureAlgo.Java.helpers.GenericPrinter;

/**
 * Author: Nitin Gupta
 * Date: 7/31/2024
 * Question Category: 61. Rotate List [medium]
 * Description: https://leetcode.com/problems/rotate-list
 * <p>
 *Given the head of a linked list, rotate the list to the right by k places.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [4,5,1,2,3]
 * Example 2:
 *
 *
 * Input: head = [0,1,2], k = 4
 * Output: [2,0,1]
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [0, 500].
 * -100 <= Node.val <= 100
 * 0 <= k <= 2 * 109
 * <p>
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link RemoveNthNodeFromEndOfList_19}
 * extension {@link }
 * <p>
 *
 * Tags
 * -----
 * @medium
 * @LinkedList
 * @TwoPointers
 *
 * <p>
 * Company Tags
 * -----
 * @Microsoft
 * @Bloomberg
 */
public class RotateList_61 {

    public static void main(String[] args) {
        boolean testResult = true;
        testResult &= test(new Integer[]{1,2,3,4,5}, 2, new Integer[]{4,5,1,2,3});
        testResult &= test(new Integer[]{0,1,2}, 4, new Integer[]{2,0,1});
        System.out.println("\nTest result = " + (testResult ? "Pass" : "Fail"));
    }

    private static boolean test(Integer[] input, int k, Integer[] expected) {
        ListNode inputList = ListBuilder.arrayToSinglyList(input);
        ListNode expectedList = ListBuilder.arrayToSinglyList(expected);
        System.out.println("\n Input :" + GenericPrinter.print(inputList) + " k = " + k + "\nexpected :" + GenericPrinter.print(expectedList));

        RotateList.Solution solution = new RotateList.Solution();
        ListNode output = solution.rotateRight(inputList, k);
        boolean result = GenericPrinter.equalsValues(expectedList, output);
        System.out.println(" Output :" + GenericPrinter.print(output) + " Result match : " + result);

        return result;
    }
}

/**
 * this is similar to finding the kth element from the end of linked list. once found, then simply detach the list post kth element and attach in front.
 * 1. Find previous of kth element from end of list
 * 2. detach list post using kth element onwards.
 * 3. Get the last element of kth element list and attach that list to front.
 *
 * if k is greater than the length list, then it means that multiple of n (length of list), the list will come to its original shape.
 * hence we can take k = k%length of list
 *
 * O(n) / O(1)
 */
class RotateList{
    static class Solution {
        public ListNode rotateRight(ListNode head, int k) {

            if(head == null || k ==0)
                return head;

            int length = length(head);
            k = k%length;
            if(k == 0) //list will come its original shape, no need to rotate
                return head;

            ListNode prevKthNodeFromLast = kthNodeFromLastLinkedList(head, k);
            if(prevKthNodeFromLast == null) //k is greater than length of list
                return null;

            ListNode kthNode = prevKthNodeFromLast.next;
            prevKthNodeFromLast.next = null;

            ListNode temp = kthNode;
            while(temp.next!=null)
                temp = temp.next;

            temp.next = head;
            return kthNode;

        }

        private int length(ListNode head) {
            int length = 0;
            ListNode temp = head;
            while (temp!=null){
                temp = temp.next;
                length++;
            }
            return length;
        }



        /**
         * nthNodeFromLastLinkedList
         * @param head
         * @param k
         * @return

         */

        private ListNode kthNodeFromLastLinkedList(ListNode head, int k) {
            ListNode fast = head;
            ListNode slow = head;
            ListNode prevOfKthNodeFromLast = head;

            //reach kth node

            while (fast!=null && k>0){
                fast = fast.next;
                k--;
            }
            if(k>0) //if k is greater than length of list
                return null;

            //run one by one until fast reaches null, that time slow is kth node from the end.
            while(fast!=null) {
                fast = fast.next;
                prevOfKthNodeFromLast = slow;
                slow = slow.next;
            }
            return prevOfKthNodeFromLast;
        }
    }

}

class KthNodeFromEndOfList {
    /**
     * nthNodeFromLastLinkedList
     * @param head
     * @param k
     * @return
     */


    private ListNode kthNodeFromLastLinkedList(ListNode head, int k) {
        ListNode fast = head;
        ListNode slow = head;

        //reach kth node

        while (fast!=null && k>0){
            fast = fast.next;
            k--;
        }
        if(k>0) //if k is greater than length of list
            return null;

        //run one by one until fast reaches null, that time slow is kth node from the end.
        while(fast!=null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}