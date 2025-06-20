# Pattern 7: Tree Breadth First Search

This pattern is based on the <b>Breadth First Search (BFS)</b> technique to traverse a tree.

Any problem involving the traversal of a tree in a level-by-level order can be efficiently solved using this approach. We will use a <b>Queue</b> to keep track of all the nodes of a level before we jump onto the next level. This also means that the space complexity of the algorithm will be `O(W)`, where `W` is the maximum number of nodes on any level.


## 1. Binary Tree Level Order Traversal (easy)
https://leetcode.com/problems/binary-tree-level-order-traversal/

> Given a binary tree, populate an array to represent its level-by-level traversal. You should populate the values of all <b>nodes of each level from left to right</b> in separate sub-arrays.

Since we need to traverse all nodes of each level before moving onto the next level, we can use the <b>Breadth First Search (BFS)</b> technique to solve this problem.

We can use a <b>Queue</b> to efficiently traverse in <b>BFS</b> fashion. Here are the steps of our algorithm:
1. Start by pushing the `root` node to the queue.
2. Keep iterating until the <b>queue</b> is empty.
3. In each iteration, first count the elements in the <b>queue</b> (let’s call it `levelSize`). We will have these many nodes in the current level.
4. Next, remove `levelSize` nodes from the <b>queue</b> and push their `value` in an array to represent the current level.
5. After removing each node from the queue, insert both of its children into the queue.
6. If the <b>queue</b> is not empty, repeat from <i>step 3</i> for the next level.

````java
import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class BinaryTreeLevelOrder {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>(levelSize);
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                // add the node to the current level
                currentLevel.add(currentNode.val);
                // insert the children of current node in the queue
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
            result.add(currentLevel);
        }
        
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        
        BinaryTreeLevelOrder solution = new BinaryTreeLevelOrder();
        List<List<Integer>> result = solution.levelOrder(root);
        System.out.println("Level order traversal: " + result);
    }
}
````

- The time complexity of the above algorithm is `O(N)`, where `N` is the total number of nodes in the tree. This is due to the fact that we traverse each node once.
- The space complexity of the above algorithm will be `O(N)` as we need to return a list containing the level order traversal. We will also need `O(N)` space for the queue. Since we can have a maximum of `N/2` nodes at any level (this could happen only at the lowest level), therefore we will need `O(N)` space to store them in the queue.

### Java Solution using Queue
````java
import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<>();
        if (root == null) return levels;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                currentLevel.add(currentNode.val);
                
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
            levels.add(currentLevel);
        }
        return levels;
    }

    public static void main(String[] args) {
        // Test case 1
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);
        
        Solution solution = new Solution();
        System.out.println(solution.levelOrder(root1)); // [[3], [9, 20], [15, 7]]

        // Test case 2
        TreeNode root2 = new TreeNode(1);
        System.out.println(solution.levelOrder(root2)); // [[1]]
        
        // Test case 3
        System.out.println(solution.levelOrder(null)); // []
    }
}
````

## 2. Reverse Level Order Traversal (easy)
https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
> Given a binary tree, populate an array to represent its level-by-level traversal in reverse order, i.e., <b>the lowest level comes first</b>. You should populate the values of all nodes in each level from left to right in separate sub-arrays.

This problem follows the <b>Binary Tree Level Order Traversal</b> pattern. We can follow the same <b>BFS</b> approach. The only difference will be that instead of appending the current level at the end, we will append the current level at the beginning of the result list.

````java
import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) { val = x; }
}

public class ReverseLevelOrder {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                currentLevel.add(currentNode.val);

                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
            // Add the current level at the beginning of the result list
            result.add(0, currentLevel);
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.left.right = new TreeNode(10);
        root.right.right = new TreeNode(5);
        
        ReverseLevelOrder solution = new ReverseLevelOrder();
        List<List<Integer>> result = solution.levelOrderBottom(root);
        System.out.println(result); // [[9, 10, 5], [7, 1], [12]]
    }
}
````
- The time complexity of the above algorithm is `O(N)`, where `N` is the total number of nodes in the tree. This is due to the fact that we traverse each node once.
- The space complexity of the above algorithm will be `O(N)` as we need to return a list containing the level order traversal. We will also need `O(N)` space for the queue. Since we can have a maximum of `N/2` nodes at any level (this could happen only at the lowest level), therefore we will need `O(N)` space to store them in the queue.

