package DataStructureAlgo.Java.LeetCode.tree.serializeDeserialize.genrictree.direct;

import DataStructureAlgo.Java.helpers.templates.NArrayTreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: Nitin Gupta
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
 * <p>
 * [Amazon]
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

        final String levelSep = NULL_INDICATOR + SEPARATOR;
        Queue<NArrayTreeNode> queue = new LinkedList<>();

        StringBuilder serialize = new StringBuilder();


        queue.offer(root);
        serialize.append(root.val).append(SEPARATOR).append(levelSep);//Defines the Level end

        while (!queue.isEmpty()) {

            NArrayTreeNode treeNode = queue.poll();

            //append child's of this level
            if (treeNode.children != null) {
                for (NArrayTreeNode child : treeNode.children) {
                    serialize.append(child.val).append(SEPARATOR);
                    queue.offer(child);
                }
                //Defines the Level end
                serialize.append(levelSep);
            }
        }
        serialize.setLength(serialize.length() - 1); //Remove last ','
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

        String[] content = data.split(SEPARATOR);
        root.val = Integer.parseInt(content[index++]);

        Queue<NArrayTreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty() && index < content.length - 1) {

            NArrayTreeNode node = queue.poll();
            index++; //Skip line separator

            //build child for this node
            while (!content[index].equals(NULL_INDICATOR)) {
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
