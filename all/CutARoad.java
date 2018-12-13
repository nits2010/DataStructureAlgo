package DataStructureAlgo.Java;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 11/12/18
 * Description:
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

        int max = Integer.MIN_VALUE;

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

    public static void main(String args[]) {
        int arr[] = {1, 5, 8, 9, 10, 17, 17, 20};

        System.out.println(cutARod(arr) == 22);
    }
}
