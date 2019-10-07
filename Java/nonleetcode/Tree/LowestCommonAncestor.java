package Java.nonleetcode.Tree;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-09-02
 * Description:
 */
public class LowestCommonAncestor {

    public static TreeNode<Integer> lowestCommonAncestorBST(TreeNode<Integer> root, TreeNode<Integer> alpha, TreeNode<Integer> beta) {
        if (null == root)
            return null;

        //If this element is in between alpha and beta then this is LCA
        if (alpha.getData().compareTo(root.getData()) <= 0 && beta.getData().compareTo(root.getData()) >= 0)
            return root;

        if (alpha.getData().compareTo(root.getData()) < 0)
            return lowestCommonAncestorBST(root.getLeft(), alpha, beta);
        else
            return lowestCommonAncestorBST(root.getRight(), alpha, beta);
    }


    public static TreeNode<Integer> lowestCommonAncestorBT(TreeNode<Integer> root, TreeNode<Integer> alpha, TreeNode<Integer> beta) {
        if (null == root)
            return null;

        if (root == alpha || root == beta)
            return root;

        TreeNode left = lowestCommonAncestorBT(root.getLeft(), alpha, beta);
        TreeNode right = lowestCommonAncestorBT(root.getRight(), alpha, beta);

        if (left != null && right != null)
            return root;

        return (left != null) ? left : right;


    }

}
