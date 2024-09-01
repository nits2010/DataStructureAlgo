package DataStructureAlgo.Java.LeetCode2025.ProblemSet.List.AddList;


import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.ListBuilder;
import DataStructureAlgo.Java.helpers.templates.ListNode;

import java.util.Stack;

/**
 * Author: Nitin Gupta
 * Date: 08/01/2024
 * Question Category: 445. Add Two Numbers II [medium]
 * Description: https://leetcode.com/problems/add-two-numbers-ii/description/
 * <p>
 * You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: l1 = [7,2,4,3], l2 = [5,6,4]
 * Output: [7,8,0,7]
 * Example 2:
 *
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [8,0,7]
 * Example 3:
 *
 * Input: l1 = [0], l2 = [0]
 * Output: [0]
 *
 *
 * Constraints:
 *
 * The number of nodes in each linked list is in the range [1, 100].
 * 0 <= Node.val <= 9
 * It is guaranteed that the list represents a number that does not have leading zeros.
 *
 *
 * Follow up: Could you solve it without reversing the input lists?
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.AddTwoNumbersList.AddTwoNumbersListListII}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 * @medium
 * @LinkedList
 * @Math
 * @Stack
 *
 * <p>
 * Company Tags
 * -----
 * @Bloomberg
 * @Microsoft
 * @Amazon
 * @Oracle

 *
 * @Editorial <a href="https://leetcode.com/problems/add-two-numbers-ii/solutions/5567977/multiple-solution-easy-explanation-beats-100-basic-school-solution-too/">...</a>
 *
 */
public class AddTwoNumbersII_445 {
    public static void main(String[] args) {
        boolean testResult = true;
        testResult &= test(new Integer[]{7,2,4,3}, new Integer[]{5,6,4}, new Integer[]{7,8,0,7});
        testResult &= test(new Integer[]{2,4,3}, new Integer[]{5,6,4}, new Integer[]{8,0,7});
        testResult &= test(new Integer[]{0}, new Integer[]{0}, new Integer[]{0});
        testResult &= test(new Integer[]{9,9,9,9,9,9,9}, new Integer[]{9,9,9,9}, new Integer[]{1,0,0,0,9,9,9,8});
        System.out.println("\n\nAll tests passed: " + testResult);
    }

    private static boolean test(Integer[] l1, Integer[] l2, Integer[] expected) {
        ListNode l1Node = ListBuilder.arrayToSinglyList(l1);
        ListNode l2Node = ListBuilder.arrayToSinglyList(l2);
        ListNode expectedNode = ListBuilder.arrayToSinglyList(expected);
        System.out.println("\n L1 :" + CommonMethods.print(l1Node) + "\n L2 :" + CommonMethods.print(l2Node) + "\n Expected :" + CommonMethods.print(expectedNode));

        AddTwoNumbersIIUsingReverse.Solution solutionUsingReverse = new AddTwoNumbersIIUsingReverse.Solution();
        AddTwoNumbersIIUsingStack.Solution solutionUsingStacks = new AddTwoNumbersIIUsingStack.Solution();
        AddTwoNumbersIIBasicMethod.Solution solutionUsingBasic = new AddTwoNumbersIIBasicMethod.Solution();

        ListNode outputUsingStack = solutionUsingStacks.addTwoNumbers(l1Node, l2Node);
        ListNode outputUsingBasic = solutionUsingBasic.addTwoNumbers(l1Node, l2Node);
        ListNode outputUsingReverse = solutionUsingReverse.addTwoNumbers(l1Node, l2Node);

        boolean resultStack = CommonMethods.equalsValues(outputUsingStack, expectedNode);
        boolean resultSReverse = CommonMethods.equalsValues(outputUsingReverse, expectedNode);
        boolean resultBasic = CommonMethods.equalsValues(outputUsingBasic, expectedNode);

        System.out.println("Obtained Stack:" + CommonMethods.print(outputUsingStack));
        System.out.println("Obtained Reverse:" + CommonMethods.print(outputUsingReverse));
        System.out.println("Obtained Basic:" + CommonMethods.print(outputUsingBasic));

        System.out.println("Test passed: " + resultStack + "|" + resultSReverse + "|"+ resultBasic);
        return (resultStack == resultSReverse)==resultBasic;
    }
}

/**
 * O(n+m) / O(1) solution
 * Reverse both list, add them using {@link DataStructureAlgo.Java.LeetCode.AddTwoNumbersList.AddTwoNumbersListI}
 * and reverse resultant list.
 * 1 ms Beats 100.00%
 */
class AddTwoNumbersIIUsingReverse {

