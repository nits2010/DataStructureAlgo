package Java.LeetCode;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-07
 * Description:https://leetcode.com/problems/maximize-distance-to-closest-person/
 * n a row of seats, 1 represents a person sitting in that seat, and 0 represents that the seat is empty.
 * <p>
 * There is at least one empty seat, and at least one person sitting.
 * <p>
 * Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized.
 * <p>
 * Return that maximum distance to closest person.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,0,0,0,1,0,1]
 * Output: 2
 * Explanation:
 * If Alex sits in the second open seat (seats[2]), then the closest person has distance 2.
 * If Alex sits in any other open seat, the closest person has distance 1.
 * Thus, the maximum distance to the closest person is 2.
 * Example 2:
 * <p>
 * Input: [1,0,0,0]
 * Output: 3
 * Explanation:
 * If Alex sits in the last seat, the closest person is 3 seats away.
 * This is the maximum distance possible, so the answer is 3.
 */
public class MaximizeDistanceClosestPerson {

    public static void main(String []args) {
        SolutionMaximizeDistanceClosestPerson sol = new SolutionMaximizeDistanceClosestPerson();

        int seats1[] = {1, 0, 0, 0, 1, 0, 1}; // 2
        System.out.println("Expected: 2 Obtained " + sol.maxDistToClosest(seats1));

        int seats2[] = {1, 0, 0, 0}; // 3
        System.out.println("Expected: 3 Obtained " +sol.maxDistToClosest(seats2));

        int seats3[] = {0, 0, 0, 0, 0, 1}; // 5
        System.out.println("Expected: 5 Obtained " +sol.maxDistToClosest(seats3));

        int seats4[] = {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1}; // 3
        System.out.println("Expected: 3 Obtained " +sol.maxDistToClosest(seats4));

        int seats5[] = {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1}; // 6
        System.out.println("Expected: 6 Obtained " +sol.maxDistToClosest(seats5));

        int seats6[] = {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0}; // 9
        System.out.println("Expected: 9 Obtained " +sol.maxDistToClosest(seats6));


    }
}

class SolutionMaximizeDistanceClosestPerson {
    public int maxDistToClosest(int[] seats) {


        return sol2(seats);

    }

    public int sol2(int[] seats) {

        int dist = 0;
        int groupZero = 0;

        //count how many zero before first 1 to count seats gap
        while (seats[dist] == 0)
            dist++;

        groupZero = 0;

        //now find the next 1, the number of zero in getween will be counted.
        //as we see in two pointer approach, first 1 at f and second 1 at s; (s-p+1)/2 distance
        //hence for zero, (zero+1)/2
        for (int next = dist + 1; next < seats.length; next++) {

            if (seats[next] == 0)
                groupZero++;
            else {

                dist = Math.max(dist, (groupZero + 1) / 2);
                groupZero = 0;
            }
        }
        //may be next 1 never found,
        return Math.max(dist, groupZero);
    }

    public int sol1(int[] seats) {

        int prev = -1;
        int next = 0;

        int ans = 0;
        for (int i = 0; i < seats.length; i++) {

            //find second 1
            if (seats[i] == 1)
                prev = i;
            else {

                //find first 1
                while (next < seats.length && seats[next] == 0 || next < i)
                    next++;

                int left = prev == -1 ? seats.length : i - prev;
                int right = next == seats.length ? seats.length : next - i;

                ans = Math.max(ans, Math.min(left, right));

            }
        }

        return ans;

    }
}