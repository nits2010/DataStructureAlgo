package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays._1033;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.Arrays;
import java.util.Map;

/**
 * Author: Nitin Gupta
 * Date: 9/2/2024
 * Question Category: 1093. Statistics from a Large Sample
 * Description: https://leetcode.com/problems/statistics-from-a-large-sample/description/
 * You are given a large sample of integers in the range [0, 255]. Since the sample is so large, it is represented by an array count where count[k] is the number of times that k appears in the sample.
 * <p>
 * Calculate the following statistics:
 * <p>
 * minimum: The minimum element in the sample.
 * maximum: The maximum element in the sample.
 * mean: The average of the sample, calculated as the total sum of all elements divided by the total number of elements.
 * median:
 * If the sample has an odd number of elements, then the median is the middle element once the sample is sorted.
 * If the sample has an even number of elements, then the median is the average of the two middle elements once the sample is sorted.
 * mode: The number that appears the most in the sample. It is guaranteed to be unique.
 * Return the statistics of the sample as an array of floating-point numbers [minimum, maximum, mean, median, mode]. Answers within 10-5 of the actual answer will be accepted.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: count = [0,1,3,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
 * Output: [1.00000,3.00000,2.37500,2.50000,3.00000]
 * Explanation: The sample represented by count is [1,2,2,2,3,3,3,3].
 * The minimum and maximum are 1 and 3 respectively.
 * The mean is (1+2+2+2+3+3+3+3) / 8 = 19 / 8 = 2.375.
 * Since the size of the sample is even, the median is the average of the two middle elements 2 and 3, which is 2.5.
 * The mode is 3 as it appears the most in the sample.
 * Example 2:
 * <p>
 * Input: count = [0,4,3,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
 * Output: [1.00000,4.00000,2.18182,2.00000,1.00000]
 * Explanation: The sample represented by count is [1,1,1,1,2,2,2,3,3,4,4].
 * The minimum and maximum are 1 and 4 respectively.
 * The mean is (1+1+1+1+2+2+2+3+3+4+4) / 11 = 24 / 11 = 2.18181818... (for display purposes, the output shows the rounded number 2.18182).
 * Since the size of the sample is odd, the median is the middle element 2.
 * The mode is 1 as it appears the most in the sample.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * count.length == 256
 * 0 <= count[i] <= 109
 * 1 <= sum(count) <= 109
 * The mode of the sample that count represents is unique.
 * <p>
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.StatisticsLargeSample}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @Math
 * @ProbabilityandStatistics <p>
 * <p>
 * Company Tags
 * -----
 * @Microsoft <p><p>
 * @Editorial https://leetcode.ca/2018-11-27-1093-Statistics-from-a-Large-Sample/
 * https://leetcode.com/problems/statistics-from-a-large-sample/solutions/332618/full-explanation-beat-100-and-100
 */
public class StatisticsFromALargeSample_1033 {
    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[]{0, 1, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                new double[]{1.00000, 3.00000, 2.37500, 2.50000, 3.00000});

        test &= test(new int[]{0, 4, 3, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                new double[]{1.00000, 4.00000, 2.18182, 2.00000, 1.00000});

        test &= test(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                new double[]{191.00000, 204.00000, 200.40000, 204.00000, 204.00000});

        test &= test(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3510, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6718, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 27870, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 35, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 26256, 0, 0, 0, 0, 9586565, 0, 0, 0, 0, 0, 0, 0, 2333, 0, 0, 0,},
                new double[]{32.00000, 251.00000, 242.40435, 243.00000, 243.00000});

