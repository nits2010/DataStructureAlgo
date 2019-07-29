package Java.companyWise.facebook;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-21
 * Description: https://leetcode.com/problems/number-of-subarrays-with-bounded-maximum/
 * <p>
 * https://aonecode.com/aplusplus/interviewctrl/getInterview/7243247199135871999
 * We are given an array A of positive integers, and two positive integers L and R (L <= R).
 * <p>
 * Return the number of (contiguous, non-empty) sub-arrays such that the value of the maximum array element in that subarray is at least L and at most R.
 * <p>
 * Example :
 * Input:
 * A = [2, 1, 4, 3]
 * L = 2
 * R = 3
 * Output: 3
 * Explanation: There are three subarrays that meet the requirements: [2], [2, 1], [3].
 * Note:
 * <p>
 * L, R  and A[i] will be an integer in the range [0, 10^9].
 * The length of A will be in the range of [1, 50000].
 * [FACEBOOK]
 */
public class NumberSubArraysBoundedMaximum {

    public static void main(String args[]) {

        SolutionNumberSubArraysBoundedMaximum sol = new SolutionNumberSubArraysBoundedMaximum();
        System.out.println(sol.countSubArrays(new int[]{2, 4, 3, 1, 8, 7, 6}, 2, 6));
        System.out.println(sol.countSubArrays(new int[]{2, 1, 4, 3}, 2, 3));
        System.out.println(sol.countSubArrays(new int[]{2, 1, 4, 3}, 2, 4));
        System.out.println(sol.countSubArrays(new int[]{2, 4, 3, 8, 7, 1, 6}, 2, 6));
        System.out.println(sol.countSubArrays(new int[]{2, 1, 3}, 2, 3));
    }

}

class SolutionNumberSubArraysBoundedMaximum {

    /**
     * As we know we need to find the "sub-arrays", we need to keep track of it from where the sub-array start
     * and it ends. The number of sub-arrays depends on this two index (Start and ends).
     * <p>
     * As element withing this range will be satisfy the condition, we can simply count by how many sub-arrays would be there
     * which is (end-start) = x => x + (x-1) + (x-2) .... 1 [ formula ]
     * As from start to end, increasing start also give you another sub-array which follow condition.
     * <p>
     * But there is a catch.
     * <p>
     * if any element within sub-array is < low then there is two things happen
     * 1. The whole sub-array will definitely make a required sub-array following condition regardless the element index < l
     * 2. but sub-array at this index (element  < l) would not make any sub-array as this won't follow this condition. Hence we need
     * to discard those sub-arrays, in which the elements are < l.
     * <p>
     * So, how do we find those sub-arrays which need to consider and discard.
     * Let say we have a sub-array start at "start" index and ends at "end" index
     * <p>
     * Let say: indexes which are < l are [p..q]
     * <p>
     * If "start<p && end>q" then we can consider these sub-arrays (by using formula).
     * and those sub-array [p,..q] we can discard and reduce them from above.
     * <p>
     * Otherwise element > r then we need to discard whole sub-array and start a new sub-array.
     * <p>
     * Note: to simplify the case of [p..q] we can keep a indexer which tells where it see a element which was not in the range [l,r], then we simply discard all before current index
     *
     * @param a
     * @param l
     * @param r
     * @return
     */

    public int countSubArrays(int a[], int l, int r) {
        if (null == a || a.length == 0 || l > r)
            return 0;

        int start = 0;
        int end;
        int count = 0;
        int lastWithIn = -1;

        for (end = 0; end < a.length; end++) {

            int ele = a[end];
            if (ele >= l && ele <= r) {

                count += end - start + 1; //this equivalent to (end-start) = x => x + (x-1) + (x-2) .... 1 [ formula ] if
                lastWithIn = end; //notice where we found last within range

            } else if (ele < l) {

                if (lastWithIn != -1)
                    count += lastWithIn - start + 1; //discard the element before current index (i) as this is <l and take all sub-array before this \
                /**
                 * Example:
                 * (2,4,3,1) start = 0, end = 3 and lastWithIn = 2 then we need to take only (2,4,3,1), (4,3,1) and (3,1) and discard (1) [total 3]
                 * which is 2-0+1 = 3
                 */


            } else if (a[end] > r) {
                start = end + 1;
                lastWithIn = -1;
            }
        }
        return count;
    }
}

