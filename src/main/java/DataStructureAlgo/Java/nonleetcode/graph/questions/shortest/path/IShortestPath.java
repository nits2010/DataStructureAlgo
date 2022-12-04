package DataStructureAlgo.Java.nonleetcode.graph.questions.shortest.path;

import  DataStructureAlgo.Java.nonleetcode.graph.graph.IWeightedGraph;
import  DataStructureAlgo.Java.nonleetcode.graph.graph.types.Edges;

import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 2019-09-08
 * Description:
 */
public interface IShortestPath {

    List<Edges> shortestPath(IWeightedGraph graph, int source);
}
