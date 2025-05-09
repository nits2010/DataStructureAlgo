package DataStructureAlgo.Java.LeetCode2025.ProblemSet.List._82;

import DataStructureAlgo.Java.LeetCode2025.ProblemSet.List._83.RemoveDuplicatesFromSortedList_83;
import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.ListBuilder;
import DataStructureAlgo.Java.helpers.templates.ListNode;

/**
 * Author: Nitin Gupta
 * Date: 7/31/2024
 * Question Category: 82. Remove Duplicates from Sorted List II [ medium ]
 * Description:https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/description/
 *
 * <p>
 * Given the head of a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list. Return the linked list sorted as well.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [1,2,3,3,4,4,5]
 * Output: [1,2,5]
 * Example 2:
 * <p>
 * <p>
 * Input: head = [1,1,1,2,3]
 * Output: [2,3]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is in the range [0, 300].
 * -100 <= Node.val <= 100
 * The list is guaranteed to be sorted in ascending order.
 * <p>
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._80.RemoveDuplicatesFromSortedArrayII_80}
 * extension {@link RemoveDuplicatesFromSortedList_83}
 * <p>
 * Tags
 * -----
 *
 * @medium
 * @LinkedList
 * @TwoPointers <p>
 * Company Tags
 * -----
 * @Amazon
 * @Microsoft
 * @Facebook
 */
public class RemoveDuplicatesFromSortedListII_82 {
    public static void main(String[] args) {
        boolean testResult = true;
        testResult &= test(new Integer[]{1, 2, 3, 3, 4, 5, 8}, new Integer[]{1, 2, 4, 5, 8});
        testResult &= test(new Integer[]{1, 1, 1, 2, 3}, new Integer[]{2, 3});
        testResult &= test(new Integer[]{1, 2, 3, 4, 5, 6}, new Integer[]{1, 2, 3, 4, 5, 6});
        testResult &= test(new Integer[]{1, 1, 1}, null);
        System.out.println("\nTest result = " + (testResult ? "Pass" : "Fail"));
    }

    private static boolean test(Integer[] input, Integer[] expected) {
        ListNode inputList = ListBuilder.arrayToSinglyList(input);
        ListNode expectedList = ListBuilder.arrayToSinglyList(expected);
        System.out.println("\n Input :" + CommonMethods.print(inputList) + "\nexpected :" + CommonMethods.print(expectedList));

        RemoveDuplicatesFromSortedListII.Solution solution = new RemoveDuplicatesFromSortedListII.Solution();
        ListNode result = solution.deleteDuplicates(inputList);
        boolean testResult = CommonMethods.equalsValues(result, expectedList);
        System.out.println(" Output :" + CommonMethods.print(result) + " Result match : " + testResult);
        return testResult;

    }
}

class RemoveDuplicatesFromSortedListII {
    static class Solution {
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null)
                return null;
            ListNode current = head;
            ListNode prev = null;
            ListNode newHead = head;

            while (current != null) {

                //find common values node
                ListNode next = current.next;
                while (next != null && next.val == current.val) {

                    next = next.next;
                }

                //means multiple occurrence of current exit, we have to remove all values like current
                if (current.next != next) {
                    //if the head is repeated, the head needs to change
                    if (prev == null) {
                        newHead = next;
                    } else {
                        //if not head, just remove the current values list
                        prev.next = next;
                    }

                } else {
                    prev = current; // since no duplicate, update prev to current
                }
                current = next; //move to the next group

            }
            return newHead;
        }
    }
}