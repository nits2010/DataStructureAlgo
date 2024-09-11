package DataStructureAlgo.Java.helpers;

import DataStructureAlgo.Java.helpers.templates.GraphNode.Node;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Nitin Gupta
 * Date: 9/11/2024
 * Question Category: [easy | medium | hard ]
 * Description:
 * <p><p>
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p><p>
 * Tags
 * -----
 *
 * <p><p>
 * Company Tags
 * -----
 * <p><p>
 *
 * @Editorial
 */
public class GraphBuilder {

    public static Node createGraph(List<List<Integer>> adjList) {
        // Create a map to store the nodes
        Map<Integer, Node> nodeMap = new HashMap<>();

        // Create nodes and add them to the map
        for (int i = 0; i < adjList.size(); i++) {
            Node node = new Node(i + 1);
            nodeMap.put(i + 1, node);
        }

        // Connect the nodes using the adjacency list
        for (int i = 0; i < adjList.size(); i++) {
            Node node = nodeMap.get(i + 1);
            for (int neighbor : adjList.get(i)) {
                node.neighbors.add(nodeMap.get(neighbor));
            }
        }

        // Return the root node
        return nodeMap.get(1);
    }

    public static boolean isSameGraph(Node node1, Node node2) {
        if (node1 == null && node2 == null) return true;
        if (node1 == null || node2 == null) return false;

        if (node1.val != node2.val) return false;

        if (node1.neighbors.size() != node2.neighbors.size()) return false;

        Map<Node, Boolean> visited = new HashMap<>();

        return isSameGraphHelper(node1, node2, visited);
    }

    private static boolean isSameGraphHelper(Node node1, Node node2, Map<Node, Boolean> visited) {
        if (visited.containsKey(node1)) return true;

        visited.put(node1, true);

        for (int i = 0; i < node1.neighbors.size(); i++) {
            Node neighbor1 = node1.neighbors.get(i);
            Node neighbor2 = node2.neighbors.get(i);

            if (!isSameGraphHelper(neighbor1, neighbor2, visited)) return false;
        }

        return true;
    }
}
