package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Graph._1791;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date:09/09/24
 * Question Category: 1791. Find Center of Star Graph
 * Description: https://leetcode.com/problems/find-center-of-star-graph/description/
 * <p>
 * There is an undirected star graph consisting of n nodes labeled from 1 to n. A star graph is a graph where there is one center node and exactly n - 1 edges that connect the center node with every other node.
 * <p>
 * You are given a 2D integer array edges where each edges[i] = [ui, vi] indicates that there is an edge between the nodes ui and vi. Return the center of the given star graph.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: edges = [[1,2],[2,3],[4,2]]
 * Output: 2
 * Explanation: As shown in the figure above, node 2 is connected to every other node, so 2 is the center.
 * Example 2:
 * <p>
 * Input: edges = [[1,2],[5,1],[1,3],[1,4]]
 * Output: 1
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 * @easy
 * @Graph
 *
 * Company Tags
 * -----
 * @Microsoft
 * @Editorial
 */

public class FindCenterOfStarGraph_1791 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[][]{{1, 2}, {2, 3}, {4, 2}}, 2);
        test &= test(new int[][]{{1, 2}, {5, 1}, {1, 3}, {1, 4}}, 1);

        CommonMethods.printAllTestOutCome(test);
    }

    private static boolean test(int[][] edges, int expected) {
        System.out.println("-----------------------");
        System.out.println("Edges :" + CommonMethods.toString(edges) + " expected :" + expected);
        Solution solution = new Solution();
        int output = solution.findCenter(edges);
        boolean result = output == expected;
        System.out.println("Output :" + output + " expected :" + expected + " result : " + (result ? "Passed" : "Failed"));

        int output2 = solution.findCenter2(edges);
        boolean result2 = output2 == expected;
        System.out.println("Output :" + output2 + " expected :" + expected + " result : " + (result2 ? "Passed" : "Failed"));
        return result && result2;
    }

    static class Solution {
        public int findCenter(int[][] edges) {
            if (edges == null || edges.length == 0 || edges[0].length == 0)
                return -1;

            int commonVertex = -1;
            for (int i = 0; i < edges.length - 1; i++) {
                if (commonVertex == -1) {
                    if (edges[i][0] == edges[i + 1][0] || edges[i][0] == edges[i + 1][1])
                        commonVertex = edges[i][0];
                    else if (edges[i][1] == edges[i + 1][0] || edges[i][1] == edges[i + 1][1])
                        commonVertex = edges[i][1];
                } else {
                    if (edges[i][0] != commonVertex && edges[i][1] != commonVertex)
                        return -1;
                }

            }
            return commonVertex;
        }

        //As this is a 100 % star graph, hence there will be 100% a node which is center and this must be part of first two edge only.
        public int findCenter2(int[][] edges) {
            if (edges == null || edges.length == 0 || edges[0].length == 0)
                return -1;

            if (edges[0][0] == edges[1][0] || edges[0][0] == edges[1][1])
                return edges[0][0];
            else if (edges[0][1] == edges[1][0] || edges[0][1] == edges[1][1])
                return edges[0][1];
            return -1;


        }
    }
}
