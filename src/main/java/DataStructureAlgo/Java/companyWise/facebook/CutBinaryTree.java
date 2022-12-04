package DataStructureAlgo.Java.companyWise.facebook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-20
 * Description: https://aonecode.com/aplusplus/interviewctrl/getInterview/4630517297687979583
 * Give a binary tree, find if it's possible to cut the tree into two halves of equal sum. You can only cut one edge.
 * <p>
 * [FACEBOOK]
 */
public class CutBinaryTree {


    static class Node {
        int v;
        Node left, right;

        Node(int v) {
            this.v = v;
            left = right = null;
        }
    }


    /**
     * Build the sum array of given tree, and if sum is even, then find the index where the sum is so far is
     * half of overall sum
     *
     * @param root
     * @return
     */
    private static boolean isCutPossible(Node root) {

        if (null == root)
            return true;

        List<Integer> sumArray = sumArray(root);

        int sum = sumArray.get(sumArray.size() - 1);

        if (sum % 2 == 0) {

            for (Integer s : sumArray) {

                if (s == sum / 2)
                    return true;
            }
        }
        return false;


    }

    private static List<Integer> sumArray(Node root) {
        if (null == root)
            return Collections.EMPTY_LIST;

        List<Integer> sumList = new ArrayList<>();

        sumArray(root, sumList);

        return sumList;

    }

    /**
     * Similar to Size of binary tree
     * @param root
     * @param sumList
     * @return
     */
    private static int sumArray(Node root, List<Integer> sumList) {
        if (root == null)
            return 0;

        int leftSum = sumArray(root.left, sumList);
        int rightSum = sumArray(root.right, sumList);
        sumList.add(leftSum + rightSum + root.v);

        return leftSum + rightSum + root.v;


    }


    public static void main(String []args) {

        test(buildBTree1());
        test(buildBTree2());
    }

    private static void test(Node root) {

        System.out.println("Given tree inOrder " + inorder(root));
        System.out.println("Cut Possible : " + isCutPossible(root));


    }

    private static List<Integer> inorder(Node root) {
        List<Integer> inorder = new ArrayList<>();
        inorder(root, inorder);
        return inorder;
    }

    private static void inorder(Node root, List<Integer> inorder) {
        if (null == root)
            return;

        inorder(root.left, inorder);
        inorder.add(root.v);
        inorder(root.right, inorder);

    }

    private static Node buildBTree1() {

        Node root = new Node(5);
        root.left = new Node(4);
        root.right = new Node(8);
        root.left.left = new Node(15);
        root.left.right = new Node(-8);
        root.right.left = new Node(16);

        return root;


    }

    private static Node buildBTree2() {

        Node root = new Node(5);
        root.left = new Node(6);
        root.right = new Node(8);
        root.left.left = new Node(3);
        root.left.right = new Node(2);
        root.right.left = new Node(8);

        return root;


    }
}

