package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Heap.CartesianGrid;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * Author: Nitin Gupta
 * Date: 9/2/2024
 * Question Category: 973. K Closest Points to Origin
 * Description: https://leetcode.com/problems/k-closest-points-to-origin/description/
 * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, return the k closest points to the origin (0, 0).
 * <p>
 * The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).
 * <p>
 * You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: points = [[1,3],[-2,2]], k = 1
 * Output: [[-2,2]]
 * Explanation:
 * The distance between (1, 3) and the origin is sqrt(10).
 * The distance between (-2, 2) and the origin is sqrt(8).
 * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 * We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].
 * Example 2:
 * <p>
 * Input: points = [[3,3],[5,-1],[-2,4]], k = 2
 * Output: [[3,3],[-2,4]]
 * Explanation: The answer [[-2,4],[3,3]] would also be accepted.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= k <= points.length <= 104
 * -104 <= xi, yi <= 104
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.KClosestPointsOrigin}
 * Similar {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.Heap.KthLargestElementInAnArray_245}
 * extension {@link }
 * <p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @Math
 * @DivideandConquer
 * @Geometry
 * @Sorting
 * @Heap(PriorityQueue)
 * @Quickselect <p>
 * <p>
 * Company Tags
 * -----
 * @Amazon
 * @Apple
 * @Asana
 * @eBay
 * @Expedia
 * @Facebook
 * @Google
 * @LinkedIn
 * @Microsoft
 * @Oracle
 * @Paypal
 * @Uber
 * @Yahoo
 * @Editorial
 */
public class KClosestPointsToOrigin_973 {
    public static void main(String[] args) {
        boolean test = true;

        test &= test(new int[][]{{1, 3}, {-2, 2}}, 1, new int[][]{{-2, 2}});
        test &= test(new int[][]{{3, 3}, {5, -1}, {-2, 4}}, 2, new int[][]{{3, 3}, {-2, 4}});
        test &= test(new int[][]{{3, 3}, {5, -1}, {-2, 4}, {-2, -5}, {-9, 8}, {0, 0}, {6, 6}}, 2, new int[][]{{3, 3}, {0, 0}});
        test &= test(new int[][]{{3, 3}, {5, -1}, {-2, 4}, {-2, -5}, {-9, 8}, {0, 0}, {6, 6}}, 4, new int[][]{{5, -1}, {-2, 4}, {3, 3}, {0, 0}});
        test &= test(new int[][]{{-2, -6}, {-7, -2}, {-9, 6}, {10, 3}, {-8, 1}, {2, 8}}, 5, new int[][]{{-2, -6}, {-7, -2}, {-8, 1}, {2, 8}, {10, 3}});
        test &= test(new int[][]{{-2, 10}, {-4, -8}, {10, 7}, {-4, -7}}, 3, new int[][]{{-4, -7}, {-4, -8}, {-2, 10}});
        CommonMethods.printResult(test);
    }

    private static boolean test(int[][] points, int k, int[][] expected) {
        System.out.println("-------------------------------");
        System.out.println("Points : " + CommonMethods.toStringNew(points) + " k: " + k + " expected :" + CommonMethods.toStringNew(expected));

        final SolutionPQ pq = new SolutionPQ();
        final Solution2WayPartition solution2WayPartition = new Solution2WayPartition();

        int[][] outputPQ = pq.kClosest(CommonMethods.copyOf(points), k);
        boolean resultPQ = CommonMethods.compareArrays(outputPQ, expected);
        System.out.println("pq :" + CommonMethods.toStringNew(outputPQ) + " ResultPQ : " + (resultPQ ? " Passed " : " Failed "));

        int[][] outputPQV2 = pq.kClosestV2(CommonMethods.copyOf(points), k);
        boolean resultPQV2 = CommonMethods.compareArrays(outputPQV2, expected);
        System.out.println("outputPQV2 :" + CommonMethods.toStringNew(outputPQV2) + " resultPQV2 : " + (resultPQV2 ? " Passed " : " Failed "));

        int[][] output2WayPartition = solution2WayPartition.kClosest(CommonMethods.copyOf(points), k);
        boolean result2WayPartition = CommonMethods.compareArrays(output2WayPartition, expected);
        System.out.println("output2WayPartition :" + CommonMethods.toStringNew(output2WayPartition) + " result2WayPartition : " + (result2WayPartition ? " Passed " : " Failed "));


        boolean finalResult = resultPQ && resultPQ && result2WayPartition;


        System.out.println(CommonMethods.getResultStringSubTest(finalResult, " Final Result : "));

        return finalResult;

    }

