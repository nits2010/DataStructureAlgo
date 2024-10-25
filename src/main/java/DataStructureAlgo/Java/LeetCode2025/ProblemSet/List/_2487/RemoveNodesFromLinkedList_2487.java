package DataStructureAlgo.Java.LeetCode2025.ProblemSet.List._2487;

import DataStructureAlgo.Java.LeetCode2025.ProblemSet.Stacks.NextGreaterElement._503.NextGreaterElementII_503;
import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.ListBuilder;
import DataStructureAlgo.Java.helpers.templates.ListNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Author: Nitin Gupta
 * Date:07/08/24
 * Question Category:2487. Remove Nodes From Linked List @medium
 * Description: https://leetcode.com/problems/remove-nodes-from-linked-list
 * <p>
 * You are given the head of a linked list.
 *
 * Remove every node which has a node with a greater value anywhere to the right side of it.
 *
 * Return the head of the modified linked list.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [5,2,13,3,8]
 * Output: [13,8]
 * Explanation: The nodes that should be removed are 5, 2 and 3.
 * - Node 13 is to the right of node 5.
 * - Node 13 is to the right of node 2.
 * - Node 8 is to the right of node 3.
 * Example 2:
 *
 * Input: head = [1,1,1,1]
 * Output: [1,1,1,1]
 * Explanation: Every node has value 1, so no nodes are removed.
 *
 *
 * Constraints:
 *
 * The number of the nodes in the given list is in the range [1, 105].
 * 1 <= Node.val <= 105
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link NextGreaterElementII_503}
 * extension {@link }
 * <p>
 *
 * Tags
 * -----
 * @medium
 * @LinkedList
 * @Stack
 * @Recursion
 * @MonotonicStack
 *
 *
 * <p>
 * Company Tags
 * -----
 *
 * @Editorial <a href="https://leetcode.com/problems/remove-nodes-from-linked-list/solutions/5602470/best-solution-step-by-step-improvement-o-2n-o-n-to-o-n-o-1-only/">...</a>
 */

