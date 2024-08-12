package DataStructureAlgo.Java.nonleetcode.Tree;

/**
 * Author: Nitin Gupta
 * Date: 28/12/18
 * Description:
 */
public abstract class TreeNode<T> {

    abstract public TreeNode<T> getLeft();

    abstract public T getData();

    abstract public TreeNode<T> getRight();

    abstract public void setData(T data);

    abstract public void setLeft(TreeNode<T> left);

    abstract public void setRight(TreeNode<T> right);
}
