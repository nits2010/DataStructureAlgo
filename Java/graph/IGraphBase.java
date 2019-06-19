package Java.graph;

import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 20/02/19
 * Description:
 */
public interface IGraphBase {

    List<Integer>[] getAdjList();

    int getVertices();

    void addEdge(int source, int destination);






}
