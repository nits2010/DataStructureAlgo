package Java.companyWise.phonepe;

import java.io.Serializable;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-12
 * Description:
 */
public class CustomConcurrentHashMap<Key, Value> implements ICustomMap<Key, Value>, Serializable {


    private static final int DEFAULT_CAPACITY = 2;


    transient volatile CustomNode<Key, Value> table[];

    public CustomConcurrentHashMap() {
        table = new CustomNode[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {

        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public synchronized Value put(Key key, Value value) {
        int hashCode = key.hashCode();

        int hash = hashCode % DEFAULT_CAPACITY;

        if (table != null) {

            if (table[hash] == null) {

                CustomNode<Key, Value> temp = new CustomNode<>();
                temp.setKey(key);
                temp.setValue(value);
                temp.setNext(null);

                table[hash] = temp;
                return value;

            } else {

                CustomNode<Key, Value> head = table[hash];

                CustomNode<Key, Value> temp = head;

                while (temp != null) {
                    if (temp.getKey().hashCode() == hashCode && temp.getKey().equals(key)) {
                        temp.setValue(value);
                        return value;
                    }
                    temp = temp.getNext();
                }

                if (temp == null) {
                    CustomNode<Key, Value> newValue = new CustomNode<>();
                    newValue.setKey(key);
                    newValue.setValue(value);
                    newValue.setNext(head);

                    table[hash] = newValue;

                    return value;

                }

            }
        }


        return value;
    }

    @Override
    public Value get(Key key) {

        int hashCode = key.hashCode();

        int hash = hashCode % DEFAULT_CAPACITY;

        if (table != null && table[hash] != null) {

            CustomNode<Key, Value> head = table[hash];

            if (head.getKey().hashCode() == hashCode && head.getKey().equals(key)) {
                return head.getValue();
            } else {

                CustomNode<Key, Value> temp = head;

                while (temp != null) {
                    if (temp.getKey().hashCode() == hashCode && temp.getKey().equals(key)) {
                        return temp.getValue();
                    }
                    temp = temp.getNext();
                }
            }


        }

        return null;
    }
}
