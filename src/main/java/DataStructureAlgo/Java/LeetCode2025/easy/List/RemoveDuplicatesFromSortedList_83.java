package DataStructureAlgo.Java.LeetCode2025.easy.List;

import DataStructureAlgo.Java.helpers.ListBuilder;
import DataStructureAlgo.Java.helpers.templates.ListNode;
import DataStructureAlgo.Java.helpers.GenericPrinter;

/**
 * Author: Nitin Gupta
 * Date: 7/31/2024
 * Question Category: 83. Remove Duplicates from Sorted List. [easy]
 * Description:https://leetcode.com/problems/remove-duplicates-from-sorted-list/description/
 *
 * <p>
 *Given the head of a sorted linked list, delete all duplicates such that each element appears only once. Return the linked list sorted as well.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,1,2]
 * Output: [1,2]
 * Example 2:
 *
 *
 * Input: head = [1,1,2,3,3]
 * Output: [1,2,3]
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [0, 300].
 * -100 <= Node.val <= 100
 * The list is guaranteed to be sorted in ascending order.
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
 * @LinkedList
 *
 * <p>
 * Company Tags
 * -----
 * @Amazon
 * @Facebook
 * @Bloomberg
 * @Adobe
 * @Microsoft
 */
public class RemoveDuplicatesFromSortedList_83 {

    public static void main(String[] args) {
       boolean testResult = true;
       testResult &= test(new Integer[]{1,1,2,3,3}, new Integer[]{1,2,3});
       testResult &= test(new Integer[]{1,1,2}, new Integer[]{1,2});
       System.out.println("\nTest result = " + (testResult ? "Pass" : "Fail"));
    }

    private static boolean test(Integer[] input, Integer[] expected) {
        ListNode originalList = ListBuilder.arrayToSinglyList(input);
        ListNode expectedList = ListBuilder.arrayToSinglyList(expected);
        System.out.println("\n Input :" + GenericPrinter.print(originalList) + "\nexpected :" + GenericPrinter.print(expectedList));

        ListNode result = new RemoveDuplicatesFromSortedList.Solution().deleteDuplicates(originalList);
        boolean testResult =  GenericPrinter.equalsValues(result, expectedList);
        System.out.println(" Output :" + GenericPrinter.print(result) + " Result match : " + testResult);

        return testResult;
    }
}

class RemoveDuplicatesFromSortedList {

    static class Solution {
        public ListNode deleteDuplicates(ListNode head) {
            if(head == null)
                return null;

            ListNode current = head;

            while(current!=null){
                ListNode next = current.next;
                while (next!=null && (next.val == current.val))
                    next = next.next;

                current.next = next; //skip duplicates
                current = next; //move to next
            }
            return head;
        }
    }

}