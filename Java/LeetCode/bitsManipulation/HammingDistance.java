package Java.LeetCode.bitsManipulation;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-09
 * https://leetcode.com/problems/hamming-distance/
 * https://leetcode.com/problems/total-hamming-distance/
 */
public class HammingDistance {
}

class SolutionHammingDistance {

    public int totalHammingDistance(int[] nums) {
        return totalHammingDistanceOptimized(nums);
    }


    /**
     * https://leetcode.com/problems/total-hamming-distance/
     * <p>
     * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
     * <p>
     * Now your job is to find the total Hamming distance between all pairs of the given numbers.
     * <p>
     * Example:
     * Input: 4, 14, 2
     * <p>
     * Output: 6
     * <p>
     * Explanation: In binary representation, the 4 is 0100, 14 is 1110, and 2 is 0010 (just
     * showing the four bits relevant in this case). So the answer will be:
     * HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.
     * Note:
     * Elements of the given array are in the range of 0 to 10^9
     * Length of the array will not exceed 10^4.
     * Count each bit in 32 bit
     * O(n)
     *
     * @param nums
     * @return
     */
    private int totalHammingDistanceOptimized(int[] nums) {
        int count = 0;
        int hammers = 0;
        for (int i = 0; i < 32; i++) {

            count = 0;

            /**
             * Count how many number has 'i'th bit set
             */
            for (int j = 0; j < nums.length; j++) {

                if ((nums[j] & (1 << i)) != 0)
                    count++;
            }
            /**
             *  Since we need to find the total hamming distance, which tells total different bits in two pairs
             *
             * Count;  count of all the number has 'i'th bit set [ part 1 ]
             * (nums.length - count) will are the number that don't have don't set the i'th bit [ part 2]
             *
             */
            hammers += count * (nums.length - count);
        }
        return hammers;
    }

    //================ Naive: O(n^2) ======================
    private int totalHammingDistanceNaive(int[] nums) {
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++)
                count += hammingDistance(nums[i], nums[j]);
        }
        return count;
    }

    /**
     * * Description: https://leetcode.com/problems/hamming-distance/
     * * <p>
     * * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
     * * <p>
     * * Given two integers x and y, calculate the Hamming distance.
     * * <p>
     * * Note:
     * * 0 ≤ x, y < 2^31.
     * * <p>
     * * Example:
     * * <p>
     * * Input: x = 1, y = 4
     * * <p>
     * * Output: 2
     * * <p>
     * * Explanation:
     * * 1   (0 0 0 1)
     * * 4   (0 1 0 0)
     * * *       ↑   ↑
     * * <p>
     * * The above arrows point to positions where the corresponding bits are different.
     * <p>
     * <p>
     * x= 5  :  101
     * y = 3 :  011
     * x^y =    110
     *
     * @param x
     * @param y
     * @return
     */

    public int hammingDistance(int x, int y) {

        int hammer = x ^ y; // using this, it will change the bits where they are different

        int count = 0;

        //count the bits which are set :)
        while (hammer > 0) {
            count += (hammer % 2);
            hammer /= 2;
        }
        return count;

    }
}