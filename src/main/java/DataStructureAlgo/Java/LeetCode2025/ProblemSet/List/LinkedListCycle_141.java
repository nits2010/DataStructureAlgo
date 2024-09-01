package DataStructureAlgo.Java.LeetCode2025.ProblemSet.List;

import DataStructureAlgo.Java.helpers.ListBuilder;
import DataStructureAlgo.Java.helpers.templates.ListNode;

/**
 * Author: Nitin Gupta
 * Date: 7/31/2024
 * Question Category: 141. Linked List Cycle [easy ]
 * Description: https://leetcode.com/problems/linked-list-cycle
 *
 * <p>
 *Given head, the head of a linked list, determine if the linked list has a cycle in it.
 *
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
 *
 * Return true if there is a cycle in the linked list. Otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [3,2,0,-4], pos = 1
 * Output: true
 * Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).
 * Example 2:
 *
 *
 * Input: head = [1,2], pos = 0
 * Output: true
 * Explanation: There is a cycle in the linked list, where the tail connects to the 0th node.
 * Example 3:
 *
 *
 * Input: head = [1], pos = -1
 * Output: false
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
 * extension {@link }
 * <p>
 * Tags
 * -----
 * @easy
 * @HashTable
 * @LinkedList
 * @TwoPointers
 *
 * <p>
 * Company Tags
 * -----
 * @Amazon
 * @Spotify
 * @Microsoft
 * @Oracle
 * @Visa
 *
 * @Editorial
 */
public class LinkedListCycle_141 {
    public static void main(String[] args) {
        boolean testResult = true;
        testResult &= test(new Integer[]{1, 2, 3, 4, 5}, 2, true);
        testResult &= test(new Integer[]{1, 2, 3, 4, 5}, -1, false);
        testResult &= test(new Integer[]{1, 2, 3, 4, 5,7,8,9}, 2, true);
        testResult &= test(new Integer[]{1}, 0, true);

        System.out.println("\nTest result = " + (testResult ? "Pass" : "Fail"));

    }

    private static boolean test(Integer[] input, int pos, boolean expectedValue) {
        ListNode originalList = ListBuilder.arrayToCyclicSinglyList(input, pos);

        LinkedListCycleUsingTwoPointers.Solution solution = new LinkedListCycleUsingTwoPointers().new Solution();
        boolean result = solution.hasCycle(originalList);
        System.out.println("\n expected = " + expectedValue + " Obtained = " + result);
        return  (result == expectedValue) ;
    }

}

class LinkedListCycleUsingTwoPointers {

    public class Solution {
        public boolean hasCycle(ListNode head) {
            if (head == null || head.next == null)
                return false;

            ListNode fast = head;
            ListNode slow = head;

            while (slow!=null && fast!=null){

                fast = fast.next;
                if(fast == slow)
                    return true;

                if(fast == null)
                    return false;

                fast = fast.next;
                if(fast == slow)
                    return true;

                slow = slow.next;

            }
            return false;
        }

        /**
         * 0 ms Beats 100.00%
         * @param head
         * @return
         */
        public boolean hasCycleSimplified(ListNode head) {
            if (head == null || head.next == null)
                return false;

            ListNode fast = head;
            ListNode slow = head;

            while (fast!=null && fast.next!=null){

                slow = slow.next;
                fast = fast.next.next;
                if(fast == slow)
                    return true;
            }
            return false;
        }
    }
}