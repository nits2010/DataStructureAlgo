package Java.LeetCode.serializeDeserialize.binaryTree;

import Java.LeetCode.HelperDatastructure.TreeNode;
import Java.LeetCode.serializeDeserialize.ISerializeDeserialize;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-04
 * Description: https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 * <p>
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file
 * or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 * <p>
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization
 * algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized
 * to the original tree structure.
 * <p>
 * Example:
 * <p>
 * You may serialize the following tree:
 * <p>
 * *     1
 * *    / \
 * *   2   3
 * *      / \
 * *     4   5
 * <p>
 * as "[1,2,3,null,null,4,5]"
 * Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this
 * format, so please be creative and come up with different approaches yourself.
 * <p>
 * Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 *
 * [Facebook]
 */

public class SerializeDeSerializeBinaryTree {

    class PreOrder implements ISerializeDeserialize {

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


    class LevelOrder implements ISerializeDeserialize {

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

            if (null == data || data.isEmpty())
                return null;

            String split[] = data.split(",");

            if (split.length == 1 && split[0].equals(seperator))
                return null;

            TreeNode root = new TreeNode(Integer.parseInt(split[0]));
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            int index = 1;

            while (!queue.isEmpty()) {

                int size = queue.size();

                while (size > 0) {

                    TreeNode node = queue.poll();

                    if (!split[index].equals(seperator)) {
                        node.left = new TreeNode(Integer.parseInt(split[index]));
                        queue.offer(node.left);
                    }
                    index++;
                    if (!split[index].equals(seperator)) {
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


}


