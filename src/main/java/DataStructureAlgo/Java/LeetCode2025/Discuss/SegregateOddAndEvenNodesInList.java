package DataStructureAlgo.Java.LeetCode2025.Discuss;


import DataStructureAlgo.Java.helpers.ListBuilder;
import DataStructureAlgo.Java.LeetCode.templates.ListNode;
import DataStructureAlgo.Java.LeetCode2025.medium.List.OddEvenLinkedList_328;
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
 * Similar {@link OddEvenLinkedList_328}
 * extension {@link }
 * <p>
 * Tags
 * -----
 *
 * @medium
 * @LinkedList
 * Company Tags
 * -----
 * @Google
 * @Editorial <a href="https://leetcode.com/problems/odd-even-linked-list/solutions/5568483/simple-solution/">...</a>
 */
public class SegregateOddAndEvenNodesInList {

    public static void main(String[] args) {
        boolean testResult = true;
        testResult &= test(new Integer[]{17, 15, 8, 9, 2, 4, 6}, new Integer[]{8, 2, 4, 6, 17, 15, 9});
        testResult &= test(new Integer[]{17, 2}, new Integer[]{2, 17});
        testResult &= test(new Integer[]{17}, new Integer[]{17});
        testResult &= test(new Integer[]{2}, new Integer[]{2});
        testResult &= test(new Integer[]{2, 4, 6, 8}, new Integer[]{2, 4, 6, 8});
        testResult &= test(new Integer[]{1, 3, 5, 7}, new Integer[]{1, 3, 5, 7});
        System.out.println("Tests passed : " + (testResult ? "Yes" : "No"));
    }

    private static boolean test(Integer[] input, Integer[] expected) {
        ListNode head = ListBuilder.arrayToSinglyList(input);
        ListNode expectedNode = ListBuilder.arrayToSinglyList(expected);
        System.out.println("\nInput :" + GenericPrinter.print(head) + " expected :" + GenericPrinter.print(expectedNode));
        SegregateOddAndEvenNodesInList solution = new SegregateOddAndEvenNodesInList();
        ListNode output = solution.segregateOddAndEvenNodesInList(ListBuilder.arrayToSinglyList(input));
        ListNode outputSepList = solution.segregateOddAndEvenNodesInListSepList(ListBuilder.arrayToSinglyList(input));
        System.out.println("Obtained :" + GenericPrinter.print(output));
        System.out.println("OutputSepList Obtained :" + GenericPrinter.print(outputSepList));
        boolean testResult = GenericPrinter.equalsValues(expectedNode, output);
        boolean testResultSepList = GenericPrinter.equalsValues(expectedNode, outputSepList);
        System.out.println("Test passed: " + testResult + " | " + testResultSepList);
        return (testResult == testResultSepList);
    }

    /**
     * Since we need not to maintain order, we can simply scan the list, if the current node is odd, simply append at the end, however if its even, skip it.
     * Maintain the pointer, specially when the odd node is b/w the nodes.
     *
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
                if (temp == finalHead)
                    finalHead = next;

                temp.next = null;

                //add at the end and update OddTail
                oddTail.next = temp;
                oddTail = temp;

                //if this node is in b/w then detach it from prev node as well
                if (prev != null)
                    prev.next = next;
            } else {
                prev = temp;
            }
            temp = next;
            i++;

        }
        return finalHead;
    }

    /**
     * Keep tracking the list, if you encounter a even node, then add it in even list otherwise add in odd list.
     * While adding in list, we can add both type of node in its list at tail. Keep track of oddHead and oddTail while keep track evenTail.
     * At last join both list.
     *
     * @param head
     * @return
     */
    public ListNode segregateOddAndEvenNodesInListSepList(ListNode head) {
        if (head == null || head.next == null)
            return head;


        ListNode evenTail = null;
        ListNode oddHead = null;
        ListNode oddTail = null;
        ListNode current = head;
        ListNode finalHead = head;


        while (current != null) {

            ListNode next = current.next;
            current.next = null; //we are adding at tail, hence need to disconnect node.
            if (current.val % 2 != 0) {
                //detach lists


                //add node to tail
                if (oddTail != null)
                    oddTail.next = current;

                //updated tail
                oddTail = current;

                //if oddHead found
                if (oddHead == null) {
                    oddHead = oddTail;
                }
            } else {
                // get final head
                if (evenTail == null)
                    finalHead = current;

                //add node to tail
                if (evenTail != null)
                    evenTail.next = current;

                //updated tail
                evenTail = current;
            }
            current = next;
        }

        if (evenTail != null) {
            evenTail.next = oddHead;
        }else{
            return oddHead;
        }
        return finalHead;
    }
}
