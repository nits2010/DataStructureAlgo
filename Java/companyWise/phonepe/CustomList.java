package Java.companyWise.phonepe;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-12
 * Description:
 */
public class CustomList<T> implements IList<T> {

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


}
