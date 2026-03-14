package DataStructureAlgo.Java.companyWise.facebook;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-31
 * Question Title: Contiguous Sum
 * Link: https://leetcode.com/problems/contiguous-sum/
 * Description:
 * Description: You’re given an array of integer and a number "sum", find does any subarray exist which sum up to given "sum".
 * Facebook 2019
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class ContiguousSum {
    public static void main(String[] args) {
        System.out.println(contiguousSum(new int[]{3, 5, 6, 3}, 11));
        System.out.println(contiguousSum(new int[]{3, 5, 6, 9}, 9));
        System.out.println(contiguousSum(new int[]{3, 5, 6, 9}, 14));
        System.out.println(contiguousSum(new int[]{3, 5, 6, 9}, 23));
        System.out.println(contiguousSum(new int[]{3, 5, 6, 9}, 24));
        System.out.println(contiguousSum(new int[]{3, 5, 6, 9}, 15));
        System.out.println(contiguousSum(new int[]{1,2,3,7,-3,4}, 0));
    }

    private static boolean contiguousSum(int a[], int sum) {

        int s = 0;
        int cSum = 0;
        for (int e = 0; e < a.length; e++) {

            if (a[e] == sum)
                return true;
            cSum += a[e];

            while (cSum >= sum && s < e) {
                if (cSum == sum)
                    return true;

                cSum -= a[s];
                s++;
            }


        }
        return false;
    }
}
