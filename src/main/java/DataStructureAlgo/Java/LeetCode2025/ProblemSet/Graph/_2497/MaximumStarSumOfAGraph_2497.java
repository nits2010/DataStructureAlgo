package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Graph._2497;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 9/10/2024
 * Question Category: 2497. Maximum Star Sum of a Graph
 * Description: https://leetcode.com/problems/maximum-star-sum-of-a-graph/description/
 * There is an undirected graph consisting of n nodes numbered from 0 to n - 1. You are given a 0-indexed integer array vals of length n where vals[i] denotes the value of the ith node.
 *
 * You are also given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting nodes ai and bi.
 *
 * A star graph is a subgraph of the given graph having a center node containing 0 or more neighbors. In other words, it is a subset of edges of the given graph such that there exists a common node for all edges.
 *
 * The image below shows star graphs with 3 and 4 neighbors respectively, centered at the blue node.
 *
 *
 * The star sum is the sum of the values of all the nodes present in the star graph.
 *
 * Given an integer k, return the maximum star sum of a star graph containing at most k edges.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: vals = [1,2,3,4,10,-10,-20], edges = [[0,1],[1,2],[1,3],[3,4],[3,5],[3,6]], k = 2
 * Output: 16
 * Explanation: The above diagram represents the input graph.
 * The star graph with the maximum star sum is denoted by blue. It is centered at 3 and includes its neighbors 1 and 4.
 * It can be shown it is not possible to get a star graph with a sum greater than 16.
 * Example 2:
 *
 * Input: vals = [-5], edges = [], k = 0
 * Output: -5
 * Explanation: There is only one possible star graph, which is node 0 itself.
 * Hence, we return -5.
 *
 *
 * Constraints:
 *
 * n == vals.length
 * 1 <= n <= 105
 * -104 <= vals[i] <= 104
 * 0 <= edges.length <= min(n * (n - 1) / 2, 105)
 * edges[i].length == 2
 * 0 <= ai, bi <= n - 1
 * ai != bi
 * 0 <= k <= n - 1
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p><p>
 * Tags
 * -----
 * @medium
 * @Array
 * @Greedy
 * @Graph
 * @Sorting
 * @Heap(PriorityQueue)
 *
 * <p><p>
 * Company Tags
 * -----
 * @Microsoft
 *
 * @Editorial https://leetcode.com/problems/maximum-star-sum-of-a-graph/solutions/5767955/easy-to-understand-optimized-solution-beat-100-detailed-explanations-with-complexity/
 */
