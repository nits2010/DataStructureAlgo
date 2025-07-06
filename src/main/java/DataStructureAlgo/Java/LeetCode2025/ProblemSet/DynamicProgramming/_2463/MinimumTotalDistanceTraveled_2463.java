package DataStructureAlgo.Java.LeetCode2025.ProblemSet.DynamicProgramming._2463;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.nonleetcode.Knapsack;

/**
 * Author: Nitin Gupta
 * Date: 11/1/2024
 * Question Category: 2463. Minimum Total Distance Traveled
 * Description: https://leetcode.com/problems/minimum-total-distance-traveled/description/?envType=daily-question&envId=2024-10-31
 * There are some robots and factories on the X-axis. You are given an integer array robot where robot[i] is the position of the ith robot. You are also given a 2D integer array factory where factory[j] = [positionj, limitj] indicates that positionj is the position of the jth factory and that the jth factory can repair at most limitj robots.
 * <p>
 * The positions of each robot are unique. The positions of each factory are also unique. Note that a robot can be in the same position as a factory initially.
 * <p>
 * All the robots are initially broken; they keep moving in one direction. The direction could be the negative or the positive direction of the X-axis. When a robot reaches a factory that did not reach its limit, the factory repairs the robot, and it stops moving.
 * <p>
 * At any moment, you can set the initial direction of moving for some robot. Your target is to minimize the total distance traveled by all the robots.
 * <p>
 * Return the minimum total distance traveled by all the robots. The test cases are generated such that all the robots can be repaired.
 * <p>
 * Note that
 * <p>
 * All robots move at the same speed.
 * If two robots move in the same direction, they will never collide.
 * If two robots move in opposite directions and they meet at some point, they do not collide. They cross each other.
 * If a robot passes by a factory that reached its limits, it crosses it as if it does not exist.
 * If the robot moved from a position x to a position y, the distance it moved is |y - x|.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: robot = [0,4,6], factory = [[2,2],[6,2]]
 * Output: 4
 * Explanation: As shown in the figure:
 * - The first robot at position 0 moves in the positive direction. It will be repaired at the first factory.
 * - The second robot at position 4 moves in the negative direction. It will be repaired at the first factory.
 * - The third robot at position 6 will be repaired at the second factory. It does not need to move.
 * The limit of the first factory is 2, and it fixed 2 robots.
 * The limit of the second factory is 2, and it fixed 1 robot.
 * The total distance is |2 - 0| + |2 - 4| + |6 - 6| = 4. It can be shown that we cannot achieve a better total distance than 4.
 * Example 2:
 * <p>
 * <p>
 * Input: robot = [1,-1], factory = [[-2,1],[2,1]]
 * Output: 2
 * Explanation: As shown in the figure:
 * - The first robot at position 1 moves in the positive direction. It will be repaired at the second factory.
 * - The second robot at position -1 moves in the negative direction. It will be repaired at the first factory.
 * The limit of the first factory is 1, and it fixed 1 robot.
 * The limit of the second factory is 1, and it fixed 1 robot.
 * The total distance is |2 - 1| + |(-2) - (-1)| = 2. It can be shown that we cannot achieve a better total distance than 2.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= robot.length, factory.length <= 100
 * factory[j].length == 2
 * -109 <= robot[i], positionj <= 109
 * 0 <= limitj <= robot.length
 * The input will be generated such that it is always possible to repair every robot.
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link Knapsack}
 * <p><p>
 * Tags
 * -----
 *
 * @hard
 * @Array
 * @DynamicProgramming
 * @Sorting <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSoltuion
 */
