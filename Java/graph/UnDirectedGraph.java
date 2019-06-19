package Java.graph;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 12/04/19
 * Description:
 */
public class UnDirectedGraph implements IGraphBase {

    //To hold the edges
    private final List<Integer>[] adjancyList;
    private final int vertices;
    private  int val ;

    //initiate the graph
    public UnDirectedGraph(int vertices) {

        adjancyList = new LinkedList[vertices];
        for (int i = 0; i < vertices; i++)
            adjancyList[i] = new LinkedList<>();

        this.vertices = vertices;
    }

    @Override
    public List<Integer>[] getAdjList() {
        return this.adjancyList;
    }

    @Override
    public int getVertices() {
        return this.vertices;
    }

    @Override
    public void addEdge(int source, int destination) {
        adjancyList[source].add(destination);
        adjancyList[destination].add(source);

    }

    public void setVal(int _val){
        this.val = _val;
    }


}
