package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees._116;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: Nitin Gupta
 * Date: 8/19/2024
 * Question Category: 116. Populating Next Right Pointers in Each Node
 * Description: https://leetcode.com/problems/populating-next-right-pointers-in-each-node/description/
 * You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:
 * <p>
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 * <p>
 * Initially, all next pointers are set to NULL.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [1,2,3,4,5,6,7]
 * Output: [1,#,2,3,#,4,5,6,7,#]
 * Explanation: Given the above perfect binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
 * Example 2:
 * <p>
 * Input: root = []
 * Output: []
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 212-1].
 * -1000 <= Node.val <= 1000
 * <p>
 * <p>
 * Follow-up:
 * <p>
 * You may only use constant extra space.
 * The recursive approach is fine. You may assume implicit stack space does not count as extra space for this problem.
 * <p>
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.tree.connectNodesTree.ConnectNodesNext}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 *
 * @medium
 * @LinkedList
 * @Tree
 * @Depth-FirstSearch
 * @Breadth-FirstSearch
 * @BinaryTree <p>
 * <p>
 * Company Tags
 * -----
 * @Editorial
 */
public class PopulatingNextRightPointersInEachNode_116 {


    static class Solution {


        // Definition for a Node.
        class Node {
            public Node next;
            public int val;
            public Node left;
            public Node right;

            public Node() {

            }


            public Node(int _val, Node _left, Node _right, Node _next) {
                val = _val;
                left = _left;
                right = _right;
                next = _next;
            }
        }

        static class SolutionUsingPreOrderTraversal {

            public Node connect(Node root) {
                if (root == null)
                    return root;

                //if left node exists for this root, connect it with the right node
                if (root.left != null)
                    root.left.next = root.right;

                if (root.right != null)
                    root.right.next = (root.next == null) ? null : root.next.left;

                connect(root.left);
                connect(root.right);
                return root;
            }
        }

        static class SolutionUsingLevelOrderTraversal {

            public Node connect(Node root) {
                if (root == null)
                    return root;

                Queue<Node> queue = new LinkedList<>();
                queue.offer(root);
                queue.offer(null);

                while (!queue.isEmpty()) {

                    Node current = queue.poll();

                    if (current == null) {
                        if (!queue.isEmpty())
                            queue.offer(null);
                    } else {
                        current.next = queue.peek();


                        if (current.left != null)
                            queue.offer(current.left);

                        if (current.right != null)
                            queue.offer(current.right);

                    }
                }
                return root;
            }
        }
    }
}