public class MinimumTotalDistanceTraveled_2463 {
    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new ArrayList<>(Arrays.asList(0, 4, 6)), new int[][]{{2, 2}, {6, 2}}, 4));
        tests.add(test(new ArrayList<>(Arrays.asList(1, -1)), new int[][]{{-2, 1}, {2, 1}}, 2));
        CommonMethods.printAllTestOutCome(tests);

    }

    private static boolean test(List<Integer> robot, int[][] factory, long expected) {
        CommonMethods.printTest(new String[]{"robot", "factory", "expected"}, true, robot, factory, expected);

        long output;
        boolean pass, finalPass = true;

        Recursion.SolutionRecursion1 solutionRecursion1 = new Recursion.SolutionRecursion1();
        output = solutionRecursion1.minimumTotalDistance(robot, factory);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Recursion-1", "Pass"}, false, output, (pass ? "Yes" : "No"));

        Recursion.SolutionRecursion2 solutionRecursion2 = new Recursion.SolutionRecursion2();
        output = solutionRecursion2.minimumTotalDistance(robot, factory);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Recursion-2", "Pass"}, false, output, (pass ? "Yes" : "No"));


        DynamicProgramming.TopDown_Memoization topDownMemoization = new DynamicProgramming.TopDown_Memoization();
        output = topDownMemoization.minimumTotalDistance(robot, factory);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"topDownMemoization", "Pass"}, false, output, (pass ? "Yes" : "No"));

        DynamicProgramming.TopDown_Memoization2 topDownMemoization2 = new DynamicProgramming.TopDown_Memoization2();
        output = topDownMemoization2.minimumTotalDistance(robot, factory);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"topDownMemoization-2", "Pass"}, false, output, (pass ? "Yes" : "No"));

        DynamicProgramming.BottomUp_Tabulations bottomUpTabulations = new DynamicProgramming.BottomUp_Tabulations();
        output = bottomUpTabulations.minimumTotalDistance(robot, factory);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"bottomUpTabulations", "Pass"}, false, output, (pass ? "Yes" : "No"));

        DynamicProgramming.BottomUp_Tabulations2 bottomUpTabulations2 = new DynamicProgramming.BottomUp_Tabulations2();
        output = bottomUpTabulations2.minimumTotalDistance(robot, factory);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"bottomUpTabulations-2", "Pass"}, false, output, (pass ? "Yes" : "No"));


        return finalPass;
    }


    static class Recursion {
        static class SolutionRecursion1 {
            public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
                if (factory.length == 0 || robot.isEmpty()) return 0;


                //Since we need to assign robots and robots which has the nearest factory will move in the same the direction,
                //it will be easy to maintain then in one the directions only instead of moving left or right based on distance.
                //Hence, sorting robots will simplify the robot assignment in one direction.
                Collections.sort(robot);

                //Similarly, since we are assigning robot in one increasing direction, making factories same also simplifies
                //sort factories by their position
                Arrays.sort(factory, Comparator.comparingInt(a -> a[0]));


                //Now, we have to assign robots to the nearest factory and additionally since a factory can fix `x` number of robots,
                //we have to maintain the limit of factories that has been fixed so far, that will make managing them might be difficult.
                //However, if we say the same thing differently ?? Like, instead of a factory at 'i` position has limit=2 we say
                // At position `i` we have 2 factories.
                //Then it will become easy as we need to assign a single roboto to a single factory only.
                //Flatten the factory array
                List<Integer> factoryList = new ArrayList<>();
                for (int[] f : factory) {
                    for (int i = 0; i < f[1]; i++) {
                        factoryList.add(f[0]); // there are F[1] factories at position F[0]
                    }
                }


                return minimumTotalDistance(robot, factoryList, 0, 0);

            }

            private long minimumTotalDistance(List<Integer> robot, List<Integer> factory, int rbtIndex, int factIndex) {

                //base case

                //if we finished all the robots, then no more distance to travel for robots.
                if (rbtIndex == robot.size())
                    return 0;

                //if we have not finished all the robots and there is no factory left, then we can't fix the robot
                if (factIndex == factory.size())
                    return (long) 1e12;


                //we have two choices to make
                // 1. we assign robot to the current factory, then we left with remaining robots and remaining factories
                long assign = Math.abs(robot.get(rbtIndex) - factory.get(factIndex)) // the distance it traveled to get assign
                        + minimumTotalDistance(robot, factory, rbtIndex + 1, factIndex + 1);

                // 2. we don't assign robot to the current factory, then we left with remaining robots and remaining factories - 1
                long notAssign = minimumTotalDistance(robot, factory, rbtIndex, factIndex + 1);

                return Math.min(assign, notAssign);
            }
        }


        //bottom up higher to lower
        static class SolutionRecursion2 {
            public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
                if (factory.length == 0 || robot.isEmpty()) return 0;


                //Since we need to assign robots and robots which has the nearest factory will move in the same the direction,
                //it will be easy to maintain then in one the directions only instead of moving left or right based on distance.
                //Hence, sorting robots will simplify the robot assignment in one direction.
                Collections.sort(robot);

                //Similarly, since we are assigning robot in one increasing direction, making factories same also simplifies
                //sort factories by their position
                Arrays.sort(factory, Comparator.comparingInt(a -> a[0]));


                // now, we have to assign robots to the nearest factory and additionally since a factory can fix `x` number of robots,
                //we have to maintain the limit of factories that has been fixed so far, that will make managing them might be difficult.
                //However, if we say the same thing differently ?? Like, instead of a factory at 'i` position has limit=2 we say
                // At position `i` we have 2 factories.
                //Then it will become easy as we need to assign a single roboto to a single factory only.
                //flatten the factory array
                List<Integer> factoryList = new ArrayList<>();
                for (int[] f : factory) {
                    for (int i = 0; i < f[1]; i++) {
                        factoryList.add(f[0]); // there are F[1] factories at position F[0]
                    }
                }


                return minimumTotalDistance(robot, factoryList, robot.size() - 1, factoryList.size() - 1);

            }

            private long minimumTotalDistance(List<Integer> robot, List<Integer> factory, int rbtIndex, int factIndex) {

                //base case

                //if we finished all the robots, then no more distance to travel for robots.
                if (rbtIndex <= -1)
                    return 0;

                //if we have not finished all the robots and there is no factory left, then we can't fix the robot
                if (factIndex <= -1)
                    return (long) 1e12;


                //we have two choices to make
                // 1. we assign robot to the current factory, then we left with remaining robots and remaining factories
                long assign = Math.abs(robot.get(rbtIndex) - factory.get(factIndex)) // the distance it traveled to get assign
                        + minimumTotalDistance(robot, factory, rbtIndex - 1, factIndex - 1);

                // 2. we don't assign robot to the current factory, then we left with remaining robots and remaining factories - 1
                long notAssign = minimumTotalDistance(robot, factory, rbtIndex, factIndex - 1);

                return Math.min(assign, notAssign);
            }
        }
    }

    static class DynamicProgramming {

        static class TopDown_Memoization {
            public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
                if (factory.length == 0 || robot.isEmpty()) return 0;


                //Since we need to assign robots and robots which has the nearest factory will move in the same the direction,
                //it will be easy to maintain then in one the directions only instead of moving left or right based on distance.
                //Hence, sorting robots will simplify the robot assignment in one direction.
                Collections.sort(robot);

                //Similarly, since we are assigning robot in one increasing direction, making factories same also simplifies
                //sort factories by their position
                Arrays.sort(factory, Comparator.comparingInt(a -> a[0]));


                //Now, we have to assign robots to the nearest factory and additionally since a factory can fix `x` number of robots,
                //we have to maintain the limit of factories that has been fixed so far, that will make managing them might be difficult.
                //However, if we say the same thing differently ?? Like, instead of a factory at 'i` position has limit=2 we say
                // At position `i` we have 2 factories.
                //Then it will become easy as we need to assign a single roboto to a single factory only.
                //flatten the factory array
                List<Integer> factoryList = new ArrayList<>();
                for (int[] f : factory) {
                    for (int i = 0; i < f[1]; i++) {
                        factoryList.add(f[0]); // there are F[1] factories at position F[0]
                    }
                }

                int n = robot.size();
                int m = factoryList.size();
                long[][] memo = new long[n][m]; // 2d array for memoization

                //since 0 distances are possible value, we have to fill it with -1
                for (int i = 0; i < n; i++)
                    Arrays.fill(memo[i], -1);

                return minimumTotalDistance(robot, factoryList, 0, 0, memo);

            }

            private long minimumTotalDistance(List<Integer> robot, List<Integer> factory, int rbtIndex, int factIndex, long[][] memo) {

                //base case

                //if we finished all the robots, then no more distance to travel for robots.
                if (rbtIndex == robot.size())
                    return 0;

                //if we have not finished all the robots and there is no factory left, then we can't fix the robot
                if (factIndex == factory.size())
                    return (long) 1e12;

                if (memo[rbtIndex][factIndex] != -1)
                    return memo[rbtIndex][factIndex];


                //we have two choices to make
                // 1. we assign robot to the current factory, then we left with remaining robots and remaining factories
                long assign = Math.abs(robot.get(rbtIndex) - factory.get(factIndex)) // the distance it traveled to get assign
                        + minimumTotalDistance(robot, factory, rbtIndex + 1, factIndex + 1, memo);

                // 2. we don't assign robot to the current factory, then we left with remaining robots and remaining factories - 1
                long notAssign = minimumTotalDistance(robot, factory, rbtIndex, factIndex + 1, memo);

                return memo[rbtIndex][factIndex] = Math.min(assign, notAssign);
            }
        }

        static class TopDown_Memoization2 {
            public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
                if (factory.length == 0 || robot.isEmpty()) return 0;


                //Since we need to assign robots and robots which has the nearest factory will move in the same the direction,
                //it will be easy to maintain then in one the directions only instead of moving left or right based on distance.
                //Hence, sorting robots will simplify the robot assignment in one direction.
                Collections.sort(robot);

                //Similarly, since we are assigning robot in one increasing direction, making factories same also simplifies
                //sort factories by their position
                Arrays.sort(factory, Comparator.comparingInt(a -> a[0]));


                //Now, we have to assign robots to the nearest factory and additionally since a factory can fix `x` number of robots,
                //we have to maintain the limit of factories that has been fixed so far, that will make managing them might be difficult.
                //However, if we say the same thing differently ?? Like, instead of a factory at 'i` position has limit=2 we say
                // At position `i` we have 2 factories.
                //Then it will become easy as we need to assign a single roboto to a single factory only.
                //flatten the factory array
                List<Integer> factoryList = new ArrayList<>();
                for (int[] f : factory) {
                    for (int i = 0; i < f[1]; i++) {
                        factoryList.add(f[0]); // there are F[1] factories at position F[0]
                    }
                }

                int n = robot.size();
                int m = factoryList.size();
                long[][] memo = new long[n][m]; // 2d array for memoization

                //since 0 distances are possible value, we have to fill it with -1
                for (int i = 0; i < n; i++)
                    Arrays.fill(memo[i], -1);

                return minimumTotalDistance(robot, factoryList, robot.size() - 1, factoryList.size() - 1, memo);

            }

            private long minimumTotalDistance(List<Integer> robot, List<Integer> factory, int rbtIndex, int factIndex, long[][] memo) {

                //base case

                //if we finished all the robots, then no more distance to travel for robots.
                if (rbtIndex <= -1)
                    return 0;

                //if we have not finished all the robots and there is no factory left, then we can't fix the robot
                if (factIndex <= -1)
                    return (long) 1e12;

                if (memo[rbtIndex][factIndex] != -1)
                    return memo[rbtIndex][factIndex];


                //we have two choices to make
                // 1. we assign robot to the current factory, then we left with remaining robots and remaining factories
                long assign = Math.abs(robot.get(rbtIndex) - factory.get(factIndex)) // the distance it traveled to get assign
                        + minimumTotalDistance(robot, factory, rbtIndex - 1, factIndex - 1, memo);

                // 2. we don't assign robot to the current factory, then we left with remaining robots and remaining factories - 1
                long notAssign = minimumTotalDistance(robot, factory, rbtIndex, factIndex - 1, memo);

                return memo[rbtIndex][factIndex] = Math.min(assign, notAssign);
            }
        }

        static class BottomUp_Tabulations {
            public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
                if (factory.length == 0 || robot.isEmpty()) return 0;


                //Since we need to assign robots and robots which has the nearest factory will move in the same the direction,
                //it will be easy to maintain then in one the directions only instead of moving left or right based on distance.
                //Hence, sorting robots will simplify the robot assignment in one direction.
                Collections.sort(robot);

                //Similarly, since we are assigning robot in one increasing direction, making factories same also simplifies
                //sort factories by their position
                Arrays.sort(factory, Comparator.comparingInt(a -> a[0]));


                //Now, we have to assign robots to the nearest factory and additionally since a factory can fix `x` number of robots,
                //we have to maintain the limit of factories that has been fixed so far, that will make managing them might be difficult.
                //However, if we say the same thing differently ?? Like, instead of a factory at 'i` position has limit=2 we say
                // At position `i` we have 2 factories.
                //Then it will become easy as we need to assign a single roboto to a single factory only.
                //flatten the factory array
                List<Integer> factoryList = new ArrayList<>();
                for (int[] f : factory) {
                    for (int i = 0; i < f[1]; i++) {
                        factoryList.add(f[0]); // there are F[1] factories at position F[0]
                    }
                }

                int n = robot.size();
                int m = factoryList.size();


                //we have seen in a memoization solution, we had two choices for all the combination of robots and factories.
                //either we assign current robot to current factory or we don't assign current robot to current factory,
                //hence we can solve this problem in a bottom-up manner

                //distance[i][j] represent the total distance traveled by the robot stating from `i` to fix at factory starting from `j`.
                //distance[i][j] = Math.min ( <assign>, <don't_assign )
                //now, if we want to assign robot at `i` position to `j` factory, then we have to travel distance  = abs(robot[i] - factory[j]) and we have to include the solution that is computed with `i+1` and `j+1`
                // and if we don't assign robot at `i` position to `j` factory, then we have to continue with solution previously computed with `i` and `j+1`

                // distance[i][j] = Math.min ( abs(robot[i] - factory[j]) + distance[i+1][j+1], distance[i][j+1] )

                // this can also be done from lower to higher as
                // distance[i][j] = Math.min ( abs(robot[i] - factory[j]) + distance[i-1][j-1], distance[i][j-1] )

                long[][] distance = new long[n + 1][m + 1]; // 2d array for memoization

                //since 0 distances are possible value, we have to fill it with -1
                for (int i = 0; i < n; i++)
                    distance[i][m] = (long) 1e12; // m is total factories in the array, hence distance[i][m] = 1e12

                return minimumTotalDistance(robot, factoryList, distance);

            }

            private long minimumTotalDistance(List<Integer> robot, List<Integer> factory, long[][] distance) {
                int n = robot.size();
                int m = factory.size();

                // we need to start from last robot and last factory
                for (int i = n - 1; i >= 0; i--) {
                    for (int j = m - 1; j >= 0; j--) {

                        long assign = Math.abs(robot.get(i) - factory.get(j)) + distance[i + 1][j + 1];
                        long notAssign = distance[i][j + 1];

                        distance[i][j] = Math.min(assign, notAssign);
                    }

                }

                return distance[0][0];
            }
        }


        static class BottomUp_Tabulations2 {
            public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
                if (factory.length == 0 || robot.isEmpty()) return 0;


                //Since we need to assign robots and robots which has the nearest factory will move in the same the direction,
                //it will be easy to maintain then in one the directions only instead of moving left or right based on distance.
                //Hence, sorting robots will simplify the robot assignment in one direction.
                Collections.sort(robot);

                //Similarly, since we are assigning robot in one increasing direction, making factories same also simplifies
                //sort factories by their position
                Arrays.sort(factory, Comparator.comparingInt(a -> a[0]));


                //Now, we have to assign robots to the nearest factory and additionally since a factory can fix `x` number of robots,
                //we have to maintain the limit of factories that has been fixed so far, that will make managing them might be difficult.
                //However, if we say the same thing differently ?? Like, instead of a factory at 'i` position has limit=2 we say
                // At position `i` we have 2 factories.
                //Then it will become easy as we need to assign a single roboto to a single factory only.
                //flatten the factory array
                List<Integer> factoryList = new ArrayList<>();
                for (int[] f : factory) {
                    for (int i = 0; i < f[1]; i++) {
                        factoryList.add(f[0]); // there are F[1] factories at position F[0]
                    }
                }

                int n = robot.size();
                int m = factoryList.size();


                //we have seen in a memoization solution, we had two choices for all the combination of robots and factories.
                //either we assign current robot to current factory or we don't assign current robot to current factory,
                //hence we can solve this problem in a bottom-up manner

                //distance[i][j] represent the total distance traveled by the robot stating from `i` to fix at factory starting from `j`.
                //distance[i][j] = Math.min ( <assign>, <don't_assign )
                //now, if we want to assign robot at `i` position to `j` factory, then we have to travel distance  = abs(robot[i] - factory[j]) and we have to include the solution that is computed with `i+1` and `j+1`
                // and if we don't assign robot at `i` position to `j` factory, then we have to continue with solution previously computed with `i` and `j+1`

                // distance[i][j] = Math.min ( abs(robot[i] - factory[j]) + distance[i+1][j+1], distance[i][j+1] )

                // this can also be done from lower to higher as
                // distance[i][j] = Math.min ( abs(robot[i] - factory[j]) + distance[i-1][j-1], distance[i][j-1] )

                long[][] distance = new long[n + 1][m + 1]; // 2d array for memoization

                // No possibilities initially, hence infinity
                for (long[] row : distance) {
                    Arrays.fill(row, (long) 1e12);
                }

                for (int j = 0; j < m; j++)
                    distance[0][j] = 0; // no robot and any factory yields zero distances


                return minimumTotalDistance(robot, factoryList, n, m, distance);

            }

            private long minimumTotalDistance(List<Integer> robot, List<Integer> factory, int n, int m, long[][] distance) {
                // we need to start from last robot and last factory
                for (int r = 1; r <= n; r++) {
                    for (int f = 1; f <= m; f++) {

                        long assign = Math.abs(robot.get(r - 1) - factory.get(f - 1)) + distance[r - 1][f - 1];
                        long notAssign = distance[r][f - 1];

                        distance[r][f] = Math.min(assign, notAssign);
                    }

                }

                return distance[n][m];
            }
        }
    }
}
