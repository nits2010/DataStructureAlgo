package Java.nonleetcode.Tree;

import Java.LeetCode.CloneListRandomPointer;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-22
 * Description:
 * https://leetcode.com/problems/clone-graph/discuss/341276/Clone-single-Graph-multi-component-graph.-Clone-List-.-Clone-Binary-Tree
 */

class NodeWithRandom {
    NodeWithRandom left, right, random;
    int value;

    public NodeWithRandom(int value) {
        this.value = value;
    }

}


public class CloneTreeRandomPointer {

    public static void main(String []args) {

        NodeWithRandom tree = new NodeWithRandom(10);
        NodeWithRandom n2 = new NodeWithRandom(6);
        NodeWithRandom n3 = new NodeWithRandom(12);
        NodeWithRandom n4 = new NodeWithRandom(5);
        NodeWithRandom n5 = new NodeWithRandom(8);
        NodeWithRandom n6 = new NodeWithRandom(11);
        NodeWithRandom n7 = new NodeWithRandom(13);
        NodeWithRandom n8 = new NodeWithRandom(7);
        NodeWithRandom n9 = new NodeWithRandom(9);
        tree.left = n2;
        tree.right = n3;
        tree.random = n2;
        n2.left = n4;
        n2.right = n5;
        n2.random = n8;
        n3.left = n6;
        n3.right = n7;
        n3.random = n5;
        n4.random = n9;
        n5.left = n8;
        n5.right = n9;
        n5.random = tree;
        n6.random = n9;
        n9.random = n8;

        System.out.println("Original Tree\n");

        inorder(tree);

        CloneTree cloneTree = new CloneTree();
        NodeWithRandom clone = cloneTree.clone(tree);
        System.out.println("\nClone Tree\n");
        inorder(clone);


    }

    private static void inorder(NodeWithRandom root) {
        if (null == root)
            return;

        inorder(root.left);
        System.out.println(root.value);
        inorder(root.right);
    }

}

/**
 * Logic is same as {@link CloneListRandomPointer}
 */
class CloneTree {


    public NodeWithRandom clone(NodeWithRandom root) {

        if (root == null)
            return null;

        NodeWithRandom cloneRoot = cloneLeftRight(root);
        cloneRandom(root, cloneRoot);
        restoreTree(root, cloneRoot);

        return cloneRoot;

    }


    /**
     * 1. Create a clone node, insert it between left nodes
     * 2. Do it recursively for left sub-tree
     * 3. for right node, clone it and set clone right correctly by recurring on right side
     *
     * @param root
     * @return clone node root pointer
     */
    private NodeWithRandom cloneLeftRight(NodeWithRandom root) {

        if (root == null)
            return null;


        NodeWithRandom clone = new NodeWithRandom(root.value);

        //Put clone in between left nodes of original
        NodeWithRandom left = root.left;

        // insert it between left nodes
        root.left = clone;
        clone.left = left;

        //clone left tree;  Do it recursively for left sub-tree
        if (left != null)
            left.left = cloneLeftRight(left);

        //for right node, clone it and set clone right correctly by recurring on right side
        clone.right = cloneLeftRight(root.right);

        //return clone root node
        return clone;
    }

    /**
     * Clone Random;
     * 1. Since root left is nothing but a clone tree root, so clone.random = root.random.left
     * 2. Do recursively for left sub-tree and right sub-tree
     *
     * @param root
     * @param cloneRoot
     */
    private void cloneRandom(NodeWithRandom root, NodeWithRandom cloneRoot) {

        if (root == null)
            return;

        if (root.random != null)
            cloneRoot.random = root.random.left;
        else
            cloneRoot.random = null;

        //do for left sub-tree
        if (root.left != null && cloneRoot.left != null)
            cloneRandom(root.left.left, cloneRoot.left.left);

        //do for right sub-tree
        cloneRandom(root.right, cloneRoot.right);


    }


    /**
     * Restore tree;
     * 1. since root.left is noting but clone root.
     * 2. root.left = clone.left
     * and clone.left = clone.left.left
     * <p>
     * Do recursively for left and right sub-tree
     *
     * @param root
     * @param cloneRoot
     */
    private void restoreTree(NodeWithRandom root, NodeWithRandom cloneRoot) {

        if (root == null)
            return;

        //is root has left child
        if (cloneRoot.left != null) {
            NodeWithRandom clonesLeft = cloneRoot.left.left;
            root.left = cloneRoot.left;
            cloneRoot.left = clonesLeft;

        } else
            root.left = null; //since root has no left sub-tree

        //left sub-tree
        restoreTree(root.left, cloneRoot.left);

        //right sub-tree
        restoreTree(root.right, cloneRoot.right);
    }

}
