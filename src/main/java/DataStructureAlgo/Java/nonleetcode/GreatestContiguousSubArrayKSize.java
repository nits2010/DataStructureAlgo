package DataStructureAlgo.Java.nonleetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-15
 * Description:https://www.geeksforgeeks.org/greatest-contiguous-sub-array-of-size-k/
 * Given an array arr[] of integers and an integer K, the task is to find the greatest contiguous sub-array of size K. Sub-array X is said to be greater than sub-array Y if the first non-matching element in both the sub-arrays has a greater value in X than in Y.
 * <p>
 * Examples:
 * <p>
 * Input: arr[] = {1, 4, 3, 2, 5}, K = 4
 * Output: 4 3 2 5
 * Two subarrays are {1, 4, 3, 2} and {4, 3, 2, 5}.
 * Hence, the greater one is {4, 3, 2, 5}
 * <p>
 * Input: arr[] = {1, 9, 2, 7, 9, 3}, K = 3
 * Output: 9 2 7
 */
public class GreatestContiguousSubArrayKSize {

    public static void main(String []args) {

        test1();
    }

    private static void test1() {

        int a[] = {1, 4, 3, 2, 5};
        int k = 4;
        System.out.println(greatestSubArray(a, k));

        int b[] = {1, 9, 9, 8, 2, 1};
        k = 3;
        System.out.println(greatestSubArray(b, k));
    }


    private static List<Integer> greatestSubArray(int[] a, int k) {

        List<List<Integer>> list = new ArrayList<>(a.length / k);

        for (int i = 0; i < a.length - k + 1; i++) {

            List<Integer> l = new ArrayList<>();
            for (int j = i; j < i + k; j++)
                l.add(a[j]);

            list.add(l);

        }

        Collections.sort(list, (o1, o2) -> {
            int i = 0;
            while (i < o1.size()) {
                if (o1.get(i) != o2.get(i))
                    break;
                i++;
            }
            if (i == o1.size())
                return 0;

            return o2.get(i).compareTo(o1.get(i));
        });

        return list.get(0);

    }
}
