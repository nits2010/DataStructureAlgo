package Java.nonleetcode.graph.questions.minimum.spanning.tree;

import Java.nonleetcode.graph.graph.IWeightedGraph;
import Java.nonleetcode.graph.graph.types.Edges;

import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-09-08
 * Description:
 */
public interface IMinimumSpanningTree {


    List<Edges> mst(IWeightedGraph graph);

}
