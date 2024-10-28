package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays.DynamicProgramming.sequences._2501;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Author: Nitin Gupta
 * Date:28/10/24
 * Question Category:
 * Description: 2501. Longest Square Streak in an Array
 * You are given an integer array nums. A subsequence of nums is called a square streak if:
 * <p>
 * The length of the subsequence is at least 2, and
 * after sorting the subsequence, each element (except the first element) is the square of the previous number.
 * Return the length of the longest square streak in nums, or return -1 if there is no square streak.
 * <p>
 * A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [4,3,6,16,8,2]
 * Output: 3
 * Explanation: Choose the subsequence [4,16,2]. After sorting it, it becomes [2,4,16].
 * - 4 = 2 * 2.
 * - 16 = 4 * 4.
 * Therefore, [4,16,2] is a square streak.
 * It can be shown that every subsequence of length 4 is not a square streak.
 * Example 2:
 * <p>
 * Input: nums = [2,3,5,6,7]
 * Output: -1
 * Explanation: There is no square streak in nums so return -1.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= nums.length <= 105
 * 2 <= nums[i] <= 105
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @HashTable
 * @BinarySearch
 * @DynamicProgramming
 * @Sorting <p>
 * Company Tags
 * -----
 * @Editorial
 */

public class LongestSquareStreakInAnArray_2501 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[]{57044, 68879, 916, 16512, 34776, 77929, 95685, 68153, 53877, 68617, 61264, 9172, 95471, 86374, 25292, 29948, 43434, 72108, 18536, 31149, 4871, 98887, 89004, 24718, 78079, 7433, 17954, 87036, 61732, 92976, 75756, 22963, 41305, 86318, 2642, 85551, 41664, 47274, 30273, 13649, 62700, 18784, 86619, 67061, 7742, 61448, 83406, 17828, 16384, 70815, 8431, 57596, 68118, 36095, 93523, 69623, 4603, 17368, 15193, 95191, 10133, 62694, 43974, 79584, 75489, 12104, 29073, 62700, 24320, 12330, 66491, 49740, 73184, 62854, 11648, 18196, 2475, 16624, 95444, 3745, 18076, 34719, 92759, 17190, 42969, 59774, 83083, 88130, 45304, 77588, 20928, 74712, 96631, 22665, 28183, 59984, 3663, 83781, 11902, 48445, 58424, 25315, 12874, 3960, 74187, 66127, 99769, 30927, 64001, 39377, 90658, 32484, 56058, 92466, 38484, 31137, 4672, 28168, 7825, 82423, 63338, 1065, 88473, 64371, 1414, 87734, 30799, 44383, 9626, 23589, 27125, 41538, 67024, 11753, 43086, 83809, 89273, 51545, 34671, 97600, 97124, 56601, 43953, 3426, 87357, 93958, 78617, 40820, 79406, 35356, 22773, 22331, 824, 13335, 87491, 45952, 64051, 4422, 32732, 54810, 58319, 80257, 8457, 38567, 23825, 90986, 53332, 80829, 42630, 84703, 92059, 71706, 82859, 10932, 44526, 75366, 94556, 63508, 97168, 93738, 50132, 97203, 37589, 25247, 91722, 78975, 46174, 18243, 31035, 35123, 68799, 90306, 37986, 54070, 74776, 81358, 68775, 58324, 89562, 2414, 25662, 89651, 32724, 1513, 7956, 99662, 43491, 87221, 78281, 18532, 85654, 27844, 94960, 12323, 69262, 77316, 1529, 96345, 54224, 31667, 11341, 74926, 81639, 22016, 15435, 21043, 75240, 44041, 41995, 63145, 31152, 11839, 44851, 41044, 25249, 85228, 32416, 19363, 59340, 92682, 8985, 34620, 19582, 34092, 44460, 75180, 30065, 87239, 82190, 65554, 21533, 17823, 4942, 74283, 85615, 98013, 77917, 91595, 13003, 47974, 71578, 90978, 85115, 90662, 24566, 94919, 12402, 16684, 367, 10186, 57090, 61947, 22330, 35424, 17835, 43363, 67607, 78103, 97290, 95214, 27559, 31675, 64594, 66189, 51482, 13368, 32055, 19302, 27842, 38091, 57168, 23314, 83867, 54887, 14179, 6793, 49643, 53522, 27599, 3919, 98308, 98405, 22269, 61504, 79522, 40657, 49053, 4709, 67769, 39429, 48828, 88834, 42535, 76393, 48147, 65246, 80079, 76512, 89293, 71234, 26528, 11235, 35342, 30643, 64679, 69718, 5338, 13441, 76133, 63183, 18984, 12510, 33658, 13884, 41050, 87905, 92799, 2178, 98761, 40606, 2992, 1268, 12352, 58325, 12272, 92713, 86555, 60458, 28896, 57882, 53824, 34237, 64917, 37947, 75421, 37784, 17352, 23495, 91134, 13002, 65928, 4803, 50925, 24483, 11272, 48590, 52836, 56399, 95390, 432, 58075, 338, 45148, 17047, 53132, 69305, 47917, 90444, 5875, 17277, 2599, 25016, 6913, 56469, 117, 4518, 1307, 53562, 53695, 50005, 80637, 19761, 99481, 48576, 41048, 7177, 74176, 99343, 97723, 16457, 80681, 12056, 3061, 89095, 82260, 58852, 33805, 20558, 45657, 18304, 269, 92630, 31192, 70905, 34505, 9195, 50200, 22082, 86326, 84806, 10492, 21917, 22761, 51636, 16320, 25925, 734, 45335, 8484, 25408, 92021, 17450, 93908, 85683, 29532, 58415, 37662, 24807, 32884, 30182, 46622, 90526, 23834, 25559, 93765, 91897, 92480, 77494, 10466, 91615, 1590, 68506, 57006, 88513, 89690, 86031, 78750, 10850, 98067, 78566, 64908, 36917, 96062, 62095, 40617, 71781, 77510, 76861, 64928, 73193, 76345, 97029, 89582, 37404, 95317, 80838, 52635, 54712, 65883, 18915, 83082, 70082, 17831, 73698, 8090, 42519, 48205, 12009, 59619, 1221, 1116, 26697, 47161, 39833, 37243, 24516, 68473, 88943, 53000, 34773, 6454, 19789, 92058, 94593, 32766, 82773, 7813, 58033, 13743, 81611, 53608, 77347, 18364, 38883, 14679, 67834, 27212, 45934, 55974, 37008, 65727, 53404, 83997, 72637, 44819, 57724, 54750, 72299, 67644, 28697, 45606, 19158, 39657, 78544, 25012, 97326, 65304, 95602, 8953, 82400, 31410, 7022, 64694, 15879, 49639, 52297, 99627, 90946, 58074, 8175, 39406, 34826, 31611, 95607, 54453, 28082, 53560, 19953, 41960, 99635, 23739, 83406, 89066, 46353, 43071, 52314, 61442, 83833, 67954, 42243, 28914, 97086, 58479, 90326, 3534, 73113, 20060, 69851, 92884, 66750, 4401, 70560, 50120, 96706, 59154, 4340, 92146, 88127, 65807, 71681, 22122, 22497, 66145, 55604, 85965, 38885, 66467, 21991, 78039, 29510, 41360, 3191, 2104, 10791, 73824, 7699, 80362, 27776, 51333, 2840, 56324, 48469, 3979, 49613, 44485, 82002, 40133}, -1);
        test &= test(new int[]{4, 3, 6, 16, 8, 2}, 3);
        test &= test(new int[]{2, 3, 5, 6, 7}, -1);
        test &= test(new int[]{32, 16, 4, 2, 8, 64, 4096}, 3);
        test &= test(new int[]{2, 4, 16, 256, 65536}, 5);
        test &= test(new int[]{2, 4, 16, 256, 66536}, 4);
        test &= test(new int[]{3, 9, 81, 6561}, 4);
        CommonMethods.printResult(test);
    }

    private static boolean test(int[] nums, int expected) {
        CommonMethods.print(new String[]{"Nums", "Expected"}, true, nums, expected);

        int output;
        boolean pass, finalPass = true;

        SolutionUsingMapWithoutSort solutionUsingMapWithoutSort = new SolutionUsingMapWithoutSort();
        output = solutionUsingMapWithoutSort.longestSquareStreak(nums);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.print(new String[]{"UsingMapWithoutSort", "Pass"}, false, output, pass ? "Pass" : "Fail");

        SolutionUsingMapWithoutSortImproved solutionUsingMapWithoutSortImproved = new SolutionUsingMapWithoutSortImproved();
        output = solutionUsingMapWithoutSortImproved.longestSquareStreak(nums);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.print(new String[]{"UsingMapWithoutSortImproved", "Pass"}, false, output, pass ? "Pass" : "Fail");


        SolutionUsingVisitedArray solutionUsingVisitedArray = new SolutionUsingVisitedArray();
        output = solutionUsingVisitedArray.longestSquareStreak(nums);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.print(new String[]{"UsingVisitedArray", "Pass"}, false, output, pass ? "Pass" : "Fail");


        SolutionUsingMapSort solutionUsingMapSort = new SolutionUsingMapSort();
        output = solutionUsingMapSort.longestSquareStreak(nums);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.print(new String[]{"UsingMapSort", "Pass"}, false, output, pass ? "Pass" : "Fail");

        SolutionUsingMapSortBinarySearch solutionUsingMapSortBinarySearch = new SolutionUsingMapSortBinarySearch();
        output = solutionUsingMapSortBinarySearch.longestSquareStreak(nums);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.print(new String[]{"UsingMapSortBinarySearch", "Pass"}, false, output, pass ? "Pass" : "Fail");

        return finalPass;
    }

    /**
     * Time complexity: O(n*10^5) *-  O(n)
     * 1. Cache element in set for quick access.
     * 2. loop through element and check its continous square in set or not. if its there, then increase the streak.
     * 3. If we already processed this number, then just add the streak of that number to current streak
     */
    static class SolutionUsingMapWithoutSort {
        public int longestSquareStreak(int[] nums) {

            int[] dp = new int[nums.length];
            HashMap<Integer, Integer> set = new HashMap<>();

            //O(n)
            for (int i = 0; i < nums.length; i++) {
                set.put(nums[i], i);
            }

            int maxStreak = -1;

            //O(n)
            for (int i = 0; i < nums.length; i++) {

                int currentStreak = 1;
                long current = (long) nums[i] * nums[i]; // 10^5 * 10^5 = 10^10 > Integer.MAX_VALUE


                while (current > 0 && current < 1e5 && set.containsKey((int) current)) {

                    //if we already processed this number, then just add the streak of that number
                    Integer next = set.get((int) current);
                    if (next != null && dp[next] != 0) {
                        currentStreak = currentStreak + dp[next];
                        break;
                    }
                    current *= current;
                    currentStreak++;


                    if (currentStreak == nums.length)
                        return nums.length; //All elements are square of each other
                }

                maxStreak = Math.max(maxStreak, currentStreak);

                dp[i] = currentStreak;
            }

            return maxStreak == 1 ? -1 : maxStreak;
        }
    }


    /**
     * Time complexity: O(n*10^5) *-  O(n)
     * Without using dp array
     */
    static class SolutionUsingMapWithoutSortImproved {
        public int longestSquareStreak(int[] nums) {

            HashMap<Integer, Integer> set = new HashMap<>();

            //O(n)
            for (int num : nums) {
                set.put(num, 0);
            }

            int maxStreak = -1;

            //O(n)
            for (int num : nums) {

                int currentStreak = 1;
                long current = (long) num * num; // 10^5 * 10^5 = 10^10 > Integer.MAX_VALUE


                while (current > 0 && current < 1e5 && set.containsKey((int) current)) {

                    //if we already processed this number, then just add the streak of that number
                    Integer exisitingStreak = set.get((int) current);
                    if (exisitingStreak != null && exisitingStreak != 0) {
                        currentStreak = currentStreak + exisitingStreak;
                        break;
                    }
                    current *= current;
                    currentStreak++;


                    if (currentStreak == nums.length)
                        return nums.length; //All elements are square of each other
                }

                maxStreak = Math.max(maxStreak, currentStreak);

                set.put(num, currentStreak);
            }

            return maxStreak == 1 ? -1 : maxStreak;
        }
    }

    static class SolutionUsingVisitedArray {

        public int longestSquareStreak(int[] nums) {
            int result = -1;
            final int max = 100000;
            boolean[] isExisted = new boolean[max + 1];
            boolean[] isVisited = new boolean[max + 1];

            //cache all the element in isExisted
            for (int num : nums) {
                isExisted[num] = true;
            }

            //scan all the square elements from 2 to max
            for (int i = 2; i * i <= max; i++) {

                //if this element is not present in nums or already visited, then skip
                if (!isExisted[i] || isVisited[i]) {
                    continue;
                }

                //visit
                isVisited[i] = true;

                int currentStreak = 1;
                int next = i * i;

                //scan all the number which are square of each other in nums
                while (next >= 0 && next <= max && isExisted[next]) {
                    isVisited[next] = true;
                    currentStreak++;
                    next = next * next;
                }
                if (currentStreak > 1) {
                    result = Math.max(result, currentStreak);
                }

                //if this is maximum possible streak, then return
                if(currentStreak == nums.length)
                    return nums.length;
            }
            return result;
        }

    }

    /**
     * 1. Sort the array, so that numbers processed in increasing order.
     * 2. Push them in set for quick access.
     * 3. scan array and check if its square exists in set, if so, remove that element (so that we don't process same square again) and increase streak.
     * <p>
     * Removing from set makes its O(log(n)) but since we are removing only once, it's O(1) in average case.
     * Time complexity: O(n*log(n)) + O(n) = O(n*log(n))
     */
    static class SolutionUsingMapSort {
        public int longestSquareStreak(int[] nums) {

            Arrays.sort(nums); //O(n*log(n))
            Set<Long> set = new HashSet<>();


            ////O(n)
            for (int j : nums) {
                set.add((long) j);
            }

            int maxStreak = 1;
            for (int num : nums) {
                //find the streak of this number;
                maxStreak = Math.max(maxStreak, getMaxStreakBS(set, num));

            }

            return maxStreak == 1 ? -1 : maxStreak;
        }

        private int getMaxStreakBS(Set<Long> set, long num) {
            int streak = 1;
            long current = num * num;

            //if there is number is set or its square is in set, then increase the streak
            while (!set.isEmpty() && set.contains(current)) {
                streak++;
                set.remove(current); //remove so that it does not process again.
                current = current * current;
            }
            return streak;
        }

    }

    /**
     * We can further improve the above solution by binary search. Instead of removing element from set, we will push element in set
     * only if square present in streak.
     */

    static class SolutionUsingMapSortBinarySearch {
        public int longestSquareStreak(int[] nums) {

            Arrays.sort(nums); //O(n*log(n))
            Set<Integer> processed = new HashSet<>();
            int maxStreak = 1;

            for (int num : nums) {
                int streak = 1;
                //consider only those which are not processed yet
                if (!processed.contains(num)) {

                    while (true) {
                        int next = num * num;
                        if ((long) num * (long) num > 1e5 || next < 0)  //only 10^5 elements can be processed
                            break;

                        if (binarySearch(nums, next)) {
                            processed.add(num);
                            streak++;
                            num = next;
                        } else {
                            break;
                        }
                    }
                }

                maxStreak = Math.max(maxStreak, streak);

            }

            return maxStreak == 1 ? -1 : maxStreak;
        }

        private boolean binarySearch(int[] nums, int num) {

            int low = 0, high = nums.length - 1;

            while (low <= high) {

                int mid = low + (high - low) / 2;
                if (nums[mid] == num)
                    return true;
                if (nums[mid] > num)
                    high = mid - 1;
                else
                    low = mid + 1;
            }
            return false;
        }

    }


}
