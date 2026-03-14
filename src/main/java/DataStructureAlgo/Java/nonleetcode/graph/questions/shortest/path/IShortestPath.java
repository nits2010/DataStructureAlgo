package DataStructureAlgo.Java.nonleetcode.graph.questions.shortest.path;

import  DataStructureAlgo.Java.nonleetcode.graph.graph.IWeightedGraph;
import  DataStructureAlgo.Java.nonleetcode.graph.graph.types.Edges;

import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 2019-09-08
 * Question Title: I Shortest Path
 * Link: https://leetcode.com/problems/i-shortest-path/
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

public interface IShortestPath {

    List<Edges> shortestPath(IWeightedGraph graph, int source);
}
