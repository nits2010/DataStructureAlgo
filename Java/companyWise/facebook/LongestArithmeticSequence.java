package Java.companyWise.facebook;

import java.util.HashMap;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-23
 * Description:
 * https://leetcode.com/problems/longest-arithmetic-sequence/
 * Given an array A of integers, return the length of the longest arithmetic sub sequence in A.
 * <p>
 * Recall that a subsequence of A is a list A[i_1], A[i_2], ..., A[i_k] with 0 <= i_1 < i_2 < ... < i_k <= A.length - 1, and that a sequence B is arithmetic if B[i+1] - B[i] are all the same value (for 0 <= i < B.length - 1).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: [3,6,9,12]
 * Output: 4
 * Explanation:
 * The whole array is an arithmetic sequence with steps of length = 3.
 * Example 2:
 * <p>
 * Input: [9,4,7,2,10]
 * Output: 3
 * Explanation:
 * The longest arithmetic subsequence is [4,7,10].
 * Example 3:
 * <p>
 * Input: [20,1,15,3,10,5,8]
 * Output: 4
 * Explanation:
 * The longest arithmetic subsequence is [20,15,10,5].
 */
public class LongestArithmeticSequence {

    public static void main(String args[]) {

        test1();
        test2();
        test3();
    }

    private static void test3() {
        LongestArithmeticSequenceUnsorted sol = new LongestArithmeticSequenceUnsorted();
        boolean pass =
                sol.longestArithSeqLength(new int[]{0, 8, 45, 88, 48, 68, 28, 55, 17, 24}) == 2 &&
                        sol.longestArithSeqLength(new int[]{1, 7, 10, 13, 14, 19}) == 4 &&
                        sol.longestArithSeqLength(new int[]{1, 7, 10, 15, 27, 29}) == 3 &&
                        sol.longestArithSeqLength(new int[]{2, 4, 6, 8, 10}) == 5 &&
                        sol.longestArithSeqLength(new int[]{3, 6, 9, 12}) == 4 &&
                        sol.longestArithSeqLength(new int[]{9, 4, 7, 2, 10}) == 3 &&
                        sol.longestArithSeqLength(new int[]{20, 1, 15, 3, 10, 5, 8}) == 4;

        System.out.println(pass);

    }

    private static void test2() {
        LongestArithmeticSequenceSpaceOptimizedSorted sol = new LongestArithmeticSequenceSpaceOptimizedSorted();

        System.out.println("Running Test Type 2");
        boolean pass =

                test2(sol, new int[]{1, 7, 10, 13, 14, 19}) == 4 &&
                        test2(sol, new int[]{1, 7, 10, 15, 27, 29}) == 3 &&
                        test2(sol, new int[]{2, 4, 6, 8, 10}) == 5 &&
                        test2(sol, new int[]{3, 6, 9, 12}) == 4;
        System.out.println(pass);
    }

    private static void test1() {
        LongestArithmeticSequenceSol1Sorted sol = new LongestArithmeticSequenceSol1Sorted();
        System.out.println("Running Test Type 1");
        boolean pass =

                test1(sol, new int[]{1, 7, 10, 13, 14, 19}) == 4 &&
                        test1(sol, new int[]{1, 7, 10, 15, 27, 29}) == 3 &&
                        test1(sol, new int[]{2, 4, 6, 8, 10}) == 5 &&
                        test1(sol, new int[]{3, 6, 9, 12}) == 4;
        System.out.println(pass);


    }

    private static int test1(LongestArithmeticSequenceSol1Sorted sol, int[] a) {
        return sol.longestArithSeqLength(a);
    }

    private static int test2(LongestArithmeticSequenceSpaceOptimizedSorted sol, int[] a) {
        return sol.longestArithSeqLength(a);
    }
}

class LongestArithmeticSequenceUnsorted {

    public int longestArithSeqLength(int[] a) {

        if (a.length == 2)
            return 2;

        int min = a[0];
        int max = a[0];

        //Find the maximum difference that could possible with array elements
        for (int i : a) {
            if (i < min)
                min = i;
            if (i > max)
                max = i;
        }

        int maxDiff = max - min;
        int dp[][] = new int[a.length][2 * maxDiff + 1];

        int maxLAP = 0;

        for (int i = 1; i < a.length; i++) {

            for (int j = 0; j < i; j++) {
                int diff = a[i] - a[j] + maxDiff;
                dp[i][diff] = Math.max(dp[i][diff], dp[j][diff] + 1);
                maxLAP = Math.max(maxLAP, dp[i][diff]);
            }
        }
        return maxLAP + 1;
    }

