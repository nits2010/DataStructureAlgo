package Java.companyWise.GroupOn;

import java.util.Arrays;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-10
 * Description:
 */
public class MinimumMoves {
    public static void main(String[] args) {
        System.out.println(minimumMoves(Arrays.asList(123, 543), Arrays.asList(321, 279)));
        System.out.println(minimumMoves(Arrays.asList(1234, 4321), Arrays.asList(2345, 3214)));

    }


    public static int minimumMoves(List<Integer> a, List<Integer> m) {

        int total = 0;

        for (int i = 0; i < a.size(); i++) {
            int x = a.get(i);
            int y = m.get(i);
            total += moves(x, y);

        }

        return total;
    }

    private static int moves(int x, int y) {

        int sum = 0;
        while (x != 0) {

            int r1 = x % 10;
            int r2 = y % 10;

            sum += Math.abs(r1 - r2);

            x /= 10;
            y /= 10;
        }
        return sum;
    }



}
