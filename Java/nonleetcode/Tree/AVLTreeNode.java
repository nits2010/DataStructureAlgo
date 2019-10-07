package Java.nonleetcode.Tree;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 28/12/18
 * Description:
 */
public class AVLTreeNode<T> extends TreeNode<T> {

    private int height;
    private T data;
    private AVLTreeNode<T> left;
    private AVLTreeNode<T> right;


    public AVLTreeNode(T data) {
        this.data = data;
        this.height = 1;
    }

    public int getHeight() {

        return height;
    }

    public int getHeight(AVLTreeNode node) {
        if (node == null)
            return 0;
        return node.getHeight();
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public TreeNode getLeft() {
        return left;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public TreeNode getRight() {
        return right;
    }

    @Override
    public void setData(T data) {
        this.data = data;

    }

    @Override
    public void setLeft(TreeNode left) {
        this.left = (AVLTreeNode<T>) left;

    }

    @Override
    public void setRight(TreeNode right) {
        this.right = (AVLTreeNode<T>) right;
    }

    @Override
    public String toString() {
        return "AVLTreeNode{" +
                "height=" + height +
                ", data=" + data +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
