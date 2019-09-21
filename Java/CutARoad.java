package Java;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 11/12/18
 * Description: https://www.geeksforgeeks.org/cutting-a-rod-dp-13/
 * Given a rod of length n inches and an array of prices that contains prices of all pieces of size smaller than n. Determine the maximum value obtainable by cutting up the rod and selling the pieces. For example, if length of the rod is 8 and the values of different pieces are given as following, then the maximum obtainable value is 22 (by cutting in two pieces of lengths 2 and 6)
 * <p>
 * <p>
 * length   | 1   2   3   4   5   6   7   8
 * --------------------------------------------
 * price    | 1   5   8   9  10  17  17  20
 * And if the prices are as following, then the maximum obtainable value is 24 (by cutting in eight pieces of length 1)
 * <p>
 * length   | 1   2   3   4   5   6   7   8
 * --------------------------------------------
 * price    | 3   5   8   9  10  17  17  20
 *
 * <p>
 * Revenue[i] = is the maximum revenue we can get by cutting a road of i size.
 * <p>
 * Revenue[i]   =     0    ;i=0 // If no cut made, then total revenue 0
 * *             =     Max{p[j] + Revenue[i-j-1]}     ;i>0   where 0<=j<i
 */
public class CutARoad {


    /*
    Revenue[i]      =     0    ;i=0 // If no cut made, then total revenue 0
    * *             =     Max{p[j] + Revenue[i-j-1]}     ;i>0   where 0<=j<i
     */
    private static int cutARod(int price[]) {

        int length = price.length;

        int revenue[] = new int[length + 1];

        revenue[0] = 0; //base case

        int max;

        //every cut from 1 to n length
        for (int i = 1; i <= length; i++) {

            max = Integer.MIN_VALUE;

            //the maximum value can be get to make i cut
            for (int j = 0; j < i; j++) {

                max = Math.max(max, price[j] + revenue[i - j - 1]);
                revenue[i] = max;

            }
        }

        return revenue[length];

    }

    public static void main(String []args) {
        int arr[] = {1, 5, 8, 9, 10, 17, 17, 20};

        System.out.println(cutARod(arr) == 22);
    }
}
