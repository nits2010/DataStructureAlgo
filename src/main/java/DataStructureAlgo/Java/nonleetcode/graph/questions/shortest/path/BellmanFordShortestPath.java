package DataStructureAlgo.Java.nonleetcode.graph.questions.shortest.path;

import DataStructureAlgo.Java.nonleetcode.graph.graph.IWeightedGraph;
import DataStructureAlgo.Java.nonleetcode.graph.graph.types.Edges;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 2019-09-08
 * Description:
 * <a href="https://www.geeksforgeeks.org/bellman-ford-algorithm-dp-23/">...</a>
 * <a href="https://leetcode.com/discuss/study-guide/4811631/Bellman-Ford-Algorithm">...</a>
 * <p>
 * Given a graph and a source vertex src in graph, find the shortest paths from src to all vertices in the given graph. The graph may contain negative weight edges.
 * We have discussed Dijkstra’s algorithm for this problem. {@link DijkstraShortestPath} algorithm is a Greedy algorithm and time complexity is O(VLogV) (with the use of Fibonacci heap).
 * {@link DijkstraShortestPath} does’t work for Graphs with negative weight edges, Bellman-Ford works for such graphs. Bellman-Ford is also simpler than {@link DijkstraShortestPath} and suites well
 * for distributed systems. But time complexity of Bellman-Ford is O(VE), which is more than Dijkstra.
 * <p>
 * Algorithm:
 * 1) This step initializes distances from source to all vertices as infinite and distance to source itself as 0.
 * Create an array dist[] of size |V| with all values as infinite except dist[src] where src is source vertex.
 * <p>
 * 2) This step calculates shortest distances. Do the following |V|-1 times when |V| is the number of vertices in the given graph.
 * …..a) Do following for each edge u-v
 * ………………If dist[v] > dist[u] + weight of edge uv, then update dist[v]
 * ………………….dist[v] = dist[u] + weight of edge uv
 * <p>
 * Time Complexity: O(V*E)
 * Negative Cycle: https://www.dyclassroom.com/graph/detecting-negative-cycle-using-bellman-ford-algorithm
 * Bellman Ford Problems : https://leetcode.com/problem-list/aju0qcxh/
 */
public class BellmanFordShortestPath implements IShortestPath {


    @Override
    public List<Edges> shortestPath(IWeightedGraph graph, int source) {
        if (graph.getAdjList() == null || graph.getAdjList().length == 0 || source >= graph.getVertices())
            return new LinkedList<>();

        final int infinity = Integer.MAX_VALUE;
        final double[] distanceCost = new double[graph.getVertices()];
        Arrays.fill(distanceCost, infinity);

        final int vertices = graph.getVertices();
        final List<Edges> edgesList = graph.getEdgesList();
        final List<Edges> shortestPath = new ArrayList<>();

        distanceCost[source] = 0;

        // Relax all the edge number of vertices - 1 times
        for (int i = 0; i < vertices - 1; i++) {

            for(Edges edge : edgesList){
                int u = edge.source;
                int v = edge.destination;
                double w = edge.weight;


                //if the old cost is not infinity && new cost is smaller than update it
                if (distanceCost[u] != infinity
                        && distanceCost[v] > distanceCost[u] + w  ) {
                    distanceCost[v] = distanceCost[u] + w;
                }
            }

        }

        //if the graph doesn't contain any negative cycle, then we can't update distance Nth time
        for(Edges edge : edgesList){
            int u = edge.source;
            int v = edge.destination;
            double w = edge.weight;


            //if the old cost is not infinity && new cost is smaller than update it, now its negative weight cycle
            if (distanceCost[u] != infinity
                    && distanceCost[v] > distanceCost[u] + w ) {

                //the shortest path doesn't exist, hence empty list
               return shortestPath;
            }
        }


        for (int i = 0; i < vertices; i++)
            shortestPath.add(new Edges(source, i, distanceCost[i]));


        return shortestPath;
    }
}
