package DataStructureAlgo.Java.helpers.templates;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-29
 * Description: https://www.geeksforgeeks.org/treap-a-randomized-binary-search-tree/
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
