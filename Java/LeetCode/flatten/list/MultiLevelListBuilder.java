package Java.LeetCode.flatten.list;

import java.util.Arrays;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-31
 * Description:
 */
public class MultiLevelListBuilder {


    public static Triple buildSinglyList() {

        int arr1[] = new int[]{10, 5, 12, 7, 11};
        int arr2[] = new int[]{4, 20, 13};
        int arr3[] = new int[]{17, 6};
        int arr4[] = new int[]{9, 8};
        int arr5[] = new int[]{19, 15};
        int arr6[] = new int[]{2};
        int arr7[] = new int[]{16};
        int arr8[] = new int[]{3};

        /* create 8 linked lists */
        SinglyNode head1 = buildSinglyList(arr1, arr1.length);
        SinglyNode head2 = buildSinglyList(arr2, arr2.length);
        SinglyNode head3 = buildSinglyList(arr3, arr3.length);
        SinglyNode head4 = buildSinglyList(arr4, arr4.length);
        SinglyNode head5 = buildSinglyList(arr5, arr5.length);
        SinglyNode head6 = buildSinglyList(arr6, arr6.length);
        SinglyNode head7 = buildSinglyList(arr7, arr7.length);
        SinglyNode head8 = buildSinglyList(arr8, arr8.length);

        /* modify child pointers to create the list shown above */
        head1.child = head2;
        head1.next.next.next.child = head3;
        head3.child = head4;
        head4.child = head5;
        head2.next.child = head6;
        head2.next.next.child = head7;
        head7.child = head8;

        /* Return head pointer of first linked list.  Note that all nodes are
         reachable from head1 */

        List<Integer> levelWise = Arrays.asList(10, 5, 12, 7, 11, 4, 20, 13, 17, 6, 2, 16, 9, 8, 3, 19, 15);
        List<Integer> depthWise = Arrays.asList(10, 5, 12, 7, 11, 4, 20, 13, 17, 6, 2, 16, 9, 8, 3, 19, 15);

        Triple triple = new Triple();
        triple.head = head1;
        triple.depthWise = depthWise;
        triple.levelWise = levelWise;


        return triple;
    }

    // A utility function to create a linked list with n nodes. The data
    // of nodes is taken from arr[].  All child pointers are set as NULL
    private static SinglyNode buildSinglyList(int arr[], int n) {
        SinglyNode node = null;
        SinglyNode p = null;

        int i;
        for (i = 0; i < n; ++i) {
            if (node == null) {
                node = p = new SinglyNode(arr[i]);
            } else {
                p.next = new SinglyNode(arr[i]);
                p = p.next;
            }
            p.next = p.child = null;
        }
        return node;
    }


    /**
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
     *
     * @return
     */
    public static Triple buildSinglyList2() {
        SinglyNode head = new SinglyNode(1);
        head.next = new SinglyNode(2);
        head.next.next = new SinglyNode(3);
        head.next.next.next = new SinglyNode(4);
        head.next.child = new SinglyNode(7);
        head.next.child.child = new SinglyNode(9);
        head.next.child.child.child = new SinglyNode(14);
        head.next.child.child.child.child = new SinglyNode(15);
        head.next.child.child.child.child.next = new SinglyNode(23);
        head.next.child.child.child.child.next.child = new SinglyNode(24);
        head.next.child.next = new SinglyNode(8);
        head.next.child.next.child = new SinglyNode(16);
        head.next.child.next.child.child = new SinglyNode(17);
        head.next.child.next.child.child.next = new SinglyNode(18);
        head.next.child.next.child.child.next.next = new SinglyNode(19);
        head.next.child.next.child.child.next.next.next
                = new SinglyNode(20);
        head.next.child.next.child.child.next.next.next.child
                = new SinglyNode(21);
        head.next.child.next.next = new SinglyNode(10);
        head.next.child.next.next.child = new SinglyNode(11);
        head.next.child.next.next.next = new SinglyNode(12);


        List<Integer> levelWise = Arrays.asList(1, 2, 3, 4, 7, 8, 10, 12, 9, 16, 11, 14, 17, 18, 19, 20, 15, 23, 21, 24);
        List<Integer> depthWise = Arrays.asList(1, 2, 7, 9, 14, 15, 23, 24, 8, 16, 17, 18, 19, 20, 21, 10, 11, 12, 3, 4);

        Triple triple = new Triple();
        triple.head = head;
        triple.depthWise = depthWise;
        triple.levelWise = levelWise;


        return triple;
    }


    /**
     * *  1---2---3---4---5---6--NULL
     * *          |
     * *          7---8---9---10--NULL
     * *              |
     * *              11--12--NULL
     * *
     *
     * @return
     */
    public static Triple buildDoublyList1() {

        Node node1 = new Node(1);
        Node node2 = new Node(2);

        Node node3 = new Node(3);
        Node node4 = new Node(4);


        Node node5 = new Node(5);
        Node node6 = new Node(6);

        Node node7 = new Node(7);
        Node node8 = new Node(8);

        Node node9 = new Node(9);
        Node node10 = new Node(10);

        Node node11 = new Node(11);
        Node node12 = new Node(12);


        node1.next = node2;
        node2.prev = node1;

        node2.next = node3;
        node3.prev = node2;

        node3.next = node4;
        node4.prev = node3;

        node4.next = node5;
        node5.prev = node4;

        node5.next = node6;
        node6.prev = node5;

        node3.child = node7;
        node8.child = node11;

        node7.next = node8;
        node8.prev = node7;

        node8.next = node9;
        node9.prev = node8;

        node9.next = node10;
        node10.prev = node9;


        node11.next = node12;
        node12.prev = node11;


        return new Triple(node1, Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12), Arrays.asList(1, 2, 3, 7, 8, 11, 12, 9, 10, 4, 5, 6));

    }
}
