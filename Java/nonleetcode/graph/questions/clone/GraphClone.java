package Java.nonleetcode.graph.questions.clone;

import Java.nonleetcode.graph.graph.node.base.Graph;
import Java.nonleetcode.graph.graph.node.base.GraphNode;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-21
 * Description:
 * https://leetcode.com/problems/clone-graph/
 * https://leetcode.com/problems/clone-graph/discuss/341276/Clone-single-Graph-multi-component-graph.-Clone-List-.-Clone-Binary-Tree
 */
public class GraphClone {

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

}
