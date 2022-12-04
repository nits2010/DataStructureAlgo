package DataStructureAlgo.Java.nonleetcode.graph.questions.minimum.spanning.tree;

import  DataStructureAlgo.Java.nonleetcode.graph.graph.IWeightedGraph;
import  DataStructureAlgo.Java.nonleetcode.graph.graph.types.Edges;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 2019-09-08
 * Description:
 * A group of edges that connects two set of vertices in a graph is called cut in graph theory.
 * So, at every step of Prim’s algorithm, we find a cut (of two sets, one contains the vertices
 * already included in MST and other contains rest of the verices), pick the minimum weight edge from the cut and include
 * this vertex to MST Set (the set that contains already included vertices).
 * <p>
 * How does Prim’s Algorithm Work? The idea behind Prim’s algorithm is simple, a spanning tree means all vertices must be connected.
 * So the two disjoint subsets (discussed above) of vertices must be connected to make a Spanning Tree. And they must be connected
 * with the minimum weight edge to make it a Minimum Spanning Tree.
 * <p>
 * Algorithm
 * 1) Create a set mstSet that keeps track of vertices already included in MST.
 * 2) Assign a cost value to all vertices in the input graph. Initialize all key values as INFINITE.
 * Assign key value as 0 for the first vertex so that it is picked first.
 * 3) While mstSet doesn’t include all vertices
 * ….a) Pick a vertex u which is not there in mstSet and has minimum cost .
 * ….b) Include u to mstSet.
 * ….c) Update cost value of all adjacent vertices of u. To update the key values,
 * iterate through all adjacent vertices. For every adjacent vertex v, if weight of edge u-v is less than the previous key value of v,
 * update the key value as weight of u-v
 * <p>
 * <p>
 *
 *
 *
 * Something is wrong with code
 */
public class PrimMinimumSpanningTree implements IMinimumSpanningTree {


    @Override
    public List<Edges> mst(IWeightedGraph graph) {

        if (graph.getAdjList() == null || graph.getAdjList().length == 0)
            return Collections.EMPTY_LIST;


        final int INFINITE = Integer.MAX_VALUE;

        final class Node {

            int vertex;
            double cost;

            public Node(int vertex, double cost) {
                this.vertex = vertex;
                this.cost = cost;
            }

            public Node() {
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
                        "vertex=" + vertex +
                        ", cost=" + cost +
                        '}';
            }
        }


        //Vertex set, sorted based on cost. This contains all the vertex
        //1) Create a set mstSet that keeps track of vertices already included in MST.
        final PriorityQueue<Node> set = new PriorityQueue<>(((o1, o2) -> (int) (o1.cost - o2.cost)));

        final List<Edges>[] edges = graph.getAdjList();
        final List<Edges> mst = new LinkedList<>();

        final int vertices = graph.getVertices();

        //set which contains all the vertex which are been part of our mst, max size would be 'vertices'
        final Set<Node> mstSet = new HashSet<>(vertices);

        final Node[] costs = new Node[vertices];
        int parent[] = new int[vertices];

        //2) Assign a cost value to all vertices in the input graph. Initialize all cost values as INFINITE.
        for (int i = 0; i < vertices; i++) {
            costs[i] = new Node(i, INFINITE);
            parent[i] = -1;
        }

        final int source = 0;
        parent[source] = 0;
        costs[source].cost = 0; //cost of source vertex is 0, to pick it first

        //add all this costs node to tree set
//        for (int i = 0; i < vertices; i++)
//            set.add(costs[i]);

        set.add(costs[source]);


        //3) While mstSet doesn’t include all vertices
        while (!set.isEmpty()) {

            final Node u = set.poll();

            //….a) Pick a vertex u which is not there in mstSet and has minimum cost value.
            if (!mstSet.contains(u)) {

                //….b) Include u to mstSet.
                mstSet.add(u);

                //….c) Update cost value of all adjacent vertices of u. To update the key values,
                for (Edges edge : edges[u.vertex]) {

                    if (!mstSet.contains(edge.destination)) {

                        if (costs[edge.destination].cost > edge.weight) {

                            set.remove(costs[edge.destination]);

                            costs[edge.destination].cost = edge.weight;

                            set.add(costs[edge.destination]);

                            parent[edge.destination] = u.vertex;

                        }

                    }
                }

            }

        }

        for (int i = 1; i < vertices; i++)
            mst.add(new Edges(parent[i], i, costs[i].cost));


        return mst;
    }
}
