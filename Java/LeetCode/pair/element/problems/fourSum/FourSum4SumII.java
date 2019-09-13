package Java.LeetCode.pair.element.problems.fourSum;

import Java.HelpersToPrint.Printer;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-16
 * Description: https://leetcode.com/problems/4sum-ii/
 * Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are
 * such that A[i] + B[j] + C[k] + D[l] is zero.
 * To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500.
 * All integers are in the range of -2^28 to 2^28 - 1 and the result is guaranteed to be at most 2^31 - 1.
 * <p>
 * Example:
 * <p>
 * Input:
 * A = [ 1, 2]
 * B = [-2,-1]
 * C = [-1, 2]
 * D = [ 0, 2]
 * Output:
 * 2
 * Explanation:
 * The two tuples are:
 * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 */
public class FourSum4SumII {

    public static void main(String[] args) {
        test(new int[]{1, 2}, new int[]{-2, -1}, new int[]{-1, 2}, new int[]{0, 2}, 2);
        test(new int[]{1, 2, 0}, new int[]{-2, -1, 3}, new int[]{-1, 2, -2}, new int[]{0, 2, -1}, 2);
        test(new int[]{1, 2, 0, 4}, new int[]{-2, -1, 3, -1}, new int[]{-1, 2, -2, -2}, new int[]{0, 2, -1, -1}, 2);
    }

    private static void test(int[] a, int[] b, int[] c, int[] d, int expected) {

        System.out.println("input A :" + Printer.toString(a) + " B :" + Printer.toString(b) + " c :" + Printer.toString(c) + " d :" + Printer.toString(d)
                + "\n expected :" + expected);
        IFourSum4SumII sorting = new FourSum4SumIISorting();
        IFourSum4SumII hashing = new FourSum4SumIIHashing();

        System.out.println("Sorting: " + sorting.fourSumCount(a, b, c, d));
        System.out.println("Hashing: " + hashing.fourSumCount(a, b, c, d));
    }
}

interface IFourSum4SumII {
    int fourSumCount(int[] a, int[] b, int[] c, int[] d);
}


/**
 * Reduce the problem to two sum problem
 * Algo:
 * Compute Sum List-> A+B for all
 * Compute diff List-> -(C+D) for all [ note here  -(c+d) not (c+d) . a+b+c+d => a+b = -(c+d) ]
 * For each element in sum list -> element
 * Find how many times it occurred in Diff list and add that count
 * To Find occurrence, we can use binary search
 * <p>
 * Find the index of first occurrence of element
 * find the index of last occurrence of element
 * count = last-fist+1
 * <p>
 * Runtime: 271 ms, faster than 5.10% of Java online submissions for 4Sum II.
 * Memory Usage: 51.8 MB, less than 88.00% of Java online submissions for 4Sum II.
 */
class FourSum4SumIISorting implements IFourSum4SumII {

    @Override
    public int fourSumCount(int[] a, int[] b, int[] c, int[] d) {
        return fourSumCount(a, b, c, d, 0);
    }

    private int fourSumCount(int[] a, int[] b, int[] c, int[] d, int target) {


        ArrayList<Integer> sumPairs = new ArrayList<>();
        ArrayList<Integer> diffPairs = new ArrayList<>();


        int n = a.length;
        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                /*
                 * Convert those array in elements such a way that for elemnets sum become zero.
                 * a + b + c + d = target
                 * a + b = target - (c+d)
                 */
                sumPairs.add(a[i] + b[j]);
                diffPairs.add(target - (c[i] + d[j]));
            }
        }

        //sort them to reduce it to TwoSum
        Collections.sort(sumPairs);
        Collections.sort(diffPairs);

        int count = 0;
        for (int element : sumPairs) {

            int countOfElement = binarySearch(diffPairs, element);
            if (countOfElement != -1)
                count += countOfElement;
        }

        return count;

    }

    /**
     * {@link Java.LeetCode.FindFirstLastPositionElementSortedArray}
     *
     * @param diffPairs
     * @param element
     * @return
     */
    private int binarySearch(ArrayList<Integer> diffPairs, int element) {
        if (diffPairs == null || diffPairs.isEmpty())
            return -1;

        int first = firstIndex(diffPairs, element);
        if (first == -1)
            return -1;

        int last = lastIndex(diffPairs, element);

        return last - first + 1;

    }


    private int firstIndex(ArrayList<Integer> diffPairs, int element) {
        if (diffPairs == null || diffPairs.isEmpty())
            return -1;

        int low = 0, high = diffPairs.size() - 1;

        while (low <= high) {

            int mid = low + (high - low) >> 1;

            int midV = diffPairs.get(mid);

            if ((low == mid && midV == element) || (midV == element && diffPairs.get(mid - 1) < element))
                return mid;
            else if (midV < element)
                low = mid + 1;
            else
                high = mid - 1;
        }

        return -1;
    }


    private int lastIndex(ArrayList<Integer> diffPairs, int element) {
        if (diffPairs == null || diffPairs.isEmpty())
            return -1;

        int low = 0, high = diffPairs.size() - 1;

        while (low <= high) {
            int mid = (low + high) >> 1;
            int midV = diffPairs.get(mid);

            if ((high == mid && midV == element) || (midV == element && diffPairs.get(mid + 1) > element))
                return mid;
            else if (midV > element)
                high = mid - 1;
            else
                low = mid + 1; //mid already been tested
        }

        return -1;
    }
}


/**
 * Count frequency of A+B.
 * Then For every -(C+D) see how many times it occurred in frequency map
 * <p>
 * Runtime: 73 ms, faster than 79.17% of Java online submissions for 4Sum II.
 * Memory Usage: 59.2 MB, less than 44.00% of Java online submissions for 4Sum II.
 */
class FourSum4SumIIHashing implements IFourSum4SumII {

    @Override
    public int fourSumCount(int[] a, int[] b, int[] c, int[] d) {

        Map<Integer, Integer> map = new HashMap<>();

        int count = 0;

        //cache them
        for (int x : a) {
            for (int y : b) {
                int sum = x + y;
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }

        //find them
        for (int x : c) {
            for (int y : d) {
                int diff = -(x + y);
                count += map.getOrDefault(diff, 0);
            }
        }


        return count;
    }
}