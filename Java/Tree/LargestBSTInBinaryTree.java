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

        public LargestBSTHelper(Integer size, Boolean isBST, Integer min, Integer max) {
            this.size = size;
            this.isBST = isBST;
            this.min = min;
            this.max = max;
        }

        public LargestBSTHelper() {
        }
    }


    public static int largestBSTSize(TreeNode<Integer> root) {

        LargestBSTHelper helper = largestBSTSizeUtil(root);

        return helper.size;
    }


    private static LargestBSTHelper largestBSTSizeUtil(TreeNode<Integer> root) {

        //If its null root, then its a bst and size =0
        if (null == root)
            return new LargestBSTHelper(0, true, Integer.MIN_VALUE, Integer.MAX_VALUE);


        //if its a single node with no left and right child then its a bst of size 1 and min & max would be itself
        if (null == root.getLeft() && null == root.getRight())
            return new LargestBSTHelper(1, true, root.getData(), root.getData());

        //Find size of left
        LargestBSTHelper lHelper = largestBSTSizeUtil(root.getLeft());

        //Find size of right
        LargestBSTHelper rHelper = largestBSTSizeUtil(root.getRight());

        LargestBSTHelper helper = new LargestBSTHelper();

        //If left and right is a bst, then update the values
        if (lHelper.isBST && rHelper.isBST) {

            if ((lHelper.size == 0 || (root.getData() > lHelper.max)) && (rHelper.size == 0 || root.getData() < rHelper.min)) {

                //this binary tree is bst rooted at root
                helper.isBST = true;

                helper.max = root.getData();
                helper.min = root.getData();

                if (lHelper.size != 0) {
                    helper.max = Math.max(lHelper.max, helper.max);
                    helper.min = Math.min(lHelper.min, helper.min);
                }


                if (rHelper.size != 0) {
                    helper.max = Math.max(rHelper.max, helper.max);
                    helper.min = Math.min(rHelper.min, helper.min);
                }

                helper.size = lHelper.size + rHelper.size + 1;

                return helper;
            }
        }

        //This bt is not bst rooted at root
        helper.isBST = false;

        //but there could be a bst already exist on left or right, return the max size
        helper.size = Math.max(lHelper.size, rHelper.size);

        return helper;
    }
}
