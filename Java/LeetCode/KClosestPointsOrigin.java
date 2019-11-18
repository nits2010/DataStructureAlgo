package Java.LeetCode;

import Java.helpers.GenericPrinter;
import Java.nonleetcode.KthLargestElement;
import javafx.util.Pair;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 15/09/19
 * Description: https://leetcode.com/problems/k-closest-points-to-origin/
 * 973. K Closest Points to Origin[Medium]
 * <p>
 * We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
 * <p>
 * (Here, the distance between two points on a plane is the Euclidean distance.)
 * <p>
 * You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: points = [[1,3],[-2,2]], K = 1
 * Output: [[-2,2]]
 * Explanation:
 * The distance between (1, 3) and the origin is sqrt(10).
 * The distance between (-2, 2) and the origin is sqrt(8).
 * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 * We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
 * Example 2:
 * <p>
 * Input: points = [[3,3],[5,-1],[-2,4]], K = 2
 * Output: [[3,3],[-2,4]]
 * (The answer [[-2,4],[3,3]] would also be accepted.)
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= K <= points.length <= 10000
 * -10000 < points[i][0] < 10000
 * -10000 < points[i][1] < 10000
 * <p>
 * Prefer solution {@link KClosestPointsOriginPriorityQueueOptimized} and {@link KClosestPointsOriginPartition}
 */
public class KClosestPointsOrigin {

    public static void main(String[] args) {
        test(new int[][]{{1, 3}, {-2, 2}}, 1, new int[][]{{-2, 2}});
        test(new int[][]{{3, 3}, {5, -1}, {-2, 4}}, 2, new int[][]{{3, 3}, {-2, 4}});
        test(new int[][]{{3, 3}, {5, -1}, {-2, 4}, {-2, -5}, {-9, 8}, {0, 0}, {6, 6}}, 2, new int[][]{{3, 3}, {0, 0}});
        test(new int[][]{{3, 3}, {5, -1}, {-2, 4}, {-2, -5}, {-9, 8}, {0, 0}, {6, 6}}, 4, new int[][]{{5, -1}, {-2, 4}, {3, 3}, {0, 0}});
    }

    private static void test(int[][] points, int k, int[][] expected) {
        System.out.println("\n Points :\n" + GenericPrinter.toString(points) + " k: " + k + " \nexpected :\n" + GenericPrinter.toString(expected));

        final KClosestPointsOriginPriorityQueue pq = new KClosestPointsOriginPriorityQueue();

        final KClosestPointsOriginPriorityQueueOptimized pqOptimized = new KClosestPointsOriginPriorityQueueOptimized();

        final KClosestPointsOriginPartition partition = new KClosestPointsOriginPartition();

        final KClosestPointsOriginPartitionMedianOfMedian kClosestPointsOriginPartitionMedianOfMedian = new KClosestPointsOriginPartitionMedianOfMedian();

        System.out.println("pq :\n" + GenericPrinter.toString(pq.kClosest(GenericPrinter.copyOf(points), k)));
        System.out.println("pqOptimized :\n" + GenericPrinter.toString(pqOptimized.kClosest(GenericPrinter.copyOf(points), k)));
        System.out.println("partition :\n" + GenericPrinter.toString(partition.kClosest(GenericPrinter.copyOf(points), k)));
        System.out.println("kClosestPointsOriginPartitionMedianOfMedian :\n" + GenericPrinter.toString(kClosestPointsOriginPartitionMedianOfMedian.kClosest(GenericPrinter.copyOf(points), k)));
    }
}

/**
 * O(n*log(n))
 * Runtime: 69 ms, faster than 8.87% of Java online submissions for K Closest Points to Origin.
 * Memory Usage: 60.2 MB, less than 65.22% of Java online submissions for K Closest Points to Origin.
 */
class KClosestPointsOriginPriorityQueue {

