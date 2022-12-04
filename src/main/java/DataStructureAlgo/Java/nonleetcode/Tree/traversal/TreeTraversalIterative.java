package DataStructureAlgo.Java.nonleetcode.Tree.traversal;

import  DataStructureAlgo.Java.nonleetcode.Tree.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-30
 * Description:
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
            // then push the root again in order to process that right node

            stack.push(root);

            //go to right for processing
            root = root.getRight();

        }

        return postOrder;
    }

}
