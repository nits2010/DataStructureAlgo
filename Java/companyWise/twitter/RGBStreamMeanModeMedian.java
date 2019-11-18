package Java.companyWise.twitter;

import Java.helpers.GenericPrinter;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-24
 * Description: https://leetcode.com/discuss/interview-question/336848/Twitter-or-Phone-Screen-or-RGB-Stream
 * You have a set of users visiting your website. Each user picks a color represented by RGB. R, G, B can take values between 0 and 255.
 * <p>
 * You have a stream of users coming and at any point you have to calculate the mean and median for RGB colors seen so far.
 * <p>
 * Example:
 * <p>
 * setRGB(), red, green, blue, {0, ..., 255}
 * showMean()
 * showMedian()
 * RGB                 Mean                Median
 * (0, 0, 0),        (0, 0, 0)              (0, 0, 0)
 * (34, 232, 9), (17, 116, 4.5)   (17, 116, 4.5)
 * (43, 21, 34), (77/3, .., ..)        (34, 21, 9)
 */
public class RGBStreamMeanModeMedian {

    public static void main(String[] args) {

        test(new int[]{0, 34, 43, 39, 90}, new int[]{0, 232, 21, 210, 110}, new int[]{0, 9, 34, 23, 230});

    }

    private static void test(int[] r, int[] g, int[] b) {
        if (r == null || g == null || b == null || r.length != g.length || g.length != b.length)
            return;

        IRGBStream streamProcessor = new RGBStream();
        for (int i = 0; i < r.length; i++) {
            streamProcessor.setRGB(r[i], g[i], b[i]);
            System.out.println("\nr:" + r[i] + " g:" + g[i] + " b:" + b[i]);
            System.out.println("Mean:   " + GenericPrinter.toString(streamProcessor.showMean()));
            System.out.println("Median: " + GenericPrinter.toString(streamProcessor.showMedian()));
        }


    }

}

interface IRGBStream {
    int RANGE = 255;

    void setRGB(int red, int green, int blue);

    double[] showMean();

    double[] showMedian();
}

//Assuming stream come one by one (as it should be) and not accessed by multi thread
class RGBStream implements IRGBStream {

    private static final int MAX = Integer.MAX_VALUE;
    private static final int MIN = Integer.MIN_VALUE;

    final int[] R;
    int rMinimum = MAX, rMaximum = MIN;
    double rSum = 0;
    int rLength = 0;

    final int[] G;
    int gMinimum = MAX, gMaximum = MIN;
    double gSum = 0;
    int gLength = 0;

    final int[] B;
    int bMinimum = MAX, bMaximum = MIN;
    double bSum = 0;
    int bLength = 0;

    RGBStream() {
        R = new int[RANGE];
        G = new int[RANGE];
        B = new int[RANGE];

    }


    @Override
    public void setRGB(int red, int green, int blue) {
        red = red % RANGE;
        blue = blue % RANGE;
        green = green % RANGE;

        int oldR = R[red];
        int oldB = B[blue];
        int oldG = G[green];

        R[red]++;
        B[blue]++;
        G[green]++;

        //Find minimum Index
        rMinimum = Math.min(rMinimum, red);
        bMinimum = Math.min(bMinimum, blue);
        gMinimum = Math.min(gMinimum, green);

        //Find maximum Index
        rMaximum = Math.max(rMaximum, red);
        bMaximum = Math.max(bMaximum, blue);
        gMaximum = Math.max(gMaximum, green);

        //Find sum;  add new sum and remove old sum
        rSum += red * R[red] - (red * (oldR));
        gSum += green * G[green] - (green * (oldG));
        bSum += blue * B[blue] - (blue * (oldB));

        //find length
        rLength += R[red] - oldR;
        gLength += G[green] - oldG;
        bLength += B[blue] - oldB;

    }

    @Override
    public double[] showMean() {

        double rMean = rSum / rLength;
        double bMean = bSum / bLength;
        double gMean = gSum / gLength;


        return new double[]{round(rMean), round(gMean), round(bMean)};
    }

    /**
     * Either this or we have to use min/max -heap concept for finding median. But since its only 255 then its better as below
     *
     * @return
     */
    @Override
    public double[] showMedian() {

        double rMedian = getMedian(R, rMinimum, rMaximum, rLength);

        double gMedian = getMedian(G, gMinimum, gMaximum, gLength);

        double bMedian = getMedian(B, bMinimum, bMaximum, bLength);

        return new double[]{rMedian, gMedian, bMedian};
    }

    private double getMedian(int a[], int min, int max, int num) {
        int e1 = -1, e2 = -1;
        int total = 0;

        for (int i = min; i <= max; i++) {

            total += a[i];

            if (total >= num / 2 && e1 == -1)
                e1 = i;

            if (total >= num / 2 + 1) {
                e2 = i;
                break;
            }

        }

        return round((num % 2 == 0) ? ((double) (e1 + e2) / 2) : e2);
    }

    private double round(double n) {
        return Math.round(n * 100) / 100.00;
    }


}

