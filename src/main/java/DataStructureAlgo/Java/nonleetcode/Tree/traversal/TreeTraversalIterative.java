package DataStructureAlgo.Java.nonleetcode.Tree.traversal;

import DataStructureAlgo.Java.helpers.templates.NArrayTreeNode;

import  DataStructureAlgo.Java.nonleetcode.Tree.TreeNode;

import java.util.*;

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
public class TreeTraversalIterative {


    /****************** In Order Iterative - public static  implementation Traversal *******************/
    public static  List<Integer> inOrderIterative(TreeNode<Integer> root) {
        List<Integer> inOrder = new LinkedList<>();
        if (null == root)
            return inOrder;

        Stack<TreeNode<Integer>> stack = new Stack<>();


        while (root != null || !stack.isEmpty()) {


            //Process left subtree first, push all left subtree node over root
            while (root != null) {

                stack.push(root);
                root = root.getLeft();
            }

            //If all elements has processed then break and return
            if (stack.isEmpty())
                return inOrder;

            //process this node
            root = stack.pop();

            inOrder.add(root.getData());

            //go to right after processing
            root = root.getRight();

        }


        return inOrder;
    }


    /****************** Pre Order Iterative - public static  implementation Traversal *******************/
    public static  List<Integer> preOrderIterative(TreeNode<Integer> root) {
        List<Integer> preOrder = new LinkedList<>();
        if (null == root)
            return preOrder;

        Stack<TreeNode<Integer>> stack = new Stack<>();


        //Push root, as this will process first
        stack.push(root);

        while (!stack.isEmpty()) {

            //Process root first
            root = stack.pop();
            preOrder.add(root.getData());

            //push right child first as this traversal is Root left and right;
            // pushing right first insure left is on top of stack and process first
            if (null != root.getRight())
                stack.push(root.getRight());

            //push left
            if (null != root.getLeft())
                stack.push(root.getLeft());

        }


        return preOrder;
    }

    /****************** Post Order Iterative - public static  implementation Traversal *******************/
    public static  List<Integer> postOrderIterative(TreeNode<Integer> root) {
        List<Integer> postOrder = new LinkedList<>();
        if (null == root)
            return postOrder;

        Stack<TreeNode<Integer>> stack = new Stack<>();

        //to ensure right process first
        TreeNode<Integer> previous = root;


        while (root != null) {

            //Process left subtree first, push all root of left subtree node
            while (root.getLeft() != null) {
                stack.push(root);
                root = root.getLeft();
            }


            //Process right first, to process it we need to deep down the right part of it.
            while (null != root && (root.getRight() == null || root.getRight() == previous)) {

                //Since there is no right node of right node, then this is the rightmost node then process it
                postOrder.add(root.getData());

                //set previous node as current node; right
                previous = root;

                //get the above tree node; root
                if (!stack.isEmpty())
                    root = stack.pop();
                else
                    return postOrder;


            }

            //it may possible the right node of current "right" hasn't process yet [since there is right subtree exist]
            // then push the root again to process that right node

            stack.push(root);

            //go to right for processing
            if(root!=null)
                root = root.getRight();

        }

        return postOrder;
    }

    /****************** LevelOrder - public static implementation Traversal For N-ary Tree *******************/

    public static List<List<Integer>> levelOrder(NArrayTreeNode root) {

        List<List<Integer>> levelOrder = new ArrayList<>();
        if (null == root)
            return levelOrder;

        Queue<NArrayTreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);

        while (!queue.isEmpty()) {
            NArrayTreeNode temp = queue.poll();

            if(temp == null){
                if(queue.isEmpty())
                    return levelOrder;
                else
                    queue.offer(null);

                levelOrder.add(new LinkedList<>());

            }else {
                if (levelOrder.isEmpty()) {
                    levelOrder.add(new LinkedList<>());
                }

                List<Integer> children = levelOrder.get(levelOrder.size() - 1);
                children.add( temp.val);

                for(NArrayTreeNode child : temp.children)
                    queue.offer(child);


            }


        }
        return levelOrder;
    }


}