public class MaximumStarSumOfAGraph_2497 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[]{-2,-1,-2}, new int[][]{{0, 2}}, 1, -1);
        test &= test(new int[]{0, -36, 4, 35, 27, -13}, new int[][]{{5, 3}, {4, 3}, {0, 4}, {2, 4}, {0, 2}}, 1, 62);
        test &= test(new int[]{1, -8, 0}, new int[][]{{1, 0}, {2, 1}}, 2, 1);
        test &= test(new int[]{1, 2, 3, 4, 10, -10, -20}, new int[][]{{0, 1}, {1, 2}, {1, 3}, {3, 4}, {3, 5}, {3, 6}}, 2, 16);
        test &= test(new int[]{-5}, new int[][]{{}}, 0, -5);
        test &= test(new int[]{-1, 0}, new int[][]{{}}, 1, 0);
        CommonMethods.printResult(test);
    }

    private static boolean test(int[] vals, int[][] edges, int k, int expected) {
        System.out.println("------------------------------------");
        System.out.println("Values : " + CommonMethods.toString(vals) + " edges : " + CommonMethods.toStringFlat(edges) + " k : " + k + " expected : " + expected);
        Solution solution = new Solution();
        int output = solution.maxStarSum(vals, edges, k);
        boolean result = output == expected;
        System.out.println("Output :" + output + " expected :" + expected + " result : " + (result ? "Passed" : "Failed"));

        System.out.println("------------------------------------");
        SolutionOptimized solutionOptimized = new SolutionOptimized();
        int outputOptimized = solutionOptimized.maxStarSum(vals, edges, k);
        boolean resultOptimized = outputOptimized == expected;
        System.out.println("outputOptimized :" + outputOptimized + " expected :" + expected + " resultOptimized : " + (resultOptimized ? "Passed" : "Failed"));

        return result && resultOptimized;
    }

    static class Solution {

        /**
         * Approach; Since it is certain that a graph will have a center node, and we can choose any node as a center.
         * And we have to choose the maximum value only from the at most k edges.
         * <p>
         * Hence,
         * 1. Build a graph, adj List, instead of holding the node or node values as list/array, hold it as min heap of a maximum element of size k. So that later we can extract maximum k nodes values.
         * 2. Post-graph creation, just consider each node as a potential center node and try to include top k nodes from PQ.
         * 3. Finally, return the max sum.
         * <p>
         * Time complexity: O(EK*Log(K)) +  O(N*k*Log(k)) = O(max(N,E)*k*Log(k))
         * Space complexity: O(NK)
         *
         * @param vals
         * @param edges
         * @param k
         * @return
         */
        public int maxStarSum(int[] vals, int[][] edges, int k) {
            if (vals == null || vals.length == 0)
                return Integer.MIN_VALUE;

            if (k == 0 || edges == null || edges.length == 0 || edges[0].length == 0)
                return Arrays.stream(vals).max().getAsInt();

            //generate graph as adj list O (EK*Log(K))
            PriorityQueue<Integer>[] graphAdjList = buildGraphAdjList(edges, vals, k);

            //consider each node as a potential center node and try to include top k nodes from PQ
            int maxSum = Integer.MIN_VALUE;

            //O(N)*O(k*Log(k)) = O(N*k*Log(k))
            for (int i = 0; i < graphAdjList.length; i++) {
                int sum = vals[i];
                PriorityQueue<Integer> pq = graphAdjList[i];
                //O(k*Log(k))
                while (!pq.isEmpty())
                    sum += pq.poll();

                maxSum = Math.max(maxSum, sum);
            }


            return maxSum;


        }

        /**
         * T/S : O (EK*Log(K))
         *
         * @param edges
         * @param nodeValues
         * @param k
         * @return PriorityQueue<Integer>[]
         */
        private PriorityQueue<Integer>[] buildGraphAdjList(int[][] edges, int[] nodeValues, int k) {

            //Build a graph, where each connected node value is stored as min heap containing largest k nodes values [ min heap as max heap of k nodes ]
            PriorityQueue<Integer>[] graphAdjList = new PriorityQueue[nodeValues.length];
            for (int i = 0; i < nodeValues.length; i++)
                graphAdjList[i] = new PriorityQueue<>();

            //build graph
            //T: O(E) * O(k*Log(k))
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];

                // for the u-v edge, if u is positive, then add it to our min heap (of max elements)
                if (nodeValues[v] > 0) {
                    PriorityQueue<Integer> pq = graphAdjList[u];
                    offer(pq, nodeValues[v], k);

                }

                // for the v-u edge, if u is positive, then add it to our min heap (of max elements)
                if (nodeValues[u] > 0) {
                    PriorityQueue<Integer> pq = graphAdjList[v];
                    offer(pq, nodeValues[u], k);
                }

            }
            return graphAdjList;
        }

        //T: O(k*Log(k))
        private void offer(PriorityQueue<Integer> pq, int element, int k) {
            pq.offer(element);
            if (pq.size() > k)
                pq.poll();
        }

    }

    static class SolutionOptimized {

        /**
         * Approach; Since it is certain that a graph will have a center node, and we can choose any node as a center.
         * And we have to choose the maximum value only from the at most k edges.
         * <p>
         * Hence,
         * 1. Build a graph, adj List, instead of holding the node or node values as list/array, hold it as min heap of a maximum element of size k. So that later we can extract maximum k nodes values.
         * 2. Post-graph creation, just consider each node as a potential center node and try to include top k nodes from PQ.
         * 3. Finally, return the max sum.
         * <p>
         * Time complexity: O(EK*Log(K)) +  O(N*k*Log(k)) = O(max(N,E)*k*Log(k))
         * Space complexity: O(NK)
         * <p>
         * The optimization can be done for calculating sum on the fly and maximize it on the fly as well during building the graph.
         *
         * @param vals
         * @param edges
         * @param k
         * @return
         */
        public int maxStarSum(int[] vals, int[][] edges, int k) {
            if (vals == null || vals.length == 0)
                return Integer.MIN_VALUE;

            if (k == 0 || edges == null || edges.length == 0 || edges[0].length == 0)
                return Arrays.stream(vals).max().getAsInt();

            return buildGraphAdjList(edges, vals, k);

        }

        /**
         * T/S : O (EK*Log(K))
         *
         * @param edges
         * @param nodeValues
         * @param k
         * @return PriorityQueue<Integer>[]
         */
        private int buildGraphAdjList(int[][] edges, int[] nodeValues, int k) {

            int[] sum = new int[nodeValues.length];
            int maxSum = Integer.MIN_VALUE;
            //Build a graph, where each connected node value is stored as min heap containing largest k nodes values [ min heap as max heap of k nodes ]
            PriorityQueue<Integer>[] graphAdjList = new PriorityQueue[nodeValues.length];
            for (int i = 0; i < nodeValues.length; i++) {
                sum[i] = nodeValues[i];
                graphAdjList[i] = new PriorityQueue<>();
                maxSum = Math.max(maxSum, nodeValues[i]);
            }

            //build graph
            //T: O(E) * O(k*Log(k))
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];

                // for the u-v edge, if u is positive, then add it to our min heap (of max elements)
                if (nodeValues[v] > 0) {
                    PriorityQueue<Integer> pq = graphAdjList[u];
                    offer(pq, nodeValues[v], k, sum, u);
                    maxSum = Math.max(Math.max(sum[u], nodeValues[v]),maxSum);


                }

                // for the v-u edge, if u is positive, then add it to our min heap (of max elements)
                if (nodeValues[u] > 0) {
                    PriorityQueue<Integer> pq = graphAdjList[v];
                    offer(pq, nodeValues[u], k, sum, v);
                    maxSum = Math.max(Math.max(sum[v], nodeValues[u]),maxSum);
                }

                //in case both node values are negative, then we have to choose the maximum out of it
                maxSum = Math.max(maxSum, Math.max(nodeValues[u], nodeValues[v]));

            }
            return maxSum;
        }

        //T: O(k*Log(k))
        private void offer(PriorityQueue<Integer> pq, int element, int k, int[] sum, int uv) {

            pq.offer(element);

            int polledElement = 0;
            if (pq.size() > k) {
                polledElement = pq.poll();
            }


            sum[uv] += element - polledElement;

        }

    }
}

