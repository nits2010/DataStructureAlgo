package Java.zigzag;

import Java.HelpersToPrint.Printer;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-11
 * Description: Build & print a matrix formed in zig-zag fashioned[See image for more info]
 */
public class ZigZagMatrix {

    public static int[][] build(int n) {

        int zigZag[][] = new int[n][n];

        int i = 1;
        int j = 1;

        for (int ele = 0; ele < n * n; ele++) {

            zigZag[i - 1][j - 1] = ele;

            if ((i + j) % 2 == 0) {

                if (j < n)
                    j++;
                else
                    i += 2;

                if (i > 1)
                    i--;


            } else {

                if (i < n)
                    i++;
                else
                    j += 2;

                if (j > 1)
                    j--;
            }
        }


        return zigZag;
    }


    public static void print(int zigZag[][]) {

        Printer.print(zigZag);
        System.out.println("\n\n");
        int n = zigZag.length;
        int i = 1;
        int j = 1;
        for (int ele = 0; ele < n * n; ele++) {

            System.out.print (zigZag[i - 1][j - 1] + " ");

            if ((i + j) % 2 != 0) {

                if (j < n)
                    j++;
                else
                    i += 2;

                if (i > 1)
                    i--;


            } else {

                if (i < n)
                    i++;
                else
                    j += 2;

                if (j > 1)
                    j--;
            }
        }
    }

    public static void main(String[] args) {
        print(build(3));
        System.out.println("\n\n");
        print(build(5));
    }
}
