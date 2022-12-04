package DataStructureAlgo.Java.LeetCode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-01
 * Description: https://leetcode.com/problems/is-graph-bipartite/
 * <p>
 * Given an undirected graph, return true if and only if it is bipartite.
 * <p>
 * Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that every edge in the graph has one node in A and another node in B.
 * <p>
 * The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.  Each node is an integer between 0 and graph.length - 1.  There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.
 * <p>
 * Example 1:
 * Input: [[1,3], [0,2], [1,3], [0,2]]
 * Output: true
 * Explanation:
 * The graph looks like this:
 * 0----1
 * |    |
 * |    |
 * 3----2
 * We can divide the vertices into two groups: {0, 2} and {1, 3}.
 * Example 2:
 * Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
 * Output: false
 * Explanation:
 * The graph looks like this:
 * 0----1
 * | \  |
 * |  \ |
 * 3----2
 * We cannot find a way to divide the set of nodes into two independent subsets.
 * <p>
 * <p>
 * Note:
 * <p>
 * graph will have length in range [1, 100].
 * graph[i] will contain integers in range [0, graph.length - 1].
 * graph[i] will not contain i or duplicate values.
 * The graph is undirected: if any element j is in graph[i], then i will be in graph[j].
 * <p>
 * {@link DataStructureAlgo.Java.companyWise.facebook.GraphDivideTwoSetsOrBipartiteGraph}
 * In this question, the input is in different form only.
 */
public class IsGraphBipartite {
    public static void main(String[] args) {
        System.out.println("DFS......\n");
        System.out.println(DFS.isBipartite(new int[][]{{1, 3}, {0, 2}, {1, 3}, {0, 2}}));
        System.out.println(DFS.isBipartite(new int[][]{{1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}}));
        System.out.println(DFS.isBipartite(new int[][]{{}, {3}, {}, {1}, {}}));
        System.out.println(DFS.isBipartite(new int[][]{{}, {2, 4, 6}, {1, 4, 8, 9}, {7, 8}, {1, 2, 8, 9}, {6, 9}, {1, 5, 7, 8, 9}, {3, 6, 9}, {2, 3, 4, 6, 9}, {2, 4, 5, 6, 7, 8}}));


        System.out.println("BFS......\n");
        System.out.println(BFS.isBipartite(new int[][]{{1, 3}, {0, 2}, {1, 3}, {0, 2}}));
        System.out.println(BFS.isBipartite(new int[][]{{1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}}));
        System.out.println(BFS.isBipartite(new int[][]{{}, {3}, {}, {1}, {}}));
        System.out.println(BFS.isBipartite(new int[][]{{}, {2, 4, 6}, {1, 4, 8, 9}, {7, 8}, {1, 2, 8, 9}, {6, 9}, {1, 5, 7, 8, 9}, {3, 6, 9}, {2, 3, 4, 6, 9}, {2, 4, 5, 6, 7, 8}}));


    }
}

/**
 * O(V+E)/O(V)
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Is Graph Bipartite?.
 * Memory Usage: 44.1 MB, less than 62.93% of Java online submissions for Is Graph Bipartite?.
 */
class DFS {

    public static boolean isBipartite(int[][] graph) {
        if (graph == null || graph.length == 0)
            return false;

        int V = graph.length;
        int color[] = new int[V];
        Arrays.fill(color, -1);

        for (int i = 0; i < V; i++) {
            if (color[i] == -1)
                if (!isBipartite(graph, color, 1, i))
                    return false;
        }


        return true;
    }

    private static boolean isBipartite(int[][] graph, int[] color, int assigned, int u) {

        /**
         * Has this vertex processed earlier?
         * if so, then this color should not be different from its source
         */
        if (color[u] != -1)
            return color[u] == assigned;

        //Take this vertex
        color[u] = assigned;

        for (int v : graph[u])
            if (!isBipartite(graph, color, 1 - assigned, v))
                return false;

        return true;


    }
}

/**
 * O(V+E)/ O(V)
 * Runtime: 1 ms, faster than 62.91% of Java online submissions for Is Graph Bipartite?.
 * Memory Usage: 44.4 MB, less than 62.93% of Java online submissions for Is Graph Bipartite?.
 */
class BFS {

    public static boolean isBipartite(int[][] graph) {
        if (graph == null || graph.length == 0)
            return false;

        int V = graph.length;
        int color[] = new int[V];
        Arrays.fill(color, -1);

        for (int u = 0; u < V; u++)
            if (color[u] == -1)
                if (!isBipartite(graph, u, color))
                    return false;


        return true;
    }

    private static boolean isBipartite(int graph[][], int start, int[] color) {

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        color[start] = 1;

        while (!queue.isEmpty()) {

            int u = queue.poll();

            for (int v : graph[u]) {

                if (color[v] == -1) {

                    color[v] = 1 - color[u];
                    queue.offer(v);
                } else if (color[u] == color[v])
                    return false;
            }
        }
        return true;
    }
}