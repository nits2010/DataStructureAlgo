package DataStructureAlgo.Java.nonleetcode.Tree;

import java.util.LinkedList;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-26
 * Description:
 */
public class TreeBoundaryTraversal {


    /**
     * Boundary traversal
     *
     * @param root
     * @return
     */
    public static List<TreeNode> boundaryTraversal(TreeNode root) {

        List<TreeNode> traversal = new LinkedList<>();

        if (root == null)
            return traversal;

        traversal.add(root);
        boundaryTraversalLeft(root.getLeft(), traversal);
        boundaryTraversalLeaves(root.getLeft(), traversal);
        boundaryTraversalLeaves(root.getRight(), traversal);
        boundaryTraversalRight(root.getRight(), traversal);
        return traversal;
    }


    public static void boundaryTraversalLeft(TreeNode root, List<TreeNode> traversal) {

        if (root == null)
            return;

        if (root.getLeft() != null) {
            traversal.add(root);
            boundaryTraversalLeft(root.getLeft(), traversal);


        } else if (root.getRight() != null) {
            traversal.add(root);
            boundaryTraversalLeft(root.getRight(), traversal);


        }
    }

    public static void boundaryTraversalLeaves(TreeNode root, List<TreeNode> traversal) {

        if (root == null)
            return;


        boundaryTraversalLeaves(root.getLeft(), traversal);

        if (isLeaf(root)) {
            traversal.add(root);
        }

        boundaryTraversalLeaves(root.getRight(), traversal);


    }

    public static void boundaryTraversalRight(TreeNode root, List<TreeNode> traversal) {

        if (root == null)
            return;

        if (root.getRight() != null) {
            boundaryTraversalRight(root.getRight(), traversal);

            traversal.add(root);
        } else if (root.getLeft() != null) {
            boundaryTraversalRight(root.getLeft(), traversal);

            traversal.add(root);
        }
    }

    public static boolean isLeaf(TreeNode root) {
        if (root == null || (root.getLeft() == null && root.getRight() == null))
            return true;
        return false;
    }


}
