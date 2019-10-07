package Java.companyWise.intuit.reversegear;

import Java.helpers.GenericPrinter;
import Java.nonleetcode.graph.questions.shortest.path.DijkstraShortestPath;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-09-07
 * Description:https://www.geeksforgeeks.org/minimum-edges-reverse-make-path-source-destination/
 * <p>
 * Given a directed graph and a source node and destination node, we need to find how many edges we need to
 * reverse in order to make at least 1 path from source node to destination node.
 */
public class ReverseGearReverseEdgesInGraphPath {


    public static void main(String[] args) {

        /**
         * In above graph there were two paths from node 0 to node 6,
         * 0 -> 1 -> 2 -> 3 -> 6
         * 0 -> 1 -> 5 -> 4 -> 6
         * But for first path only two edges need to be reversed, so answer will be 2 only
         */
        test(new int[][]{{0, 1}, {2, 1}, {2, 3}, {6, 3}, {5, 1}, {4, 5}, {6, 4}}, 2);

//        Scanner scanner = new Scanner(System.in);
//
//        int n = scanner.nextInt();
//        int m = scanner.nextInt();
//
//        final int graphD[][] = new int[m][2];
//
//        for (int i = 0; i < m; i++) {
//            graphD[i][0] = scanner.nextInt();
//            graphD[i][1] = scanner.nextInt();
//        }


    }

    private static void test(int[][] graph, int expected) {
        System.out.println("\n Given Graph :\n" + GenericPrinter.toString(graph) + " expected :" + expected);
        System.out.println(MinimumEdgesToReverseMakePathFromSourceToDestination.minReverse(graph, graph.length, graph.length));
    }


}

/**
 * This problem can be solved assuming a different version of the given graph.
 * In this version we make a reverse edge corresponding to every edge and we assign that a weight 1 and assign a weight 0 to original edge.
 * After this modification above graph looks something like below,
 * <p>
 * <p>
 * Now we can see that we have modified the graph in such a way that, if we move towards original edge, no cost is incurred,
 * but if we move toward reverse edge 1 cost is added. So if we apply Dijkstra’s shortest path on this modified graph from given source,
 * then that will give us minimum cost to reach from source to destination i.e. minimum edge reversal from source to destination.
 *
 * {@link DijkstraShortestPath}
 *
 */
class MinimumEdgesToReverseMakePathFromSourceToDestination {

    public static int minReverse(int graphD[][], int m, int n) {

        if (null == graphD || graphD.length == 0)
            return Integer.MIN_VALUE;

        final Graph graph = assignWeight(graphD, m, n);
        final int[] reverse = graph.shortestPath(0);

        if (reverse[n - 1] != Integer.MAX_VALUE)
            return reverse[n - 1];
        return Integer.MIN_VALUE;

    }


    private static Graph assignWeight(int graphD[][], int m, int n) {

        Graph graph = new Graph(m);
        for (int i = 0; i < m; i++) {
            graph.add(graphD[i][0], graphD[i][1], 0);
            graph.add(graphD[i][1], graphD[i][0], 1);
        }

        return graph;

    }


    static class Graph {

        static class Weight {
            int v;
            int w;

            public Weight(int v, int w) {
                this.v = v;
                this.w = w;
            }
        }

        int v;
        List<Weight>[] adj;

        public Graph(int v) {
            this.v = v;
            adj = new List[v];

            for (int i = 0; i < v; i++)
                adj[i] = new ArrayList<>();
        }

        public void add(int u, int v, int w) {

            adj[u].add(new Weight(v, w));
        }


        public int[] shortestPath(int src) {
            PriorityQueue<Weight> priorityQueue = new PriorityQueue<>((Comparator.comparingInt(o -> o.w)));

            int[] dist = new int[v];
            Arrays.fill(dist, Integer.MAX_VALUE);


            priorityQueue.add(new Weight(src, 0));
            dist[src] = 0;

            while (!priorityQueue.isEmpty()) {

                Weight temp = priorityQueue.poll();

                int u = temp.v;

                for (Weight weight : adj[u]) {
                    int v = weight.v;
                    int w = weight.w;

                    if (dist[v] > dist[u] + w) {

                        if (dist[v] != Integer.MAX_VALUE) {
                            priorityQueue.remove(new Weight(v, dist[v]));
                        }


                        dist[v] = dist[u] + w;
                        priorityQueue.add(new Weight(v, dist[v]));
                    }
                }
            }
            return dist;
        }
    }


}
