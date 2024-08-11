package DataStructureAlgo.Java.LeetCode2025.medium.List;

import DataStructureAlgo.Java.helpers.ListBuilder;
import DataStructureAlgo.Java.LeetCode.templates.ListNode;
import DataStructureAlgo.Java.LeetCode2025.easy.List.MiddleOfTheLinkedList_876;
import DataStructureAlgo.Java.helpers.GenericPrinter;

/**
 * Author: Nitin Gupta
 * Date: 02/08/24
 * Question Category: 2095. Delete the Middle Node of a Linked List [Medium]
 * Description: https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list
 * <p>
 * You are given the head of a linked list. Delete the middle node, and return the head of the modified linked list.
 *
 * The middle node of a linked list of size n is the ⌊n / 2⌋th node from the start using 0-based indexing, where ⌊x⌋ denotes the largest integer less than or equal to x.
 *
 * For n = 1, 2, 3, 4, and 5, the middle nodes are 0, 1, 1, 2, and 2, respectively.
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,3,4,7,1,2,6]
 * Output: [1,3,4,1,2,6]
 * Explanation:
 * The above figure represents the given linked list. The indices of the nodes are written below.
 * Since n = 7, node 3 with value 7 is the middle node, which is marked in red.
 * We return the new list after removing this node.
 * Example 2:
 *
 *
 * Input: head = [1,2,3,4]
 * Output: [1,2,4]
 * Explanation:
 * The above figure represents the given linked list.
 * For n = 4, node 2 with value 3 is the middle node, which is marked in red.
 * Example 3:
 *
 *
 * Input: head = [2,1]
 * Output: [2]
 * Explanation:
 * The above figure represents the given linked list.
 * For n = 2, node 1 with value 1 is the middle node, which is marked in red.
 * Node 0 with value 2 is the only node remaining after removing node 1.
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [1, 105].
 * 1 <= Node.val <= 105
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link MiddleOfTheLinkedList_876}
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
 *
 * @Editorial
 */
public class DeleteTheMiddleNodeOfALinkedList_2095 {


    public static void main(String[] args) {
        boolean testResult = true;
        testResult &= test(new Integer[]{1,2,3,4,5,6}, new Integer[]{1,2,3,5,6});
        testResult &= test(new Integer[]{1,3,4,7,1,2,6}, new Integer[]{1,3,4,1,2,6});
        testResult &= test(new Integer[]{1,2,3,4}, new Integer[]{1,2,4});
        testResult &= test(new Integer[]{1}, new Integer[]{});
        testResult &= test(new Integer[]{1,2}, new Integer[]{1});
        testResult &= test(new Integer[]{1,2,3}, new Integer[]{1,3});
        testResult &= test(new Integer[]{}, new Integer[]{});
        System.out.println((testResult ? "All passed" : "Something failed"));

    }
    private static boolean test(Integer[] input, Integer[] expected){
        System.out.println("--------------");
        ListNode inputList = ListBuilder.arrayToSinglyList(input);
        ListNode expectedList = ListBuilder.arrayToSinglyList(expected);
        System.out.println("Input: " + GenericPrinter.print(inputList) + "\nExpected: " + GenericPrinter.print(expectedList)) ;
        Solution solution = new Solution();
        ListNode output = solution.deleteMiddle(inputList);
        System.out.println("Output: " + GenericPrinter.print(output)) ;

        boolean testResult = GenericPrinter.equalsValues(expectedList, output);
        System.out.println("Test result: " + testResult);
        return testResult;
    }

    static class Solution {
        public ListNode deleteMiddle(ListNode head) {

            //if there is only one node, then we need to delete it as well
            if(head == null )
                return null;

            ListNode fast = head;
            ListNode slow = head;
            ListNode prevSlow = null;

            while (fast!=null && fast.next!=null){
                fast = fast.next.next;
                prevSlow = slow;
                slow = slow.next;
            }

            //List has only one node
            //if there is only one node, then we need to delete it as well
            if(prevSlow==null)
                return null; //delete the head node which is at slow

            prevSlow.next = slow.next;
            return head;

        }
    }
}
