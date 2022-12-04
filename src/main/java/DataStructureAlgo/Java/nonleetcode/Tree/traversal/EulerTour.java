package DataStructureAlgo.Java.nonleetcode.Tree.traversal;

import  DataStructureAlgo.Java.nonleetcode.Tree.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-30
 * Description:
 */
public class EulerTour {

    public static  List<Integer> eulerTour(TreeNode<Integer> root) {
        List<Integer> eulerTour = new LinkedList<>();
        eulerTour(root, eulerTour);
        return eulerTour;

    }

    private static  void eulerTour(TreeNode<Integer> root, List<Integer> eulerTour) {
        if (null == root)
            return;

        //At the entry point
        eulerTour.add(root.getData());

        if (null != root.getLeft()) {

            eulerTour(root.getLeft(), eulerTour);

            //at the exit point
            eulerTour.add(root.getData());

        }

        if (null != root.getRight()) {


            eulerTour(root.getRight(), eulerTour);

            //at the exit point
            eulerTour.add(root.getData());

        }
    }
}
