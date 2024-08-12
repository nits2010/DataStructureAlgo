package DataStructureAlgo.Java.helpers.templates;

import java.util.Objects;

/**
 * Author: Nitin Gupta
 * Date: 13/09/19
 * Description:
 */
public class DoublyListNode {
    public int val;
    public DoublyListNode next;
    public DoublyListNode prev;

    public DoublyListNode(int x) {
        val = x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoublyListNode treeNode = (DoublyListNode) o;
        return val == treeNode.val;
    }

    @Override
    public int hashCode() {
        return Objects.hash(val);
    }

    @Override
    public String toString() {
        return "" + val;

    }
}
