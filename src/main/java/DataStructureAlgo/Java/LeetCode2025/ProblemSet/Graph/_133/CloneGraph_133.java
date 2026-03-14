package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Graph._133;

import DataStructureAlgo.Java.LeetCode2025.ProblemSet.List._138.CopyListWithRandomPointer_138;
import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.GraphBuilder;
import DataStructureAlgo.Java.helpers.templates.GraphNode.Node;
import DataStructureAlgo.Java.nonleetcode.graph.questions.clone.GraphClone;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 2026-03-14
 * Question Title: Clone Graph
 * Link: https://leetcode.com/problems/clone-graph/
 * Description:
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
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
