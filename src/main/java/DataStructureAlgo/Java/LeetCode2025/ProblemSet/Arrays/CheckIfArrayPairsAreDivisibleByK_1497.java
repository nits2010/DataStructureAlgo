package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Author: Nitin Gupta
 * Date: 10/2/2024
 * Question Category: 1497. Check If Array Pairs Are Divisible by k
 * Description: https://leetcode.com/problems/check-if-array-pairs-are-divisible-by-k/description/
 * Given an array of integers arr of even length n and an integer k.
 * <p>
 * We want to divide the array into exactly n / 2 pairs such that the sum of each pair is divisible by k.
 * <p>
 * Return true If you can find a way to do that or false otherwise.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [1,2,3,4,5,10,6,7,8,9], k = 5
 * Output: true
 * Explanation: Pairs are (1,9),(2,8),(3,7),(4,6) and (5,10).
 * Example 2:
 * <p>
 * Input: arr = [1,2,3,4,5,6], k = 7
 * Output: true
 * Explanation: Pairs are (1,6),(2,5) and(3,4).
 * Example 3:
 * <p>
 * Input: arr = [1,2,3,4,5,6], k = 10
 * Output: false
 * Explanation: You can try all possible pairs to see that there is no way to divide arr into 3 pairs each with sum divisible by 10.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * arr.length == n
 * 1 <= n <= 105
 * n is even.
 * -109 <= arr[i] <= 109
 * 1 <= k <= 105
 * <p>
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @HashTable
 * @Counting <p><p>
 * Company Tags
 * -----
 * @Quble <p><p>
 * @Editorial https://leetcode.com/problems/check-if-array-pairs-are-divisible-by-k/editorial/
 */
