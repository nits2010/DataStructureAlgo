package Java.graph.graph.types;

import Java.graph.graph.IWeightedGraph;
import Java.graph.questions.minimum.spanning.tree.Edges;

import java.util.LinkedList;
import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-09-08
 * Description:
 */
public class WeightedDirectedGraph implements IWeightedGraph {

    //To hold the edges
    private final List<Edges> adjacencyList;
    private final int vertices;

    //initiate the graph
    public WeightedDirectedGraph(int vertices) {

        adjacencyList = new LinkedList<>();
        this.vertices = vertices;
    }

    @Override
    public List<Edges> getAdjList() {
        return adjacencyList;
    }

    @Override
    public int getVertices() {
        return this.vertices;
    }

    @Override
    public void addEdge(int source, int destination, double weight) {
        adjacencyList.add(new Edges(source, destination, weight));
    }

    @Override
    public String scan() {

        StringBuilder graphData = new StringBuilder();
        for (int u = 0; u < this.adjacencyList.size(); u++)
            graphData.append(this.adjacencyList.get(u) + " \n");

        return graphData.toString();
    }
}