    /**
     * T/S : O(n*log(k)) / O(k)
     */
    static class SolutionPQ {


        /**
         * PQ contains distance and index of element
         * 33 ms  Beats 38.54%
         *
         * @param points
         * @param k
         * @return
         */
        public int[][] kClosest(int[][] points, int k) {
            if (points == null || points.length == 0 || points[0].length == 0)
                return new int[][]{{}};

            //MaxHeap of min distance element
            final PriorityQueue<int[]> pq = new PriorityQueue<>(k,
                    (o1, o2) ->
                            (o1[0] == o2[0])
                                    ? (Integer.compare(o2[1], o1[1]))
                                    : (Integer.compare(o2[0], o1[0]))
            );

            for (int i = 0; i < points.length; i++) {
                int distance = distanceWithoutSQT(points[i]);

                if (pq.size() < k)
                    pq.offer(new int[]{distance, i});
                else if (!pq.isEmpty() && pq.peek()[0] > distance) {
                    pq.poll();
                    pq.offer(new int[]{distance, i});
                }


            }

            int[][] result = new int[k][2];

            while (!pq.isEmpty()) {
                result[--k] = points[pq.poll()[1]];
            }
            return result;
        }

        int distanceWithoutSQT(int[] point) {
            return (point[0] * point[0] + point[1] * point[1]);
        }


        /**
         * Pq contains point but orders them (maxHeap) based on distance
         * 33 ms Beats 38.54%
         *
         * @param points
         * @param k
         * @return
         */
        public int[][] kClosestV2(int[][] points, int k) {
            if (points == null || points.length == 0 || points[0].length == 0)
                return new int[][]{{}};

            //MaxHeap of min distance element
            final PriorityQueue<int[]> pq = new PriorityQueue<>(k,
                    (o1, o2) ->
                            Integer.compare(distanceWithoutSQT(o2), distanceWithoutSQT(o1))

            );

            for (int[] point : points) {
                pq.offer(point);

                if (pq.size() > k)
                    pq.poll();

            }

            int[][] result = new int[k][2];

            while (!pq.isEmpty()) {
                result[--k] = pq.poll();
            }
            return result;
        }
    }


    static class Solution2WayPartition {

        Random random = new Random();

        static class Distance {
            int distance;
            int index;

            Distance(int distance, int i) {
                this.distance = distance;
                this.index = i;
            }
        }

        /**
         * {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.Heap.KthLargestElementInAnArray_245.SolutionUsingQuickSelect}
         *
         * @param points
         * @param k
         * @return
         */
        public int[][] kClosest(int[][] points, int k) {
            if (points == null || points.length == 0 || points[0].length == 0)
                return new int[][]{{}};

            //compute distance of all points and its index
            Distance[] distances = computeDistance(points);

            //Now the problem reduces to find the kth smallest element. However, since we need to return all the elements that have the least distance of size k
            // hence post finding the kth smallest element (index of it) all the element till it is our result.
            int index = findKthSmallest(distances, k);

            int[][] result = new int[k][2];
            while (k > 0) {
                result[k - 1] = points[distances[k - 1].index];
                k--;
            }

            return result;
        }

        private int findKthSmallest(Distance[] distances, int k) {

            int low = 0;
            int n = distances.length;
            int high = n - 1;


            while (low < high) {

                int partitionIndex = partition(distances, low, high);

                if (partitionIndex == k)
                    return partitionIndex;

                if (partitionIndex > k)
                    high = partitionIndex - 1;
                else
                    low = partitionIndex + 1;
            }

            return -1;
        }

        private int partition(Distance[] distances, int low, int high) {

            int l = low + 1;


            // to avoid hitting the worst case, assume an array is sorted ascending order
            // randomize pivot/boundary
            int pivotIndex = low + random.nextInt(high - low + 1);
            swap(distances, low, pivotIndex);

            int boundary = low;
            int pivotElement = distances[boundary].distance;

            while (l <= high) {

                if (distances[l].distance <= pivotElement) {
                    boundary++;
                    swap(distances, boundary, l);
                }
                l++;
            }

            swap(distances, boundary, low);

            return boundary;
        }

        private void swap(Distance[] distance, int x, int y) {
            Distance temp = distance[x];
            distance[x] = distance[y];
            distance[y] = temp;
        }


        Distance[] computeDistance(int[][] points) {
            Distance[] distance = new Distance[points.length];
            int i = 0;
            for (int[] point : points) {
                distance[i] = new Distance(distanceWithoutSQT(point), i);
                i++;
            }
            return distance;
        }


        int distanceWithoutSQT(int[] point) {
            return (point[0] * point[0] + point[1] * point[1]);
        }


    }
}
