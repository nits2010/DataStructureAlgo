package Java.LeetCode.serializeDeserialize.genrictree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-22
 * Description:https://leetcode.com/problems/serialize-and-deserialize-n-ary-tree
 * http://leetcode.liangjiateng.cn/leetcode/serialize-and-deserialize-n-ary-tree/description
 * https://www.geeksforgeeks.org/serialize-deserialize-n-ary-tree/
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 * <p>
 * Design an algorithm to serialize and deserialize an N-ary tree. An N-ary tree is a rooted tree in which each node has no more than N children. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that an N-ary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 * <p>
 * For example, you may serialize the following 3-ary tree
 * as [1 [3[5 6] 2 4]]. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
 * Note:
 * <p>
 * N is in the range of [1, 1000]
 * Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 */
public class SerializeDeserializeNAryTree implements ISerializeDeserializeNArrayTree {

    /**
     * *                             10
     * *
     * *                 2           20              12
     * *
     * *         1           7    8      9       21  24  25
     * *
     * * Serialized: 10,$,2,20,12,$,1,7, $, 8, 9,  $, 21, 24 , 25, $
     * *
     *
     * @param root
     * @return
     */
    @Override
    public String serialize(NArrayTreeNode root) {
        if (root == null)
            return "";

        final String levelSep = seperator + ",";
        Queue<NArrayTreeNode> queue = new LinkedList<>();

        StringBuilder serialize = new StringBuilder();


        queue.offer(root);
        serialize.append(root.val);
        serialize.append("," + levelSep); //Defines the Level end

        while (!queue.isEmpty()) {

            NArrayTreeNode treeNode = queue.poll();

            if (treeNode.children != null) {
                for (NArrayTreeNode child : treeNode.children) {
                    serialize.append(child.val);
                    serialize.append(",");
                    queue.offer(child);
                }
                serialize.append(levelSep); //Defines the Level end
            }
        }
        serialize.setLength(serialize.length() - 1); //Remove last ,

        return serialize.toString();
    }

    /**
     * *                             10
     * *
     * *                 2           20              12
     * *
     * *         1           7    8      9       21  24  25
     * *
     * * Serialized: 10,$,2,20,12,$,1,7, $, 8, 9,  $, 21, 24 , 25, $
     * *
     *
     * @return
     */
    @Override
    public NArrayTreeNode deserialize(String data) {
        if (data == null || data.isEmpty())
            return null;

        NArrayTreeNode root = new NArrayTreeNode();

        int index = 0;

        String[] content = data.split("\\,");
        root.val = Integer.parseInt(content[index++]);

        Queue<NArrayTreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty() && index < content.length-1) {

            NArrayTreeNode node = queue.poll();
            index++;

            while (!content[index].equals(seperator)) {
                if (node.children == null)
                    node.children = new ArrayList<>();

                NArrayTreeNode child = new NArrayTreeNode();
                child.val = Integer.parseInt(content[index++]);
                node.children.add(child);

                queue.offer(child);
            }


        }

        return root;
    }
}
