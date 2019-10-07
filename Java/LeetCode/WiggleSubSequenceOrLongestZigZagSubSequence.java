package Java.LeetCode;

import Java.nonleetcode.LongestIncreasingSubSequence;

import java.util.Arrays;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-24
 * Description: https://leetcode.com/problems/wiggle-subsequence/
 * https://www.geeksforgeeks.org/longest-zig-zag-subsequence/
 * <p>
 * A sequence of numbers is called a wiggle sequence if the differences between successive numbers strictly
 * alternate between positive and negative. The first difference (if one exists) may be either positive or negative.
 * A sequence with fewer than two elements is trivially a wiggle sequence.
 * <p>
 * For example, [1,7,4,9,2,5] is a wiggle sequence because the differences (6,-3,5,-7,3) are alternately positive and negative.
 * In contrast, [1,4,7,2,5] and [1,7,4,5,5] are not wiggle sequences, the first because its first two differences are positive
 * and the second because its last difference is zero.
 * <p>
 * Given a sequence of integers, return the length of the longest subsequence that is a wiggle sequence.
 * A subsequence is obtained by deleting some number of elements (eventually, also zero) from the original sequence,
 * leaving the remaining elements in their original order.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,7,4,9,2,5]
 * Output: 6
 * Explanation: The entire sequence is a wiggle sequence.
 * Example 2:
 * <p>
 * Input: [1,17,5,10,13,15,10,5,16,8]
 * Output: 7
 * Explanation: There are several subsequences that achieve this length. One is [1,17,10,13,10,16,8].
 * Example 3:
 * <p>
 * Input: [1,2,3,4,5,6,7,8,9]
 * Output: 2
 * Follow up:
 * Can you do it in O(n) time?
 */
public class WiggleSubSequenceOrLongestZigZagSubSequence {

    public static void main(String[] args) {
        int arr[] = {10, 22, 9, 33, 49,
                50, 31, 60};

        WiggleSubSequencePoly poly = new WiggleSubSequencePoly();
        WiggleSubSequenceLinear linear = new WiggleSubSequenceLinear();

        System.out.println("Poly " + poly.wiggleMaxLength(arr));
        System.out.println("linear " + linear.wiggleMaxLength(arr));

    }
}


/**
 * base idea {@link LongestIncreasingSubSequence}
 */
class WiggleSubSequencePoly {


    public int wiggleMaxLength(int[] nums) {

        if (nums.length == 0)
            return 0;

        /**
         * Z[i][0] is the length of longest Zig-Zag sub-sequence s.t. last element is greatest
         * Z[i][1] is the length of longest Zig-Zag sub-sequence s.t. last element is smallest
         *
         * Z[i][0] = Max { Z[i][0], { Z[j][1] + 1 } where 0<=j<i and A[i] > A[j]
         * Z[i][1] = Max { Z[i][1], { Z[j][0] + 1 } where 0<=j<i and A[i] < A[j]
         *
         * The first recurrence relation is based on the fact that, If we are at position i and this element has to bigger
         * than its previous element then for this sequence (upto i) to be bigger we will try to choose an element j ( < i)
         * such that A[j] < A[i] i.e. A[j] can become A[i]’s previous element and Z[j][1] + 1 is bigger than Z[i][0]
         * then we will update Z[i][0].
         * Remember we have chosen Z[j][1] + 1 not Z[j][0] + 1 to satisfy alternate property because in Z[j][0] last element
         * is bigger than its previous one and A[i] is greater than A[j] which will break the alternating property if we update.
         * So above fact derives first recurrence relation, similar argument can be made for second recurrence relation also
         *
         */
        int z[][] = new int[nums.length][2];

        //Every element form zig-zag of length 1
        for (int i = 0; i < nums.length; i++)
            Arrays.fill(z[i], 1);

        int max = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {

            for (int j = 0; j < i; j++) {

                if (nums[i] > nums[j]) {

                    if (z[j][1] + 1 > z[i][0])
                        z[i][0] = z[j][1] + 1;
                } else if (nums[i] < nums[j]) { //Not considering equals, as they don't form zig-zag
                    if (z[j][0] + 1 > z[i][1])
                        z[i][1] = z[j][0] + 1;
                }

            }

            max = Math.max(max, Math.max(z[i][0], z[i][1]));
        }

        return max;

    }
}

//Linear
class WiggleSubSequenceLinear {

    /**
     * Check alternate sign on SEQUENCE
     *
     * @param nums
     * @return
     */
    public int wiggleMaxLength(int[] nums) {
        if (nums.length == 0)
            return 0;


        int lastSign = 0, length = 1;

        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {

            int sign = sign(nums[i + 1] - nums[i]);

            if (sign != 0 && sign != lastSign) {
                length++;
                lastSign = sign;
            }
        }
        return length;
    }

    int sign(int i) {
        if (i != 0)
            return i > 0 ? 1 : -1;
        return 0;
    }
}