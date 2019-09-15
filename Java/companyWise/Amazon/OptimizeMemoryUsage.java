package Java.companyWise.Amazon;

import Java.HelpersToPrint.GenericPrinter;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-05
 * Description:https://aonecode.com/amazon-online-assessment-oa2-optimize-memory-usage
 * <p>
 * Give a computer with total K memory space, and an array of foreground tasks and background tasks the computer need to do.
 * Write an algorithm to find a pair of tasks from each array to maximize the memory usage. Notice the tasks could be done
 * without origin order.
 * <p>
 * Input
 * The input to the function/method consists of three arguments :
 * foregroundTask, an array representing the memory usage of the foreground tasks,
 * backgroundTask, an array representing the memory usage of the background tasks,
 * K, the total memory space of the computer.
 * <p>
 * Output
 * Return a list of pairs of the task ids.
 * <p>
 * Examples 1
 * Input:
 * foregroundTasks = [1, 7, 2, 4, 5, 6]
 * backgroundTasks = [3, 1, 2]
 * K = 6
 * <p>
 * Output:
 * [(3, 2), (4, 1)]
 * <p>
 * Explanation:
 * Here we have 5 foreground tasks: task 0 uses 1 memory. task 1 uses 7 memeory. task 2 uses 2 memeory..
 * And 5 background tasks: task 0 uses 3 memory. task 1 uses 1 memory. task 2 uses 2 memory..
 * We need to find two tasks with total memory usage sum <= K.
 * So we find the foreground task 3 and background task 2. Total memory usage is 6.
 * And the foreground task 4 and background task 1. Total memory usage is also 6.
 * <p>
 * Examples 2
 * Input:
 * foregroundTasks = [1, 7, 2, 4, 5, 6]
 * backgroundTasks = [3, 1, 2]
 * K = 10
 * <p>
 * Output:
 * [(1, 0))]
 * <p>
 * Explanation:
 * Here we find the foreground task 1 and background task 2. Total memory usage is 7 + 2 = 9, which is < 10.
 * <p>
 * <p>
 * https://leetcode.com/discuss/interview-question/351807/Amazon-online-assessment-question-or-Help
 * <p>
 * Similar question: https://leetcode.com/discuss/interview-question/318918/Amazon-or-Online-Assessment-2019-or-Optimal-Aircraft-Utilization
 * <p>
 * Your task is to write an algorithm to optimize the sets of forward/return shipping route pairs that allow the aircraft to be optimally utilized, given a list a of forward routes and a list of return shipping routes.
 * <p>
 * INPUT
 * The input to the function/method consisits of three arguments:
 * maxTravelDist, an integer representing the maximum operating travel distance of the given aircraft;
 * forwardRouteList, a list of pairs of integers where the first integer represents the unique identifier of a forward shipping
 * route and the second integer represents the amount of travel distance required bu this shipping route;
 * returnRouteList, a list of pairs of integers where the first integer represents the unique identifer of a return shipping route
 * and the second integer represents the amount of travel distance required by this shipping route.
 * <p>
 * OUTPUT
 * Return a list of pairs of integers representing the pairs of IDs of forward and return shipping routes that optimally utilize the given aircraft. If no route is possible, return a list with empty pair.
 * <p>
 * Example 1:
 * Input:
 * maxTravelDist = 7000
 * forwardRouteList = [[1,2000],[2,4000],[3,6000]]
 * returnRouteList = [[1,2000]]
 * <p>
 * Output:
 * [[2,1]]
 * <p>
 * Explanation:
 * There are only three combinations [1,1],[2,1],and [3,1], which have a total of 4000, 6000, and 8000 miles, respectively. Since 6000 is the largest use that does tnot exceed 7000, [2,1] is the optimal pair.
 * <p>
 * Example 2:
 * Input:
 * maxTravelDist = 10,000
 * forwardRouteList = [[1,3000],[2,5000],[3,7000],[4,10000]]
 * returnRouteList = [[1,2000],[2,3000],[3,4000],[4,5000]]
 * <p>
 * Output:
 * [[2,4],[3,2]]
 * <p>
 * Explanation:
 * There are two pairs of forward and return shipping routes possible that optimally utilizes the given aircraft.
 * Shipping Route ID#2 from the forwardShippingRouteList, required 5000 miles travelled,
 * and Shipping Route ID#4 from returnShippingRouteList also required 5000 miles travelled. Combined, they add up to 10000 miles travelled.
 * Similarly, Shipping Route ID#3 from forwardShippingRouteList requires 7000 miles travelled, and
 * Shipping Route ID#2 from returnShippingRouteList requires 3000 miles travelled. These also add up to 10000 miles travelled. Therefore,
 * the pairs of forward and return shipping routes that optimally utilize the aircraft are [2,4] and [3,2].
 */
