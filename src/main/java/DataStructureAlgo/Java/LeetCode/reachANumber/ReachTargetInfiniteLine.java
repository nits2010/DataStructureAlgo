package DataStructureAlgo.Java.LeetCode.reachANumber;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-12
 * Description: https://leetcode.com/problems/reach-a-number/
 * ou are standing at position 0 on an infinite number line. There is a goal at position target.
 * <p>
 * On each move, you can either go left or right. During the n-th move (starting from 1), you take n steps.
 * <p>
 * Return the minimum number of steps required to reach the destination.
 * <p>
 * Example 1:
 * Input: target = 3
 * Output: 2
 * Explanation:
 * On the first move we step from 0 to 1.
 * On the second step we step from 1 to 3.
 * Example 2:
 * Input: target = 2
 * Output: 3
 * Explanation:
 * On the first move we step from 0 to 1.
 * On the second move we step  from 1 to -1.
 * On the third move we step from -1 to 2.
 * Note:
 * target will be a non-zero integer in the range [-10^9, 10^9].
 *
 * For full solution: https://leetcode.com/problems/reach-a-number/discuss/357047/With-Full-explanation-and-approach-to-reach-solution
 *
 */
public class ReachTargetInfiniteLine {

    public static void main(String[] args) {
        test(3, 2);
        test(2, 3);
        test(5, 5);
        test(4, 3);
        test(11, 5);
        test(7, 5);
        test(12, 7);
//        test(-1000000000, 44723);
    }

    private static void test(int target, int expected) {
        System.out.println("\nTarget: " + target);
        System.out.println(" Brute " + ReachNumberInfiniteLineBruteForce.reachNumber(target) + " expected: " + expected);
        System.out.println(" Intuitive " + ReachNumberInfiniteLineIntuitive.reachNumber(target) + " expected: " + expected);
        System.out.println(" MAths " + ReachNumberInfiniteLineMathematical.reachNumber(target) + " expected: " + expected);
    }
}

class ReachNumberInfiniteLineBruteForce {


    /**
     * At each step, we have two choices
     * 1. either we go right side (+) direction
     * 2. Or go left side (-ve) direction.
     * <p>
     * Our goal: To reach the target value; Math.abs(target)
     * Our constraints: we can't go beyond the target value
     * Our choices:
     * 1. either we go right side (+) direction
     * 2. Or go left side (-ve) direction.
     * <p>
     * <p>
     * Complexity:
     * At each position we have two choice, which means that we can reach to another number with possible two choices.
     * Since we search in whole space, between neg and positive hence O(2^n) where n = space
     *
     * @param target
     * @return
     */
    public static int reachNumber(int target) {


        int steps = 0;
        int i = 0;
        return reachNumber(target, steps, i);

    }

    /**
     * Give error for big number which is outside Integer range
     * @param target
     * @param steps
     * @param currentPos
     * @return
     */
    private static int reachNumber(int target, int steps, int currentPos) {

        if (Math.abs(currentPos) > Math.abs(target))
            return Integer.MAX_VALUE;

        if (currentPos == target)
            return steps;


        long positive = reachNumber(target, steps + 1, currentPos + steps + 1);
        long negative = reachNumber(target, steps + 1, currentPos - (steps + 1));


        return (int)Math.min(positive, negative);
    }


}

