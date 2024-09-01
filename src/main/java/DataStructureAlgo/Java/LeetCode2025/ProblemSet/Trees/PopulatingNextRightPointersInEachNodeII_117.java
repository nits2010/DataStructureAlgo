package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: Nitin Gupta
 * Date: 8/19/2024
 * Question Category: 117. Populating Next Right Pointers in Each Node II
 * Description: https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/description/
 * Given a binary tree
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
 * Input: root = [1,2,3,4,5,null,7]
 * Output: [1,#,2,3,#,4,5,7,#]
 * Explanation: Given the above binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
 * Example 2:
 * <p>
 * Input: root = []
 * Output: []
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 6000].
 * -100 <= Node.val <= 100
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
 * extension {@link PopulatingNextRightPointersInEachNode_116}
 * <p>
 * Tags
 * -----
 *
 * @medium <p>
 * <p>
 * Company Tags
 * -----
 * @Editorial <a href="https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/solutions/331784/a-intersting-solution-iterative-simple-java">...</a>
 */
public class PopulatingNextRightPointersInEachNodeII_117 {


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


        class SolutionUsingLevelOrderTraversal {
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


        /**
          In this solution {@link PopulatingNextRightPointersInEachNode_116.Solution.SolutionUsingPreOrderTraversal} we used pre-order traversal.
         However, the same pre-order traversal won’t work here as the tree is not a complete binary tree.

         The main challenge is connecting siblings whose parent either does not have a left or right child. This can lead to a left node being connected to a far-away sibling’s right node. For example, in left-skewed or right-skewed trees, the bottom-most node on the left has no reachability to the bottom-most right.

         To handle this with a recursive approach similar to pre-order traversal, we need a way to traverse far-away siblings from the parent node. This requires ensuring that the parent’s next pointer is well-connected.

         To achieve this, we’ll run both pre-order traversal and next sibling traversal in parallel, ensuring we always have access to the next sibling while dealing with the current sibling.

         Algorithm:
         1. Create a Dummy Sibling: For the parent’s children, create a dummy sibling on the left side.
         2. Connect the Dummy Sibling: Connect this dummy sibling to the current parent’s left child to establish the dummy sibling, and hence the right sibling.
         3. Access the Next Sibling: After connecting, we can access the next sibling by simply traversing the next pointer of this dummy node.
         4. Reset When Necessary: Whenever we reach a position where there is no next sibling for a node, reset the dummy sibling. This will essentially happen level by level.
         */
        class SolutionUsingPreOrder {
            public Node connect(Node root) {
                if (root == null)
                    return root;

                Node current = root;
                Node dummy = new Node(); //fake sibling
                Node lastNext = dummy;

                while (current!=null){

                    if(current.left!=null){
                        lastNext.next = current.left;
                        lastNext = lastNext.next;
                    }

                    if(current.right!=null){
                        lastNext.next = current.right;
                        lastNext = lastNext.next;
                    }

                    //move to next sibling for this root
                    current = current.next;

                    //if there is no sibling, then we have to reset and move the next level
                    if(current == null){
                        //the next level will always be the next of dummy
                        current = dummy.next; // this will move root to its left child however, if left child not exists then it moves either right child or next sibling

                        //reset
                        lastNext = dummy;
                        dummy.next = null;

                    }

                }

                return root;
            }
        }


    }
}