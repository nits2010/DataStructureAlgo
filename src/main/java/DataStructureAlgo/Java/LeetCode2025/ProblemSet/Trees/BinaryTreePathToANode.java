package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees;

import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.TreeBuilder;
import DataStructureAlgo.Java.helpers.templates.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: Nitin Gupta
 * Date:16/08/24
 * Question Category:
 * Description:
 * <p>
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 *
 *
 * <p>
 * Company Tags
 * -----
 *
 * @Editorial
 */

public class BinaryTreePathToANode {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new Integer[]{1, 2, 3, null, 5}, 3, List.of(1,3));
        test &= test(new Integer[]{1}, 1, List.of(1));
        test &= test(new Integer[]{1,2,3,4,5,6}, 8, List.of());

        System.out.println(test ? "\n All Passed" : "\n Something Failed");
    }

    private static boolean test(Integer[] input, Integer p, List<Integer> expected) {
        System.out.println("-----------------------");
        System.out.println("Input :" + Arrays.toString(input));
        System.out.println("expected :" + expected);
        TreeNode root = TreeBuilder.buildTreeFromLevelOrder(input);
        TreeNode nodeP = CommonMethods.searchNodeByValue(root, p);



        List<Integer> outputRecursive = binaryTreePaths(root, nodeP);
        System.out.println("outputRecursive : " + outputRecursive);
        boolean resultRecursive = CommonMethods.equalsValues(outputRecursive, expected);
        System.out.println("resultRecursive : " + resultRecursive);
        return resultRecursive;

    }

    public static List<Integer> binaryTreePaths(TreeNode root, TreeNode node) {
        List<Integer> path = new ArrayList<>();
        binaryTreePaths(root, node, path);
        return path;
    }

    private static boolean binaryTreePaths(TreeNode root, TreeNode node, List<Integer> path) {
        if(root == null)
            return false;

        //add all the node to paht, as they could be participant
        path.add(root.val);

        //if we hit the node, then we need to discontinue from here
        if( root == node)
            return true;

        //go left and right, if we found node on either side, then we need to stop
        if( (binaryTreePaths(root.left, node, path) || binaryTreePaths(root.right, node, path)))
            return true;

        //if we did not find the node, then current root is not eligible for path.
        path.remove(path.size()-1);
        return false;
    }
}
