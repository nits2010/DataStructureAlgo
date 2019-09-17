package Java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-18
 * Description:
 * <p>
 */

class Job {
    String id;
    int startTime;
    int deadline;
    double profit;
    int lossInTime;

    public Job(String id, int startTime, int deadline) {
        this.id = id;
        this.startTime = startTime;
        this.deadline = deadline;
    }

    public Job(String id, int startTime, int deadline, double profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
        this.startTime = startTime;
    }

    public Job(String id, int startTime, int deadline, int lossInTime, double profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
        this.startTime = startTime;
        this.lossInTime = lossInTime;
    }


    @Override
    public String toString() {
        return "Job{" +
                "id='" + id + '\'' +
                '}';
    }
}

public class JobScheduling {

    public static void main(String []args) {
        testActivitySelection();

        testSequence();
        testWithLoss();

        testWithLossVolume();
    }

    private static void testSequence() {

        List<Job> jobs = new ArrayList<>(5);
        jobs.add(new Job("a", 2, 2, 100.0));
        jobs.add(new Job("b", 1, 1, 19.0));
        jobs.add(new Job("c", 2, 2, 27.0));
        jobs.add(new Job("d", 1, 1, 25.0));
        jobs.add(new Job("e", 3, 3, 15.0));

        jobs.add(new Job("f", 2, 3, 200.0));
        jobs.add(new Job("g", 1, 5, 19.0));
        jobs.add(new Job("h", 2, 7, 207.0));
        jobs.add(new Job("i", 1, 1, 25.0));
        jobs.add(new Job("j", 3, 3, 105.0));

        JobSchedulingGreedy greedy = new JobSchedulingGreedy();
        JobSchedulingDisJointSet disJointSet = new JobSchedulingDisJointSet();

        System.out.println("Greedy ");
        System.out.println(greedy.maxProfitGreedy(jobs));
        System.out.println("Disjoint set ");
        System.out.println(disJointSet.maxProfitGreedy(jobs));
    }


    private static void testActivitySelection() {
        System.out.println("\n Activity selection ");
        List<Job> jobs = new ArrayList<>(5);
        jobs.add(new Job("0", 3, 4));
        jobs.add(new Job("1", 1, 2));
        jobs.add(new Job("2", 8, 9));
        jobs.add(new Job("3", 0, 6));
        jobs.add(new Job("4", 5, 7));
        jobs.add(new Job("5", 5, 9));


        ActivitySelection activitySelection = new ActivitySelection();
        System.out.println(activitySelection.activitySelection(jobs));


    }

    private static void testWithLoss() {

        System.out.println("\ntestWithLoss");

        List<Job> jobs = new ArrayList<>(5);
        jobs.add(new Job("1", 1, 1, 2, 100.0));
        jobs.add(new Job("2", 2, 2, 4, 19.0));
        jobs.add(new Job("3", 3, 3, 1, 27.0));
        jobs.add(new Job("4", 5, 5, 3, 25.0));
        jobs.add(new Job("5", 6, 6, 2, 15.0));

        JobSchedulingMinimizeLoss minimizeLoss = new JobSchedulingMinimizeLoss();
        System.out.println("Greedy ");
        System.out.println(minimizeLoss.optimalSequenceOfJobs(jobs));

    }


    private static void testWithLossVolume() {

        int a[] = {4, 2, 151, 15, 1, 52, 12};
        double p = 10;


        int b[] = {3, 1, 41, 52, 15, 4, 1, 63, 12};
        p = 20;
    }

}


/**
 * You are given n activities with their start and finish times. Select the maximum number of activities that can be performed by a single person, assuming that a person can only work on a single activity at a time.
 * Example:
 * <p>
 * Example 1 : Consider the following 3 activities sorted by
 * by finish time.
 * start[]  =  {10, 12, 20};
 * finish[] =  {20, 25, 30};
 * A person can perform at most two activities. The
 * maximum set of activities that can be executed
 * is {0, 2} [ These are indexes in start[] and
 * finish[] ]
 * <p>
 * Example 2 : Consider the following 6 activities
 * sorted by by finish time.
 * start[]  =  {1, 3, 0, 5, 8, 5};
 * finish[] =  {2, 4, 6, 7, 9, 9};
 * A person can perform at most four activities. The
 * maximum set of activities that can be executed
 * is {0, 1, 3, 4} [ These are indexes in start[] and
 * finish[] ]
 */
