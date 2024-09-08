package DataStructureAlgo.Java.nonleetcode.graph.graph.types;

import  DataStructureAlgo.Java.nonleetcode.graph.graph.IGraph;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 12/04/19
 * Description:
 */
public class UnDirectedGraph implements IGraph {

    //To hold the edges
    private final List<Integer>[] adjacencyList;
    private final int vertices;

    //initiate the graph
    public UnDirectedGraph(int vertices) {

        adjacencyList = new LinkedList[vertices];
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
        adjacencyList[source].add(destination);
        adjacencyList[destination].add(source);

    }

    @Override
    public String print() {

        StringBuilder graphData = new StringBuilder();
        for (int u = 0; u < this.adjacencyList.length; u++)
            graphData.append(u).append("->").append(this.adjacencyList[u]).append(" \n");

        return graphData.toString();
    }


}
