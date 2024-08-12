package DataStructureAlgo.Java.LeetCode2025.hard.List;

import DataStructureAlgo.Java.helpers.ListBuilder;
import DataStructureAlgo.Java.helpers.templates.ListNode;
import DataStructureAlgo.Java.LeetCode2025.easy.List.MergeTwoSortedLists_21;
import DataStructureAlgo.Java.helpers.GenericPrinter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.PriorityQueue;

/**
 * Author: Nitin Gupta
 * Date:08/08/24
 * Question Category: 23. Merge k Sorted Lists @hard
 * Description: https://leetcode.com/problems/merge-k-sorted-lists
 * <p>
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 *
 * Merge all the linked-lists into one sorted linked-list and return it.
 *
 *
 *
 * Example 1:
 *
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * Explanation: The linked-lists are:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * merging them into one sorted list:
 * 1->1->2->3->4->4->5->6
 * Example 2:
 *
 * Input: lists = []
 * Output: []
 * Example 3:
 *
 * Input: lists = [[]]
 * Output: []
 *
 *
 * Constraints:
 *
 * k == lists.length
 * 0 <= k <= 104
 * 0 <= lists[i].length <= 500
 * -104 <= lists[i][j] <= 104
 * lists[i] is sorted in ascending order.
 * The sum of lists[i].length will not exceed 104.
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.MergeKSortedLists}
 * Similar {@link}
 * extension {@link MergeTwoSortedLists_21}
 * <p>
 * Tags
 * -----
 * @hard
 * @LinkedList
 * @DivideandConquer
 * @Heap(PriorityQueue)
 * @MergeSort
 *
 *
 * <p>
 * Company Tags
 * -----
 * @Facebook
 * @Amazon
 * @Microsoft
 * @Apple
 * @Google
 *
 * @Editorial <a href="https://leetcode.com/problems/merge-k-sorted-lists/solutions/5606397/beat-100-simple-easy-to-explain-solution">...</a>
 */

public class MergeKSortedLists_23 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new Integer[][]{{1, 4, 5}, {1, 3, 4}, {2, 6}}, new Integer[]{1, 1, 2, 3, 4, 4, 5, 6});
        test &= test(new Integer[][]{{1, 2, 3}, {4, 5, 6}, {7, 8}}, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8});
        test &= test(new Integer[][]{{7, 8, 9}, {4, 5, 6}, {1, 2, 3}}, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        test &= test(new Integer[][]{{7, 8, 9}, {4, 5, 6}, {1, 2, 3}, {11, 12}}, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12});
        test &= test(new Integer[][]{{7}, {4}, {1}, {11}}, new Integer[]{1, 4, 7, 11});
        test &= test(new Integer[][]{{7}, {4}, {1}, {11, 12, 13, 14}}, new Integer[]{1, 4, 7, 11, 12, 13, 14});
        test &= test(new Integer[][]{{0}, {0}, {0}, {11, 12, 13, 14}}, new Integer[]{0, 0, 0, 11, 12, 13, 14});
        test &= test(new Integer[][]{{0}, {0}}, new Integer[]{0, 0});
        test &= test(new Integer[][]{{0}, {null}}, new Integer[]{0});
        System.out.println((test ? "All passed" : "Something failed"));

    }

    private static boolean test(Integer[][] list, Integer[] expected) {

        MergeKSortedLists.SolutionUsingPriorityQueue pq = new MergeKSortedLists.SolutionUsingPriorityQueue();
        MergeKSortedLists.SolutionKWayMergeIterative kWayMergeIterative = new MergeKSortedLists.SolutionKWayMergeIterative();

        ListNode[] listNode = Arrays.stream(list).map(ListBuilder::arrayToSinglyList).toArray(ListNode[]::new);
        ListNode expectedList = ListBuilder.arrayToSinglyList(expected);

        System.out.println("\nInput: ");
        Arrays.stream(listNode).map(GenericPrinter::print).forEach(System.out::println);
        System.out.println("Expected: "+GenericPrinter.print(expectedList));

        ListNode headPQ = pq.mergeKLists(Arrays.stream(list).map(ListBuilder::arrayToSinglyList).toArray(ListNode[]::new));
        ListNode headKWayIterative = kWayMergeIterative.mergeKLists(Arrays.stream(list).map(ListBuilder::arrayToSinglyList).toArray(ListNode[]::new));
        System.out.println("PQ -> " + GenericPrinter.print(headPQ));
        System.out.println("KWayIterative -> " + GenericPrinter.print(headKWayIterative));
        boolean testPQ = GenericPrinter.equalsValues(headPQ, expectedList);
        boolean testKWayIterative = GenericPrinter.equalsValues(headKWayIterative, expectedList);

        System.out.println("PQ | KWayIterative: " + testPQ + " | " + testKWayIterative);
        return testPQ && testKWayIterative;
    }
}

