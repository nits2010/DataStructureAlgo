package Java.LeetCode.tree.serializeDeserialize.binaryTree;

import Java.LeetCode.templates.TreeNode;
import Java.LeetCode.tree.serializeDeserialize.ISerializeDeserialize;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: Nitin Gupta
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
 * <p>
 * [Facebook]
 */

public class SerializeDeSerializeBinaryTree {

    static class PreOrder implements ISerializeDeserialize {

        // Encodes a tree to a single string.
        @Override
        public String serialize(TreeNode root) {
            StringBuilder s = new StringBuilder();
            serialize(root, s);
            return s.toString();
        }

        /**
         * Append $ for null node
         * Append value for non-null node
         * Append "," as separator
         *
         * @param root
         * @param s
         */
        private void serialize(TreeNode root, StringBuilder s) {
            if (root == null) {
                s.append(NULL_INDICATOR).append(SEPARATOR);
                return;
            }
            s.append(root.val).append(SEPARATOR);
            serialize(root.left, s);
            serialize(root.right, s);

        }

        /**
         * Algo:
         * 1. Separate nodes
         *
         * @param data
         * @return
         */
        public TreeNode deserialize(String data) {
            String[] node = data.split(SEPARATOR);
            int[] counter = new int[1];
            return deserialize(node, counter);
        }

        private TreeNode deserialize(String[] node, int[] counter) {
            if (node[counter[0]].equals(NULL_INDICATOR)) {
                counter[0]++;
                return null;
            }
            TreeNode x = new TreeNode(Integer.parseInt(node[counter[0]]));
            counter[0]++;
            x.left = deserialize(node, counter);
            x.right = deserialize(node, counter);
            return x;

        }
    }


    static class LevelOrder implements ISerializeDeserialize {

        @Override
        public String serialize(TreeNode root) {
            if (root == null)
                return NULL_INDICATOR;

            StringBuilder serialized = new StringBuilder();

            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);

            while (!queue.isEmpty()) {

                int size = queue.size();

                while (size > 0) {
                    TreeNode node = queue.poll();

                    if (node == null) {
                        serialized.append(NULL_INDICATOR).append(SEPARATOR);
                        size--;
                        continue;
                    }

                    serialized.append(node.val).append(SEPARATOR);
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

            String[] split = data.split(SEPARATOR);

            if (split.length == 1 && split[0].equals(NULL_INDICATOR))
                return null;

            TreeNode root = new TreeNode(Integer.parseInt(split[0]));
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            int index = 1;

            while (!queue.isEmpty()) {

                int size = queue.size();

                while (size > 0 && !queue.isEmpty()) {

                    TreeNode node = queue.poll();

                    //if not null node
                    if (!split[index].equals(NULL_INDICATOR)) {
                        node.left = new TreeNode(Integer.parseInt(split[index]));
                        queue.offer(node.left);
                    }

                    index++;

                    //if not null node
                    if (!split[index].equals(NULL_INDICATOR)) {
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


