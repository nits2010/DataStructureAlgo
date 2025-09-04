package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Graph._2097;

import DataStructureAlgo.Java.LeetCode.graph.ReconstructItinerary;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.nonleetcode.Tree.traversal.EulerTour;


/**
 * Author: Nitin Gupta
 * Date: 12/1/2024
 * Question Title: 2097. Valid Arrangement of Pairs
 * Link: https://leetcode.com/problems/valid-arrangement-of-pairs/description
 * Description: You are given a 0-indexed 2D integer array pairs where pairs[i] = [starti, endi]. An arrangement of pairs is valid if for every index i where 1 <= i < pairs.length, we have endi-1 == starti.
 * <p>
 * Return any valid arrangement of pairs.
 * <p>
 * Note: The inputs will be generated such that there exists a valid arrangement of pairs.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: pairs = [[5,1],[4,5],[11,9],[9,4]]
 * Output: [[11,9],[9,4],[4,5],[5,1]]
 * Explanation:
 * This is a valid arrangement since endi-1 always equals starti.
 * end0 = 9 == 9 = start1
 * end1 = 4 == 4 = start2
 * end2 = 5 == 5 = start3
 * Example 2:
 * <p>
 * Input: pairs = [[1,3],[3,2],[2,1]]
 * Output: [[1,3],[3,2],[2,1]]
 * Explanation:
 * This is a valid arrangement since endi-1 always equals starti.
 * end0 = 3 == 3 = start1
 * end1 = 2 == 2 = start2
 * The arrangements [[2,1],[1,3],[3,2]] and [[3,2],[2,1],[1,3]] are also valid.
 * Example 3:
 * <p>
 * Input: pairs = [[1,2],[1,3],[2,1]]
 * Output: [[1,2],[2,1],[1,3]]
 * Explanation:
 * This is a valid arrangement since endi-1 always equals starti.
 * end0 = 2 == 2 = start1
 * end1 = 1 == 1 = start2
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= pairs.length <= 105
 * pairs[i].length == 2
 * 0 <= starti, endi <= 109
 * starti != endi
 * No two pairs are exactly the same.
 * There exists a valid arrangement of pairs.
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link ReconstructItinerary}
 * extension {@link EulerTour}
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @hard
 * @Depth-FirstSearch
 * @Graph
 * @EulerianCircuit <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link Solution_Hierholzer} {@link Solution_EulerianCircuit}
 */

