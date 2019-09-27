package Java;

import Java.HelpersToPrint.GenericPrinter;
import javafx.util.Pair;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-04
 * Description: https://www.geeksforgeeks.org/find-nth-item-set-formed-sum-two-arrays/
 * Given two sorted arrays, we can get a set of sums(add one element from the first and one from second).
 * Find the N’th element in the elements of the formed set considered in sorted order.
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

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[]{1, 2}, new int[]{3, 4}, 3, 6);
        test &= test(new int[]{1, 3, 4, 8, 10}, new int[]{20, 22, 30, 40}, 4, 25);
        System.out.println("\n Tests : " + (test ? "Passed" : "Failed"));

    }

    private static boolean test(int[] a, int[] b, int n, int expected) {
        System.out.println("\nA:" + GenericPrinter.toString(a));
        System.out.println("\nB:" + GenericPrinter.toString(b) + "\nN=" + n);

        SolutionNthItemInSumOfTwoArraysDuplicateSumNotAllowed duplicateSumNotAllowed = new SolutionNthItemInSumOfTwoArraysDuplicateSumNotAllowed();
        int obtained = duplicateSumNotAllowed.nthItem(a, b, n);
        System.out.println("Expected:" + expected);
        System.out.println("Obtained:" + obtained);

        return GenericPrinter.equalsValues(obtained, expected);

    }


}

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

    @Override
    public String toString() {
        return "Node{" +
                "sum=" + sum +
                ", ai=" + ai +
                ", bi=" + bi +
                '}';
    }
}



/**
 * Duplicate sum Not allowed
 * Algo:
 * 1. create all the sum nodes for a[i] + b[0]
 * 2. Build priority queue using above nodes
 * 3. For each poll
 * *    3.1: Insert the appropriate pair in PQ either a[i+1]+b[j] or a[i]+b[j+1] if not seen as pair, not seen as sum
 *
 * Time Complexity:  N length of a[], M length of b[]
 * Step 1: O(N)
 * Step 2: O(N) Priority queue could be build in O(n)
 * step 3: In worst case, all the sum are unique and we need to find the last element. This case PQ will contain max(N,M)+1 elements since we remove 1 and add 2 so effectively adding 1 element.
 * That takes O(log(Max(N,M)) time run for n times { n= nth sum element}
 * O(n * log(Max(N,M) )
 *
 * O(N) + O(N) + O(n * log(Max(N,M) )  => O(n * log(Max(N,M) )
 *
 * Space: O(Max(N,M))
 *
 */
class SolutionNthItemInSumOfTwoArraysDuplicateSumNotAllowed {


    public int nthItem(int[] a, int[] b, int n) {

        if (a == null || a.length == 0) {
            if (b.length >= n)
                return b[n - 1];
            else
                return Integer.MIN_VALUE;
        }

        if (b == null || b.length == 0) {
            if (a.length >= n)
                return a[n - 1];
            else
                return Integer.MIN_VALUE;
        }

        return nthItem(a, b, a.length, b.length, n);
    }


    private int nthItem(int[] a, int[] b, int aLength, int bLength, int n) {


        Set<int[]> seenPair = new HashSet<>();
        Set<Integer> sumSeen = new HashSet<>();
        List<Node> nodes = new ArrayList<>();

        //O(aLength)
        for (int i = 0; i < a.length; i++) {
            int sum = a[i] + b[0];
            if (!sumSeen.contains(sum)) {
                nodes.add(new Node(a[i] + b[0], i, 0));
                sumSeen.add(sum);
                seenPair.add(new int[]{i, 0});
            }
        }

        //O(aLength)
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(nodes);

        int i = 0;
        Node currentPair = null;


        while (i < n && !priorityQueue.isEmpty()) {

            currentPair = priorityQueue.poll();

            int[] pairWithA = {currentPair.ai + 1, currentPair.bi};
            int[] pairWithB = {currentPair.ai, currentPair.bi + 1};

            if (!seenPair.contains(pairWithA) && currentPair.ai + 1 < aLength && !sumSeen.contains(a[currentPair.ai + 1] + b[currentPair.bi])) {
                seenPair.add(pairWithA);
                sumSeen.add(a[currentPair.ai + 1] + b[currentPair.bi]);
                priorityQueue.offer(new Node(a[currentPair.ai + 1] + b[currentPair.bi], currentPair.ai + 1, currentPair.bi));
            }

            if (!seenPair.contains(pairWithB) && currentPair.bi + 1 < bLength && !sumSeen.contains(a[currentPair.ai] + b[currentPair.bi + 1])) {
                seenPair.add(pairWithA);
                sumSeen.add(a[currentPair.ai] + b[currentPair.bi + 1]);
                priorityQueue.offer(new Node(a[currentPair.ai] + b[currentPair.bi + 1], currentPair.ai, currentPair.bi + 1));
            }
            i++;

        }

        if (i < n)
            return Integer.MAX_VALUE;
        assert currentPair != null;
        return currentPair.sum;
    }
}


//Duplicate sum allowed
class SolutionNthItemInSumOfTwoArraysDuplicateSumAllowed {

    public int nthItem(int[] a, int[] b, int n) {

        if (a == null || a.length == 0) {
            if (b.length >= n)
                return b[n - 1];
            else
                return Integer.MIN_VALUE;
        }

        if (b == null || b.length == 0) {
            if (a.length >= n)
                return a[n - 1];
            else
                return Integer.MIN_VALUE;
        }

        int x = a.length;
        int y = b.length;

        return nthItem(a, b, x, y, n);
    }


    private int nthItem(int[] a, int[] b, int aLength, int bLength, int n) {

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        Set<int[]> seenPair = new HashSet<>();


        for (int i = 0; i < a.length; i++) {
            priorityQueue.offer(new Node(a[i] + b[0], i, 0));
            seenPair.add(new int[]{i, 0});
        }


        int i = 0;
        Node currentPair = null;
        while (i < n && !priorityQueue.isEmpty()) {

            currentPair = priorityQueue.poll();

            int[] pairWithA = {currentPair.ai + 1, currentPair.bi};
            int[] pairWithB = {currentPair.ai, currentPair.bi + 1};

            if (!seenPair.contains(pairWithA) && currentPair.ai + 1 < aLength) {
                seenPair.add(pairWithA);
                priorityQueue.offer(new Node(a[currentPair.ai + 1] + b[currentPair.bi], currentPair.ai + 1, currentPair.bi));
            }

            if (!seenPair.contains(pairWithB) && currentPair.bi + 1 < bLength) {
                seenPair.add(pairWithA);
                priorityQueue.offer(new Node(a[currentPair.ai] + b[currentPair.bi + 1], currentPair.ai, currentPair.bi + 1));
            }
            i++;

        }

        if (i < n)
            return Integer.MAX_VALUE;
        assert currentPair != null;
        return currentPair.sum;
    }
}
