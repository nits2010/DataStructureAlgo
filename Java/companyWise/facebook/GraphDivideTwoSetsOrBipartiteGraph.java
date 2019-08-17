package Java.companyWise.facebook;

import Java.HelpersToPrint.Printer;

import java.util.*;

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
 * <p>
 * {@link Java.LeetCode.IsGraphBipartite}
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
        Printer.print(graph);

        System.out.println(BipartiteGraphDFSBackTracking.isBipartiteGraph(graph));

        System.out.println("Graph coloring : " + GraphColoringProblem.isColor(graph, 2));

        System.out.println(BipartiteGraphUsingGraphColoringApproach.BFS.isBipartiteGraph(graph));
        System.out.println(BipartiteGraphUsingGraphColoringApproach.DFS.isBipartiteGraph(graph));

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
 * Runtime: Each vertex has two choices hence => O(2^v)
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


/**
 * https://www.geeksforgeeks.org/m-coloring-problem-backtracking-5/
 * Motivation to the solution:
 * <p>
 * Given an undirected graph and a number m, determine if the graph can be colored with at most m colors such
 * that no two adjacent vertices of the graph are colored with the same color. Here coloring of a graph means the assignment of colors to all vertices.
 * <p>
 * Input:
 * 1) A 2D array graph[V][V] where V is the number of vertices in graph and graph[V][V] is adjacency matrix representation of the graph.
 * A value graph[i][j] is 1 if there is a direct edge from i to j, otherwise graph[i][j] is 0.
 * 2) An integer m which is the maximum number of colors that can be used.
 * <p>
 * Output:
 * An array color[V] that should have numbers from 1 to m. color[i] should represent the color assigned to the ith vertex.
 * The code should also return false if the graph cannot be colored with m colors.
 */
class GraphColoringProblem {


    public static boolean isColor(int graph[][], int colors) {

        /**
         * Empty graph can't be color with any number of colors
         */
        if (null == graph || ((graph.length == 0 || graph[0].length == 0) && colors != 0))
            return false;

        int V = graph.length;
        int color[] = new int[V];
        Arrays.fill(color, -1);

        return isColor(graph, colors, 0, color);


    }

    /**
     * We'll use backtracking approach to solve this problem.
     * <p>
     * Our choice: We can assign any of the color to the vertex from "m" colors.
     * Our Constrains: We can not assign a color to this vertex which already been assigned to its neighbour
     * Our Goal: We need to find does its possible to assign all different colors without breaking rule and we ran complete on graph
     *
     * @param graph
     * @param m
     * @param u
     * @return
     */
    private static boolean isColor(int[][] graph, int m, int u, int colors[]) {


        /**
         * Our Goal: Is whole graph colored?
         */
        if (u == graph.length)
            return true;


        /**
         * Our choice: We can assign any of the color to the vertex from "m" colors.
         */

        for (int c = 0; c < m; c++) {

            /**
             * Our Constrains: We can not assign a color to this vertex which already been assigned to its neighbour
             */

            if (isSafe(graph, u, c, colors)) {

                /**
                 * choose this color
                 */
                colors[u] = c;

                /**
                 * Try its neighbour
                 */
                if (isColor(graph, m, u + 1, colors))
                    return true;

                /**
                 * Backtrack
                 */
                colors[u] = -1;

            }
        }


        return false;
    }

    private static boolean isSafe(int[][] graph, int u, int color, int colors[]) {

        for (int v = 0; v < graph.length; v++) {

            /**
             * if there is edge between these two vertex and then the color of this vertex can't be same as neighbour
             */
            if (graph[u][v] == 1 && colors[v] == color)
                return false;
        }

        return true;

    }
}


/**
 * https://www.geeksforgeeks.org/bipartite-graph/
 * <p>
 * This problem can be solve using graph coloring problem.
 * In Graph coloring problem, we need to find that how many different color we require to provide a color to each vertex such that no two neighbour has same color.
 * <p>
 * <p>
 * Here, we'll have at most two color to choose (below implementation use 1 and 0 for two different color). If we can make the graph colorful with this two different color following constraint
 * then this is possible otherwise not.
 */
