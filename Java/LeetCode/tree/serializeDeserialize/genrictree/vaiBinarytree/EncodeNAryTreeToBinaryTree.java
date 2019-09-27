package Java.LeetCode.tree.serializeDeserialize.genrictree.vaiBinarytree;

import Java.HelpersToPrint.GenericPrinter;
import Java.LeetCode.templates.TreeNode;
import Java.LeetCode.tree.serializeDeserialize.genrictree.NArrayTreeNode;

import java.util.Arrays;
import java.util.Collections;

/**
 * Author: Nitin Gupta
 * Date: 27/09/19
 * Description: http://leetcode.liangjiateng.cn/leetcode/encode-n-ary-tree-to-binary-tree/description
 * 431.Encode N-ary Tree to Binary Tree [Hard]
 * Design an algorithm to encode an N-ary tree into a binary tree and decode the binary tree to get the original N-ary tree. An N-ary tree is a rooted tree in which each node has no more than N children. Similarly, a binary tree is a rooted tree in which each node has no more than 2 children. There is no restriction on how your encode/decode algorithm should work. You just need to ensure that an N-ary tree can be encoded to a binary tree and this binary tree can be decoded to the original N-nary tree structure.
 * <p>
 * For example, you may encode the following 3-ary tree to a binary tree in this way:
 * See link
 * Note that the above is just an example which might or might not work. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
 * <p>
 * https://www.cnblogs.com/grandyang/p/9945345.html
 * http://examradar.com/converting-m-ary-tree-general-tree-binary-tree/
 */
public class EncodeNAryTreeToBinaryTree {

    static NArrayTreeNode getTree() {
        NArrayTreeNode one = new NArrayTreeNode(1);
        NArrayTreeNode two = new NArrayTreeNode(2);
        NArrayTreeNode three = new NArrayTreeNode(3);
        NArrayTreeNode four = new NArrayTreeNode(4);

        NArrayTreeNode five = new NArrayTreeNode(5);
        NArrayTreeNode six = new NArrayTreeNode(6);
        NArrayTreeNode seven = new NArrayTreeNode(7);
        NArrayTreeNode eight = new NArrayTreeNode(8);

        NArrayTreeNode nine = new NArrayTreeNode(9);
        NArrayTreeNode ten = new NArrayTreeNode(10);
        NArrayTreeNode eleven = new NArrayTreeNode(11);
        NArrayTreeNode twelve = new NArrayTreeNode(12);

        one.children = Arrays.asList(two, three, four);
        two.children = Arrays.asList(five, six);
        three.children = Arrays.asList(seven, eight, nine);
        four.children = Arrays.asList(ten, eleven, twelve);
        return one;
    }

    static NArrayTreeNode getTree2() {
        NArrayTreeNode one = new NArrayTreeNode(1);
        NArrayTreeNode two = new NArrayTreeNode(2);
        NArrayTreeNode three = new NArrayTreeNode(3);
        NArrayTreeNode four = new NArrayTreeNode(4);

        NArrayTreeNode five = new NArrayTreeNode(5);
        NArrayTreeNode six = new NArrayTreeNode(6);
        NArrayTreeNode seven = new NArrayTreeNode(7);
        NArrayTreeNode eight = new NArrayTreeNode(8);

        NArrayTreeNode nine = new NArrayTreeNode(9);
        NArrayTreeNode ten = new NArrayTreeNode(10);
        NArrayTreeNode eleven = new NArrayTreeNode(11);
        NArrayTreeNode twelve = new NArrayTreeNode(12);

        one.children = Arrays.asList(two, three, four);
        two.children = Arrays.asList(five, six);
        three.children = Collections.singletonList(seven);
        four.children = Arrays.asList(ten, eleven, twelve);
        ten.children = Arrays.asList(eight, nine);
        return one;
    }

    public static void main(String[] args) {

        test(getTree());
        test(getTree2());

    }

    private static void test(NArrayTreeNode nArrayTreeRoot) {

        System.out.println("\nN-Ary-Tree  :" + GenericPrinter.levelOrder(nArrayTreeRoot));
        final TreeNode binaryTreeRoot = encode(nArrayTreeRoot);
        System.out.println("Encoded     :" + GenericPrinter.preOrder(binaryTreeRoot));
        final NArrayTreeNode decoded = decode(binaryTreeRoot);
        System.out.println("Decoded     :" + GenericPrinter.levelOrder(decoded));

    }


    public static TreeNode encode(NArrayTreeNode root) {
        if (null == root)
            return null;

        final TreeNode binaryTreeRoot = new TreeNode(root.val);

        //if child's are there
        if (!root.children.isEmpty()) {
            //convert first node as left node
            binaryTreeRoot.left = encode(root.children.get(0));
        }

        //convert rest of the node as right node of left sub-tree
        TreeNode current = binaryTreeRoot.left;
        for (int i = 1; i < root.children.size(); i++) {
            current.right = encode(root.children.get(i));
            current = current.right;
        }

        return binaryTreeRoot;
    }


    public static NArrayTreeNode decode(TreeNode root) {
        if (root == null)
            return null;

        NArrayTreeNode nArrayTreeRoot = new NArrayTreeNode(root.val);
        TreeNode current = root.left;
        while (current != null) {
            nArrayTreeRoot.children.add(decode(current));
            current = current.right;
        }


        return nArrayTreeRoot;
    }
}
