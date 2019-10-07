package Java.nonleetcode.graph.graph.node.base;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-06-19
 * Description:Class to represent the graph
 */
public class Graph {

    private List<GraphNode> nodes;

    // Constructor to create an empty ArrayList
    // to store the nodes of the graph
    public Graph() {
        this.nodes = new ArrayList<>();
    }

    // Constructor to set the graph's nodes
    public Graph(List<GraphNode> nodes) {
        this.nodes = nodes;
        this.nodes = new ArrayList<>();
    }

    // Function to add a node to the graph
    public void addNode(GraphNode node) {
        this.nodes.add(node);
    }

    // Function to return the list of nodes
    // for the graph
    public List<GraphNode> getNodes() {
        return this.nodes;
    }

    public String scanGraph(Graph graph) {
        StringBuilder graphData = new StringBuilder();
        Set<GraphNode> visited = new HashSet<>();

        for (GraphNode n : graph.getNodes())
            if (!visited.contains(n))
                graphData.append(printConnectedComponent(n, visited));

        return graphData.toString();
    }

    // Function to print the connected components
    public String printConnectedComponent(GraphNode node,
                                          Set<GraphNode> visited) {

        StringBuilder graph = new StringBuilder();
        Queue<GraphNode> q = new LinkedList<>();
        q.add(node);

        while (!q.isEmpty()) {
            GraphNode currentNode = q.remove();

            if (visited.contains(currentNode))
                continue;

            visited.add(currentNode);

            graph.append("\n" + currentNode.getData() + " -> ");


            for (GraphNode child : currentNode.getChildren()) {

                graph.append(child.getData() + ",");
                q.add(child);
            }
            graph.setLength(graph.length()-1);
        }

        return graph.toString();
    }
}
