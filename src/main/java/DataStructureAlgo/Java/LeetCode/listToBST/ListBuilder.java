package DataStructureAlgo.Java.LeetCode.listToBST;

import  DataStructureAlgo.Java.LeetCode.templates.DoublyListNode;
import  DataStructureAlgo.Java.LeetCode.templates.ListNode;
import  DataStructureAlgo.Java.LeetCode.templates.TreeNode;

import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 2019-09-01
 * Description:
 */
public class ListBuilder {

    public static ListNode arrayToCyclicSinglyList(Integer[] elements, int indexOfCycle) {

        if (elements == null || elements.length == 0)
            return null;

        ListNode head = new ListNode(elements[0]);

        if(indexOfCycle == 0){
            head.next = head;
            return head;
        }

        ListNode tail = head;
        ListNode temp = head;
        ListNode cycleNode = null;
        int i = 1;

        while (i < elements.length) {
            temp.next = new ListNode(elements[i]);

            if(indexOfCycle == i){
                cycleNode = temp.next;
            }
            temp = temp.next;
            i++;
        }
        tail = temp;
        tail.next = cycleNode;

        return head;
    }

    public static ListNode arrayToSinglyList(Integer[] elements) {

        if (elements == null || elements.length == 0)
            return null;

        ListNode head = new ListNode(elements[0]);
        ListNode temp = head;
        int i = 1;

        while (i < elements.length) {
            temp.next = new ListNode(elements[i]);
            temp = temp.next;
            i++;
        }

        return head;
    }

    public static ListNode arrayToSinglyList(List<Integer> elements) {

        if (elements == null || elements.size() == 0)
            return null;

        ListNode head = new ListNode(elements.get(0));
        ListNode temp = head;
        int i = 1;

        while (i < elements.size()) {
            temp.next = new ListNode(elements.get(i));
            temp = temp.next;
            i++;
        }

        return head;
    }

    public static ListNode copyOf(ListNode head) {

        if (head == null)
            return head;

        ListNode copyHead = new ListNode(-1);
        ListNode current = copyHead;
        while (head != null) {
            current.next = new ListNode(head.val);
            current = current.next;

            head = head.next;
        }

        return copyHead.next;

    }


    /**
     * Utilizing TreeNode as doubly ll node
     *
     * @param elements
     * @return
     */
    public static TreeNode arrayToDoublyList(Integer[] elements) {

        if (elements == null || elements.length == 0)
            return null;

        TreeNode head = new TreeNode(elements[0]);
        head.left = null;

        TreeNode temp = head;

        int i = 1;

        while (i < elements.length) {
            temp.right = new TreeNode(elements[i]);
            temp.right.left = temp;
            temp = temp.right;
            i++;
        }

        return head;
    }


    /**
     * @param elements Values in list
     * @return Head of doubly linked list
     */
    public static DoublyListNode arrayToDoublyListNode(Integer[] elements) {

        if (elements == null || elements.length == 0)
            return null;

        DoublyListNode head = new DoublyListNode(elements[0]);
        DoublyListNode temp = head;
        int i = 1;

        while (i < elements.length) {
            temp.next = new DoublyListNode(elements[i]);
            temp.next.prev = temp;
            temp = temp.next;
            i++;
        }

        return head;
    }

    /**
     * @param elements Values in list
     * @return Head of doubly linked list
     */
    public static DoublyListNode arrayToDoublyListNode(List<Integer> elements) {

        if (elements == null || elements.isEmpty())
            return null;

        DoublyListNode head = new DoublyListNode(elements.get(0));
        DoublyListNode temp = head;
        int i = 1;

        while (i < elements.size()) {
            temp.next = new DoublyListNode(elements.get(i));
            temp.next.prev = temp;
            temp = temp.next;
            i++;
        }

        return head;
    }

    public static DoublyListNode copyOf(DoublyListNode head) {

        if (head == null)
            return head;

        DoublyListNode copyHead = null;
        DoublyListNode current = null;
        while (head != null) {
            if (current == null) {
                current = new DoublyListNode(head.val);
                copyHead = current;
            } else {
                current.next = new DoublyListNode(head.val);
                current.next.prev = current;
                current = current.next;
            }

            head = head.next;
        }

        return copyHead;
    }


}
