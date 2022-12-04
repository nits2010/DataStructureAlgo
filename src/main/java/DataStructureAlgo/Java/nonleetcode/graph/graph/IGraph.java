package DataStructureAlgo.Java.nonleetcode.graph.graph;

import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 20/02/19
 * Description:
 */
public interface IGraph {

    List<Integer>[] getAdjList();

    int getVertices();

    void addEdge(int source, int destination);

    String print();



}
