package DataStructureAlgo.Java.LeetCode2025.ProblemSet.List;

import DataStructureAlgo.Java.helpers.ListBuilder;
import DataStructureAlgo.Java.helpers.templates.ListNode;
import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Author: Nitin Gupta
 * Date: 7/28/2024
 * Question Category: 234. Palindrome Linked List [easy]
 * Description: https://leetcode.com/problems/palindrome-linked-list/description
 * Given the head of a singly linked list, return true if it is a
 * palindrome or false otherwise.

 * Example 1:
 * Input: head = [1,2,2,1]
 * Output: true
 * Example 2:
 *
 *
 * Input: head = [1,2]
 * Output: false
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [1, 105].
 * 0 <= Node.val <= 9
 *
 *
 * Follow up: Could you do it in O(n) time and O(1) space?
 * <p>
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * <p>
 *
 * Tags
 * -----
 * @easy
 * @LinkedList
 * @TwoPointers
 * @Stack
 * @Recursion
 *
 * Company Tags
 * ------------
 *
 * @Editorial
 * : <a href="https://leetcode.com/problems/palindrome-linked-list/solutions/5549850/multiple-solution-multiple-ds-with-simple-explanation/">...</a>
 *
 */
public class PalindromeLinkedList_234 {

    public static void main(String[] args) {
        boolean testResult = true;
        testResult &= test(new Integer[]{1, 2, 2, 1}, true);
        testResult &= test(new Integer[]{1, 2, 3, 2, 1}, true);
        testResult &= test(new Integer[]{1, 2, 3, 4, 5}, false);
        testResult &=test(new Integer[]{1, 2, 3, 4, 5,6,7,8,9,10}, false);
        testResult &=test(new Integer[]{1, 2, 3, 4, 5,6,7,8,9,10}, false);
        testResult &=test(new Integer[]{1, 2, 3, 4, 5,6,7,8,9,10}, false);
        testResult &= test(new Integer[]{1, 2, 3, 4, 5,6,7,8,9,10}, false);

        System.out.println("\nTest result ="+(testResult?"Pass":"Fail"));

    }


    private static boolean test(Integer[] elements, boolean expected ) {
        ListNode originalList = ListBuilder.arrayToSinglyList(elements);
        System.out.println("\n Input :"+ CommonMethods.print(originalList) + "\nexpected :" +expected);

        Solution solution = new Solution();
        boolean output=  solution.isPalindrome(originalList);
        boolean result;
        System.out.println(" Output :"+output + "\nResult match : "+(result = expected == output));
        return result;



    }
    static class Solution {
        public boolean isPalindrome(ListNode head) {
            return isPalindromeReverse(head);
        }

        /** O(2n) / O(n)
         * <a href="https://leetcode.com/problems/palindrome-linked-list/submissions/1336235577/">...</a>
         * 15 ms
         * Beats 11.09%
         *
         * @param head
         * @return
         */
        private boolean isPalindromeUsingStack(ListNode head) {
            if(head == null || head.next == null)
                return true;

            Stack<ListNode> stack = new Stack<>();

            //push to stack
            ListNode temp = head;
            while(temp!=null){
                stack.push(temp);
                temp =temp.next;
            }


            //compare
            temp = head;
            while (!stack.isEmpty()){
                if(stack.pop().val != temp.val)
                    return false;
                temp =temp.next;

            }
            return true;

        }

        /**
         * Two pointer + reverse approach
         * <a href="https://leetcode.com/problems/palindrome-linked-list/submissions/1336262569/">...</a>
         * 9 ms Beats 28.82%
         * O(3n)/O(1)
         * 1. fine middle of the list
         * 2. detach and reverse second part
         * 3. compare both list
         * 4. re-correct list if needed
         * @param head
         * @return
         */
        private boolean isPalindromeReverse(ListNode head) {

            if(head == null)
                return true;

            final ListNode middle = middle(head);

            //if even number size of list, then middle node must be just before the second list
            // if odd number size of list, then next list has to be post middle node
            ListNode secondList = middle.next;

            //detach to make two list
            middle.next = null ;

            ListNode reversedListHead = reverseList(secondList);

            while (head!=null && reversedListHead!=null){
                if(head.val != reversedListHead.val)
                    return false;
                head = head.next;
                reversedListHead = reversedListHead.next;
            }
            if(head!=null && head != middle)
                return false;

            return true;


        }

        private ListNode reverseList(ListNode head) {
            if(head == null || head.next == null)
                return head;

            ListNode n_1_list = head.next;
            head.next = null;

            ListNode newHead = reverseList(n_1_list); //reverse remaining list and return new head

            //attach previous list
            n_1_list.next = head;

            return newHead;
        }

        private ListNode middle(ListNode head) {
            ListNode slow = head;
            ListNode fast = head.next;

            while(fast!=null && fast.next!=null){
                fast = fast.next.next;
                slow = slow.next;
            }

            return slow;
        }


        /** O(n) / O(n)
         * https://leetcode.com/problems/palindrome-linked-list/submissions/1336268317/
         * 14  ms
         * Beats 17.12%
         */
        ListNode curr;
        private boolean isPalindromeRecursive(ListNode head){
            curr = head;
            return isPalindromeRecursiveSolve(head);
        }

        private boolean isPalindromeRecursiveSolve(ListNode head) {

            if(head ==null)
                return true;

            boolean x = isPalindromeRecursiveSolve(head.next);
            x = x & (head.val == curr.val) ;
            curr = curr.next;
            return x;

        }

        /** O(2n) / O(n)
         * https://leetcode.com/problems/palindrome-linked-list/submissions/1336473596/
         * 5 ms
         * Beats 61.74%
         * @param head
         * @return
         */
        private boolean isPalindromeUsingExternalList(ListNode head){
            if(head == null || head.next == null)
                return true;

            ArrayList<ListNode> list = new ArrayList<>();
            ListNode temp = head;
            while(temp!=null){
                list.add(temp);
                temp = temp.next;
            }

            int length = list.size()-1;

            while(length>0){
                if(head.val != list.get(length).val)
                    return false;
                length--;
                head = head.next;
            }

            return true;
        }


    }
}

