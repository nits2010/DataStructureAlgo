package Java.companyWise.Amazon.BinaryPeriod;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-07
 * Description:
 */
public class BinaryPeriod {
    public static void main(String []args) {
        SolutionBinaryPeriod sol = new SolutionBinaryPeriod();
        System.out.println(sol.binaryPeriod(15));
        System.out.println(sol.binaryPeriodBiggest(15));


    }


}

class SolutionBinaryPeriod {


    //Smallest
    int binaryPeriod(int n) {
        int[] d = new int[30];
        int l = 0;
        int p;
        while (n > 0) {
            d[l] = n % 2;
            n /= 2;
            l++;
        }

        for (p = 1; p <= l / 2; ++p) { // mistake 1+l
            int i;
            boolean ok = true;
            for (i = 0; i < l - p; ++i) {
                if (d[i] != d[i + p]) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                return p; //this find the smallest
            }
        }
        return -1;
    }

    //wrong
    int binaryPeriodBiggest(int n) {
        int[] d = new int[30];
        int l = 0, res = -1;
        while (n > 0) {
            d[l] = n % 2;
            n /= 2;
            l++;
        }

        for (int p = 1; p <= l / 2; p++) {
            boolean ok = true;
            for (int i = 0; i < l - p; i++) {
                if (d[i] != d[i + p]) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                res = p; //This find the biggest number
            }
        }

        return res;
    }
}