public class RemoveNodesFromLinkedList_2487 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new Integer[]{5, 2, 13, 3, 8}, new Integer[]{13, 8});
        test &= test(new Integer[]{1, 1, 1, 1}, new Integer[]{1, 1, 1, 1});
        test &= test(
                new Integer[]{138,466,216,67,642,978,264,136,463,331,60,600,223,275,856,809,167,101,846,165,575,276,409,590,733,200,839,515,852,615,8,584,250,337,537,63,797,900,670,636,112,701,334,422,780,552,912,506,313,474,183,792,822,661,37,164,601,271,902,792,501,184,559,140,506,94,161,167,622,288,457,953,700,464,785,203,729,725,422,76,191,195,157,854,730,577,503,401,517,692,42,135,823,883,255,111,334,365,513,338,65,600,926,607,193,763,366,674,145,229,700,11,984,36,185,475,204,604,191,898,876,762,654,770,774,575,276,165,610,649,235,749,440,607,962,747,891,943,839,403,655,22,705,416,904,765,905,574,214,471,451,774,41,365,703,895,327,879,414,821,363,30,130,14,754,41,494,548,76,825,899,499,188,982,8,890,563,438,363,32,482,623,864,161,962,678,414,659,612,332,164,580,14,633,842,969,792,777,705,436,750,501,395,342,838,493,998,112,660,961,943,721,480,522,133,129,276,362,616,52,117,300,274,862,487,715,272,232,543,275,68,144,656,623,317,63,908,565,880,12,920,467,559,91,698},
                new Integer[]{998,961,943,920,698});

        System.out.println((test ? "All passed" : "Something failed"));
    }

    private static boolean test(Integer[] input, Integer[] expected) {
        ListNode inputList = ListBuilder.arrayToSinglyList(input);
        ListNode expectedList = ListBuilder.arrayToSinglyList(expected);
        System.out.println("\n Input :" + CommonMethods.print(inputList) + "\nexpected :" + CommonMethods.print(expectedList));

        RemoveNodesFromLinkedList.SolutionUsingStacksTwoScans solutionUsingTwoScans = new RemoveNodesFromLinkedList.SolutionUsingStacksTwoScans();
        RemoveNodesFromLinkedList.SolutionUsingStacksOneScans solutionUsingOneScans = new RemoveNodesFromLinkedList.SolutionUsingStacksOneScans();
        RemoveNodesFromLinkedList.SolutionWithoutUsingStacks solutionWithoutStacks = new RemoveNodesFromLinkedList.SolutionWithoutUsingStacks();

        ListNode obtainedTwoScan = solutionUsingTwoScans.removeNodes(ListBuilder.arrayToSinglyList(input));
        ListNode obtainedOneScan = solutionUsingOneScans.removeNodes(ListBuilder.arrayToSinglyList(input));
        ListNode obtainedWithoutStacks= solutionWithoutStacks.removeNodes(ListBuilder.arrayToSinglyList(input));
        ListNode obtainedWithoutStacksFaster = solutionWithoutStacks.removeNodesFaster(ListBuilder.arrayToSinglyList(input));
        System.out.println("obtainedTwoScan :" + CommonMethods.print(obtainedTwoScan));
        System.out.println("obtainedOneScan :" + CommonMethods.print(obtainedOneScan));
        System.out.println("obtainedWithoutStacks :" + CommonMethods.print(obtainedWithoutStacks));
        System.out.println("obtainedWithoutStacksFaster :" + CommonMethods.print(obtainedWithoutStacksFaster));
        boolean resultTwoScan = CommonMethods.equalsValues(expectedList, obtainedTwoScan);
        boolean resultOneScan = CommonMethods.equalsValues(expectedList, obtainedOneScan);
        boolean resultWithoutStacks = CommonMethods.equalsValues(expectedList, obtainedWithoutStacks);
        boolean resultWithoutStacksFaster = CommonMethods.equalsValues(expectedList, obtainedWithoutStacksFaster);
        System.out.println(" Result match : " + resultTwoScan + " | " + resultOneScan + " | " + resultWithoutStacks + " | " + resultWithoutStacksFaster);

        return resultTwoScan && resultOneScan && resultWithoutStacks && resultWithoutStacksFaster;
    }
}

class RemoveNodesFromLinkedList {

    /**
     *  The idea is inspired from the logic {@link DataStructureAlgo.Java.LeetCode2025.medium.NextGreaterElementI_496.NextGreaterElementI.SolutionUsingStack#nextGreaterElementValuesBackWards(int[])}
     *  In that solution, we follow the original in
     */
    static class SolutionWithoutUsingStacks {

        /**
         *
         *
         * @param head
         * @return
         */
        public ListNode removeNodes(ListNode head) {
            if (head == null || head.next == null)
                return head;

            ListNode reverseHead = reverse(head);
            ListNode prev = reverseHead;
            ListNode toBeDelete = reverseHead.next;

            while (toBeDelete != null) {

                //check if we want to delete this node or not.
                if (toBeDelete.val < prev.val) {
                    //delete this node.
                    prev.next = toBeDelete.next;
                } else {
                    prev = toBeDelete;
                }
                toBeDelete = toBeDelete.next;
            }

            if (reverseHead.next == null)
                return reverseHead;
            return reverse(reverseHead);

        }

        private ListNode reverse(ListNode head) {
            if (head == null || head.next == null)
                return head;

            ListNode current = head;
            ListNode next = current.next;
            current.next = null;
            ListNode cache;

            while (next != null) {
                cache = next.next;
                next.next = current;
                current = next;
                next = cache;
            }
            return current;
        }

