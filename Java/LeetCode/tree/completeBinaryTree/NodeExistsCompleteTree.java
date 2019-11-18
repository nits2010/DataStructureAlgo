package Java.LeetCode.tree.completeBinaryTree;

import Java.LeetCode.templates.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-25
 * Description: https://leetcode.com/discuss/interview-question/236898
 * <p>
 * Given a complete (virtual) binary tree, return true/false if the given target node exists in the tree or not. Here, the virtual means the tree nodes are numbered assuming the tree is a complete binary tree.
 * <p>
 * Example:
 * <p>
 * <p>
 * *     1
 * *    / \
 * *   2   3
 * *  / \  /\
 * * 4  5 6 null
 * *
 * doesNodeExist(root, 4); // true
 * doesNodeExist(root, 7); // false, given the node on #7 is a null node
 *
 * Follow-up:
 * https://leetcode.com/problems/count-complete-tree-nodes -> {@link CountNodesInCompleteBinaryTree}
 */
public class NodeExistsCompleteTree {

    public static void main(String[] args) {
        test();
    }


    private static void test() {
        NodeExistsCompleteTreeTraversal traversal = new NodeExistsCompleteTreeTraversal();
        NodeExistsCompleteTreeByNumberingSystemCountNodes countNodesNumbering = new NodeExistsCompleteTreeByNumberingSystemCountNodes();
        NodeExistsCompleteTreeByNumberingSystemBinarySearch binarySearch = new NodeExistsCompleteTreeByNumberingSystemBinarySearch();
        NodeExistsCompleteTreeByPathSearching pathSearching = new NodeExistsCompleteTreeByPathSearching();
        NodeExistsCompleteTreeHuffmanCoding huffmanCoding = new NodeExistsCompleteTreeHuffmanCoding();

        /**
         * *     1
         * *    / \
         * *   2   3
         * *  / \  /
         * * 4  5 6
         *
         * @return
         */
        System.out.println("traversal : " + traversal.nodeExist(Helper.getTree1(), 7) + " expected :" + false);
        System.out.println("NumberingCountNodes : " + countNodesNumbering.nodeExist(Helper.getTree1(), 7) + " expected :" + false);
        System.out.println("binarySearch : " + binarySearch.nodeExist(Helper.getTree1(), 7) + " expected :" + false);
        System.out.println("pathSearching : " + pathSearching.nodeExist(Helper.getTree1(), 7) + " expected :" + false);
        System.out.println("huffmanCoding : " + huffmanCoding.nodeExist(Helper.getTree1(), 7) + " expected :" + false);

        /**
         * *     1
         * *    / \
         * *   2   3
         * *  / \  /\
         * * 4  5 6 7
         *
         * @return
         */
        System.out.println("\n traversal : " + traversal.nodeExist(Helper.getTree2(), 5) + " expected :" + true);
        System.out.println("NumberingCountNodes : " + countNodesNumbering.nodeExist(Helper.getTree2(), 5) + " expected :" + true);
        System.out.println("binarySearch : " + binarySearch.nodeExist(Helper.getTree2(), 5) + " expected :" + true);
        System.out.println("pathSearching : " + pathSearching.nodeExist(Helper.getTree2(), 5) + " expected :" + true);
        System.out.println("huffmanCoding : " + huffmanCoding.nodeExist(Helper.getTree2(), 5) + " expected :" + true);


        /**
         * *           1
         * *          /  \
         * *         2    3
         * *         / \  /\
         * *        4  5 6 7
         * *      /  \
         * *     8    9
         *
         * @return
         */
        System.out.println("\n traversal : " + traversal.nodeExist(Helper.getTree3(), 8) + " expected :" + true);
        System.out.println("NumberingCountNodes : " + countNodesNumbering.nodeExist(Helper.getTree3(), 8) + " expected :" + true);
        System.out.println("binarySearch : " + binarySearch.nodeExist(Helper.getTree3(), 8) + " expected :" + true);
        System.out.println("pathSearching : " + pathSearching.nodeExist(Helper.getTree3(), 8) + " expected :" + true);
        System.out.println("huffmanCoding : " + huffmanCoding.nodeExist(Helper.getTree3(), 8) + " expected :" + true);


    }
}


