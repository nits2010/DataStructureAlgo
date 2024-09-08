package DataStructureAlgo.Java.nonleetcode.graph.graph.types;

import  DataStructureAlgo.Java.nonleetcode.graph.graph.IWeightedGraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 2019-09-08
 * Description:
 */
public class WeightedUnDirectedGraph implements IWeightedGraph {

    //To hold the edges
    private final List<Edges>[] adjList;

    private final List<Edges> edgesList;
    private final int vertices;
    private int edges;

    //initiate the graph
    public WeightedUnDirectedGraph(int vertices) {

        adjList = new LinkedList[vertices];
        edgesList = new LinkedList<>();
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
        Edges sourceToDestinationEdge = new Edges(source, destination, weight);
        Edges destinationEdgeToSource = new Edges(destination, source, weight);

        adjList[source].add(sourceToDestinationEdge);
        adjList[destination].add(destinationEdgeToSource);

        edgesList.add(sourceToDestinationEdge);
        edgesList.add(destinationEdgeToSource);

        this.edges += 2;
    }

    @Override
    public String scan() {

        StringBuilder graphData = new StringBuilder();
        for (List<Edges> list : this.adjList)
            graphData.append(list).append(" \n");

        return graphData.toString();
    }

    @Override
    public int getEdges() {
        return this.edges;
    }

    @Override
    public List<Edges> getEdgesList() {
        return edgesList;
    }
}
