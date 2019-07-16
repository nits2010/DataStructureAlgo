package Java.companyWise.facebook;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-16
 * Description: https://www.geeksforgeeks.org/minimum-flips-make-1s-left-0s-right-set-2/
 * Given a binary array, we can flip all the 1 are in the left part and all the 0 to the right part.
 * Calculate the minimum flips required to make all 1s in left and all 0s in right.
 * <p>
 * Examples :
 * <p>
 * Input: 1011000
 * Output: 1
 * 1 flip is required to make it 1111000.
 * <p>
 * Input : 00001
 * Output : 2
 * 2 flips required to make it 10000.
 */
public class MinimumFlips1OnLeft0OnRight {

    public static void main(String args[]) {

        System.out.println(flips( "00001"));
        System.out.println(flips( "1011000"));
        System.out.println(flips( "1111000"));
        System.out.println(flips( "000000"));
        System.out.println(flips( "1111"));

    }

    /**
     * We need to count how many flips we should do so that all 1 is on left side and 0 on right side.
     * lets understand this problem with an example;
     * bits: 1011000
     * <p>
     * we have 3 -> 1 and 4 -> 0
     * if we change the second bit from right to 1, then all one be on one side
     * 1011000 -> 1111000
     * that require only 1 flip
     * <p>
     * bits: 000001
     * we have 1->1 and 5 -> 0
     * <p>
     * if we flip first bit on right side and 1 bit on left side
     * 000001 -> 100000
     * ans= 2
     * <p>
     * So what we are essentially doing is counting how many bits we need to flip on left side so that all 0 become 1
     * and
     * counting how many bits we need to flip on right side so that all 1 become 0.
     * <p>
     * Since we need to to minimize the total flips., we need to find the optimal point where they have minimum flips;
     * <p>
     * Lets count how many flips reuired to make all 0 to 1 on left side [1011000]
     * left -> [0, 1, 1, 1, 2,3,4]
     * similarly on right side all 1 to 0
     * left -> [3, 2, 2, 1, 0,0,0]
     * <p>
     * Now we need to find the optimal point at which number of flip on either side is minimum. which is sum of both side flips
     * <p>
     * LFlip[] = defines the number of flips required to make all 0 to 1 on left side
     * rFlip[] = defines the number of flips required to make all 1 to 0 on right side
     * <p>
     * min[i] = Min ( LFlip[i-1] + RFlip[i] )
     * <p>
     * ans would be the minimum value
     *
     * @param bits
     * @return
     */
    private static int flips(String bits) {


        if (null == bits || bits.isEmpty())
            return 0;

        int lFlip[] = new int[bits.length()];
        int rFlip[] = new int[bits.length()];

        lFlip[0] = bits.charAt(0) == '0' ? 1 : 0;

        for (int i = 1; i < bits.length(); i++)
            if (bits.charAt(i) == '0')
                lFlip[i] = lFlip[i - 1] + 1;
            else
                lFlip[i] = lFlip[i - 1];

        rFlip[bits.length() - 1] = bits.charAt(bits.length() - 1) == '1' ? 1 : 0;

        for (int i = bits.length() - 2; i >= 0; i--)
            if (bits.charAt(i) == '1')
                rFlip[i] = rFlip[i + 1] + 1;
            else
                rFlip[i] = rFlip[i + 1];

        int minimum = Integer.MAX_VALUE;

        for (int i = 1; i < bits.length(); i++)
            minimum = Math.min(lFlip[i - 1] + rFlip[i], minimum);

        return minimum;

    }
}