/**
 * Here is one very interesting idea. We know that huffman-coding tree is also a complete binary tree.
 * In huffman tree, left node we append '0' while for right '1'.
 * We can use same logic for finding the node.
 * <p>
 * Let root's id be i, then its left child's id will be i * 2 + 0, and the right child's id will be i * 2 + 1.
 * So when we see '0,' we know it's a left child, and when we see '1' we know it's a right child.
 * <p>
 * Binary String represents the path from root to the node, where '1' means going right and '0' means going left.
 * For example, 4 = "100", starting from the index 1, we go from root = 1, going left --> 2, going left --> 4;
 * 7 = "111", starting from index 1, we go from root = 1, going right --> 3, going right --> 7.
 * <p>
 * <p>
 * And total time complexity of this solution is O(log2(N)), as the length of binary representation of an input N is log2(N).
 * <p>
 * Implementation is similar to {@link NodeExistsCompleteTreeByPathSearching}, instead of having path as integer number,
 * we'll have it as binary number, directly build using in-build functionality
 */
class NodeExistsCompleteTreeHuffmanCoding {
    public boolean nodeExist(TreeNode root, int id) {
        if (null == root)
            return false;
        TreeNode temp = root;
        char[] binaryString = Integer.toBinaryString(id).toCharArray();

        for (int i = 1; i < binaryString.length; i++) {
            char c = binaryString[i];
            if (c == '0')
                temp = temp.left;
            else
                temp = temp.right;

            if (temp == null) return false;
        }
        return true;

    }
}

/**
 * {@link NodeExistsCompleteTreeByNumberingSystemBinarySearch}
 * Though this solution is fast enough and we can not achieve better than the complexity O(log(n)).
 * There is another algorithm based on PATH SEARCHING only.
 * <p>
 * Idea is to use the same numbering system and build the path backward from where this id could exist just like binary search algo
 * * *     1
 * * *    / \
 * * *   2   3
 * * *  / \  /\
 * * * 4  5 6 7
 * <p>
 * for example target = 4;
 * then path would be [4, 2, 1 ] as this is complete binary tree we can get the parent of any node as child/2.
 * Note: this path is not using tree rather just through id. Since if this node exist in the tree then it would have similar path {not exactly}
 * <p>
 * we'll run through the path and according to element in path, we'll move left and right, till root.val == id
 * [4,2] : root = 1 {exclude root}
 * i=0; 4 ->even -> left -> root = 2
 * i=1; 2 -> even -> left -> root = 4
 * means this path exist hence found
 * <p>
 * target = 7
 * path [7,3] : root = 1
 * i = 0; 7 -> odd -> right -> root = 3
 * i = 1; 3 -> odd -> right -> root = 7
 * means this path exist hence found
 * <p>
 * target = 9
 * path [9,4,2] : root = 1
 * i =0; 9 -> odd -> right -> root = 3
 * i = 1; 4 -> even -> left -> root = 6
 * i = 2; 2 -> even ->left => root = null Not found
 * means this path does not exist hence not found
 * * target = 8
 * * path [8,4,2] : root = 1
 * * i =0; 98-> even -> left -> root = 2
 * * i = 1; 4 -> even -> left -> root = 4
 * * i = 2; 2 -> even ->left => root = null Not found
 * means this path does not exist hence not found
 * <p>
 * <p>
 * We can also implement using stack this idea:
 * Put target into stack and divide target /= 2 and repeat as long as target > 1
 * While poping element from the stack, check if I should go left child or right child. If the value is not found, return false
 */
class NodeExistsCompleteTreeByPathSearching {

    public boolean nodeExist(TreeNode root, int id) {
        if (null == root)
            return false;

        List<Integer> path = getPath(id);

        return nodeExist(root, id, path);

    }

    /**
     * O(log(id))
     *
     * @param id
     * @return
     */
    private List<Integer> getPath(int id) {
        List<Integer> path = new ArrayList<>();

        while (id != 1) {
            path.add(id);
            id = id / 2; //parent
        }
        return path;
    }

    /**
     * O(log(n))
     *
     * @param root
     * @param id
     * @param path
     * @return
     */
    private boolean nodeExist(TreeNode root, int id, List<Integer> path) {

        for (int i : path) {

            if (i % 2 == 0)
                root = root.left;
            else
                root = root.right;

            if (root == null)
                return false;

        }
        return true;
    }