## 3. Zigzag Traversal (medium)
https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/

> Given a binary tree, populate an array to represent its zigzag level order traversal. You should populate the values of all <b>nodes of the first level from left to right</b>, then <b>right to left for the next level</b> and keep alternating in the same manner for the following levels.

This problem follows the <b>Binary Tree Level Order Traversal</b> pattern. We can follow the same <b>BFS</b> approach. The only additional step we have to keep in mind is to alternate the level order traversal, which means that for every other level, we will traverse similar to <b>[Reverse Level Order Traversal](#reverse-level-order-traversal-easy)</b>.

````java
import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) { val = x; }
}

public class ZigzagLevelOrder {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new LinkedList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();

                // Add the node to the current level based on the traverse direction
                if (leftToRight) {
                    currentLevel.add(currentNode.val);
                } else {
                    currentLevel.add(0, currentNode.val);
                }

                // Insert the children of current node in the queue
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
            result.add(currentLevel);
            // Reverse the traversal direction
            leftToRight = !leftToRight;
        }
        return result;
    }

    public static void main(String[] args) {
        // Test case 1
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);
        root1.right.left = new TreeNode(6);
        root1.right.right = new TreeNode(7);
        
        ZigzagLevelOrder solution = new ZigzagLevelOrder();
        System.out.println(solution.zigzagLevelOrder(root1)); // [[1], [3, 2], [4, 5, 6, 7]]

        // Test case 2
        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(9);
        root2.right = new TreeNode(20);
        root2.right.left = new TreeNode(15);
        root2.right.right = new TreeNode(7);
        System.out.println(solution.zigzagLevelOrder(root2)); // [[3], [20, 9], [15, 7]]

        // Test case 3
        TreeNode root3 = new TreeNode(1);
        System.out.println(solution.zigzagLevelOrder(root3)); // [[1]]
        
        // Test case 4
        System.out.println(solution.zigzagLevelOrder(null)); // []
    }
}
````
- The time complexity of the above algorithm is `O(N)`, where `N` is the total number of nodes in the tree. This is due to the fact that we traverse each node once.
- The space complexity of the above algorithm will be `O(N)` as we need to return a list containing the level order traversal. We will also need `O(N)` space for the queue. Since we can have a maximum of `N/2` nodes at any level (this could happen only at the lowest level), therefore we will need `O(N)` space to store them in the queue.

## 4. Level Averages in a Binary Tree (easy)
https://leetcode.com/problems/average-of-levels-in-binary-tree/

> Given a binary tree, populate an array to represent the <b>averages of all of its levels</b>

This problem follows the <b>Binary Tree Level Order Traversal</b> pattern. We can follow the same <b>BFS</b> approach. The only difference will be that instead of keeping track of all nodes of a level, we will only track the running sum of the values of all nodes in each level. In the end, we will append the average of the current level to the result array.

````java
import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) { val = x; }
}

public class LevelAverages {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            double levelSum = 0;

            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                // Add the node's value to the running sum
                levelSum += currentNode.val;


                // Insert the children of current node in the queue
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
            // Append the current level's average to the result array
            result.add(levelSum / levelSize);
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        
        LevelAverages solution = new LevelAverages();
        List<Double> result = solution.averageOfLevels(root);
        System.out.println("Level averages are: " + result); // [12.0, 4.0, 6.5]
    }
}
````
- The time complexity of the above algorithm is `O(N)`, where `N` is the total number of nodes in the tree. This is due to the fact that we traverse each node once.
- The space complexity of the above algorithm will be `O(N)` which is required for the queue. Since we can have a maximum of `N/2` nodes at any level (this could happen only at the lowest level), therefore we will need `O(N)` space to store them in the queue

### 5. Level Maximum in a Binary Tree 
https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/
> 🌟  Find the largest value on each level of a binary tree.

We will follow a similar approach, but instead of having a running sum we will track the maximum value of each level.

````java
import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) { val = x; }
}

public class LevelMaximum {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            int maxValue = Integer.MIN_VALUE;

            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                maxValue = Math.max(maxValue, currentNode.val);

