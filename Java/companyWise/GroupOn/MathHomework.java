package Java.companyWise.GroupOn;

import java.util.Arrays;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-10
 * Description:
 */
public class MathHomework {

    public static void main(String[] args) {
        System.out.println(minNum(10, Arrays.asList(1, 3, 4, 7, 8, 9, 11, 13)));
        System.out.println(minNum(4, Arrays.asList(1, 3, 4, 7, 8, 9, 11, 13)));
        System.out.println(minNum(4, Arrays.asList(1, 3, 4, 7)));
        System.out.println(minNum(4, Arrays.asList(1, 2, 3, 5)));
        System.out.println(minNum(2, Arrays.asList(1, 2, 3)));
        System.out.println(minNum(5, Arrays.asList(1, 2, 3, 4, 5)));
    }

    public static int minNum(int threshold, List<Integer> happy) {
        int answer = 1;

        Integer[] item = happy.toArray(new Integer[happy.size()]);


        int min = item[0];

        for (int i = 0; i < item.length; ) {


            if (i + 2 < item.length && item[i + 2] - min <= threshold) {
                answer++;
                i = i + 2;
            } else {
                if (i + 1 < item.length) {
                    return answer + 1;
                } else
                    return answer;
            }


        }

        return Math.min(answer, item.length);

    }
}
