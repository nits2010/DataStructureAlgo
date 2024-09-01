package DataStructureAlgo.Java.LeetCode2025.ProblemSet.List;

import DataStructureAlgo.Java.helpers.ListBuilder;
import DataStructureAlgo.Java.helpers.templates.ListNode;

import DataStructureAlgo.Java.LeetCode2025.ProblemSet.List.LinkedListCycle_141;

/**
 * Author: Nitin Gupta
 * Date: 7/31/2024
 * Question Category: 142. Linked List Cycle II [ medium ]
 * Description: https://leetcode.com/problems/linked-list-cycle-ii
 * <p>
 * Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.
 *
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed). It is -1 if there is no cycle. Note that pos is not passed as a parameter.
 *
 * Do not modify the linked list.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [3,2,0,-4], pos = 1
 * Output: tail connects to node index 1
 * Explanation: There is a cycle in the linked list, where tail connects to the second node.
 * Example 2:
 *
 *
 * Input: head = [1,2], pos = 0
 * Output: tail connects to node index 0
 * Explanation: There is a cycle in the linked list, where tail connects to the first node.
 * Example 3:
 *
 *
 * Input: head = [1], pos = -1
 * Output: no cycle
 * Explanation: There is no cycle in the linked list.
 *
 *
 * Constraints:
 *
 * The number of the nodes in the list is in the range [0, 104].
 * -105 <= Node.val <= 105
 * pos is -1 or a valid index in the linked-list.
 *
 *
 * Follow up: Can you solve it using O(1) (i.e. constant) memory?
 * <p>
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link LinkedListCycle_141}
 * <p>
 * Tags
 * -----
 * @medium
 * @LinkedList
 * @HashTable
 * @TwoPointers
 *
 * <p>
 * Company Tags
 * -----
 * @Microsoft
 * @Amazon
 *
 * @Editorial
 */
public class LinkedListCycleII_142 {

    public static void main(String[] args) {
        boolean testResult = true;
        testResult &= test(new Integer[]{1, 2, 3, 4, 5}, 2, 3);
        testResult &= test(new Integer[]{1, 2, 3, 4, 5}, -1, null);
        testResult &= test(new Integer[]{1, 2, 3, 4, 5,7,8,9}, 2, 3);
        testResult &= test(new Integer[]{1}, 0, 1);

        System.out.println("\nTest result = " + (testResult ? "Pass" : "Fail"));

    }

    private static boolean test(Integer[] input, int pos, Integer expectedValue) {
        ListNode originalList = ListBuilder.arrayToCyclicSinglyList(input, pos);

        LinkedListCycleIITwoPointers.Solution solution = new LinkedListCycleIITwoPointers().new Solution();
        ListNode result = solution.detectCycle(originalList);
        boolean testResult = false;
        if (result == null && expectedValue == null) {
            testResult=true;

        } else {
            ListNode temp = originalList;
            int i = 0;
            while (i<pos && temp!=null){
                temp = temp.next;
                i++;
            }
            if(temp == result){
                testResult = true;
            }
        }
        if(result == null){
            System.out.println("\n expected = " + expectedValue + " Obtained = nothing"  );
        }else {
            System.out.println("\n expected = " + expectedValue + " Obtained = " +  result.val);
        }
        return testResult;
    }

}
class LinkedListCycleIITwoPointers {
    public class Solution {
        public ListNode detectCycle(ListNode head) {

            if(head==null || head.next == null)
                return null;

            ListNode fast, slow;
            fast = slow = head;

            while (fast!=null && fast.next!=null){
                fast = fast.next.next;
                slow = slow.next;

                if(slow == fast) //cycle detected
                    break;
            }

            //cycle exist
            if(fast!=null){
                slow = head;
                while (slow!=fast & fast!=null){
                    slow = slow.next;
                    fast = fast.next;
                }

                return fast;
            }
            return null;
        }

        /**
         * 0 ms
         * Beats 100.00%
         * @param head
         * @return
         */

        public ListNode detectCycleSimplified(ListNode head) {

            if(head==null || head.next == null)
                return null;

            ListNode fast, slow;
            fast = slow = head;

            while (fast!=null && fast.next!=null){
                fast = fast.next.next;
                slow = slow.next;

                if(slow == fast) { //cycle detected
                    slow = head;
                    while (slow!=fast){
                        slow = slow.next;
                        fast = fast.next;
                    }

                    return fast;
                }
            }
            return null;
        }
    }
}