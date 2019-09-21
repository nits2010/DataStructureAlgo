package Java;

import javafx.util.Pair;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-04
 * Description: https://www.geeksforgeeks.org/find-nth-item-set-formed-sum-two-arrays/
 * Given two sorted arrays, we can get a set of sums(add one element from the first and one from second). Find the N’th element in the elements of the formed set considered in sorted order.
 * <p>
 * Note: Set of sums should have unique elements.
 * <p>
 * Examples:
 * <p>
 * Input : arr1[] = {1, 2}
 * arr2[] = {3, 4}
 * N = 3
 * Output : 6
 * We get following elements set of sums.
 * 4(1+3), 5(2+3 or 1+4), 6(2+4)
 * Third element in above set is 6.
 * <p>
 * Input : arr1[] = { 1,3, 4, 8, 10}
 * arr2[] = {20, 22, 30, 40}
 * N = 4
 * Output : 25
 * We get following elements set of sums.
 * 21(1+20), 23(1+22 or 20+3), 24(20+4), 25(22+3)...
 * Fourth element is 25.
 */
public class NthItemInSumOfTwoArrays {

    public static void main(String []args) {
        int arr1[] = {1, 2};
        int arr2[] = {3, 4};

        SolutionNthItemInSumOfTwoArrays sol = new SolutionNthItemInSumOfTwoArrays();
        System.out.println(sol.nthItem(arr1, arr2, 3));

    }

}

class SolutionNthItemInSumOfTwoArrays {

    class Node implements Comparable<Node> {
        int sum, ai, bi;

        public Node(int s, int a, int b) {
            this.sum = s;
            this.ai = a;
            this.bi = b;
        }

        @Override
        public int compareTo(Node o) {
            return this.sum - o.sum;
        }
    }

    public int nthItem(int a[], int b[], int n) {

        if (a == null || a.length == 0) {
            if (b.length >= n) {
                return b[n - 1];

            } else return Integer.MIN_VALUE;
        }

        if (b == null || b.length == 0) {
            if (a.length >= n) {
                return a[n - 1];

            } else return Integer.MIN_VALUE;
        }

        int x = a.length;
        int y = b.length;

        return nthItemNotAllowedDuplicates(a, b, x, y, n);
    }

    private int nthItem(int a[], int b[], int x, int y, int n) {

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        Set<Pair<Integer, Integer>> seen = new HashSet<>();

        Pair p = new Pair<>(0, 0);

        priorityQueue.offer(new Node(a[0] + b[0], 0, 0));
        seen.add(p);

        int i = 0;

        int item = -1;

        while (i < n && !priorityQueue.isEmpty()) {

            Node currentPair = priorityQueue.poll();
            item = currentPair.sum;

            Pair<Integer, Integer> newPairWithA = new Pair<>(currentPair.ai + 1, currentPair.bi);
            Pair<Integer, Integer> newPairWithB = new Pair<>(currentPair.ai, currentPair.bi + 1);

            if (!seen.contains(newPairWithB) && currentPair.bi + 1 < y) {
                seen.add(newPairWithB);

                priorityQueue.offer(new Node(a[currentPair.ai] + b[currentPair.bi + 1], currentPair.ai, currentPair.bi + 1));
            }

            if (!seen.contains(newPairWithA) && currentPair.ai + 1 < x) {
                seen.add(newPairWithA);
                priorityQueue.offer(new Node(a[currentPair.ai + 1] + b[currentPair.bi], currentPair.ai + 1, currentPair.bi));
            }
            i++;

        }

        if (i < n)
            return Integer.MIN_VALUE;
        return item;

    }


    private int nthItemNotAllowedDuplicates(int a[], int b[], int x, int y, int n) {

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        Set<Pair<Integer, Integer>> seen = new HashSet<>();
        Set<Integer> sumSeen = new HashSet<>();

        Pair p = new Pair<>(0, 0);

        priorityQueue.offer(new Node(a[0] + b[0], 0, 0));
        seen.add(p);
        sumSeen.add(a[0] + b[0]);

        int i = 0;

        int item = -1;

        while (i < n && !priorityQueue.isEmpty()) {

            Node currentPair = priorityQueue.poll();
            item = currentPair.sum;

            Pair<Integer, Integer> newPairWithA = new Pair<>(currentPair.ai + 1, currentPair.bi);
            Pair<Integer, Integer> newPairWithB = new Pair<>(currentPair.ai, currentPair.bi + 1);

            if (!seen.contains(newPairWithB) && currentPair.bi + 1 < y && !sumSeen.contains(a[currentPair.ai] + b[currentPair.bi + 1])) {
                seen.add(newPairWithB);

                sumSeen.add(a[currentPair.ai] + b[currentPair.bi + 1]);
                priorityQueue.offer(new Node(a[currentPair.ai] + b[currentPair.bi + 1], currentPair.ai, currentPair.bi + 1));
            }

            if (!seen.contains(newPairWithA) && currentPair.ai + 1 < x && !sumSeen.contains(a[currentPair.ai + 1] + b[currentPair.bi])) {

                seen.add(newPairWithA);
                sumSeen.add(a[currentPair.ai + 1] + b[currentPair.bi]);
                priorityQueue.offer(new Node(a[currentPair.ai + 1] + b[currentPair.bi], currentPair.ai + 1, currentPair.bi));
            }
            i++;

        }

        if (i < n)
            return Integer.MIN_VALUE;
        return item;

    }
}