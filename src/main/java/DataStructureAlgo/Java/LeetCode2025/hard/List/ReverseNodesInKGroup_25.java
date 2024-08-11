package DataStructureAlgo.Java.LeetCode2025.hard.List;

import DataStructureAlgo.Java.helpers.ListBuilder;
import DataStructureAlgo.Java.LeetCode.templates.ListNode;
import DataStructureAlgo.Java.LeetCode2025.medium.List.SwapNodesInPairs_24;
import DataStructureAlgo.Java.helpers.GenericPrinter;

/**
 * Author: Nitin Gupta
 * Date: 7/28/2024
 * Question Category:  25. Reverse Nodes in k-Group [hard]
 * Description: https://leetcode.com/problems/reverse-nodes-in-k-group/description/
 *
 * <p>
 *Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.
 *
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
 *
 * You may not alter the values in the list's nodes, only nodes themselves may be changed.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [2,1,4,3,5]
 * Example 2:
 *
 *
 * Input: head = [1,2,3,4,5], k = 3
 * Output: [3,2,1,4,5]
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is n.
 * 1 <= k <= n <= 5000
 * 0 <= Node.val <= 1000
 *
 *
 * Follow-up: Can you solve the problem in O(1) extra memory space?
 * <p>
 *
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link SwapNodesInPairs_24}
 * extension {@link SwapNodesInPairs_24}
 * <p>
 * Tags
 * -----
 * @hard
 * @LinkedList
 * @Recursion
 *
 * <p>
 * Company Tags
 * -----
 *
 * @Editorial <a href="https://leetcode.com/problems/reverse-nodes-in-k-group/solutions/5553863/easy-pissy-chissy-super-tizy-to-understand/">...</a>
 */
public class ReverseNodesInKGroup_25 {

    public static void main(String[] args) {
        boolean testResult = test(new Integer[]{1, 2, 3, 4, 5}, 2,new Integer[]{2, 1, 4, 3, 5});
        testResult&=test(new Integer[]{1, 2, 3, 4, 5,6,7,8,9,10}, 3, new Integer[]{3, 2, 1, 6, 5, 4, 9, 8, 7, 10});
        testResult&=test(new Integer[]{1, 2, 3, 4}, 2, new Integer[]{2, 1, 4, 3});
        testResult&=test(new Integer[]{1}, 1, new Integer[]{1});
        testResult&=test(new Integer[]{1, 2}, 1, new Integer[]{1,2});
        testResult&=test(new Integer[]{1, 2, 3, 4, 5,6,7,8,9,10}, 0, new Integer[]{1, 2, 3, 4, 5,6,7,8,9,10});
        testResult&=test(new Integer[]{1, 2, 3, 4, 5,6,7,8,9,10}, 10, new Integer[]{10 , 9 , 8 , 7 , 6 , 5, 4, 3, 2, 1});
        System.out.println("\nTest result = "+(testResult?"Pass":"Fail"));
    }


    private static boolean test(Integer[] elements,int k, Integer[] expected ) {
        ListNode originalList = ListBuilder.arrayToSinglyList(elements);
        ListNode expectedList = ListBuilder.arrayToSinglyList(expected);

        System.out.println("\nInput :"+ GenericPrinter.print(originalList) + " k="+k + "\nexpected :" + GenericPrinter.print(expectedList));

        Solution solution = new Solution();
        ListNode output =  solution.reverseKGroup(originalList, k);
        boolean result ;
        System.out.println("Output :"+GenericPrinter.print(output) + "\nResult match : "+(result= GenericPrinter.equalsValues(expectedList,output)));
        return result;




    }

    static class Solution {

        public ListNode reverseKGroup(ListNode head, int k) {
//            return reverseKGroupRecursive(head,k);

                return reverseKGroupIterative(head,k);
        }
        /**
         * O(n) / O(n/k)
         * https://leetcode.com/problems/reverse-nodes-in-k-group/submissions/1337363962/
         * 0 ms
         * Beats 100.00%
         * @param head
         * @param k
         * @return
         */
        public ListNode reverseKGroupRecursive(ListNode head, int k) {
            if (head == null || k <=0)
                return head;

            ListNode tail = getKNodes(head, k); //O(k)

            //if there is not enough nodes
            if(tail == null)
                return head; //this list is over for work

            //make two different list
            ListNode nextGroup = tail.next;
            tail.next = null;

            tail = reverse(head); //O(k)

            //do same for next group and attach them back, post reverse, head will be tail and vice versa in reverse list
            head.next = reverseKGroup(nextGroup, k);

            return tail; // post reversal, tail will be our new head; assume there are only k nodes.
        }


        private ListNode getKNodes(ListNode head, int k) {
            k = k-1; //head is counted as one
            while (head != null && k>0){
                head = head.next;
                k--;
            }
            return head;
        }

        /**
         *
         * {@link DataStructureAlgo.Java.LeetCode.ReverseSinglyLinkedList}
         *  * // 1-2-3-4; n_1 = 2-3-4 ; head = 1
         *      * //  2-3-4 ; n_1 = 3-4 ; head = 2
         *      * // 3-4; n_1 = 4; head= 3
         *      * // 4 -> return ; newHead = 4, 4->3
         *      * // 4-3-2; newHead = 4
         *      * // 4-3-2-1, newHEad = 4
         * @param head
         * @return
         */
        private ListNode reverse(ListNode head){

            if(head == null || head.next == null)
                return head;

            ListNode n_1_list = head.next;
            head.next = null;
            ListNode newHead = reverse(n_1_list);
            n_1_list.next = head;
            return newHead;
        }

        /** O(n) / O(1)
         * https://leetcode.com/problems/reverse-nodes-in-k-group/submissions/1337413111/
         * 0 ms
         * Beats 100.00%
         * @param head
         * @param k
         * @return
         */
        public ListNode reverseKGroupIterative(ListNode head, int k) {

            if (head == null || k <= 0)
                return head;

            ListNode finalHead = null;


            ListNode prevGroupTail = null;
            ListNode currentGroupHead = head;
            ListNode currentGroupTail;
            ListNode nextGroup;


            while(true){

                currentGroupTail = getKNodes(currentGroupHead, k); //O(k)


                //if there is not enough nodes
                if (currentGroupTail == null) {
                   break;
                }

                //if this is the first group that is being under consideration, then update final head up-hand.
                if(finalHead == null)
                    finalHead=currentGroupTail;

                //take next group to work on-before
                nextGroup = currentGroupTail.next;

                //make this group solo, between currentGroupHead to currentGroupTail
                currentGroupTail.next = null;
                if(prevGroupTail!=null) //if this is the first group, the prevGroupTail must be null
                    prevGroupTail.next = null;

                currentGroupTail = currentGroupHead ; //post reversal, they will swap position, hence save now
                currentGroupHead = reverse(currentGroupHead);

                //join back
                if(prevGroupTail!=null)
                    prevGroupTail.next = currentGroupHead;

                currentGroupTail.next = nextGroup;

                //prepare for next group
                prevGroupTail = currentGroupTail;
                currentGroupHead = nextGroup;


            }


            return finalHead;


        }


    }
}
