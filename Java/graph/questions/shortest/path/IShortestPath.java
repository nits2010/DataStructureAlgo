package Java.graph.questions.shortest.path;

import Java.graph.graph.IWeightedGraph;
import Java.graph.graph.types.Edges;

import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-09-08
 * Description:
 */
public interface IShortestPath {

    List<Edges> shortestPath(IWeightedGraph graph, int source);
}
