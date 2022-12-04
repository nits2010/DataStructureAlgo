package DataStructureAlgo.Java.LeetCode.tree.connectNodesTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-10
 * Description:
 * 1.Perfect tree: https://leetcode.com/problems/populating-next-right-pointers-in-each-node/submissions/
 * 2. General tree:  https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/
 * <p>
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/discuss/331784/A-intersting-solution%3A-iterative-simple-JAVA
 *
 * https://www.geeksforgeeks.org/connect-nodes-at-same-level-with-o1-extra-space/
 */

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                ", left=" + left +
                ", right=" + right +
                ", next=" + next +
                '}';
    }
};

public class ConnectNodesNext {

    public static void main(String []args) {

        Node root = new Node();
        root.val = 3;
        root.left = new Node();
        root.right = new Node();
        root.left.val = 9;
        root.right.val = 20;

        root.right.left = new Node();
        root.right.right = new Node();
        root.right.left.val = 15;
        root.right.right.val = 7;

        Node r = connect(root);
        System.out.println(r);


    }

    public static Node connect(Node root) {

//        return UsingTraversal.connectPreOrderTraversalRecursive(root);
//        return UsingTraversal.connectLevelOrderTraversalIterative(root);
//        return UsingNext.connectNodesUsingNextRecursive (root);
//        return UsingNext.connectNodesUsingNextIterative(root);
        return UsingDummyNode.connectUsingDummy(root);

    }

    /**
     * Work only on Perfect tree
     * //O(n)/O(n)
     *
     * @param root
     * @return
     */

    private static Node connectPreOrderTraversalRecursive(Node root) {

        if (null == root || (root.left == null && root.right == null))
            return root;

        if (root.left != null)
            root.left.next = root.right;

        if (root.right != null)
            root.right.next = (root.next == null) ? null : root.next.left;

        connectPreOrderTraversalRecursive(root.left);
        connectPreOrderTraversalRecursive(root.right);

        return root;
    }

    /**
     * work on any tree
     * //O(n)/O(n) -> Iterative
     *
     * @param root
     * @return
     */
    private static Node connectLevelOrderTraversalIterative(Node root) {


        if (null == root || (root.left == null && root.right == null))
            return root;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(null);

        while (!queue.isEmpty()) {

            Node curr = queue.poll();

            if (curr != null) {

                curr.next = queue.peek();

                if (curr.left != null)
                    queue.offer(curr.left);

                if (curr.right != null)
                    queue.offer(curr.right);

            } else if (!queue.isEmpty())
                queue.offer(null);
        }
        return root;

    }


}

class UsingDummyNode {

    /**
     * this is interesting approach.
     * <p>
     * We use a dummy node to help us to traverse next to every node.
     * <p>
     * What we essentially going to do is, we'll use a pointer (lastNext) which help us to connect and traverse
     * next node in a level. ['dummy' ]
     * <p>
     * Since we need to connect first far away node (tree is not perfect) we need to first stabilise the far away node  of this level
     * before jumping to next level ['runner' and 'lastNext" will help us ]
     * <p>
     * But how do we connect this level node first it self?
     * <p>
     * we are going to follow pre-order + next-order traversal here.
     * <p>
     * initially we assume our 'lastNext' is in the wood (pointing to dummy node).
     * now, in order to do pre-order, we need to have left pointer save :). we'll make 'lastNext"  pointing runner's left pointer (essentially by 'dummy' ) and move our 'lastNext' to this left
     * <p>
     * [this way our dummy will always point to from where we need to start once we finish this level (i.e. 'runner' left )]
     * <p>
     * Now, start simply connecting each node, since 'lastNext' is nothing but 'runner' left only, so if runner has right, we simply connect them.
     * Once we finish with this, we need to move to make sure connecting runner's right to next right of runner. for that we move our runner to next of runner
     * <p>
     * Now important part;
     * once we finish this level (runner = null) we need to point this runner to start point which was pointing by dummy node, remember ?
     * <p>
     * <p>
     * continue this until level finishes
     *
     * @param root
     * @return
     */
    public static Node connectUsingDummy(Node root) {

        Node runner = root;

        Node dummy = new Node();
        Node lastNext = dummy;

        while (runner != null) {

            if (runner.left != null) {
                lastNext.next = runner.left;
                lastNext = lastNext.next;
            }

            if (runner.right != null) {
                lastNext.next = runner.right;
                lastNext = lastNext.next;
            }

            runner = runner.next;

            if (runner == null) {

                runner = dummy.next;
                lastNext = dummy;
                dummy.next = null;

            }
        }

        return root;

    }
}

class UsingTraversal {

    /**
     * Work only on Perfect tree
     * //O(n)/O(n)
     *
     * @param root
     * @return
     */

    public static Node connectPreOrderTraversalRecursive(Node root) {

        if (null == root || (root.left == null && root.right == null))
            return root;

        if (root.left != null)
            root.left.next = root.right;

        if (root.right != null)
            root.right.next = (root.next == null) ? null : root.next.left;

        connectPreOrderTraversalRecursive(root.left);
        connectPreOrderTraversalRecursive(root.right);

        return root;
    }

    /**
     * work on any tree
     * //O(n)/O(n) -> Iterative
     *
     * @param root
     * @return
     */
    public static Node connectLevelOrderTraversalIterative(Node root) {


        if (null == root || (root.left == null && root.right == null))
            return root;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(null);

        while (!queue.isEmpty()) {

            Node curr = queue.poll();

            if (curr != null) {

                curr.next = queue.peek();

                if (curr.left != null)
                    queue.offer(curr.left);

                if (curr.right != null)
                    queue.offer(curr.right);

            } else if (!queue.isEmpty())
                queue.offer(null);
        }
        return root;

    }


}


/**
 * This will give Time limit exceed as it unnessary solving for same node multiple time becuase of first if
 */
class UsingNext {
    /**
     * Instead of connecting child first, we connect parent next first
     * O(n)/O(n)
     *
     * @param root
     * @return
     */
    public static Node connectNodesUsingNextRecursive(Node root) {
        if (null == root)
            return root;


        if (root.next != null)
            connectNodesUsingNextRecursive(root.next);

        if (root.left != null) {

            if (root.right != null) {

                root.left.next = root.right;
                root.right.next = getNext(root);
            } else
                root.left.next = getNext(root);

            connectNodesUsingNextRecursive(root.left);
        } else if (root.right != null) {
            root.right.next = getNext(root);
            connectNodesUsingNextRecursive(root.right);
        } else
            connectNodesUsingNextRecursive(getNext(root));


        return root;

    }

    private static Node getNext(Node root) {
        Node next = root.next;
        while (next != null) {
            if (next.left != null)
                return next.left;
            if (next.right != null)
                return next.right;
            next = next.next;
        }

        return next;
    }


    public static Node connectNodesUsingNextIterative(Node root) {
        if (null == root || (root.left == null && root.right == null))
            return root;

        Node curr = root;

        while (curr != null) {

            Node temp = curr;

            while (temp != null) {

                if (temp.left != null) {

                    if (temp.right != null) {

                        temp.left.next = temp.right;
                        temp.right.next = getNext(temp);
                    } else
                        temp.left.next = getNext(temp);

                    temp = temp.next;
                }
            }

            if (curr.left != null)
                curr = curr.left;
            else if (curr.right != null)
                curr = curr.right;
            else
                curr = getNext(curr);
        }

        return root;
    }

}