    static class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            if ( l1 == null)
                return l2;
            if(l2 == null)
                return l1;

            l1 = reverse(l1);
            l2 = reverse(l2);
            return reverse(addNumbersInList(l1, l2));

        }

        private ListNode addNumbersInList(ListNode l1, ListNode l2){
            if ( l1 == null)
                return l2;
            if(l2 == null)
                return l1;

            ListNode resultHead = null;
            ListNode resultTail = null;
            int carry = 0 ;

            while (l1 != null || l2!=null){
                int v1 = l1 == null ? 0: l1.val;
                int v2 = l2 == null ? 0: l2.val;

                int sum = v1 + v2 + carry;
                carry = sum/10;
                sum = sum % 10;

                ListNode node = new ListNode(sum);
                if(resultTail == null){
                    resultHead = node;
                }else{
                    resultTail.next = node;
                }
                resultTail = node;
                l1 = l1 == null ? null : l1.next;
                l2 = l2 == null ? null : l2.next;
            }

            if (carry > 0){
                resultTail.next = new ListNode(carry);
            }
            return resultHead;

        }


        /**
         * {@link DataStructureAlgo.Java.LeetCode.ReverseLinkedList}
         * @param head
         * @return
         * 1-2-3-4
         * c=1, p=null, n=2 ; 1 2-3-4
         * p=1, c=2, n=3; 2-1 3-4;
         * p=2, c=3, n=4; 3-2-1 4
         * p=3, c=4, n=null; 4-3-2-1
         * p=4, c=null, n=null; 4-3-2-1
         */
        private ListNode reverse(ListNode head){
            if(head == null || head.next == null)
                return head;

            ListNode prev = null;
            ListNode current = head;
            ListNode next = head.next;
            current.next = null;

            while (current!=null){
                //join current node to previous list at head
                current.next = prev;

                //update prev list head
                prev = current;

                //move to next chunk
                current = next;
                if(next!=null)
                    next = next.next;
            }
            return prev;
        }

    }
}

/**
 * O(n+m) / O(n+m) solution
 * Push both list in stack, so that they are reverse when pop. Build new list using stacks and keep adding at head.
 * 1 ms Beats 100.00%
 */
class AddTwoNumbersIIUsingStack {
    static class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            if ( l1 == null)
                return l2;
            if(l2 == null)
                return l1;

            Stack<ListNode> first = stackUpList(l1);
            Stack<ListNode> second = stackUpList(l2);

            ListNode resultHead = null;
            int carry = 0;

            while (!first.isEmpty() || !second.isEmpty()){
                int v1 = first.isEmpty() ? 0: first.pop().val;
                int v2 = second.isEmpty() ? 0: second.pop().val;
                int sum = v1+v2+carry;
                carry = sum/10;
                sum = sum % 10;

                //add new node at head of list, to keep most significant digit at first
                ListNode node = new ListNode(sum);
                node.next = resultHead;
                resultHead = node;
            }

            if(carry >0){
                ListNode node = new ListNode(carry);
                node.next = resultHead;
                resultHead = node;
            }
            return resultHead;

        }

        private Stack<ListNode> stackUpList(ListNode head){
            if(head == null)
                return null;

            final Stack<ListNode> stack = new Stack<>();
            while (head!=null){
                stack.push(head);
                head = head.next;
            }
            return stack;
        }
    }
}


/**
 * Another interesting solution without doing reverse and using stack (which does reverse actually).
 * Intuition:
 * 1. One this is to that, if bot the list are of same size, then we can simply add them but in order to keep the most significant digit at first, we need to reverse the list.
 * So instead of reversing the list, we can use recursion ( that is implicit reverse only ).
 * 2. if list size are not same, then we can move bigger list ahead and make them equal and them apply step 1.
 * Post that, we can simply add remaining numbers ( from the bigger list ) to our resultant list.
 *
 * This is the method we follow when we add two numbers right? :)
 */
class AddTwoNumbersIIBasicMethod {

