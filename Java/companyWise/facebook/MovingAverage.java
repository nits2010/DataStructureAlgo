package Java.companyWise.facebook;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 03/04/19
 * Description:
 */
public class MovingAverage {

    static class MovingAverageHolder {

        int circularQueue[];
        int size;
        int head = 0;
        int tail = 0;
        int sum = 0;
        double average = 0.0;
        int maxSize = 0;

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

            return ((double) sum / this.size);

        }
    }

    public static void main(String args[]) {

        int arr[] = {1, 2, 35, 343, 1, 21, 212, 324};
        int n = 3;

        movingAverage(arr, n);
    }

    private static void movingAverage(int arr[], int n) {

        MovingAverageHolder movingAverageHolder = new MovingAverageHolder(n);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(movingAverageHolder.getAverage(arr[i]));

        }
    }
}
