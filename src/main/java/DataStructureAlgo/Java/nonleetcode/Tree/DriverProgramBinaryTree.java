package DataStructureAlgo.Java.nonleetcode.Tree;

import java.util.LinkedList;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 26/12/18
 * Description:
 */
public class DriverProgramBinaryTree {


    public static void main(String []args) {


        IBinaryTree binaryTree = new BinaryTree();

        System.out.println("***********Maximum Even path Sum***************");


        BinaryTreeNode<Integer> evenPathSumRoot4 = new BinaryTreeNode<>(1);
        binaryTree.insert(3, evenPathSumRoot4);
        binaryTree.insert(5, evenPathSumRoot4);
        binaryTree.insert(9, evenPathSumRoot4);
        binaryTree.insert(17, evenPathSumRoot4);
        binaryTree.insert(19, evenPathSumRoot4);

        System.out.println(binaryTree.maximumEvenPathSum(evenPathSumRoot4));


        BinaryTreeNode<Integer> evenPathSumRoot3 = new BinaryTreeNode<>(10);
        binaryTree.insert(2, evenPathSumRoot3);
        binaryTree.insert(4, evenPathSumRoot3);
        binaryTree.insert(8, evenPathSumRoot3);
        binaryTree.insert(18, evenPathSumRoot3);
        binaryTree.insert(9, evenPathSumRoot3);

        System.out.println(binaryTree.maximumEvenPathSum(evenPathSumRoot3));


        BinaryTreeNode<Integer> evenPathSumRoot2 = new BinaryTreeNode<>(10);
        binaryTree.insert(2, evenPathSumRoot2);
        binaryTree.insert(4, evenPathSumRoot2);

        System.out.println(binaryTree.maximumEvenPathSum(evenPathSumRoot2));


        BinaryTreeNode<Integer> evenPathSumRoot = new BinaryTreeNode<>(10);
        binaryTree.insert(2, evenPathSumRoot);
        binaryTree.insert(5, evenPathSumRoot);
        binaryTree.insert(1, evenPathSumRoot);
        binaryTree.insert(101, evenPathSumRoot);
        binaryTree.insert(13, evenPathSumRoot);

        System.out.println(binaryTree.maximumEvenPathSum(evenPathSumRoot));


        List<TreeNode<Integer>> nodes = new LinkedList<>();

        BinaryTreeNode<Integer> bt = new BinaryTreeNode<>(1);
        nodes.add(bt);

        nodes.add(binaryTree.insert(2, bt));
        nodes.add(binaryTree.insert(3, bt));
        nodes.add(binaryTree.insert(4, bt));
        nodes.add(binaryTree.insert(5, bt));
        nodes.add(binaryTree.insert(6, bt));
        nodes.add(binaryTree.insert(7, bt));

        System.out.println("***********Boundary Traversal***************");
        TestHelper.boundaryTraversal(binaryTree, bt);
        System.out.println("***********Boundary Traversal***************\n");

        TestHelper.runTraversal(binaryTree, bt);


        TestHelper.runPreSucTest(nodes, binaryTree, bt);


        System.out.println("Height of Java.Tree: " + binaryTree.height(bt));
        System.out.println("diameter of Java.Tree: " + binaryTree.diameter(bt));

        TreeNode<Integer> alpha = nodes.get(3);
        TreeNode<Integer> beta = nodes.get(1);
        TreeNode<Integer> lca = binaryTree.lowestCommonAncestor(bt, alpha, beta);
        int distance = binaryTree.distanceBetweenTwoNodes(bt, alpha, beta);

        System.out.println("LCA of " + alpha.getData() + "and " + beta.getData() + " Java.Tree: " + (lca != null ? lca.getData() : null));
        System.out.println("Level of " + beta.getData() + " Java.Tree: " + binaryTree.level(bt, beta));
        System.out.println("Distance between " + alpha.getData() + "and " + beta.getData() + " Java.Tree: " + distance);


        /** Flipped tree test ***/
        System.out.println("***********Flipped tree***************");
        BinaryTreeNode<Integer> bt2 = new BinaryTreeNode<>(1);

        binaryTree.insert(2, bt2);
        binaryTree.insert(3, bt2);
        TreeNode<Integer> test = binaryTree.insert(4, bt2);
        binaryTree.insert(5, bt2);
        binaryTree.insert(6, bt2);
        binaryTree.insert(7, bt2);
        test.setLeft(null);
        test.setRight(new BinaryTreeNode<>(8));
        TestHelper.runFlipedTreeTest(binaryTree, bt2);


        TestHelper.largestBST();
        TestHelper.isBST();


    }
}