class ActivitySelection {

    public List<Job> activitySelection(List<Job> jobs) {
        if (jobs == null || jobs.isEmpty())
            return Collections.EMPTY_LIST;

        if (jobs.size() == 1)
            return jobs;

        /**
         * Sort by deadline increasing order
         */
        Collections.sort(jobs, Comparator.comparingDouble(o -> o.deadline));

        List<Job> selected = new ArrayList<>();

        int i = 0;
        selected.add(jobs.get(i));

        for (int j = 1; j < jobs.size(); j++) {

            if (jobs.get(j).startTime >= jobs.get(i).deadline) {
                selected.add(jobs.get(j));
                i = j;
            }
        }
        return selected;

    }
}


/**
 * https://www.geeksforgeeks.org/job-sequencing-problem/
 * Given a set of n jobs where each job i has a deadline di >=1 and profit pi>=0. Only one job can be scheduled at a time. Each job takes 1 unit of time to complete. We earn the profit
 * if and only if the job is completed by its deadline. The task is to find the subset of jobs that maximizes profit.
 * <p>
 * Examples:
 * <p>
 * Input: Four Jobs with following deadlines and profits
 * JobID Deadline Profit
 * a      4      20
 * b      1      10
 * c      1      40
 * d      1      30
 * Output: Following is maximum profit sequence of jobs:
 * c, a
 * Input: Five Jobs with following deadlines and profits
 * JobID Deadline Profit
 * a     2       100
 * b     1       19
 * c     2       27
 * d     1       25
 * e     3       15
 * Output: Following is maximum profit sequence of jobs:
 * c, a, e
 * Solution through Greedy approach
 * * O(n^2)
 */
class JobSchedulingGreedy {


    public double maxProfitGreedy(List<Job> jobs) {

        if (jobs == null || jobs.isEmpty())
            return 0.0;

        if (jobs.size() == 1)
            return jobs.get(0).profit;


        /**
         * Sort by profit Decreasing Order
         * O(nlogn)
         */
        Collections.sort(jobs, (o1, o2) -> Double.compare(o2.profit, o1.profit));


        /**
         * to assign slots; choose the max by time
         */
        boolean slots[] = new boolean[jobs.size()];
        int jobId[] = new int[jobs.size()]; //to show the ids

        double maxProfit = 0;

        //O(n^2)
        for (int i = 0; i < jobs.size(); i++) { //O(n)


            int deadline = jobs.get(i).deadline;

            /**
             * Find the max slot where this job can fit, finding max will ensure the other jobs won't starve for slot
             *
             * Suppose that a job J1 has a deadline of time t = 5. We assign the greatest
             * time slot which is free and less than the deadline i.e 4-5 for this job. Now another job J2 with deadline of 5 comes in,
             * so the time slot allotted will be 3-4 since 4-5 has already been allotted to job J1.
             */
            //O(n)
            for (int j = Math.min(deadline, jobs.size()) - 1; j >= 0; j--)

                if (!slots[j]) {
                    maxProfit += jobs.get(i).profit;
                    jobId[j] = i;
                    slots[j] = true;
                    break;
                }


        }

        for (int i = 0; i < jobId.length; i++)
            if (slots[i])
                System.out.print(jobs.get(jobId[i]) + " ");

        return maxProfit;

    }

}



/**
 * https://www.geeksforgeeks.org/job-sequencing-using-disjoint-set-union/
 * Solution through Disjoint set data structure approach
 * O(nlogn)
 */
class JobSchedulingDisJointSet {



    /**
     * O(nglogn)
     *
     * @param jobs
     * @return
     */

