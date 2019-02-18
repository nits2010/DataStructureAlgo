package Java.Tree;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 26/12/18
 * Description:
 */
public class BinaryTreeNode<T> extends TreeNode<T> {

    private T data;
    private BinaryTreeNode<T> left;
    private BinaryTreeNode<T> right;

    public BinaryTreeNode(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public void buildNode(T data, TreeNode left, TreeNode right) {
        this.data = data;
        this.left = (BinaryTreeNode<T>) left;
        this.right = (BinaryTreeNode<T>) right;
    }

    @Override
    public BinaryTreeNode getLeft() {
        return left;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public BinaryTreeNode getRight() {
        return right;
    }

    @Override
    public void setData(T data) {
        this.data = data;
    }

    @Override
    public void setLeft(TreeNode left) {
        this.left = (BinaryTreeNode<T>) left;
    }

    @Override
    public void setRight(TreeNode right) {
        this.right = (BinaryTreeNode<T>) right;
    }

    @Override
    public String toString() {
        return "BinaryTreeNode{" +
                "data=" + data +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}





