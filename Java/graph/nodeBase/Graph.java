package Java.graph.nodeBase;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-06-19
 * Description:Class to represent the graph
 */
class Graph {

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
}
