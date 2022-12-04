package DataStructureAlgo.Java.nonleetcode.graph.graph;

import  DataStructureAlgo.Java.nonleetcode.graph.graph.types.Edges;

import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 2019-09-08
 * Description:
 */
public interface IWeightedGraph {


    List<Edges>[] getAdjList();

    int getVertices();

    void addEdge(int source, int destination, double weight);

    String scan();

    int getEdges();
}