class BipartiteGraphUsingGraphColoringApproach {

    static class BFS {

        /**
         * @param graph
         * @return : True if we can divide the graph in two sets with given constraints
         * otherwise false
         */
        public static boolean isBipartiteGraph(int graph[][]) {

            //An empty graph can't be divided in two sets
            if (null == graph || graph.length == 0 || graph[0].length == 0)
                return false;
            /**
             * Track the current vertex is part of which set and has been assigned to either a set or not. i.e. has we assigned any color to this vertex or not?
             * Two color 1 and 0
             */
            int color[] = new int[graph.length];
            Arrays.fill(color, -1);

            /**
             * To accomplish all the components in graph (either connected or not )
             */
            for (int v = 0; v < graph.length; v++)
                if (color[v] == -1) //if this vertex has not been assigned a color
                    if (!isBipartiteGraph(graph, 0, color))
                        return false;


            return true;

        }

        /**
         * Since each vertex can be have color 1 or 0. Check does this possible.
         *
         * @param graph
         * @param start
         * @return
         */
        private static boolean isBipartiteGraph(int graph[][], int start, int color[]) {


            /**
             * Mark this vertex and choose a color
             */
            color[start] = 1;

            Queue<Integer> queue = new LinkedList<>();
            queue.offer(start);

            while (!queue.isEmpty()) {

                int source = queue.poll();

                /**
                 * Self loop?
                 * Not possible then
                 */
                if (graph[source][source] == 1)
                    return false;

                /**
                 * Run all its vertex
                 */
                for (int dest = 0; dest < graph.length; dest++) {

                    /**
                     *  Does there any edge?
                     */
                    if (graph[source][dest] == 1) {

                        /**
                         * does this vertex hasn't been chosen earlier?
                         */
                        if (color[dest] == -1) {

                            //Since the current vertex (source) is already been colored, then its neighbour should be colored different color
                            color[dest] = 1 - color[source];

                            queue.offer(dest);

                        } else if (color[dest] == color[source]) //if source and des are both part of same color, not possible then
                            return false;

                    }
                }


            }


            return true;
        }
    }

    static class DFS {

        /**
         * @param graph
         * @return : True if we can divide the graph in two sets with given constraints
         * otherwise false
         */
        public static boolean isBipartiteGraph(int graph[][]) {

            //An empty graph can't be divided in two sets
            if (null == graph || graph.length == 0 || graph[0].length == 0)
                return false;
            /**
             * Track the current vertex is part of which set and has been assigned to either a set or not.
             * False means this vertex has not been part of any set.
             */
            int vSets[] = new int[graph.length];
            Arrays.fill(vSets, -1);

            return (isBipartiteGraph(graph, 0, 1, vSets));


        }

        private static boolean isBipartiteGraph(int[][] graph, int u, int assigned, int[] color) {

            /**
             * Self loop?
             * Not possible then
             */
            if (graph[u][u] == 1)
                return false;

            /**
             * Has this vertex processed earlier?
             * if so, then this color should not be different from its source
             */
            if (color[u] != -1 && color[u] != assigned)
                return false;

            //Take this vertex
            color[u] = assigned;

            //now its all neighbour should be in different sets
            boolean isBipartiteGraph = true;
            for (int v = 0; v < graph.length; v++) {

                //if there is a edge from source
                if (graph[u][v] == 1) {
                    //if not processed
                    if (color[v] == -1) //process it with different color from source(assigned)
                        isBipartiteGraph &= isBipartiteGraph(graph, v, 1 - assigned, color);

                    //all its neighbour should be on different sets then
                    if (color[v] != -1 && color[v] != 1 - assigned)
                        return false;


                }

                if (!isBipartiteGraph)
                    return false;
            }


            return true;
        }


    }
}
