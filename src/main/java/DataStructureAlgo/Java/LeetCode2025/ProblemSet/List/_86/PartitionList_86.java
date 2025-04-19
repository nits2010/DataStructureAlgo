package DataStructureAlgo.Java.LeetCode2025.ProblemSet.List._86;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.templates.ListNode;


/**
 * Author: Nitin Gupta
 * Date: 4/19/2025
 * Question Title: 86. Partition List
 * Link: https://leetcode.com/problems/partition-list/description/
 * Description: Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 * <p>
 * You should preserve the original relative order of the nodes in each of the two partitions.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [1,4,3,2,5,2], x = 3
 * Output: [1,2,2,4,3,5]
 * Example 2:
 * <p>
 * Input: head = [2,1], x = 2
 * Output: [1,2]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is in the range [0, 200].
 * -100 <= Node.val <= 100
 * -200 <= x <= 200
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @LinkedList
 * @TwoPointers <p><p>
 * Company Tags
 * -----
 * @Adobe
 * @Amazon
 * @Apple
 * @Microsoft <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class PartitionList_86 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new Integer[]{1, 4, 3, 2, 5, 2}, 3, new Integer[]{1, 2, 2, 4, 3, 5}));
        tests.add(test(new Integer[]{2, 1}, 2, new Integer[]{1, 2}));
        tests.add(test(new Integer[]{1}, 0, new Integer[]{1}));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(Integer[] nodes, int x, Integer[] expected) {

        CommonMethods.printTestOutcome(new String[]{"nodes", "x", "Expected"}, true, nodes, x, expected);
        ListNode head = ListBuilder.arrayToSinglyList(nodes);
        ListNode expectedList = ListBuilder.arrayToSinglyList(expected);

        ListNode output;
        boolean pass, finalPass = true;

        Solution_FlyList solutionFlyList = new Solution_FlyList();
        output = solutionFlyList.partition(head, x);
        pass = CommonMethods.compareResultOutCome(output, expectedList, true);
        finalPass &= pass;

        CommonMethods.printTestOutcome(new String[]{"Solution_FlyList", "Pass"}, false, output, pass ? "PASS" : "FAIL");


        SolutionMultiList solutionMultiList = new SolutionMultiList();
        output = solutionMultiList.partition(ListBuilder.arrayToSinglyList(nodes), x);
        pass = CommonMethods.compareResultOutCome(output, expectedList, true);
        finalPass &= pass;

        CommonMethods.printTestOutcome(new String[]{"solutionMultiList", "Pass"}, false, output, pass ? "PASS" : "FAIL");


        return finalPass;

    }


    static class Solution_FlyList {
        public ListNode partition(ListNode head, int x) {

            ListNode smallListDummy = new ListNode(0);
            ListNode bigListDummy = new ListNode(0);

            ListNode smallList = smallListDummy; //tracks a small list < target and maintain order
            ListNode bigList = bigListDummy; //track a big list > target and maintain order

            ListNode temp = head;

            while (temp != null) {

                if (temp.val < x) {
                    //attach it to a small list
                    smallList.next = temp;
                    smallList = temp;
                } else {
                    //attach to a big list
                    bigList.next = temp;
                    bigList = temp;
                }

                temp = temp.next;
            }

            smallList.next = bigListDummy.next;
            bigList.next = null;
            return smallListDummy.next;

        }

    }

    //build a different list of less than x and greater than x and then merge them
    static class SolutionMultiList {
        public ListNode partition(ListNode head, int x) {
            if (head == null || head.next == null)
                return head;

            ListNode lessThanXHead = null;
            ListNode lessThanXTail = null;
            ListNode greaterEqualToXHead = null;
            ListNode greaterEqualToXTail = null;

            ListNode current = head;
            ListNode next;

            while (current != null) {

                next = current.next;
                current.next = null;

                if (current.val < x) {
                    if (lessThanXTail != null) {
                        lessThanXTail.next = current;
                    }
                    lessThanXTail = current;

                    if (lessThanXHead == null)
                        lessThanXHead = current;
                } else {
                    if (greaterEqualToXTail != null) {
                        greaterEqualToXTail.next = current;
                    }
                    greaterEqualToXTail = current;

                    if (greaterEqualToXHead == null)
                        greaterEqualToXHead = current;
                }
                current = next;
            }

            if (lessThanXTail != null) {
                lessThanXTail.next = greaterEqualToXHead;
            } else {
                return greaterEqualToXHead;
            }
            return lessThanXHead;
        }
    }
}
