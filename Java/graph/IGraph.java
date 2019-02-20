package Java.graph;

import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 20/02/19
 * Description:
 */
public interface IGraph {

    void addEdge(int source, int destination);

    List<Integer> topologicalSort();

    List<Integer> topologicalSortKhanAlgo();


}
