package DataStructureAlgo.Java.nonleetcode.InterviewBit;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 2019-05-20
 * Question Title: Maximum Absolute Difference
 * Link: https://leetcode.com/problems/maximum-absolute-difference/
 * Description:
 * Description:
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

public class MaximumAbsoluteDifference {

    public static void main(String s[]) {
        List<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(3);
        a.add(-1);
        System.out.println(maxArrOp(a));
    }

    public static int maxArrOp(List<Integer> A) {

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < A.size(); i++) {
           int min = A.get(i) + (i+1) ;
           int tmax = A.get(i) - (i+1);

           max = Math.max(max, Math.max(Math.abs(min), Math.abs(tmax)));
        }

        return max;
    }

    public static int maxArr(List<Integer> A) {

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < A.size(); i++) {
            for (int j = 0; j < A.size(); j++) {

                int curr = Math.abs(A.get(i) - A.get(j)) + Math.abs((i + 1) - (j + 1));
                max = Math.max(curr, max);
            }
        }

        return max;
    }
}
