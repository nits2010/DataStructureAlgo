package DataStructureAlgo.Java.LeetCode2025.ProblemSet.List;


import DataStructureAlgo.Java.helpers.ListBuilder;
import DataStructureAlgo.Java.helpers.templates.ListNode;
import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date: 08/01/2024
 * Question Category: 328. Odd Even Linked List [medium]
 * Description:https://leetcode.com/problems/odd-even-linked-list
 * <p>
 * Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes with even indices, and return the reordered list.
 *
 * The first node is considered odd, and the second node is even, and so on.
 *
 * Note that the relative order inside both the even and odd groups should remain as it was in the input.
 *
 * You must solve the problem in O(1) extra space complexity and O(n) time complexity.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4,5]
 * Output: [1,3,5,2,4]
 * Example 2:
 *
 *
 * Input: head = [2,1,3,5,6,4,7]
 * Output: [2,3,6,7,1,5,4]
 *
 *
 * Constraints:
 *
 * The number of nodes in the linked list is in the range [0, 104].
 * -106 <= Node.val <= 106
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link DataStructureAlgo.Java.LeetCode2025.Discuss.SegregateOddAndEvenNodesInList}
 * extension {@link }
 * <p>
 * Tags
 * -----
 * @medium
 * @LinkedList
 *
 *
 * <p>
 * Company Tags
 * -----
 *

 *
 * @Editorial <a href="https://leetcode.com/problems/odd-even-linked-list/solutions/5568483/simple-solution/">...</a>
 *
 */
public class OddEvenLinkedList_328 {

    public static void main(String[] args) {
        boolean testResult= true;
        testResult &= test(new Integer[]{1,2,3,4,5,6,7,8}, new Integer[]{1,3,5,7,2,4,6,8});
        testResult &= test(new Integer[]{1, 2, 3, 4, 5}, new Integer[]{1, 3, 5, 2, 4});
        testResult &= test(new Integer[]{2, 1, 3, 5, 6, 4, 7}, new Integer[]{2, 3, 6, 7, 1, 5, 4});
        testResult &= test(new Integer[]{2}, new Integer[]{2});
        testResult &= test(new Integer[]{2,3}, new Integer[]{2,3});

        System.out.println("\nAll tests passed: " + testResult);
    }

    private static boolean test(Integer[] list, Integer[] expected) {
        Solution solution = new Solution();
        ListNode head = ListBuilder.arrayToSinglyList(list);
        ListNode expectedHead = ListBuilder.arrayToSinglyList(expected);
        System.out.println("\nInput :" + CommonMethods.print(head) + " expected :" + CommonMethods.print(expectedHead));
        ListNode actual = solution.oddEvenList(head);
        System.out.println("Obtained :" + CommonMethods.print(actual));
        boolean testResult = CommonMethods.equalsValues(expectedHead, actual);;
        System.out.println("Test result :" + testResult);
        return testResult;
    }

    /**
     * O(n)/O(1)
     * 0 ms Beats 100.00%
     */
    static class Solution {
        /**
         * The idea is simple, for all even index node, we need to add them at the end of list,
         * while skipping all the odd index node.
         * How to add at the end ? -> get the tail node pointing to tail of list. add odd node after tail and updated tail
         * How to skip odd -> move to next index and pointer
         * @param head
         * @return
         */
        public ListNode oddEvenList(ListNode head) {

            if (head == null || head.next == null)
                return head;

            int index = 1; //1-based index list

            int length = 1; // to capture length of list; if we don't use it then it will become endless

            //get tail of list
            ListNode tail = head;
            while (tail.next != null) {
                length++;
                tail = tail.next;
            }

            //Current point the current node under investigation at ith index
            //prev points to the previous of current node
            ListNode current = head;
            ListNode prev = null;
            //index <= length ; = to consider the last node as well. When list is even length
            //then last node also need to shift
            while (current != null && index <= length) {

                //if this is even index node.
                //current!=tail; if the list of 2 size, then tail will be same as current when i=2
                if (index % 2 == 0 && current!=tail) {

                    //even index node; detach and attach at last of list
                    ListNode next = current.next;
                    current.next = null;
                    tail.next = current;
                    tail = current;


                    prev.next = next;

                    //next must be an odd node, no need to update prev, as this node going to be skip
                    current = next;
                }else {

                    prev = current;
                    current = current.next;
                }

                index++;
            }
            return head;

        }
    }
}