        test &= test(new int[]{264, 912, 1416, 1903, 2515, 3080, 3598, 4099, 4757, 5270, 5748, 6451, 7074, 7367, 7847, 8653, 9318, 9601, 10481, 10787, 11563, 11869, 12278, 12939, 13737, 13909, 14621, 15264, 15833, 16562, 17135, 17491, 17982, 18731, 19127, 19579, 20524, 20941, 21347, 21800, 22342, 21567, 21063, 20683, 20204, 19818, 19351, 18971, 18496, 17974, 17677, 17034, 16701, 16223, 15671, 15167, 14718, 14552, 14061, 13448, 13199, 12539, 12265, 11912, 10931, 10947, 10516, 10177, 9582, 9102, 8699, 8091, 7864, 7330, 6915, 6492, 6013, 5513, 5140, 4701, 4111, 3725, 3321, 2947, 2357, 1988, 1535, 1124, 664, 206, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                new double[]{0.00000, 89.00000, 42.82683, 42.00000, 40.00000});

        test &= test(new int[]{2725123, 2529890, 2612115, 3807943, 3002363, 3107290, 2767526, 981092, 896521, 2576757, 2808163, 3315813, 2004022, 2516900, 607052, 1203189, 2907162, 1849193, 1486120, 743035, 3621726, 3366475, 639843, 3836904, 462733, 2614577, 1881392, 85099, 709390, 3534613, 360309, 404975, 715871, 2258745, 1682843, 3725079, 564127, 1893839, 2793387, 2236577, 522108, 1183512, 859756, 3431566, 907265, 1272267, 2261055, 2234764, 1901434, 3023329, 863353, 2140290, 2221702, 623198, 955635, 304443, 282157, 3133971, 1985993, 1113476, 2092502, 2896781, 1245030, 2681380, 2286852, 3423914, 3549428, 2720176, 2832468, 3608887, 174642, 1437770, 1545228, 650920, 2357584, 3037465, 3674038, 2450617, 578392, 622803, 3206006, 3685232, 2687252, 1001246, 3865843, 2755767, 184888, 2543886, 2567950, 1755006, 249516, 3241670, 1422728, 809805, 955992, 415481, 26094, 2757283, 995334, 3713918, 2772540, 2719728, 1204666, 1590541, 2962447, 779517, 1322374, 1675147, 3146304, 2412486, 902468, 259007, 3161334, 1735554, 2623893, 1863961, 520352, 167827, 3654335, 3492218, 1449347, 1460253, 983079, 1135, 208617, 969433, 2669769, 284741, 1002734, 3694338, 2567646, 3042965, 3186843, 906766, 2755956, 2075889, 1241484, 3790012, 2037406, 2776032, 1123633, 2537866, 3028339, 3375304, 1621954, 2299012, 1518828, 1380554, 2083623, 3521053, 1291275, 180303, 1344232, 2122185, 2519290, 832389, 1711223, 2828198, 2747583, 789884, 2116590, 2294299, 1038729, 1996529, 600580, 184130, 3044375, 261274, 3041086, 3473202, 2318793, 2967147, 2506188, 127448, 290011, 3868450, 1659949, 3662189, 1720152, 25266, 1126602, 1015878, 2635566, 619797, 2898869, 3470795, 2226675, 2348104, 2914940, 1907109, 604482, 2574752, 1841777, 880254, 616721, 3786049, 2278898, 3797514, 1328854, 1881493, 1802018, 3034791, 3615171, 400080, 2277949, 221689, 1021253, 544372, 3101480, 1155691, 3730276, 1827138, 3621214, 2348383, 2305429, 313820, 36481, 2581470, 2794393, 902504, 2589859, 740480, 2387513, 2716342, 1914543, 3219912, 1865333, 2388350, 3525289, 3758988, 961406, 1539328, 448809, 1326527, 1339048, 2924378, 2715811, 376047, 3642811, 2973602, 389167, 1026011, 3633833, 2848596, 3353421, 1426817, 219995, 1503946, 2311246, 2618861, 1497325, 3758762, 2115273, 3238053, 2419849, 2545790}
                , new double[]{0.00000, 255.00000, 128.64187, 131.00000, 175.00000});


        CommonMethods.printResult(test);
    }

    private static boolean test(int[] count, double[] expected) {
        System.out.println("----------------------------------");
        System.out.println("Count : " + Arrays.toString(count));
        System.out.println("Expected :" + Arrays.toString(expected));

        Solution solution = new Solution();
        double[] output = solution.sampleStats(count);
        boolean match = Arrays.equals(output, expected);
        System.out.println("Output : " + Arrays.toString(output) + CommonMethods.getResultStringSubTest(match, " match : "));
        return match;
    }

    static class Solution {


        public double[] sampleStats(int[] count) {

            if (count == null || count.length < 255)
                return new double[]{};

            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            int mode = 0;
            double mean, median;
            int totalCount = 0;
            long sum = 0;

            for (int i = 0; i < count.length; i++) {

                //get min value
                if (count[i] > 0) {
                    min = Math.min(min,i);
                    max = Math.max(max, i);

                    //get sum
                    sum += (long) i * count[i];

                    //get count
                    totalCount += count[i];

                    //get mode
                    if (count[i] > count[mode]) {
                        mode = i;
                    }
                }

            }
            //find median
            median = (totalCount % 2 == 1)
                    ? findMedian(totalCount / 2 + 1, count)
                    : (findMedian(totalCount / 2, count) + findMedian(totalCount / 2 + 1, count)) / 2.0;
            mean = (double) sum / totalCount;
            return new double[]{min, max, mean, median, mode};

        }

        int findMedian(int index, int[] count) {
            int totalCount = 0;
            for (int i = 0; ; i++) {
                totalCount += count[i];

                //the total count reached the position; it could be possible that the median is multiple time repeated, hence the total count may get greater then index
                if (totalCount >= index)
                    return i;

            }
        }
    }
}
