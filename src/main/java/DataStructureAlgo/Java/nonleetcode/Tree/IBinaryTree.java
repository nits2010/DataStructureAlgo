package DataStructureAlgo.Java.nonleetcode.Tree;

import DataStructureAlgo.Java.LeetCode.tree.MaximumPathSum;
import DataStructureAlgo.Java.nonleetcode.Tree.traversal.EulerTour;
import DataStructureAlgo.Java.nonleetcode.Tree.traversal.MoriesTreeTraversal;
import DataStructureAlgo.Java.nonleetcode.Tree.traversal.TreeTraversalIterative;
import DataStructureAlgo.Java.nonleetcode.Tree.traversal.TreeTraversalRecursive;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Author: Nitin Gupta
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

        return isBSTUtil(root, null, null);
    }


    default boolean isBSTUtil(TreeNode<Integer> root, Integer min, Integer max) {

        if (null == root)
            return true;

        if (min != null && root.getData() <= min)
            return false;

        if (max != null && root.getData() >= max)
            return false;


        return (isBSTUtil(root.getLeft(), min, root.getData()) && isBSTUtil(root.getRight(), root.getData(), max));

    }

    /****************** Java.Tree properties methods *******************/

    boolean search(TreeNode<Integer> root, Integer data);

    int largestBSTSize(TreeNode<Integer> root);


    /****************** Successors and predecessors *******************/
    /**
     * 285. Inorder Successor in BST
     * https://leetcode.com/problems/inorder-successor-in-bst/description/
     * @param root
     * @param node
     * @return
     */
    default Integer inOrderSuccessor(TreeNode<Integer> root, TreeNode<Integer> node) {
        if (null == root || null == node)
            return null;
        boolean[] found = new boolean[1];
        TreeNode<Integer>[] successor = new TreeNode[1];
        inOrderSuccessor(root, node, successor, found);
        return successor[0] == null ? null : successor[0].getData();

    }

    private void inOrderSuccessor(TreeNode<Integer> root, TreeNode<Integer> node, TreeNode<Integer>[] successor, boolean[] found) {
        if (null == root) {
            return;
        }

        inOrderSuccessor(root.getLeft(), node, successor, found);

        if (found[0] && successor[0] == null) {
            successor[0] = root;
            return;
        }

        if (root.getData().compareTo(node.getData()) == 0) {
            found[0] = true;
        }

        inOrderSuccessor(root.getRight(), node, successor, found);

    }


    default Integer inOrderPredecessor(TreeNode<Integer> root, TreeNode<Integer> node){
        if (null == root || null == node)
            return null;
        TreeNode<Integer>[] predecessor = new TreeNode[1];
        TreeNode<Integer>[] prev = new TreeNode[1];
        inOrderPredecessor(root, node, predecessor, prev);
        return predecessor[0] == null ? null : predecessor[0].getData();

    }

    private void inOrderPredecessor(TreeNode<Integer> root, TreeNode<Integer> node, TreeNode<Integer>[] predecessor, TreeNode<Integer>[] prev) {
        if (null == root)
            return;

        inOrderPredecessor(root.getLeft(), node, predecessor, prev);
        if (root.getData().compareTo(node.getData()) == 0) {
            predecessor[0] = prev[0];
        }
        prev[0] = root;
        inOrderPredecessor(root.getRight(), node, predecessor, prev);
    }

    default Integer preOrderSuccessor(TreeNode<Integer> root, TreeNode<Integer> node){
        //Case 1: if this node is root itself then there is no predecessor of root
        if (null == root || null == node || root.getData() == node.getData())
            return null;


        Integer[] successor = new Integer[1];
        preOrderSuccessor(root, node, successor, new boolean[1]);
        return successor[0];

    }


    private boolean preOrderSuccessor(TreeNode<Integer> root, TreeNode<Integer> node, Integer[] successor, boolean[] found) {

        if (null == root || successor[0] != null)
            return true;

        if (found[0] && successor[0] == null) {
            //then this root is the successor
            successor[0] = root.getData();
            return true;
        }

        //node found, store the traversal path
        if (root.getData().compareTo(node.getData()) == 0){
            found[0] = true;
            return true;
        }

        return preOrderSuccessor(root.getLeft(), node, successor, found) || preOrderSuccessor(root.getRight(), node, successor, found);


    }

    default Integer preOrderPredecessor(TreeNode<Integer> root, TreeNode<Integer> node){
        if (null == root || null == node)
            return null;

        Integer[] predecessor = new Integer[1];
        ;
        preOrderPredecessor(root, node, predecessor);
        return predecessor[0];

    }

    private boolean preOrderPredecessor(TreeNode<Integer> root, TreeNode<Integer> node, Integer[] predecessor) {
        if (null == root) {
            return false;
        }

        if (root.getData().compareTo(node.getData()) == 0) {
            // node found, store the traversal path
            return true;
        }

        predecessor[0] = root.getData();
        return preOrderPredecessor(root.getLeft(), node, predecessor) || preOrderPredecessor(root.getRight(), node, predecessor);

    }

    default Integer postOrderSuccessor(TreeNode<Integer> root, TreeNode<Integer> node){
        if (null == root || null == node)
            return null;

        TreeNode<Integer>[] successor = new TreeNode[1];
        postOrderSuccessor(root, node, successor, new boolean[1]);
        return successor[0] == null ? null : successor[0].getData();

    }

    private void postOrderSuccessor(TreeNode<Integer> root, TreeNode<Integer> node, TreeNode<Integer>[] successor, boolean []found) {
        if (null == root || successor[0]!= null)
            return ;

        postOrderSuccessor(root.getLeft(), node, successor, found);
        postOrderSuccessor(root.getRight(), node, successor, found);

        if (found[0] && successor[0] == null) {
            //then this root is the successor
            successor[0] = root;
            return ;
        }

        //node found, store the traversal path
        if (root.getData().compareTo(node.getData()) == 0) {
            found[0] = true;
        }
    }



    default Integer postOrderPredecessor(TreeNode<Integer> root, TreeNode<Integer> node){
        if (null == root || null == node)
            return null;

        TreeNode<Integer>[] predecessor = new TreeNode[1];
        //previous node in postorder
        TreeNode<Integer>[] prev = new TreeNode[1];

        postOrderPredecessor(root, node, predecessor, prev);
        return predecessor[0] == null ? null : predecessor[0].getData();
    }


    private void postOrderPredecessor(TreeNode<Integer> root, TreeNode<Integer> node, TreeNode<Integer>[] predecessor, TreeNode<Integer>[] prev) {
        if (null == root)
            return;

        postOrderPredecessor(root.getLeft(), node, predecessor, prev);
        postOrderPredecessor(root.getRight(), node, predecessor, prev);

        //node found, store the traversal path
        if (root.getData().compareTo(node.getData()) == 0){
            if (prev[0] != null) {
                predecessor[0] = prev[0];
            }
        }

        //store the previous node
        prev[0] = root;
    }

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
