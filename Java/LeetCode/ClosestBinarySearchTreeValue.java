package Java.LeetCode;


import Java.LeetCode.HelperDatastructure.TreeNode;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-20
 * Description:https://medium.com/algorithms-and-leetcode/solving-tree-problems-on-leetcode-d0b7a9b4a7a4
 * 270. Closest Binary Search Tree Value
 * Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
 * Note:
 * Given target value is a floating point.
 * You are guaranteed to have only one unique value in the BST that is closest to the target.
 */
public class ClosestBinarySearchTreeValue {

    public static void main(String args[]) {
        TreeNode bst = getBST();
        System.out.println(closestValue(bst, 4.4));
        System.out.println(closestValue(bst, 3.4));
        System.out.println(closestValue(bst, 3));
        System.out.println(closestValue(bst, 9));
        System.out.println(closestValue(bst, 9.6));
        System.out.println(closestValue(bst, 9.6));
        System.out.println(closestValue(bst, 7));
    }

    private static TreeNode getBST() {

        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(9);

        return root;
    }


    /**
     * Find the closest value based on root value
     * 1. if its same, then this the closest;otherwise  current closest at root value
     * 2. if root is bigger then potential closest value may appear in left side
     * otherwise it will be right side
     * Find out which side gives you closest.
     *
     * Complexity: O(heightOfBST)
     * @param root
     * @param value
     * @return
     */
    private static int closestValue(TreeNode root, double value) {

        if (null == root)
            return Integer.MIN_VALUE;

        if (root.val == value)
            return root.val;

        //This will be our closest value so far
        int closest = root.val;

        int potentialClosest;
        if (root.val > value) {
            //Find the closest on left side
            potentialClosest = closestValue(root.left, value);
        } else {
            //Find the closest on right side
            potentialClosest = closestValue(root.right, value);

        }

        if (Math.abs(closest - value) > Math.abs(potentialClosest - value))
            closest = potentialClosest;
        return closest;
    }

}