class MergeKSortedLists {
    /**
     * 1. Simply push first node all the list in pq. Maintain pq as min heap
     * 2. Extract top element from pq, and append to last final list tail.
     * 3. move to next element of the list whose node extracted from pq.
     * 4. Repeat till all the element process. if PQ still have some element, simply append at the end of final list.
     * T/S: O(n*logk) / O(k) ; n is total number of elements in all the list
     *
     */
    static class SolutionUsingPriorityQueue {

        public ListNode mergeKLists(ListNode[] lists) {
            if(lists == null || lists.length ==0)
                return null;
            if(lists.length == 1)
                return lists[0];

            final PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o-> o.val));

            //enqueue first element for all the list
            // O(k*logK) ; since we are not given an array to pq directly, hence it will take O(k*logK)
            Arrays.stream(lists).filter(Objects::nonNull).forEach(priorityQueue::offer);
//            for(ListNode list: lists)
//                if(list!=null)
//                    priorityQueue.offer(list);

            ListNode resultHead = null, tail=null;

            //O(n*logK) ; n is total number of elements in all the list
            while(!priorityQueue.isEmpty()){

                final ListNode current = priorityQueue.poll();

                if(resultHead == null) {
                    resultHead = current;
                    tail = current;
                }else{
                    tail.next = current;
                    tail = tail.next;
                }

                Optional.ofNullable(current.next).ifPresent(priorityQueue::offer);
//                if(current.next!=null)
//                    priorityQueue.offer(current.next);
            }

            return resultHead;

        }
    }


    /**
     * In order to do the merge of k list, we have to divide the list in chunk of two list merging.
     * One way of doing is, first merging, first two list and then merge the resultant list to next list and so on.
     * Doing this way, will make a binary tree, left skewed. Which is not optimal in some cases.
     *
     * the other way is merging, by making complete binary tree. Which is merging first two list, merging next two list and so on.
     * And apply the same to resultant list as well.
     * This way this will become complete binary tree, which has height log(n).
     *
     * This is also known as Tournament binary tree.
     *
     * Now inorder to proceed with this solution, we have to from height 1 to height log(n) base 2. which can be done by height = 1, height = height*2
     * Since we should not cross the log(n) height, the outer boundary would be size of list node array.
     *
     * Each time we'll pick the current and the current+height lists and merge them keeping the resultant list at current position.
     */
    static class SolutionKWayMergeIterative {

        public ListNode mergeKLists(ListNode[] lists) {
            if(lists == null || lists.length ==0)
                return null;
            if(lists.length == 1)
                return lists[0];


            int height = 1;

            while (height < lists.length){

                for (int i=0; i+height < lists.length; i += 2*height){
                    lists[i] = merge2List(lists[i], lists[i+height]);
                }
                height *=2;
            }

            return lists[0];
        }

        private ListNode merge2List(ListNode first, ListNode second){
            if(first == null)
                return second;
            if(second == null)
                return first;

            ListNode head, tail;


            if(first.val <= second.val ){
                head = tail = first;
                first = first.next;
            }else{
                head = tail = second;
                second = second.next;
            }

            while (first!=null && second!=null){

                if(first.val <= second.val){
                    tail.next = first;
                    first = first.next;
                }else{
                    tail.next = second;
                    second = second.next;
                }
                tail = tail.next;
            }

            if(first!=null)
                tail.next = first;
            else
                tail.next = second;

            return head;
        }
    }


}