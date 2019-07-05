package facebook.serializeDeserializeBinaryTree;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-04
 * Description:
 */

class SerializeDeSerializePreOrder {

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

        helper.str += root.value + ",";
        serializer(root.left, helper);
        serializer(root.right, helper);


    }


    static Node deserialize(String encodedBT) {
        if (encodedBT.isEmpty())
            return null;

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
}
