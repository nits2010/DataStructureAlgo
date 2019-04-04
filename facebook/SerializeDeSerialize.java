package facebook;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 04/04/19
 * Description:
 */
public class SerializeDeSerialize {

    static class Node {
        int value;
        Node left = null, right = null;

        public Node(int value) {
            this.value = value;
        }
    }

    static class Helper {
        String str = "";
        int index = 0;
        String[] toDeserialize = {};
    }

    static void inOrder(Node root) {
        if (null == root)
            return;

        inOrder(root.left);
        System.out.print(root.value + " ");
        inOrder(root.right);

    }


    static String serialize(Node root) {

        if (null == root)
            return "";


        Helper helper = new Helper();

        serializer(root, helper);
        return helper.str.substring(0, helper.str.length() - 1);
    }

    static void serializer(Node root, Helper helper) {

        if (null == root) {
            helper.str += "$,";
            return;

        }

        helper.str += String.valueOf(root.value) + ",";
        serializer(root.left, helper);
        serializer(root.right, helper);


    }


    static Node deserialize(String encodedBT) {
        if (encodedBT.isEmpty())
            return null;

        Node root = null;
        Helper index = new Helper();
        index.str = encodedBT;
        index.toDeserialize = index.str.split(",");
        return deserialize(index);

    }

    //PreOrder = 2 3 1 $ $ 10 $ $ 5 $ 12 $ $
    static Node deserialize(Helper index) {


        if (index.toDeserialize[index.index].equals("$"))
            return null;

        Node root = new Node(Integer.parseInt(index.toDeserialize[index.index]));
        index.index++;
        root.left = deserialize(index);
        index.index++;
        root.right = deserialize(index);

        return root;
    }

    public static void main(String args[]) {

        Node root = new Node(2);

        root.left = new Node(3);
        root.right = new Node(5);

        root.left.left = new Node(11);
        root.left.right = new Node(4);

        root.right.left = null;
        root.right.right = new Node(9);
        inOrder(root);
        System.out.println();

        String vv = serialize(root);
        System.out.println(vv);

        Node r = deserialize(vv);
        inOrder(r);


    }
}
