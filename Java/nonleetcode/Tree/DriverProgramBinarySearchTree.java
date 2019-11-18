package Java.nonleetcode.Tree;

import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 26/12/18
 * Description:
 */
public class DriverProgramBinarySearchTree {


    public static void main(String []args) {


        IBinaryTree binaryTree = new BinarySearchTree();


        TreeNode<Integer> bt = new BinaryTreeNode<>(1);
        binaryTree.insert(2, bt);
        binaryTree.insert(3, bt);
        binaryTree.insert(4, bt);
        binaryTree.insert(5, bt);
        binaryTree.insert(6, bt);
        binaryTree.insert(7, bt);
        System.out.println("\n is BST: " + binaryTree.isBST(bt));


        TestHelper.runPreSucTest(((BinarySearchTree) binaryTree).nodes, binaryTree, bt);

        List<Integer> postOrder = binaryTree.postOrder(bt);
        TestHelper.buildFromOrderTest(binaryTree, postOrder, 3);

        List<Integer> preOrder = binaryTree.preOrder(bt);
        TestHelper.buildFromOrderTest(binaryTree, preOrder, 2);

        List<Integer> inOrder = binaryTree.inOrder(bt);
        TestHelper.buildFromOrderTest(binaryTree, inOrder, 1);

        List<Integer> level = binaryTree.levelOrder(bt);
        TestHelper.buildFromOrderTest(binaryTree, level, 4);


        TestHelper.fromOrderToOrderTest();
        TestHelper.medianTest();
        TestHelper.largestBST();
        TestHelper.identicalBST();


    }




}
