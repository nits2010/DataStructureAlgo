package Java.graph;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 20/02/19
 * Description:
 */
public class DirectedGraph implements IGraphTopologicalCycle {


    //To hold the edges
    private final List<Integer>[] adjancyList;
    private final int vertices;

    //initiate the graph
    public DirectedGraph(int vertices) {

        adjancyList = new LinkedList[vertices];
        for (int i = 0; i < vertices; i++)
            adjancyList[i] = new LinkedList<>();

        this.vertices = vertices;
    }

    @Override
    public void addEdge(int source, int destination) {
        adjancyList[source].add(destination);

    }

    @Override
    public List<Integer>[] getAdjList() {
        return this.adjancyList;
    }

    @Override
    public int getVertices() {
        return this.vertices;
    }

    /**
     * This works for both, Directed Graph
     * and Un-Directed Graph
     *
     * @return
     */
    @Override
    public List<Integer> topologicalSort() {
        return TopologicalSorts.topologicalSortDFS(this.vertices, this.adjancyList);
    }


    /**
     * This works for only, Directed Graph
     * because A DAG G has at least one vertex with in-degree 0 and one vertex with out-degree 0.
     *
     * @return
     */
    @Override
    public List<Integer> topologicalSortKhanAlgo() {
        return TopologicalSorts.topologicalSortKhanAlgo(this.adjancyList, this.vertices);
    }

    @Override
    public boolean detectCycleKhanAlgo() {
        return GraphDetectCycle.detectCycleKhanAlgo(this.vertices, this.adjancyList);
    }

    @Override
    public boolean detectCycleDFS() {
        return GraphDetectCycle.detectCycleDFS(this.vertices, this.adjancyList);
    }


}
