package DataStructureAlgo.Java.nonleetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Author: Nitin Gupta
 * Date: 2019-06-23
 * Description: https://www.geeksforgeeks.org/weighted-job-scheduling/
 * Given N jobs where every job is represented by following three elements of it.
 *
 * Start Time
 * Finish Time
 * Profit or Value Associated
 * Find the maximum profit subset of jobs such that no two jobs in the subset overlap.
 *
 * Example:
 *
 * Input: Number of Jobs n = 4
 *        Job Details {Start Time, Finish Time, Profit}
 *        Job 1:  {1, 2, 50}
 *        Job 2:  {3, 5, 20}
 *        Job 3:  {6, 19, 100}
 *        Job 4:  {2, 100, 200}
 * Output: The maximum profit is 250.
 * We can get the maximum profit by scheduling jobs 1 and 4.
 * Note that there is longer schedules possible Jobs 1, 2 and 3
 * but the profit with this schedule is 20+50+100 which is less than 250.
 */
public class WeightedJobScheduling {

    static class Job {
        int start;
        int finish;
        int profit;

        // Constructor
        Job(int start, int finish, int profit) {
            this.start = start;
            this.finish = finish;
            this.profit = profit;
        }
    }

    public static void main(String[] args) {
        Job[] jobs = {new Job(1, 2, 50), new Job(3, 5, 20),
                new Job(6, 19, 100), new Job(2, 100, 200)};

        System.out.println(maxProfit(jobs));

    }

    private static int maxProfit(Job[] jobs) {

        if (null == jobs || jobs.length == 0)
            return 0;


        int n = jobs.length;


        Arrays.sort(jobs, Comparator.comparingInt(o -> o.finish));


        int[] profit = new int[n];


        profit[0] = jobs[0].profit;

        for (int i = 1; i < n; i++) {

            int include = jobs[i].profit;

            int x = findLatestJobNotConflicting(jobs, i);
            if (x != -1)
                include += profit[x];

            int exclude = profit[i - 1];

            profit[i] = Math.max(include, exclude);
        }
        return profit[n - 1];


    }

    private static int findLatestJobNotConflicting(Job[] jobs, int i) {
        int start = 0, end = i - 1;

        while (start <= end) {
            int mid = (start + end) >> 1;

            if (jobs[mid].finish <= jobs[i].start) {

                if (jobs[mid + 1].finish < jobs[i].start)
                    start = mid + 1;
                else
                    return mid;

            } else
                end = mid - 1;

        }
        return -1;
    }
}
