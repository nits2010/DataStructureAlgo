package Java.graph;

import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 12/04/19
 * Description:
 */
public interface IGraph extends IGraphBase {



    List<Integer> topologicalSort();

    List<Integer> topologicalSortKhanAlgo();

    boolean detectCycleKhanAlgo();

    boolean detectCycleDFS();


}
