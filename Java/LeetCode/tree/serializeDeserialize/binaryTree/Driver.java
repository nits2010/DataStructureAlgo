package Java.LeetCode.tree.serializeDeserialize.binaryTree;

import Java.helpers.GenericPrinter;
import Java.LeetCode.templates.TreeNode;
import Java.LeetCode.tree.serializeDeserialize.ISerializeDeserialize;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 04/04/19
 * Description:
 */


public class Driver {

    public static void main(String[] args) {


        preOrder();
        levelOrder();


    }

    private static TreeNode getTree() {
        TreeNode root = new TreeNode(2);

        root.left = new TreeNode(3);
        root.right = new TreeNode(5);

        root.left.left = new TreeNode(11);
        root.left.right = new TreeNode(4);

        root.right.left = null;
        root.right.right = new TreeNode(9);

        return root;
    }

    private static void preOrder() {
        System.out.println("\n\n Pre order ");
        TreeNode root = getTree();

        System.out.println("Original            : " + GenericPrinter.inOrder(root));
        ISerializeDeserialize preOrder = new SerializeDeSerializeBinaryTree.PreOrder();

        String vv = preOrder.serialize(root);
        System.out.println("Serialized          : " + vv);

        TreeNode r = preOrder.deserialize(vv);
        System.out.println("Deserialize         : " + GenericPrinter.inOrder(r));


    }


    private static void levelOrder() {

        System.out.println("\n\n Level order ");
        TreeNode root = getTree();

        System.out.println("Original            : " + GenericPrinter.inOrder(root));

        ISerializeDeserialize levelOrder = new SerializeDeSerializeBinaryTree.LevelOrder();


        String vv = levelOrder.serialize(root);
        System.out.println("Serialized          : " + vv);

        TreeNode r = levelOrder.deserialize(vv);
        System.out.println("Deserialize         : " + GenericPrinter.inOrder(r));


    }


}
