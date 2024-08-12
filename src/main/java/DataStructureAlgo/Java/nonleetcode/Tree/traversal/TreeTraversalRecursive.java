package DataStructureAlgo.Java.nonleetcode.Tree.traversal;

import  DataStructureAlgo.Java.nonleetcode.Tree.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * Author: Nitin Gupta
 * Date: 11/8/2024 & 2019-08-30
 * Question Category:
 * A) 145. Binary Tree Postorder Traversal
 * B) 94. Binary Tree Inorder Traversal
 * C) 144. Binary Tree Preorder Traversal
 * Description:
 * <a href="https://leetcode.com/problems/binary-tree-postorder-traversal/description/">...</a>
 * <a href="https://leetcode.com/problems/binary-tree-inorder-traversal/description/">...</a>
 * <a href="https://leetcode.com/problems/binary-tree-preorder-traversal/description/">...</a>
 *
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 * @easy
 * @Stack
 * @Tree
 * @Depth-FirstSearch
 * @BinaryTree
 *
 * Company Tags
 * -----
 * @Adobe
 * @Google
 * @Facebook
 * @TuSimple
 * @Microsoft
 * @Amazon
 * @Apple
 *

 *
 *
 * @Editorial
 * <a href="https://leetcode.com/problems/binary-tree-preorder-traversal/solutions/5620461/recursive-easy-iterative-with-stack-iterative-without-stack-o-n-o-1">...</a>
 * <a href="https://leetcode.com/problems/binary-tree-inorder-traversal/solutions/5620435/recursive-easy-iterative-with-stack-iterative-without-stack-o-n-o-1">...</a>
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
