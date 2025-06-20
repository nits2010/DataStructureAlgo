package DataStructureAlgo.Java.helpers;



import DataStructureAlgo.Java.helpers.templates.NArrayTreeNode;
import DataStructureAlgo.Java.helpers.templates.TreeNode;
import DataStructureAlgo.Java.helpers.templates.TreeNodeWithParent;
import DataStructureAlgo.Java.helpers.templates.TreeNodeWithRandom;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date:11/08/24
 * Question Category:
 * Description:
 * <p>
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 *
 *
 * <p>
 * Company Tags
 * -----
 *
 * @Editorial
 */

public class TreeBuilder {

    public static class NaryTree {

        public static NArrayTreeNode buildTreeFromLevelOrder(Integer[] elements) {
            if (elements == null || elements.length == 0 || elements[0] == null)
                return null;

            NArrayTreeNode root = new NArrayTreeNode(elements[0]);
            Queue<NArrayTreeNode> queue = new LinkedList<>();
            queue.add(root);
            int i = 2;
            while(!queue.isEmpty() && i<elements.length) {
                NArrayTreeNode current = queue.poll();
                List<NArrayTreeNode> children = current.children;
                if (children == null)
                    children = new ArrayList<>();

                while(i<elements.length && elements[i]!=null){
                    NArrayTreeNode child = new NArrayTreeNode(elements[i]);
                    children.add(child);
                    queue.add(child);
                    i++;
                }
                i++; // skip the null
            }

            return root;
        }
    }

    private static int preIndex = 0, postIndex;

