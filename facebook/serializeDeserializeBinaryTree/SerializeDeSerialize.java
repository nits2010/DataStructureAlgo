package facebook.serializeDeserializeBinaryTree;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 04/04/19
 * Description:
 */


public class SerializeDeSerialize {


    static void inOrder(Node root) {
        if (null == root)
            return;

        inOrder(root.left);
        System.out.print(root.value + " ");
        inOrder(root.right);

    }

    public static void main(String args[]) {


        preOrder();


    }


    private static void preOrder() {

        Node root = new Node(2);

        root.left = new Node(3);
        root.right = new Node(5);

        root.left.left = new Node(11);
        root.left.right = new Node(4);

        root.right.left = null;
        root.right.right = new Node(9);
        inOrder(root);
        System.out.println();


        SerializeDeSerializePreOrder serializeDeSerializePreOrder = new SerializeDeSerializePreOrder();

        String vv = serializeDeSerializePreOrder.serialize(root);
        System.out.println(vv);

        Node r = serializeDeSerializePreOrder.deserialize(vv);
        inOrder(r);


    }


}
