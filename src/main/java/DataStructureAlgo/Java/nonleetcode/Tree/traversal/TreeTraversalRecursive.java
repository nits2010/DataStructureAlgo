package DataStructureAlgo.Java.nonleetcode.Tree.traversal;

import  DataStructureAlgo.Java.nonleetcode.Tree.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-30
 * Description:
 */
public class TreeTraversalRecursive {

    /****************** InOrder - public static  implementation Traversal *******************/


    public static List<Integer> inOrder(TreeNode<Integer> root) {
        final List<Integer> inOrder = new LinkedList<>();

        if (null == root)
            return inOrder;

        inorderUtil(root, inOrder);

        return inOrder;
    }

    private static void inorderUtil(TreeNode<Integer> root, final List<Integer> inorder) {

        if (null == root)
            return;

        inorderUtil(root.getLeft(), inorder);
        inorder.add(root.getData());
        inorderUtil(root.getRight(), inorder);


    }

    /****************** PostOrder  - public static  implementation Traversal *******************/

    public static List<Integer> postOrder(TreeNode<Integer> root) {
        final List<Integer> postOrder = new LinkedList<>();

        if (null == root)
            return postOrder;

        postOrderUtil(root, postOrder);

        return postOrder;
    }

    private static void postOrderUtil(TreeNode<Integer> root, List<Integer> postorder) {
        if (null == root)
            return;

        postOrderUtil(root.getLeft(), postorder);
        postOrderUtil(root.getRight(), postorder);
        postorder.add(root.getData());
    }

    /****************** PreOrder - public static  implementation Traversal *******************/

    public static List<Integer> preOrder(TreeNode<Integer> root) {
        final List<Integer> preOrder = new LinkedList<>();

        if (null == root)
            return preOrder;

        preOrderUtil(root, preOrder);

        return preOrder;
    }


    private static void preOrderUtil(TreeNode<Integer> root, List<Integer> preorder) {

        if (null == root)
            return;

        preorder.add(root.getData());
        preOrderUtil(root.getLeft(), preorder);
        preOrderUtil(root.getRight(), preorder);

    }

    /****************** Level Order - public static  implementation Traversal *******************/

    public static List<Integer> levelOrder(TreeNode<Integer> root) {

        if (null == root)
            return null;

        List<Integer> levelOrder = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            levelOrder.add((Integer) temp.getData());

            if (null != temp.getLeft())
                queue.add(temp.getLeft());

            if (null != temp.getRight())
                queue.add(temp.getRight());


        }
        return levelOrder;
    }

}
