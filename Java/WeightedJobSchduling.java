package Java;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-06-23
 * Description:
 */
public class WeightedJobSchduling {

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

    public static void main(String args[]) {
        Job jobs[] = {new Job(1, 2, 50), new Job(3, 5, 20),
                new Job(6, 19, 100), new Job(2, 100, 200)};

        System.out.println(maxProfit(jobs));

    }

    private static int maxProfit(Job[] jobs) {

        if (null == jobs || jobs.length == 0)
            return 0;


        int n = jobs.length;


        Arrays.sort(jobs, Comparator.comparingInt(o -> o.finish));


        int profit[] = new int[n];


        profit[0] = jobs[0].profit;

        for (int i = 1; i < n; i++) {

            int include = jobs[i].profit;

            int x = findLatestJobNotConflicting(jobs, i);
            if (x != -1)
                include += profit[x];

            int exluded = profit[i - 1];

            profit[i] = Math.max(include, exluded);
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
