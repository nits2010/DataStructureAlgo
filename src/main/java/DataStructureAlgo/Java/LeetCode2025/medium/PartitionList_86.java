package DataStructureAlgo.Java.LeetCode2025.medium;


import DataStructureAlgo.Java.LeetCode.listToBST.ListBuilder;
import DataStructureAlgo.Java.LeetCode.templates.ListNode;
import DataStructureAlgo.Java.helpers.GenericPrinter;

/**
 * Author: Nitin Gupta
 * Date: 08/01/2024
 * Question Category: 86. Partition List [medium]
 * Description:https://leetcode.com/problems/partition-list
 * <p>
 * Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 *
 * You should preserve the original relative order of the nodes in each of the two partitions.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,4,3,2,5,2], x = 3
 * Output: [1,2,2,4,3,5]
 * Example 2:
 *
 * Input: head = [2,1], x = 2
 * Output: [1,2]
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [0, 200].
 * -100 <= Node.val <= 100
 * -200 <= x <= 200
 *
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
 * @LinkedList
 * @TwoPointers
 *
 * <p>
 * Company Tags
 * -----
 * @Adobe
 *
 * @Editorial
 */
public class PartitionList_86 {

    public static void main(String[] args) {
        boolean testResult = true;
//        testResult &= test(new Integer[]{1,4,3,2,5,2}, 3, new Integer[]{1,2,2,4,3,5});
//        testResult &= test(new Integer[]{2,1}, 2, new Integer[]{1,2});
        testResult &= test(new Integer[]{1}, 0, new Integer[]{1});
        System.out.println("Tests passed : " + (testResult ? "Yes" : "No"));
    }

    private static boolean test(Integer[] input, int x,  Integer[] expected) {
        ListNode head = ListBuilder.arrayToSinglyList(input);
        ListNode expectedNode = ListBuilder.arrayToSinglyList(expected);

        System.out.println("\nInput :" + GenericPrinter.print(head) + " x : "+ x + " expected :" + GenericPrinter.print(expectedNode));

        PartitionList.Solution solution = new PartitionList().new Solution();

        ListNode output = solution.partition(head, x);
        System.out.println("Obtained :" + GenericPrinter.print(output));
        boolean testResult = GenericPrinter.equalsValues(expectedNode, output);
        System.out.println("Test passed: " + testResult );
        return (testResult);
    }

}

class PartitionList{
    //build different list of less than x and greater than x and then merge them
    class Solution {
        public ListNode partition(ListNode head, int x) {
            if(head == null)
                return head;

            ListNode lessThanXHead = null;
            ListNode lessThanXTail = null;
            ListNode greaterEqualToXHead = null;
            ListNode greaterEqualToXTail = null;

            ListNode current = head;
            ListNode next;

            while (current!=null){

                next = current.next;
                current.next = null;

                if(current.val < x){
                    if(lessThanXTail!=null){
                        lessThanXTail.next = current;
                    }
                    lessThanXTail = current;

                    if(lessThanXHead == null)
                        lessThanXHead = current;
                }else{
                    if(greaterEqualToXTail!=null){
                        greaterEqualToXTail.next = current;
                    }
                    greaterEqualToXTail = current;

                    if(greaterEqualToXHead == null)
                        greaterEqualToXHead = current;
                }
                current = next;
            }

            if(lessThanXTail!=null){
                lessThanXTail.next = greaterEqualToXHead;
            }else{
                return greaterEqualToXHead;
            }
            return lessThanXHead;
        }
    }
}