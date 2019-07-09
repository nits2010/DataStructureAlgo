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


    }


    private static void preOrder() {

        TreeNode root = new TreeNode(2);

        root.left = new TreeNode(3);
        root.right = new TreeNode(5);

        root.left.left = new TreeNode(11);
        root.left.right = new TreeNode(4);

        root.right.left = null;
        root.right.right = new TreeNode(9);
        inOrder(root);
        System.out.println();


        SerializeDeSerializePreOrder serializeDeSerializePreOrder = new SerializeDeSerializePreOrder();

        String vv = serializeDeSerializePreOrder.serialize(root);
        System.out.println(vv);

        TreeNode r = serializeDeSerializePreOrder.deserialize(vv);
        inOrder(r);


    }


}
