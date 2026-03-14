package DataStructureAlgo.Java.companyWise.phonepe.keyValueStore;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-12
 * Question Title: Custom Node
 * Link: https://leetcode.com/problems/custom-node/
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
