package Java.graph.nodeBase;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-06-19
 * Description:
 */
// Class to represent the graph
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


    /**
     * This clone a graph where it has multiple components
     *
     * @param gNode
     * @return
     */
    public Graph cloneGraphDisConnectedUndirected(Graph gNode) {

        //Node to clone node
        Map<GraphNode, GraphNode> map = new HashMap<>();

        Graph cloned = new Graph();
        for (GraphNode node : gNode.getNodes()) {
            if (!map.containsKey(node)) {
                //clone the connected component
                cloneConnectedComponent(node, map);

                //attache the cloned connected components
                cloned.getNodes().add(map.get(node));
            }
        }

        return cloned;


    }

    /******* https://leetcode.com/problems/clone-graph/ ******/
    //https://leetcode.com/problems/clone-graph/discuss/341276/Clone-single-Graph-multi-component-graph.-Clone-List-.-Clone-Binary-Tree
    public Graph cloneConnectedComponent(Graph gNode) {

        Map<GraphNode, GraphNode> map = new HashMap<>();

        return cloneConnectedComponent(gNode.getNodes().get(0), map);

    }


    //Do BFS and keep cloning
    private Graph cloneConnectedComponent(GraphNode node, Map<GraphNode, GraphNode> map) {
        Queue<GraphNode> queue = new LinkedList<>();

        GraphNode clone = new GraphNode();
        clone.val = node.val;
        clone.neighbors = new LinkedList<>();
        map.put(node, clone);

        queue.offer(node);

        while (!queue.isEmpty()) {

            GraphNode current = queue.poll();
            GraphNode currentClone = map.get(current);


            List<GraphNode> neighbors = current.neighbors;

            for (GraphNode neighbor : neighbors) {

                GraphNode neiClone = map.get(neighbor);

                if (neiClone == null) {
                    neiClone = new GraphNode();
                    neiClone.val = neighbor.val;
                    neiClone.neighbors = new LinkedList<>();
                    map.put(neighbor, neiClone);
                    queue.offer(neighbor);
                }
                currentClone.neighbors.add(neiClone);

            }
        }

        //For To Build a graph
        GraphNode cloneGraphNode = clone;

        Graph cloned = new Graph();
        cloned.addNode(cloneGraphNode);

        return cloned;
    }

    /******* https://leetcode.com/problems/clone-graph/ ******/
}
