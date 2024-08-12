package DataStructureAlgo.Java.LeetCode.tree.serializeDeserialize.binarSearchTree;

import  DataStructureAlgo.Java.helpers.GenericPrinter;
import  DataStructureAlgo.Java.helpers.templates.TreeNode;
import  DataStructureAlgo.Java.LeetCode.tree.serializeDeserialize.ISerializeDeserialize;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-22
 * Description:
 */
public class Driver {


    public static void main(String[] args) {


        preOrder();
        postOrder();


    }

    private static TreeNode getTree() {
        TreeNode root = new TreeNode(10);

        root.left = new TreeNode(6);
        root.right = new TreeNode(20);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(8);

        root.right.left = null;
        root.right.right = new TreeNode(30);

        return root;
    }

    private static void preOrder() {
        System.out.println("\n\n Pre order ");
        TreeNode root = getTree();

        System.out.println("Original            : " + GenericPrinter.inOrder(root));
        ISerializeDeserialize preOrder = new SerializeDeSerializeBinarySearchTree.PreOrder();

        String vv = preOrder.serialize(root);
        System.out.println("Serialized          : " + vv);

        TreeNode r = preOrder.deserialize(vv);
        System.out.println("Deserialize         : " + GenericPrinter.inOrder(r));


    }

    private static void postOrder() {
        System.out.println("\n\n Post order ");
        TreeNode root = getTree();

        System.out.println("Original            : " + GenericPrinter.inOrder(root));
        ISerializeDeserialize postOrder = new SerializeDeSerializeBinarySearchTree.PostOrder();

        String vv = postOrder.serialize(root);
        System.out.println("Serialized          : " + vv);

        TreeNode r = postOrder.deserialize(vv);
        System.out.println("Deserialize         : " + GenericPrinter.inOrder(r));


    }


}
