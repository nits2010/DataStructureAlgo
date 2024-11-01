package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Graph._133;

import DataStructureAlgo.Java.LeetCode2025.ProblemSet.List._138.CopyListWithRandomPointer_138;
import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.GraphBuilder;
import DataStructureAlgo.Java.helpers.templates.GraphNode.Node;
import DataStructureAlgo.Java.nonleetcode.graph.questions.clone.GraphClone;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 9/11/2024
 * Question Category: 133. Clone Graph
 * Description: https://leetcode.com/problems/clone-graph/description/
 * Given a reference of a node in a connected undirected graph.
 * <p>
 * Return a deep copy (clone) of the graph.
 * <p>
 * Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.
 * <p>
 * class Node {
 * public int val;
 * public List<Node> neighbors;
 * }
 * <p>
 * <p>
 * Test case format:
 * <p>
 * For simplicity, each node's value is the same as the node's index (1-indexed). For example, the first node with val == 1, the second node with val == 2, and so on. The graph is represented in the test case using an adjacency list.
 * <p>
 * An adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.
 * <p>
 * The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
 * Output: [[2,4],[1,3],[2,4],[1,3]]
 * Explanation: There are 4 nodes in the graph.
 * 1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 * 3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 * Example 2:
 * <p>
 * <p>
 * Input: adjList = [[]]
 * Output: [[]]
 * Explanation: Note that the input contains one empty list. The graph consists of only one node with val = 1 and it does not have any neighbors.
 * Example 3:
 * <p>
 * Input: adjList = []
 * Output: []
 * Explanation: This an empty graph, it does not have any nodes.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the graph is in the range [0, 100].
 * 1 <= Node.val <= 100
 * Node.val is unique for each node.
 * There are no repeated edges and no self-loops in the graph.
 * The Graph is connected and all nodes can be visited starting from the given node.
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.graph.CloneGraph}
 * Similar  {@link DataStructureAlgo.Java.nonleetcode.Tree.CloneTree} {@link CopyListWithRandomPointer_138}
 * extension {@link GraphClone}
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @HashTable
 * @Depth-FirstSearch
 * @Breadth-FirstSearch
 * @Graph <p><p>
 * Company Tags
 * -----
 * @Facebook
 * @Amazon
 * @Bloomberg
 * @Google
 * @Microsoft
 * @Apple
 * @LinkedIn
 * @Mathworks
 * @Pocket
 * @Gems
 * @Splunk
 * @Twitter
 * @Uber
 * @WalmartLabs
 *
 *
 * @Editorial https://leetcode.com/problems/clone-graph/solutions/341276/clone-single-graph-multi-component-graph-clone-list-clone-binary-tree
 */
public class CloneGraph_133 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(List.of(List.of(2, 4), List.of(1, 3), List.of(2, 4), List.of(1, 3)), List.of(List.of(2, 4), List.of(1, 3), List.of(2, 4), List.of(1, 3)));
        test &= test(List.of(), List.of());
        test &= test(List.of(List.of()), List.of(List.of()));
        CommonMethods.printAllTestOutCome(test);
    }


    public static boolean test(List<List<Integer>> adjList, List<List<Integer>> expected) {
        System.out.println("-------------------------------");
        System.out.println("adjList = " + adjList);
        System.out.println("expected = " + expected);

        Node originalGraph = GraphBuilder.createGraph(adjList);
        SolutionBFS solutionBFS = new SolutionBFS();
        SolutionDFS solutionDFS = new SolutionDFS();

        Node clonedGraphBFS = solutionBFS.cloneGraph(originalGraph);
        boolean bfsResult = GraphBuilder.isSameGraph(originalGraph, clonedGraphBFS);
        System.out.println("bfsResult = " + (bfsResult ? "Passed" : "Failed"));

        Node clonedGraphDFS = solutionDFS.cloneGraph(originalGraph);
        boolean dfsResult = GraphBuilder.isSameGraph(originalGraph, clonedGraphDFS);
        System.out.println("DfsResult = " + (dfsResult ? "Passed" : "Failed"));
        return bfsResult && dfsResult;
    }


    /**
     * Create a hashmap of original vs. clone
     * Run a BFS and visit each node.
     * Check for existence in the map, if it exists use it otherwise create a new clone
     */
    static class SolutionBFS {
        public Node cloneGraph(Node node) {
            if (node == null) return null;

            Map<Node, Node> originalVsClone = new HashMap<>();
            Queue<Node> queue = new LinkedList<>();

            //clone the first node
            Node clone = new Node(node.val, new ArrayList<>());
            originalVsClone.put(node, clone);

            //run throw a bfs
            queue.offer(node);

            //clone each node via bfs
            while (!queue.isEmpty()) {

                Node current = queue.poll();

                //check for existence
                Node currentClone = originalVsClone.get(current);

                //if exits, then use it for neighbors
                List<Node> neighbors = current.neighbors;

                //visit all neighbors and clone them
                for (Node neighbor : neighbors) {
                    Node cloneNeighbor = originalVsClone.get(neighbor);

                    if (cloneNeighbor == null) {
                        cloneNeighbor = new Node(neighbor.val, new ArrayList<>());

                        //push to map for next access
                        originalVsClone.put(neighbor, cloneNeighbor);

                        //enqueue it for the next round
                        queue.offer(neighbor);
                    }
                    currentClone.neighbors.add(cloneNeighbor);
                }
            }

            return clone;
        }
    }

    static class SolutionDFS {
        public Node cloneGraph(Node node) {
            if (node == null) return null;

            Map<Node, Node> originalVsClone = new HashMap<>();
            return cloneGraph(node, originalVsClone);
        }

        private Node cloneGraph(Node node, Map<Node, Node> originalVsClone) {
            //return if the clone already done
            if (originalVsClone.containsKey(node))
                return originalVsClone.get(node);

            //clone current node
            Node clone = new Node(node.val, new ArrayList<>());
            originalVsClone.put(node, clone);

            //clone neighbors
            for (Node neighbor : node.neighbors) {
                //clone this neighbor
                clone.neighbors.add(cloneGraph(neighbor, originalVsClone));
            }
            return clone;

        }
    }
}