    /**
     * l1 = 1 - 9 - 8 - 9 - 8 [5]
     * l2 =             9 - 9 [2]
     * e =  1 - 9 - 9 - 9 - 7
     * rh = l1, rt = l1, diff = 5-2 = 3
     * rt = 1, 3 > 1
     * rt = 9, 2 > 1
     * rt = 8, 1 > 1
     *
     * rh = 1, rt = 8 [1-9-8], l1 = [9-8], l2 = [9-9]
     * addSameSizeList([9-8], [9-9]) => addSameSizeList(9,9,[])
     * ->addSameSizeList(8,9,[])
     * ->carry = addSameSizeList(null,null,[]) -> ([null],0)
     * ->carry = 0 => addSameSizeList(8,9,[null])
     * ->sum = 8+9+0 = 17, carry = 1, sum = 7 => [7]; return 1
     *
     * ->carry = 1 => addSameSizeList(9,9,[7])
     * ->sum = 9+9+1 = 19, carry = 1, sum = 9 => [9,7]; return 1
     * return 1;
     *
     * propagateCarry([1-9-8],[1],[9,7])
     * ->propagateCarry([9-8],[1],[9,7])
     * ->propagateCarry([8],[1],[9,7])
     * ->propagateCarry([null],[1],[9,7]) return
     * ->propagateCarry([8],[1],[9,7])
     * ->sum = 8+1 = 9, carry = 0, sum = 9 => [9,9,7] [0]
     * ->propagateCarry([9-8],[0],[9,9,7])
     * ->sum = 9+0 = 9, carry = 0, sum = 9 => [9,9,9,7] [0]
     * propagateCarry([1-9-8],[0],[9,9,9,7])
     * ->sum = 1+0 = 1, carry = 0, sum = 1 => [1,9,9,9,7] [0]
     *
     * carry=0
     *
     * Result -> [1,9,9,9,7]
     *
     *
     *
     *
     *
     */
    static class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            if (l1 == null)
                return l2;
            if (l2 == null)
                return l1;

            int[] size = size(l1, l2);

            ListNode remainingElementsHead = null;
            ListNode remainingElementTail = null;

            //check if the size are same or not
            if (size[0] != size[1]) {
                //not same, move bigger list ahead

                if (size[0] > size[1]) {
                    remainingElementsHead = remainingElementTail = l1;
                } else {
                    remainingElementsHead = remainingElementTail = l2;
                }

                int diff = Math.abs(size[0] - size[1]);

                //diff > 1 so that we can get the previous node of bigger list, to detach it later
                while (remainingElementTail != null && diff > 1) {
                    remainingElementTail = remainingElementTail.next;
                    diff--;
                }

                //update list l1/l2
                if (remainingElementTail != null) {
                    if (size[0] > size[1]) {
                        l1 = remainingElementTail.next;
                    } else {
                        l2 = remainingElementTail.next;
                    }
                    //detach it, we'll attach it later to retain the original list
                    remainingElementTail.next = null;
                }

            }

            //add same size list;
            ListNode[] resultHead = {null};
            int carry = addSameSizeList(l1, l2, resultHead);

            //if they were of not same size, then add remaining elements
            int[] c = {carry};
            propagateCarry(remainingElementsHead, c, resultHead);
            carry = c[0];

            if (carry > 0) {
                ListNode node = new ListNode(carry);
                node.next = resultHead[0];
                resultHead[0] = node;
            }

            //retain list
            if (remainingElementTail != null) {

                if (size[0] > size[1]) {
                    remainingElementTail.next = l1;
                    l1= remainingElementsHead;
                }
                else {
                    remainingElementTail.next = l2;
                    l2 = remainingElementsHead;
                }
            }

            return resultHead[0];
        }

        private int addSameSizeList(ListNode l1, ListNode l2, ListNode[] resultHead) {
            if (l1 == null) {
                resultHead[0] = l2;
                return 0;
            }
            if (l2 == null) {
                resultHead[0] = l1;
                return 0;
            }

            int carry = addSameSizeList(l1.next, l2.next, resultHead);

            int sum = l1.val + l2.val + carry;
            carry = sum / 10;
            sum = sum % 10;

            ListNode node = new ListNode(sum);
            if (resultHead[0] != null) {
                node.next = resultHead[0];
            }
            resultHead[0] = node;
            return carry;
        }



        private void propagateCarry(ListNode current, int[] carry, ListNode[] resultHead) {
            if (current == null)
                return;

            propagateCarry(current.next, carry, resultHead);

            int sum = current.val + carry[0];
            carry[0] = sum / 10;
            sum = sum % 10;

            ListNode node = new ListNode(sum);
            if (resultHead[0] != null) {
                node.next = resultHead[0];
            }
            resultHead[0] = node;
        }

        private int[] size(ListNode l1, ListNode l2) {
            int size1 = 0;
            int size2 = 0;

            while (l1 != null && l2 != null) {
                size1++;
                size2++;
                l1 = l1.next;
                l2 = l2.next;
            }

            while (l1 != null) {
                size1++;
                l1 = l1.next;
            }

            while (l2 != null) {
                size2++;
                l2 = l2.next;
            }


            return new int[]{size1, size2};
        }

    }
}