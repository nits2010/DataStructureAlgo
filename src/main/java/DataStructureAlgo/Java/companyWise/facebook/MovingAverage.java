package DataStructureAlgo.Java.companyWise.facebook;

import  DataStructureAlgo.Java.helpers.GenericPrinter;
import  DataStructureAlgo.Java.nonleetcode.SimpleMovingAverage;

/**
 * Author: Nitin Gupta
 * Date: 03/04/19
 * Description: https://www.careercup.com/question?id=6313643925831680
 * Calculate a moving average that considers the last N values.
 * <p>
 * {@link SimpleMovingAverage}
 */
public class MovingAverage {

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
        public double getAverage(int item) {

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

            /**
             *  considers the last N values.
             */
//            if (this.size < this.maxSize)
//                return ((double) sum / this.size); //Note here we divide by the current size of array
//            else
//                return ((double) sum / this.maxSize); //Note here we divide by the max size of array

            //considers the last N values.
            double avg = (double) sum / (this.size % (this.maxSize + 1));
            return Math.round(avg * 100) / 100.0;
        }


    }

    public static void main(String[] args) {

        test(new int[]{1, 2, 35, 343, 1, 21, 212, 324}, 3, new double[]{1.0, 1.5, 12.67, 126.67, 126.33, 121.67, 78.0, 185.67});
        test(new int[]{1, 3, 5, 6, 8}, 3, new double[]{1.0, 2.0, 3.0, 4.67, 6.33});
    }

    private static void test(int[] nums, int n, double[] expected) {
        System.out.println("\nNums:" + GenericPrinter.toString(nums) + ", n:" + n + "\nExpected:" + GenericPrinter.toString(expected));
        System.out.println("Obtained:" + GenericPrinter.toString(movingAverage(nums, n)));
    }

    private static double[] movingAverage(int[] arr, int n) {

        MovingAverageHolder movingAverageHolder = new MovingAverageHolder(n);

        double[] avg = new double[arr.length];
        for (int i = 0; i < arr.length; i++)
            avg[i] = movingAverageHolder.getAverage(arr[i]);

        return avg;
    }
}
