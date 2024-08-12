package DataStructureAlgo.Java.nonleetcode.Tree.traversal;

import DataStructureAlgo.Java.helpers.templates.NArrayTreeNode;

import  DataStructureAlgo.Java.nonleetcode.Tree.TreeNode;

import java.util.ArrayList;
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
 * Binary Tree postorder Traversal <a href="https://leetcode.com/problems/binary-tree-postorder-traversal/description/">...</a>
 * Binary Tree Inorder Traversal <a href="https://leetcode.com/problems/binary-tree-inorder-traversal/description/">...</a>
 * Binary Tree Preorder Traversal <a href="https://leetcode.com/problems/binary-tree-preorder-traversal/description/">...</a>
 * Binary Tree level order traversal <a href="https://leetcode.com/problems/binary-tree-level-order-traversal/description/">...</a>
 *
 * N-ary Tree Preorder Traversal <a href="https://leetcode.com/problems/n-ary-tree-preorder-traversal/description/">...</a>
 * N-ary Tree Postorder Traversal <a href="https://leetcode.com/problems/n-ary-tree-postorder-traversal/description/">...</a>
 *
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
 * @LinkedIn
 * @Bloomberg
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

    public static List<List<Integer>> levelOrder2(TreeNode<Integer> root) {

        List<List<Integer>> levelOrder = new ArrayList<>();
        if (null == root)
            return levelOrder;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);

        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();

            if(temp == null){
                if(queue.isEmpty())
                    return levelOrder;
                else
                    queue.offer(null);
                List<Integer> nextChildren = new LinkedList<>();
                levelOrder.add(nextChildren);

            }else {
                if (levelOrder.isEmpty()) {
                    levelOrder.add(new LinkedList<>());
                }

                List<Integer> children = levelOrder.get(levelOrder.size() - 1);
                children.add((Integer) temp.getData());

                if(temp.getLeft()!=null)
                    queue.offer(temp.getLeft());
                if(temp.getRight()!=null)
                    queue.offer(temp.getRight());
            }


        }
        return levelOrder;
    }

    /****************** PreOrder - public static  implementation Traversal For N-ary Tree *******************/
    public static List<Integer> preOrder(NArrayTreeNode root) {
        List<Integer> preOrder = new LinkedList<>();
        if(root == null)
            return preOrder;
        preOrderUtil(root, preOrder);
        return preOrder;
    }

    private static void preOrderUtil(NArrayTreeNode root, List<Integer> preorder) {
        if(root == null)
            return;

        preorder.add(root.val);
        for(NArrayTreeNode child : root.children)
            preOrderUtil(child, preorder);
    }

    /****************** PostOrder - public static implementation Traversal For N-ary Tree *******************/

    public static List<Integer> postOrder(NArrayTreeNode root) {
        List<Integer> postOrder = new LinkedList<>();
        if(root == null)
            return postOrder;
        postOrderUtil(root, postOrder);
        return postOrder;
    }

    private static void postOrderUtil(NArrayTreeNode root, List<Integer> postOrder) {
        if(root == null)
            return;

        for(NArrayTreeNode child : root.children)
            postOrderUtil(child, postOrder);
        postOrder.add(root.val);
    }

    /****************** LevelOrder - public static implementation Traversal For N-ary Tree *******************/

    public static List<List<Integer>> levelOrder(NArrayTreeNode root) {
        List<List<Integer>> levelOrder = new ArrayList<>();
        if(root == null)
            return levelOrder;

        levelOrderUtil(root,levelOrder, 0);
        return levelOrder;
    }

    private static void levelOrderUtil(NArrayTreeNode root, List<List<Integer>> levelOrder, int level) {

        //a new level needs to be start
        if(level == levelOrder.size())
            levelOrder.add(new LinkedList<>());

        levelOrder.get(level).add(root.val);

        for(NArrayTreeNode child : root.children)
            levelOrderUtil(child, levelOrder, level+1);

    }




}
