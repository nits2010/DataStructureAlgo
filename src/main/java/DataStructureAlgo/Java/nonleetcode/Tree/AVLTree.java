package DataStructureAlgo.Java.nonleetcode.Tree;

/**
 * Author: Nitin Gupta
 * Date: 28/12/18
 * Description:
 */
public class AVLTree extends BinarySearchTree {

    @Override
    public TreeNode<Integer> insert(Integer data, TreeNode<Integer> root) {

        if (null == root)
            return new AVLTreeNode<>(data);

        if (root.getData() > data) {
            root.setLeft(insert(data, root.getLeft()));
        } else if (root.getData() < data) {
            root.setRight(insert(data, root.getRight()));
        } else  //Duplicate key not allowed
            return root;

        AVLTreeNode avlRoot = ((AVLTreeNode) root);


        //Adjust the current node height
        int leftHeight = avlRoot.getHeight((AVLTreeNode) avlRoot.getLeft());
        int rightHeight = avlRoot.getHeight((AVLTreeNode) avlRoot.getRight());
        avlRoot.setHeight(1 + Math.max(leftHeight, rightHeight));


        //Check the balancing
        int balance = leftHeight - rightHeight;


        //if tree is unbalanced
        if (balance > 1) {

            //Either Left Left case or Left right Case

            //Left left case; key < root.left.key
            if (data.compareTo((Integer) avlRoot.getLeft().getData()) < 0) {
                return leftLeftRotate(avlRoot);
            } else {
                //Left Right case; key > root.left.key
                return leftRightRotate(avlRoot);
            }

        } else if (balance < -1) {

            //Either Right Right case or Right Left Case

            //right right case; key > root.left.key
            if (data.compareTo((Integer) avlRoot.getRight().getData()) > 0) {
                return rightRightRotate(avlRoot);
            } else {
                //Right Left case; key < root.left.key
                return rightLeftRotate(avlRoot);
            }
        }


        return root;
    }

    @Override
    public TreeNode<Integer> delete(Integer data, TreeNode<Integer> root) {
        //Not implemented yet

        return null;
    }

    //Left Left Rotate at W
    private TreeNode leftLeftRotate(TreeNode w) {
        TreeNode left = w.getLeft();
        w.setLeft(left.getRight());
        left.setRight(w);


        updateHeight(w);
        updateHeight(left);
        return left;

    }


    //right right Rotate at W
    private TreeNode rightRightRotate(TreeNode w) {
        TreeNode right = w.getRight();
        w.setRight(right.getLeft());
        right.setLeft(w);

        updateHeight(w);
        updateHeight(right);
        return right;

    }

    //Left right rotate
    private TreeNode leftRightRotate(AVLTreeNode w) {
        w.setLeft(leftLeftRotate(w.getLeft()));
        return rightRightRotate(w);
    }

    //right left rotate
    private TreeNode rightLeftRotate(AVLTreeNode w) {
        w.setRight(rightRightRotate(w.getRight()));
        return leftLeftRotate(w);
    }


    private void updateHeight(TreeNode node) {
        ((AVLTreeNode) node).setHeight(
                1 + Math.max(
                        ((AVLTreeNode) node).getHeight((AVLTreeNode) node.getLeft()),
                        ((AVLTreeNode) node).getHeight((AVLTreeNode) node.getRight())
                ));


    }
}
