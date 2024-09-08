package DataStructureAlgo.Java.nonleetcode.graph.questions.shortest.path;

import DataStructureAlgo.Java.nonleetcode.graph.graph.IWeightedGraph;
import DataStructureAlgo.Java.nonleetcode.graph.graph.types.Edges;
import DataStructureAlgo.Java.nonleetcode.graph.graph.types.WeightedUnDirectedGraph;

import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 2019-09-08
 * Description:
 */
public class ShortestPathTest {

    private static IWeightedGraph getGraph(int[][] edges, int vertices) {
        IWeightedGraph graph = new WeightedUnDirectedGraph(vertices);

        for (int edge[] : edges) {

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


        test(getGraph(new int[][]{{0, 1, 10}, {1, 3, 15}, {2, 3, 4}, {0, 2, 6}, {0, 3, 5}}, 4), 0);


        test(getGraph(new int[][]{{0, 1, 9}, {0, 2, 6}, {0, 3, 5}, {0, 4, 3}, {2, 1, 2}, {2, 3, 4}}, 5), 0);


        test(getGraph(new int[][]{{0, 1, 4}, {0, 7, 8}, {1, 2, 8}, {2, 3, 7}, {3, 4, 9},
                {4, 5, 10}, {5, 6, 2}, {6, 7, 1}, {7, 8, 7}, {2, 8, 2}, {1, 7, 11}, {2, 5, 4}, {3, 5, 14}, {8, 6, 6}}, 9), 0);

        test(getGraph(new int[][]{{0, 1, 4}, {0, 7, 8}, {1, 2, 8}, {2, 3, 7}, {3, 4, 9},
                {4, 5, 10}, {5, 6, 2}, {6, 7, 1}, {7, 8, 7}, {2, 8, 2}, {1, 7, 11}, {2, 5, 4}, {3, 5, 14}, {8, 6, 6}}, 9), 3);

//        test(getGraph(new int[][]{{0,1,}}))

    }

    private static void test(IWeightedGraph graph, int source) {
        System.out.println("\n Graph :\n" + graph.scan());

        final IShortestPath dijkstraShortestPath = new DijkstraShortestPath();
        final IShortestPath bellmanFordShortestPath = new BellmanFordShortestPath();


        final List<Edges> shortestPathDijkstraShortestPath = dijkstraShortestPath.shortestPath(graph, source);
        final List<Edges> shortestPathBellmanFordShortestPath = bellmanFordShortestPath.shortestPath(graph, source);

        System.out.println("\n Dijkstra -> ");
        shortestPathDijkstraShortestPath.forEach(System.out::println);

        System.out.println("\n bellmanFord -> ");
        shortestPathBellmanFordShortestPath.forEach(System.out::println);


    }
}
