package DataStructureAlgo.Java.companyWise.phonepe.keyValueStore;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-12
 * Question Title: Custom List
 * Link: TODO: Add Link
 * Description:
 * Description:
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class CustomList<T> implements ICustomList<T> {

    private int size = 0;
    private ListNode<T> head;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void linkFirst(T e) {
        ListNode<T> node = new ListNode<>(e);

        if (head == null) {
            head = node;
            return;
        }

        node.setNext(head);
        head = node;
    }

    @Override
    public void add(T e) {
        linkFirst(e);
    }


    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[");

        ListNode<T> node = head;

        while (node != null) {
            str.append(node.getValue() + ",");
            node = node.getNext();
        }

        str.setCharAt(str.length() - 1, ']');
        return str.toString();
    }
}
