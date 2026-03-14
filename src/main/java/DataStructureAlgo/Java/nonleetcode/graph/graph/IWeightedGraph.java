package DataStructureAlgo.Java.nonleetcode.graph.graph;

import  DataStructureAlgo.Java.nonleetcode.graph.graph.types.Edges;

import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 2019-09-08
 * Question Title: I Weighted Graph
 * Link: https://leetcode.com/problems/i-weighted-graph/
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

public interface IWeightedGraph {


    List<Edges>[] getAdjList();

    int getVertices();

    void addEdge(int source, int destination, double weight);

    String scan();

    int getEdges();

    List<Edges> getEdgesList();
}
