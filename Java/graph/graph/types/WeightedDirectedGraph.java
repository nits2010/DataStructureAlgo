package Java.graph.graph.types;

import Java.graph.graph.IWeightedGraph;

import java.util.LinkedList;
import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-09-08
 * Description:
 */
public class WeightedDirectedGraph implements IWeightedGraph {

    //To hold the edges
    private final List<Edges>[] adjList;
    private final int vertices;

    //initiate the graph
    public WeightedDirectedGraph(int vertices) {

        adjList = new LinkedList[vertices];

        for (int i = 0; i < vertices; i++)
            adjList[i] = new LinkedList<>();

        this.vertices = vertices;
    }


    @Override
    public List<Edges>[] getAdjList() {
        return adjList;
    }

    @Override
    public int getVertices() {
        return this.vertices;
    }

    @Override
    public void addEdge(int source, int destination, double weight) {
        adjList[source].add(new Edges(source, destination, weight));
    }

    @Override
    public String scan() {

        StringBuilder graphData = new StringBuilder();
        for (int u = 0; u < this.adjList.length; u++)
            graphData.append(this.adjList[u] + " \n");

        return graphData.toString();
    }
}
