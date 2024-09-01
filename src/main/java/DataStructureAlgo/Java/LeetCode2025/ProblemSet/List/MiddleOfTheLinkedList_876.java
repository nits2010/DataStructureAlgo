package DataStructureAlgo.Java.LeetCode2025.ProblemSet.List;

import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.ListBuilder;
import DataStructureAlgo.Java.helpers.templates.ListNode;

/**
 * Author: Nitin Gupta
 * Date: 7/31/2024
 * Question Category:876. Middle of the Linked List [easy]
 * Description: https://leetcode.com/problems/middle-of-the-linked-list/description/
 * <p>
 * Given the head of a singly linked list, return the middle node of the linked list.
 *
 * If there are two middle nodes, return the second middle node.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4,5]
 * Output: [3,4,5]
 * Explanation: The middle node of the list is node 3.
 * Example 2:
 *
 *
 * Input: head = [1,2,3,4,5,6]
 * Output: [4,5,6]
 * Explanation: Since the list has two middle nodes with values 3 and 4, we return the second one.
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [1, 100].
 * 1 <= Node.val <= 100
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link DeleteTheMiddleNodeOfALinkedList_2095}
 * extension {@link }
 * <p>
 * Tags
 * -----
 * @easy
 * @LinkedList
 * @TwoPointers
 *
 *
 * <p>
 * Company Tags
 * -----
 * @Amazon
 * @Adobe
 * @Facebook
 * @Google
 * @Microsoft
 *
 * @Editorial
 */

public class MiddleOfTheLinkedList_876 {
    public static void main(String[] args) {
        boolean testResult = true;
        testResult &= test(new Integer[]{1,2,3,4,5,6}, new Integer[]{4,5,6});
        testResult &= test(new Integer[]{1,3,4,7,1,2,6}, new Integer[]{7,1,2,6});
        testResult &= test(new Integer[]{1,2,3,4}, new Integer[]{3,4});
        testResult &= test(new Integer[]{1}, new Integer[]{1});
        testResult &= test(new Integer[]{1,2}, new Integer[]{2});
        testResult &= test(new Integer[]{1,2,3}, new Integer[]{2,3});
        testResult &= test(new Integer[]{}, new Integer[]{});
        System.out.println((testResult ? "All passed" : "Something failed"));

    }
    private static boolean test(Integer[] input, Integer[] expected){
        System.out.println("--------------");
        ListNode inputList = ListBuilder.arrayToSinglyList(input);
        ListNode expectedList = ListBuilder.arrayToSinglyList(expected);
        System.out.println("Input: " + CommonMethods.print(inputList) + "\nExpected: " + CommonMethods.print(expectedList)) ;
        Solution solution = new Solution();
        ListNode output = solution.middleNode(inputList);
        System.out.println("Output: " + CommonMethods.print(output)) ;

        boolean testResult = CommonMethods.equalsValues(expectedList, output);
        System.out.println("Test result: " + testResult);
        return testResult;
    }



    static class Solution {
        public ListNode middleNode(ListNode head) {
            if(head == null || head.next == null)
                return head;

            ListNode fast = head;
            ListNode slow = head;

            while (fast!=null && fast.next!=null){
                fast = fast.next.next;
                slow = slow.next;
            }
            return slow;
        }
    }
}
