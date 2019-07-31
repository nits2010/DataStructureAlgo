package Java.companyWise.facebook;

import Java.HelpersToPrint.HelperToPrint;

import java.util.HashSet;
import java.util.Set;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-31
 * Description: https://www.geeksforgeeks.org/bipartite-graph/
 * <p>
 * Given a random graph (undirected/directed /tree anything). You need to find that can we divide this graph in two different sets of
 * vertices such that there is no edge exists which connects two nodes within a set, but a edge can be there which connects two nodes in different sets.
 * <p>
 * See different approaches and solution below;
 * 1. Backtracking
 * 2. DFS
 * 3. BFS
 * 4. Coloring Problem
 */
public class GraphDivideTwoSetsOrBipartiteGraph {

    public static void main(String[] args) {

        /**
         **            (3)---(2)
         **            |   / |
         **            |  /  |
         **            | /   |
         **           (0)---(1)
         */
        int graph[][] = {
                {0, 1, 1, 1},
                {1, 0, 1, 0},
                {1, 1, 0, 1},
                {1, 0, 1, 0},
        };

        /**
         **            (3)---(2)
         **            |     |
         **            |     |
         **            |     |
         **           (0)---(1)
         */
        int graph2[][] = {
                {0, 1, 0, 1},
                {1, 0, 1, 0},
                {0, 1, 0, 1},
                {1, 0, 1, 0}
        };


        /**
         **            (3)  (2)
         **            |     |
         **            |     |
         **            |     |
         **           (0)---(1)
         */
        int graph3[][] = {
                {0, 1, 0, 1},
                {1, 0, 1, 0},
                {0, 1, 0, 0},
                {1, 0, 0, 0}
        };

        /**
         **            (3)  (2)
         **                 |
         **                 |
         **                 |
         **           (0)---(1)
         */
        int graph4[][] = {
                {0, 1, 0, 0},
                {1, 0, 1, 0},
                {0, 1, 0, 0},
                {0, 0, 0, 0}
        };

        test(graph);
        test(graph2);
        test(graph3);
        test(graph4);

    }

    private static void test(int[][] graph) {
        System.out.println("Given Graph");
        HelperToPrint.print2DArray(graph);

        System.out.println(BipartiteGraphDFSBackTracking.isBipartiteGraph(graph));

    }
}

/**
 * We can simply DFS the given graph and see can we divide the graph in two sets such that it follow the given constraints
 * <p>
 * Our choices: A vertices can be part of group 'A' if and only if, there is no edge between this vertices and the vertices in the given set otherwise it goes in different set
 * Our Constraints: There should not be edge exists which connects two nodes within a set, but a edge can be there which connects two nodes in different sets.
 * Our Goal: We need two different sets which contains all the vertices of given graph
 * Our Boundary:
 * 1. Either we left with no vertices and both are divided in two sets (set1.size() + set2.size() = V ) - Success
 * 2. Otherwise backtrack
 * <p>
 * <p>
 * Runtime:
 * O(V*V*2) = O(V^2)
 */
class BipartiteGraphDFSBackTracking {


    /**
     * @param graph
     * @return : True if we can divide the graph in two sets with given constraints
     * otherwise false
     */
    public static boolean isBipartiteGraph(int graph[][]) {

        //An empty graph can't be divided in two sets
        if (null == graph || graph.length == 0 || graph[0].length == 0)
            return false;

        final Set<Integer> set1 = new HashSet<>();
        final Set<Integer> set2 = new HashSet<>();

        boolean result = isBipartiteGraph(graph, set1, set2, 0);

        System.out.println("Set 1 " + set1 + " set 2: " + set2);
        return result;


    }

    private static boolean isBipartiteGraph(int[][] graph, Set<Integer> set1, Set<Integer> set2, int v) {
        /**
         *  Either we left with no vertices and both are divided in two sets (set1.size() + set2.size() = V ) - Success
         */
        if (v == graph.length)
            return true;


        /**
         * Self loop?
         * Not possible then
         */
        if (graph[v][v] == 1) {
            return false;

        }


        /**
         * Our choices:
         *  A vertices can be part of group 'A' if and only if, there is no edge between this vertices and the vertices in the given set otherwise it goes in different set
         *
         *  Consider this vertex and put into a set 1 and check
         */

        set1.add(v);

        /**
         * Our Goal: We need two different sets which contains all the vertices of given graph
         */
        if (isSafe(graph, set1, v) && isBipartiteGraph(graph, set1, set2, v + 1))
            return true;

        //Backtrack
        set1.remove(v);

        /**
         *  A vertices can be part of group 'A' if and only if, there is no edge between this vertices and the vertices in the given set otherwise it goes in different set
         *
         *  Consider this vertex and put into a set 2 and check
         */

        set2.add(v);

        /**
         * Our Goal: We need two different sets which contains all the vertices of given graph
         */
        if (isSafe(graph, set2, v) && isBipartiteGraph(graph, set1, set2, v + 1))
            return true;

        //Backtrack
        set2.remove(v);


        return false;
    }

    private static boolean isSafe(int[][] graph, Set<Integer> shouldNotExistInThisSet, int v) {

        for (int i = 0; i < graph[0].length; i++) {
            if (graph[v][i] == 1 && shouldNotExistInThisSet.contains(i))
                return false;
        }

        return true;
    }
}


class BipartiteGraphBFSBackTracking {

    /**
     * @param graph
     * @return : True if we can divide the graph in two sets with given constraints
     * otherwise false
     */
    public static boolean isBipartiteGraph(int graph[][]) {

        //An empty graph can't be divided in two sets
        if (null == graph || graph.length == 0 || graph[0].length == 0)
            return false;

        final Set<Integer> set1 = new HashSet<>();
        final Set<Integer> set2 = new HashSet<>();


        System.out.println("Set 1 " + set1 + " set 2: " + set2);


    }

}