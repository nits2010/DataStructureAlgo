package DataStructureAlgo.Java.companyWise.Amazon.CarsAndDispensers;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-31
 * Description:
 * https://leetcode.com/discuss/interview-question/algorithms/124747/find-maximum-waiting-time
 * <p>
 * There are 3 fueling pumps(1, 2, ,3) with fixed capacity. There is a queue of vehicles waiting for fuel.
 * It takes 1 second to fuel 1 gallon. If multiple pumps can satisfy the vehicle, then the pump with least index takes
 * preference. Compute the maximum wait time
 * <p>
 * A{2, 8, 4, 3, 2}
 * X=7, Y=11, Z=3
 * <p>
 * For more info: see image
 */
public class CarsAndDispensers {

    public static void main(String[] args) {
        System.out.println(maxWaitTime(new int[]{2, 8, 4, 3, 2}, 0, 0, 0)); // -1
        System.out.println(maxWaitTime(new int[]{2, 8, 4, 3, 2}, 7, 11, 3)); // 8
        System.out.println(maxWaitTime(new int[]{2, 8, 4, 3, 2}, 0, 11, 3)); // -1
        System.out.println(maxWaitTime(new int[]{3, 2, 4, 3}, 8, 4, 10)); //3
        System.out.println(maxWaitTime(new int[]{5}, 4, 0, 3)); // -1
    }


    /**
     * We need to find the maximum time it takes to process all cars.
     * restriction is that
     * 1. If a car reaches on station X, and do not find the enough fuel, it will try station Y and Z. In case it does not find fuel,
     * then it will wait until some other gas pump is get free which has enough gas otherwise keep waiting
     * 2. At each gas station, once the gas is consumed by a car, it will reduce that much amount to of gas from that station.
     * <p>
     * Since we need to be care full that on a station there could be two possibilities
     * 1. Either occupied : Car has to try next station.
     * 2. Or unoccupied : Then only a car can occupied this station
     * <p>
     * <p>
     * We can assume that all station are in cycling connected graph. A car will be pass through all the station till it
     * able to fill the fuel.
     * The amount it takes to fill the fuel is the amount of gas is required.
     * <p>
     * Since we need to minimize the wait time, hence we need to apply BFS on this station graph.
     * Important point is, each car will wait in the queue for a car which occupied the current station
     *
     * @param A
     * @param X
     * @param Y
     * @param Z
     * @return
     */
    public static int maxWaitTime(int[] A, int X, int Y, int Z) {
        if (A == null || A.length == 0)
            return -1;

        /**
         * Tells about the time took at each station by a car to fuel the gas
         */
        Queue<Integer> occupiedTime = new LinkedList<>(Arrays.asList(0, 0, 0));
        /**
         * Hold the capacity in circular manner
         */
        Queue<Integer> dispensers = new LinkedList<>(Arrays.asList(X, Y, Z));

        /**
         * Tells that this car is stuck on any dispenser or not
         */
        Map<Integer, Boolean> carStatus = new HashMap<>();

        for (int i = 0; i < A.length; i++) {
            carStatus.put(i, false);
        }

        for (int i = 0; i < A.length; i++) {
            int carToFuel = i;

            while (!carStatus.get(i) && !dispensers.isEmpty()) {

                int demand = A[carToFuel];

                Integer currentCapacity = dispensers.peek();

                /**
                 * If this dispenser has no more fuel, then remove this dispenser
                 */
                if (currentCapacity <= 0) {

                    dispensers.poll();
                    continue;

                } else if (currentCapacity >= demand) { //if this dispenser can full fill the gas request.

                    //if all the cars has processed, and this is the car and we have enough fuel, then we are done
                    if (i == A.length - 1) {

                        return occupiedTime.peek();
                    }

                    /**
                     * Update the capacity
                     */
                    dispensers.add(dispensers.poll() - demand);

                    /**
                     * update the time took at this dispenser
                     */
                    occupiedTime.add(occupiedTime.poll() + demand);

                    /**
                     * here the jth car is filled, hence,
                     * Push this filled car towards left side, so that we don't need touch it again.
                     */
                    int temp = A[i];
                    A[i] = A[carToFuel];
                    A[carToFuel] = temp;

                    carStatus.put(i, true);

                    break;

                } else {

                    if (carToFuel == A.length - 1)
                        return -1;


                    //try next car
                    carToFuel++;


                }
            }

        }

        return -1;
    }


}
