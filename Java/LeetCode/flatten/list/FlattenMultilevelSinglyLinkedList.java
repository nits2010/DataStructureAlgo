package Java.LeetCode.flatten.list;

import Java.helpers.GenericPrinter;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-31
 * Description: https://www.geeksforgeeks.org/flatten-a-linked-list-with-next-and-child-pointers/
 * <p>
 * Given a linked list where in addition to the next pointer, each node has a child pointer,
 * which may or may not point to a separate list. These child lists may have one or more children of their own,
 * and so on, to produce a multilevel data structure, as shown in below figure.You are given the head of the
 * first level of the list. Flatten the list so that all the nodes appear in a single-level linked list. You need to
 * flatten the list in way that all nodes at first level should come first, then nodes of second level, and so on.
 * <p>
 * <p>
 * struct List
 * {
 * int data;
 * struct List *next;
 * struct List *child;
 * };
 * <p>
 */
public class FlattenMultilevelSinglyLinkedList {

    public static void main(String[] args) {

        testLevelWise(MultiLevelListBuilder.buildSinglyList());
        testLevelWise(MultiLevelListBuilder.buildSinglyList2());
        System.out.println();
        testDepthWise(MultiLevelListBuilder.buildSinglyList());
        testDepthWise(MultiLevelListBuilder.buildSinglyList2());
    }


    private static void testLevelWise(Triple<SinglyNode> head) {

        System.out.println("Level wise -> :" + head.levelWise);
        final SinglyNode levelWise = FlattenMultilevelSinglyListLevelWise.flatten(head.head);
        System.out.println("flatten Level Wise " + GenericPrinter.print(levelWise));
    }

    private static void testDepthWise(Triple<SinglyNode> head) {
        System.out.println("Depth wise -> :" + head.depthWise);
        final SinglyNode depthWise = FlattenMultilevelSinglyListDepthWise.flatten(head.head);
        System.out.println("flatten Depth Wise " + GenericPrinter.print(depthWise));
    }


}

/**
 * Level wise : Level order traversal BFS
 * https://www.geeksforgeeks.org/flatten-a-linked-list-with-next-and-child-pointers/
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
 * <p>
 * 1 - 2 - 3 - 4 - 7 - 8 - 10 - 12 - 9- 16 - 11 - 14 - 17 - 18 - 19 - 20 -15 - 23 - 21 - 24
 */
class FlattenMultilevelSinglyListLevelWise {
    /**
     * Simple travers the list,
     * 1. if this node has child, then add it to tail of the linked list and update the tail
     * 2. if not, move ahead
     *
     * @param head
     * @return
     */
    public static SinglyNode flatten(SinglyNode head) {

        if (head == null)
            return head;

        SinglyNode tail = getTail(head);
        SinglyNode current = head;

        while (current != tail) {

            if (current.child != null) {

                SinglyNode childHead = current.child;

                tail.next = childHead;

                tail = getTail(tail);

                current.child = null; //nullify the child
            }
            current = current.next;
        }


        return head;

    }

    private static SinglyNode getTail(SinglyNode head) {
        SinglyNode tail = head;
        while (tail.next != null)
            tail = tail.next;
        return tail;
    }
}


/**
 * Depth wise: DFS
 * https://www.geeksforgeeks.org/flatten-a-multi-level-linked-list-set-2-depth-wise/
 * <p>
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
 * <p>
 * 1 - 2 - 7 - 9 - 14 - 15 - 23 - 24 - 8- 16 - 17 - 18 - 19 - 20 - 21 - 10 -11 - 12 - 3 - 4
 */
class FlattenMultilevelSinglyListDepthWise {

    /**
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
     *
     * @param head
     * @return
     */
    public static SinglyNode flatten(SinglyNode head) {

        if (head == null)
            return head;

        flattenList(head);

        return head;
    }

    private static SinglyNode flattenList(SinglyNode current) {

        if (current == null)
            return current;

        SinglyNode next = current.next;
        SinglyNode last = current;

        //go dfs on child
        if (current.child != null) {

            //attach them
            last.next = current.child;

            //dfs
            last = flattenList(current.child);

            //nullify child
            current.child = null;
        }

        //process next now
        if (next != null) {
            last.next = next;

            last = flattenList(next);
        }

        return last;

    }

}