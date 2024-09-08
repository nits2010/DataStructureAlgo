package DataStructureAlgo.Java.nonleetcode.graph.graph.types;

import  DataStructureAlgo.Java.nonleetcode.graph.graph.IWeightedGraph;

import java.util.LinkedList;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 2019-09-08
 * Description:
 */
public class WeightedDirectedGraph implements IWeightedGraph {

    //To hold the edges
    private final List<Edges>[] adjList;
    private final int vertices;
    private int edges;

    //initiate the graph
    public WeightedDirectedGraph(int vertices) {

        adjList = new LinkedList[vertices];

        for (int i = 0; i < vertices; i++)
            adjList[i] = new LinkedList<>();

        this.vertices = vertices;
        this.edges = 0;
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
        this.edges++;
    }

    @Override
    public String scan() {

        StringBuilder graphData = new StringBuilder();
        for (List<Edges> edgesList : this.adjList)
            graphData.append(edgesList).append(" \n");

        return graphData.toString();
    }

    @Override
    public int getEdges() {
        return this.edges;
    }
}
