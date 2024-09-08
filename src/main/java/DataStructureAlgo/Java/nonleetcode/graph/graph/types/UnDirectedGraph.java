package DataStructureAlgo.Java.nonleetcode.graph.graph.types;

import DataStructureAlgo.Java.nonleetcode.graph.graph.IGraph;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 12/04/19
 * Description:
 */
public class UnDirectedGraph implements IGraph {

    //To hold the edges
    private final List<Integer>[] adjacencyList;
    private final List<Edges> edgesList;
    private final int vertices;

    private int edges = 0;

    //initiate the graph
    public UnDirectedGraph(int vertices) {

        adjacencyList = new LinkedList[vertices];
        edgesList = new LinkedList<>();
        for (int i = 0; i < vertices; i++)
            adjacencyList[i] = new LinkedList<>();

        this.vertices = vertices;
    }

    @Override
    public List<Integer>[] getAdjList() {
        return this.adjacencyList;
    }

    @Override
    public int getVertices() {
        return this.vertices;
    }

    @Override
    public void addEdge(int source, int destination) {
        Edges sourceToDestinationEdge = new Edges(source, destination, 1);
        Edges destinationEdgeToSource = new Edges(destination, source, 1);

        adjacencyList[source].add(destination);
        adjacencyList[destination].add(source);

        edgesList.add(sourceToDestinationEdge);
        edgesList.add(destinationEdgeToSource);

        edges += 2;


    }

    @Override
    public String print() {

        StringBuilder graphData = new StringBuilder();
        for (int u = 0; u < this.adjacencyList.length; u++)
            graphData.append(u).append("->").append(this.adjacencyList[u]).append(" \n");

        return graphData.toString();
    }


}
