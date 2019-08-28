package Java.LeetCode.pair.element.problems.modulo;

import Java.HelpersToPrint.Printer;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-25
 * Description:https://www.geeksforgeeks.org/find-pairs-b-array-b-k/
 * Find all pairs (a, b) in an array such that a % b = k
 * Given an array with distinct elements, the task is to find the pairs in the array such that a % b = k, where k is a given integer.
 * <p>
 * Examples :
 * <p>
 * Input  :  arr[] = {2, 3, 5, 4, 7}
 * k = 3
 * Output :  (7, 4), (3, 4), (3, 5), (3, 7)
 * 7 % 4 = 3
 * 3 % 4 = 3
 * 3 % 5 = 3
 * 3 % 7 = 3
 */
public class AModKPairs {

    public static void main(String[] args) {

        test(new int[]{2, 3, 5, 4, 7}, 3);
    }

    private static void test(int[] nums, int k) {
        System.out.println(Printer.toString(nums) + " k " + k);
        System.out.println(Printer.toString(pairs(nums, k)));
    }

    /*
    We can run through every pair and check the validity of a % b = k, if satisfy then count it. O(n^2).

    The important observation is a % b = k is possible when either of condition satisfy
    1. if b > k and a < k then a % b = k .
    2. if K present in the array, then all element which are > k will form a pair
    3. a % b = k =>
    => a = x * b + k
    => (a - k) = x*b
    i.e all element x*b which follow above equation follow a % b = k.
    x*b is all the divisor of (a-k). So we'll find all the division of a-k in the given array and count them.


    Hence:
    1. Find does k present in Array, if so find all the element greater than k and count them as a pair.
    2. Find all the divisor of (a[i]- k ) in the array, that also form the pair.

    We can find all divisor a number n in sqrt(n) time.

     */


    /**
     * 16 -> [2, 8, 4 ]
     *
     * @param n
     * @return
     */
    private static List<Integer> divisor(int n) {
        List<Integer> divisor = new ArrayList<>();

        /**
         * test each number is divisor of n or not
         */
        for (int i = 1; i <= Math.sqrt(n); i++) {

            if (n % i == 0) {
                if (n / i == i) // 16 , 4 since 16 is square of 4
                    divisor.add(i);
                else { // 16 , 2 then both 2 and 16/2 = 8 is divisor
                    divisor.add(i);
                    divisor.add(n / i);
                }

            }
        }

        return divisor;
    }


    private static List<int[]> pairs(int nums[], int k) {

        if (nums == null || nums.length == 0 || k == 0)
            return Collections.EMPTY_LIST;

        final List<int[]> pairs = new ArrayList<>();

        Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        boolean hasK = set.contains(k);

        for (int i = 0; i < nums.length; i++) {

            /**
             *  Find does k present in Array, if so find all the element greater than k and count them as a pair.
             */
            if (hasK && nums[i] > k) {
                pairs.add(new int[]{k, nums[i]});
            }

            /**
             * For rest of all
             */
            if (nums[i] >= k) {

                final List<Integer> divisor = divisor(nums[i] - k);

                for (int d : divisor) {

                    if (set.contains(d) && nums[i] % d == k) {
                        if (d != nums[i]) //avoid duplicate pair
                            pairs.add(new int[]{d, nums[i]});
                    }

                }

            }


        }
        return pairs;


    }
}
