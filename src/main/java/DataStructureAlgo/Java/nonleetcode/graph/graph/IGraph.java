package DataStructureAlgo.Java.nonleetcode.graph.graph;

import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 20/02/19
 * Question Title: I Graph
 * Link: TODO: Add Link
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

public interface IGraph {

    List<Integer>[] getAdjList();

    int getVertices();

    void addEdge(int source, int destination);

    String print();



}
