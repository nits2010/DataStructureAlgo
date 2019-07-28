package Java.companyWise.facebook;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 03/04/19
 * Description: https://www.careercup.com/question?id=6313643925831680
 * Calculate a moving average that considers the last N values.
 */
public class MovingAverage {

    static class MovingAverageHolder {

        int circularQueue[];
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
            return (double) sum / (this.size % (this.maxSize + 1));
        }


    }

    public static void main(String args[]) {

        int arr[] = {1, 2, 35, 343, 1, 21, 212, 324};
        int n = 3;
        int arr2[] = {1, 3, 5, 6, 8};

        System.out.println("\n\n\narr1");
        movingAverage(arr, n);

        System.out.println("\n\narr2");
        movingAverage(arr2, n);
    }

    private static void movingAverage(int arr[], int n) {

        MovingAverageHolder movingAverageHolder = new MovingAverageHolder(n);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(movingAverageHolder.getAverage(arr[i]));

        }
    }
}
