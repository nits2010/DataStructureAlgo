package Java.nonleetcode;

import Java.helpers.GenericPrinter;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-25
 * Description: https://www.geeksforgeeks.org/subset-no-pair-sum-divisible-k/
 * Description of problems are below
 * <p>
 */
public class NoPairSumDivisibleByK {


    public static void main(String[] args) {
        testSubSet();
        testSubArray();
    }


    private static void testSubSet() {
        System.out.println("Testing Sub-set\n");
        SubSetNoPairSumDivisibleByK subset = new SubSetNoPairSumDivisibleByK();

        testSubSet(new int[]{3, 7, 2, 9, 1}, 3, subset);
        testSubSet(new int[]{3, 17, 12, 9, 11, 15}, 5, subset);
        testSubSet(new int[]{2, 4, 4, 3}, 4, subset);


    }

    private static void testSubSet(int[] nums, int k, SubSetNoPairSumDivisibleByK subset) {
        System.out.println("Input ");
        GenericPrinter.print(nums);
        List<Integer> modulo = subset.maximumSizeSubSetNoPairSumDivisibleByKElements(nums, k);
        System.out.println("Size : " + modulo.size() + " array " + modulo);
    }

    private static void testSubArray() {

        System.out.println("Testing Sub-array\n");
        SubArrayNoPairSumDivisibleByK array = new SubArrayNoPairSumDivisibleByK();

        testSubArray(new int[]{3, 7, 2, 9, 1}, 3, array);
        testSubArray(new int[]{3, 17, 12, 9, 11, 15}, 5, array);
        testSubArray(new int[]{2, 4, 4, 3}, 4, array);
        testSubArray(new int[]{3, 7, 1, 9, 2}, 3, array);

    }

    private static void testSubArray(int[] nums, int k, SubArrayNoPairSumDivisibleByK array) {
        System.out.println("Input ");
        GenericPrinter.print(nums);
        int[] modulo = array.maximumSizeSubSetNoPairSumDivisibleByK(nums, k);
        System.out.println("output ");
        GenericPrinter.print(modulo);
    }
}

/**
 * https://www.geeksforgeeks.org/subset-no-pair-sum-divisible-k/
 * * Given an array of integer numbers, we need to find maximum size of a subset such that sum of each pair of this subset is not divisible by K.
 * * Examples :
 * * <p>
 * * Input :  arr[] = [3, 7, 2, 9, 1]
 * * K = 3
 * * Output : 3
 * * Maximum size subset whose each pair sum
 * * is not divisible by K is [3, 7, 1] because,
 * * 3+7 = 10,
 * * 3+1 = 4,
 * * 7+1 = 8        all are not divisible by 3.
 * * It is not possible to get a subset of size
 * * bigger than 3 with the above-mentioned property.
 * * <p>
 * * Input : arr[] = [3, 17, 12, 9, 11, 15]
 * * K = 5
 * * Output : 4
 * <p>
 * <p>
 * SUB-SET
 * <p>
 * We can solve this problem by computing modulo of array numbers with K.
 * if sum of two numbers is divisible by K, then if one of them gives remainder i, other will give remainder (K – i).
 * First we store frequencies of numbers giving specific remainder in a frequency array of size K. Then we loop for all remainders i and include max(f[i], f[K – i]).
 * Why? a subset with no pair sum divisible by K must include either elements with remainder f[i] or with remainder f[K – i].
 * Since we want to maximize the size of subset, we pick maximum of two sizes.
 * In below code array numbers with remainder 0 and remainder K/2 are handled separately.
 * If we include more than 2 numbers with remainder 0 then their sum will be divisible by K, so we have taken at max 1 number in our consideration,
 * same is the case with array numbers giving remainder K/2.
 */
class SubSetNoPairSumDivisibleByK {

    public int maximumSizeSubSetNoPairSumDivisibleByK(int nums[], int k) {
        if (null == nums || nums.length == 0)
            return 0;

        if (k == 0 || k == 1)
            return nums.length;

        int[] modulo = modulo(nums, k);

        //We can only choose 1 element with %k = 0 , as multiple make pair and it violate the rul
        int max = Math.min(1, modulo[0]);

        //for k/2 when even we can take only 1 element as two element with k/2 module will make pair %k == 0
        if (k % 2 == 0)
            modulo[k / 2] = Math.max(1, modulo[k / 2]);

        //Either choose p or k-p whichever gives better
        for (int p = 1; p <= k / 2; p++) {

            max += Math.max(modulo[p], modulo[k - p]);

        }

        return max;


    }

