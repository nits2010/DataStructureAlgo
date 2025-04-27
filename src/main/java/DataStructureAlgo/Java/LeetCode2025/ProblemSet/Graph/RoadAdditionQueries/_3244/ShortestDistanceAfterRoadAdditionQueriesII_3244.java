package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Graph.RoadAdditionQueries._3244;

import DataStructureAlgo.Java.LeetCode2025.ProblemSet.Graph.RoadAdditionQueries._3243.ShortestDistanceAfterRoadAdditionQueriesI_3243;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 11/27/2024
 * Question Title: 3244. Shortest Distance After Road Addition Queries II
 * Link: https://leetcode.com/problems/shortest-distance-after-road-addition-queries-ii/description/
 * Description: You are given an integer n and a 2D integer array queries.
 * <p>
 * There are n cities numbered from 0 to n - 1. Initially, there is a unidirectional road from city i to city i + 1 for all 0 <= i < n - 1.
 * <p>
 * queries[i] = [ui, vi] represents the addition of a new unidirectional road from city ui to city vi. After each query, you need to find the length of the shortest path from city 0 to city n - 1.
 * <p>
 * There are no two queries such that `queries[i][0] < queries[j][0] < queries[i][1] < queries[j][1].`
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
 * 3 <= n <= 105
 * 1 <= queries.length <= 105
 * queries[i].length == 2
 * 0 <= queries[i][0] < queries[i][1] < n
 * 1 < queries[i][1] - queries[i][0]
 * There are no repeated roads among the queries.
 * There are no two queries such that i != j and queries[i][0] < queries[j][0] < queries[i][1] < queries[j][1].
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link ShortestDistanceAfterRoadAdditionQueriesI_3243}
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @Array
 * @Greedy
 * @Graph
 * @OrderedSet
 * @hard <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class ShortestDistanceAfterRoadAdditionQueriesII_3244 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[][]{{2, 4}, {0, 2}, {0, 4}}, 5, new int[]{3, 2, 1}));
        tests.add(test(new int[][]{{0, 3}, {0, 2}}, 4, new int[]{1, 1}));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[][] queries, int n, int[] expected) {
        //add print here
        CommonMethods.printTest(new String[]{"Queries", "N", "Expected"}, true, queries, n, expected);


        int[] output;
        boolean pass, finalPass = true;

        Solution_HashMap solutionHashMap = new Solution_HashMap();
        output = solutionHashMap.shortestDistanceAfterQueries(n, queries);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"HashMap", "Pass"}, false, output, pass?"PASS":"FAIL");

        Solution_HashMapOptimized solutionHashMapOptimized = new Solution_HashMapOptimized();
        output = solutionHashMapOptimized.shortestDistanceAfterQueries(n, queries);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"HashMapOptimized", "Pass"}, false, output, pass?"PASS":"FAIL");



        Solution_Distance solutionDistance = new Solution_Distance();
        output = solutionDistance.shortestDistanceAfterQueries(n, queries);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Distance", "Pass"}, false, output, pass?"PASS":"FAIL");
        return finalPass;

    }

    /**
     * This is similar to this problem {@link ShortestDistanceAfterRoadAdditionQueriesI_3243},
     * the only difference here is, for any two queries at i and j index,
     * `queries[i][0] < queries[j][0] < queries[i][1] < queries[j][1].`
     * Means that, they are in sorted order and never overlap to each other.
     * For example,
     * {2, 4}, {0, 2}, {0, 4}
     * if you sort them
     * {0,2} {0,4} {2,4}
     * then there is no path exists which overlap / intersecting path to each other
     * <p>
     * for example:
     * {1,3} {2,4}
     * then there is path from 1 to 3 and there is path from 2 to 4 while the path from 2,4 makes its overlap as the path from 1,3 and 2 comes in between
     *
     * [TLE]
     */
    static class Solution_HashMap {

        public int[] shortestDistanceAfterQueries(int n, int[][] queries) {

            Map<Integer, Integer> adjListMap = new HashMap<>(n);

            //build a hashmap for a path from i to i+1; default road graph
            for (int i = 0; i < n - 1; i++) {
                adjListMap.put(i, i + 1);
            }

            int[] result = new int[queries.length];
            int r = 0;
            for (int[] query : queries) {
                int u = query[0];
                int v = query[1];

                //since there is a path from u to v, means that all the nodes
                // between u and v are of no use, as because of the problem constraint
                //remove all those edges
                for (int i = u + 1; i < v; i++) {
                    adjListMap.remove(i);
                }

                //add the edge
                adjListMap.put(u, v);

                //now a map will have only those nodes which matter, hence the shortest path would be the size of the graph itself
                //because that many nodes would be necessary to travel to reach the end
                result[r++] = adjListMap.size();
            }
            return result;

        }

    }


    /**
     * The main problem in above solution is to remove the nodes in between [u,v]
     * and each time we run a loop between them, without considering the fact that, there could be a previous
     * query which would have removed some nodes in between [u,v].
     * Assume a query [5,10], then first time we would remove 6,7,8,9 nodes,
     * and now with another query [0,11], we will try to remove nodes 1,2,3,4,5,6,7,8,9,10.
     * However, in the last query, we have already removed 6,7,8,9 which makes valid candidate from second query is
     * 1,2,3,4,5,10 but the loop will run for all, which makes the above solution TLE, because in the worst case, the first query it self would have
     * removed all the nodes from 0 to n-1 and all the subsequent queries were in between of (0, n-1) only.
     *
     * To avoid this, we can find the range we need to remove.
     */
    static class Solution_HashMapOptimized {

        public int[] shortestDistanceAfterQueries(int n, int[][] queries) {

            Map<Integer, Integer> adjListMap = new HashMap<>(n);

            //build a hashmap for a path from i to i+1; default road graph
            for (int i = 0; i < n - 1; i++) {
                adjListMap.put(i, i + 1);
            }

            int[] result = new int[queries.length];
            int r = 0;
            for (int[] query : queries) {
                int u = query[0];
                int v = query[1];

                //if this node already been removed,
                // or there is an edge available that is better than [u,v] then size of the current map is the output
                if(!adjListMap.containsKey(u) || adjListMap.get(u) >= v){
                    result[r++] = adjListMap.size();
                    continue;
                }

                //now remove all the edge which is in between [u,v]
                int in = adjListMap.get(u);

                while (in < v){

                    //remove the edge b/w [u,in] and take the next edge [in,...,v] to remove further
                    in = adjListMap.remove(in);
                }

                adjListMap.put(u,v);

                //now a map will have only those nodes which matter, hence the shortest path would be the size of the graph itself
                //because that many nodes would be necessary to travel to reach the end
                result[r++] = adjListMap.size();
            }
            return result;

        }

    }


    /**
     * The idea is same as map, however, in a map, we have to remove the edge to calculate the distance.
     * this can be avoided, as the max distance b/w 0 to n-1 is n-1.
     * Now, as we append query, all the nodes in between can be removed or make them to jump directly to the destination node, that
     * will be eqivalent to the max distance b/w 0 to n-1.
     */
    static class Solution_Distance {
        public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
            int []next = new int[n];

            //build the adj graph
            for(int i = 0; i<n; i++){
                next[i] = i+1;
            }
            int distance = n-1;
            int[] result = new int[queries.length];
            int r = 0;

            for(int []query : queries){
                int u = query[0];
                int v = query[1];

                //reduce the path and reset the not required edge
                while(next[u] < v){
                    int edge = next[u]; // cache the edge between [u, u+1]
                    next[u] = v; // set the edge, as this will be our direct edge
                    u = edge;
                    distance--; //nodes in b/w removed they won't be part of final answer

                }
                result[r++] = distance;
            }

            return result;
        }
    }
}
