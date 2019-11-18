package Java.nonleetcode.Tree;

/**
 * Author: Nitin Gupta
 * Date: 28/12/18
 * Description:
 */
public abstract class TreeNode<T> {

    abstract public TreeNode getLeft();

    abstract public T getData();

    abstract public TreeNode getRight();

    abstract public void setData(T data);

    abstract public void setLeft(TreeNode left);

    abstract public void setRight(TreeNode right);
}
