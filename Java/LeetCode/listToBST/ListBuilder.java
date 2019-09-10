package Java.LeetCode.listToBST;

import Java.LeetCode.templates.ListNode;
import Java.LeetCode.templates.TreeNode;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-09-01
 * Description:
 */
public class ListBuilder {

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


    /**
     * Utilizing TreeNode as doubly ll node
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
}
