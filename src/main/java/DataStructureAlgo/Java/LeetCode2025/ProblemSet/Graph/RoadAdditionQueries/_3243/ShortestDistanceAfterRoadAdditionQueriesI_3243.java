package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Graph.RoadAdditionQueries._3243;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.nonleetcode.graph.questions.shortest.path.BellmanFordShortestPath;
import DataStructureAlgo.Java.nonleetcode.graph.questions.shortest.path.DijkstraShortestPath;


/**
 * Author: Nitin Gupta
 * Date: 11/27/2024
 * Question Title: 3243. Shortest Distance After Road Addition Queries I
 * Link:
 * Description: You are given an integer n and a 2D integer array queries.
 * <p>
 * There are n cities numbered from 0 to n - 1. Initially, there is a unidirectional road from city i to city i + 1 for all 0 <= i < n - 1.
 * <p>
 * queries[i] = [ui, vi] represents the addition of a new unidirectional road from city ui to city vi. After each query, you need to find the length of the shortest path from city 0 to city n - 1.
 * <p>
 * Return an array answer where for each i in the range [0, queries.length - 1], answer[i] is the length of the shortest path from city 0 to city n - 1 after processing the first i + 1 queries.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 5, queries = [[2,4],[0,2],[0,4]]
 * <p>
 * Output: [3,2,1]
 * <p>
 * Explanation:
 * <p>
 * <p>
 * <p>
 * After the addition of the road from 2 to 4, the length of the shortest path from 0 to 4 is 3.
 * <p>
 * <p>
 * <p>
 * After the addition of the road from 0 to 2, the length of the shortest path from 0 to 4 is 2.
 * <p>
 * <p>
 * <p>
 * After the addition of the road from 0 to 4, the length of the shortest path from 0 to 4 is 1.
 * <p>
 * Example 2:
 * <p>
 * Input: n = 4, queries = [[0,3],[0,2]]
 * <p>
 * Output: [1,1]
 * <p>
 * Explanation:
 * <p>
 * <p>
 * <p>
 * After the addition of the road from 0 to 3, the length of the shortest path from 0 to 3 is 1.
 * <p>
 * <p>
 * <p>
 * After the addition of the road from 0 to 2, the length of the shortest path remains 1.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 3 <= n <= 500
 * 1 <= queries.length <= 500
 * queries[i].length == 2
 * 0 <= queries[i][0] < queries[i][1] < n
 * 1 < queries[i][1] - queries[i][0]
 * There are no repeated roads among the queries.
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link DijkstraShortestPath} {@link BellmanFordShortestPath}
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @Graph
 * @Breadth-FirstSearch
 * @medium <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link Solution_BFS}
 */