    public static TreeNode buildTreeFromLevelOrder(Integer[] elements) {
        if (elements == null || elements.length == 0 || elements[0] == null)
            return null;

        TreeNode root = new TreeNode(elements[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int i = 1;
        while (i < elements.length) {
            TreeNode current = queue.poll();

            if (elements[i] != null && current!=null) {
                current.left = new TreeNode(elements[i]);
                queue.add(current.left);
            }
            i++;

            if (i < elements.length && elements[i] != null && current!=null) {
                current.right = new TreeNode(elements[i]);
                queue.add(current.right);
            }
            i++;
        }

        return root;
    }

    public static TreeNodeWithParent buildTreeFromLevelOrderWithParent(Integer[] elements) {
        if (elements == null || elements.length == 0 || elements[0] == null)
            return null;

        TreeNodeWithParent root = new TreeNodeWithParent(elements[0], null);

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int i = 1;
        while (i < elements.length) {
            TreeNode current = queue.poll();

            if (elements[i] != null && current!=null) {
                current.left = new TreeNodeWithParent(elements[i], current);
                queue.add(current.left);
            }
            i++;

            if (i < elements.length && elements[i] != null && current!=null) {
                current.right = new TreeNodeWithParent(elements[i], current);
                queue.add(current.right);
            }
            i++;
        }

        return root;
    }

    private static TreeNode insertNodeAtFirstAvailablePlace(TreeNode root, TreeNode node) {
        if (root == null)
            return node;

        if (root.left == null)
            root.left = node;
        else if (root.right == null)
            root.right = node;
        else
            insertNodeAtFirstAvailablePlace(root.left, node);

        return root;
    }


    public static TreeNode buildTreeFromInorderAndPreorder(Integer[] inorder, Integer[] preorder) {
        if (inorder == null || preorder == null || inorder.length != preorder.length)
            return null;

        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        preIndex=0;
        return buildTreeInPreOrder(preorder, inorderMap, 0, inorder.length - 1);
    }

    private static TreeNode buildTreeInPreOrder(Integer[] preorder, Map<Integer, Integer> inorderMap, int inStart, int inEnd) {
        if (inStart > inEnd)
            return null;

        int rootVal = preorder[preIndex++];
        TreeNode root = new TreeNode(rootVal);

        int inIndex = inorderMap.get(rootVal);

        root.left = buildTreeInPreOrder(preorder, inorderMap, inStart, inIndex - 1);
        root.right = buildTreeInPreOrder(preorder, inorderMap, inIndex + 1, inEnd);

        return root;
    }


    public static TreeNode buildTreeFromInorderAndPostorder(Integer[] inorder, Integer[] postorder) {
        if (inorder == null || postorder == null || inorder.length != postorder.length)
            return null;

        postIndex = postorder.length - 1;
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return buildTreeInPostOrder(postorder, inorderMap, 0, inorder.length - 1);
    }

    private static TreeNode buildTreeInPostOrder(Integer[] postorder, Map<Integer, Integer> inorderMap, int inStart, int inEnd) {
        if (inStart > inEnd)
            return null;

        int rootVal = postorder[postIndex--];
        TreeNode root = new TreeNode(rootVal);

        int inIndex = inorderMap.get(rootVal);

        root.right = buildTreeInPostOrder(postorder, inorderMap, inIndex + 1, inEnd);
        root.left = buildTreeInPostOrder(postorder, inorderMap, inStart, inIndex - 1);

        return root;
    }


    public static TreeNode buildTreeFromPreorderAndPostorder(Integer[] preorder, Integer[] postorder) {
        if (preorder == null || postorder == null || preorder.length != postorder.length)
            return null;

        int preLength = preorder.length;
        int postLength = postorder.length;

        //cache the index for postOrder
        Map<Integer, Integer> postIndex = new HashMap<>();
        for (int i = 0; i < postLength; i++) {
            postIndex.put(postorder[i], i);
        }


        return buildTreePrPostOrder(preorder, 0, preLength - 1, postorder, 0, postIndex);
    }

    private static TreeNode buildTreePrPostOrder(Integer[] preorder, int preStart, int preEnd, int[] postorder, int postStart,
                                                 Map<Integer, Integer> map) {
        //if no elements left
        if (preStart > preEnd)
            return null;

        int currentValue = preorder[preStart];
        TreeNode root = new TreeNode(currentValue);

        //if there is only one element
        if (preStart == preEnd)
            return root;

        //there are multiple elements > 1
        // Find the root of the left child in the preorder array.
        // It's the element immediately after the current root.
        int leftRoot = preorder[preStart + 1];

        //get the nextElement position in postorder
        int postIndex = map.get(leftRoot);

        //all elements from postStart to postIndex are in the left subtree
        int leftSubTreeSize = postIndex - postStart + 1;

        root.left = buildTreePrPostOrder(
                preorder,
                preStart + 1, // move a pointer in pre-order to the next element
                preStart + leftSubTreeSize, // all elements post this root will end when the entire left subtree is traversed, hence its end there
                postorder,
                postStart, // for the postOrder, it starts from postStart
                map);
        root.right = buildTreePrPostOrder(
                preorder,
                preStart + leftSubTreeSize + 1, // Start of right subtree in preorder
                preEnd, // End of right subtree in preorder
                postorder,
                postStart + leftSubTreeSize, // Start of right subtree in postorder
                map);

        return root;
    }

    public static TreeNodeWithRandom createBinaryTreeWithRandom(List<Integer[]> values) {
        if (values == null || values.isEmpty()) {
            return null;
        }

        Map<Integer, TreeNodeWithRandom> nodeMap = new HashMap<>();
        TreeNodeWithRandom root = null;
        for (Integer[] value : values) {
            int ownValue = value[0];
            TreeNodeWithRandom node = new TreeNodeWithRandom(ownValue);
            if(root == null)
                root = node;

            nodeMap.put(ownValue, node);
        }

        for (Integer[] value : values) {
            Integer ownValue = value[0];
            Integer leftValue = value[1];
            Integer rightValue = value[2];
            Integer randomValue = value[3];

            TreeNodeWithRandom node = nodeMap.get(ownValue);
            if (leftValue != null) {
                node.left = nodeMap.get(leftValue);
            }
            if (rightValue != null) {
                node.right = nodeMap.get(rightValue);
            }
            if (randomValue != null) {
                node.random = nodeMap.get(randomValue);
            }
        }

        return root;
    }

    public static boolean isSameTreeWithRandom(TreeNodeWithRandom p, TreeNodeWithRandom q) {
        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;
        if(p == q) //if they are same node
            return false;

        boolean ownEqual = (p.val == q.val);
        boolean leftEqual = isSameTreeWithRandom((TreeNodeWithRandom) p.left, (TreeNodeWithRandom) q.left);
        boolean rightEqual = isSameTreeWithRandom((TreeNodeWithRandom) p.right, (TreeNodeWithRandom) q.right);
        boolean randomEqual;

        if (p.random == null && q.random == null)
            randomEqual = true;
        else if (p.random != null && q.random != null)
            randomEqual = (p.random.val == q.random.val);
        else
            randomEqual = false;


        return ownEqual && leftEqual && rightEqual && randomEqual;
    }

    public static List<Integer[]> buildOriginalInputWithRandom(TreeNodeWithRandom root) {
        List<Integer[]> result = new ArrayList<>();
        Map<TreeNodeWithRandom, Integer> nodeValueMap = new HashMap<>();
        int[] index = {0};

        // First, traverse the tree and store the node values in a map
        traverseAndStoreNodeValues(root, nodeValueMap, index);

        // Then, traverse the tree again and build the original input
        traverseAndBuildOriginalInput(root, nodeValueMap, result);

        return result;
    }

    private static void traverseAndStoreNodeValues(TreeNodeWithRandom node, Map<TreeNodeWithRandom, Integer> nodeValueMap, int[] index) {
        if (node == null) {
            return;
        }

        nodeValueMap.put(node, index[0]++);
        traverseAndStoreNodeValues((TreeNodeWithRandom) node.left, nodeValueMap, index);
        traverseAndStoreNodeValues((TreeNodeWithRandom) node.right, nodeValueMap, index);
        traverseAndStoreNodeValues((TreeNodeWithRandom) node.random, nodeValueMap, index);
    }

    private static void traverseAndBuildOriginalInput(TreeNodeWithRandom node, Map<TreeNodeWithRandom, Integer> nodeValueMap, List<Integer[]> result) {
        if (node == null) {
            return;
        }

        Integer[] value = new Integer[4];
        value[0] = node.val;
        value[1] = node.left != null ? nodeValueMap.get(node.left) : null;
        value[2] = node.right != null ? nodeValueMap.get(node.right) : null;
        value[3] = node.random != null ? nodeValueMap.get(node.random) : null;

        result.add(value);

        traverseAndBuildOriginalInput((TreeNodeWithRandom) node.left, nodeValueMap, result);
        traverseAndBuildOriginalInput((TreeNodeWithRandom) node.right, nodeValueMap, result);
    }

}
