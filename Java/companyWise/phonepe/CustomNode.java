package Java.companyWise.phonepe;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-12
 * Description:
 */
public class CustomNode<Key, Value> {

    private Key key;
    private Value value;
    private CustomNode<Key, Value> next;

    public Key getKey() {
        return key;
    }

    public Value getValue() {
        return value;
    }

    public CustomNode<Key, Value> getNext() {
        return next;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public void setNext(CustomNode<Key, Value> next) {
        this.next = next;
    }
}
