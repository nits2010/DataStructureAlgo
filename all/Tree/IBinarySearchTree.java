package Tree;

        import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 28/12/18
 * Description:
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


}
