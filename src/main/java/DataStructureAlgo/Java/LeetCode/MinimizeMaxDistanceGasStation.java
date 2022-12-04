package DataStructureAlgo.Java.LeetCode;

import  DataStructureAlgo.Java.helpers.GenericPrinter;
import  DataStructureAlgo.Java.nonleetcode.PainterPartitionProblem;

/**
 * Author: Nitin Gupta
 * Date: 16/09/19
 * Description: https://leetcode.com/problems/minimize-max-distance-to-gas-station/
 * http://leetcode.liangjiateng.cn/leetcode/minimize-max-distance-to-gas-station/description
 * https://www.lintcode.com/problem/minimize-max-distance-to-gas-station/note
 * 774.Minimize Max Distance to Gas Station [hard]
 * On a horizontal number line, we have gas stations at positions stations[0], stations[1], ..., stations[N-1], where N = stations.length.
 * <p>
 * Now, we add K more gas stations so that D, the maximum distance between adjacent gas stations, is minimized.
 * <p>
 * Return the smallest possible value of D.
 * <p>
 * Example:
 * <p>
 * Input: stations = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], K = 9
 * Output: 0.500000
 * Note:
 * <p>
 * stations.length will be an integer in range [10, 2000].
 * stations[i] will be an integer in range [0, 10^8].
 * K will be an integer in range [1, 10^6].
 * Answers within 10^-6 of the true value will be accepted as correct.
 * <p>
 * Similar to {@link PainterPartitionProblem}
 */
public class MinimizeMaxDistanceGasStation {

    public static void main(String[] args) {
        test(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 9, 0.5);
        test(new int[]{1, 5, 10}, 2, 2.5);
        test(new int[]{1, 5, 10}, 3, 2.0);
        test(new int[]{3, 6, 12, 19, 33, 44, 67, 72, 89, 95}, 2, 14.0);


    }

    private static void test(int[] stations, int k, Double expected) {
        System.out.println("\n stations :" + GenericPrinter.toString(stations) + " k:" + k + "\nexpected:" + expected);
        MinimizeMaxDistanceGasStationDPBottomUp bottomUp = new MinimizeMaxDistanceGasStationDPBottomUp();
        MinimizeMaxDistanceGasStationBinarySearch binarySearch = new MinimizeMaxDistanceGasStationBinarySearch();
        System.out.println("bottomUp :" + bottomUp.minmaxGasDist(stations, k));
        System.out.println("binarySearch :" + binarySearch.minmaxGasDist(stations, k));
    }
}

/**
 * Similar idea as {@link PainterPartitionProblem}.
 * here; painter = station to be added
 * and board = stations given.
 * <p>
 * <p>
 * M[i][j] represent the minimum distance between stations from 0 to i by adding j stations optimally
 * <p>
 * Base :
 * M[i][0] if we don't add any station then whatever the distance between stations will remain same;
 * M[i][0] = stations[i] - stations[i-1]
 * <p>
 * M[i][j] to add a station j at i, we need to see what was the minimum distance we were able to get earlier when
 * *         adding this station did not there. which is M[i-1][j] but since we need to know all the states of earlier stations when adding 0 to j station in order to find what happen
 * *        with distance by adding current station 'i' at 'j' additional stations.
 * *        Which means we need to look back all the 0...j additional stations earlier hence M[i-1][0...j]
 * <p>
 * M[i][j] = Min { Max { M[i-1][s-p], (cost of adding p stations between i to i-1}} ; 0<=p<=s and 1<=s<=k
 * (cost of adding p stations between i to i-1} = (stations[i] - stations[i-1])/(p+1) ; means divide the space between i and i-1 in p equal parts.
 * <p>
 * M[i-1][s-p] : means what was the cost when having 0 to s stations in earlier.
 * <p>
 * Complexity: O(n*k^2) / O (n*k)
 */
class MinimizeMaxDistanceGasStationDPBottomUp {
    public double minmaxGasDist(int[] stations, int k) {

        if (stations == null || stations.length == 0)
            return 0;

        final int N = stations.length;
        double[][] minDistance = new double[N][k + 1];

        for (int i = 1; i < N; i++) {
            minDistance[i][0] = Math.max(minDistance[i - 1][0], stations[i] - stations[i - 1]);
        }

        /**
         *
         * M[i][j] = Min
         * *        { Max
         * *            { M[i-1][s-p], (stations[i] - stations[i-1])/(p+1)} ; 0<=p<=s and 1<=s<=k
         *
         */

        //O(k)
        for (int s = 1; s <= k; s++) {
            //O(n)
            for (int i = 1; i < N; i++) {

                double min = Integer.MAX_VALUE;
                //O(k)
                for (int p = 0; p <= s; p++) {
                    double a = minDistance[i - 1][s - p];
                    double b = (double) (stations[i] - stations[i - 1]) / (p + 1);
                    min = Math.min(min, Math.max(a, b));
                }

                minDistance[i][s] = min;
            }
        }


        return minDistance[N - 1][k];
    }


}


/**
 * Similar idea as {@link PainterPartitionProblem}. #Optimized
 * we can apply binary search algo here.
 * <p>
 * We know the minimum distance we can make between any two stations 0 {hypothetically putting infinite stations between two stations}
 * and max is the maximum distance we have in given array.
 * <p>
 * low = 0, high = maxDistance
 * Now we know our answer would lie between [low, high]. Hence we try all the possibility.
 * <p>
 * To find a distance mid= (low+high)/2 is feasible or not, we need to see is it possible to put k stations between all stations such that distance won't go beyond mid.
 * case 1: if goes beyond, then we need to increase our search space so that low = mid
 * case 2: if require less, then we need to reduce the search space so high = mid
 * case 3: we found one potetial solution, try to reduce it more so that we can minimize the maximum distance.
 * <p>
 * Complexity:  O( n *  log (max)) but max is limit  to [0, 10^8] hence O(n)
 * This verification algorithm runs at O(n), where n is the length of the stations array. T
 * his is acceptable if we can walk the search space very efficiently (which can be done at the order of O(log(max/step)),
 * with step = 10^-6). In particular, this is much faster than the straightforward O(Klogn) solution where we add the stations one by one in a greedy manner (i.e.,
 * always reduce the current maximum distance first), given that K could be orders of magnitude larger than n (note this greedy algorithm can be optimized to run at O(nlogn),
 * <p>
 * Your submission beats 92.78% Submissions!
 * <p>
 * Priority queue solution: https://www.youtube.com/watch?v=C_OdHoPJqLQ
 */
class MinimizeMaxDistanceGasStationBinarySearch {
    public double minmaxGasDist(int[] stations, int k) {

        if (stations == null || stations.length == 0)
            return 0;

        double low = 0;
        double high = stations[stations.length - 1] - stations[0]; //stations are in increasing order
        double eps = 1e-6;

        //O( n* log (max))
        while ((low + eps) < high) { //O( log (max))
            double mid = (low + high) / 2;

            if (isPossible(stations, k, mid)) {
                high = mid;
            } else
                low = mid;
        }
        return Math.round(low * 100.0) / 100.0; //round low to 2 decimal

    }

    //O(n)
    private boolean isPossible(int[] stations, int k, double mid) {
        int requireStations = 0;
        for (int i = 1; i < stations.length; i++) {
            //find how many stations would require between stations[i] and stations[i-1] in order to have distance between them as mid
            requireStations += (int) Math.abs((stations[i] - stations[i - 1]) / mid);
        }
        return requireStations <= k;
    }


}
