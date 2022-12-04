package DataStructureAlgo.Java.nonleetcode.graph.questions.minimum.spanning.tree;

import  DataStructureAlgo.Java.nonleetcode.UnionFindDisjointSets;
import  DataStructureAlgo.Java.nonleetcode.graph.graph.IWeightedGraph;
import  DataStructureAlgo.Java.nonleetcode.graph.graph.types.Edges;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 2019-09-08
 * Description: https://www.geeksforgeeks.org/kruskals-minimum-spanning-tree-algorithm-greedy-algo-2/
 * Refer theory for understanding purpose
 * <p>
 * {@link UnionFindDisjointSets}
 * <p>
 * Algorithm
 * 1. Sort all the edges in non-decreasing order of their weight.
 * 2. Pick the smallest edge. Check if it forms a cycle with the spanning tree formed so far.
 * If cycle is not formed, include this edge. Else, discard it.
 * 3. Repeat step#2 until there are (V-1) edges in the spanning tree.
 * <p>
 * Time Complexity: O(E*logE) or O(E*logV). Sorting of edges takes O(ELogE) time.
 * After sorting, we iterate through all edges and apply find-union algorithm. The find and union operations can take at most O(LogV) time.
 * So overall complexity is O(ELogE + ELogV) time. The value of E can be at most O(V^2), so O(LogV) are O(LogE) same. Therefore, overall time complexity is O(E*logE) or O(E*logV)
 * O(E*LogE + E*LogV) time.
 */
public class KruskalMinimumSpanningTree implements IMinimumSpanningTree {


    @Override
    public List<Edges> mst(IWeightedGraph graph) {

        if (graph.getAdjList() == null || graph.getAdjList().length == 0)
            return Collections.EMPTY_LIST;

        //Create a copy of graph
        final List<Edges> edge = new LinkedList<>();
        final List<Edges>[] adjList = graph.getAdjList();

        for (List<Edges> e : adjList)
            edge.addAll(e);


        //1. Sort all the edges in non-decreasing order of their weight.
        Collections.sort(edge, (Comparator.comparingDouble(o -> o.weight)));

        final List<Edges> mst = new LinkedList<>();

        final UnionFindDisjointSets disjointSets = new UnionFindDisjointSets(graph.getVertices());


        //3. Repeat step#2 until there are (V-1) edges in the spanning tree.
        int e = 0;
        while (e < edge.size() && mst.size() < graph.getVertices()) {  //A minimum spanning tree has (V – 1) edges where V is the number of vertices in the given graph.
            Edges edges = edge.get(e);
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
