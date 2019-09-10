package Java.LeetCode.serializeDeserialize.binarSearchTree;

import Java.LeetCode.templates.TreeNode;
import Java.LeetCode.serializeDeserialize.ISerializeDeserialize;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-22
 * Description:
 */
public class Driver {
    static SerializeDeSerializeBinarySearchTree serializeDeSerializeBinarySearchTree = new SerializeDeSerializeBinarySearchTree();

    static void inOrder(TreeNode root) {
        if (null == root)
            return;

        inOrder(root.left);
        System.out.print(root.val + " ");
        inOrder(root.right);

    }


    public static void main(String args[]) {


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

        System.out.println("Original Tree Inorder");
        inOrder(root);
        ISerializeDeserialize preOrder = serializeDeSerializeBinarySearchTree.new PreOrder();

        System.out.println("\nSerializing Tree");

        String vv = preOrder.serialize(root);
        System.out.println(" Serialized tree " + vv);

        TreeNode r = preOrder.deserialize(vv);
        System.out.println("Deserialize tree , inorder");
        inOrder(r);


    }


    private static void postOrder() {

        System.out.println("\n\n Level order ");
        TreeNode root = getTree();

        System.out.println("Original Tree Inorder");
        inOrder(root);


        System.out.println("Serializing Tree");


        ISerializeDeserialize levelOrder = serializeDeSerializeBinarySearchTree.new PostOrder();


        String vv = levelOrder.serialize(root);
        System.out.println(" Serialized tree " + vv);

        TreeNode r = levelOrder.deserialize(vv);
        System.out.println("Deserialize tree , inorder");
        inOrder(r);


    }


}
