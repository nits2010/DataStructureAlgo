package DataStructureAlgo.Java.LeetCode.pair.element.problems.power;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-25
 * Description:
 * Find number of pairs (x, y) in an array such that x^y > y^x
 * Given two arrays X[] and Y[] of positive integers, find number of pairs such that x^y > y^x where x is an element from X[] and y is an element from Y[].
 * Examples:
 * <p>
 * Input: X[] = {2, 1, 6}, Y = {1, 5}
 * Output: 3
 * Explanation: There are total 3 pairs where pow(x, y) is greater
 * than pow(y, x) Pairs are (2, 1), (2, 5) and (6, 1)
 * <p>
 * Input: X[] = {10, 19, 18}, Y[] = {11, 15, 9}
 * Output: 2
 * Explanation: There are total 2 pairs where pow(x, y) is greater
 * than pow(y, x) Pairs are (10, 11) and (10, 15)
 */
public class XPowerYGreaterThanYPowerX {

    public static void main(String[] args) {

        System.out.println(pairs(new int[]{2, 1, 6}, new int[]{1, 5}) + " expected " + 3);
        System.out.println(pairs(new int[]{10, 19, 18}, new int[]{11, 15, 9}) + " expected :" + 2);
    }

    /*

        Algo:
        for having x^y > y ^x ;
        we must have  y > x with some exceptions;


        Example:
        0. x = 3 , y = 4 than 3 ^ 4 > 4 ^ 3
        1. x = 4 , y = 5 than 4^5 > 5^4 = 1024 > 625
        2. x = 2 , y = 9 than 2 ^ 9 > 8 ^2 = 512  > 62

        But for x = 2 and y = 3 or 4
        1. x = 2 , y = 3 then 2 ^ 3 < 3 ^2
        2. x = 2 , y = 4 then 2 ^ 4 = 4 ^2

        As well when
        1. x = 0 then no y can satisfy the condition
        2. x = 1 then only Y[i] = 0 can satisfy the condition


        Hence we need to know count of y = 0, 1, 2, 3, 4

        Than base cases:
        x = 0 -> ans = 0
        x = 1 -> ans += Count[0]

       Sort Y array;

       X[] = {2, 1, 6} Len=M, Y = {1, 5} Len=N

       for X[0]; x = 2 ; Find number of Y that is greater than x; finding index is sufficient as N-index would give the count
       but we need to remove where y=3 and 4 so
       x = 2 -> ans= (N-index) - Count[3] - Count[4]
       Hence for above case its ;
       x = 2 ; idx = 1{5} => ans = 1

       for X[1]; x = 1 ; ans = Count[0] = 0

       For X[2]; x = 6 ; idx = -1 since there is no element greater than 6 in Y; ans = 0

       Additionally we need to add all Y where its 0 or 1 when x > 1
       x = 2 ; ans = ans + Count[0] + Count[1] = 1 + 0 + 1 = 2
       x = 6 ; ans = ans + Count[0] + Count[1] = 0 + 0 + 1 = 1

       Hence answer is = 3

       Mathematically :
               x^y = y^x
        take natural log on both sides.
        => yln(x)=xln(y)
        => ln(x)/x = ln(y)/y

        now differentiate ln(x)/x wrt x and compare it with zero.

        => d/dx(ln(x)/x)
        => 1/x^2 - ln(x)/x^2
        => (1-ln(x))/x^2

        for all real x, x^2 >=0
        therefore : (1-ln(x)) >=0 for x<=e (~ 2.71 )

        1-ln(x) < 0 for x>e
        So ln(x)/x is increasing in range <=e, i.e. for integers, its increasing for 1,2
        and decreasing else where.

        Following are the cases :

        Case 1 : x<3 and y<3
        As in this range function ln(x)/x is increasing
        so f(x)>f(y), for x>y
        => ln(x)/x > ln(y)/y
        => x^y > y^x, for x>y and x<3 and y<3

        Case 2 : x>3 and y>3
        As in this range function is decreasing, so
        f(x)<f(y), for="" x="">y
        => ln(x)/x < ln(y)/y
        => x^y < y^x , for x>y and x>3 and y>3

        Case 3 : x<3 and y>=3
        Here x=1,2
        Case 3.1 : x=1
        this is trivial case 1^n=1
        so x^y < y^x

        => 1<y case="" 3.2="" :="" x="2" here="" x^y="2^y" and="" y^x="y^2" comparing="" the="" graph="" of="" exponential="" and="" parabola,="" following="" are="" results="" as="" y="">=3,
        2^3 < 3^2 => 8<9
        2^4 = 4^2 => 16=16
        2^5 > 5^2 => 32>25
        2^6 > 6^2 => 64>36
        2^7 > 7^2 => 128>49
        2^8 > 8^2 => 512 > 64
        ............... and so on.
        So, x^y > y^x, for x=2, and y>3

        x^y < y^x, for x=1,2 and y>=3

        Note :
        I have taken natural, you can take log for any base, answer will remain same because :

        d/dx(lnx)=1/x
        and d/dx(log(x)) = 1/x.log(b)

        where b is base, for which log is taken.

     */

    private static int pairs(int []xs, int []ys) {
        if (xs == null || xs.length == 0 || ys == null || ys.length == 0)
            return 0;

        int ans = 0;

        Arrays.sort(ys);

        int[] countOfY = new int[5]; //0,1,2,3,4
        for (int y : ys) {
            if (y < 5)
                countOfY[y]++;
            else
                break;
        }


        /*
         * Find for every element of X
         */

        for (int x : xs) {

            int pairsCount = binarySearch(x, ys, countOfY);
            ans += pairsCount;

        }

        return ans;

    }

    private static int binarySearch(int x, int[] y, int[] countOfY) {

        // x = 0 -> ans = 0
        if (x == 0)
            return 0;

        //x = 1 -> ans += Count[0]
        if (x == 1)
            return countOfY[0];

        int count ;

        // Find number of Y that is greater than x; finding index is sufficient as N-index would give the count
        int index = ceilIndex(y, x);

        /*
         * if no element found
         */
        if (index < 0) {
            count = 0;
        } else {
            /*
             * Skip same element in y which are equal to x
             */
            while (y[index] == x)
                index++;

            count = y.length - index;
        }


        /*
         * we need to remove where y=3 and 4 so
         *        x = 2 -> ans= (N-index) - Count[3] - Count[4]
         */
        if (x == 2 && count > 0)
            count = count - countOfY[3] - countOfY[4];

        /*
         *  Additionally we need to add all Y where its 0 or 1 when x > 1
         *  Example:
         *        x = 2 ; ans = ans + Count[0] + Count[1] = 1 + 0 + 1 = 2
         *        x = 6 ; ans = ans + Count[0] + Count[1] = 0 + 0 + 1 = 1
         */
        count = count + countOfY[0] + countOfY[1];

        return count;
    }

    private static int ceilIndex(int[] y, int x) {
        int low = 0, high = y.length - 1;

        while (low < high) {

            int mid = (low + high) >> 1;

            if (y[mid] >= x)
                high = mid;
            else
                low = mid + 1;
        }
        if (y[high] < x)
            return -1;

        return high;


    }
}