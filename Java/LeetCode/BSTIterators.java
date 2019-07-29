package Java.LeetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-26
 * Description: https://leetcode.com/problems/binary-search-tree-iterator/
 * <p>
 * If you go with Pre-Cached there are following things
 * <p>
 * Its lightening fast, as we don't need to touch the actual tree again
 * Its fail safe iterator, means andy modification in actual tree won't affect this [ use in concurent system]
 * It consume lot of memory as we need to cache whole tree (O(n))
 * next() and hashNext() is constant time.
 */
public class BSTIterators {

    private static TreeNode getTree() {

        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        return root;
    }

    private static TreeNode getTree3() {

        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        return root;
    }

    private static TreeNode getTree2() {

        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.right.left = new TreeNode(5);
        return root;
    }

    static void inorder(TreeNode root, List<Integer> inorder) {
        if (root == null)
            return;

        inorder(root.left, inorder);
        inorder.add(root.val);
        inorder(root.right, inorder);

    }

    static void preOrder(TreeNode root, List<Integer> preorder) {
        if (null == root)
            return;
        preorder.add(root.val);
        preOrder(root.left, preorder);
        preOrder(root.right, preorder);


    }

    static void postOrder(TreeNode root, List<Integer> postOrder) {
        if (null == root)
            return;

        postOrder(root.left, postOrder);
        postOrder(root.right, postOrder);
        postOrder.add(root.val);


    }


    public static void main(String[] args) {

        TreeNode root1 = getTree();
        TreeNode root2 = getTree2();
        TreeNode root3 = getTree3();

        List<Integer> inorder1 = new ArrayList<>();
        inorder(root1, inorder1);

        List<Integer> inorder2 = new ArrayList<>();
        inorder(root2, inorder2);

        List<Integer> inorder3 = new ArrayList<>();
        inorder(root3, inorder3);


        List<Integer> preOrder1 = new ArrayList<>();
        preOrder(root1, preOrder1);

        List<Integer> preOrder2 = new ArrayList<>();
        preOrder(root2, preOrder2);

        List<Integer> preOrder3 = new ArrayList<>();
        preOrder(root3, preOrder3);


        List<Integer> postOrder1 = new ArrayList<>();
        postOrder(root1, postOrder1);

        List<Integer> postOrder2 = new ArrayList<>();
        postOrder(root2, postOrder2);

        List<Integer> postOrder3 = new ArrayList<>();
        postOrder(root3, postOrder3);


        System.out.println("Inorder ");
        System.out.println("................ ");
        testInorder(root1, inorder1);
        testInorder(root2, inorder2);
        testInorder(root3, inorder3);

        System.out.println("\n\nPre-Order ");
        System.out.println("................ ");
        testPreOrder(root1, preOrder1);
        testPreOrder(root2, preOrder2);
        testPreOrder(root3, preOrder3);


        System.out.println("\n\nPost-Order ");
        System.out.println("................ ");
        testPostOrder(root1, postOrder1);
        testPostOrder(root2, postOrder2);
        testPostOrder(root3, postOrder3);


    }

    static void testInorder(TreeNode root, List<Integer> original) {
        System.out.println("\n\nOriginal :" + original);

        System.out.println("\nInorder Traversal is :");
        BSTIteratorInorder parent = new BSTIteratorInorder();
        BSTIteratorInorder.BSTIterator inorder = parent.new BSTIterator(root);

        while (inorder.hasNext()) {
            System.out.print(" " + inorder.next());
        }


        System.out.println("\nInorder Traversal with Pre-Cached is :");
        BSTIteratorInorderPreCache parentCached = new BSTIteratorInorderPreCache();
        BSTIteratorInorderPreCache.BSTIterator inorderCached = parentCached.new BSTIterator(root);

        while (inorderCached.hasNext()) {
            System.out.print(" " + inorderCached.next());
        }

    }

    private static void testPreOrder(TreeNode root, List<Integer> original) {
        System.out.println("\n\nOriginal :" + original);
        System.out.println("\nPreorder Traversal is :");
        BSTIteratorPreOrder parent = new BSTIteratorPreOrder();
        BSTIteratorPreOrder.BSTIterator preOrder = parent.new BSTIterator(root);

        while (preOrder.hasNext()) {
            System.out.print(" " + preOrder.next());
        }


        System.out.println("\nInorder Traversal with Pre-Cached is :");
        BSTIteratorPreOrderPreCache parentCached = new BSTIteratorPreOrderPreCache();
        BSTIteratorPreOrderPreCache.BSTIterator preorder = parentCached.new BSTIterator(root);

        while (preorder.hasNext()) {
            System.out.print(" " + preorder.next());
        }


    }


    private static void testPostOrder(TreeNode root, List<Integer> original) {
        System.out.println("\n\nOriginal :" + original);
        System.out.println("\nPost order Traversal is :");
        BSTIteratorPostOrder parent = new BSTIteratorPostOrder();
        BSTIteratorPostOrder.BSTIterator postOrder = parent.new BSTIterator(root);

        while (postOrder.hasNext()) {
            System.out.print(" " + postOrder.next());
        }


        System.out.println("\nPost order Traversal  with Pre-Cached is :");
        BSTIteratorPostOrderPreCached parentCached = new BSTIteratorPostOrderPreCached();
        BSTIteratorPostOrderPreCached.BSTIterator postOrderCached = parentCached.new BSTIterator(root);

        while (postOrderCached.hasNext()) {
            System.out.print(" " + postOrderCached.next());
        }


    }


}