public class OptimizeMemoryUsage {

    public static void main(String[] args) {

        optimizeMemoryUsageTest(new int[]{1, 7, 2, 4, 5, 6}, new int[]{3, 1, 2}, 6);
        optimizeMemoryUsageTest(new int[]{1, 7, 2, 4, 5, 6}, new int[]{3, 1, 2}, 10);
        optimizeMemoryUsageTest(new int[]{2, 4, 6}, new int[]{2}, 7);
        optimizeMemoryUsageTest(new int[]{3, 5, 7, 10}, new int[]{2, 3, 4, 5}, 10);
    }


    public static void optimizeMemoryUsageTest(int foreground[], int background[], int memory) {

        if (memory == 0)
            System.out.println("Not found");

        if ((foreground == null && background == null) || (foreground.length == 0 && background.length == 0))
            System.out.println("Not found");

        System.out.println("\n\nForeground :");
        GenericPrinter.print(foreground);
        System.out.println("Background :");
        GenericPrinter.print(background);


        List<int[]> usages = OptimalMemoryUsage.optimizeMemoryUsage(foreground, background, memory);
        System.out.println("Optimal -> ");
        GenericPrinter.print(usages);

    }


}

/**
 * We need to find those indexes where total usage is "At most" equal to "memory" available which can be given by
 * 1. Either total usage is = memory usage [ This is max ]
 * 2. Or if all of the usage is less then available memory, then we need to find the maximum out of them
 * <p>
 * We can find the what is the max usage in our system by joining foreground and background usage and find out which is the
 * maximum s.t. its <= memory allowed.
 * Denoted as "max"
 * <p>
 * Now if we have max = memory then we'll get all the process which have memory usage up to "memory" available
 * otherwise we'll find the maximum out of them and get all the process which participate in maximum.
 */
class OptimalMemoryUsage {

    static class Process {
        int memoryUsage;
        int index;
    }

    public static List<int[]> optimizeMemoryUsage(int foreground[], int background[], int memory) {

        /**
         * this map holds the Usage(key) vs Process indexes which have this usage (key)
         */
        Map<Integer, List<int[]>> memoryUsageMap = new HashMap<>();


        /**
         * Build index and process usage list to identify its id
         */
        Process[] foregroundProcess = getProcess(foreground);
        Process[] backgroundProcess = getProcess(background);

        /**
         * Since we need to find the maximum s.t. <= memory then we can sort this and take the maximum one
         */
        Arrays.sort(foregroundProcess, Comparator.comparingInt(o -> o.memoryUsage));
        Arrays.sort(backgroundProcess, Comparator.comparingInt(o -> o.memoryUsage));


        int max = 0;
        for (int i = 0; i < foregroundProcess.length; i++) {

            /**
             * find for this usage, what is the maximum possible in background process s.t. background process
             * usage >= remaining usage ( memory - this foreground usage)
             */
            int fUsage = foregroundProcess[i].memoryUsage;

            int cellingIndex = getCellingIndex(backgroundProcess, memory - fUsage);

            int usage = fUsage + backgroundProcess[cellingIndex].memoryUsage;

            /**
             * if we have the usage which either < or = to given memory, not it down store.
             * As well as store what is the maximum one
             */
            if (usage <= memory) {
                max = Math.max(max, usage);

                if (!memoryUsageMap.containsKey(max))
                    memoryUsageMap.put(max, new ArrayList<>());

                memoryUsageMap.get(max).add(new int[]{foregroundProcess[i].index, backgroundProcess[cellingIndex].index});
            }
        }

        return memoryUsageMap.get(max);

    }

    private static int getCellingIndex(Process[] backgroundProcess, int usage) {

        int l = 0, h = backgroundProcess.length - 1;

        while (l < h) {
            int mid = (l + h) >> 1;

            if (backgroundProcess[mid].memoryUsage >= usage)
                h = mid;
            else
                l = mid + 1;


        }
        return l;
    }

    static Process[] getProcess(int process[]) {
        Process[] processes = new Process[process.length];

        for (int i = 0; i < process.length; i++) {
            processes[i] = new Process();
            processes[i].memoryUsage = process[i];
            processes[i].index = i;
        }


        return processes;
    }
}