public class CheckIfArrayPairsAreDivisibleByK_1497 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[]{1, 2, 3, 4, 5, 10, 6, 7, 8, 9}, 5, true);
        test &= test(new int[]{1, 2, 3, 4, 5, 6}, 7, true);
        test &= test(new int[]{1, 2, 3, 4, 5, 6}, 10, false);
        test &= test(new int[]{-1, 1, -2, 2, -3, 3, -4, 4}, 3, true);
        CommonMethods.printResult(test);
    }

    private static boolean test(int[] arr, int k, boolean expected) {
        System.out.println("----------------------------------");
        System.out.println("Input :" + Arrays.toString(arr) + " k : " + k + " expected : " + expected);

        boolean output;
        boolean pass;
        boolean finalPass = true;

        SolutionUsingHashmap solutionUsingHashmap = new SolutionUsingHashmap();
        output = solutionUsingHashmap.canArrange(arr, k);
        pass = output == expected;
        System.out.println("Output Map : " + output + " Pass : " + (pass ? "Pass" : "Failed"));
        finalPass &= pass;

        SolutionUsingArray solutionUsingArray = new SolutionUsingArray();
        output = solutionUsingArray.canArrange(arr, k);
        pass = output == expected;
        System.out.println("Output Array : " + output + " Pass : " + (pass ? "Pass" : "Failed"));
        finalPass &= pass;

        SolutionSortingTwoPointers solutionSortingTwoPointers = new SolutionSortingTwoPointers();
        output = solutionSortingTwoPointers.canArrange(arr, k);
        pass = output == expected;
        System.out.println("Output Two pointers : " + output + " Pass : " + (pass ? "Pass" : "Failed"));
        finalPass &= pass;

        return finalPass;
    }

    /**
     * (ai+aj)%k=0
     * => ( ai % k + aj % k) % k = 0
     * => (mod(ai) + mod(aj)) % k = 0
     * i.e. mod(ai) = k - mod(aj)
     * <p>
     * Since numbers can be negative, we have to handle them separately. To avoid, we can add K back post-modulo to make the number positive.
     * example:
     * x = -2 , k=3
     * then x % k = -2
     * and then (-2 + 3) = 1 which is round number of a negative number, which can be further take modulo down with k
     * <p>
     * hence a reminder would be
     * reminder = ( x % k + k ) % k
     * https://leetcode.com/problems/check-if-array-pairs-are-divisible-by-k/editorial/
     *
     * T/S : O(n) / O(n)
     */
    static class SolutionUsingHashmap {
        public boolean canArrange(int[] arr, int k) {
            if (arr == null || arr.length % 2 != 0)
                return false;

            final Map<Integer, Integer> reminderMap = new HashMap<>(k);

            //count the frequency of each element under % k in reminderMap
            for (int ele : arr) {
                int mod = reminder(ele, k);

                reminderMap.put(mod,
                        reminderMap.getOrDefault(mod, 0) + 1);

            }

            for (int ele : arr) {
                int mod = reminder(ele, k); //mod(ai)

                //if the reminder is zero, then abs(ele) = k
                //to make them in pair, they should be even count
                if (mod == 0) {
                    if (reminderMap.get(mod) % 2 != 0)
                        return false;
                } else {

                    //other element would be ( k - mod(aj) )
                    int other = k - mod; // other element that needs to be present; i = 2, k = 3 then mod = 2 then there must be a 1 present in a map to make a pair

                    //the count should be the same, as only they will make pairs
                    if (!Objects.equals(reminderMap.get(mod), reminderMap.get(other)))
                        return false;
                }
            }

            return true;
        }

        private int reminder(int x, int k) {
            //if an element were negative, then we have to turn it to a positive reminder
            return ((x % k) + k) % k;


        }
    }

    /**
     * Just use array instead of map
     * T/S : O(n) / O(n)
     */
    static class SolutionUsingArray {

        public boolean canArrange(int[] arr, int k) {
            if (arr == null || arr.length % 2 != 0)
                return false;
            int []reminderFreq = new int[k];
            int index = 0;

            for (int ele : arr){
                reminderFreq[reminder(ele, k)]++;
            }

            //if the reminder is zero, then abs(ele) = k
            //to make them in pair, they should be even count
            if(reminderFreq[0] % 2 != 0)
                return false;

            //other element would be ( k - mod(aj) )
            for(int i = 1; i<k; i++){
                //the count should be the same, as only they will make pairs
                if(reminderFreq[i] != reminderFreq[k - i]){
                    return false;
                }
            }

            return true;

        }

        private int reminder(int x, int k) {
            //if an element were negative, then we have to turn it to a positive reminder
            return ((x % k) + k) % k;
        }
    }
    /**
     * (ai+aj)%k=0
     * => ( ai % k + aj % k) % k = 0
     * => (mod(ai) + mod(aj)) % k = 0
     * i.e. mod(ai) = k - mod(aj)
     *
     * if we sort such array which store reminder of each element, then they will separate apart in each corner of the array.
     * [1,2,3,4,5,6] k = 7
     * reminder array/map & sorted {it is sorted already since a source is sorted}
     * [1, 2, 3, 4, 5, 6]
     *  (1,6), (2,5). (3,4) all are separated at each corner, hence two pointers can be used
     */
    static class SolutionSortingTwoPointers {
        public boolean canArrange(int[] arr, int k) {
            if (arr == null || arr.length % 2 != 0)
                return false;

            int[] reminders = new int[arr.length];

            for(int i = 0; i<arr.length ; i++)
                reminders[i] = reminder(arr[i], k);

            Arrays.sort(reminders);
            int i  = 0, j = arr.length - 1;

            //skip 0's, all zero would have come together only.
            while (i < j) {

                //if this element is zero, then next has to be zero to make the pair
                if(reminders[i] == 0 ) {
                    if (reminders[i + 1] != 0)
                        return false;

                    i+=2; //two element counted
                }else
                    break;
            }

            while(i < j){

                if((reminders[i] + reminders[j]) % k != 0)
                    return false;
                i++;
                j--;
            }

            return true;
        }

        private int reminder(int x, int k) {
            //if an element were negative, then we have to turn it to a positive reminder
            return ((x % k) + k) % k;
        }
    }
}
