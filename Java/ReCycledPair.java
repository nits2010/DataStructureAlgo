package Java;

import javafx.util.Pair;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
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

    public static void main(String args[]) {

        SolutionReCycledPair sol = new SolutionReCycledPair();
        int a[] = {32, 42, 301, 23, 9, 5, 130};
        Set<Pair<Integer, Integer>> pairs = sol.recycledPairs(a);
        System.out.println("Total :" + pairs.size());
        pairs.stream().forEach(p -> System.out.println(" first :" + p.getKey() + " second " + p.getValue()));

    }


}

class SolutionReCycledPair {

    public final Set<Pair<Integer, Integer>> recycledPairs(int a[]) {

        if (null == a || a.length == 0)
            return new HashSet<>();


        int n = a.length;

        Arrays.sort(a);

        List<Integer> arr = removeDuplicates(a, n);


        final Set<Pair<Integer, Integer>> pairs = getPairsSol1(arr);
        return pairs;

    }



    private int getRotatedNumber(int number, int pow) {
        int r = number % 10;
        int q = number / 10;
        return r * pow + q;
    }

    private Set<Pair<Integer, Integer>> getPairsSol1(List<Integer> arr) {

        int len = arr.size();
        Set<Pair<Integer, Integer>> pairs = new HashSet<>(len);
        Map<Integer, Set<Integer>> map = new HashMap<>();

        for (Integer i : arr) {

            int digits = (int) Math.log10(i) + 1;
            int pow = (int) Math.pow(10, digits - 1);
            int rotation = digits;
            int number = i;

            while (rotation > 0) {


                int rotatedNumber = getRotatedNumber(number, pow);

                if (map.containsKey(rotatedNumber)) {

                    for (Integer x : map.get(rotatedNumber)) {
                        if (x != i)
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

        return pairs;
    }


    private final List<Integer> removeDuplicates(int a[], int n) {

        List<Integer> temp = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            int x = a[i];

            while (i < n && a[i] == x)
                i++;

            temp.add(x);
            i--;
        }

        return temp;

    }



    ////////

    private Set<Pair<Integer, Integer>> getPairsSol2(List<Integer> arr) {
        int len = arr.size();
        Set<Integer> pairs = new HashSet<>(len);
        Set<Pair<Integer, Integer>> res = new HashSet<>();

        int[] temp = arr.stream().mapToInt(i -> i).toArray();

        for (int i = 0; i < temp.length; i++) {
            pairs.clear();

            int number = temp[i];

            int digits = (int) Math.log10(number) + 1;
            int pow = (int) Math.pow(10, digits - 1);
            int rotation = digits;

            while (rotation > 0) {

                int rotatedNumber = getRotatedNumber(number, pow);

                int digitsInRotatedNumber = (int) Math.log10(rotatedNumber) + 1;

                if (!pairs.contains(rotatedNumber)) {
                    pairs.add(rotatedNumber);

                    if (digits == digitsInRotatedNumber) {

                        int pos = Arrays.binarySearch(temp, i + 1, temp.length, rotatedNumber);
                        if (pos > 0) {
                            res.add(new Pair<>(temp[i], rotatedNumber));
                        }
                    }

                }
                number = rotatedNumber;
                rotation--;
            }

        }

        return res;

    }
}
