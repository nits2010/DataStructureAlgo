package Java.companyWise.facebook.serializeDeserializeBinaryTree;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 04/04/19
 * Description:
 */


public class SerializeDeSerialize {


    static void inOrder(TreeNode root) {
        if (null == root)
            return;

        inOrder(root.left);
        System.out.print(root.val + " ");
        inOrder(root.right);

    }




    public static void main(String args[]) {


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

        System.out.println("Original Tree Inorder");
        inOrder(root);


        System.out.println("\nSerializing Tree");
        ISerializeDeserialize serializeDeserialize = new SerializeDeSerializePreOrder();

        String vv = serializeDeserialize.serialize(root);
        System.out.println(" Serialized tree " + vv);

        TreeNode r = serializeDeserialize.deserialize(vv);
        System.out.println("Deserialize tree , inorder");
        inOrder(r);


    }


    private static void levelOrder() {

        System.out.println("\n\n Level order ");
        TreeNode root = getTree();

        System.out.println("Original Tree Inorder");
        inOrder(root);


        System.out.println("Serializing Tree");
        ISerializeDeserialize serializeDeserialize = new SerializeDeSerializeLevelOrder();

        String vv = serializeDeserialize.serialize(root);
        System.out.println(" Serialized tree " + vv);

        TreeNode r = serializeDeserialize.deserialize(vv);
        System.out.println("Deserialize tree , inorder");
        inOrder(r);


    }


}
