package DataStructureAlgo.Java.LeetCode.tree;

import  DataStructureAlgo.Java.helpers.GenericPrinter;
import  DataStructureAlgo.Java.LeetCode.templates.TreeNode;
import  DataStructureAlgo.Java.nonleetcode.Tree.FlipTreeUpSideDown;

/**
 * Author: Nitin Gupta
 * Date: 2019-09-09
 * Description:
 * https://leetcode.com/problems/binary-tree-upside-down
 * http://leetcode.liangjiateng.cn/leetcode/binary-tree-upside-down/description
 * <p>
 * 156.Binary Tree Upside Down
 * <p>
 * <p>
 * Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node)
 * or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.
 * In the flip operation, left most node becomes the root of flipped tree and its parent become its right child
 * and the right sibling become its left child and same should be done for all left most nodes recursively.
 * Example:
 * <p>
 * Input: [1,2,3,4,5]
 * <p>
 * *     1
 * *    / \
 * *   2   3
 * *  / \
 * * 4   5
 * <p>
 * Output: return the root of the binary tree [4,5,2,#,#,3,1]
 * <p>
 * *    4
 * *   / \
 * *  5   2
 * *     / \
 * *    3   1
 * Clarification:
 * <p>
 * Confused what [4,5,2,#,#,3,1] means? Read more below on how binary tree is serialized on OJ.
 * <p>
 * The serialization of a binary tree follows a level order traversal, where '#' signifies a path terminator where no node exists below.
 * <p>
 * Here's an example:
 * <p>
 * *    1
 * *   / \
 * *  2   3
 * *     /
 * *    4
 * *     \
 * *      5
 * The above binary tree is serialized as [1,2,3,#,#,4,#,#,5].
 * <p>
 * http://tiancao.me/Leetcode-Unlocked/LeetCode%20Locked/c1.13.html
 * * https://www.geeksforgeeks.org/flip-binary-tree/
 * * https://medium.com/@jimdaosui/binary-tree-upside-down-77af203c79af
 * {@link FlipTreeUpSideDown}
 */
public class BinaryTreeUpsideDown {

    public static void main(String[] args) {
        test(new Integer[]{1, 2, 3, 4, 5}, new Integer[]{4, 5, 2, 3, 1});
    }

    private static void test(Integer[] tree, Integer[] expected) {
        System.out.println("\n Given tree " + GenericPrinter.toString(tree) + " expected :" + GenericPrinter.toString(expected));

        System.out.println("Recursive :");
        test(new BinaryTreeUpsideDownRecursive(), TreeBuilder.arrayToTree(tree));

        System.out.println("Iterative :");
        test(new BinaryTreeUpsideDownIterative(), TreeBuilder.arrayToTree(tree));
    }


    private static void test(IBinaryTreeUpsideDown impl, TreeNode root) {
        final TreeNode flipped = impl.upsideDownBinaryTree(root);
        System.out.println(" Obtained :" + GenericPrinter.preOrder(flipped));

    }
}


interface IBinaryTreeUpsideDown {
    TreeNode upsideDownBinaryTree(TreeNode root);
}

/**
 * Observation:
 * *     1
 * *    / \
 * *   2   3
 * *  / \
 * * 4   5
 * <p>
 * Result to =>
 * *    4
 * *   / \
 * *  5   2
 * *     / \
 * *    3   1
 * <p>
 * 1. The left most node become root
 * 2. Parent become right node of left node
 * 3. right node become left node of left.
 * <p>
 * As above observation, we'll perform the task. In order to return new root which is apparently leftmost node.
 * <p>
 * Algorithm:
 * 1. Go Left of current root
 * 2. if there is no more left sub-tree.
 * 3. Then transform this sub-tree to up-side down tree
 */
class BinaryTreeUpsideDownRecursive implements IBinaryTreeUpsideDown {

    public TreeNode upsideDownBinaryTree(TreeNode root) {

        //2. if there is no more left sub-tree.
        if (root == null || root.left == null)
            return root;


        //1. Go Left of current root
        TreeNode newRoot = upsideDownBinaryTree(root.left);

        //3. Then transform this sub-tree to up-side down tree
        TreeNode left = root.left;
        TreeNode rightSibling = root.right;

        //2. Parent become right node of left node
        left.right = root;
        left.left = rightSibling;

        root.left = null;
        root.right = null;


        //1. The left most node become root
        return newRoot;
    }
}

/**
 * Iterative version of above
 */
class BinaryTreeUpsideDownIterative implements IBinaryTreeUpsideDown {

    public TreeNode upsideDownBinaryTree(TreeNode root) {

        //2. if there is no more left sub-tree.
        if (root == null || root.left == null)
            return root;


        TreeNode rightSibling = null, left = null, parent = null;

        TreeNode current = root;

        while (current != null) {

            //cache left
            left = current.left;

            //3. right node become left node of left.
            current.left = rightSibling;

            //cache right
            rightSibling = current.right;

            //2. Parent become right node of left node
            current.right = parent;

            parent = current;
            current = left;


        }

        return parent;


    }
}
