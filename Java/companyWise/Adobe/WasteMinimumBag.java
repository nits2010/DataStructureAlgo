package Java.companyWise.Adobe;

import Java.HelpersToPrint.GenericPrinter;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-24
 * Description: https://www.geeksforgeeks.org/adobe-interview-experience-set-26-on-campus-for-mts-1/#targetText=A%20CEO%20of%20a%20company,much%20cookies%20one%20person%20needs.
 * You are given the waste at certain positions with values between x and y and you can carry atmost y kg at one time.
 * You had to tell the minimum number of bags required to carry the waste.
 * Input
 * 4
 * 1.30 1.40 1.50 1.60
 * Output
 * 2
 * Input
 * 4
 * 1.40 1.70 1.50 1.50
 * Output
 * 3
 */
public class WasteMinimumBag {

    public static void main(String[] args) {


        test(new double[]{1.30, 1.40, 1.50, 1.60}, 4.0, 2, 2);
        test(new double[]{1.40, 1.70, 1.50, 1.50}, 4.0, 2, 2);
        test(new double[]{2.40, 1.70, 2.50, 1.50}, 4.0, 3, 3);
        test(new double[]{1.60, 1.10, 1.12}, 2, 2, 3);


    }

    private static void test(double[] waste, double capacity, int expected, int zeroOr1Expected) {
        System.out.println("\nInput :" + GenericPrinter.toString(waste) + " capacity : " + capacity);
        System.out.println("Partially : obtained : " + minimumBag(waste, capacity) + " expected :" + expected);
        System.out.println("0/1 : obtained : " + minimumBag0Or1(waste, capacity) + " expected :" + zeroOr1Expected);

    }


    /**
     * Assumption: We can divide the waste in two different bags if needed
     * Algo: 1:
     * 1. Take a bag, and try to fill it as much possible.
     * 2. Once we see, we need another bag then increase the bag count and reduce the overflowed waste amount.
     * 3. continue this
     * <p>
     * O(n)
     *
     * @param waste
     * @param maxCap
     * @return
     */
    public static int minimumBag(double[] waste, double maxCap) {

        if (waste == null || waste.length == 0)
            return 0;

        if (Double.compare(maxCap, 0) == 0)
            return Integer.MAX_VALUE;

        double sumOfWaste = 0;
        int minimumBag = 1;

        for (int i = 0; i < waste.length; i++) {

            sumOfWaste += waste[i];

            if (sumOfWaste > maxCap) {
                minimumBag++;
                sumOfWaste -= maxCap;
            }

        }
        return minimumBag;

    }

    /**
     * Assumption: We can't divide the waste in two different bags
     * Algo: 1:
     * 1. Take a bag, and try to fill it as much possible.
     * 2. Once we see, we need another bag then increase the bag count and reduce the overflowed waste amount.
     * 3. continue this
     *
     * @param waste
     * @param maxCap
     * @return
     */
    public static int minimumBag0Or1(double[] waste, double maxCap) {

        if (waste == null || waste.length == 0)
            return 0;

        if (Double.compare(maxCap, 0) == 0)
            return Integer.MAX_VALUE;

        double sumOfWaste = 0;
        int minimumBag = 1;

        for (int i = 0; i < waste.length; i++) {

            sumOfWaste += waste[i];

            if (sumOfWaste > maxCap) {
                minimumBag++;
                sumOfWaste = waste[i];
            }

        }
        return minimumBag;

    }
}
