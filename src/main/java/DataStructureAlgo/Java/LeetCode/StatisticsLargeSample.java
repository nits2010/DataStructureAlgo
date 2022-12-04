package DataStructureAlgo.Java.LeetCode;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-11
 * Description:https://leetcode.com/problems/statistics-from-a-large-sample/discuss/332618/Full-explanation%3A-Beat-100-and-100
 * We sampled integers between 0 and 255, and stored the results in an array count:  count[k] is the number of integers we sampled equal to k.
 *
 * Return the minimum, maximum, mean, median, and mode of the sample respectively, as an array of floating point numbers.  The mode is guaranteed to be unique.
 *
 * (Recall that the median of a sample is:
 *
 * The middle element, if the elements of the sample were sorted and the number of elements is odd;
 * The average of the middle two elements, if the elements of the sample were sorted and the number of elements is even.)
 *
 *
 * Example 1:
 *
 * Input: count = [0,1,3,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
 * Output: [1.00000,3.00000,2.37500,2.50000,3.00000]
 */
public class StatisticsLargeSample {

    public static void main(String []args) {
        SolutionStatisticsLargeSample sol = new SolutionStatisticsLargeSample();
        int a[] = {0, 1, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        int b[] = {264, 912, 1416, 1903, 2515, 3080, 3598, 4099, 4757, 5270, 5748, 6451, 7074, 7367, 7847, 8653, 9318, 9601, 10481, 10787, 11563, 11869, 12278, 12939, 13737, 13909, 14621, 15264, 15833, 16562, 17135, 17491, 17982, 18731, 19127, 19579, 20524, 20941, 21347, 21800, 22342, 21567, 21063, 20683, 20204, 19818, 19351, 18971, 18496, 17974, 17677, 17034, 16701, 16223, 15671, 15167, 14718, 14552, 14061, 13448, 13199, 12539, 12265, 11912, 10931, 10947, 10516, 10177, 9582, 9102, 8699, 8091, 7864, 7330, 6915, 6492, 6013, 5513, 5140, 4701, 4111, 3725, 3321, 2947, 2357, 1988, 1535, 1124, 664, 206, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int c[] = {2725123, 2529890, 2612115, 3807943, 3002363, 3107290, 2767526, 981092, 896521, 2576757, 2808163, 3315813, 2004022, 2516900, 607052, 1203189, 2907162, 1849193, 1486120, 743035, 3621726, 3366475, 639843, 3836904, 462733, 2614577, 1881392, 85099, 709390, 3534613, 360309, 404975, 715871, 2258745, 1682843, 3725079, 564127, 1893839, 2793387, 2236577, 522108, 1183512, 859756, 3431566, 907265, 1272267, 2261055, 2234764, 1901434, 3023329, 863353, 2140290, 2221702, 623198, 955635, 304443, 282157, 3133971, 1985993, 1113476, 2092502, 2896781, 1245030, 2681380, 2286852, 3423914, 3549428, 2720176, 2832468, 3608887, 174642, 1437770, 1545228, 650920, 2357584, 3037465, 3674038, 2450617, 578392, 622803, 3206006, 3685232, 2687252, 1001246, 3865843, 2755767, 184888, 2543886, 2567950, 1755006, 249516, 3241670, 1422728, 809805, 955992, 415481, 26094, 2757283, 995334, 3713918, 2772540, 2719728, 1204666, 1590541, 2962447, 779517, 1322374, 1675147, 3146304, 2412486, 902468, 259007, 3161334, 1735554, 2623893, 1863961, 520352, 167827, 3654335, 3492218, 1449347, 1460253, 983079, 1135, 208617, 969433, 2669769, 284741, 1002734, 3694338, 2567646, 3042965, 3186843, 906766, 2755956, 2075889, 1241484, 3790012, 2037406, 2776032, 1123633, 2537866, 3028339, 3375304, 1621954, 2299012, 1518828, 1380554, 2083623, 3521053, 1291275, 180303, 1344232, 2122185, 2519290, 832389, 1711223, 2828198, 2747583, 789884, 2116590, 2294299, 1038729, 1996529, 600580, 184130, 3044375, 261274, 3041086, 3473202, 2318793, 2967147, 2506188, 127448, 290011, 3868450, 1659949, 3662189, 1720152, 25266, 1126602, 1015878, 2635566, 619797, 2898869, 3470795, 2226675, 2348104, 2914940, 1907109, 604482, 2574752, 1841777, 880254, 616721, 3786049, 2278898, 3797514, 1328854, 1881493, 1802018, 3034791, 3615171, 400080, 2277949, 221689, 1021253, 544372, 3101480, 1155691, 3730276, 1827138, 3621214, 2348383, 2305429, 313820, 36481, 2581470, 2794393, 902504, 2589859, 740480, 2387513, 2716342, 1914543, 3219912, 1865333, 2388350, 3525289, 3758988, 961406, 1539328, 448809, 1326527, 1339048, 2924378, 2715811, 376047, 3642811, 2973602, 389167, 1026011, 3633833, 2848596, 3353421, 1426817, 219995, 1503946, 2311246, 2618861, 1497325, 3758762, 2115273, 3238053, 2419849, 2545790};
        double[] d = sol.sampleStats(c);
        for (Double dd : d)
            System.out.print(" " + dd);
    }
}

/**
 * About question:
 * It hit me hard to understand the input it self. May be you also
 * <p>
 * Input says, at index 'i' what is the occrrence of a value i in samples denoted as count[i].
 * <p>
 * Now, for minimum we need to find the minumum value where it has at least 1 occurrence (start from left)
 * similarly for maximum (start from end)
 * <p>
 * Explanation for median:
 * As we understand, the element value in count[i] represent that how many times i occurred in our data.
 * What we can do is, visulize that those numbers are presented in a big-big array and i is repeated cout[i] times. You can easily find out that, these numbers are already in SORTED FORM.
 * now, since we know that they are sorted form, then we need to reach the index which is nothing but middle of 'num' of count[i] where count[i] !=0 and minimum<=i<=maximum;
 * <p>
 * This means, we just find number of element just before or at the num/2. And take average accordingly
 * <p>
 * Important thing is to note for even 'num', when its even then we need to find two index and it may possible that there is 0 in between those index. We need to discard them
 */
class SolutionStatisticsLargeSample {
    public double[] sampleStats(int[] count) {

        int minimum = -1, maximum = -1, mode;
        double mean, median;

        int start = 0;
        int end = count.length - 1;

        //Find minimum and maximum value index
        while (start < count.length - 1 && end >= 0) {

            if (count[start] != 0 && minimum == -1) {
                minimum = start;
            }

            if (count[end] != 0 && maximum == -1) {
                maximum = end;
            }

            if (minimum != -1 && maximum != -1)
                break;
            start++;
            end--;
        }

        if (minimum == -1) {
            double d[] = {0, 0, 0, 0, 0};
            return d;
        }
        //find mean and mode;
        long num = 0;
        long sum = 0;

        int i = minimum;
        mode = i;
        while (i <= maximum) {

            if (count[i] != 0) {
                sum += i * count[i];
                num += count[i];

                if (count[mode] < count[i]) {
                    mode = i;
                }
            }
            i++;
        }

        mean = (double) sum / num;

        //Find median
        int element1 = -1, element2 = -1;
        i = minimum;
        sum = 0;
        while (i <= maximum) {

            sum += count[i];
            if (sum >= num / 2 && element1 == -1) {
                element1 = i;

            }
            if (sum >= (num / 2 + 1) && element2 == -1) {
                element2 = i;
                break;
            }
            i++;
        }

        median = (num % 2 == 0) ? (double) (element1 + element2) / 2 : element1;

        double result[] = {minimum, maximum, mean, median, mode};
        return result;


    }

}