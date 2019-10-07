package Java.nonleetcode.Tree;

import java.util.Arrays;
import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 30/12/18
 * Description:
 */
public class TestHelper {


    public static void runFlipedTreeTest(IBinaryTree binaryTree, TreeNode root) {
        System.out.println(binaryTree.inOrder(root));
        TreeNode newRoot = binaryTree.flipTreeUpSideDown(root);
        System.out.println(binaryTree.inOrder(newRoot));
    }

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

    public static void fromOrderToOrderTest() {

        System.out.println("Running pre-order To Post Order test ");

        IBinaryTree binaryTree = new BinarySearchTree();

        TreeNode<Integer> root = new BinaryTreeNode<>(1);
        binaryTree.insert(2, root);
        binaryTree.insert(3, root);
        binaryTree.insert(4, root);
        binaryTree.insert(5, root);
        binaryTree.insert(6, root);
        binaryTree.insert(7, root);


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

    public static void boundaryTraversal(IBinaryTree binaryTree, TreeNode<Integer> root) {


        List<TreeNode> traversal = binaryTree.boundaryTraversal(root);
        traversal.stream().forEach(n -> System.out.print(n.getData() + " "));

    }

    public static void medianTest() {
        IBinaryTree binaryTree = new BinarySearchTree();


        //======= Test 1============
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(4);

        binaryTree.insert(2, root);
        binaryTree.insert(5, root);
        binaryTree.insert(1, root);
        binaryTree.insert(3, root);

        TestHelper.runTraversal(binaryTree, root);

        System.out.println("********** Median Test********");
        List<Integer> inOrder = binaryTree.inOrder(root);
        System.out.println("inorder " + inOrder);
        System.out.println("Median: " + ((BinarySearchTree) binaryTree).medianO1(root));


        //======= Test 2============

        TreeNode<Integer> bt = new BinaryTreeNode<>(1);


        binaryTree.insert(2, bt);
        binaryTree.insert(3, bt);
        binaryTree.insert(4, bt);
        binaryTree.insert(5, bt);
        binaryTree.insert(6, bt);
        binaryTree.insert(7, bt);


        System.out.println("********** Median Test********");
        inOrder = binaryTree.inOrder(bt);
        System.out.println("inorder " + inOrder);
        System.out.println("Median: " + ((BinarySearchTree) binaryTree).medianO1(bt));

    }


    public static void largestBST() {

        IBinaryTree binaryTree = new BinaryTree();

        BinaryTreeNode<Integer> largestBST1 = buildTree(binaryTree, new int[]{60, 55, 70, 50});
        System.out.println("\n largest Bst size " + binaryTree.largestBSTSize(largestBST1) + " should be 4");


        BinaryTreeNode<Integer> largestBST2 = buildTree(binaryTree, new int[]{60, 55, 70, 80, 25, 70, 54});
        System.out.println("\n largest Bst size " + binaryTree.largestBSTSize(largestBST2) + " should be 1");


        BinaryTreeNode<Integer> largestBST3 = buildTree(binaryTree, new int[]{70, 80, 60, 40, 90, 50, 100, 20, 50, 30, 45, 45, 55, 70, 120});
        System.out.println("\n largest Bst size " + binaryTree.largestBSTSize(largestBST3) + "should be 7");


    }

    private static BinaryTreeNode<Integer> buildTree(IBinaryTree binaryTree, int nums[]) {
        BinaryTreeNode<Integer> tree =
                new BinaryTreeNode<>(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            binaryTree.insert(nums[i], tree);
        }
        return tree;
    }


    public static void identicalBST() {
        IBinaryTree binaryTree = new BinarySearchTree();

        System.out.println("Check for identical bst ");

        Integer input1[] = {8, 3, 6, 1, 4, 7, 10, 14, 13};
        Integer input2[] = {8, 10, 14, 3, 6, 4, 1, 7, 13};

        System.out.println("Input array, a->");
        Arrays.stream(input1).forEach(e -> System.out.print(e + " "));
        System.out.println("Input array, b->");
        Arrays.stream(input2).forEach(e -> System.out.print(e + " "));
        System.out.println("\n" + ((BinarySearchTree) binaryTree).checkForIdenticalBST(Arrays.asList(input1), Arrays.asList(input2)));


        Integer input3[] = {8, 7, 3, 6, 1, 4, 13, 14, 10};


        System.out.println("Input array, a->");
        Arrays.stream(input3).forEach(e -> System.out.print(e + " "));
        System.out.println("Input array, b->");
        Arrays.stream(input2).forEach(e -> System.out.print(e + " "));
        System.out.println("\n" + ((BinarySearchTree) binaryTree).checkForIdenticalBST(Arrays.asList(input2), Arrays.asList(input3)));

    }

    public static void isBST() {
        IBinaryTree binaryTree = new BinaryTree();


        BinaryTreeNode<Integer> isBSTRoot = new BinaryTreeNode<>(4);

        binaryTree.insert(2, isBSTRoot);
        binaryTree.insert(5, isBSTRoot);
        binaryTree.insert(1, isBSTRoot);
        binaryTree.insert(3, isBSTRoot);
        System.out.println("\n is BST: " + binaryTree.isBST(isBSTRoot));

    }

}
