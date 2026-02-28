package DataStructureAlgo.Java.nonleetcode.graph.questions.minimum.spanning.tree;

import  DataStructureAlgo.Java.nonleetcode.graph.graph.IWeightedGraph;
import DataStructureAlgo.Java.nonleetcode.graph.graph.types.Edges;
import DataStructureAlgo.Java.nonleetcode.graph.graph.types.WeightedUnDirectedGraph;

import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 2019-09-08
 * Description:
 */
public class MinimumSpanningTreeTest {

    private static IWeightedGraph getGraph(int[][] edges, int vertices) {
        IWeightedGraph graph = new WeightedUnDirectedGraph(vertices);

        for (int[] edge : edges) {

            graph.addEdge(edge[0], edge[1], edge[2]);

        }

        return graph;

    }

    public static void main(String[] args) {
         /* Let us create the following weighted graph
                 10
            0--------1
            |  \     |
           6|   5\   |15
            |      \ |
            2--------3
                4       */


        test(getGraph(new int[][]{{0, 1, 10}, {1, 3, 15}, {2, 3, 4}, {0, 2, 6}, {0, 3, 5}}, 4));
    }

    private static void test(IWeightedGraph graph) {

        System.out.println("\n Graph :\n" + graph.scan());

        IMinimumSpanningTree kruskalMinimumSpanningTree = new KruskalMinimumSpanningTree();
        IMinimumSpanningTree primMinimumSpanningTree = new PrimMinimumSpanningTree();

        List<Edges> kruskalMST =  kruskalMinimumSpanningTree.mst(graph);
        List<Edges> primMST = primMinimumSpanningTree.mst(graph);
        System.out.println("Kruskal MST :" + kruskalMST);
        System.out.println("Prims MST :" + primMST );

        System.out.println("\nKruskal Edges in MST:");
        for (Edges edge : kruskalMST) {
            System.out.println("From " + edge.source + " to " + edge.destination + " with weight " + edge.weight);
        }

        System.out.println("\nPrims Edges in MST:");
        for (Edges edge : primMST) {
            System.out.println("From " + edge.source + " to " + edge.destination + " with weight " + edge.weight);
        }


    }


}
