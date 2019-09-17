package Java.dominoArragement;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-05-20
 * Description: see screen shot for question
 */
public class DominoesArrangementProblem {


    public static void main(String []args) {

        int a[] = {4, 3, 3, 4, 1, 2, 2, 3, 6, 5, 4, 5};
        System.out.println(solution(a));
    }

    private static boolean solution(int[] a) {

        Map<Integer, Integer> map = new HashMap<>();


        for (int i = 0; i < a.length; i++) {
            map.put(a[i], map.getOrDefault(a[i], 0) + 1);
        }


        int three = 0;
        int two = 0;
        int one = 0;
        for (Integer key : map.keySet()) {


            if (map.get(key) == 1)
                one++;

            if (map.get(key) == 2)
                two++;

            if (map.get(key) == 3)
                three++;


        }

        if (three != 3 && two != 2 && one != 2)
            return false;

        return true;

    }


}
