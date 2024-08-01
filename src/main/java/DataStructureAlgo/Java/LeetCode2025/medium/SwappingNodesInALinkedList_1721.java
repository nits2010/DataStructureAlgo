package DataStructureAlgo.Java.LeetCode2025.medium;

import DataStructureAlgo.Java.LeetCode.listToBST.ListBuilder;
import DataStructureAlgo.Java.LeetCode.templates.ListNode;
import DataStructureAlgo.Java.helpers.GenericPrinter;


/**
 * Author: Nitin Gupta
 * Date: 7/31/2024
 * Question Category: 1721. Swapping Nodes in a Linked List [medium]
 * Description: https://leetcode.com/problems/swapping-nodes-in-a-linked-list
 *
 * You are given the head of a linked list, and an integer k.
 *
 * Return the head of the linked list after swapping the values of the kth node from the beginning and the kth node from the end (the list is 1-indexed).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [1,4,3,2,5]
 * Example 2:
 *
 * Input: head = [7,9,6,6,7,8,3,0,9,5], k = 5
 * Output: [7,9,6,6,8,7,3,0,9,5]
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is n.
 * 1 <= k <= n <= 105
 * 0 <= Node.val <= 100
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
 * @medium
 * @LinkedList
 * @TwoPointers
 *
 *
 * <p>
 * Company Tags
 * -----
 * @Amazon
 * @Facebook
 * @Bloomberg

 *
 * @Editorial
 */

public class SwappingNodesInALinkedList_1721 {

    public static void main(String[] args) {
       boolean testResult = true;
        testResult &= test(new Integer[]{1, 2, 3, 4, 5}, 2, new Integer[]{1, 4, 3, 2, 5});
        testResult &=test(new Integer[]{1, 2, 3, 4, 5,6,7,8,9,10}, 5, new Integer[]{1, 2, 3, 4, 6,5,7,8,9,10});
        testResult &=test(new Integer[]{1, 2, 3, 4, 5,6,7,8,9,10}, 1, new Integer[]{10, 2, 3, 4, 5,6,7,8,9,1});
        testResult &=test(new Integer[]{1, 2, 3, 4, 5,6,7,8,9,10}, 11, null);
        testResult &= test(new Integer[]{1, 2, 3, 4, 5,6,7,8,9,10}, 0, new Integer[]{1, 2, 3, 4, 5,6,7,8,9,10});

        System.out.println("\nTest result ="+(testResult?"Pass":"Fail"));

    }


    private static boolean test(Integer[] elements,int k, Integer[] expected ) {
        ListNode originalList = ListBuilder.arrayToSinglyList(elements);
        ListNode expectedList = ListBuilder.arrayToSinglyList(expected);
        System.out.println("\n Input :"+ GenericPrinter.print(originalList) + " k = " + k + "\nexpected :" + GenericPrinter.print(expectedList));

        Solution solution = new Solution();
        ListNode output =  solution.swapNodes(originalList, k);
        boolean result = false;
        System.out.println(" Output :"+GenericPrinter.print(output) + " Result match : "+(result = GenericPrinter.equalsValues(expectedList,output)));
        return result;



    }
 static class Solution {
        public ListNode swapNodes(ListNode head, int k) {
            return swapNodes1Scans(head,k);

        }

     /**
      * https://leetcode.com/problems/swapping-nodes-in-a-linked-list/submissions/1336171568/
      * O(n)/O(1)
      * @param head
      * @param k
      * @return
      */
     public ListNode swapNodes1Scans(ListNode head, int k) {
         if(head == null || k <= 0)
             return head;

         k = k-1;  //first node counted


         ListNode kthFromEnd = head;
         while(kthFromEnd!=null && k>0){
             kthFromEnd = kthFromEnd.next;
             k--;
         }
         if(kthFromEnd == null)
             return null;

         ListNode kthFromFirst = kthFromEnd; //at this point kthFromEnd is sitting at kth node from start

         //get prev of kth node from end
         ListNode temp = head;
         while (kthFromEnd.next != null){
             temp = temp.next;
             kthFromEnd = kthFromEnd.next;
         }
         kthFromEnd = temp;

         int tempV = kthFromEnd.val;
         kthFromEnd.val = kthFromFirst.val;
         kthFromFirst.val = tempV;

         return head;
     }

     /**
      * O(2n)/O(1)
      * https://leetcode.com/problems/swapping-nodes-in-a-linked-list/submissions/1336161818/
      * @param head
      * @param k
      * @return
      */
        public ListNode swapNodes2Scans(ListNode head, int k) {

            if(head == null || k <= 0)
                return head;

            final ListNode kthNodeFromEnd = prevOfKthNodeFromEndFromLinkedList(head, k-1);
            final ListNode kthNodeFromStart  = kthNodeFromStartFromLinkedList(head,k-1); // n-k=1 is from end

            if(kthNodeFromEnd == null || kthNodeFromStart == null)
                return null;

            //swap values
            int temp = kthNodeFromEnd.val;
            kthNodeFromEnd.val = kthNodeFromStart.val;
            kthNodeFromStart.val = temp;

            return head;

        }

        private ListNode kthNodeFromStartFromLinkedList(ListNode head, int k) {
            while( head!=null && k>0){
                head = head.next;
                k--;
            }

            return head;

        }

        /**
         * two pointers, one fast, jump k node once and other is slow pointer. Both start from head.
         * Once fast pointer jump kth node, start one by one both pointer until kthPointer reach end.
         * nthNodeFromEndFromLinkedList
         * @param head
         * @param k
         * @return
         */
        private ListNode prevOfKthNodeFromEndFromLinkedList(ListNode head, int k) {
            ListNode kth = head;
            while(kth!=null && k>0){
                kth = kth.next;
                k--;
            }

            if(kth == null && k>0)
                return null;

            while (kth!=null && kth.next!=null){
                head = head.next;
                kth = kth.next;
            }
            return head;
        }
    }
}
