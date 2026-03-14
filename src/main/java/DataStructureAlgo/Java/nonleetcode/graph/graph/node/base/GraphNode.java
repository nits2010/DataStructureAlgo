package DataStructureAlgo.Java.nonleetcode.graph.graph.node.base;

import java.util.LinkedList;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 2019-06-19
 * Question Title: Graph Node
 * Link: https://leetcode.com/problems/graph-node/
 * Description:
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

public class GraphNode {

    public int val;
    public List<GraphNode> neighbors;

    public GraphNode() {
        neighbors = new LinkedList<>();
    }

    public GraphNode(int _val) {
        this.val = _val;
        neighbors = new LinkedList<>();
    }

    public GraphNode(int _val, List<GraphNode> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }

    // Function to add a child to the current node
    public void addChild(GraphNode node) {
        this.neighbors.add(node);
    }

    // Function to return a list of children
    // for the current node
    public List<GraphNode> getChildren() {
        return neighbors;
    }

    // Function to set the node's value
    public void setData(int data) {
        this.val = data;
    }

    // Function to return the node's value
    public int getData() {
        return val;
    }

    @Override
    public String toString() {
        return "" + val;
    }
}
