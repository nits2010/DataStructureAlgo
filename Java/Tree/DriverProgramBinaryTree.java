package Java.Tree;

import java.util.LinkedList;
import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 26/12/18
 * Description:
 */
public class DriverProgramBinaryTree {


    public static void main(String args[]) {


        IBinaryTree binaryTree = new BinaryTree();

        List<TreeNode<Integer>> nodes = new LinkedList<>();

        BinaryTreeNode<Integer> bt = new BinaryTreeNode<>(1);
        nodes.add(bt);

        nodes.add(binaryTree.insert(2, bt));
        nodes.add(binaryTree.insert(3, bt));
        nodes.add(binaryTree.insert(4, bt));
        nodes.add(binaryTree.insert(5, bt));
        nodes.add(binaryTree.insert(6, bt));
        nodes.add(binaryTree.insert(7, bt));
//        nodes.add(binaryTree.insert(8, bt));

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


        System.out.println("***********BST***************");


        BinaryTreeNode<Integer> bst = new BinaryTreeNode<>(4);

        binaryTree.insert(2, bst);
        binaryTree.insert(5, bst);
        binaryTree.insert(1, bst);
        binaryTree.insert(3, bst);

        TestHelper.runTraversal(binaryTree, bst);

        System.out.println(binaryTree.isBST(bst));

        System.out.println("***********Largest BST***************");

        BinaryTreeNode<Integer> largestBST = new BinaryTreeNode<>(60);

        binaryTree.insert(55, largestBST);
        binaryTree.insert(70, largestBST);
        binaryTree.insert(50, largestBST);


        System.out.println(binaryTree.inOrder(largestBST));
        System.out.println(binaryTree.isBST(largestBST));
        System.out.println(binaryTree.largestBSTSize(largestBST));


    }
}