/**
 *
 * If you observe the movement, we can see some pattern exist in the way the step should be taken.
 * <p>
 * first either we reach -target or +target, the answer would be same, as we can always flip the direction of step to other side.
 * <p>
 * Now; Lets focus on +ve side only.
 * Lets take an example:
 * Target = 5
 * <p>
 * Step 1: 0->1 [sum]
 * step 2: 1->3 [sum]
 * step 3: 3->6 [sum]
 * step 4: 6->10 [sum]
 * step 5: 10->5 [sum]
 * <p>
 * We can reach 5 in 5 steps.
 * <p>
 * There are following possibilities can happen.
 * 1. Sum == target -> Then 'step' is our answer.
 * 2. Sum < target -> we need to take more step towards right.
 * 3. Sum > target -> Then we need to find weather we should take step toward right or left.
 * <p>
 * <p>
 * if you see at step 3 (3->6) we reach more than the target as 6[sum] > 5. as we took our step towards right.
 * But if we have took our step towards left then we would have reach at step 3: 3 -> 0, which is equal to
 * sum - 2*step since 6 - 2*3 = 0.
 * <p>
 * Similarly for step 4; 10 - 2*4 = 2
 * <p>
 * now if sum-2*step = target, then current step is our answer, as we have reach the target.
 * But if sum-2*Step != target, then we need to still find out at which direction we should take the next step.
 * <p>
 * solving above equation
 * sum - 2*step = target => sum-target = 2*step. Which means if you have not reached the target then sum - target != 2*step.
 * <p>
 * <p>
 * Hence we need to increase the sum by taking more step s.t. sum - target = 2*Step.
 * Which means 'sum-target' has to be even in order to reach the target with 'step'.
 * <p>
 * now essentially we need to find the number of step, of which sum is 'sum' and equation 'sum-target' is even.  [AS we can see '2*step' always a even number,]
 * <p>
 * Now, what are the possibilities when difference of two numbers are even. Lets try
 * <p>
 * Even  - Even = Even [ when both 'sum' and 'target' is even then difference is also even ]
 * Even - odd = Odd [ when  'sum' is even and 'target' is odd then difference is odd ]
 * Odd - Odd = Even [ when both 'sum' and 'target' is odd then difference is also even ]
 * Odd - Even = Odd [ when  'sum' is Odd and 'target' is even then difference is odd ]
 * <p>
 * <p>
 * Which boils down to below;
 * 1. When target is Odd: Say 5
 * a. When sum is even; say 6 => 6-5 = 1(odd) to make it even we take one more step (after step 3 i.e. step 4) which makes it 10 - 5 = 5 [odd] and if we take one more step then (after step 4 i.e. step 5)
 * *** 15 - 5 = 10 [ even ] Which means when the difference is odd, we may take up to 2 more steps in order to make 'sum-target' even.
 * b. When sum is odd: Say 15 => 15 - 5 = 10 [ even ] we don't need to take any more step as this is even ]
 * <p>
 * 2. When Target is even: 4
 * a. When sum is even; say 6 => 6-4 = 2 [ even ]
 * b. When sum is odd: Say 15 => 15 - 4 = 11 by taking one more step; sum => 21-4 = 17 and one more => 28-4 = 24 [ even]
 * <p>
 * <p>
 * Hence, we increase our step till 'sum-target' is even
 * <p>
 * <p>
 * Example:
 * Target: 5 [ odd ]
 * 1 + 2 + 3 = 6 > 5 => 6-5 = 1[odd]
 * Take another step right side
 * 1+2+3+4=10 => 10-5 = 5 [odd] take another step backside -> sum= 15-5 = 10[even] Hence we need 5 steps.
 * <p>
 * <p>
 * Target: 4
 * Step 1: 0->1 [sum]
 * step 2: 1->3 [sum]
 * step 3: 3->6 [sum] > 4 and 6 - 4 = even
 * Minimum steps required is 3
 * s1: 0->-1
 * s2: -1 -> 1
 * s3: 1 -> 4
 * <p>
 * <p>
 * If you want to print, then mind when ever the sum-target is odd, you take sum-2*target as a candidate
 * <p>
 * <p>
 */
class ReachNumberInfiniteLineIntuitive {

    /**
     * Complexity:
     * we we'll take at max k steps which is
     * 1 + 2 + 3 + 4 + .... k = k(k+1)/2
     * target = k(k+1)/2
     * 2*target = k^2 + k
     * k = Sqrt (2*target - k)
     * <p>
     * Hence O(Sqrt(target))
     *
     * @param target
     * @return
     */
    public static int reachNumber(int target) {
        if (target == 0)
            return 0;

        int sum = 0;
        int step = 0;


        while (sum < target || (sum - target) % 2 != 0) {
            step++;
            sum += step;
        }
        return step;
    }

}

/**
 * We can solve this question using maths.
 * as above stated already;
 * 1 + 2 + 3 + 4 + .... = sum >= target [Replace sum with 'n' and 'target' with 'x' for simple interpretation ]
 * <p>
 * n(n+1)/2 >= x
 * n^2 + n - 2*x >= 0
 * <p>
 * Which is like
 * AN^2 + BN + C = 0
 * Has solution
 * N = ( -B (+,-) Sqrt (B^2 - 4*A*C) )/ 2*A ; (+,-) means either + or -
 * <p>
 * apply on equation;
 * N >= ( -1 (+,-) Sqrt ( 1 - 4*1*(-2*x) ) ) / 2
 * N >= ( -1 (+,-) Sqrt ( 1 + 8*x ) ) / 2
 * <p>
 * See image for more clarity
 *
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Reach a Number.
 * Memory Usage: 33.1 MB, less than 20.00% of Java online submissions for Reach a Number.
 */

class ReachNumberInfiniteLineMathematical {
    public static int reachNumber(int target) {

        long targetLong = Math.abs(target);

        long value =  (int)(Math.ceil(((Math.sqrt(Math.abs(targetLong) * 8 + 1)) -1)/2));

        //difference should be even of (target & value (total steps)) and it will come in one step or two
        //even difference will ensure us there is a way to adjust the back steps.
        long totalSteps = (value * (value + 1) )/ 2;

        if((totalSteps - targetLong) % 2 == 0 ) {
            return (int) value;
        }
        value++;
        totalSteps = (value * (value + 1) )/ 2;

        if((totalSteps - targetLong) % 2 == 0 ) {
            return (int) value;
        }
        value++;

        return (int) value;



    }
}