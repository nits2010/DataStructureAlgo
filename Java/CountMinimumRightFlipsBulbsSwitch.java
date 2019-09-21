package Java;

import java.util.Arrays;
import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-17
 * Description:
 * https://www.geeksforgeeks.org/count-minimum-right-flips-to-set-all-values-in-an-array/
 * N light bulbs are connected by a wire. Each bulb has a switch associated with it, however due to faulty wiring, a switch also changes the state of all the bulbs to the right of current bulb. Given an initial state of all bulbs, find the minimum number of switches you have to press to turn on all the bulbs. You can press the same switch multiple times.
 * Note: 0 represents the bulb is off and 1 represents the bulb is on.
 * Examples:
 *
 * Input :  [0 1 0 1]
 * Output : 4
 * Explanation :
 *     press switch 0 : [1 0 1 0]
 *     press switch 1 : [1 1 0 1]
 *     press switch 2 : [1 1 1 0]
 *     press switch 3 : [1 1 1 1]
 *
 * Input : [1 0 0 0 0]
 * Output : 1
 *
 */
public class CountMinimumRightFlipsBulbsSwitch {


    private static int countMinimumRightFlips(List<Integer> a) {
        int n = a.size();

        if (n == 0) return 0;

        if (n == 1)
            return a.get(0) == 0 ? 1 : 0;

        int count = 0;
        int oldState, newState;
        for (int i = 0; i < n; i++) {

            oldState = a.get(i);
            newState = count % 2 == 0 ? oldState : (oldState == 0) ? 1 : 0;

            if (newState == 0)
                count++;
        }

        return count;

    }

    public static void main(String []args) {
        test(Arrays.asList(1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1));
        test(Arrays.asList(0, 0, 0, 1));
        test(Arrays.asList(1, 0, 0, 0));
        test(Arrays.asList(1, 0, 0, 1));
    }

    private static void test(List<Integer> asList) {
        System.out.println(" List :" + asList + " flips required " + countMinimumRightFlips(asList));
    }


}