                // Insert the children of current node in the queue
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
            result.add(maxValue);
        }
        return result;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        
        LevelMaximum solution = new LevelMaximum();
        List<Integer> result = solution.largestValues(root);
        System.out.println("Max values for each level are: " + result); // [12, 7, 10]
    }
}
````

## 6. Minimum Depth of a Binary Tree (easy)
https://leetcode.com/problems/minimum-depth-of-binary-tree/

> Find the minimum depth of a binary tree. The minimum depth is the number of nodes along the <b>shortest path from the root node to the nearest leaf node</b>.

This problem follows the <b>Binary Tree Level Order Traversal</b> pattern. We can follow the same <b>BFS</b> approach. The only difference will be, instead of keeping track of all the nodes in a level, we will only track the depth of the tree. As soon as we find our first leaf node, that level will represent the minimum depth of the tree.

````java
import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) { val = x; }
}

public class MinimumDepth {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }


        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int minimumTreeDepth = 0;

        while (!queue.isEmpty()) {
            minimumTreeDepth++;
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();

                // Check if this is a leaf node
                if (currentNode.left == null && currentNode.right == null) {
                    return minimumTreeDepth;
                }


                // Insert the children of current node in the queue
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
        }
        return minimumTreeDepth;
    }


    public static void main(String[] args) {
        // Test case 1
        TreeNode root1 = new TreeNode(12);
        root1.left = new TreeNode(7);
        root1.right = new TreeNode(1);
        root1.right.left = new TreeNode(10);
        root1.right.right = new TreeNode(5);
        
        MinimumDepth solution = new MinimumDepth();
        System.out.println("Tree Minimum Depth: " + solution.minDepth(root1)); // 2

        // Test case 2
        root1.left.left = new TreeNode(9);
        root1.right.left.left = new TreeNode(11);
        System.out.println("Tree Minimum Depth: " + solution.minDepth(root1)); // 3
    }
}
````
- The time complexity of the above algorithm is `O(N)`, where `N` is the total number of nodes in the tree. This is due to the fact that we traverse each node once.
- The space complexity of the above algorithm will be `O(N)` which is required for the queue. Since we can have a maximum of `N/2` nodes at any level (this could happen only at the lowest level), therefore we will need `O(N)` space to store them in the queue.

### 7. Maximum Depth of a Binary Tree
https://leetcode.com/problems/maximum-depth-of-binary-tree/
> Given a binary tree, find its maximum depth (or height).

We will follow a similar approach. Instead of returning as soon as we find a leaf node, we will keep traversing for all the levels, incrementing `maximumDepth` each time we complete a level. 

````java
import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) { val = x; }
}

public class MaximumDepth {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int maximumTreeDepth = 0;

        while (!queue.isEmpty()) {
            maximumTreeDepth++;
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();

                // Insert the children of current node in the queue
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
        }
        return maximumTreeDepth;
    }

    public static void main(String[] args) {
        // Test case 1
        TreeNode root1 = new TreeNode(12);
        root1.left = new TreeNode(7);
        root1.right = new TreeNode(1);
        root1.right.left = new TreeNode(10);
        root1.right.right = new TreeNode(5);
        
        MaximumDepth solution = new MaximumDepth();
        System.out.println("Tree Maximum Depth: " + solution.maxDepth(root1)); // 3

        // Test case 2
        root1.left.left = new TreeNode(9);
        root1.right.left.left = new TreeNode(11);
        System.out.println("Tree Maximum Depth: " + solution.maxDepth(root1)); // 4
    }
}
````
## 8. Level Order Successor (easy) 
> Given a binary tree and a node, find the level order successor of the given node in the tree. The level order successor is the node that appears right after the given node in the level order traversal.

This problem follows the <b>Binary Tree Level Order Traversal</b> pattern. We can follow the same <b>BFS</b> approach. The only difference will be that we will not keep track of all the levels. Instead we will keep inserting child nodes to the queue. As soon as we find the given node, we will return the next node from the <b>queue</b> as the level order successor.

````java
import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) { val = x; }
}