    private int[] modulo(int nums[], int k) {
        int modulo[] = new int[k];

        for (int i = 0; i < nums.length; i++)
            modulo[nums[i] % k]++;

        return modulo;
    }


    public List<Integer> maximumSizeSubSetNoPairSumDivisibleByKElements(int nums[], int k) {
        if (null == nums || nums.length == 0)
            return Collections.EMPTY_LIST;

        if (k == 0 || k == 1) {


        }


        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] modulo = modulo(nums, k, map);
        List<Integer> sol = new ArrayList<>();

        //We can only choose 1 element with %k = 0 , as multiple make pair and it violate the rul
        if (map.containsKey(0)) {
            sol.add(map.get(0).get(0));
        }
        int max = Math.min(1, modulo[0]);

        //for k/2 when even we can take only 1 element as two element with k/2 module will make pair %k == 0
        if (k % 2 == 0) {
            modulo[k / 2] = Math.max(1, modulo[k / 2]);

            if (map.containsKey(k / 2)) {
                sol.add(map.get(k / 2).get(0));
            }
        }

        //Either choose p or k-p whichever gives better
        for (int p = 1; p <= k / 2; p++) {

            if (modulo[p] > modulo[k - p]) {
                max += modulo[p];
                sol.addAll(map.get(p));
            } else {
                max += modulo[k - p];
                sol.addAll(map.get(k - p));
            }


        }

        return sol;


    }

    private int[] modulo(int[] nums, int k, Map<Integer, List<Integer>> map) {

        int modulo[] = new int[k];

        for (int i = 0; i < nums.length; i++) {
            int mod = nums[i] % k;
            modulo[mod]++;

            List<Integer> orDefault = map.getOrDefault(mod, new ArrayList<>());
            orDefault.add(nums[i]);
            map.put(mod, orDefault);

        }

        return modulo;

    }


}

/**
 * https://www.geeksforgeeks.org/subarray-no-pair-sum-divisible-k/
 * Given an array of N non-negative integers, task is to find the maximum size of a subarray such that the pairwise sum of the elements of this subarray is not divisible by a given integer, K.
 * Also, print this subarray as well. If there are two or more subarrays which follow the above stated condition, then print the first one from the left.
 * <p>
 * Prerequisite : {@link SubSetNoPairSumDivisibleByK}
 * <p>
 * Examples :
 * <p>
 * Input : arr[] = [3, 7, 1, 9, 2]
 * K = 3
 * Output : 3
 * [3, 7, 1]
 * 3 + 7 = 10, 3 + 1 = 4, 7 + 1 = 8, all are
 * not divisible by 3.
 * It is not possible to get a subarray of size bigger
 * than 3 with the above-mentioned property.
 * [7, 1, 9] is also of the same size but [3, 7, 1] comes first.
 * <p>
 * Input : arr[] = [2, 4, 4, 3]
 * K = 4
 * Output : 2
 * [2, 4]
 * 2 + 4 = 6 is not divisible by 4.
 * It is not possible to get a subarray of size bigger
 * than 2 with the above-mentioned property.
 * [4, 3] is also of the same size but [2, 4] comes first.
 */
class SubArrayNoPairSumDivisibleByK {

    public int[] maximumSizeSubSetNoPairSumDivisibleByK(int nums[], int k) {
        if (null == nums || nums.length == 0)
            return new int[0];

        if (k == 0 || k == 1)
            return nums;

        Map<Integer, Integer> modulo = new HashMap<>();
        int start = 0, end = 0;

        modulo.put(nums[0] % k, 1);

        int max = 0;
        int maxStart = 0;
        int maxEnd = 0;

        for (int i = 1; i < nums.length; i++) {

            int currM = nums[i] % k;

            //see this is zero (0) and is there any element already exist
            //see the other part of module means k-mod exist in table, if yes then remove it \
            while (start < i && modulo.containsKey(k - currM) || (currM == 0 && modulo.containsKey(0) && modulo.get(0) > 0)) {// that means we already included this element

                //remove this element
                modulo.put(nums[start] % k, modulo.getOrDefault(nums[start] % k, 0) - 1);
                start++;
            }

            //include this element
            end++;
            modulo.put(currM, modulo.getOrDefault(currM, 0) + 1);

            if (end - start + 1 > max) {
                max = end - start + 1;
                maxStart = start;
                maxEnd = end;
            }
        }

        int result[] = new int[max];
        for (int i = maxStart; i <= maxEnd; i++) {
            result[i - maxStart] = nums[i];
        }


        return result;


    }


}