    public int longestArithSeqLengthUsingHashMap(int[] a) {
        if (a == null) return 0;

        HashMap<Integer, Integer>[] dp = new HashMap[a.length];
        dp[0] = new HashMap();
        int res = 1;
        for (int i = 1; i < a.length; i++) {
            dp[i] = new HashMap();
            for (int j = i - 1; j >= 0; j--) {
                int diff = a[i] - a[j];
                int prev = dp[j].getOrDefault(diff, 0) + 1;
                int cur = Math.max(dp[i].getOrDefault(diff, 0), prev);

                dp[i].put(diff, cur);

                res = Math.max(res, cur);
            }
        }

        return res + 1;
    }
}


class LongestArithmeticSequenceSol1Sorted {


    /**
     * Explanation: sorted sequence
     * Lets first understand how to find 2 & 3element which are in AP.
     * We know any two number can form AP (1, 6) since they always have a difference which is in AP.
     * To extend this idea, we can find 3 elements in AP s.t. first two elements are always are in AP. Let say we have
     * a[i] , a[j] and a[k];  Say a[j] and a[i] form a AP. Then in order to make (a[i] , a[j] and a[k]) in AP they should have the below condition satisfy
     * <p>
     * a[i] + a[k] = 2*a[j] then only the can make in AP as a[i] -> a[j] -> a[k] { note a[j] is the middle element in AP}
     * <p>
     * To find such combination, we can fix a element in (second element in array) and keep finding i and k on left side and right side of j respectively ( i = j-1..0 and k = j+1..n)
     * <p>
     * Ok.
     * <p>
     * So we can use above logic to find multiple element which are in AP. Lets define LAP[i][j] as
     * <p>
     * LAP[i][j] = length of longest A.P. where two elements are a[i] and a[j].
     * Since every two elements make A.P. of two size then all values of LAP defaulted to 2.
     * <p>
     * To find all i and k we can search like ( i = j-1..0 and k = j+1..n) assuming j as middle and satisfy the condition
     * a[i] + a[k] = 2*a[j]
     * <p>
     * let say some i and k satisfy this condition then
     * LAP[i][j] = LAP[j][k] + 1; if a[i] + a[k] = 2*a[j] otherwise 2
     *
     * @param a
     * @return
     */
    public int longestArithSeqLength(int[] a) {

        if (null == a || a.length == 0)
            return 0;

        int n = a.length;
        final int LAP[][] = new int[n][n];


        int maxLengthAP = 2;

        //every element form a size of 2 A.P. in sorted sequence with last element [ you can choose any element but since we are going to fill table in bottom up so i choose last ]
        for (int i = 0; i < n; i++)
            LAP[i][n - 1] = 2;


        //Keep choosing a element as middle element of A.P.
        for (int j = n - 2; j >= 1; j--) {

            int i = j - 1;
            int k = j + 1;

//            LAP[i][j] = 2 ; //this

            while (i >= 0 && k < n) {

                int e1 = a[i];
                int e2 = a[j];
                int e3 = a[k];

                //does this follow A.P.
                if (e1 + e3 == 2 * e2) {
                    //if yes;
                    LAP[i][j] = LAP[j][k] + 1;
                    maxLengthAP = Math.max(maxLengthAP, LAP[i][j]);
                    i--;
                    k++;
                } else if (e1 + e3 > 2 * e2) {
                    //if we have more then expected, then move i left side
                    LAP[i][j] = 2; //since every element make 2 size AP [ you can run two loop in first place to set this , for efficiency i choose here ]
                    i--;
                } else if (e1 + e3 < 2 * e2)
                    k++;
            }

            //means we close the right side but left side is still there, update it with 2

            while (i >= 0) {
                LAP[i][j] = 2;
                i--;
            }


        }


        return maxLengthAP;
    }
}

/**
 * Notice in above solution we have
 * LAP[i][j] = LAP[j][k] + 1 or 2
 * which means we are using only 1 row at a time
 */
class LongestArithmeticSequenceSpaceOptimizedSorted {
    public int longestArithSeqLength(int[] a) {

        if (null == a || a.length == 0)
            return 0;

        int n = a.length;
        final int LAP[] = new int[n];


        int maxLengthAP = 2;

        for (int i = 0; i < n; i++)
            LAP[i] = 2;


        for (int j = n - 2; j >= 0; j--) {
            int i = j - 1;
            int k = j + 1;

            while (i >= 0 && k < n) {
                if (a[i] + a[k] == 2 * a[j]) {
                    //if yes;
                    LAP[j] = Math.max(LAP[k] + 1, LAP[j]);
                    maxLengthAP = Math.max(maxLengthAP, LAP[j]);
                    i--;
                    k++;
                } else if (a[i] + a[k] > 2 * a[j]) {
                    //if we have more then expected, then move i left side
                    i--;
                } else if (a[i] + a[k] < 2 * a[j])
                    k++;
            }
        }


        return maxLengthAP;

    }
}
