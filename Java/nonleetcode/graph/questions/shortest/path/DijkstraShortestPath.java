package Java.nonleetcode.graph.questions.shortest.path;

import Java.nonleetcode.graph.graph.IWeightedGraph;
import Java.nonleetcode.graph.graph.types.Edges;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-09-08
 * Description:https://www.geeksforgeeks.org/dijkstras-algorithm-for-adjacency-list-representation-greedy-algo-8/
 * <p>
 * 1) Create a Min Heap of size V where V is the number of vertices in the given graph.
 * Every node of min heap contains vertex number and distance value of the vertex.
 * 2) Initialize Min Heap with source vertex as root (the distance value assigned to source vertex is 0).
 * The distance value assigned to all other vertices is INF (infinite).
 * 3) While Min Heap is not empty, do following
 * …..a) Extract the vertex with minimum distance value node from Min Heap. Let the extracted vertex be u.
 * …..b) For every adjacent vertex v of u, check if v is in Min Heap. If v is in Min Heap and distance value is more
 * than weight of u-v plus distance value of u, then update the distance value of v.
 * <p>
 * Complexity: O(V*log(V))
 * <p>
 * https://www.dyclassroom.com/graph/detecting-negative-cycle-using-bellman-ford-algorithm
 */
public class DijkstraShortestPath implements IShortestPath {


    private static class CostNode {
        int vertex;
        double cost;

        public CostNode(int vertex, double cost) {
            this.vertex = vertex;
            this.cost = cost;
        }
    }

    @Override
    public List<Edges> shortestPath(IWeightedGraph graph, int source) {
        final List<Edges> shortestPath = new ArrayList<>();

        if (graph.getAdjList() == null || graph.getAdjList().length == 0)
            return shortestPath;

        final List<Edges>[] adjList = graph.getAdjList();


        final Set<Integer> settled = new HashSet<>();

        // 1) Create a Min Heap of size V where V is the number of vertices in the given graph.
        // * Every node of min heap contains vertex number and distance value of the vertex.
        final PriorityQueue<CostNode> pq = new PriorityQueue<>((Comparator.comparingDouble(o -> o.cost)));

        //To keep the distance of vertices distance[V] from source 's'
        final double[] cost = new double[graph.getVertices()];
        Arrays.fill(cost, Integer.MAX_VALUE);

        //distance of source to source is 0
        cost[source] = 0;

        pq.offer(new CostNode(source, 0));

        //3) While Min Heap is not empty, do following
        while (!pq.isEmpty() && settled.size() != graph.getVertices()) {

            //…..a) Extract the vertex with minimum distance value node from Min Heap. Let the extracted vertex be u.
            final CostNode node = pq.poll();
            int u = node.vertex;

            settled.add(node.vertex);

            //…..b) For every adjacent vertex v of u, distance value is more
            for (Edges edges : adjList[u]) {

                if (settled.contains(edges.destination))
                    continue;

                int v = edges.destination;
                double weight = edges.weight;

                if (cost[v] > cost[u] + weight)
                    cost[v] = cost[u] + weight;

                pq.offer(new CostNode(v, cost[v]));

            }

        }

        for (int i = 0; i < cost.length; i++)
            shortestPath.add(new Edges(source, i, cost[i]));


        return shortestPath;
    }
}
