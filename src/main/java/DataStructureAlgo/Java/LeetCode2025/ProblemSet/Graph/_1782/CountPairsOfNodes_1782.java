package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Graph._1782;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 4/12/2025
 * Question Title: 1782. Count Pairs Of Nodes
 * Link: https://leetcode.com/problems/count-pairs-of-nodes/description/
 * Description: You are given an undirected graph defined by an integer n, the number of nodes, and a 2D integer array edges, the edges in the graph, where edges[i] = [ui, vi] indicates that there is an undirected edge between ui and vi. You are also given an integer array queries.
 * <p>
 * Let incident(a, b) be defined as the number of edges that are connected to either node a or b.
 * <p>
 * The answer to the jth query is the number of pairs of nodes (a, b) that satisfy both of the following conditions:
 * <p>
 * a < b
 * incident(a, b) > queries[j]
 * Return an array answers such that answers.length == queries.length and answers[j] is the answer of the jth query.
 * <p>
 * Note that there can be multiple edges between the same two nodes.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: n = 4, edges = [[1,2],[2,4],[1,3],[2,3],[2,1]], queries = [2,3]
 * Output: [6,5]
 * Explanation: The calculations for incident(a, b) are shown in the table above.
 * The answers for each of the queries are as follows:
 * - answers[0] = 6. All the pairs have an incident(a, b) value greater than 2.
 * - answers[1] = 5. All the pairs except (3, 4) have an incident(a, b) value greater than 3.
 * Example 2:
 * <p>
 * Input: n = 5, edges = [[1,5],[1,5],[3,4],[2,5],[1,3],[5,1],[2,3],[2,5]], queries = [1,2,3,4,5]
 * Output: [10,10,9,8,6]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= n <= 2 * 104
 * 1 <= edges.length <= 105
 * 1 <= ui, vi <= n
 * ui != vi
 * 1 <= queries.length <= 20
 * 0 <= queries[j] < edges.length
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @hard <p><p>
 * Company Tags
 * -----
 * @Amazon <p>
 * -----
 * @Editorial https://leetcode.com/problems/count-pairs-of-nodes/solutions/1096432/java-two-steps-o-nlgn-q-n-e <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class CountPairsOfNodes_1782 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[][]{{1, 2}, {2, 4}, {1, 3}, {2, 3}, {2, 1}}, 4, new int[]{2, 3}, new int[]{6, 5}));
        tests.add(test(new int[][]{{1, 5}, {1, 5}, {3, 4}, {2, 5}, {1, 3}, {5, 1}, {2, 3}, {2, 5}}, 5,
                new int[]{1, 2, 3, 4, 5}, new int[]{10, 10, 9, 8, 6}));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[][] edges, int n, int[] queries, int[] expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"edges", "n", "queries", "Expected"}, true, edges, n, queries, expected);

        int[] output;
        boolean pass, finalPass = true;

        Solution_String solutionString = new Solution_String();
        output = solutionString.countPairs(n, edges, queries);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"EncodedString", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        Solution_EncodedOptimized solutionEncodedOptimized = new Solution_EncodedOptimized();
        output = solutionEncodedOptimized.countPairs(n, edges, queries);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"EncodedOptimized", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }


    //O(E + n*logn + q *(n+E)) / O(E+n)
    // E - number of edges
    // n - number of nodes
    // q - total queries
    static class Solution_String {
        public int[] countPairs(int n, int[][] edges, int[] queries) {
            Map<String, Integer> shared = new HashMap<>(); // contains the pair count
            final int[] inDeg = inDeg(edges, shared, n); //contains in-degree of each 1..n node

            //create a clone for sorting, we need original inDeg to exclude the shared pair
            int[] sorted = inDeg.clone();
            Arrays.sort(sorted); //O(n*logn)

            return countPairs(queries, inDeg, sorted, shared, edges, n);

        }

        //Standard in-Degree algo
        //O(E) ; E number of edges
        int[] inDeg(int[][] edges, Map<String, Integer> shared, int n) {
            final int[] inDeg = new int[n + 1]; //contains in-degree of each 1..n node
            for (int[] edge : edges) {

                int a = edge[0];
                int b = edge[1];
                inDeg[a]++;
                inDeg[b]++;

                String pair = pair(Math.min(a, b), Math.max(a, b));
                shared.put(pair, shared.getOrDefault(pair, 0) + 1);

            }
            return inDeg;
        }

        String pair(int a, int b) {
            return a + "#" + b;
        }

        int[] unPair(String s) {
            String[] temp = s.split("#");
            return new int[]{Integer.parseInt(temp[0]), Integer.parseInt(temp[1])};
        }

        //O(q * (n + E))
        int[] countPairs(int[] queries, int[] inDeg, int[] sorted, Map<String, Integer> shared, int[][] edges, int n) {

            int[] result = new int[queries.length];
            int i = 0;
            for (int query : queries) {

                //count how many pair of edges, whose inDeg sum is > query
                int pairSumCount = pairSum(query, sorted, n);
                result[i++] = eliminateCommonPair(inDeg, shared, pairSumCount, query);
            }

            return result;
        }

        // In the worst case, all edges are unique, so total entry in map would be E hence O(E)
        int eliminateCommonPair(int[] inDeg, Map<String, Integer> shared, int result, int query) {
            //get all key pairs
            for (String key : shared.keySet()) {

                int[] pair = unPair(key);

                int inDegA = inDeg[pair[0]];
                int inDegB = inDeg[pair[1]];
                int sum = inDegA + inDegB;

                if (sum > query && sum - shared.get(key) <= query)
                    result--; // reduce this pair from total count, as it must be counted in pairSum since sum > query
            }

            return result;
        }

        //Standard two sum : O(n)
        int pairSum(int k, int[] nums, int n) {
            int low = 1, high = n; //represent the two different edges (a,b)
            int pairSumCount = 0;

            while (low < high) {
                int sum = nums[low] + nums[high];
                if (sum > k) {
                    pairSumCount += high - low;
                    high--;
                } else {
                    //since we need to get more pairSum, increasing low will increase the sum, and if the sum = k and still we need to count remaining greater sum pair
                    low++;
                }
            }
            return pairSumCount;
        }

    }

    //O(E + n*logn + q *(n+E)) / O(E+n)
    // E - number of edges
    // n - number of nodes
    // q - total queries
    static class Solution_EncodedOptimized {
        final int N = 30000; // since n is 2 <= n <= 2 * 10^4 -> 2 <= n <= 20,000

        public int[] countPairs(int n, int[][] edges, int[] queries) {
            Map<Integer, Integer> shared = new HashMap<>();
            int[] inDeg = computeInDegrees(edges, shared, n);

            int[] sorted = inDeg.clone();
            Arrays.sort(sorted); // For binary search-based counting O(n*logn)

            return processQueries(queries, inDeg, sorted, shared, n);
        }

        //Standard in-Degree algo
        //O(E) ; E number of edges
        int[] computeInDegrees(int[][] edges, Map<Integer, Integer> shared, int n) {
            int[] inDeg = new int[n + 1];
            for (int[] edge : edges) {
                int u = edge[0], v = edge[1];
                inDeg[u]++;
                inDeg[v]++;
                int key = encodePair(Math.min(u, v), Math.max(u, v));
                shared.put(key, shared.getOrDefault(key, 0) + 1);
            }
            return inDeg;
        }

        int encodePair(int a, int b) {
            return a * N + b;
        }

        int[] decodePair(int key) {
            return new int[]{key / N, key % N};
        }

        //O(q * (n + E))
        int[] processQueries(int[] queries, int[] inDeg, int[] sorted, Map<Integer, Integer> shared, int n) {
            int[] result = new int[queries.length];
            for (int i = 0; i < queries.length; i++) {
                int query = queries[i];
                int pairCount = countValidPairs(sorted, query, n);
                result[i] = adjustForSharedPairs(pairCount, inDeg, shared, query);
            }
            return result;
        }

        // In worst case, all edges are unique, so total entry in map would be E hence O(E)
        int adjustForSharedPairs(int count, int[] inDeg, Map<Integer, Integer> shared, int query) {
            for (Map.Entry<Integer, Integer> entry : shared.entrySet()) {
                int[] pair = decodePair(entry.getKey());
                int a = pair[0], b = pair[1];
                int sum = inDeg[a] + inDeg[b];
                int sharedEdges = entry.getValue();
                if (sum > query && sum - sharedEdges <= query) {
                    count--;
                }
            }
            return count;
        }

        //Standard two sum : O(n)
        int countValidPairs(int[] sorted, int k, int n) {
            int count = 0, left = 1, right = n; // node indices are 1-based
            while (left < right) {
                if (sorted[left] + sorted[right] > k) {
                    count += (right - left);
                    right--;
                } else {
                    left++;
                }
            }
            return count;
        }
    }

}
