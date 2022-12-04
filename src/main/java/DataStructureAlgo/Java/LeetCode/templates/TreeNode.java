package DataStructureAlgo.Java.LeetCode.templates;

import java.util.Objects;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-12
 * Description:
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreeNode treeNode = (TreeNode) o;
        return val == treeNode.val;
    }

    @Override
    public int hashCode() {
        return Objects.hash(val);
    }

    @Override
    public String toString() {
        return "" + val;

//        return "TreeNode{" +
//                "val=" + val +
//                ", left=" + left +
//                ", right=" + right +
//                '}';
    }
}
