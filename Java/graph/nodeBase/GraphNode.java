package Java.graph.nodeBase;

import java.util.LinkedList;
import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-06-19
 * Description:
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
    public void addChild(GraphNode node)
    {
        this.neighbors.add(node);
    }

    // Function to return a list of children
    // for the current node
    public List<GraphNode> getChildren()
    {
        return neighbors;
    }

    // Function to set the node's value
    public void setData(int data)
    {
        this.val = data;
    }

    // Function to return the node's value
    public int getData()
    {
        return val;
    }


}