public class ShortestDistanceAfterRoadAdditionQueriesI_3243 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[][]{{0, 2}, {0, 3}}, 6, new int[]{4, 3}));
        tests.add(test(new int[][]{{2, 4}, {0, 2}, {0, 4}}, 5, new int[]{3, 2, 1}));
        tests.add(test(new int[][]{{0, 3}, {0, 2}}, 4, new int[]{1, 1}));

        tests.add(test(new int[][]{{10, 12}, {6, 12}}, 15, new int[]{13, 9}));
        tests.add(test(new int[][]{{0, 6}, {4, 12}}, 14, new int[]{8, 6}));
        tests.add(test(new int[][]{{3, 6}, {0, 6}, {2, 5}, {0, 2}}, 7, new int[]{4, 1, 1, 1}));
        tests.add(test(new int[][]{{4, 7}, {2, 4}, {1, 6}, {2, 7}, {0, 2}, {2, 5}}, 8, new int[]{5, 4, 3, 3, 2, 2}));
        tests.add(test(new int[][]{{1, 7}, {11, 13}, {6, 10}, {5, 12}, {4, 8}, {8, 11}, {0, 6}, {2, 13}, {0, 5}, {1, 8}, {10, 13}}, 14, new int[]{8, 7, 7, 7, 7, 5, 4, 3, 3, 3, 3}));
        tests.add(test(new int[][]{{0, 13}, {1, 8}, {0, 5}, {8, 11}, {5, 9}, {11, 16}, {1, 3}, {0, 2}, {3, 12}, {1, 15}, {5, 15}, {9, 16}, {7, 14}, {8, 10}, {9, 15}, {1, 6}, {1, 12}, {4, 9}, {6, 14}, {6, 9}, {10, 12}, {3, 10}, {9, 14}, {8, 15}, {4, 11}, {4, 15}, {5, 14}, {5, 7}, {8, 12}, {10, 16}, {13, 16}, {10, 15}, {2, 4}, {1, 10}},
                17,
                new int[]{4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2}));


        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[][] queries, int n, int[] expected) {
        //add print here
        CommonMethods.printTest(new String[]{"Queries", "N", "Expected"}, true, queries, n, expected);

        int[] output;
        boolean pass, finalPass = true;

        Solution_Dijkstra solutionDijkstra = new Solution_Dijkstra();
        output = solutionDijkstra.shortestDistanceAfterQueries(n, queries);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Dijkstra", "Pass"}, false, output, pass ? "Pass" : "Fail");

        Solution_BellmanFord solutionBellmanFord = new Solution_BellmanFord();
        output = solutionBellmanFord.shortestDistanceAfterQueries(n, queries);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"BellmanFord", "Pass"}, false, output, pass ? "Pass" : "Fail");


        Solution_BFS solutionBfs = new Solution_BFS();
        output = solutionBfs.shortestDistanceAfterQueries(n, queries);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"BFS", "Pass"}, false, output, pass ? "Pass" : "Fail");

        return finalPass;

    }


    /**
     * T: O(Q * (E*log(V)))
     * S: O(V + E)
     */
    static class Solution_Dijkstra {
        public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
            //this will hold the graph
            List<List<Integer>> adjList = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                adjList.add(new ArrayList<>());
                if (i != n - 1)
                    adjList.get(i).add(i + 1);
            }

            int[] result = new int[queries.length];
            int r = 0;
            for (int[] query : queries) {

                // add link and calculate the distance
                adjList.get(query[0]).add(query[1]);

                result[r++] = shortestDistance(adjList, n, 0);

            }
            return result;
        }

        /**
         * {@link DijkstraShortestPath}
         * T: O(E*log(V))
         * S: O(V + E)
         */
        private int shortestDistance(List<List<Integer>> adjList, int n, int source) {
            int[] distance = new int[n];
            Arrays.fill(distance, n + 1);

            distance[source] = 0; // distance from source to source is 0

            PriorityQueue<Integer> pq = new PriorityQueue<>();

            pq.offer(source);

            int[] visited = new int[n];
            int visitedCount = 0;

            while (!pq.isEmpty() && visitedCount != n) {

                int u = pq.poll();

                if (visited[u] != 1) {
                    visited[u] = 1;
                    visitedCount++;

                    // go over all adj nodes
                    for (int v : adjList.get(u)) {

                        // have not visisted yet
                        if (visited[v] != 1) {

                            distance[v] = Math.min(distance[v], distance[u] + 1);

                            pq.offer(v);
                        }
                    }

                }
            }

            return distance[n - 1];
        }
    }


    /**
     * {@link BellmanFordShortestPath}
     * T:  O(Q * (V*E) ) [V = number of nodes, E = number of edges, Q = number of queries]
     * <p>
     * This solution results in TLE
     */
    static class Solution_BellmanFord {
        public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
            //this will hold the graph as an edge list
            List<int[]> edgeList = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                if (i != n - 1)
                    edgeList.add(new int[]{i, i + 1});
                else
                    edgeList.add(new int[]{i, -1}); // no directed edge from last to any node
            }

            int[] result = new int[queries.length];
            int r = 0;
            for (int[] query : queries) {

                // add link and calculate the distance
                edgeList.add(new int[]{query[0], query[1]});

                result[r++] = shortestDistance(edgeList, n, 0);

            }
            return result;
        }

        /**
         * {@link BellmanFordShortestPath}
         * T:  O(V*E)
         */
        private int shortestDistance(List<int[]> edgeList, int n, int source) {
            int[] distance = new int[n];
            Arrays.fill(distance, n + 1);

            distance[source] = 0; // distance from source to source is 0

            //relax all edges `n - 1` times
            for (int i = 0; i < n - 1; i++) {

                for (int[] edge : edgeList) {
                    int u = edge[0];
                    int v = edge[1];

                    if (v != -1) {
                        if (distance[u] != n + 1) {
                            distance[v] = Math.min(distance[v], distance[u] + 1);
                        }
                    }
                }
            }

            return distance[n - 1];
        }
    }


    /**
     * We don't need to use priorityQuery as used in {@link Solution_Dijkstra}, since the distance b/w adj node is always 1.
     * Hence instead of priority queue, we can use BFS to explore the nodes.
     */
    static class Solution_BFS {

        public int[] shortestDistanceAfterQueries(int n, int[][] queries) {


            List<List<Integer>> adjList = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                adjList.add(new ArrayList<>());
                if (i != n - 1) {
                    adjList.get(i).add(i + 1);
                }
            }
            int[] result = new int[queries.length];
            int r = 0;
            for (int[] query : queries) {
                adjList.get(query[0]).add(query[1]);
                result[r++] = bfs(adjList, n, 0, n - 1);
            }
            return result;
        }

        int bfs(List<List<Integer>> adjList, int n, int source, int destination) {

            //represent the node and the distance from source
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{source, 0}); // distance from source to source is 0

            //to skip the explored node
            boolean[] visited = new boolean[n];
            visited[source] = true;

            while (!queue.isEmpty()) {

                int[] cur = queue.poll();
                int u = cur[0];
                int distance = cur[1];

                //if we have reached destination, this distance is the minimum one because we are doing it in bfs manner
                if (u == destination)
                    return cur[1];

                //explore all adjacent nodes
                for (int v : adjList.get(u)) {

                    //if we have not visited this node
                    if (!visited[v]) {

                        //visited
                        visited[v] = true;

                        //offer it to the queue
                        queue.offer(new int[]{v, distance + 1});
                    }
                }
            }

            return -1;
        }
    }

}
