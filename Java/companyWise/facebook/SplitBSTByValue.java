package Java.companyWise.facebook;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-20
 * Description: https://aonecode.com/aplusplus/interviewctrl/getInterview/8713158291562499583
 * https://medium.com/algorithms-and-leetcode/solving-tree-problems-on-leetcode-d0b7a9b4a7a4
 * Given a Binary Search Tree (BST) with root node root, and a target value V, split the tree into two subtrees where one subtree has nodes that are all smaller or equal to the target value, while the other subtree has all nodes that are greater than the target value. It's not necessarily the case that the tree contains a node with value V.
 * <p>
 * Additionally, most of the structure of the original tree should remain. Formally, for any child C with parent P in the original tree, if they are both in the same subtree after the split, then node C should still have the parent P. You should output the root TreeNode of both subtrees after splitting, in any order.
 * <p>
 * Example 1:
 * <p>
 * Input: root = [4,2,6,1,3,5,7], V = 2
 * Output: [[2,1],[4,3,6,null,null,5,7]]
 * Explanation:
 * Note that root, output[0], and output[1] are TreeNode objects, not arrays.
 * <p>
 * The given tree [4,2,6,1,3,5,7] is represented by the following diagram:

 * <p>
 * 4
 * /   \
 * 2      6
 * / \    / \
 * 1   3  5   7
 * <p>
 * while the diagrams for the outputs are:
 * <p>
 * 4
 * /   \
 * 3      6      and    2
 * / \          /
 * 5   7         1
 * <p>
 * Note:
 * The size of the BST will not exceed 50.
 * The BST is always valid and each node's value is different.
 */

class BSTNode {
    int v;
    BSTNode left, right;

    BSTNode(int v) {
        this.v = v;
        left = right = null;
    }
}

public class SplitBSTByValue {


    public static void main(String args[]) {

        test();
    }

    private static List<Integer> inorder(BSTNode root) {
        List<Integer> inorder = new ArrayList<>();
        inorder(root, inorder);
        return inorder;
    }

    private static void inorder(BSTNode root, List<Integer> inorder) {
        if (null == root)
            return;

        inorder(root.left, inorder);
        inorder.add(root.v);
        inorder(root.right, inorder);

    }


    private static void test() {
        BinarySearchTreeBased bstSplit = new BinarySearchTreeBased();

        test(getBST1(), 2, bstSplit);

        test(getBST1(), 3, bstSplit);

        test(getBST1(), 90, bstSplit);

        test(getBST2(), 10, bstSplit);

        test(getBST2(), 22, bstSplit);

        test(getBST2(), 18, bstSplit);

        test(getBST2(), 32, bstSplit);


    }

    private static void test(BSTNode root, int v, BinarySearchTreeBased bstSplit) {
        System.out.println("\n Testing bst :[ " + inorder(root) + "] v " + v);
        BSTNode[] splits = bstSplit.splitBST(root, v);
        if (splits.length != 0)
            System.out.println(" smaller part [ " + inorder(splits[0]) + "] and greater part [" + inorder(splits[1]) + "]");
        else
            System.out.println("Not found");
    }

    private static BSTNode getBST1() {

        BSTNode root = new BSTNode(4);
        root.left = new BSTNode(2);
        root.left.left = new BSTNode(1);
        root.left.right = new BSTNode(3);
        root.right = new BSTNode(6);
        root.right.left = new BSTNode(5);
        root.right.right = new BSTNode(7);

        return root;
    }


    private static BSTNode getBST2() {

        BSTNode root = new BSTNode(28);

        root.left = new BSTNode(16);

        root.left.left = new BSTNode(14);
        root.left.left.left = new BSTNode(12);
        root.left.left.right = new BSTNode(15);

        root.left.right = new BSTNode(22);
        root.left.right.left = new BSTNode(18);
        root.left.right.right = new BSTNode(25);


        root.right = new BSTNode(32);
        root.right.left = new BSTNode(31);
        root.right.right = new BSTNode(35);

        return root;
    }
}


class BinarySearchTreeBased {


    /**
     * This is similar to Trim Tree  {@link Java.LeetCode.TrimBinarySearchTree}.
     * Lets revise it,
     * 1. We purge the left sub-tree if its outside the lowest range since all left won't satisfy the condition
     * 2. WE purge the right sub-tree if its outside the highest range since all right won't satisfy the condition.
     * 3. If this is in range, then we consider it and try to find solution for its left and right sub-tree.
     * <p>
     * <p>
     * Observation:
     * Instead of having two ranges, we have only one range in this problem V. And we need to divide in smaller (<=V) and greater ( > V ) parts ; So;
     * Same;
     * 1. if this root is less or equal to V then all left sub-tree will satisfy the condition (since all <=V) but right sub-tree may not satisfy the condition. Split on right side
     * 2. If this root is greater then V then all right sub-tree won't satisfy the condition (since all >V) but left sub-tree may satisfy the condition. Split on left side;
     * <p>
     * Now, question is how to attach the tree back so that they follow what is asked in problem.
     * <p>
     * Note;
     * 1. When we recurring on right side (in case 1) then we need to correct the current node right sub-tree with all higher value sub-tree  of it [ so that tree remain BST ]
     * 2. When we recurring on left side (in case 2) then we need to correct the current node left sub-tree with all lower value sub-tree of it [so that tree remain BST ].
     * <p>
     * Means at each point, we need to know what are the root of lower value sub-tree or higher value sub-tree w.r.t. current node.
     * <p>
     * with an exception that if root is null, then both left and right is null tree.
     *
     * @param root
     * @param v
     * @return
     */
    public BSTNode[] splitBST(BSTNode root, int v) {

        //if root is null, then both left and right is null tree.
        if (null == root) {
            return new BSTNode[]{null, null};
        }

        /*
        Case 1:  if this root is less or equal to V then all left sub-tree will satisfy the condition (since all <=V) but right sub-tree may not satisfy the condition. Split on right side
        Connect: We need to correct the current node right sub-tree with all higher value sub-tree  of it [ so that tree remain BST ]
        Return: [lower and higher value root ] => [current node, higher ]

         */

        if (root.v <= v) {
            //Split on right side
            BSTNode[] lowHigh = splitBST(root.right, v);

            // correct the current node right sub-tree
            root.right = lowHigh[0];

            return new BSTNode[]{root, lowHigh[1]};


        } else {
            /*
            Case 2: If this root is greater then V then all right sub-tree won't satisfy the condition (since all >V) but left sub-tree may satisfy the condition. Split on left side;
            Connect: When we recurring on left side (in case 2) then we need to correct the current node left sub-tree with all lower value sub-tree of it [so that tree remain BST ].
            Return: [lower and higher value root ] => [lower,  node ]
             */

            //Split on left side;
            BSTNode[] lowHigh = splitBST(root.left, v);

            //correct the current node left sub-tree
            root.left = lowHigh[1];

            return new BSTNode[]{lowHigh[0], root};

        }

    }
}








