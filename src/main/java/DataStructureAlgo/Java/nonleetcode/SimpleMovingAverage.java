package DataStructureAlgo.Java.nonleetcode;

import  DataStructureAlgo.Java.helpers.GenericPrinter;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-14
 * Description: https://www.geeksforgeeks.org/program-find-simple-moving-average/
 * <p>
 * Simple Moving Average is the average obtained from the data for some t period of time . In normal mean,
 * it’s value get changed with the changing data but in this type of mean it also changes with the time interval .
 * We get the mean for some period t and then we remove some previous data . Again we get new mean and this process continues .
 * This is why it is moving average . This have a great application in financial market .
 * * <p>
 * * Examples:
 * * <p>
 * * Input : { 1, 3, 5, 6, 8 }
 * * Period = 3
 * * Output :New number added is 1.0, SMA = 0.3333333333333333
 * * New number added is 3.0, SMA = 1.3333333333333333
 * * New number added is 5.0, SMA = 3.0
 * * New number added is 6.0, SMA = 4.666666666666667
 * * New number added is 8.0, SMA = 6.333333333333333
 * <p>
 * {@link DataStructureAlgo.Java.companyWise.facebook.MovingAverage}
 */
public class SimpleMovingAverage {

    public static void main(String[] args) {

        test(new int[]{1, 2, 35, 343, 1, 21, 212, 324}, 3, new double[]{0.33, 1.0, 12.67, 126.67, 126.33, 121.67, 78.0, 185.67});
        test(new int[]{1, 3, 5, 6, 8}, 3, new double[]{0.33, 1.33, 3.0, 4.67, 6.33});
    }

    private static void test(int[] nums, int n, double[] expected) {
        System.out.println("\nNums:" + GenericPrinter.toString(nums) + " n:" + n + "\nExpected:" + GenericPrinter.toString(expected));
        System.out.println("Obtained:" + GenericPrinter.toString(movingAverage(nums, n)));
    }

    private static double[] movingAverage(int[] arr, int n) {

        MovingAverageHolder movingAverageHolder = new MovingAverageHolder(n);
        double[] avg = new double[arr.length];
        for (int i = 0; i < arr.length; i++)
            avg[i] = movingAverageHolder.getSimpleAverage(arr[i]);

        return avg;

    }

    static class MovingAverageHolder {

        int[] circularQueue;
        int size;
        int head = 0;
        int tail = 0;
        int sum = 0;
        int maxSize;

        public MovingAverageHolder(int n) {
            this.circularQueue = new int[n];
            this.maxSize = n;
        }

        //O(1)
        public double getSimpleAverage(int item) {

            this.sum += item;

            //if this reach max sized then, remove head element from sum
            if (this.size == maxSize) {

                this.sum -= circularQueue[head];

                //proceed the head pointer to next cell
                head = (head + 1) % this.maxSize;
            } else {
                //update this size
                this.size++;
            }

            //add this element in queue
            circularQueue[tail] = item;
            tail = (tail + 1) % this.maxSize;

            double av = ((double) sum / this.maxSize); //Note: this distinguish this question to actual moving average question

            return Math.round(av * 100) / 100.0;

        }
    }

}