    public int[][] kClosest(int[][] points, int k) {

        if (points == null || points.length == 0 || points[0].length == 0)
            return points;

        if (k == points.length)
            return points;

        final PriorityQueue<Pair<int[], Double>> pq = new PriorityQueue<>(Comparator.comparingDouble(Pair::getValue));

        final List<Pair<int[], Double>> distanceList = new ArrayList<>(points.length);
        for (int[] point : points) {
            distanceList.add(new Pair<>(point, distance(point[0], point[1])));
        }
        pq.addAll(distanceList);

        final int[][] result = new int[k][2];

        while (k > 0 && !pq.isEmpty()) {
            int[] point = pq.poll().getKey();
            result[k - 1][0] = point[0];
            result[k - 1][1] = point[1];

            k--;
        }

        return result;

    }

    private double distance(int x, int y) {
        return Math.sqrt(x * x + y * y);
    }
}


/**
 * O(k*log(n))
 * Runtime: 66 ms, faster than 10.38% of Java online submissions for K Closest Points to Origin.
 * Memory Usage: 59.7 MB, less than 73.91% of Java online submissions for K Closest Points to Origin.
 */
class KClosestPointsOriginPriorityQueueOptimized {

    public int[][] kClosest(int[][] points, int k) {

        if (points == null || points.length == 0 || points[0].length == 0)
            return points;

        if (k == points.length)
            return points;

        final PriorityQueue<int[]> pq = new PriorityQueue<>(((o1, o2) -> {
            double d1 = distance(o1[0], o1[1]);
            double d2 = distance(o2[0], o2[1]);

            return Double.compare(d2, d1);
        }));


        for (int[] point : points) {
            pq.offer(point);

            if (pq.size() > k) pq.poll(); //remove greatest distance from pq
        }


        final int[][] result = new int[k][2];

        while (k > 0 && !pq.isEmpty()) {
            result[k - 1] = pq.poll();

            k--;
        }

        return result;

    }

    private double distance(int x, int y) {
        return Math.sqrt(x * x + y * y);
    }
}

/**
 * We'll use Quicksort partition logic to partition the array based on 'k' element in the array. This way we'll avoid sorting complete array
 * <p>
 * Runtime: 4 ms, faster than 99.69% of Java online submissions for K Closest Points to Origin.
 * Memory Usage: 61.2 MB, less than 47.21% of Java online submissions for K Closest Points to Origin.
 */
class KClosestPointsOriginPartition {

    public int[][] kClosest(int[][] points, int k) {
        int l = 0, r = points.length - 1;


        while (l <= r) {

            int partitionIndex = partition(points, l, r, points[l]);

            if (partitionIndex == k)
                break;

            if (partitionIndex > k)
                r = partitionIndex - 1;
            else
                l = partitionIndex + 1;

        }
        return Arrays.copyOfRange(points, 0, k);
    }

    private int partition(int[][] points, int l, int r, int[] pivot) {
        int i;
        for (i = l; i < r; i++) {

            if (compare(points[i], pivot) == 0)
                break;
        }
        swap(points, i, r);

        i = l;
        for (int j = l; j < r; j++) {

            if (compare(points[j], pivot) <= 0) {
                swap(points, i, j);
                i++;
            }
        }
        swap(points, i, r);
        return i;
    }


    private int compare(int[] p1, int[] p2) {
        int x = p1[0] * p1[0] + p1[1] * p1[1]; //(x1^2 + y1^2)
        int y = p2[0] * p2[0] + p2[1] * p2[1]; //(x2^2 + y2^2)
        return x - y;
    }


    private void swap(int[][] points, int i, int j) {

        final int[] temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }
}

/**
 * Avoid below algorithm as though its O(n) but has more computation then needed. Its good for very huge array.
 * {@link KthLargestElement} #KthSmallest
 * We'll apply same logic as finding the kthSmallest element. Once we partition the array at index 'partition'
 * 1. if partition = k then all the element on left side of this index would be lesser than elements on right side of partition
 * 2. otherwise either we need to go left or right based on partition vs k
 * <p>
 * To find partition point efficiently we'll use median of median algorithm
 * <p>
 * Runtime: 63 ms, faster than 16.80% of Java online submissions for K Closest Points to Origin.
 * Memory Usage: 63 MB, less than 34.16% of Java online submissions for K Closest Points to Origin.
 */