        public ListNode removeNodesFaster(ListNode head) {
            if (head == null || head.next == null)
                return head;

            ListNode reverseHead = reverse(head);
            ListNode prev = reverseHead;
            ListNode current = reverseHead.next;

            //This solution will keep deleting the nodes and also revert the list to original form.
            while(current!=null){

                if(current.val < prev.val){
                    //this node needs to be deleted, hence ignore it and move ahead
                    current = current.next;
                }else {
                    //current node does not need to be removed, hence we need to remove all the node between
                    //prev & current node and reverse the link as well.

                    //cache next for the next iteration
                    ListNode next = current.next;

                    //reverse the link
                    current.next = prev;
                    prev = current;

                    //move to next
                    current = next;

                }

            }
            //Post reversing the list, the last node will be now reverseHead, hence next should be null.
            //Notice that, in above code we did not delete any node, however, we reverse the list from the point which node needs to be there in final list.
            // that left all the node which needs to be delete, next to reverseHead only.
            reverseHead.next = null;
            return prev;

        }

    }


    /**
     * Keep two stacks, one for getting nge and one for getting prev of last popped element
     */
    static class SolutionUsingStacksOneScans {
        public ListNode removeNodes(ListNode head) {
            if (head == null || head.next == null)
                return head;

            ListNode current = head, prev = null;

            Stack<ListNode> nge = new Stack<>();
            Stack<ListNode> prevStack = new Stack<>();

            //Push current node, as this will be under investigation
            nge.push(current);

            //there is no previous of current
            prevStack.push(null);

            //move to next node.
            prev = current;
            current = current.next;

            while (current != null) {

                //pop till we need to delete nodes
                while (!nge.isEmpty() && nge.peek().val < current.val) {

                    //get node to be deleted
                    ListNode nodeToDelete = nge.pop();

                    //prev of node to be deleted
                    ListNode prevNode = prevStack.pop();

                    //if we are going to delete head, updated head
                    if (nodeToDelete == head)
                        head = head.next;

                    //if there is a prev exist (only when its not head) then connect it.
                    if (prevNode != null) {
                        prevNode.next = current;

                    }
                    //now prevNode become previous of current node.
                    prev = prevNode;
                }

                nge.push(current);
                prevStack.push(prev);
                prev = current;
                current = current.next;
            }

            return head;

        }

    }

    /**
     * T/S: O(n)/O(2n)
     * Compute nge using {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.Stacks.NextGreaterElement.NextGreaterElementII.SolutionUsingStack3Scans#nextGreaterElementsOnRight(int[], int[])}
     * keep it in a hash and scan again the original list and delete node present in map.
     */
    static class SolutionUsingStacksTwoScans {
        public ListNode removeNodes(ListNode head) {
            if (head == null || head.next == null)
                return head;

            final Map<ListNode, Boolean> mapOfNodeToDelete = nextGreaterElement(head);
            ListNode current = head, prev = null;

            while (current != null) {

                ListNode next = current.next;
                if (mapOfNodeToDelete.getOrDefault(current, Boolean.FALSE)) {

                    if (current == head) { // head to be deleted.
                        head = head.next; //assign to new head
                    } else {
                        prev.next = current.next;
                    }
                } else {
                    prev = current;
                }
                current = next;
            }
            return head;

        }

        /**
         * T/S: O(n)/O(2n)
         * {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.Stacks.NextGreaterElement.NextGreaterElementII.SolutionUsingStack3Scans#nextGreaterElementsOnRight(int[], int[])}
         *
         * @param head
         * @return
         */
        private Map<ListNode, Boolean> nextGreaterElement(ListNode head) {
            if (head == null || head.next == null)
                return new HashMap<>();

            Map<ListNode, Boolean> mapOfNodeToDelete = new HashMap<>();
            ListNode current = head;

            Stack<ListNode> nge = new Stack<>();
            nge.push(current);
            current = current.next;

            while (current != null) {
                while (!nge.isEmpty() && nge.peek().val < current.val) {
                    mapOfNodeToDelete.put(nge.pop(), true);
                }
                nge.push(current);
                current = current.next;
            }


            return mapOfNodeToDelete;
        }


    }


}


