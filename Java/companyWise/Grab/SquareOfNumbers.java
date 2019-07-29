package Java.companyWise.Grab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-23
 * Description:
 * Given an array of integer in sorted manner, find the resultant array which contains squares of input array in sorted manner.
 * input
 * (-4, -3, 0, 2, 5, 7}
 * output:
 * {0, 4, 9, 16, 25, 49}
 */
public class SquareOfNumbers {

    private static List<Integer> squares(List<Integer> in) {

        if (in == null || in.isEmpty())
            return Collections.EMPTY_LIST;

        List<Integer> output = new ArrayList<>(in.size());
        for (int i = 0; i < in.size(); i++)
            output.add(0);

        int index = in.size() - 1;

        int low = 0;
        int high = in.size() - 1;

        while (low <= high) {

            int l = in.get(low);
            int h = in.get(high);

            if (Math.abs(l) > Math.abs(h)) {

                output.set(index--, (int) Math.pow(l, 2));
                low++;
            } else {
                output.set(index--, (int) Math.pow(h, 2));
                high--;
            }
        }

        return output;
    }

    public static void main(String args[]) {
        System.out.println(squares(Arrays.asList(-4, -3, 0, 2, 5, 7)).equals(Arrays.asList(0, 4, 9, 16, 25, 49)));
        System.out.println(squares(Arrays.asList(-9, -4, -3, 0, 2, 5, 7)).equals(Arrays.asList(0, 4, 9, 16, 25, 49, 81)));
        System.out.println(squares(Arrays.asList(0, 2, 5, 7)).equals(Arrays.asList(0, 4, 25, 49)));
        System.out.println(squares(Arrays.asList(-2, -5, -7)).equals(Arrays.asList(4, 25, 49)));
        System.out.println(squares(Arrays.asList(2, 5, 7)).equals(Arrays.asList(4, 25, 49)));
    }

}