class KClosestPointsOriginPartitionMedianOfMedian {

    static class Pair {
        int key;
        double distance;

        public Pair(int key, double distance) {
            this.key = key;
            this.distance = distance;
        }

        @Override
        public String toString() {
            return "key=" + key +
                    ", distance=" + distance +
                    '}';
        }
    }

    public int[][] kClosest(int[][] points, int k) {

        if (points == null || points.length == 0 || points[0].length == 0)
            return points;

        if (k == points.length)
            return points;

        final Pair[] distanceList = new Pair[points.length];

        for (int i = 0; i < points.length; i++) {
            int[] point = points[i];
            distanceList[i] = new Pair(i, distance(point[0], point[1]));
        }

        if (distanceList.length <= 5) {
            Arrays.sort(distanceList, Comparator.comparingDouble(o -> o.distance));
            return getKValues(distanceList, points, k);
        }

        int n = distanceList.length;
        final Double distance = kthLargestElement(distanceList, 0, n - 1, k);

        final int[][] result = new int[k][2];
        int p = 0;

        for (int i = 0; i < n && p < k; i++) {
            if (Double.compare(distanceList[i].distance, distance) <= 0)
                result[p++] = points[distanceList[i].key];
        }

        return result;


    }

    private int[][] getKValues(final Pair[] distanceList, int[][] points, int k) {
        final int[][] result = new int[k][2];

        int i = 0;
        while (k > 0) {
            result[k - 1] = points[distanceList[i++].key];
            k--;
        }

        return result;


    }

    private Double kthLargestElement(Pair[] distanceList, int l, int r, int k) {

        final int n = r - l + 1;

        if (k > 0 && k <= n) {

            final Pair[] median = new Pair[(n + 4) / 5];
            int i;
            for (i = 0; i < n / 5; i++) {
                median[i] = findMedian(distanceList, l + i * 5, 5);
            }

            if (i * 5 < n) {
                median[i] = findMedian(distanceList, l + i * 5, n % 5);
                i++;
            }

            final double medianOfMedian = (i == 1)
                    ? median[0].distance
                    : kthLargestElement(median, 0, i - 1, i / 2); //find median of median array

            final int partitionIndex = partition(distanceList, l, r, medianOfMedian);

            if (partitionIndex - l == k - 1)
                return distanceList[partitionIndex].distance;
            else if (partitionIndex - l > k - 1)
                return kthLargestElement(distanceList, l, partitionIndex - 1, k);
            else // k-1 > p - l => k > p -l+1 => k < -p+l-1 =>
                return kthLargestElement(distanceList, partitionIndex + 1, r, k - partitionIndex + l - 1);
        }


        return -1.0;
    }

    private int partition(Pair[] distanceList, int l, int r, double pivot) {

        int i;
        for (i = l; i < r; i++) {

            if (Double.compare(distanceList[i].distance, pivot) == 0) {
                break;
            }
        }

        swap(distanceList, i, r);

        i = l;
        for (int j = l; j < r; j++) {
            if (Double.compare(distanceList[j].distance, pivot) <= 0) {
                swap(distanceList, i, j);
                i++;
            }
        }
        swap(distanceList, i, r);
        return i;

    }

    private void swap(Pair[] distanceList, int i, int j) {

        final Pair temp = distanceList[i];
        distanceList[i] = distanceList[j];
        distanceList[j] = temp;
    }

    private Pair findMedian(Pair[] distanceList, int i, int n) {
        Arrays.sort(distanceList, i, i + n, Comparator.comparingDouble(o -> o.distance));
        return distanceList[i + (n / 2)];
    }


    private double distance(int x, int y) {
        return Math.sqrt(x * x + y * y);
    }
}
