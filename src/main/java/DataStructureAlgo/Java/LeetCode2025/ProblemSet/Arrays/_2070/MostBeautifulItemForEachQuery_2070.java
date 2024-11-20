package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays._2070;

import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.*;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date:13/11/24
 * Question Category: 2070. Most Beautiful Item for Each Query
 * Description: https://leetcode.com/problems/most-beautiful-item-for-each-query/description/
 * You are given a 2D integer array items where items[i] = [pricei, beautyi] denotes the price and beauty of an item respectively.
 * <p>
 * You are also given a 0-indexed integer array queries. For each queries[j], you want to determine the maximum beauty of an item whose price is less than or equal to queries[j]. If no such item exists, then the answer to this query is 0.
 * <p>
 * Return an array answer of the same length as queries where answer[j] is the answer to the jth query.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: items = [[1,2],[3,2],[2,4],[5,6],[3,5]], queries = [1,2,3,4,5,6]
 * Output: [2,4,5,5,6,6]
 * Explanation:
 * - For queries[0]=1, [1,2] is the only item which has price <= 1. Hence, the answer for this query is 2.
 * - For queries[1]=2, the items which can be considered are [1,2] and [2,4].
 * The maximum beauty among them is 4.
 * - For queries[2]=3 and queries[3]=4, the items which can be considered are [1,2], [3,2], [2,4], and [3,5].
 * The maximum beauty among them is 5.
 * - For queries[4]=5 and queries[5]=6, all items can be considered.
 * Hence, the answer for them is the maximum beauty of all items, i.e., 6.
 * Example 2:
 * <p>
 * Input: items = [[1,2],[1,2],[1,3],[1,4]], queries = [1]
 * Output: [4]
 * Explanation:
 * The price of every item is equal to 1, so we choose the item with the maximum beauty 4.
 * Note that multiple items can have the same price and/or beauty.
 * Example 3:
 * <p>
 * Input: items = [[10,1000]], queries = [5]
 * Output: [0]
 * Explanation:
 * No item has a price less than or equal to 5, so no item can be chosen.
 * Hence, the answer to the query is 0.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= items.length, queries.length <= 105
 * items[i].length == 2
 * 1 <= pricei, beautyi, queries[j] <= 109
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @BinarySearch
 * @Sorting <p>
 * Company Tags
 * -----
 * @Postmates
 * @Editorial
 */

public class MostBeautifulItemForEachQuery_2070 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        //add tests cases here
        tests.add(tests(new int[][]{{1, 2}, {3, 2}, {2, 4}, {5, 6}, {3, 5}}, new int[]{1, 2, 3, 4, 5, 6}, new int[]{2, 4, 5, 5, 6, 6}));
        tests.add(tests(new int[][]{{1, 2}, {1, 2}, {1, 3}, {1, 4}}, new int[]{1}, new int[]{4}));
        tests.add(tests(new int[][]{{10, 1000}}, new int[]{5}, new int[]{0}));

        CommonMethods.printAllTestOutCome(tests);
    }


    private static boolean tests(int[][] items, int[] queries, int[] expected) {
        CommonMethods.printTestOutcome(new String[]{"Items", "Queries", "Expected"}, true, items, queries, expected);

        int[] output;
        boolean pass, finalPass = true;

        SolutionBinarySearch solutionBinarySearch = new SolutionBinarySearch();
        output = solutionBinarySearch.maximumBeauty(items, queries);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"Binary-Search", "Pass"}, false, output, pass ? "Pass" : "Fail");

        SolutionSortingQueries solutionSortingQueries = new SolutionSortingQueries();
        output = solutionSortingQueries.maximumBeauty(items, queries);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"Sorting-Queries", "Pass"}, false, output, pass ? "Pass" : "Fail");
        return finalPass;

    }

    static class SolutionBinarySearch {

        public int[] maximumBeauty(int[][] items, int[] queries) {
            int[] ans = new int[queries.length];

            // Sort and store max beauty
            Arrays.sort(items, Comparator.comparingInt(a -> a[0]));

            //Arrange the max beauty by order as well
            int max = items[0][1];
            for (int i = 0; i < items.length; i++) {
                max = Math.max(max, items[i][1]);
                items[i][1] = max;
            }

            for (int i = 0; i < queries.length; i++) {
                // answer i-th query
                ans[i] = binarySearch(items, queries[i]);
            }

            return ans;
        }

        private int binarySearch(int[][] items, int targetPrice) {
            int l = 0;
            int r = items.length - 1;
            int maxBeauty = 0;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (items[mid][0] > targetPrice) {
                    r = mid - 1;
                } else {
                    // Found viable price. Keep moving to right to get max beauty
                    maxBeauty = Math.max(maxBeauty, items[mid][1]);
                    l = mid + 1;
                }
            }
            return maxBeauty;
        }
    }


    static class SolutionSortingQueries {

        public int[] maximumBeauty(int[][] items, int[] queries) {
            int[] ans = new int[queries.length];

            // sort both items and queries in ascending order
            Arrays.sort(items, Comparator.comparingInt(a -> a[0]));

            int[][] queriesWithIndices = new int[queries.length][2];
            for (int i = 0; i < queries.length; i++) {
                queriesWithIndices[i][0] = queries[i];
                queriesWithIndices[i][1] = i;
            }

            Arrays.sort(queriesWithIndices, Comparator.comparingInt(a -> a[0]));

            int itemIndex = 0;
            int maxBeauty = 0;

            for (int i = 0; i < queries.length; i++) {
                int query = queriesWithIndices[i][0];
                int originalIndex = queriesWithIndices[i][1];

                while (itemIndex < items.length && items[itemIndex][0] <= query) {
                    maxBeauty = Math.max(maxBeauty, items[itemIndex][1]);
                    itemIndex++;
                }

                ans[originalIndex] = maxBeauty;
            }

            return ans;
        }
    }
}
