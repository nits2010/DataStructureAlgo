package Java.Tree;

import java.util.Arrays;
import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 26/12/18
 * Description:
 */
public class DriverProgramBinarySearchTree {


    public static void main(String args[]) {


        IBinaryTree binaryTree = new BinarySearchTree();


        TreeNode<Integer> bt = new BinaryTreeNode<>(1);


        binaryTree.insert(2, bt);
        binaryTree.insert(3, bt);
        binaryTree.insert(4, bt);
        binaryTree.insert(5, bt);
        binaryTree.insert(6, bt);
        binaryTree.insert(7, bt);


        TestHelper.runPreSucTest(((BinarySearchTree) binaryTree).nodes, binaryTree, bt);

        TestHelper.fromOrderToOrderTest(binaryTree, bt);

        List<Integer> postOrder = binaryTree.postOrder(bt);
        TestHelper.buildFromOrderTest(binaryTree, postOrder, 3);

        List<Integer> preOrder = binaryTree.preOrder(bt);
        TestHelper.buildFromOrderTest(binaryTree, preOrder, 2);

        List<Integer> inOrder = binaryTree.inOrder(bt);
        TestHelper.buildFromOrderTest(binaryTree, inOrder, 1);

        List<Integer> level = binaryTree.levelOrder(bt);
        TestHelper.buildFromOrderTest(binaryTree, level, 4);


        System.out.println("\n is BST: " + binaryTree.isBST(bt));

        ((BinarySearchTree) binaryTree).clear();
        BinaryTreeNode<Integer> bst = new BinaryTreeNode<>(4);

        binaryTree.insert(2, bst);
        binaryTree.insert(5, bst);
        binaryTree.insert(1, bst);
        binaryTree.insert(3, bst);

        TestHelper.runTraversal(binaryTree, bst);


        System.out.println("\n is BST: " + binaryTree.isBST(bst));

        ((BinarySearchTree) binaryTree).clear();
        BinaryTreeNode<Integer> largestBST = new BinaryTreeNode<>(60);

        binaryTree.insert(55, largestBST);
        binaryTree.insert(70, largestBST);
        binaryTree.insert(50, largestBST);


        TestHelper.runTraversal(binaryTree, largestBST);

        System.out.println("\n is BST: " + binaryTree.isBST(largestBST));
        System.out.println("\n largest Bst size " + binaryTree.largestBSTSize(largestBST));


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
}