public class LevelOrderSuccessor {
    public static TreeNode findSuccessor(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode currentNode = queue.poll();

            // Insert the children of current node in the queue
            if (currentNode.left != null) {
                queue.offer(currentNode.left);
            }
            if (currentNode.right != null) {
                queue.offer(currentNode.right);
            }

            // Break if we have found the key
            if (currentNode.val == key) {
                break;
            }
        }
        // The next node in the queue is the successor
        return queue.isEmpty() ? null : queue.poll();
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);

        // Test case 1: Find successor of 12
        TreeNode result1 = findSuccessor(root, 12);
        System.out.println("Successor of 12: " + (result1 != null ? result1.val : "null")); // 7

        // Test case 2: Find successor of 9
        TreeNode result2 = findSuccessor(root, 9);
        System.out.println("Successor of 9: " + (result2 != null ? result2.val : "null")); // 10
    }
}
````

- The time complexity of the above algorithm is `O(N)`, where `N` is the total number of nodes in the tree. This is due to the fact that we traverse each node once.
- The space complexity of the above algorithm will be `O(N)` which is required for the queue. Since we can have a maximum of `N/2` nodes at any level (this could happen only at the lowest level), therefore we will need `O(N)` space to store them in the queue.

## 9. 😕 Connect Level Order Siblings (medium)
https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
> Given a binary tree, connect each node with its level order successor. The last node of each level should point to a `null` node.

This problem follows the <b>Binary Tree Level Order Traversal</b> pattern. We can follow the same <b>BFS</b> approach. The only difference is that while traversing a level we will remember the previous node to connect it with the current node.

````java
import java.util.*;

class Node {
    int val;
    Node left;
    Node right;
    Node next;

    Node(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
        this.next = null;
    }
}

