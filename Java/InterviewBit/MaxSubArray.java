package Java.InterviewBit;

import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-05-20
 * Description:
 */
public class MaxSubArray {

    public int maxSubArray(final List<Integer> A) {

        int maxSoFar = Integer.MIN_VALUE;
        int currentMax = 0;

        for (Integer i : A) {

            currentMax = Math.max(currentMax + i, i);
            maxSoFar = Math.max(maxSoFar, currentMax);
        }
        return maxSoFar;
    }
}
