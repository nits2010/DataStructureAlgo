package DataStructureAlgo.Java.nonleetcode.Tree;

import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 28/12/18
 * Question Title: I Binary Search Tree
 * Link: TODO: Add Link
 * Description:
 * Description:
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public interface IBinarySearchTree extends IBinaryTree {


    TreeNode postOrderToBst(List<Integer> postOrder);

    TreeNode preOrderToBst(List<Integer> preOrder);

    TreeNode inOrderToBst(List<Integer> inOrder);

    TreeNode levelOrderToBst(List<Integer> inOrder);

    List<Integer> preOrderToPostOrder(List<Integer> preOrder);

    List<Integer> postOrderToPreOrder(List<Integer> preOrder);


    //https://www.geeksforgeeks.org/check-for-identical-bsts-without-building-the-trees/
    boolean checkForIdenticalBST(List<Integer> input1, List<Integer> inpput2);


    double medianO1(TreeNode<Integer> root);

    List<Integer> mergeInSorted(TreeNode<Integer> root1, TreeNode<Integer> root2);

}
