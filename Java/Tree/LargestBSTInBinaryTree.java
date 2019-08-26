package Java.Tree;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-26
 * Description:
 */
public class LargestBSTInBinaryTree {

    private final static class LargestBSTHelper {

        Integer size;
        Boolean isBST;
        Integer min;
        Integer max;

        //https://www.geeksforgeeks.org/adobe-interview-experience-for-member-of-technical-staff/
        //Adobe
        Integer sumOfAllNode = 0;

        public LargestBSTHelper(Integer size, Boolean isBST, Integer min, Integer max, int sum) {
            this.size = size;
            this.isBST = isBST;
            this.min = min;
            this.max = max;
            sumOfAllNode = sum;
        }

        public LargestBSTHelper() {
        }
    }


    public static int largestBSTSize(TreeNode<Integer> root) {

        LargestBSTHelper helper = largestBSTSizeUtil(root);

        System.out.println("\n\n largestBSTSize Sum of all node " + helper.sumOfAllNode);

        return helper.size;
    }


    private static LargestBSTHelper largestBSTSizeUtil(TreeNode<Integer> root) {

        //If its null root, then its a bst and size =0; and min would +inf and max would be -inf
        if (null == root)
            return new LargestBSTHelper(0, true, Integer.MAX_VALUE, Integer.MIN_VALUE, 0);

        //Find size of left
        LargestBSTHelper lHelper = largestBSTSizeUtil(root.getLeft());

        //Find size of right
        LargestBSTHelper rHelper = largestBSTSizeUtil(root.getRight());


        //If left and right is a bst, then update the values
        if (lHelper.isBST && rHelper.isBST && root.getData() > lHelper.max && root.getData() < rHelper.min) {

            //this binary tree is bst rooted at root
            boolean isBST = true;

            int max = Math.max(rHelper.max, Math.max(lHelper.max, root.getData()));
            int min = Math.min(rHelper.min, Math.min(lHelper.min, root.getData()));

            int size = lHelper.size + rHelper.size + 1;
            int sumOfAllNode = lHelper.sumOfAllNode + rHelper.sumOfAllNode + root.getData();

            LargestBSTHelper helper = new LargestBSTHelper(size, isBST, min, max, sumOfAllNode);

            return helper;

        }

        return new LargestBSTHelper(Math.max(lHelper.size, rHelper.size), false, Integer.MAX_VALUE, Integer.MIN_VALUE, Math.max(lHelper.sumOfAllNode, rHelper.sumOfAllNode));

    }
}
