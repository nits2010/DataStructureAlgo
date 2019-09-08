package Java.companyWise.intuit.appleorchard;

import java.util.Scanner;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-09-07
 * Description:
 * I need some help to grow apples in my friends's orchard.
 *
 * My friend has a piece land that has A rows and B columns and i'm allowed to grow apples in only those cells where
 * any given Column N is divisible by row M where
 *
 * 1<=N<=B
 * 1<=M<=A
 *
 * For any given cell (M,N(, i get the cell only if N/M
 *
 * Could you let me know a piece of land of AxB, how many cells do i get to grow apples ?
 *
 */
public class AppleOrchard {

    /*
    Input :
    5
    4 4
    2 3
    1 4
    4 1
    8 1
     */

    public static void main(String args[]) {

        test(4,4,8);
        test(2,3,4);
        test(1,4,4);
        test(4,1,1);
        test(8,1,1);
        test(800,1,1);
//        Scanner scanner = new Scanner(System.in);
//
//        int t = scanner.nextInt();
//        int output[] = new int[t];
//
//        for (int i = 0; i < t; i++) {
//
//            int m = scanner.nextInt();
//            int n = scanner.nextInt();
//
//            output[i] = find(m, n);
//        }
//
//        for (int i = 0; i < t; i++)
//            System.out.println(output[i]);
    }

    private static void test(int m, int n, int expected) {
        System.out.println("\n M : "+m + " N : "+n + " expected :"+ expected);
        System.out.println(" obtained :"+find(m,n));
    }


    private static int find(int m, int n) {

        int count = n;
        for (int i = 2; i <= m && i <= n; i++) {


            for (int j = 1; i * j <= n; j++) {


                count++;
            }
        }
        return count;
    }


}
