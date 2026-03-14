package DataStructureAlgo.Java.companyWise.booking.com.multiple;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-31
 * Question Title: Multiple
 * Link: https://leetcode.com/problems/multiple/
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

public class Multiple {

    public static List<Integer> multiple(int x, int y, int z, int n) {

        List<Integer> result = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if ((x != 0 && i % x == 0) || (y != 0 && i % y == 0)) {
                if (z != 0 && i % z != 0)
                    result.add(i);
            }
        }

        return result;

    }
}