    /**
     * Using stack
     * O(log(n)) / O(log(n))
     *
     * @param root
     * @param target
     * @return
     */
    boolean doesNodeExist(TreeNode root, int target) {
        if (root == null) return false;
        Stack<Integer> stack = new Stack<>();
        int temp = target;
        while (temp > 1) {
            stack.push(temp);
            temp /= 2;
        }
        while (!stack.isEmpty()) {
            if (stack.pop() % 2 == 0) root = root.left;
            else root = root.right;
            if (root == null) return false;
        }
        return true;
    }
}

/**
 * In both {@link NodeExistsCompleteTreeByNumberingSystemCountNodes} and {@link NodeExistsCompleteTreeTraversal}
 * Does not take the full advantage of being the complete binary tree.
 * But  {@link NodeExistsCompleteTreeByNumberingSystemCountNodes} gives us a important fact that
 * in binary tree has nodes based on his height.
 * if a complete binary tree is full binary tree then nodes are 2^height - 1 otherwise nodes would be in in space of
 * [2^(height-1) , 2^height ]
 * <p>
 * we also know that a complete binary tree can be represent by an array with parent at 'i'th index and left child is on
 * 2*i + 1 while right child is on 2*i + 2.
 * <p>
 * which means the node to be present in given tree must be in then range of [2^(height-1) , 2^height ].
 * finding height again is O(n)
 * <p>
 * But if using the given id, we can estimate that at what height this node would be present because of numbering system.
 * <p>
 * Once we know the height of this node, we can do binary search between the range and find out that it is present in that range or not.
 * <p>
 * * *     1
 * * *    / \
 * * *   2   3
 * * *  / \  /\
 * * * 4  5 6 7
 * <p>
 * For example: id = 4 would be level 2 as log2(4) = 2 {starting level with 0}
 * then range would be [4,7] we can do binary search using tree root and find that does it exist or not
 * <p>
 * O(log(n))
 */
class NodeExistsCompleteTreeByNumberingSystemBinarySearch {

    public boolean nodeExist(TreeNode root, int id) {
        if (null == root)
            return false;

        int idHeight = (int) Math.floor(Math.log(id) / Math.log(2));

        int start = (int) Math.pow(2, idHeight);
        int end = (int) Math.pow(2, (idHeight + 1)) - 1;

        return binarySearch(root, id, start, end);
    }

    private boolean binarySearch(TreeNode root, int id, int start, int end) {

        if (root == null)
            return false;

        int mid = (start + end) >> 1;

        /**
         * Note: this will never be true in first iteration;
         * since the range [start, end] is calculated based on height of id
         * for example id = 4
         * [4,7] then mid != 4 in first iteration
         * which make sure you go either left or right
         */
        if (id == mid)
            return true;

        if (id < mid)
            return binarySearch(root.left, id, start, mid - 1);

        return binarySearch(root.right, id, mid + 1, end);
    }

}


/**
 * if this id is present in this complete binary tree then it will be in range of 1 <= id <= count of nodes
 * O(n)
 * {@link IsCompleteBinaryTree}
 */
class NodeExistsCompleteTreeByNumberingSystemCountNodes {

    public boolean nodeExist(TreeNode root, int id) {
        if (null == root)
            return false;
        int count = countNodes(root);

        if (id < 0 || id > count)
            return false;

        return true;
    }

    /**
     * Count how many nodes are there in tree
     * O(n)/O(n)
     *
     * @param root
     * @return
     */
    private static int countNodes(TreeNode root) {
        if (root == null)
            return (0);
        return (1 + countNodes(root.left) + countNodes(root.right));
    }

}


/**
 * Simplest way to do simply traversal in the given tree and see does node exist or not
 * <p>
 * Pre-Post-In-Level order
 * O(n)
 * <p>
 * Note: This algo does not take the advantage of being given tree is Complete binary tree;
 * We can use any traversal
 */
class NodeExistsCompleteTreeTraversal {

    public boolean nodeExist(TreeNode root, int id) {
        if (null == root)
            return false;

        if (root.val == id)
            return true;

        boolean left = nodeExist(root.left, id);
        if (left)
            return true;

        boolean right = nodeExist(root.right, id);
        if (right)
            return true;

        return false;
    }

}