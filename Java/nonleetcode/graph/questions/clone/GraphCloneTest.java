package Java.nonleetcode.graph.questions.clone;

import Java.nonleetcode.graph.graph.node.base.Graph;
import Java.nonleetcode.graph.graph.node.base.GraphNode;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 2019-06-19
 * Description:
 */
public class GraphCloneTest {


    // Driver code
    public static void main(String[] args) {
        System.out.println("Connected");
        connected();

        System.out.println("\n\n\n DisConnected");
        disConnected();
    }


    private static void connected() {
        Graph connectedGraph = buildConnectedGraph();

        // Original graph
        System.out.println("\n INITIAL GRAPH");
        System.out.println(connectedGraph.printConnectedComponent(connectedGraph.getNodes().get(0), new HashSet<>()));

        System.out.println("\n CLONED GRAPH");
        GraphClone cloneGraph = new GraphClone();
        Graph cloned = cloneGraph.cloneConnectedComponent(connectedGraph);
        System.out.println(connectedGraph.printConnectedComponent(cloned.getNodes().get(0), new HashSet<>()));

    }

    private static void disConnected() {
        Graph disConnectedGraph = buildDisConnectedGraph();

        // Original graph
        System.out.println("\tINITIAL GRAPH");
        System.out.println(disConnectedGraph.scanGraph(disConnectedGraph));

        // Cloned graph
        System.out.println("\n\n\n CLONED GRAPH\n");
        GraphClone cloneGraph = new GraphClone();
        Graph clonedGraph = cloneGraph.cloneGraphDisConnectedUndirected(disConnectedGraph);
        System.out.println(disConnectedGraph.scanGraph(clonedGraph));
    }


    public static Graph buildConnectedGraph() {
        GraphNode node1 = new GraphNode(1);
        GraphNode node2 = new GraphNode(2);
        GraphNode node3 = new GraphNode(3);
        GraphNode node4 = new GraphNode(4);
        List<GraphNode> v = new LinkedList<>();
        v.add(node2);
        v.add(node4);
        node1.neighbors = v;
        v = new LinkedList<>();
        v.add(node1);
        v.add(node3);
        node2.neighbors = v;
        v = new LinkedList<>();
        v.add(node2);
        v.add(node4);
        node3.neighbors = v;
        v = new LinkedList<>();
        v.add(node3);
        v.add(node1);
        node4.neighbors = v;
        Graph graph = new Graph();
        graph.addNode(node1);
        return graph;
    }

    // Function to build the graph
    public static Graph buildDisConnectedGraph() {

        // Create graph
        Graph g = new Graph();

        // Adding nodes to the graph
        GraphNode g1 = new GraphNode(1);
        g.addNode(g1);
        GraphNode g2 = new GraphNode(2);
        g.addNode(g2);
        GraphNode g3 = new GraphNode(3);
        g.addNode(g3);
        GraphNode g4 = new GraphNode(4);
        g.addNode(g4);
        GraphNode g5 = new GraphNode(5);
        g.addNode(g5);
        GraphNode g6 = new GraphNode(6);
        g.addNode(g6);

        // Adding edges
        g1.addChild(g2);
        g1.addChild(g3);
        g2.addChild(g1);
        g2.addChild(g4);
        g3.addChild(g1);
        g3.addChild(g4);
        g4.addChild(g2);
        g4.addChild(g3);
        g5.addChild(g6);
        g6.addChild(g5);

        return g;
    }

    // Function to print the connected components
    public static void printConnectedComponent(GraphNode node,
                                               Set<GraphNode> visited) {
        if (visited.contains(node))
            return;

        Queue<GraphNode> q = new LinkedList<>();
        q.add(node);

        while (!q.isEmpty()) {
            GraphNode currentNode = q.remove();
            if (visited.contains(currentNode))
                continue;
            visited.add(currentNode);
            System.out.println("Node "
                    + currentNode.getData() + " - " + currentNode);
            for (GraphNode child : currentNode.getChildren()) {
                System.out.println("\tNode "
                        + child.getData() + " - " + child);
                q.add(child);
            }
        }
    }
}
