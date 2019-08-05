package Java.HelpersToPrint;

import java.util.Arrays;
import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-24
 * Description:
 */
public class HelperToPrint {

    public static void printArray(final int a[]) {
        System.out.println();
        Arrays.stream(a).forEach(i -> System.out.print(i + " "));
        System.out.println();

    }

    public static void print2DArray(final int a[][]) {
        System.out.println();

        for (int i = 0; i < a.length; i++) {
            System.out.println();
            Arrays.stream(a[i]).forEach(x -> System.out.print(x + " "));
        }
        System.out.println();
    }

    public static void printListWithArray(final List<int[]> list) {

        System.out.println();
        for (int i[] : list) {

            System.out.print(" ( ");
            for (int x = 0; x < i.length; x++) {
                System.out.print(i[x]);
                if (x != i.length - 1)
                    System.out.print(" , ");
            }
            System.out.print(" ) ");
        }
    }
}
