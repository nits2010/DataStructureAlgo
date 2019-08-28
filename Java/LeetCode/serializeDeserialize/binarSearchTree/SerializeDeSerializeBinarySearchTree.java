package Java.LeetCode.serializeDeserialize.binarSearchTree;

import Java.LeetCode.HelperDatastructure.TreeNode;
import Java.LeetCode.serializeDeserialize.ISerializeDeserialize;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-22
 * Description:https://leetcode.com/problems/serialize-and-deserialize-bst/
 * <p>
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a
 * file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer
 * environment.
 * <p>
 * Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization
 * algorithm should work. You just need to ensure that a binary search tree can be serialized to a string and this string can be
 * deserialized to the original tree structure.
 * <p>
 * The encoded string should be as compact as possible.
 * <p>
 * Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 * <p>
 * {@link Java.LeetCode.serializeDeserialize.binaryTree.SerializeDeSerializeBinaryTree}
 * <p>
 * The same solution can be used to make it work but the important key here is "Binary search Tree" because of that we can reduce the
 * packets which gets transfer over the network by reducing the size of serialized string.
 */
public class SerializeDeSerializeBinarySearchTree {


    /**
     * *           10
     * *        6     20
     * *     4    8     30
     * *
     * * Inorder: 4,6,8,10,20,30
     * *
     * O(n^2)
     * Runtime: 5 ms, faster than 92.26% of Java online submissions for Serialize and Deserialize BST.
     * Memory Usage: 38.6 MB, less than 100.00% of Java online submissions for Serialize and Deserialize BST.
     */

    class PreOrder implements ISerializeDeserialize {

        @Override
        public String serialize(TreeNode root) {

            if (null == root)
                return "";

            StringBuilder serialized = new StringBuilder();

            serialized(root, serialized);

            return serialized.toString();
        }

        /**
         * *           10
         * *        6     20
         * *     4    8     30
         * *
         * * Serialized preOrder: 10,6,4,8,20,30
         * *
         * *
         */
        private void serialized(TreeNode root, StringBuilder serialized) {

            if (root == null) {
                return;
            }
            serialized.append(root.val + ",");
            serialized(root.left, serialized);
            serialized(root.right, serialized);
        }

        @Override
        public TreeNode deserialize(String data) {
            if (data.isEmpty())
                return null;

            String[] preOrder = data.split("\\,");

            return reconstruct(preOrder, new int[]{0}, Integer.MAX_VALUE);

        }

        /**
         * *           10
         * *        6     20
         * *     4    8     30
         * *
         * * Serialized preOrder: [10,6,4,8,20,30 ]
         * * index = 0 ; Max = BIG {10 < BIG}
         * *            10
         * * LEFT -> index = 1 ; Max = 10  { 6 < 10 }
         * *           10
         * *         6
         * * LEFT -> index = 2 ; Max = 6  { 4  < 10 }
         * *           10
         * *         6
         * *      4
         * * LEFT -> index = 3 ; Max = 4  { 8  >= 10 }
         * *           10
         * *         6
         * *      4
         * *  null
         * * Right -> index = 3 ; Max = 6  { 8  >= 10 }
         * *           10
         * *         6
         * *      4
         * *  null  null
         * * Right -> index = 3 ; Max = 10  { 8  < 10 }
         * *           10
         * *         6
         * *      4    8
         * *  null  null
         * * RIGHT -> index = 4 ; Max = BIG  { 20  < BIG }
         * *           10
         * *         6    20
         * *      4    8
         * *  null  null
         * * LEFT -> index = 5 ; Max = 20  { 30  > 20 }
         * *           10
         * *         6    20
         * *      4    8 N
         * *  null  null
         * <p>
         * * * Right -> index = 5 ; Max = BIG  { 30  < BIG }
         * *           10
         * *         6    20
         * *      4    8 N   30
         * *  null  null
         * * * LEft -> index = 6 ; Max = BIG  { out of index }
         * <p>
         * *           10
         * *         6    20
         * *      4    8   30
         */
        private TreeNode reconstruct(String[] preOrder, int[] index, int maxValue) {
            /**
             * 1. When we left with no item to push in our tree; index[0] >= preOrder.length
             * 2. When this node is the rightmost node
             */
            if (index[0] >= preOrder.length || Integer.parseInt(preOrder[index[0]]) > maxValue)
                return null;

            int key = Integer.parseInt(preOrder[index[0]++]);
            TreeNode root = new TreeNode(key);

            root.left = reconstruct(preOrder, index, root.val);
            root.right = reconstruct(preOrder, index, maxValue);

            return root;
        }


    }

    /**
     * * O(n^2)
     */
    class PostOrder implements ISerializeDeserialize {

        @Override
        public String serialize(TreeNode root) {

            if (null == root)
                return "";

            StringBuilder serialized = new StringBuilder();

            serialized(root, serialized);

            return serialized.toString();
        }


        private void serialized(TreeNode root, StringBuilder serialized) {

            if (root == null) {
                return;
            }
            serialized(root.left, serialized);
            serialized(root.right, serialized);
            serialized.append(root.val + ",");
        }

        @Override
        public TreeNode deserialize(String data) {
            if (data.isEmpty())
                return null;

            String[] postOrder = data.split("\\,");

            return reconstruct(postOrder, new int[]{postOrder.length - 1}, Integer.MIN_VALUE);

        }


        private TreeNode reconstruct(String[] postOrder, int[] index, int minValue) {
            /**
             * 1. When we left with no item to push in our tree; index[0] >= preOrder.length
             * 2. When this node is the leftMost node
             */
            if (index[0] < 0 || Integer.parseInt(postOrder[index[0]]) < minValue)
                return null;

            int key = Integer.parseInt(postOrder[index[0]--]);
            TreeNode root = new TreeNode(key);
            root.right = reconstruct(postOrder, index, root.val);
            root.left = reconstruct(postOrder, index, minValue);


            return root;
        }


    }

}
