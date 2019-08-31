package Java.LeetCode.flatten;

import Java.HelpersToPrint.Printer;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-31
 * Description:
 */
public class FlattenMultilevelDoublyLinkedList {

    public static void main(String[] args) {

        testLevelWise(MultiLevelListBuilder.buildDoublyList1());
        testDepthWise(MultiLevelListBuilder.buildDoublyList1());
    }


    private static void testLevelWise(Triple<Node> head) {

        System.out.println("Level wise -> :" + head.levelWise);
        final Node levelWise = FlattenMultilevelDoublyLinkedListLevelWise.flatten(head.head);
        System.out.println("flatten Level Wise " + Printer.print(levelWise));
    }

    private static void testDepthWise(Triple<Node> head) {
        System.out.println("Depth wise -> :" + head.depthWise);
        final Node depthWise = FlattenMultilevelDoublyLinkedListDepthWise.flatten(head.head);
        System.out.println("flatten Depth Wise " + Printer.print(depthWise));
    }
}

/**
 * https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/
 * You are given a doubly linked list which in addition to the next and previous pointers,
 * it could have a child pointer, which may or may not point to a separate doubly linked list.
 * These child lists may have one or more children of their own, and so on, to produce a multilevel data structure,
 * as shown in the example below.
 * <p>
 * Flatten the list so that all the nodes appear in a single-level, doubly linked list. You are given the head of the first
 * level of the list.
 * Example:
 * Input:
 * *  1---2---3---4---5---6--NULL
 * *          |
 * *          7---8---9---10--NULL
 * *              |
 * *              11--12--NULL
 * *
 * Output:
 * 1-2-3-7-8-11-12-9-10-4-5-6-NULL
 * <p>
 * {@link FlattenMultilevelSinglyListDepthWise}
 * <p>
 * To flatten in depth wise, we need to dfs at each child and once child are exhausted, we apply same for next
 * The important point is, after dfs completed on a node, we need to connect its next node with the deepest dfs node
 * * * 1 - 2 - 3 - 4
 * * *     |
 * * *     7 -  8 - 10 - 12
 * * *     |    |    |
 * * *     9    16   11
 * * *     |    |
 * * *     14   17 - 18 - 19 - 20
 * * *     |                    |
 * * *     15 - 23             21
 * * *          |
 * * *          24
 * In above example;
 * 1. dfs on '2'
 * 2. dfs on '7'
 * 3. dfs on '9'......
 * ...
 * n) dfs on 23
 * n+1) dfs on 24 but there is not next and child, then here we end dfs
 * While coming down keep connecting the correct next/prev node using last.
 *
 * <p>
 * Algo:
 * 1. cache next node, and update last node as current node
 * 2. if child, connect last and child; do dfs on child;
 * 3. if no child, connect last and next; go to cached next node;
 * <p>
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Flatten a Multilevel Doubly Linked List.
 * Memory Usage: 37.4 MB, less than 82.50% of Java online submissions for Flatten a Multilevel Doubly Linked List.
 */
class FlattenMultilevelDoublyLinkedListDepthWise {

    public static Node flatten(Node head) {
        if (head == null)
            return head;

        flattenList(head);

        return head;
    }

    private static Node flattenList(Node current) {

        if (current == null)
            return current;

        Node next = current.next;
        Node last = current;

        //go dfs on child
        if (current.child != null) {

            //attach them
            last.next = current.child;
            current.child.prev = last;

            //dfs
            last = flattenList(current.child);

            //nullify child
            current.child = null;
        }

        //process next now
        if (next != null) {
            last.next = next;
            next.prev = last;

            last = flattenList(next);
        }

        return last;

    }

}


/**
 * You are given a doubly linked list which in addition to the next and previous pointers,
 * it could have a child pointer, which may or may not point to a separate doubly linked list.
 * These child lists may have one or more children of their own, and so on, to produce a multilevel data structure,
 * as shown in the example below.
 * <p>
 * Flatten the list so that all the nodes appear in a single-level, doubly linked list. You are given the head of the first
 * level of the list.
 * Example:
 * Input:
 * *  1---2---3---4---5---6--NULL
 * *          |
 * *          7---8---9---10--NULL
 * *              |
 * *              11--12--NULL
 * *
 * Output:
 * 1-2-3-4-5-6-7-8-9-10-11-12
 * <p>
 * Same as {@link FlattenMultilevelSinglyListLevelWise}, no change required for doubly LL
 */
class FlattenMultilevelDoublyLinkedListLevelWise {

    /**
     * Simple travers the list,
     * 1. if this node has child, then add it to tail of the linked list and update the tail
     * 2. if not, move ahead
     *
     * @param head
     * @return
     */
    public static Node flatten(Node head) {

        if (head == null)
            return head;

        Node tail = getTail(head);
        Node current = head;

        while (current != tail) {

            if (current.child != null) {

                Node childHead = current.child;

                tail.next = childHead;
                childHead.prev = tail;

                tail = getTail(tail);

                current.child = null; //nullify the child
            }
            current = current.next;
        }


        return head;

    }

    private static Node getTail(Node head) {
        Node tail = head;
        while (tail.next != null)
            tail = tail.next;
        return tail;
    }

}