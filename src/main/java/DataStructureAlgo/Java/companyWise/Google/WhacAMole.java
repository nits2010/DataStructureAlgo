package DataStructureAlgo.Java.companyWise.Google;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-08
 * Description: https://leetcode.com/discuss/interview-question/350139/Google-or-Phone-Screen-or-Whac-A-Mole
 * Given an int array holes where 1 means there is a mole, 0 means no mole. Find out the max number of moles you can hit with a mallet of width w.
 * <p>
 * Example:
 * <p>
 * Input: holes = [0, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0], w = 5
 * Output: 4
 * Explanation: 0(11011)01000
 * Follow-up:
 * What if you have 2 mallets?
 * <p>
 * Example:
 * <p>
 * Input: holes = [0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 0], w = 5
 * Output: 6
 * Explanation: 0(10011)0(11001)0
 * <p>
 * {GOOGLE}
 */
public class WhacAMole {

    public static void main(String[] args) {
        testMallet(new int[]{0, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0}, 5);
        testMallet(new int[]{0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 0}, 5);
        testMallet(new int[]{0, 1, 0, 0, 1}, 5);
        testMallet(new int[]{0, 1, 0, 0, 1, 1}, 5);
        testMallet(new int[]{0, 0, 0, 0}, 5);
        testMallet(new int[]{0, 0, 0, 0, 0}, 5);
        testMallet(new int[]{1, 1, 1, 1, 1}, 5);
        testMallet(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 5);
        testMallet(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 5);
        testMallet(new int[]{0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 0}, 1);
    }

    private static void testMallet(int[] nums, int w) {
        WhacAMoleUsingMemory memory = new WhacAMoleUsingMemory();
        WhacAMoleMemoryEfficient withoutMemory = new WhacAMoleMemoryEfficient();

        System.out.println("\nWindow : " + w + " Input ");
        CommonMethods.print(nums);
        System.out.println("Whac-Moles using 1 Mallet : " + memory.whacAMoleSingleMallet(nums, w));
        System.out.println("Whac-Moles using 1 Mallet (Without memory) : " + withoutMemory.whacAMoleSingleMallet(nums, w));

        System.out.println("Whac-Moles using 2 Mallet : " + memory.whacAMoleDoubleMallets(nums, w));
        System.out.println("Whac-Moles using 2 Mallet (Without memory): " + withoutMemory.whacAMoleDoubleMallets(nums, w));
    }
}

/**
 * O(n) / O(n)
 */
class WhacAMoleUsingMemory {

    public int whacAMoleSingleMallet(int[] holes, int w) {

        if (null == holes || w == 0 || holes.length == 0 || w > holes.length)
            return 0;

        if (holes.length == 1)
            return holes[0] == 1 ? 1 : 0;

        int n = holes.length;
        int preSum[] = new int[n];

        preSum[0] = holes[0];
        int max = preSum[0];

        for (int i = 1; i < n; i++) {
            preSum[i] = preSum[i - 1] + holes[i];

            if (i < w) {
                max = Math.max(max, preSum[i]);
            }
        }


        for (int i = w; i < n; i++) {

            max = Math.max(max, preSum[i] - preSum[i - w]);

        }

        return max;
    }

    public int whacAMoleDoubleMallets(int[] holes, int w) {

        if (null == holes || w == 0 || holes.length == 0 || w > holes.length)
            return 0;

        if (holes.length == 1)
            return holes[0] == 1 ? 1 : 0;

        if (holes.length <= 2 * w - 1)
            return whacAMoleSingleMallet(holes, w);

        int n = holes.length;
        int leftPreSum[] = new int[n];
        int rightPreSum[] = new int[n];


        leftPreSum[0] = holes[0];
        int maxLeft = leftPreSum[0];
        rightPreSum[n - 1] = holes[n - 1];
        int maxRight = rightPreSum[n - 1];

        for (int i = 1, j = n - 2; i < n && j >= 0; i++, j--) {

            leftPreSum[i] = leftPreSum[i - 1] + holes[i];

            if (i < w) {
                maxLeft = Math.max(maxLeft, leftPreSum[i]);
            }


            rightPreSum[j] = rightPreSum[j + 1] + holes[j];
            if (j > n - w)
                maxRight = Math.max(maxRight, rightPreSum[j]);
        }


//        for (int i = 1; i < n; i++) {
//            leftPreSum[i] = leftPreSum[i - 1] + holes[i];
//
//            if (i < w) {
//                maxLeft = Math.max(maxLeft, leftPreSum[i]);
//            }
//
//        }
//
//        rightPreSum[n - 1] = holes[n - 1];
//        int maxRight = rightPreSum[n - 1];
//
//        for (int i = n - 2; i >= 0; i--) {
//            rightPreSum[i] = rightPreSum[i + 1] + holes[i];
//            if (i > n - w)
//                maxRight = Math.max(maxRight, rightPreSum[i]);
//        }

        int max = maxLeft + maxRight;

        for (int i = w, j = n - w - 1; i < j; i++, j--) {

            maxLeft = Math.max(maxLeft, leftPreSum[i] - leftPreSum[i - w]);
            maxRight = Math.max(maxRight, rightPreSum[i] - rightPreSum[i + w]);
            max = Math.max(max, maxLeft + maxRight);
        }

        return max;
    }
}


/**
 * O(n)/O(1)
 */
class WhacAMoleMemoryEfficient {

    public int whacAMoleSingleMallet(int[] holes, int w) {

        if (null == holes || w == 0 || holes.length == 0 || w > holes.length)
            return 0;

        if (holes.length == 1)
            return holes[0] == 1 ? 1 : 0;

        int n = holes.length;

        int curr = holes[0];
        int max = 0;

        for (int i = 1; i < n; i++) {

            curr += holes[i];

            if (i == w - 1) {
                max = curr;
            } else if (i >= w) {
                curr = curr - holes[i - w];
                max = Math.max(max, curr);
            }
        }


        return max;
    }

    public int whacAMoleDoubleMallets(int[] holes, int w) {

        if (null == holes || w == 0 || holes.length == 0 || w > holes.length)
            return 0;

        if (holes.length == 1)
            return holes[0] == 1 ? 1 : 0;

        if (holes.length <= 2 * w - 1)
            return whacAMoleSingleMallet(holes, w);

        int n = holes.length;

        int max = 0;
        int leftSum = holes[0];
        int rightSum = holes[n - 1];
        int leftMax = 0;
        int rightMax = 0;

        int i = 1, j = n - 2;

        while (i < j) {

            leftSum += holes[i];

            if (i == w - 1) {
                leftMax = leftSum;
            } else if (i >= w) {
                leftSum = leftSum - holes[i - w];
                leftMax = Math.max(leftMax, leftSum);
            }

            rightSum += holes[j];


            if (j == n - w) {
                rightMax = rightSum;
            } else if (j <= n - w) {
                rightSum -= holes[j + w];
                rightMax = Math.max(rightMax, rightSum);
            }

            i++;
            j--;

            max = Math.max(max, leftMax + rightMax);
        }

        return max;
    }
}
