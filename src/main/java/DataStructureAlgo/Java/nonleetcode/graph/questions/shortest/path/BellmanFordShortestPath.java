package DataStructureAlgo.Java.nonleetcode.graph.questions.shortest.path;

import  DataStructureAlgo.Java.nonleetcode.graph.graph.IWeightedGraph;
import  DataStructureAlgo.Java.nonleetcode.graph.graph.types.Edges;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 2019-09-08
 * Description:  https://www.geeksforgeeks.org/bellman-ford-algorithm-dp-23/

https://leetcode.com/discuss/study-guide/4811631/Bellman-Ford-Algorithm

 * Given a graph and a source vertex src in graph, find shortest paths from src to all vertices in the given graph. The graph may contain negative weight edges.
 * We have discussed Dijkstra’s algorithm for this problem. {@link DijkstraShortestPath} algorithm is a Greedy algorithm and time complexity is O(VLogV) (with the use of Fibonacci heap).
 * {@link DijkstraShortestPath} doesn’t work for Graphs with negative weight edges, Bellman-Ford works for such graphs. Bellman-Ford is also simpler than {@link DijkstraShortestPath} and suites well
 * for distributed systems. But time complexity of Bellman-Ford is O(VE), which is more than Dijkstra.
 * <p>
 * Algorithm:
 * 1) This step initializes distances from source to all vertices as infinite and distance to source itself as 0.
 * Create an array dist[] of size |V| with all values as infinite except dist[src] where src is source vertex.
 * <p>
 * 2) This step calculates shortest distances. Do following |V|-1 times where |V| is the number of vertices in given graph.
 * …..a) Do following for each edge u-v
 * ………………If dist[v] > dist[u] + weight of edge uv, then update dist[v]
 * ………………….dist[v] = dist[u] + weight of edge uv
 * <p>
 * Time Complexity: O(V*E)
 */
public class BellmanFordShortestPath implements IShortestPath {


    @Override
    public List<Edges> shortestPath(IWeightedGraph graph, int source) {
        if (graph.getAdjList() == null || graph.getAdjList().length == 0)
            return Collections.EMPTY_LIST;

        final int infinity = Integer.MAX_VALUE;
        final double costs[] = new double[graph.getVertices()];
        Arrays.fill(costs, infinity);

        final int vertices = graph.getVertices();
        final List<Edges>[] adjList = graph.getAdjList();
        final List<Edges> shortestPath = new ArrayList<>();

        costs[source] = 0;
        for (int i = 0; i <vertices; i++) {


            for (Edges edge : adjList[i]) {

                int u = edge.source;
                int v = edge.destination;
                double w = edge.weight;


                //if the old cost is not infinity && new cost is smaller then update the it
                if (costs[u] != infinity && costs[u] + w < costs[v]) {

                    costs[v] = costs[u] + w;
                }

            }

        }

        for (int i = 0; i < vertices; i++)
            shortestPath.add(new Edges(source, i, costs[i]));


        return shortestPath;
    }
}
