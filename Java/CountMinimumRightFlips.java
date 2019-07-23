package Java;

import java.util.Arrays;
import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-17
 * Description:
 */
public class CountMinimumRightFlips {


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

    public static void main(String args[]) {
        test(Arrays.asList(1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1));
        test(Arrays.asList(0, 0, 0, 1));
        test(Arrays.asList(1, 0, 0, 0));
        test(Arrays.asList(1, 0, 0, 1));
    }

    private static void test(List<Integer> asList) {
        System.out.println(" List :" + asList + " flips required " + countMinimumRightFlips(asList));
    }


}
