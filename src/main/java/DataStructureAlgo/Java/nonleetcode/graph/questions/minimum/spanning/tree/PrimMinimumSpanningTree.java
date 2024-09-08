package DataStructureAlgo.Java.nonleetcode.graph.questions.minimum.spanning.tree;

import DataStructureAlgo.Java.nonleetcode.graph.graph.IWeightedGraph;
import DataStructureAlgo.Java.nonleetcode.graph.graph.types.Edges;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 2019-09-08
 * Description:
 * [minimum spanning tree (MST) doesn't apply to directed graphs]
 *
 * A group of edges that connects two sets of vertices in a graph is called cut in graph theory.
 * So, at every step of Prim’s algorithm, we find a cut (of two sets, one contains the vertices
 * already included in MST and the other contains the rest of the verices), pick the minimum weight edge from the cut and include
 * this vertex to MST Set (the set that contains already included vertices).
 * <p>
 * How does Prim’s Algorithm Work? The idea behind Prim’s algorithm is straightforward; a spanning tree means all vertices must be connected.
 * So the two disjointed subsets (discussed above) of vertices must be connected to make a Spanning Tree. And they must be connected
 * with the minimum weight edge to make it a Minimum Spanning Tree.
 * <p>
 * Algorithm
 * 1) Create a set mstSet that keeps track of vertices already included in MST.
 * 2) Assign a cost value to all vertices in the input graph. Initialize all key values as INFINITE.
 * Assign key value as 0 for the first vertex so that it is picked first.
 * 3) While mstSet does not include all vertices
 * ….a) Pick a vertex u which is not there in mstSet and has 'minimum cost' .
 * ….b) Include u to mstSet.
 * ….c) Update cost value of all adjacent vertices of u. To update the key values,
 * iterate through all adjacent vertices. For every adjacent vertex v, if the weight of edge u-v is less than the previous key value of v,
 * update the key value as weight of u-v
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * Something is wrong with code
 */
public class PrimMinimumSpanningTree implements IMinimumSpanningTree {

    final class Node {

        int source;
        int vertex;
        double cost;

        public Node(int source, int vertex, double cost) {
            this.vertex = vertex;
            this.cost = cost;
            this.source = source;
        }

        public Node() {
        }

        public Node(int v) {
            this.vertex = v;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return vertex == node.vertex;
        }

        @Override
        public int hashCode() {
            return vertex;
        }


        @Override
        public String toString() {
            return "Node{" +
                    "source=" + source +
                    ", vertex=" + vertex +
                    ", cost=" + cost +
                    '}';
        }
    }

    @Override
    public List<Edges> mst(IWeightedGraph graph) {

        if (graph.getAdjList() == null || graph.getAdjList().length == 0)
            return new LinkedList<>();


        final int INFINITE = Integer.MAX_VALUE;


        //Vertex set, sorted based on cost. This contains all vertex
        //1) Create a set mstSet that keeps track of vertices already included in MST.
        final PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingDouble(o -> o.cost));

        final List<Edges>[] edges = graph.getAdjList();
        final List<Edges> mst = new LinkedList<>();
        final int vertices = graph.getVertices();

        //set which contains all the "vertex" which are part of our mst, max size would be 'vertices'
        final boolean[] mstSet = new boolean[vertices];

        final double[] edgeCosts = new double[vertices];
        int[] parent = new int[vertices];

        //2) Assign a cost value to all vertices in the input graph. Initialize all cost values as INFINITE.
        Arrays.fill(edgeCosts, INFINITE);
        Arrays.fill(parent, -1);

        final int source = 0;
        edgeCosts[source] = 0; //cost of source vertex is 0, to pick it first


        pq.add(new Node(source, source, 0));

        //3) While mstSet does’t include all vertices
        while (!pq.isEmpty()) {

            final Node u = pq.poll();

            //….a) Pick a vertex 'u' which is not there in mstSet and has minimum cost value.
            if (!mstSet[u.vertex]) {

                //….b) Include 'u' to mstSet.
                mstSet[u.vertex] = true;


                //….c) Update cost value of all adjacent vertices of u. To update the key values,
                // iterate through all adjacent vertices. For every adjacent vertex v,
                // if the weight of edge u-v is less than the previous key value of v,
                // update the key value as weight of u-v
                for (Edges edge : edges[u.vertex]) {

                    int v = edge.destination;
                    if (!mstSet[v] && edgeCosts[v] > edge.weight) {

                        //update cost
                        edgeCosts[v] = edge.weight;
                        pq.offer(new Node(u.vertex, v, edgeCosts[v]));

                        //cache the parent to process it later
                        parent[v] = u.vertex;

                    }


                }

            }

        }

        for (int i = 1; i < vertices; i++)
            if (parent[i] != -1) {
                mst.add(new Edges(parent[i], i, edgeCosts[i]));
            }


        return mst;
    }
}
