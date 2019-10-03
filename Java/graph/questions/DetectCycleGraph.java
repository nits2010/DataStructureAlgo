package Java.graph.questions;

import Java.UnionFindDisjointSets;
import Java.graph.graph.IGraph;
import Java.graph.graph.types.DirectedGraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-23
 * Description:
 */


public class DetectCycleGraph {

    /**
     * *  0-> 1
     * *  1-> 2
     * *  2-> 0
     * *  2 ->1
     *
     * @param vertices
     * @return
     */
    private static IGraph getGraph(int vertices, int[][] edgs) {
        IGraph graph = new DirectedGraph(vertices);

        for (int[] edg : edgs) {
            graph.addEdge(edg[0], edg[1]);
        }


        return graph;
    }

    public static void main(String[] args) {
        test(getGraph(4, new int[][]{{0, 1}, {1, 2}}));
        test(getGraph(4, new int[][]{{0, 1}, {1, 2}, {2, 3}}));
        test(getGraph(3, new int[][]{{0, 1}, {1, 2}, {2, 0}, {2, 1}}));
        test(getGraph(4, new int[][]{{0, 1}, {1, 2}, {2, 3}}));
    }

    private static void test(IGraph graph) {
        System.out.println("\n Graph :\n" + graph.print());

        System.out.println(" DFS : " + detectCycleDFS(graph.getVertices(), graph.getAdjList()));
        System.out.println(" Khan Algo : " + detectCycleKhanAlgo(graph.getVertices(), graph.getAdjList()));
        System.out.println(" Disjoint Set : " + detectCycleDisjointSet(graph.getVertices(), graph.getAdjList()));

    }


    public static boolean detectCycleKhanAlgo(final int vertices, final List<Integer>[] adjacencyList) {
        return DetectCycleKhanAlgo.detectCycle(vertices, adjacencyList);

    }

    public static boolean detectCycleDFS(final int vertices, final List<Integer>[] adjacencyList) {
        return DetectCycleDFS.detectCycle(vertices, adjacencyList);
    }

    public static boolean detectCycleDisjointSet(final int vertices, final List<Integer>[] adjacencyList) {
        return DetectCycleDisjointSets.detectCycle(vertices, adjacencyList);
    }

}


/**
 * {@link Java.UnionFindDisjointSets}
 * We can use disjoint sets algorithm to find cycle.
 * Algorithm:
 * 1. Assume all vertices are in different disjoint sets
 * 2. Keep applying the find and union on all the edges
 * 3. At any moment, you find for any two nodes of a edge, has same parent, then a cycle exist.
 * <p>
 * <p>
 * Graph
 * *  0-> 1
 * *  1-> 2
 * *  2-> 0
 * *  2 ->1
 * Initially, all slots of parent array are initialized to -1 (means there is only one item in every subset).
 * <p>
 * 0   1   2
 * -1 -1  -1
 * Now process all edges one by one.
 * <p>
 * Edge 0-1: Find the subsets in which vertices 0 and 1 are. Since they are in different subsets, we take the union of them. For taking the union, either make node 0 as parent of node 1 or vice-versa.
 * <p>
 * 0   1   2    <----- 1 is made parent of 0 (1 is now representative of subset {0, 1})
 * 1  -1  -1
 * Edge 1-2: 1 is in subset 1 and 2 is in subset 2. So, take union.
 * <p>
 * 0   1   2    <----- 2 is made parent of 1 (2 is now representative of subset {0, 1, 2})
 * 1   2  -1
 * Edge 0-2: 0 is in subset 2 and 2 is also in subset 2. Hence, including this edge forms a cycle.
 * <p>
 * How subset of 0 is same as 2?
 * 0->1->2 // 1 is parent of 0 and 2 is parent of 1
 */
class DetectCycleDisjointSets {

    public static boolean detectCycle(final int vertices, final List<Integer>[] adjacencyList) {

        if (vertices == 0 || adjacencyList == null || adjacencyList.length == 0)
            return false;

        //1. Assume all vertices are in different disjoint sets
        UnionFindDisjointSets disjointSet = new UnionFindDisjointSets(vertices);

        //Iterate all the adj list
        for (int u = 0; u < vertices; u++) {

            for (int v : adjacencyList[u]) {

                if (!disjointSet.unionBoth(u, v))
                    return true;

            }

        }

        return false;

    }

}


class DetectCycleKhanAlgo {
    public static boolean detectCycle(final int vertices, final List<Integer>[] adjacencyList) {
        List<Integer> top = new ArrayList<>(vertices);


        //count inDegree
        int[] inDegree = new int[vertices];


        for (int i = 0; i < vertices; i++) {

            for (Integer adj : adjacencyList[i]) {
                inDegree[adj]++;
            }
        }


        Queue<Integer> queue = new LinkedList<>();

        //push all 0 degree elements
        for (int i = 0; i < vertices; i++)
            if (inDegree[i] == 0)
                queue.offer(i);


        //Process one by one
        while (!queue.isEmpty()) {

            int current = queue.poll();
            top.add(current);

            for (Integer adj : adjacencyList[current]) {
                inDegree[adj]--;
                if (inDegree[adj] == 0)
                    queue.offer(adj);

            }
        }


        return top.size() != vertices;

    }
}

class DetectCycleDFS {

    public static boolean detectCycle(final int vertices, final List<Integer>[] adjacencyList) {

        int visited[] = new int[vertices]; //initially all are 0

        /**
         * Visit each vertices
         */
        for (int c = 0; c < vertices; c++) {
            //Visit those which has not visit yet

            if (visited[c] != -1 && detectCycle(adjacencyList, c, visited))
                return true;

        }
        return false;
    }


    /**
     * DFS
     *
     * @param adjList
     * @param vertex
     * @param visited
     * @return
     */
    private static boolean detectCycle(List<Integer>[] adjList, int vertex, int[] visited) {

        /**
         * If visited already, then there is a cycle
         */
        if (visited[vertex] == 1)
            return true;

        /**
         * This means, that this vertex has been covered
         */
        if (visited[vertex] == -1)
            return false;

        visited[vertex] = 1;

        for (Integer c : adjList[vertex])
            if (detectCycle(adjList, c, visited))
                return true;

        /**
         * Mark this vertex as covered
         */
        visited[vertex] = -1;

        return false;
    }

}