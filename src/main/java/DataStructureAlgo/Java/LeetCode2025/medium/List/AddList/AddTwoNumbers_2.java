package DataStructureAlgo.Java.LeetCode2025.medium.List.AddList;


import DataStructureAlgo.Java.helpers.ListBuilder;
import DataStructureAlgo.Java.helpers.templates.ListNode;
import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date: 08/01/2024
 * Question Category: 2. Add Two Numbers [medium]
 * Description: https://leetcode.com/problems/add-two-numbers
 * <p>
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807.
 * Example 2:
 *
 * Input: l1 = [0], l2 = [0]
 * Output: [0]
 * Example 3:
 *
 * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * Output: [8,9,9,9,0,0,0,1]
 *
 *
 * Constraints:
 *
 * The number of nodes in each linked list is in the range [1, 100].
 * 0 <= Node.val <= 9
 * It is guaranteed that the list represents a number that does not have leading zeros.
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.AddTwoNumbersList.AddTwoNumbersListI}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 * @medium
 * @LinkedList
 * @Math
 * @Recursion
 *
 *
 * <p>
 * Company Tags
 * -----
 * @Amazon
 * @Apple
 * @Facebook
 * @Microsoft
 * @Bloomberg

 *
 * @Editorial
 */
public class AddTwoNumbers_2 {

    public static void main(String[] args) {
        boolean testResult = true;
        testResult &= test(new Integer[]{2,4,3}, new Integer[]{5,6,4}, new Integer[]{7,0,8});
        testResult &= test(new Integer[]{0}, new Integer[]{0}, new Integer[]{0});
        testResult &= test(new Integer[]{9,9,9,9,9,9,9}, new Integer[]{9,9,9,9}, new Integer[]{8,9,9,9,0,0,0,1});
        System.out.println("\n\nAll tests passed: " + testResult);
    }

    private static boolean test(Integer[] l1, Integer[] l2, Integer[] expected) {
        ListNode l1Node = ListBuilder.arrayToSinglyList(l1);
        ListNode l2Node = ListBuilder.arrayToSinglyList(l2);
        ListNode expectedNode = ListBuilder.arrayToSinglyList(expected);
        Solution solution = new Solution();
        ListNode output = solution.addTwoNumbers(l1Node, l2Node);
        boolean result = CommonMethods.equalsValues(output, expectedNode);
        System.out.println("\n L1 :" + CommonMethods.print(l1Node) + "\n L2 :" + CommonMethods.print(l2Node) + "\n Expected :" + CommonMethods.print(expectedNode));
        System.out.println("Obtained :" + CommonMethods.print(output));
        System.out.println("Test passed: " + result);
        return result;
    }


    static class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

            if(l1==null)
                return l2;
            if(l2 == null)
                return l1;

            ListNode first = l1;
            ListNode second = l2;
            ListNode resultHead = null;
            ListNode temp = null;
            int carry = 0 ;

            while (first!=null || second!=null){

                //sum of current nodes
                int v1 = first == null ? 0 : first.val;
                int v2 = second == null ? 0 : second.val;
                int sum = v1+ v2 + carry;

                //calculate carry and adjust sum as per that
                carry = sum / 10;
                sum = sum % 10;

                ListNode node = new ListNode(sum);

                //if this is the head of new list
                if(resultHead == null){
                    resultHead = node;
                }

                //append node in existing list
                if(temp!=null){
                    temp.next = node;
                    temp = node;
                }else{
                    temp = node;
                }

                //if first/second list exhausted, but second/first left
                first = first == null ? null : first.next;
                second = second == null ? null: second.next;
            }


            if(carry>0){
                temp.next = new ListNode(carry);
            }

            return resultHead;


        }

    }
}
