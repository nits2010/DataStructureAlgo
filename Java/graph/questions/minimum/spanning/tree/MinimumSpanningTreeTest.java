package Java.graph.questions.minimum.spanning.tree;

import Java.graph.graph.IWeightedGraph;
import Java.graph.graph.types.WeightedDirectedGraph;
import Java.graph.graph.types.WeightedUnDirectedGraph;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-09-08
 * Description:
 */
public class MinimumSpanningTreeTest {

    private static IWeightedGraph getGraph(int[][] edges, int vertices) {
        IWeightedGraph graph = new WeightedDirectedGraph(vertices);

        for (int edge[] : edges) {

            graph.addEdge(edge[0], edge[1], edge[2]);

        }

        return graph;

    }

    public static void main(String[] args) {
         /* Let us create following weighted graph
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
        IMinimumSpanningTree mst = new KruskalMinimumSpanningTree();
        System.out.println("Kruskal MST :" + mst.mst(graph));


    }


}
