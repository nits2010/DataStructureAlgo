package DataStructureAlgo.Java.nonleetcode.graph.questions.shortest.path;

import DataStructureAlgo.Java.nonleetcode.graph.graph.IWeightedGraph;
import DataStructureAlgo.Java.nonleetcode.graph.graph.types.Edges;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 2019-09-08
 * Description: https://www.geeksforgeeks.org/dijkstras-algorithm-for-adjacency-list-representation-greedy-algo-8/
 * <p>
 * 1) Create a Min Heap of size V where V is the number of vertices in the given graph.
 * Every node of min heap contains vertex number and distance value of the vertex.
 * 2) Initialize Min Heap with source vertex as root (the distance value assigned to source vertex is 0).
 * The distance value assigned to all other vertices is INF (infinite).
 * 3) While Min Heap is not empty, do following
 * …..a) Extract the vertex with minimum distance value node from Min Heap. Let the extracted vertex be u.
 * …..b) For every adjacent vertex v of u, check if v is in Min Heap. If v is in Min Heap and distance value is more
 *  than the weight of u-v plus distance value of u, then update the distance value of v.
 * <p>
 * Time Complexity: O(E*log(V))
 * The time complexity of the above code/algorithm looks O(V^2) as there are two nested while loops.
 * If we take a closer look, we can observe that the statements in the inner loop are executed O(V+E) times (similar to BFS).
 * The inner loop has decreaseKey() operation which takes O(LogV) time. So overall time complexity is O(E+V)*O(LogV) which is O((E+V)*LogV) = O(ELogV)
 * Space Complexity: O(V)
 * <p>
 * 
 * 
 * Excellent question. This is senior-level nuance.

---

## 1️⃣ When to Use `visited`

Use `visited` when:

* You only care about the **first time a node is finalized**
* After popping from heap, cost is guaranteed minimal
* You don’t need to relax nodes multiple times

Typical:

* Classic Dijkstra with no need to update better paths later

`visited` = finalization marker

---

## 2️⃣ When to Use `dist` (Distance Map)

Use `dist` when:

* A node might be reached multiple times with **better cost later**
* You must compare `newCost < dist[node]`
* You want standard Dijkstra relaxation

Typical:

* Sum-based shortest path
* Graphs with many alternative paths

`dist` = best-known cost tracker

---

### Key Distinction

If correctness depends on comparing old vs new costs → use `dist`.

If first pop guarantees optimal → `visited` alone is enough.

---

Now sharp test:

In THIS problem, could a cell ever be reached later with a strictly smaller cost than the first time it is popped?

Yes or no?

 * 
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

        if (graph.getAdjList() == null || graph.getAdjList().length == 0 || source >= graph.getVertices())
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

            if (node.cost > cost[u]){
                continue; 
            }
            
            settled.add(node.vertex);

            //…..b) For every adjacent vertex v of u, distance value is more
            for (Edges edges : adjList[u]) {

                if (settled.contains(edges.destination))
                    continue;

                int v = edges.destination;
                double weight = edges.weight;

                //if the weight of edge is negative, then dijkstra algo fails, hence no shortest path
                if(weight < 0)
                    return shortestPath;


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