public class ValidArrangementOfPairs_2097 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[][]{{5, 1}, {4, 5}, {11, 9}, {9, 4}}, new int[][]{{11, 9}, {9, 4}, {4, 5}, {5, 1}}));
        tests.add(test(new int[][]{{1, 3}, {3, 2}, {2, 1}}, new int[][]{{1, 3}, {3, 2}, {2, 1}}));
        tests.add(test(new int[][]{{1, 2}, {1, 3}, {2, 1}}, new int[][]{{1, 2}, {2, 1}, {1, 3}}));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[][] grid, int[][] expected) {
        //add print here
        CommonMethods.printTest(new String[]{"Grid", "Expected"}, true, grid, expected);

        int[][] output = null;
        boolean pass, finalPass = true;

        Solution_EulerianCircuit solutionEulerianCircuit = new Solution_EulerianCircuit();
        output = solutionEulerianCircuit.validArrangement(grid);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"EulerianCircuit", "Pass"}, false, output, pass ? "PASS" : "FAIL");


        Solution_Hierholzer solutionHierholzer = new Solution_Hierholzer();
        output = solutionHierholzer.validArrangement(grid);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Hierholzer", "Pass"}, false, output, pass ? "PASS" : "FAIL");


        Solution_HierholzerIterative solutionHierholzerIterative = new Solution_HierholzerIterative();
        output = solutionHierholzerIterative.validArrangement(grid);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"HierholzerIterative", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        Solution_Hierholzer_ComputeOnTheFly solutionHierholzerComputeOnTheFly = new Solution_Hierholzer_ComputeOnTheFly();
        output = solutionHierholzerComputeOnTheFly.validArrangement(grid);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Hierholzer_ComputeOnTheFly", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class Solution_EulerianCircuit {
        public int[][] validArrangement(int[][] pairs) {

            //since we don't know the number of nodes in the graph, we can create adjList as map
            //the reason of choose value as Queue instead of list is that, when we do dfs, we need to make sure that
            // a visited node should not be visited again, we can not do this by value as many pair could have same value as start or end or both.
            //use queue or deque or any DS which poll the item post visited works
            Map<Integer, Queue<Integer>> adjList = new HashMap<>();

            //we need indegree and outdegree to apply Euelerian Circuit
            Map<Integer, Integer> indegree = new HashMap<>(); //<node, indegree>
            Map<Integer, Integer> outdegree = new HashMap<>(); //<node, outdegree>

            //build grap
            for (int[] pair : pairs) {
                int start = pair[0];
                int end = pair[1];

                adjList.computeIfAbsent(start, k -> new LinkedList<>()).add(end);

                //outdegree
                outdegree.put(start, outdegree.getOrDefault(start, 0) + 1);

                //indegree
                indegree.put(end, indegree.getOrDefault(end, 0) + 1);

            }


            //now we need to choose a node in the graph
            //where outdegree == indegree + 1, if none found then, this is a eulerian circuit and any node can be chosen

            int start = pairs[0][0];
            for (int node : outdegree.keySet()) {

                if (outdegree.get(node) == indegree.getOrDefault(node, 0) + 1) {
                    start = node;
                    break;
                }
            }

            //apply eulerian circuit
            List<Integer> eulerTour = new ArrayList<>();
            eulerTour(adjList, start, eulerTour);

            return getPairs2(eulerTour);

        }

        private int[][] getPairs2(List<Integer> eulerTour) {
            //reverse the tour
            int[][] ans = new int[eulerTour.size() - 1][2];
            for (int j = 0, i = eulerTour.size() - 1; i > 0; i--, j++) {
                ans[j][0] = eulerTour.get(i);
                ans[j][1] = eulerTour.get(i - 1);
            }

            return ans;
        }

        //post-order traversal
        private void eulerTour(Map<Integer, Queue<Integer>> adjList, int start, List<Integer> eulerTour) {

            Queue<Integer> queue = adjList.get(start);
            while (queue != null && !queue.isEmpty()) {
                int end = queue.poll();
                eulerTour(adjList, end, eulerTour);

            }
            eulerTour.add(start);
        }
    }


    static class Solution_Hierholzer {
        public int[][] validArrangement(int[][] pairs) {

            //since we don't know the number of nodes in the graph, we can create adjList as map
            //the reason of choose value as Queue instead of list is that, when we do dfs, we need to make sure that
            // a visited node should not be visited again, we can not do this by value as many pair could have same value as start or end or both.
            //use queue or deque or any DS which poll the item post visited works
            Map<Integer, Queue<Integer>> adjList = new HashMap<>();

            //we need indegree and out degree to apply Hierholzer Circuit
            Map<Integer, Integer> outDegree = new HashMap<>(); //<node, outdegree + indegree>

            //build grap
            for (int[] pair : pairs) {
                int start = pair[0];
                int end = pair[1];

                adjList.computeIfAbsent(start, k -> new LinkedList<>()).add(end);

                //outDegree
                outDegree.put(start, outDegree.getOrDefault(start, 0) + 1);

                //indegree
                outDegree.put(end, outDegree.getOrDefault(end, 0) - 1);

            }


            //now we need to choose a node in the graph
            //where out degree == indegree + 1, if none found then,
            //since the way we computed it, the start node will be the node with outDegree == indegree + 1 => outDegree == (outDegree - 1) + 1 => outDegree == outDegree

            int start = pairs[0][0];
            for (int node : outDegree.keySet()) {
                if (outDegree.get(node) == 1) {
                    start = node;
                    break;
                }
            }

            //apply Hierholzer circuit
            List<int[]> heirholzer = new ArrayList<>();
            heirholzerTour(adjList, start, heirholzer);

            return getPairs(heirholzer);

        }

        private int[][] getPairs(List<int[]> heirholzer) {
            //reverse the tour
            int[][] ans = new int[heirholzer.size()][2];

            for (int j = 0, i = heirholzer.size() - 1; i >= 0; i--, j++) {
                ans[j] = heirholzer.get(i);

            }

            return ans;
        }

        //post-order traversal
        private void heirholzerTour(Map<Integer, Queue<Integer>> adjList, int start, List<int[]> heirholzer) {

            Queue<Integer> queue = adjList.get(start);
            while (queue != null && !queue.isEmpty()) {
                int end = queue.poll();
                heirholzerTour(adjList, end, heirholzer);
                heirholzer.add(new int[]{start, end});

            }

        }
    }

    static class Solution_HierholzerIterative {

        public int[][] validArrangement(int[][] pairs) {
            HashMap<Integer, LinkedList<Integer>> adjacencyMatrix = new HashMap<>();
            HashMap<Integer, Integer> inDegree = new HashMap<>(), outDegree = new HashMap<>();

            // Build the adjacency list and track in-degrees and out-degrees
            for (int[] pair : pairs) {
                int start = pair[0], end = pair[1];
                adjacencyMatrix.putIfAbsent(start, new LinkedList<>());
                adjacencyMatrix.get(start).add(end);
                outDegree.put(start, outDegree.getOrDefault(start, 0) + 1);
                inDegree.put(end, inDegree.getOrDefault(end, 0) + 1);
            }

            ArrayList<Integer> result = new ArrayList<>();

            // Find the start node (outDegree == inDegree + 1)
            int startNode = -1;
            for (int node : outDegree.keySet()) {
                if (outDegree.get(node) == inDegree.getOrDefault(node, 0) + 1) {
                    startNode = node;
                    break;
                }
            }

            // If no such node exists, start from the first pair's first element
            if (startNode == -1) {
                startNode = pairs[0][0];
            }

            Stack<Integer> nodeStack = new Stack<>();
            nodeStack.push(startNode);

            // Iterative DFS using stack
            while (!nodeStack.empty()) {
                int node = nodeStack.peek();
                if (!adjacencyMatrix.getOrDefault(node, new LinkedList<>()).isEmpty()) {
                    // Visit the next node
                    int nextNode = adjacencyMatrix.get(node).removeFirst();
                    nodeStack.push(nextNode);
                } else {
                    // No more neighbors to visit, add node to result
                    result.add(node);
                    nodeStack.pop();
                }
            }

            // Reverse the result since we collected nodes in reverse order
            Collections.reverse(result);

            // Construct the result pairs
            int[][] pairedResult = new int[result.size() - 1][2];
            for (int i = 1; i < result.size(); ++i) {
                pairedResult[i - 1][0] = result.get(i - 1);
                pairedResult[i - 1][1] = result.get(i);
            }

            return pairedResult;
        }
    }

    static class Solution_Hierholzer_ComputeOnTheFly {
        public int[][] validArrangement(int[][] pairs) {
            int n = pairs.length;

            int[][] ans = new int[n][2];
            for (int[] a : ans) {
                a[0] = -1;
                a[1] = -1;
            }

            Map<Integer, Integer> outdegree = new HashMap<>();
            Map<Integer, Deque<Integer>> adjList = new HashMap<>();

            for (int[] pair : pairs) {
                int start = pair[0];
                int end = pair[1];
                outdegree.put(start, outdegree.getOrDefault(start, 0) + 1);
                outdegree.put(end, outdegree.getOrDefault(end, 0) - 1);

                adjList.computeIfAbsent(start, k -> new ArrayDeque<>());
                adjList.computeIfAbsent(end, k -> new ArrayDeque<>());

                adjList.get(start).addLast(end);
            }

            for (Map.Entry<Integer, Integer> entry : outdegree.entrySet()) {
                // find the head and tail
                if (entry.getValue() == 1)
                    ans[0][0] = entry.getKey();
                if (entry.getValue() == -1)
                    ans[n - 1][1] = entry.getKey();
            }

            if (ans[0][0] == -1) {
                ans[0][0] = pairs[0][0];
                ans[n - 1][1] = pairs[0][0];
            }

            int i = 0;
            int j = n - 1;
            while (i < j) {
                int from = ans[i][0];

                Deque<Integer> toList = adjList.get(from);

                if (toList.isEmpty()) {
                    ans[j][0] = ans[--i][0];
                    ans[--j][1] = ans[j + 1][0];
                } else {
                    ans[i++][1] = toList.removeLast();
                    ans[i][0] = ans[i - 1][1];
                }
            }

            return ans;
        }
    }
}
