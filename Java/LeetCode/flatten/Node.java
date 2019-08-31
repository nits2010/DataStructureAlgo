package Java.LeetCode.flatten;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-31
 * Description:
 * For Doubly Linked List
 */
public class Node extends Base {
    public Node prev;
    public Node next;
    public Node child;


    public Node(int _val) {
        val = _val;
    }


    public Node() {
    }

    public Node(int _val, Node _prev, Node _next, Node _child) {
        val = _val;
        prev = _prev;
        next = _next;
        child = _child;
    }

    @Override
    public String toString() {

        StringBuilder s = new StringBuilder();


        int p = -1;
        int n = -1;

        if (prev != null)
            s.append(prev.val);

        s.append("<-" + val + "->");

        if (next != null)
            s.append(next.val);

        return s.toString();
    }

}
