package Java.companyWise.intuit.reversegear;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 2019-09-07
 * Description:https://www.geeksforgeeks.org/minimum-edges-reverse-make-path-source-destination/
 * <p>
 * Given a directed graph and a source node and destination node, we need to find how many edges we need to
 * reverse in order to make at least 1 path from source node to destination node.
 */
public class ReverseGearReverseEdgesInGraphPath {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        Graph graph = new Graph(n);

        for (int i = 0; i < m; i++) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();

            graph.add(source, destination, 0);
            graph.add(destination, source, 1);

        }
        System.out.println(minReverse(graph, n));
    }

    public static int minReverse(Graph graph, int n) {

        final int[] reverse = graph.shortestPath(0);

        if (reverse[n - 1] != Integer.MAX_VALUE)
            return reverse[n - 1];
        return Integer.MIN_VALUE;

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
