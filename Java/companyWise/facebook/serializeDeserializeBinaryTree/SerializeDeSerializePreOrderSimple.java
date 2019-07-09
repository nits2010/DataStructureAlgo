package Java.companyWise.facebook.serializeDeserializeBinaryTree;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-09
 * Description:
 */
public class SerializeDeSerializePreOrderSimple {

    // Encodes a tree to a single string.

    public String serialize(TreeNode root) {
        StringBuilder s = new StringBuilder();
        serialize(root, s);
        return s.toString();
    }

    public void serialize(TreeNode root, StringBuilder s) {
        if (root == null) {
            s.append("$,");
            return;
        }
        s.append(root.val).append(",");
        serialize(root.left, s);
        serialize(root.right, s);

    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] node = data.split(",");
        int[] counter = new int[1];
        return helper1(node, counter);
    }

    public TreeNode helper1(String[] node, int[] counter) {
        if (node[counter[0]].equals("$")) {
            counter[0]++;
            return null;
        }
        TreeNode x = new TreeNode(Integer.valueOf(node[counter[0]]));
        counter[0]++;
        x.left = helper1(node, counter);
        x.right = helper1(node, counter);
        return x;

    }
}
