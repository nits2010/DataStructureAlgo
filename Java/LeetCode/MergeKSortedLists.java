package Java.LeetCode;

import Java.LeetCode.templates.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
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
 */
public class MergeKSortedLists {

    private static ListNode getList(int[] a) {
        ListNode head = new ListNode(a[0]);
        ListNode temp = head;

        int i = 1;
        while (i < a.length) {
            temp.next = new ListNode(a[i++]);
            temp = temp.next;
        }
        return head;
    }

    private static void print(ListNode head) {

        StringBuilder print = new StringBuilder();

        while (head != null) {
            print.append(head.val);
            print.append("->");
            head = head.next;
        }
        print.setLength(print.length() - 2);
        System.out.println(print);
    }

    public static void main(String[] args) {

        ListNode[] list = new ListNode[3];
        list[0] = getList(new int[]{1, 4, 5});
        list[1] = getList(new int[]{1, 3, 4});
        list[2] = getList(new int[]{2, 6});

        ListNode[] list2 = new ListNode[5];
        list2[0] = getList(new int[]{1, 4, 5});
        list2[1] = getList(new int[]{1, 3, 4});
        list2[2] = getList(new int[]{2, 6});
        list2[3] = getList(new int[]{1, 2, 3, 4, 5, 6, 6});
        list2[4] = getList(new int[]{0, 3, 6, 8, 10});


        test(list);
        test(list2);
    }

    private static void test(ListNode[] list) {
        IMergeKSortedLists pq = new MergeKSortedListsPriorityQueue();
        IMergeKSortedLists kWayMerget = new MergeKSortedListsDC();

        System.out.println("PQ->");
        ListNode head = pq.mergeKLists(list);
        print(head);

        System.out.println("KWay->");
        head = kWayMerget.mergeKLists(list);
        print(head);

    }
}


interface IMergeKSortedLists {
    ListNode mergeKLists(ListNode[] lists);

}

/**
 * O(n*Log(k)) / O(k)
 * <p>
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

        /**
         * Cache first node of all the list
         */
        for (int i = 0; i < n; i++) {

            if (lists[i] != null)
                pq.offer(lists[i]);
        }


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

        /**
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