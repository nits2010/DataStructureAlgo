package DataStructureAlgo.Java.LeetCode2025.FromLeetCode;


import DataStructureAlgo.Java.LeetCode.listToBST.ListBuilder;
import DataStructureAlgo.Java.LeetCode.templates.ListNode;
import DataStructureAlgo.Java.helpers.GenericPrinter;

/**
 * Author: Nitin Gupta
 * Date: 08/01/2024
 * Question Category: Segregate Odd And Even Nodes In List
 * Description:https://leetcode.com/discuss/interview-question/2059848/segregate-even-and-odd-nodes-in-a-link-list-using-c-solution-timeon-spaceo1
 * <p>
 * Segregate even and odd nodes in a Link List
 * <p>
 * List = 17 -> 15 -> 8 -> 9 -> 2 -> 4 -> 6 -> NULL
 * ans = 8->2->4->6->17->15->9->NULL
 * segregate all even nodes to left and all odd ones to right
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link DataStructureAlgo.Java.LeetCode2025.medium.OddEvenLinkedList_328}
 * extension {@link }
 * <p>
 * Tags
 * -----
 *
 * @medium
 * @LinkedList <p>
 * Company Tags
 * -----
 * @Google
 * @Editorial <a href="https://leetcode.com/problems/odd-even-linked-list/solutions/5568483/simple-solution/">...</a>
 */
public class SegregateOddAndEvenNodesInList {

    public static void main(String[] args) {
        boolean testResult = true;
        testResult &= test(new Integer[]{17,15,8,9,2,4,6}, new Integer[]{8,2,4,6,17,15,9});
        testResult &= test(new Integer[]{17,2}, new Integer[]{2,17});
        testResult &= test(new Integer[]{17}, new Integer[]{17});
        testResult &= test(new Integer[]{2}, new Integer[]{2});
        testResult &= test(new Integer[]{2,4,6,8}, new Integer[]{2,4,6,8});
        testResult &= test(new Integer[]{1,3,5,7}, new Integer[]{1,3,5,7});
        System.out.println("Tests passed : " + (testResult ? "Yes" : "No"));
    }

    private static boolean test(Integer[] input, Integer[]expected){
        ListNode head = ListBuilder.arrayToSinglyList(input);
        ListNode expectedNode = ListBuilder.arrayToSinglyList(expected);
        System.out.println("\nInput :" + GenericPrinter.print(head) + " expected :" + GenericPrinter.print(expectedNode));
        SegregateOddAndEvenNodesInList solution = new SegregateOddAndEvenNodesInList();
        ListNode output = solution.segregateOddAndEvenNodesInList(head);
        System.out.println("Obtained :" + GenericPrinter.print(output));
        boolean testResult =  GenericPrinter.equalsValues(expectedNode, output);
        System.out.println("Test passed: " + testResult);
        return testResult;
    }

    /**
6,8, 2,4,6,

     * @param head
     * @return
     */
    public ListNode segregateOddAndEvenNodesInList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode finalHead = head;

        int length = 1;
        ListNode oddTail = head;
        while (oddTail.next != null) {
            length++;
            oddTail = oddTail.next;
        }

        ListNode temp = head, prev = null;
        int i = 1;
        while (temp != null && i <= length) {

            //cache next node
            ListNode next = temp.next;
            if (temp.val % 2 != 0) {
                if(temp == finalHead)
                    finalHead = next;

                temp.next = null;

                //add at the end and update OddTail
                oddTail.next = temp;
                oddTail = temp;

                //if this node is in b/w then detach it from prev node as well
                if(prev!=null)
                    prev.next = next;
            }else {
                prev = temp;
            }
            temp = next;
            i++;

        }
        return finalHead;
    }
}
