package Java.LeetCode.tree.completeBinaryTree;

import Java.LeetCode.templates.TreeNode;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-13
 * Description: https://leetcode.com/problems/count-complete-tree-nodes/
 * <p>
 * Given a complete binary tree, count the number of nodes.
 * <p>
 * Note:
 * <p>
 * Definition of a complete binary tree from Wikipedia:
 * In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 * <p>
 * Example:
 * <p>
 * Input:
 * *     1
 * *    / \
 * *   2   3
 * *  / \  /
 * * 4  5 6
 * <p>
 * Output: 6
 */
public class CountNodesInCompleteBinaryTree {


    public static void main(String[] args) {
        test(Helper.getTree1(), 6);
        test(Helper.getTree2(), 7);

    }

    private static void test(TreeNode root, int expected) {
        CountNodesInCompleteBinaryTreeUsigHeight height = new CountNodesInCompleteBinaryTreeUsigHeight();
        System.out.println(" Obtained :" + height.countNodes(root) + " expected:" + expected);
    }
}

/**
 * https://leetcode.com/problems/count-complete-tree-nodes/discuss/357767/Java-or-Full-explanation-or-100-memory-100-speed
 * We can apply the same logic of calculating the height of binary tree with slight changes.
 * Recall: Height of binary tree is Max (height of left sub-tree , height of right sub-tree ) + 1.
 * <p>
 * As we know, this is complete binary tree, which means the height of left sub-tree >= height of right sub-tree. As complete binary tree is left oriented.
 * <p>
 * case 1: When Height(Left sub-tree) = Height(right sub-tree)
 * This only possible when this tree is full binary tree.
 * *     1
 * *    / \
 * *   2   3
 * *  / \  /\
 * * 4  5 6 7
 * <p>
 * At root 1, the height of left  = height of right.
 * <p>
 * so number of nodes in full binary tree is 2^height-1; For above 2^3-1 = 7
 * <p>
 * case 2: When Height(Left sub-tree) > Height(right sub-tree)
 * <p>
 * *     1
 * *    / \
 * *   2   3
 * *  / \  /
 * * 4  5 6
 * <p>
 * Height of left = 3 while height of right is = 2
 * in this case, the total number of nodes is depends on its sub-tree. So if we know how many number of nodes are there in left and right then total would be
 * count(left) + count(right) + 1 [ for root ]
 * <p>
 * Base case:
 * When root is leaf, its height is always 1 so tha number of nodes=1
 * <p>
 * <p>
 * Hence
 * Algorithm:
 * 1. find height of left sub-tree [Lh] and right sub-tree [Rh]
 * 2. if Lh == Rh then total nodes at this root is 2^height-1
 * 3. if Lh > rH then total nodes at this root is 1 + count(left) + count(right)
 * <p>
 * Example:
 * *     1
 * *    / \
 * *   2   3
 * *  / \  /
 * * 4  5 6
 * <p>
 * Step 1: Root = 1; Lh = 3 and Rh = 2 (3!=2)
 * step 2.a : Root = 2, Lh = 2 and Rh = 2 hence number of nodes rooted at this root is 2^2 -1 = 3
 * step 2.b; Root = 3; Lh = 2 and Rh = 0 (2!=0
 * Step 2.b.a: Root=6 nodes at this is 1 [ base case ]
 * step 2.b.b: Root = null, nodes is 0
 * Hence
 * step 2.b: 1 + 1 + 0 = 2
 * And
 * Step 1: 3 + 2 + 1 = 6 output
 * <p>
 * <p>
 * Complexity:
 * Height  = O(log(n))
 * count nodes = O(log(n))
 * <p>
 * Total: O((log(n))^2) => O(h^2)
 * <p>
 * <p>
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Count Complete Tree Nodes.
 * Memory Usage: 37.4 MB, less than 100.00% of Java online submissions for Count Complete Tree Nodes.
 */
class CountNodesInCompleteBinaryTreeUsigHeight {


    public int countNodes(TreeNode root) {

        if (root == null)
            return 0;


        int leftHeight = leftHeight(root);
        int rightHeight = rightHeight(root);

        /**
         * case 1: When Height(Left sub-tree) = Height(right sub-tree)
         * 2^h - 1
         */
        if (leftHeight == rightHeight)
            return (1 << leftHeight) - 1;
        else
            return 1 + countNodes(root.left) + countNodes(root.right);


    }


    private int leftHeight(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + leftHeight(root.left);
    }

    private int rightHeight(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + rightHeight(root.right);
    }


    /**
     * Simply find the size of tree O(n)
     *
     * @param root
     * @return
     */
    public int countNodesSlow(TreeNode root) {
        if (root == null)
            return (0);
        return (1 + countNodes(root.left) + countNodes(root.right));
    }


}