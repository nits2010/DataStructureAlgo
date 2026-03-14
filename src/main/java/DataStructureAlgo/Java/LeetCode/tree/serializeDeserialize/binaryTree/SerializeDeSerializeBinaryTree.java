package DataStructureAlgo.Java.LeetCode.tree.serializeDeserialize.binaryTree;

import  DataStructureAlgo.Java.helpers.templates.TreeNode;
import  DataStructureAlgo.Java.LeetCode.tree.serializeDeserialize.ISerializeDeserialize;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: Nitin Gupta
 * Date: 2026-03-14
 * Question Title: Serialize De Serialize Binary Tree
 * Link: https://leetcode.com/problems/serialize-de-serialize-binary-tree/
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


