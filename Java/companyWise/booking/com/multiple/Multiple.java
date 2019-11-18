package Java.companyWise.booking.com.multiple;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-31
 * Description:
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
