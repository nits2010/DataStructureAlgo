package DataStructureAlgo.Java.LeetCode.templates;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-29
 * Description:
 */
public class TreapNode {


    public int bstVal;
    public int heapVal;
    public TreapNode left;
    public TreapNode right;

    public TreapNode(int bstVal, int heapVal) {
        this.bstVal = bstVal;
        this.heapVal = heapVal;
    }

    @Override
    public String toString() {
        return "TreapNode{" +
                "bstVal=" + bstVal +
                ", heapVal=" + heapVal +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