    public double maxProfitGreedy(List<Job> jobs) {

        if (jobs == null || jobs.isEmpty())
            return 0.0;

        if (jobs.size() == 1)
            return jobs.get(0).profit;

        int n = jobs.size();

        UnionFindDisjointSets disjointSet = new UnionFindDisjointSets(n);

        /**
         * Sort by profit Decreasing order
         * O(nlogn)
         */
        Collections.sort(jobs, (o1, o2) -> Double.compare(o2.profit, o1.profit));
        int jobId[] = new int[jobs.size()]; //to show the ids
        boolean slots[] = new boolean[jobs.size()];
        double maxProfit = 0;

        //O(nlogn)
        for (int i = 0; i < jobs.size(); i++) {

            //O(logn)
            //any available slots for this job?
            int slotsId = disjointSet.findParent(jobs.get(i).deadline);

            //If slot is available
            if (slotsId > 0) {

                //O(logn)
                //then make sure this slot and previous slot merge s.t. empty slot is on top, here slot-1 would be
                disjointSet.unionBoth(slotsId - 1, slotsId);

                jobId[slotsId] = i;
                slots[slotsId] = true;
                maxProfit += jobs.get(i).profit;
            }
        }

        for (int i = 0; i < jobId.length; i++)
            if (slots[i])
                System.out.print(jobs.get(jobId[i]) + " ");
        return maxProfit;
    }

}

/**
 * https://www.geeksforgeeks.org/job-selection-problem-loss-minimization-strategy-set-2/
 * We are given a sequence of N goods of production numbered from 1 to N.
 * Each good has a volume denoted by (Vi). The constraint is that once a good has been completed its volume starts decaying at a fixed percentage (P) per day.
 * All goods decay at the same rate and further each good take one day to complete.
 * We are required to find the order in which the goods should be produced so that overall volume of goods is maximized.
 * <p>
 * Example-1:
 * <p>
 * Input: 4, 2, 151, 15, 1, 52, 12 and P = 10%
 * Output: 222.503
 * Solution: In the optimum sequence of jobs, the total volume of goods left at the end of all jobs is 222.503
 * <p>
 * Example-2:
 * <p>
 * Input: 3, 1, 41, 52, 15, 4, 1, 63, 12 and P = 20%
 * Output: 145.742
 */

class JobSchedulingMinimizeLossVolume {

}


/**
 * https://www.geeksforgeeks.org/job-sequencing-problem-loss-minimization/
 * O(nlgon)
 * We are given N jobs numbered 1 to N. For each activity, let Ti denotes the number of days required to complete the job.
 * For each day of delay before starting to work for job i, a loss of Li is incurred.
 * We are required to find a sequence to complete the jobs so that overall loss is minimized. We can only work on one job at a time.
 * <p>
 * If multiple such solutions are possible, then we are required to give the lexicographically least permutation (i.e earliest in dictionary order).
 * <p>
 * Examples:
 * <p>
 * Input : L = {3, 1, 2, 4} and
 * T = {4, 1000, 2, 5}
 * Output : 3, 4, 1, 2
 * Explanation: We should first complete
 * job 3, then jobs 4, 1, 2 respectively.
 * <p>
 * Input : L = {1, 2, 3, 5, 6}
 * T = {2, 4, 1, 3, 2}
 * Output : 3, 5, 4, 1, 2
 * Explanation: We should complete jobs
 * 3, 5, 4, 1 and then 2 in this order.
 */
class JobSchedulingMinimizeLoss {

    private int compareJobsByRationOfLossAndTime(Job a, Job b) {

        int at = a.deadline;
        int bt = b.deadline;

        int al = a.lossInTime;
        int bl = b.lossInTime;

        //al/at < bl<bt => al*bt < bl*at

        return Integer.compare((al * bt), (bl * at));


    }

    public List<Job> optimalSequenceOfJobs(final List<Job> jobs) {
        if (jobs == null || jobs.isEmpty())
            return Collections.EMPTY_LIST;

        if (jobs.size() == 1)
            return jobs;

        //It sort by merge sort- stable sort
        Collections.sort(jobs, (o1, o2) -> compareJobsByRationOfLossAndTime(o1, o2));

        return jobs;

    }

}