public class ConnectLevelOrderSiblings {
    public static Node connect(Node root) {
        if (root == null) {
            return null;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            Node previousNode = null;

            // Connect all nodes of this level
            for (int i = 0; i < levelSize; i++) {
                Node currentNode = queue.poll();
                
                if (previousNode != null) {
                    previousNode.next = currentNode;
                }
                previousNode = currentNode;

                // Insert the children of current node in the queue
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
        }
        return root;
    }

    // Method to print the tree using 'next' pointers (for testing)
    public static void printLevelOrder(Node root) {
        System.out.println("Level order traversal using 'next' pointer: ");
        Node nextLevelRoot = root;
        
        while (nextLevelRoot != null) {
            Node currentNode = nextLevelRoot;
            nextLevelRoot = null;
            
            while (currentNode != null) {
                System.out.print(currentNode.val + " ");
                
                if (nextLevelRoot == null) {
                    if (currentNode.left != null) {
                        nextLevelRoot = currentNode.left;
                    } else if (currentNode.right != null) {
                        nextLevelRoot = currentNode.right;
                    }
                }
                currentNode = currentNode.next;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Node root = new Node(12);
        root.left = new Node(7);
        root.right = new Node(1);
        root.left.left = new Node(9);
        root.right.left = new Node(10);
        root.right.right = new Node(5);
        
        connect(root);
        printLevelOrder(root);
    }
}
````

- The time complexity of the above algorithm is `O(N)`, where `N` is the total number of nodes in the tree. This is due to the fact that we traverse each node once.
- The space complexity of the above algorithm will be `O(N)`, which is required for the queue. Since we can have a maximum of `N/2` nodes at any level (this could happen only at the lowest level), therefore we will need `O(N)` space to store them in the queue.

## 10. 🌟 Connect All Level Order Siblings (medium) 
> Given a binary tree, connect each node with its level order successor. The last node of each level should point to the first node of the next level.

This problem follows the <b>Binary Tree Level Order Traversal</b> pattern. We can follow the same <b>BFS</b> approach. The only difference will be that while traversing we will remember (irrespective of the level) the previous node to connect it with the current node.

````java
import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode next;

    TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
        this.next = null;
    }

    // Method to print the tree using 'next' pointers (for testing)
    void printTree() {
        TreeNode current = this;
        System.out.print("Traversal using 'next' pointer: ");
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();
    }
}

public class ConnectAllSiblings {
    public static void connect(TreeNode root) {
        if (root == null) {
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        TreeNode previousNode = null;
        
        while (!queue.isEmpty()) {
            TreeNode currentNode = queue.poll();
            
            if (previousNode != null) {
                previousNode.next = currentNode;
            }
            previousNode = currentNode;
            
            // Insert the children of current node in the queue
            if (currentNode.left != null) {
                queue.offer(currentNode.left);
            }
            if (currentNode.right != null) {
                queue.offer(currentNode.right);
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        
        connect(root);
        root.printTree(); // Should print: 12 7 1 9 10 5
    }
}
````

- The time complexity of the above algorithm is `O(N)`, where `N` is the total number of nodes in the tree. This is due to the fact that we traverse each node once.
- The space complexity of the above algorithm will be `O(N)` which is required for the queue. Since we can have a maximum of `N/2` nodes at any level (this could happen only at the lowest level), therefore we will need `O(N)` space to store them in the queue.
## 11. 🌟 Right View of a Binary Tree (easy) 
https://leetcode.com/problems/binary-tree-right-side-view/

> Given a binary tree, return an array containing nodes in its right view. The right view of a binary tree is the set of <b>nodes visible when the tree is seen from the right side</b>.

````java
import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

public class RightViewBinaryTree {
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                
                // If it's the last node of this level, add to result
                if (i == levelSize - 1) {
                    result.add(currentNode.val);
                }
                
                // Add children to the queue
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        root.left.left.left = new TreeNode(3);
        
        List<Integer> result = rightSideView(root);
        System.out.println("Tree right view: " + result); // [12, 1, 5, 3]
    }
}
````

- The time complexity of the above algorithm is `O(N)`, where `N` is the total number of nodes in the tree. This is due to the fact that we traverse each node once.
- The space complexity of the above algorithm will be `O(N)` as we need to return a list containing the level order traversal. We will also need `O(N)` space for the queue. Since we can have a maximum of `N/2` nodes at any level (this could happen only at the lowest level), therefore we will need `O(N)` space to store them in the queue.

### Similar Questions
> Given a binary tree, return an array containing nodes in its left view. The left view of a binary tree is the set of nodes visible when the tree is seen from the left side.

We will be following a similar approach, but instead of appending the last element of each level, we will be appending the first element of each level to the output array.

````java
public class LeftViewBinaryTree {
    public static List<Integer> leftSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                
                // If it's the first node of this level, add to result
                if (i == 0) {
                    result.add(currentNode.val);
                }
                
                // Add children to the queue
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        root.left.left.left = new TreeNode(3);
        
        List<Integer> result = leftSideView(root);
        System.out.println("Tree left view: " + result); // [12, 7, 9, 3]
    }
}
````
--------------

# Tree Traversal (BFS, DFS - Inorder, Preorder, Postorder) - Top 40 LeetCode Questions
- [Leetcode list](https://leetcode.com/problem-list/2kjvtnv6/)

1. [102. Binary Tree Level Order Traversal](https://leetcode.com/problems/binary-tree-level-order-traversal/) (Medium) *(BFS)*
2. [94. Binary Tree Inorder Traversal](https://leetcode.com/problems/binary-tree-inorder-traversal/) (Easy) *(Iterative and Recursive)*
3. [144. Binary Tree Preorder Traversal](https://leetcode.com/problems/binary-tree-preorder-traversal/) (Easy) *(Iterative and Recursive)*
4. [145. Binary Tree Postorder Traversal](https://leetcode.com/problems/binary-tree-postorder-traversal/) (Easy) *(Iterative and Recursive)*
5. [104. Maximum Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree/) (Easy) *(DFS or BFS)*
6. [112. Path Sum](https://leetcode.com/problems/path-sum/) (Easy) *(DFS)*
7. [226. Invert Binary Tree](https://leetcode.com/problems/invert-binary-tree/) (Easy) *(DFS or BFS)*
8. [103. Binary Tree Zigzag Level Order Traversal](https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/) (Medium) *(BFS)*
9. [199. Binary Tree Right Side View](https://leetcode.com/problems/binary-tree-right-side-view/) (Medium) *(BFS or DFS)*
10. [101. Symmetric Tree](https://leetcode.com/problems/symmetric-tree/) (Easy) *(Recursive or Iterative BFS)*
11. [100. Same Tree](https://leetcode.com/problems/same-tree/) (Easy) *(Recursive or Iterative)*
12. [113. Path Sum II](https://leetcode.com/problems/path-sum-ii/) (Medium) *(DFS with backtracking)*
13. [257. Binary Tree Paths](https://leetcode.com/problems/binary-tree-paths/) (Easy) *(DFS)*
14. [114. Flatten Binary Tree to Linked List](https://leetcode.com/problems/flatten-binary-tree-to-linked-list/) (Medium) *(DFS)*
15. [98. Validate Binary Search Tree](https://leetcode.com/problems/validate-binary-search-tree/) (Medium) *(Inorder traversal)*
16. [230. Kth Smallest Element in a BST](https://leetcode.com/problems/kth-smallest-element-in-a-bst/) (Medium) *(Inorder traversal)*
17. [236. Lowest Common Ancestor of a Binary Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/) (Medium) *(Recursive DFS)*
18. [297. Serialize and Deserialize Binary Tree](https://leetcode.com/problems/serialize-and-deserialize-binary-tree/) (Hard) *(BFS or DFS)*
19. [637. Average of Levels in Binary Tree](https://leetcode.com/problems/average-of-levels-in-binary-tree/) (Easy) *(BFS)*
20. [515. Find Largest Value in Each Tree Row](https://leetcode.com/problems/find-largest-value-in-each-tree-row/) (Medium) *(BFS)*
21. [572. Subtree of Another Tree](https://leetcode.com/problems/subtree-of-another-tree/) (Easy) *(DFS comparison)*
22. [662. Maximum Width of Binary Tree](https://leetcode.com/problems/maximum-width-of-binary-tree/) (Medium) *(BFS with indexing)*
23. [863. All Nodes Distance K in Binary Tree](https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/) (Medium) *(DFS + graph-like logic)*
24. [95. Unique Binary Search Trees II](https://leetcode.com/problems/unique-binary-search-trees-ii/) (Medium) *(Recursive generation)*
25. [107. Binary Tree Level Order Traversal II](https://leetcode.com/problems/binary-tree-level-order-traversal-ii/) (Medium) *(BFS)*
26. [1022. Sum of Root To Leaf Binary Numbers](https://leetcode.com/problems/sum-of-root-to-leaf-binary-numbers/) (Easy) *(DFS)*
27. [1161. Maximum Level Sum of a Binary Tree](https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/) (Medium) *(BFS)*
28. [1302. Deepest Leaves Sum](https://leetcode.com/problems/deepest-leaves-sum/) (Medium) *(BFS or DFS)*
29. [1379. Find a Corresponding Node of a Binary Tree in a Clone of That Tree](https://leetcode.com/problems/find-a-corresponding-node-of-a-binary-tree-in-a-clone-of-that-tree/) (Easy) *(DFS or BFS)*
30. Prime [1469. Find All The Lonely Nodes](https://leetcode.com/problems/find-all-the-lonely-nodes/) (Easy) *(DFS or BFS)*
31. Prime [1602. Find Nearest Right Node in Binary Tree](https://leetcode.com/problems/find-nearest-right-node-in-binary-tree/) (Medium) *(BFS)*
32. Prime [1650. Lowest Common Ancestor of a Binary Tree III](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iii/) (Medium) *(Traversal with parent pointers)*
33. [173. Binary Search Tree Iterator](https://leetcode.com/problems/binary-search-tree-iterator/) (Medium) *(Inorder traversal with stack)*
34. [1993. Operations on Tree](https://leetcode.com/problems/operations-on-tree/) (Hard) *(Tree structure + traversal)*
35. [222. Count Complete Tree Nodes](https://leetcode.com/problems/count-complete-tree-nodes/) (Medium) *(DFS optimized)*
36. [235. Lowest Common Ancestor of a Binary Search Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/) (Easy) *(BST-specific)*
37. Prime [285. Inorder Successor in BST II](https://leetcode.com/problems/inorder-successor-in-bst-ii/) (Medium) *(Traversal with parent pointers)*
38. [99. Recover Binary Search Tree](https://leetcode.com/problems/recover-binary-search-tree/) (Medium) *(Inorder traversal to find swapped nodes)*
39. [652. Find Duplicate Subtrees](https://leetcode.com/problems/find-duplicate-subtrees/) (Medium) *(DFS + hashing)*
40. [814. Binary Tree Pruning](https://leetcode.com/problems/binary-tree-pruning/) (Medium) *(Postorder traversal)*
