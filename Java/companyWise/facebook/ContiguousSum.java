package Java.companyWise.facebook;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-31
 * Description: You’re given an array of integer and a number "sum", find does any subarray exist which sum up to given "sum".
 * Facebook 2019
 */
public class ContiguousSum {
    public static void main(String[] args) {
        System.out.println(contiguousSum(new int[]{3, 5, 6, 3}, 11));
        System.out.println(contiguousSum(new int[]{3, 5, 6, 9}, 9));
        System.out.println(contiguousSum(new int[]{3, 5, 6, 9}, 14));
        System.out.println(contiguousSum(new int[]{3, 5, 6, 9}, 23));
        System.out.println(contiguousSum(new int[]{3, 5, 6, 9}, 24));
        System.out.println(contiguousSum(new int[]{3, 5, 6, 9}, 15));
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
