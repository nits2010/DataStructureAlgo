package Java.Tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 26/12/18
 * Description:
 * <p>
 * Iterator implementations:
 * http://n00tc0d3r.blogspot.com/2013/08/implement-iterator-for-binarytree-i-in.html
 */
public interface IBinaryTree {

    /**
     * Provide an specific implementation of insertion of node
     *
     * @param data
     * @param root
     * @return
     */
    TreeNode<Integer> insert(Integer data, TreeNode<Integer> root);

    default TreeNode<Integer> delete(Integer data, TreeNode<Integer> root) {

        if (null == root || null == data)
            return null;


        //If lies in left
        if (data.compareTo(root.getData()) < 0) {
            root.setLeft(delete(data, root.getLeft()));
            return root;
        }

        //If lies in right
        else if (data.compareTo(root.getData()) > 0) {
            root.setRight(delete(data, root.getRight()));

            return root;
        } else {

            //if this is to delete

            /**
             * Case 1: if its a leaf node
             */
            if (null == root.getLeft() && null == root.getRight())
                //delete it by resetting the parent pointer to null (left or right)
                return null;


            /**
             * Case 2: if this is a single child node
             */

            //If left child null, then carry the right child to parent
            if (null == root.getLeft()) {
                return root.getRight();
            }

            //If right child null, then carry the left child to parent
            if (null == root.getRight())
                return root.getLeft();


            /**
             * Case 3: if this is a double child node
             */

            TreeNode<Integer> successor = root.getRight();
            TreeNode<Integer> successorParent = root.getRight();

            //find successor of root, that lies in right subtree, left most child
            while (null != successor.getLeft()) {
                successorParent = successor;
                successor = successor.getLeft();
            }

            //Since the successor is always as left child of successorParent (because of above loop),
            // the reset the left child only, carry the successor right child
            successorParent.setLeft(successor.getRight());

            //update the value of root from successor
            root.setData(successor.getData());

            //carry root to its parent
            return root;
        }


    }


    /****************** default implementation Traversal *******************/

    /****************** InOrder - default implementation Traversal *******************/


    default List<Integer> inOrder(TreeNode<Integer> root) {
        final List<Integer> inOrder = new LinkedList<>();

        if (null == root)
            return inOrder;

        inorderUtil(root, inOrder);

        return inOrder;
    }

    default void inorderUtil(TreeNode<Integer> root, final List<Integer> inorder) {

        if (null == root)
            return;

        inorderUtil(root.getLeft(), inorder);
        inorder.add(root.getData());
        inorderUtil(root.getRight(), inorder);


    }

    /****************** PostOrder  - default implementation Traversal *******************/

    default List<Integer> postOrder(TreeNode<Integer> root) {
        final List<Integer> postOrder = new LinkedList<>();

        if (null == root)
            return postOrder;

        postOrderUtil(root, postOrder);

        return postOrder;
    }

    default void postOrderUtil(TreeNode<Integer> root, List<Integer> postorder) {
        if (null == root)
            return;

        postOrderUtil(root.getLeft(), postorder);
        postOrderUtil(root.getRight(), postorder);
        postorder.add(root.getData());
    }

    /****************** PreOrder - default implementation Traversal *******************/

    default List<Integer> preOrder(TreeNode<Integer> root) {
        final List<Integer> preOrder = new LinkedList<>();

        if (null == root)
            return preOrder;

        preOrderUtil(root, preOrder);

        return preOrder;
    }


    default void preOrderUtil(TreeNode<Integer> root, List<Integer> preorder) {

        if (null == root)
            return;

        preorder.add(root.getData());
        preOrderUtil(root.getLeft(), preorder);
        preOrderUtil(root.getRight(), preorder);

    }

    /****************** Level Order - default implementation Traversal *******************/

