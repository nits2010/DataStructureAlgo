package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays.CartesianGrid._1779;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date: 9/2/2024
 * Question Category: 1779. Find Nearest Point That Has the Same X or Y Coordinate
 * Description: https://leetcode.com/problems/find-nearest-point-that-has-the-same-x-or-y-coordinate/description/
 * You are given two integers, x and y, which represent your current location on a Cartesian grid: (x, y). You are also given an array points where each points[i] = [ai, bi] represents that a point exists at (ai, bi). A point is valid if it shares the same x-coordinate or the same y-coordinate as your location.
 * <p>
 * Return the index (0-indexed) of the valid point with the smallest Manhattan distance from your current location. If there are multiple, return the valid point with the smallest index. If there are no valid points, return -1.
 * <p>
 * The Manhattan distance between two points (x1, y1) and (x2, y2) is abs(x1 - x2) + abs(y1 - y2).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: x = 3, y = 4, points = [[1,2],[3,1],[2,4],[2,3],[4,4]]
 * Output: 2
 * Explanation: Of all the points, only [3,1], [2,4] and [4,4] are valid. Of the valid points, [2,4] and [4,4] have the smallest Manhattan distance from your current location, with a distance of 1. [2,4] has the smallest index, so return 2.
 * Example 2:
 * <p>
 * Input: x = 3, y = 4, points = [[3,4]]
 * Output: 0
 * Explanation: The answer is allowed to be on the same location as your current location.
 * Example 3:
 * <p>
 * Input: x = 3, y = 4, points = [[2,3]]
 * Output: -1
 * Explanation: There are no valid points.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= points.length <= 104
 * points[i].length == 2
 * 1 <= x, y, ai, bi <= 104
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 *
 * @easy
 * @Array <p>
 * <p>
 * Company Tags
 * -----
 * @Amazon
 * @DoorDash
 * @Editorial
 */
public class FindNearestPointThatHasTheSameXOrYCoordinate_1779 {
    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[][]{{1, 2}, {3, 1}, {2, 4}, {2, 3}, {4, 4}}, 3, 4, 2);
        test &= test(new int[][]{{3, 4}}, 3, 4, 0);
        test &= test(new int[][]{{2, 3}}, 3, 4, -1);

        System.out.println("=======================");
        System.out.println(test ? "\nAll Passed" : "\nSomething Failed");

    }

    private static boolean test(int[][] points, int x, int y, int expected) {
        System.out.println("--------------------------------");
        System.out.println("Points : " + CommonMethods.toStringFlat(points) + " x : " + x + " y : " + y + " expected : " + expected);
        Solution solution = new Solution();
        int output = solution.nearestValidPoint(x, y, points);
        boolean result = output == expected;
        System.out.println(" Output : " + output + " Result : " + result);
        return result;
    }


    static class Solution {
        public int nearestValidPoint(int x, int y, int[][] points) {
            if (points == null || points.length == 0 || points[0].length == 0)
                return -1;

            int index = -1; //as if nothing found than -1
            int smallestDistance = Integer.MAX_VALUE;


            for (int i = 0; i < points.length; i++) {
                int[] point = points[i];

                //if either of them (x/y) matches the point, then its dx or dy will be zero.
                // Hence, dx*dy = 0
                // Manhattan distance between two points (x1, y1) and (x2, y2) is abs(x1 - x2) + abs(y1 - y2)
                // which is abs(dx) + abs(dy)
                if (point[0] == x || point[1] == y) {
                    int dx = x - point[0];
                    int dy = y - point[1];
                    int manhattanDistance = Math.abs(dx) + Math.abs(dy);
                    if (manhattanDistance < smallestDistance) {
                        smallestDistance = manhattanDistance;
                        index = i;
                    }
                }


            }
            return index;
        }

        public int nearestValidPoint2(int x, int y, int[][] points) {
            if (points == null || points.length == 0 || points[0].length == 0)
                return -1;

            int index = -1; //as if nothing found than -1
            int smallestDistance = Integer.MAX_VALUE;


            for (int i = 0; i < points.length; i++) {
                int[] point = points[i];

                int dx = x - point[0];
                int dy = y - point[1];

                //if either of them (x/y) matches the point, then its dx or dy will be zero.
                // Hence, dx*dy = 0
                // Manhattan distance between two points (x1, y1) and (x2, y2) is abs(x1 - x2) + abs(y1 - y2)
                // which is abs(dx) + abs(dy)
                if (dx * dy == 0) {
                    int manhattanDistance = Math.abs(dx) + Math.abs(dy);
                    if (manhattanDistance < smallestDistance) {
                        smallestDistance = manhattanDistance;
                        index = i;
                    }
                }
            }


            return index;
        }
    }
}
