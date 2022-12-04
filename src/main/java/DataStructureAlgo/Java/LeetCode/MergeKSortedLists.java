package DataStructureAlgo.Java.LeetCode;

import  DataStructureAlgo.Java.helpers.GenericPrinter;
import  DataStructureAlgo.Java.LeetCode.listToBST.ListBuilder;
import  DataStructureAlgo.Java.LeetCode.templates.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-16
 * Description: https://leetcode.com/problems/merge-k-sorted-lists/
 * <p>
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 * <p>
 * Example:
 * <p>
 * Input:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
 * <p>
 * Extension of {@link DataStructureAlgo.Java.LeetCode.MergeTwoSortedLists}
 */
public class MergeKSortedLists {

    public static void main(String[] args) {

        testPQ(new ListNode[]{ListBuilder.arrayToSinglyList(new Integer[]{1, 4, 5}), ListBuilder.arrayToSinglyList(new Integer[]{1, 3, 4}), ListBuilder.arrayToSinglyList(new Integer[]{2, 6})});
        testKWay(new ListNode[]{ListBuilder.arrayToSinglyList(new Integer[]{1, 4, 5}), ListBuilder.arrayToSinglyList(new Integer[]{1, 3, 4}), ListBuilder.arrayToSinglyList(new Integer[]{2, 6})});

        testPQ(new ListNode[]{ListBuilder.arrayToSinglyList(new Integer[]{1, 4, 5}), ListBuilder.arrayToSinglyList(new Integer[]{1, 3, 4}), ListBuilder.arrayToSinglyList(new Integer[]{2, 6}), ListBuilder.arrayToSinglyList(new Integer[]{1, 2, 3, 4, 5, 6, 6}), ListBuilder.arrayToSinglyList(new Integer[]{0, 3, 6, 8, 10})});
        testKWay(new ListNode[]{ListBuilder.arrayToSinglyList(new Integer[]{1, 4, 5}), ListBuilder.arrayToSinglyList(new Integer[]{1, 3, 4}), ListBuilder.arrayToSinglyList(new Integer[]{2, 6}), ListBuilder.arrayToSinglyList(new Integer[]{1, 2, 3, 4, 5, 6, 6}), ListBuilder.arrayToSinglyList(new Integer[]{0, 3, 6, 8, 10})});
    }

    private static void testPQ(ListNode[] list) {
        IMergeKSortedLists pq = new MergeKSortedListsPriorityQueue();
        System.out.println("\n Input : " + GenericPrinter.print(list));
        System.out.println("PQ->");
        ListNode head = pq.mergeKLists(list);
        System.out.println(GenericPrinter.print(head));
    }

    private static void testKWay(ListNode[] list) {
        IMergeKSortedLists kWayMerge = new MergeKSortedListsDC();
        System.out.println("\n Input : " + GenericPrinter.print(list));


        System.out.println("KWay->");
        ListNode head = kWayMerge.mergeKLists(list);
        System.out.println(GenericPrinter.print(head));

    }
}


interface IMergeKSortedLists {
    ListNode mergeKLists(ListNode[] lists);

}

/**
 * Using Priority queue
 * O(n*Log(k)) / O(k)
 * Runtime: 38 ms, faster than 23.73% of Java online submissions for Merge k Sorted Lists.
 * Memory Usage: 39.5 MB, less than 78.69% of Java online submissions for Merge k Sorted Lists.
 */
class MergeKSortedListsPriorityQueue implements IMergeKSortedLists {

    @Override
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;

        ListNode resultHead = null;
        ListNode tail = null;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        int n = lists.length;


        //Queue first node of all the list
        for (ListNode list : lists)
            if (list != null)
                pq.offer(list);


        while (!pq.isEmpty()) {

            ListNode temp = pq.poll();

            if (resultHead == null) {
                resultHead = temp;
                tail = resultHead;
            } else {
                tail.next = temp;
                tail = tail.next;
            }

            ListNode next = temp.next;
            if (next != null)
                pq.offer(next);
        }


        return resultHead;
    }
}

/**
 * O(n*Log(k)) / O(1)
 * K-Way Merge Sort
 * Runtime: 2 ms, faster than 98.73% of Java online submissions for Merge k Sorted Lists.
 * Memory Usage: 39.1 MB, less than 86.34% of Java online submissions for Merge k Sorted Lists.
 */
class MergeKSortedListsDC implements IMergeKSortedLists {

    @Override
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;

        int interval = 1;
        int size = lists.length;

        /*
         * Since we apply k-way merge sort such a way that it form a binary tree structure,
         * this binary tree is Tournament binary tree which has height log(n) of base 2
         */
        while (interval < size) {

            for (int i = 0; i + interval < size; i += interval * 2) {

                lists[i] = mergeTwoLists(lists[i], lists[i + interval]);
            }

            interval *= 2;
        }


        return lists[0];
    }


    /**
     * {@link DataStructureAlgo.Java.LeetCode.MergeTwoSortedLists}
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode h = new ListNode(0);
        ListNode ans = h;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                h.next = l1;
                h = h.next;
                l1 = l1.next;
            } else {
                h.next = l2;
                h = h.next;
                l2 = l2.next;
            }
        }
        if (l1 == null) {
            h.next = l2;
        }
        if (l2 == null) {
            h.next = l1;
        }
        return ans.next;
    }
}