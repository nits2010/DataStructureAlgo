package DataStructureAlgo.Java.LeetCode2025.easy.List;

import DataStructureAlgo.Java.helpers.ListBuilder;
import DataStructureAlgo.Java.LeetCode.templates.ListNode;
import DataStructureAlgo.Java.LeetCode2025.medium.List.OddEvenLinkedList_328;
import DataStructureAlgo.Java.helpers.GenericPrinter;

/**
 * Author: Nitin Gupta
 * Date: 7/31/2024
 * Question Category: 203. Remove Linked List Elements [easy]
 * Description: https://leetcode.com/problems/remove-linked-list-elements/description/
 * <p>
 * Given the head of a linked list and an integer val, remove all the nodes of the linked list that has Node.val == val, and return the new head.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [1,2,6,3,4,5,6], val = 6
 * Output: [1,2,3,4,5]
 * Example 2:
 * <p>
 * Input: head = [], val = 1
 * Output: []
 * Example 3:
 * <p>
 * Input: head = [7,7,7,7], val = 7
 * Output: []
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is in the range [0, 104].
 * 1 <= Node.val <= 50
 * 0 <= val <= 50
 * <p>
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link OddEvenLinkedList_328}
 * extension {@link }
 * <p>
 * Tags
 * -----
 *
 * @easy
 * @LinkedList
 * @Recursion <p>
 * Company Tags
 * -----
 * @Amazon
 * @Microsoft
 * @Facebook
 * @Adobe
 *
 *
 * @Editorial <a href="https://leetcode.com/problems/remove-linked-list-elements/solutions/5573963/easy-solution-iterative-recursive/">...</a>
 */

public class RemoveLinkedListElements_203 {

    public static void main(String[] args) {
        boolean testResult = true;
        testResult &= test(new Integer[]{1, 2, 6, 3, 4, 5, 6}, 6, new Integer[]{1, 2, 3, 4, 5});
        testResult &= test(new Integer[]{7, 7, 7, 7}, 7, new Integer[]{});
        testResult &= test(new Integer[]{7, 7, 7, 7, 8}, 7, new Integer[]{8});
        testResult &= test(new Integer[]{8, 7, 7, 7, 7}, 7, new Integer[]{8});
        testResult &= test(new Integer[]{7, 7, 8, 7, 7}, 7, new Integer[]{8});
        testResult &= test(new Integer[]{7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 7, 7, 7, 7, 7}, 7, new Integer[]{8, 8, 8, 8, 8});
        testResult &= test(new Integer[]{8, 8, 8, 8, 8}, 7, new Integer[]{8, 8, 8, 8, 8});
        System.out.println((testResult ? "All passed" : "Something failed"));

    }

    private static boolean test(Integer[] input, int k, Integer[] expected) {
        System.out.println("--------------");
        ListNode expectedList = ListBuilder.arrayToSinglyList(expected);
        System.out.println("Input: " + GenericPrinter.print(ListBuilder.arrayToSinglyList(input)) + " k = " + k + "\nExpected: " + GenericPrinter.print(expectedList));
        SolutionIterative solutionIterative = new SolutionIterative();
        SolutionRecursive solutionRecursive = new SolutionRecursive();

        ListNode outputIterative = solutionIterative.removeElements(ListBuilder.arrayToSinglyList(input), k);
        ListNode outputRecursive = solutionRecursive.removeElements(ListBuilder.arrayToSinglyList(input), k);

        System.out.println("outputIterative: " + GenericPrinter.print(outputIterative));
        System.out.println("outputRecursive: " + GenericPrinter.print(outputRecursive));

        boolean testResultIterative = GenericPrinter.equalsValues(expectedList, outputIterative);
        boolean testResultRecursive = GenericPrinter.equalsValues(expectedList, outputRecursive);

        boolean testResult = testResultIterative == testResultRecursive;
        System.out.println("Test result: " + testResultIterative + " | " + testResultRecursive);
        return testResult;
    }

    /**
     * simply track the prev and current, match for current and remove if needed. Update newHead accordingly.
     * O(n)/O(1)
     */
    static class SolutionIterative {
        public ListNode removeElements(ListNode head, int val) {
            if (head == null)
                return null;

            ListNode current = head;
            ListNode prev = null;
            ListNode newHead = head;

            while (current != null) {

                ListNode next = current.next;
                if (current.val == val) {

                    if (newHead == current) {
                        newHead = current.next;
                    } else {
                        prev.next = current.next;

                    }
                } else {
                    prev = current;
                }
                current = next;

            }
            return newHead;
        }
    }

    /**
     * O(n)/O(n)
     */
    static class SolutionRecursive {
        public ListNode removeElements(ListNode head, int val) {
            if (head == null)
                return null;

            head.next = removeElements(head.next, val);
            if(head.val == val)
                return head.next;
            return head;

        }
    }
}
