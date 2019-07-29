package Java.companyWise.facebook.serializeDeserializeBinaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-29
 * Description:
 */
public class SerializeDeSerializeLevelOrder implements ISerializeDeserialize {

    @Override
    public String serialize(TreeNode root) {
        if (root == null)
            return seperator;

        StringBuilder serialized = new StringBuilder();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {

            int size = queue.size();

            while (size > 0) {
                TreeNode node = queue.poll();

                if (node == null) {
                    serialized.append(seperator + ",");
                    size--;
                    continue;
                }

                serialized.append(node.val + ",");
                queue.offer(node.left);
                queue.offer(node.right);

                size--;


            }
        }


        return serialized.toString();
    }

    @Override
    public TreeNode deserialize(String data) {

        if(null == data || data.isEmpty())
            return null;

        String split[] = data.split(",");

        if(split.length==1 && split[0].equals(seperator))
            return null;

        TreeNode root = new TreeNode(Integer.parseInt(split[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int index = 1;

        while (!queue.isEmpty()){

            int size = queue.size();

            while (size > 0){

                TreeNode node = queue.poll();

                if(!split[index].equals(seperator)){
                    node.left = new TreeNode(Integer.parseInt(split[index]));
                    queue.offer(node.left);
                }
                index++;
                if(!split[index].equals(seperator)){
                    node.right = new TreeNode(Integer.parseInt(split[index]));
                    queue.offer(node.right);
                }
                index++;
                size--;

            }
        }


        return root;
    }
}
