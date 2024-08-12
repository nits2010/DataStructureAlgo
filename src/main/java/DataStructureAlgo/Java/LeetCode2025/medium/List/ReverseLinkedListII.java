package DataStructureAlgo.Java.LeetCode2025.medium.List;

import DataStructureAlgo.Java.LeetCode.ReverseLinkedList;
import DataStructureAlgo.Java.helpers.ListBuilder;
import DataStructureAlgo.Java.helpers.templates.ListNode;
import DataStructureAlgo.Java.helpers.GenericPrinter;



/**
 * Author: Nitin Gupta
 * Date: 7/31/2024
 * Question Category: 92. Reverse Linked List II [ medium ]
 * Description: https://leetcode.com/problems/reverse-linked-list-ii
 * Given the head of a singly linked list and two integers left and right where left <= right,
 * reverse the nodes of the list from position left to position right, and return the reversed list.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [1,2,3,4,5], left = 2, right = 4
 * Output: [1,4,3,2,5]
 * Example 2:
 * <p>
 * Input: head = [5], left = 1, right = 1
 * Output: [5]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is n.
 * 1 <= n <= 500
 * -500 <= Node.val <= 500
 * 1 <= left <= right <= n
 * <p>
 * <p>
 * Follow up: Could you do it in one pass?
 * <p>
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link ReverseLinkedList}
 * extension {@link }
 * <p>
 * Tags
 * -----
 * @medium
 * @LinkedList
 * <p>
 * Company Tags
 * -----
 * @Facebook
 * @Microsoft
 * @Amazon
 * @Google
 * @Apple

 *
 * @Editorial https://leetcode.com/problems/reverse-linked-list-ii/solutions/5554807/just-use-reverselist-as-subfunction
 */

public class ReverseLinkedListII {

    public static void main(String[] args) {
        boolean testResult = test(new Integer[]{1, 2, 3, 4, 5}, 2, 4, new Integer[]{1, 4, 3, 2, 5});
        testResult &= test(new Integer[]{5}, 1, 1, new Integer[]{5});
        testResult &=test(new Integer[]{1, 2, 3, 4, 5}, 1, 5, new Integer[]{5,4,3,2,1});
        testResult &=test(new Integer[]{1, 2, 3, 4, 5}, 1, 6, null);

        System.out.println("\nJob Test result = " + (testResult ? "Pass" : "Fail"));
    }


    private static boolean test(Integer[] elements, int left, int right, Integer[] expected) {
        ListNode originalList = ListBuilder.arrayToSinglyList(elements);
        ListNode expectedList = ListBuilder.arrayToSinglyList(expected);

        System.out.println("\nInput :" + GenericPrinter.print(originalList) + " left=" + left + " right =" + right + "\nexpected :" + GenericPrinter.print(expectedList));

        Solution solution = new Solution();
        ListNode output = solution.reverseBetween(originalList, left, right);
        boolean result = GenericPrinter.equalsValues(expectedList, output);
        System.out.println("Output :" + GenericPrinter.print(output) + "\nResult match : " +  (result ? "Pass" : "Fail"));
        return result;


    }

    static class Solution {
        public ListNode reverseBetween(ListNode head, int left, int right) {
            return reverseBetween1Scans(head,left,right);
        }


        /**
         * 1. get holding on left node & prevLeft  - 1/2 scan
         * 2. From leftNode onwards start reversing till right and attach list - 1/2 scan
         *
         * @param head
         * @param left
         * @param right
         * @return
         */
        private ListNode reverseBetween1Scans(ListNode head, int left, int right) {
            if (head == null || head.next == null || left >= right)
                return head;

            ListNode reverseHead, prevLeft = null;

            ListNode temp = head;
            int tempLeft = left - 1;//head counted as 1
            while (tempLeft > 0 && temp != null) {
                prevLeft = temp;
                temp = temp.next;
                tempLeft--;
            }
            reverseHead = temp;

            if (temp == null) //if left > n
                return head;

            //detach list - not needed
            if (prevLeft != null)
                prevLeft.next = null;

            //now reverse all nodes b/w left and right
            int runner = left;

            ListNode prev = null, next;

            //inline reverse
            while (runner <= right && temp != null) {

                next = temp.next;
                temp.next = prev;
                prev = temp;
                temp = next;
                runner++;

            }

            if(temp == null && runner<=right) //right > n
                return null;

            //reattach it
            // Step 3: Reconnect the reversed sublist with the rest of the list
            if (prevLeft != null) {
                prevLeft.next = prev; // Connect the end of the list before `left` to the new head
            } else {
                // If left == 1, then `prevLeft` is `null`, so `head` should be updated
                head = prev;
            }

            reverseHead.next = temp;

            return head;

        }



        /**
         * O(2n)/O(1)
         * 1. get holding on left node and right node - 1scan
         * 2. get prev to left node and next to right node
         * 3. detach list, reverse list b/w left_right -1scan
         * 4. attach list
         * https://leetcode.com/problems/reverse-linked-list-ii/submissions/1337565135/?envType=problem-list-v2&envId=m4ly4d57&
         * 0  ms
         * Beats 100.00%
         * @param head
         * @param left
         * @param right
         * @return
         */
        private ListNode reverseBetween2Scans(ListNode head, int left, int right) {
            if (head == null || head.next == null || left >= right)
                return head;

            ListNode reverseHead = null, prevLeft = null, nextRight = null, reverseTail = null;

            int tempLeft = left - 1, tempRight = right-1; //head counted as 1
            ListNode temp = head;
            while (temp!=null && (tempLeft>=0 || tempRight >=0)){
                if(tempLeft == 0){
                    reverseHead = temp;
                }
                if(tempRight == 0){
                    reverseTail = temp;
                }

                if(tempLeft > 0)
                    prevLeft = temp;

                temp = temp.next;
                tempLeft--;
                tempRight--;
            }
            //if right > n
            if(reverseTail==null && temp == null)
                return null;

            if (reverseTail == reverseHead)
                return head;

            if(prevLeft != null)
                prevLeft.next = null;

            if(reverseTail!=null && reverseTail.next!=null){
                nextRight = reverseTail.next;
                reverseTail.next = null;
            }

            reverseTail = reverseHead; //post reverse, they will swap position
            reverseHead = reverse(reverseHead);

            //join back
            if(prevLeft != null)
                prevLeft.next = reverseHead;

            reverseTail.next = nextRight;

            if(left == 1){
                return reverseHead;
            }

            return head;
        }

        private ListNode reverse(ListNode head){
            if (head == null || head.next == null)
                return head;

            ListNode n_1_list = head.next;
            head.next = null;
            ListNode newHead = reverse(n_1_list);
            n_1_list.next = head;

            return newHead;
        }
    }
}
