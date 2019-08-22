package Java;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-14
 * Description: https://www.geeksforgeeks.org/program-find-simple-moving-average/
 * <p>
 * Simple Moving Average is the average obtained from the data for some t period of time . In normal mean, it’s value get changed with the changing data but in this type of mean it also changes with the time interval . We get the mean for some period t and then we remove some previous data . Again we get new mean and this process continues . This is why it is moving average . This have a great application in financial market .
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
 *
 * {@link Java.companyWise.facebook.MovingAverage}
 */
public class SimpleMovingAverage {
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

            return ((double) sum / this.maxSize); //Note: this distinguish this question to actual moving average question

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

        SimpleMovingAverage.MovingAverageHolder movingAverageHolder = new SimpleMovingAverage.MovingAverageHolder(n);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(movingAverageHolder.getSimpleAverage(arr[i]));

        }
    }
}
