package DataStructureAlgo.Java.LeetCode.tree.serializeDeserialize.genrictree.direct;

import DataStructureAlgo.Java.helpers.templates.NArrayTreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: Nitin Gupta
 * Date: 2026-03-14
 * Question Title: Serialize Deserialize N Ary Tree
 * Link: https://leetcode.com/problems/serialize-deserialize-n-ary-tree/
 * Description:
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
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
