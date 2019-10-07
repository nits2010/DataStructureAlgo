package Java.LeetCode.graph;

import Java.nonleetcode.graph.questions.clone.GraphClone;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-21
 * Description: https://leetcode.com/problems/clone-graph/
 * Given a reference of a node in a connected undirected graph, return a deep copy (clone) of the graph. Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.
 * Example:
 * Input:
 * {"$id":"1","neighbors":[{"$id":"2","neighbors":[{"$ref":"1"},{"$id":"3","neighbors":[{"$ref":"2"},{"$id":"4","neighbors":[{"$ref":"3"},{"$ref":"1"}],"val":4}],"val":3}],"val":2},{"$ref":"4"}],"val":1}
 * Explanation:
 * Node 1's value is 1, and it has two neighbors: Node 2 and 4.
 * Node 2's value is 2, and it has two neighbors: Node 1 and 3.
 * Node 3's value is 3, and it has two neighbors: Node 2 and 4.
 * Node 4's value is 4, and it has two neighbors: Node 1 and 3.
 * Note:
 * The number of nodes will be between 1 and 100.
 * The undirected graph is a simple graph, which means no repeated edges and no self-loops in the graph.
 * Since the graph is undirected, if node p has node q as neighbor, then node q must have node p as neighbor too.
 * You must return the copy of the given node as a reference to the cloned graph.
 * {@link GraphClone}
 */
public class CloneGraph {

    // Definition for a Node.
    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
        }

        public Node(int _val, List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    ;

    class Solution {
        public Node cloneGraph(Node node) {

            return buildAndClone(node);
        }

        public Node buildAndClone(Node node) {
            Map<Node, Node> map = new HashMap<>();
            Queue<Node> queue = new LinkedList<>();

            Node clone = new Node();
            clone.val = node.val;
            clone.neighbors = new LinkedList<>();
            map.put(node, clone);

            queue.offer(node);

            while (!queue.isEmpty()) {

                Node current = queue.poll();
                Node currentClone = map.get(current);


                List<Node> neighbors = current.neighbors;

                for (Node neighbor : neighbors) {

                    Node neiClone = map.get(neighbor);

                    if (neiClone == null) {
                        neiClone = new Node();
                        neiClone.val = neighbor.val;
                        neiClone.neighbors = new LinkedList<>();
                        map.put(neighbor, neiClone);
                        queue.offer(neighbor);
                    }
                    currentClone.neighbors.add(neiClone);

                }
            }
            return map.get(node);

        }


    }
}
