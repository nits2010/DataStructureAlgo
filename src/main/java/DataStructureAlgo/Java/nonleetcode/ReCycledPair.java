package DataStructureAlgo.Java.nonleetcode;

import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.Pair;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-07
 * Description: https://www.geeksforgeeks.org/number-recycled-pairs-array/
 * Given an array of integers arr[], find the number of recycled pairs in the array. A recycled pair of two numbers {a, b} has the following properties :
 * <p>
 * A should be smaller than B.
 * Number of digits should be same.
 * By rotating A any number of times in one direction, we should get B.
 * <p>
 * Input : arr[] = {32, 42, 13, 23, 9, 5, 31}
 * Output : 2
 * Explanation : Since there are two pairs {13, 31} and {23, 32}.
 * By rotating 13 for first time, output is 31 and by rotating 23 once output is 32.
 * Both of these pairs satisfy our criteria.
 * <p>
 * Input : arr[] = {1212, 2121}
 * Output : 1
 * Explanation : Since there are two pairs {1212, 2121}. By rotating 1212
 * for first time, output is 2121. This pair satisfies our criteria.
 * Note that if rotation id done further, rotating 1212 again output is 1212
 * which is given number and 2121 which has been already counted.
 * So discard both of these results.
 */
public class ReCycledPair {

    public static void main(String[] args) {
        test(new int[]{32, 42, 301, 23, 9, 5, 130}, new int[][]{{301, 130}, {32, 23}});
    }

    private static void test(int[] nums, int[][] expected) {
        System.out.println("\nNums: " + CommonMethods.toString(nums) + "\nExpected    :" + CommonMethods.toString(expected));

        SolutionReCycledPair sol = new SolutionReCycledPair();

        System.out.println("Solution1   :" + CommonMethods.toString(sol.recycledPairs1(nums)));
        System.out.println("Solution2   :" + CommonMethods.toString(sol.recycledPairs2(nums)));
    }


}

class SolutionReCycledPair {

    //======= Time Complexity: O(n * R * L); n= length of input, R is max length of a digit, L= max unique rotated number
    public int[][] recycledPairs1(int[] a) {
        if (null == a || a.length == 0)
            return null;

        int[] arr = removeDuplicates(a);

        int len = arr.length;
        Set<Pair<Integer, Integer>> pairs = new HashSet<>(len);
        Map<Integer, Set<Integer>> map = new HashMap<>();

        //O(n)
        for (Integer i : arr) {

            int digits = (int) Math.log10(i) + 1;
            int pow = (int) Math.pow(10, digits - 1);
            int rotation = digits;
            int number = i;

            //O(R)
            while (rotation > 0) {
                int rotatedNumber = rotate(number, pow);

                if (map.containsKey(rotatedNumber)) {

                    //O(L)
                    for (Integer x : map.get(rotatedNumber)) {
                        if (!x.equals(i))
                            pairs.add(new Pair<>(i, x));
                    }
                    map.get(rotatedNumber).add(i);
                } else {
                    Set<Integer> l = new HashSet<>();
                    l.add(i);
                    map.put(rotatedNumber, l);

                }
                number = rotatedNumber;
                rotation--;
            }


        }
        int[][] output = new int[pairs.size()][2];
        int i = 0;
        for (Pair<Integer, Integer> p : pairs) {
            output[i++] = new int[]{p.getKey(), p.getValue()};
        }
        return output;
    }


    //=============== Time Complexity: O(n * R * log(n)); n= length of input, R is max length of a digit
    public int[][] recycledPairs2(int[] nums) {
        if (null == nums || nums.length == 0)
            return null;

        int[] arr = removeDuplicates(nums);
        int len = arr.length;
        Set<Integer> pairs = new HashSet<>(len);
        Set<Pair<Integer, Integer>> res = new HashSet<>();

        //O(n)
        for (int i = 0; i < arr.length; i++) {
            pairs.clear();

            int number = arr[i];

            int digits = (int) Math.log10(number) + 1;
            int pow = (int) Math.pow(10, digits - 1);
            int rotation = digits;

            //O(R)
            while (rotation > 0) {

                int rotatedNumber = rotate(number, pow);

                int digitsInRotatedNumber = (int) Math.log10(rotatedNumber) + 1;

                if (!pairs.contains(rotatedNumber)) {
                    pairs.add(rotatedNumber);

                    if (digits == digitsInRotatedNumber) {

                        //O(log(n)
                        int pos = Arrays.binarySearch(arr, i + 1, arr.length, rotatedNumber);
                        if (pos > 0) {
                            res.add(new Pair<>(arr[i], rotatedNumber));
                        }
                    }

                }
                number = rotatedNumber;
                rotation--;
            }

        }
        int[][] output = new int[res.size()][2];
        int i = 0;
        for (Pair<Integer, Integer> p : res) {
            output[i++] = new int[]{p.getKey(), p.getValue()};
        }
        return output;

    }


    private int[] removeDuplicates(int[] a) {
        Arrays.sort(a);
        int[] output = new int[a.length];
        int o = 0;
        for (int i = 0; i < a.length; i++) {
            int x = a[i];

            while (i < a.length && a[i] == x)
                i++;

            output[o++] = x;
            i--;
        }
        return Arrays.copyOfRange(output, 0, o);

    }

    private int rotate(int number, int pow) {
        int r = number % 10;
        int q = number / 10;
        return r * pow + q;
    }
}
