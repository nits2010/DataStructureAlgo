package DataStructureAlgo.Java.LeetCode.matrixPrint.dignoal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 23/09/19
 * Description:
 * FOR N*N only
 * {@link DataStructureAlgo.Java.companyWise.visa.ReverseDiagonalOrder}
 */
public class ReverseDiagonalDownward {
    public static void main(String[] args) {

        test(Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6), Arrays.asList(7, 8, 9)), Arrays.asList(3, 2, 6, 1, 5, 9, 4, 8, 7));
        test(Arrays.asList(Arrays.asList(1, 2, 3, 4), Arrays.asList(5, 6, 7, 8), Arrays.asList(9, 10, 11, 12), Arrays.asList(13, 14, 15, 16)), Arrays.asList(4, 3, 8, 2, 7, 12, 1, 6, 11, 16, 5, 10, 15, 9, 14, 13));
    }

    private static void test(List<List<Integer>> matrix, List<Integer> expected) {
        System.out.println("\nMatrix:" + matrix);
        System.out.println("Expected  :" + expected);
        System.out.println("Obtained  :" + printAntiDiag1(matrix));
        System.out.println("Obtained  :" + printAntiDiag2(matrix));

    }


    public static List<Integer> printAntiDiag1(List<List<Integer>> matrix) {
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
        return item;

    }


    public static List<Integer> printAntiDiag2(List<List<Integer>> matrix) {

        final int ROW = matrix.size();

        // There will be ROW+COL-1 lines in the output
        List<Integer> solution = new ArrayList<>();
        for (int line = 1; line <= (ROW + ROW - 1); line++) {


            // Get column index of the first element in this
            // line of output.The index is 0 for first ROW
            // lines and line - ROW for remaining lines
            int start_col = Math.max(0, line - ROW);


            // Get count of elements in this line. The count
            // of elements is equal to minimum of line number,
            // COL-start_col and ROW
            int count = Math.min(line, Math.min((ROW - start_col), ROW));


            // Print elements of this line
            for (int j = 0; j < count; j++) {
                int row = start_col + j;
                int column = Math.max(0, ROW - line) + j;
                solution.add(matrix.get(row).get(column));  // I changed only this line.
            }

        }

        return solution;
    }


}
