package Java.companyWise.visa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 22/09/19
 * Description: Print the array element in reverse Diagonal manner. Up to down
 * [1,2,3]
 * [4,5,6]
 * [7,8,9]
 * <p>
 * The reverse dig are
 * 3 (0,2)
 * 2(0,1), 6(1,2)
 * 1(0,0),5(1,1),9(2,2)
 * 4(1,0),8(2,1)
 * 7(2,0)
 * <p>
 * Hence output
 * 3
 * 2
 * 6
 * 1
 * 5
 * 9
 * 4
 * 8
 * 7
 */
public class ReverseDiagonalOrder {
    public static void main(String[] args) {

//        printAntiDiag(Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6), Arrays.asList(7, 8, 9)));
        printAntiDiag(Arrays.asList(Arrays.asList(1, 2, 3, 4), Arrays.asList(5, 6, 7, 8), Arrays.asList(9, 10, 11, 12)));
    }

    public static void printAntiDiag(List<List<Integer>> matrix) {
        List<Integer> item = new ArrayList<>();
        int n = matrix.size() - 1;
        for (int c = n; c >= -n; c--) {
            for (int x = n; x >= 0; x--) {
                int y = x - c;

                if (y >= 0 && y <= n) {
                    item.add(matrix.get(x).get(y));
                }
            }
        }
        Collections.reverse(item);
        for (Integer i : item)
            System.out.println(i);


    }


}
