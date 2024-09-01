package DataStructureAlgo.Java.LeetCode2025.ProblemSet.List;


import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.ListBuilder;
import DataStructureAlgo.Java.helpers.templates.DoublyListNode;
import DataStructureAlgo.Java.helpers.templates.ListNode;

/**
 * Author: Nitin Gupta
 * Date: 2024-07-27
 * Question Category: 21. Merge Two Sorted Lists [EASY]
 * Description: https://leetcode.com/problems/merge-two-sorted-lists
 *  *
 *  * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
 *  * <p>
 *  * Example:
 *  * <p>
 *  * Input: 1->2->4, 1->3->4
 *  * Output: 1->1->2->3->4->4
 * <p>
 * File reference
 * -----------
 * Duplicate {@link MergeTwoSortedLists_21}
 * Similar {@link}
 * <p>
 * Tags
 * -----
 * @medium
 * @LinkedList
 * @Recursion
 *
 * <p>
 * Company Tags
 * -----
 * @Microsoft
 * @Amazon
 * @Facebook
 * @Adobe
 * @Oracle
 *
 * @Editorial
 */


public class MergeTwoSortedLists_21 {

    public static void main(String[] args) {
        test(new Integer[]{1, 2, 4}, new Integer[]{1, 3, 4}, new Integer[]{1, 1, 2, 3, 4, 4});
        test(new Integer[]{1, 2, 4, 6, 9}, new Integer[]{1, 3, 4}, new Integer[]{1, 1, 2, 3, 4, 4, 6, 9});
        test(new Integer[]{1, 2, 3, 4, 5}, new Integer[]{6, 7, 8}, new Integer[]{1,2, 3, 4, 5, 6, 7, 8});
    }

    private static void test(Integer[] l1, Integer[] l2, Integer[] expected) {
        testSingly(ListBuilder.arrayToSinglyList(l1), ListBuilder.arrayToSinglyList(l2), ListBuilder.arrayToSinglyList(expected));
        testDoubly(ListBuilder.arrayToDoublyListNode(l1), ListBuilder.arrayToDoublyListNode(l2), ListBuilder.arrayToDoublyListNode(expected));
    }

    private static void testSingly(ListNode l1, ListNode l2, ListNode expected) {
        System.out.println("\nSingly \n  L1 :" + CommonMethods.print(l1) + " L2 :" + CommonMethods.print(l1) + "\n expected :" + CommonMethods.print(expected));

        MergeTwoSortedSinglyLists singlyLists = new MergeTwoSortedSinglyLists();
        System.out.println(" Recursive :" + CommonMethods.print(singlyLists.mergeTwoListsRecursive(ListBuilder.copyOf(l1), ListBuilder.copyOf(l2))));
        System.out.println(" Iterative :" + CommonMethods.print(singlyLists.mergeTwoListsIterative(ListBuilder.copyOf(l1), ListBuilder.copyOf(l2))));

    }

    private static void testDoubly(DoublyListNode l1, DoublyListNode l2, DoublyListNode expected) {
        System.out.println("\nDoubly \n L1 :" + CommonMethods.print(l1) + " L2 :" + CommonMethods.print(l1) + "\n expected :" + CommonMethods.print(expected));

        MergeTwoSortedDoublyLists doublyLists = new MergeTwoSortedDoublyLists();
        System.out.println(" Recursive :" + CommonMethods.print(doublyLists.mergeTwoListsRecursive(ListBuilder.copyOf(l1), ListBuilder.copyOf(l2))));
        System.out.println(" Iterative :" + CommonMethods.print(doublyLists.mergeTwoListsIterative(ListBuilder.copyOf(l1), ListBuilder.copyOf(l2))));

    }

    static class MergeTwoSortedSinglyLists {
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            return mergeTwoListsIterative(list1,list2);
        }

        /**
         * https://leetcode.com/problems/merge-two-sorted-lists/submissions/1334884555/
         * @param list1
         * @param list2
         * @return
         */
        public ListNode mergeTwoListsIterative(ListNode list1, ListNode list2) {
            if(list1 == null)
                return list2;
            if(list2 == null)
                return list1;

            ListNode finalList, resultListHead;
            if (list1.val <= list2.val){
                finalList = list1;
                list1 = list1.next;
            }else{
                finalList = list2;
                list2 = list2.next;
            }
            resultListHead = finalList;
            while (list1!=null && list2!=null){

                if (list1.val <= list2.val){
                    finalList.next = list1;
                    list1 = list1.next;
                }else{
                    finalList.next = list2;
                    list2 = list2.next;
                }
                finalList = finalList.next;
            }
            finishOff(list1,finalList);
            finishOff(list2,finalList);

            return resultListHead;
        }

        private void finishOff(ListNode head, ListNode finalList){
            if (head!=null)
                finalList.next = head;

        }



        public ListNode mergeTwoListsRecursive(ListNode list1, ListNode list2) {

            if(list1 == null)
                return list2;

            if (list2 == null)
                return list1;

            ListNode result ;

            if(list1.val <= list2.val){
                result = list1;
                list1.next = mergeTwoListsRecursive(list1.next, list2);
            }else {
                result = list2;
                list2.next = mergeTwoListsRecursive(list1, list2.next);
            }
            return result;

        }
    }
    static class MergeTwoSortedDoublyLists {
        public DoublyListNode mergeTwoLists(DoublyListNode list1, DoublyListNode list2) {
            return mergeTwoListsIterative(list1,list2);
        }


        public DoublyListNode mergeTwoListsRecursive(DoublyListNode list1, DoublyListNode list2) {
            if(list1 == null)
                return list2;

            if(list2 == null)
                return list1;

            DoublyListNode result ;

            if(list1.val <= list2.val){
                result = list1;
                list1.next = mergeTwoListsRecursive(list1.next, list2);
            }else {
                result = list2;
                list2.next = mergeTwoListsRecursive(list1,list2.next);
            }
            result.next.prev = result;
            return result;
        }

        private DoublyListNode mergeTwoListsIterative(DoublyListNode list1, DoublyListNode list2) {

            if(list1 == null)
                return list2;

            if(list2 == null)
                return list1;

            DoublyListNode finalList, resultHead;

            if(list1.val <= list2.val){
               finalList = list1;
               list1 = list1.next;
            }else {
                finalList = list2;
                list2 = list2.next;
            }
            resultHead = finalList;

            while (list1!=null && list2!=null){

                if(list1.val <= list2.val){
                    finalList.next = list1;
                    list1.prev = finalList;
                    list1 = list1.next;
                }else {
                    finalList.next = list2;
                    list2.prev = finalList;
                    list2 = list2.next;
                }
                finalList  = finalList.next;
            }

            if(list1!=null){
                finalList.next = list1;
                list1.prev = finalList;
            }
            if(list2!=null){
                finalList.next = list2;
                list2.prev = finalList;
            }

            return resultHead;
        }
    }
}


