package Java.companyWise.facebook;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-19
 * Description: https://aonecode.com/aplusplus/interviewctrl/getInterview/8817620605028273663
 * Given many coins of 3 different face values, print the combination sums of the coins up to 1000. Must be printed in order.
 * <p>
 * eg: coins(10, 15, 55)
 * print:
 * 10
 * 15
 * 20
 * 25
 * 30
 * 35
 * <p>
 * .
 * .
 * .
 * 1000
 */
public class CombinationalSum {

    public static void main(String args[]) {

        int arr[] = {10, 15, 55};
        int sum = 1000;

        System.out.println(combinationSum(sum, arr));
    }

    /**
     * Observation: we have given coins arr[0...n-1] we can take any coin any number of time and we need to make the sum up to given Sum.
     * <p>
     * One way to using dynamic programming, which tells you how many way you can make it (coin change problem)
     * <p>
     * but here we need to find all.
     * <p>
     * IF we take closer look, we are not bound to use any coin limited number of times. What we do need to do is print
     * them in ORDER.
     * so before printing any, we need to see have we print the smaller than this or not (if possible), if not then only print it.
     * <p>
     * if given array is not sorted, then sort the array to print them in order.
     *
     * @param Sum
     * @param arr
     * @return
     */
    private static List<Integer> combinationSum(int Sum, int arr[]) {

        //to print the element only once, not repeated
        Set<Integer> sumSet = new HashSet<>();
        List<Integer> combinationSum = new ArrayList<>(Sum);

        sumSet.add(0); //to start the sum; if 0 is also a in arr then it won't affect as;

        //Try every sum, which can be made up using arr coins
        for (int sum = 1; sum <= Sum; sum++) {

            for (int i = 0; i < arr.length; i++) {

                //check using this coin, sum is possible or not
                if (sumSet.contains(sum - arr[i])) {
                    combinationSum.add(sum);
                    sumSet.add(sum);
                    break;
                }


            }
        }
        return combinationSum;
    }

}
