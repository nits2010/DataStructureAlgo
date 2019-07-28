package Java.companyWise.facebook.serializeDeserializeBinaryTree;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-04
 * Description:
 */

class SerializeDeSerializePreOrder implements ISerializeDeserialize {

    @Override
    public String serialize(TreeNode root) {

        if (null == root)
            return "";


        Helper helper = new Helper();

        serializer(root, helper);
        return helper.str.substring(0, helper.str.length() - 1);
    }

    private void serializer(TreeNode root, Helper helper) {

        if (null == root) {
            helper.str += "$,";
            return;

        }

        helper.str += root.val + ",";
        serializer(root.left, helper);
        serializer(root.right, helper);


    }


    @Override
    public TreeNode deserialize(String encodedBT) {
        if (encodedBT.isEmpty())
            return null;

        Helper index = new Helper();
        index.str = encodedBT;
        index.toDeserialize = index.str.split(",");
        return deserialize(index);

    }

    //PreOrder = 2 3 1 $ $ 10 $ $ 5 $ 12 $ $
    private TreeNode deserialize(Helper index) {


        if (index.toDeserialize[index.index].equals("$"))
            return null;

        TreeNode root = new TreeNode(Integer.parseInt(index.toDeserialize[index.index]));
        index.index++;
        root.left = deserialize(index);
        index.index++;
        root.right = deserialize(index);

        return root;
    }
}


class SerializeDeSerializePreOrderSimple implements ISerializeDeserialize {

    // Encodes a tree to a single string.
    @Override
    public String serialize(TreeNode root) {
        StringBuilder s = new StringBuilder();
        serialize(root, s);
        return s.toString();
    }

    private void serialize(TreeNode root, StringBuilder s) {
        if (root == null) {
            s.append("$,");
            return;
        }
        s.append(root.val).append(",");
        serialize(root.left, s);
        serialize(root.right, s);

    }

    @Override
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] node = data.split(",");
        int[] counter = new int[1];
        return deserialize(node, counter);
    }

    private TreeNode deserialize(String[] node, int[] counter) {
        if (node[counter[0]].equals("$")) {
            counter[0]++;
            return null;
        }
        TreeNode x = new TreeNode(Integer.valueOf(node[counter[0]]));
        counter[0]++;
        x.left = deserialize(node, counter);
        x.right = deserialize(node, counter);
        return x;

    }
}