    default List<Integer> levelOrder(TreeNode<Integer> root) {

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


    /****************** In Order Iterative - default implementation Traversal *******************/
    default List<Integer> inOrderIterative(TreeNode<Integer> root) {
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


    /****************** Pre Order Iterative - default implementation Traversal *******************/
    default List<Integer> preOrderIterative(TreeNode<Integer> root) {
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

    /****************** Post Order Iterative - default implementation Traversal *******************/
    default List<Integer> postOrderIterative(TreeNode<Integer> root) {
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


    /****************** Mories algorithm for Java.Tree Traversal- without stack *******************/


    /****************** InOrder Mories algorithm for Java.Tree Traversal- without stack *******************/
    default List<Integer> inOrderMories(TreeNode<Integer> root) {
        List<Integer> inOrder = new LinkedList<>();

        if (null == root)
            return inOrder;


        TreeNode<Integer> temp;

        while (root != null) {

            //see if no left then process it and move to right child
            if (root.getLeft() == null) {
                inOrder.add(root.getData());
                root = root.getRight();

            } else {

                //If there is left child
                temp = root.getLeft();

                //then transform this tree to right skew tree

                //find the right most node of this "temp"
                while (temp.getRight() != null && temp.getRight() != root) {
                    temp = temp.getRight();
                }

                //If this is rightmost node, then make current root as right subtree of temp; transforming to right skew tree
                if (temp.getRight() == null) {

                    temp.setRight(root);

                    //move to the new left subtree
                    root = root.getLeft();
                } else {

                    inOrder.add(root.getData());
                    //Restore the tree
                    temp.setRight(null);

                    root = root.getRight();

                }

            }
        }
        return inOrder;

    }

    /****************** PreOrder Mories algorithm for Java.Tree Traversal- without stack *******************/

    default List<Integer> preOrderMories(TreeNode<Integer> root) {
        List<Integer> preOrder = new LinkedList<>();

        if (null == root)
            return preOrder;


        TreeNode<Integer> temp = null;

        while (root != null) {

            //see if no left then process it and move to right child
            if (root.getLeft() == null) {
                preOrder.add(root.getData());
                root = root.getRight();

            } else {

                //If there is left child
                temp = root.getLeft();

                //then transform this tree to right skew tree

                //find the right most node of this "temp"
                while (temp.getRight() != null && temp.getRight() != root) {
                    temp = temp.getRight();
                }

                //If this is rightmost node, then make current root as right subtree of temp; transforming to right skew tree
                if (temp.getRight() == null) {
                    preOrder.add(root.getData());
                    temp.setRight(root);

                    //move to the new left subtree
                    root = root.getLeft();
                } else {


                    //Restore the tree
                    temp.setRight(null);

                    root = root.getRight();

                }

            }
        }
        return preOrder;

    }


    /****************** Default implementation of Euler Tour of a Binary tree *******************/

    default List<Integer> eulerTour(TreeNode<Integer> root) {
        List<Integer> eulerTour = new LinkedList<>();
        eulerTour(root, eulerTour);
        return eulerTour;

    }

    default void eulerTour(TreeNode<Integer> root, List<Integer> eulerTour) {
        if (null == root)
            return;

        //At the entry point
        eulerTour.add(root.getData());

        if (null != root.getLeft()) {

            eulerTour(root.getLeft(), eulerTour);

            //at the exit point
            eulerTour.add(root.getData());

        }

        if (null != root.getRight()) {


            eulerTour(root.getRight(), eulerTour);

            //at the exit point
            eulerTour.add(root.getData());

        }
    }


    /****************** Default implementation of utilities functions *******************/
    default int size(TreeNode<Integer> root) {
        if (null == root)
            return 0;

        return 1 + size(root.getLeft()) + size(root.getRight());
    }

    default int height(TreeNode<Integer> root) {
        if (null == root)
            return 0;

        int leftHeight = height(root.getLeft());
        int rightHeight = height(root.getRight());

        return Math.max(leftHeight, rightHeight) + 1;

    }


    default int diameter(TreeNode<Integer> root) {

        TreeElementHelper diameterHelper = new TreeElementHelper();

        diameter(root, diameterHelper);
        return diameterHelper.getElement();


    }

    default int diameter(TreeNode<Integer> root, TreeElementHelper diameterHelper) {

        if (null == root)
            return 0;

        int leftHeight = diameter(root.getLeft(), diameterHelper);
        int rightHeight = diameter(root.getRight(), diameterHelper);

        int diameter = leftHeight + rightHeight + 1;
        if (diameterHelper.getElement() < diameter)
            diameterHelper.setElement(diameter);

        return Math.max(leftHeight, rightHeight) + 1;
    }


    default int level(TreeNode<Integer> root, TreeNode<Integer> alpha) {
        if (null == root)
            return 0;


        return level(root, alpha, 1);

    }

    default int level(TreeNode<Integer> root, TreeNode<Integer> alpha, int level) {

        if (null == root)
            return 0;

        if (root == alpha)
            return level;

        int value = level(root.getLeft(), alpha, level + 1);
        if (value != 0)
            return value;
        value = level(root.getRight(), alpha, level + 1);
        return value;


    }

    default int distanceBetweenTwoNodes(TreeNode<Integer> root, TreeNode<Integer> alpha, TreeNode<Integer> beta) {
        if (null == root)
            return -1;

        //if both are same
        if (alpha == beta)
            return 0;

        TreeNode<Integer> lca = lowestCommonAncestor(root, alpha, beta);
        int alphaLevel = level(lca, alpha);
        int betaLevel = level(lca, beta);


        return (alphaLevel + betaLevel - 2);


    }


    default TreeNode<Integer> leftMostNode(TreeNode<Integer> node) {

        if (null == node)
            return null;

        //There is no more left subtree is there, then this is our node
        if (null == node.getLeft())
            return node;

        return leftMostNode(node.getLeft());


    }

    default TreeNode<Integer> rightMostNode(TreeNode<Integer> node) {

        if (null == node)
            return null;

        //There is no more left subtree is there, then this is our node
        if (null == node.getRight())
            return node;

        return rightMostNode(node.getRight());


    }


    default TreeNode rightAlignedNode(TreeNode root) {
        if (null == root)
            return null;

        if (null != root.getRight()) {
            return rightAlignedNode(root.getRight());
        } else if (null != root.getLeft()) {
            return rightAlignedNode(root.getLeft());
        }

        return root;
    }

    default TreeNode leftAlignedNode(TreeNode root) {
        if (null == root)
            return null;

        if (null != root.getLeft()) {
            return rightAlignedNode(root.getLeft());
        } else if (null != root.getRight()) {
            return rightAlignedNode(root.getRight());
        }

        return root;
    }


    /****************** Java.Tree properties methods *******************/

    boolean search(TreeNode<Integer> root, Integer data);

    int largestBSTSize(TreeNode<Integer> root);

    boolean isBST(TreeNode<Integer> root);


    /****************** Successors and predecessors *******************/
    Integer inOrderSuccessor(TreeNode<Integer> root, TreeNode<Integer> node);

    Integer inOrderPredecessor(TreeNode<Integer> root, TreeNode<Integer> node);

    Integer preOrderSuccessor(TreeNode<Integer> root, TreeNode<Integer> node);

    Integer preOrderPredecessor(TreeNode<Integer> root, TreeNode<Integer> node);

    Integer postOrderSuccessor(TreeNode<Integer> root, TreeNode<Integer> node);


    //TODO: need to implement
    Integer postOrderPredecessor(TreeNode<Integer> root, TreeNode<Integer> node);

    /****************** Default implementation of level order Successors and predecessors *******************/

    default Integer levelOrderSuccessor(TreeNode<Integer> root, TreeNode<Integer> node) {

        if (null == root || null == node)
            return null;


        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);

        //Search in every level
        while (!queue.isEmpty()) {

            TreeNode temp = queue.poll();


            if (null != temp.getLeft())
                queue.add(temp.getLeft());

            if (null != temp.getRight())
                queue.add(temp.getRight());

            if (temp == node)
                break;


        }

        if (queue.isEmpty())
            return null;
        return (Integer) queue.poll().getData();

    }

    default Integer levelOrderPredecessor(TreeNode<Integer> root, TreeNode<Integer> node) {
        if (null == root || null == node)
            return null;


        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);

        TreeNode lastNdoe = null;

        //Search in every level
        while (!queue.isEmpty()) {

            TreeNode temp = queue.poll();


            if (null != temp.getLeft())
                queue.add(temp.getLeft());

            if (null != temp.getRight())
                queue.add(temp.getRight());

            if (temp == node)
                break;
            lastNdoe = temp;


        }
        if (lastNdoe != null)
            return (Integer) lastNdoe.getData();
        return null;
    }


    TreeNode<Integer> lowestCommonAncestor(TreeNode<Integer> root, TreeNode<Integer> alpha, TreeNode<Integer> beta);


}