/**
 * /** Returns the next integer a in the in-order traversal of the given binary tree.
 * * For example, given a binary tree below,
 * *       4
 * *      / \
 * *     2   6
 * *    / \ / \
 * *   1  3 5  7
 * * the outputs will be 1, 2, 3, 4, 5, 6, 7.
 */

class BSTIteratorInorder {

    class BSTIterator {

        private Stack<TreeNode> stack;

        public BSTIterator(TreeNode root) {

            stack = new Stack<>();
            if (root != null)
                init(root);
        }

        private void init(TreeNode root) {

            while (root != null) {
                stack.push(root);
                root = root.left;
            }

        }

        /**
         * @return the next smallest number
         */
        public int next() {
            if (hasNext()) {

                TreeNode current = stack.pop();

                process(current.right);

                return current.val;
            }

            return 0;
        }

        private void process(TreeNode root) {
            init(root);
        }

        /**
         * @return whether we have a next smallest number
         */
        public boolean hasNext() {

            return !stack.isEmpty();
        }
    }
}

class BSTIteratorInorderPreCache {

    class BSTIterator {

        private LinkedList<TreeNode> inorder;

        public BSTIterator(TreeNode root) {

            inorder = new LinkedList<>();

            inorder(root);

        }

        private void inorder(TreeNode root) {

            if (root == null)
                return;

            inorder(root.left);
            inorder.add(root);
            inorder(root.right);


        }

        /**
         * @return the next smallest number
         */
        public int next() {
            TreeNode next = inorder.pollFirst();
            return next.val;
        }

        /**
         * @return whether we have a next smallest number
         */
        public boolean hasNext() {

            return !inorder.isEmpty();
        }
    }
}


/**
 * Returns the next integer a in the pre-order traversal of the given binary tree.
 * For example, given a binary tree below,
 * *       4
 * *      / \
 * *     2   6
 * *    / \ / \
 * *   1  3 5  7
 * the outputs will be 4, 2, 1, 3, 6, 5, 7.
 */
class BSTIteratorPreOrder {

    class BSTIterator {

        private Stack<TreeNode> stack;

        public BSTIterator(TreeNode root) {
            stack = new Stack<>();

            if (root != null)
                init(root);


        }

        private void init(TreeNode root) {
            stack.push(root);
        }

        /**
         * @return the next smallest number
         */
        public int next() {
            TreeNode node = stack.pop();

            if (node.right != null)
                init(node.right);

            if (node.left != null)
                init(node.left);

            return node.val;
        }

        /**
         * @return whether we have a next smallest number
         */
        public boolean hasNext() {

            return !stack.isEmpty();
        }
    }
}


class BSTIteratorPreOrderPreCache {

    class BSTIterator {


        private LinkedList<TreeNode> preorder;

        public BSTIterator(TreeNode root) {

            preorder = new LinkedList<>();

            preorder(root);

        }

        private void preorder(TreeNode root) {

            if (root == null)
                return;

            preorder.add(root);
            preorder(root.left);
            preorder(root.right);


        }

        /**
         * @return the next smallest number
         */
        public int next() {
            TreeNode next = preorder.pollFirst();
            return next.val;
        }

        /**
         * @return whether we have a next smallest number
         */
        public boolean hasNext() {

            return !preorder.isEmpty();
        }
    }
}

/**
 * Returns the next integer a in the post-order traversal of the given binary tree.
 * * For example, given a binary tree below,
 * *       4
 * *      / \
 * *     2   6
 * *    / \ / \
 * *   1  3 5  7
 * * the outputs will be 1, 3, 2, 5, 7, 6, 4.
 */
class BSTIteratorPostOrder {

    class BSTIterator {

        private Stack<TreeNode> stack;

        public BSTIterator(TreeNode root) {

            stack = new Stack<>();
            init(root);

        }

        /**
         * find the first leaf in a tree rooted at cur and store intermediate nodes
         */
        private void init(TreeNode root) {
            while (root != null) {

                stack.push(root);

                if (root.left != null)
                    root = root.left;
                else
                    root = root.right;
            }
        }

        /**
         * @return the next smallest number
         */
        public int next() {

            TreeNode node = stack.pop();

            if (!stack.isEmpty()) {
                if (node == stack.peek().left) {
                    init(stack.peek().right);  // find next leaf in right sub-tree
                }


            }
            return node.val;
        }

        /**
         * @return whether we have a next smallest number
         */
        public boolean hasNext() {

            return !stack.isEmpty();
        }
    }
}


class BSTIteratorPostOrderPreCached {

    class BSTIterator {
        private LinkedList<TreeNode> postOrder;

        public BSTIterator(TreeNode root) {

            postOrder = new LinkedList<>();
            postOrder(root);

        }

        private void postOrder(TreeNode root) {

            if (root == null)
                return;


            postOrder(root.left);
            postOrder(root.right);
            postOrder.add(root);


        }

        /**
         * @return the next smallest number
         */
        public int next() {
            TreeNode next = postOrder.pollFirst();
            return next.val;
        }

        /**
         * @return whether we have a next smallest number
         */
        public boolean hasNext() {

            return !postOrder.isEmpty();
        }
    }

}