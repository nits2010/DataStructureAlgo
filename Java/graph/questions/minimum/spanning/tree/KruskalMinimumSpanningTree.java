package Java.graph.questions.minimum.spanning.tree;

import Java.UnionFindDisjointSets;
import Java.graph.graph.IWeightedGraph;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-09-08
 * Description: https://www.geeksforgeeks.org/kruskals-minimum-spanning-tree-algorithm-greedy-algo-2/
 * Refer theory for understanding purpose
 * <p>
 * {@link Java.UnionFindDisjointSets}
 * <p>
 * Algorithm
 * 1. Sort all the edges in non-decreasing order of their weight.
 * 2. Pick the smallest edge. Check if it forms a cycle with the spanning tree formed so far.
 * If cycle is not formed, include this edge. Else, discard it.
 * 3. Repeat step#2 until there are (V-1) edges in the spanning tree.
 */
public class KruskalMinimumSpanningTree implements IMinimumSpanningTree {


    @Override
    public List<Edges> mst(IWeightedGraph graph) {

        if (graph.getAdjList() == null || graph.getAdjList().isEmpty())
            return Collections.EMPTY_LIST;

        //Create a copy of graph
        final List<Edges> adj = new LinkedList<>(graph.getAdjList());

        //1. Sort all the edges in non-decreasing order of their weight.
        Collections.sort(adj, (Comparator.comparingDouble(o -> o.weight)));

        final List<Edges> mst = new LinkedList<>();

        final UnionFindDisjointSets disjointSets = new UnionFindDisjointSets(graph.getVertices());


        //3. Repeat step#2 until there are (V-1) edges in the spanning tree.
        int e = 0;
        while (e < adj.size() && mst.size() < graph.getVertices() - 1) {  //A minimum spanning tree has (V – 1) edges where V is the number of vertices in the given graph.
            Edges edges = adj.get(e);
            int source = edges.source;
            int destination = edges.destination;
            double weight = edges.weight;

            //* 2. Pick the smallest edge. Check if it forms a cycle with the spanning tree formed so far.
            // * If cycle is not formed, include this edge. Else, discard it.
            if (disjointSets.unionBoth(source, destination)) {
                mst.add(new Edges(source, destination, weight));

            }
            e++;
        }


        return mst;
    }
}
