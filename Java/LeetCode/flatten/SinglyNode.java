package Java.LeetCode.flatten;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-31
 * Description:
 */
public class SinglyNode extends Base {
    public SinglyNode next;
    public SinglyNode child;

    public SinglyNode() {
    }

    public SinglyNode(int _val) {
        val = _val;
    }

    public SinglyNode(int _val, SinglyNode _next, SinglyNode _child) {
        val = _val;
        next = _next;
        child = _child;
    }

    @Override
    public String toString() {
        return val+"";
    }
}

