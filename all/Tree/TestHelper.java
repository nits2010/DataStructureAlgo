package Tree;

import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 30/12/18
 * Description:
 */
public class TestHelper {

    public static void runPreSucTest(List<TreeNode<Integer>> nodes, IBinaryTree binaryTree, TreeNode<Integer> root) {


        for (TreeNode node : nodes) {
            System.out.println(" inOrderSuccessor " + node.getData() + " is " + binaryTree.inOrderSuccessor(root, node));
            System.out.println(" inOrderPredecessor " + node.getData() + " is " + binaryTree.inOrderPredecessor(root, node));
            System.out.println(" preOrderSuccessor " + node.getData() + " is " + binaryTree.preOrderSuccessor(root, node));
            System.out.println(" preOrderPredecessor " + node.getData() + " is " + binaryTree.preOrderPredecessor(root, node));
            System.out.println(" postOrderSuccessor " + node.getData() + " is " + binaryTree.postOrderSuccessor(root, node));
            System.out.println(" levelOrderSuccessor " + node.getData() + " is " + binaryTree.levelOrderSuccessor(root, node));
            System.out.println(" levelOrderPredecessor " + node.getData() + " is " + binaryTree.levelOrderPredecessor(root, node));


        }
    }

    public static void runTraversal(IBinaryTree binaryTree, TreeNode<Integer> root) {
        List<Integer> preOrder = binaryTree.preOrder(root);
        List<Integer> preOrderIterative = binaryTree.preOrderIterative(root);

        List<Integer> inorder = binaryTree.inOrder(root);
        List<Integer> inorderIterative = binaryTree.inOrderIterative(root);

        List<Integer> postOrder = binaryTree.postOrder(root);
        List<Integer> postOrderIterative = binaryTree.postOrderIterative(root);

        List<Integer> levelOrder = binaryTree.levelOrder(root);

        System.out.println("Pre-Order " + preOrder);
        System.out.println("Pre-Order Iterative " + preOrderIterative);
        System.out.println();

        System.out.println("Post-Order " + postOrder);
        System.out.println("Post-Order Iterative" + postOrderIterative);
        System.out.println();

        System.out.println("In-Order " + inorder);
        System.out.println("In-Order Iterative" + inorderIterative);
        System.out.println();

        System.out.println("Level-Order" + levelOrder);

        System.out.println("******** Mories output*****");

        List<Integer> inOrderMories = binaryTree.inOrderMories(root);
        System.out.println("In-Order " + inOrderMories);
        System.out.println();

        List<Integer> preOrderMories = binaryTree.preOrderMories(root);
        System.out.println("pre-Order " + preOrderMories);
        System.out.println();


    }

    public static void fromOrderToOrderTest(IBinaryTree binaryTree, TreeNode<Integer> root) {

        System.out.println("Running pre-order To Post Order test ");

        List<Integer> preOrder = binaryTree.preOrder(root);
        List<Integer> postOrder = binaryTree.postOrder(root);

        List<Integer> postOrderFromPreOrder = ((BinarySearchTree) binaryTree).preOrderToPostOrder(preOrder);

        System.out.println("Given preOrder: ");
        preOrder.stream().forEach(System.out::print);

        System.out.println("\nActual postOrder: ");
        postOrder.stream().forEach(System.out::print);

        System.out.println("\nObtained postOrder: ");
        postOrderFromPreOrder.stream().forEach(System.out::print);


    }

    public static void buildFromOrderTest(IBinaryTree binaryTree, List<Integer> givenOrder, int order) {
        System.out.println("\nBuild from order test Running  ");

        switch (order) {
            case 3: //given order is post order

                TreeNode postOrderRoot = ((BinarySearchTree) binaryTree).postOrderToBst(givenOrder);
                System.out.println("Given postOrder : " + givenOrder);
                System.out.println("PostOrder of constructed tree :" + binaryTree.postOrder(postOrderRoot));
                break;

            case 2: //given order is pre order

                TreeNode preOrderRoot = ((BinarySearchTree) binaryTree).preOrderToBst(givenOrder);
                System.out.println("Given ppreOrder : " + givenOrder);
                System.out.println("PreOrder of constructed tree :" + binaryTree.preOrder(preOrderRoot));
                break;

            case 4: //given order is level order

                TreeNode levelOrderRoot = ((BinarySearchTree) binaryTree).levelOrderToBst(givenOrder);
                System.out.println("Given level Order : " + givenOrder);
                System.out.println("LevelOrder of constructed tree :" + binaryTree.levelOrder(levelOrderRoot));
                break;


        }

    }


}
