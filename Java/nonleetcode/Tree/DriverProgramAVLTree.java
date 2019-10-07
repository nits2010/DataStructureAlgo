package Java.nonleetcode.Tree;

import java.util.LinkedList;
import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 28/12/18
 * Description:
 */
public class DriverProgramAVLTree {


    public static void main(String []args) {

        IBinaryTree avlTree = new AVLTree();
        List<TreeNode> nodes = new LinkedList<>();
        TreeNode root = new AVLTreeNode(10);
        nodes.add(root);
        nodes.add(root = avlTree.insert(20, root));
        nodes.add(root = avlTree.insert(30, root));
        nodes.add(root = avlTree.insert(40, root));
        nodes.add(root = avlTree.insert(50, root));
        nodes.add(root = avlTree.insert(25, root));

        System.out.println(avlTree.inOrder(root));

    }
}
