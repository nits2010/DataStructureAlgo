package Java.Tree;

import Java.LeetCode.tree.MaximumPathSum;
import Java.Tree.traversal.EulerTour;
import Java.Tree.traversal.MoriesTreeTraversal;
import Java.Tree.traversal.TreeTraversalIterative;
import Java.Tree.traversal.TreeTraversalRecursive;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

    TreeNode<Integer> delete(Integer data, TreeNode<Integer> root);


    /****************** default implementation Traversal *******************/

    /****************** InOrder - default implementation Traversal *******************/


    default List<Integer> inOrder(TreeNode<Integer> root) {
        return TreeTraversalRecursive.inOrder(root);
    }


    /****************** PostOrder  - default implementation Traversal *******************/

    default List<Integer> postOrder(TreeNode<Integer> root) {
        return TreeTraversalRecursive.postOrder(root);
    }


    /****************** PreOrder - default implementation Traversal *******************/

    default List<Integer> preOrder(TreeNode<Integer> root) {
        return TreeTraversalRecursive.preOrder(root);
    }


    /****************** Level Order - default implementation Traversal *******************/

    default List<Integer> levelOrder(TreeNode<Integer> root) {

        return TreeTraversalRecursive.levelOrder(root);
    }


    /****************** In Order Iterative - default implementation Traversal *******************/
    default List<Integer> inOrderIterative(TreeNode<Integer> root) {
        return TreeTraversalIterative.inOrderIterative(root);
    }


    /****************** Pre Order Iterative - default implementation Traversal *******************/
    default List<Integer> preOrderIterative(TreeNode<Integer> root) {
        return TreeTraversalIterative.preOrderIterative(root);
    }

    /****************** Post Order Iterative - default implementation Traversal *******************/
    default List<Integer> postOrderIterative(TreeNode<Integer> root) {
        return TreeTraversalIterative.postOrderIterative(root);
    }


    /****************** Mories algorithm for Java.Tree Traversal- without stack *******************/


    /****************** InOrder Mories algorithm for Java.Tree Traversal- without stack *******************/
    default List<Integer> inOrderMories(TreeNode<Integer> root) {
        return MoriesTreeTraversal.inOrderMories(root);
    }

    /****************** PreOrder Mories algorithm for Java.Tree Traversal- without stack *******************/

    default List<Integer> preOrderMories(TreeNode<Integer> root) {
        return MoriesTreeTraversal.preOrderMories(root);

    }


    /****************** Default implementation of Euler Tour of a Binary tree *******************/

    default List<Integer> eulerTour(TreeNode<Integer> root) {
        return EulerTour.eulerTour(root);
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


    default boolean isBST(TreeNode<Integer> root) {

        return isBSTUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }


    default boolean isBSTUtil(TreeNode<Integer> root, Integer min, Integer max) {

        if (null == root)
            return true;


        if (root.getData() < min || root.getData() > max)
            return false;


        return (isBSTUtil(root.getLeft(), min, root.getData()) && isBSTUtil(root.getRight(), root.getData(), max));

    }


    /****************** Java.Tree properties methods *******************/

    boolean search(TreeNode<Integer> root, Integer data);

    int largestBSTSize(TreeNode<Integer> root);


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

    /**
     * https://www.geeksforgeeks.org/flip-binary-tree/
     * https://medium.com/@jimdaosui/binary-tree-upside-down-77af203c79af
     * <p>
     * CODE is wrong if the left most node has right child then it is wrong
     *
     * @param root
     * @return
     */
    default TreeNode flipTreeUpSideDown(TreeNode root) {

        return FlipTreeUpSideDown.flipTreeUpSideDown(root);
    }


    /**
     * Boundary traversal
     *
     * @param root
     * @return
     */
    default List<TreeNode> boundaryTraversal(TreeNode root) {
        return TreeBoundaryTraversal.boundaryTraversal(root);
    }


    /**
     * //https://leetcode.com/problems/binary-tree-maximum-path-sum/
     *
     * @param root
     * @return
     */
    default int maximumPathSumAnyNode(TreeNode root) {
        return MaximumPathSum.maximumPathSum(root);
    }


    /**
     * https://www.geeksforgeeks.org/find-maximum-path-sum-two-leaves-binary-tree/
     *
     * @param root
     * @return
     */
    default int maximumPathSumLeafToLeaf(TreeNode root) {
        return MaximumPathSum.maximumPathSumLeafToLeaf(root);
    }


    /**
     * https://www.careercup.com/question?id=5074469692375040
     */

    default int maximumEvenPathSum(TreeNode<Integer> root) {
        return MaximumPathSum.maximumEvenPathSum(root);
    }

}
