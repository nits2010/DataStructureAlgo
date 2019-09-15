package Java.LeetCode.pair.element.problems.threeSum;

import Java.HelpersToPrint.GenericPrinter;
import Java.LeetCode.pair.element.problems.twoSum.TwoSum2Sum;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-15
 * Description: https://leetcode.com/problems/3sum-with-multiplicity/
 * <p>
 * Given an integer array A, and an integer target, return the number of tuples i, j, k  such that i < j < k and A[i] + A[j] + A[k] == target.
 * <p>
 * As the answer can be very large, return it modulo 10^9 + 7.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: A = [1,1,2,2,3,3,4,4,5,5], target = 8
 * Output: 20
 * Explanation:
 * Enumerating by the values (A[i], A[j], A[k]):
 * (1, 2, 5) occurs 8 times;
 * (1, 3, 4) occurs 8 times;
 * (2, 2, 4) occurs 2 times;
 * (2, 3, 3) occurs 2 times.
 * Example 2:
 * <p>
 * Input: A = [1,1,2,2,2,2], target = 5
 * Output: 12
 * Explanation:
 * A[i] = 1, A[j] = A[k] = 2 occurs 12 times:
 * We choose one 1 from [1,1] in 2 ways,
 * and two 2s from [2,2,2,2] in 6 ways.
 * <p>
 * <p>
 * Note:
 * <p>
 * 3 <= A.length <= 3000
 * 0 <= A[i] <= 100
 * 0 <= target <= 300
 * <p>
 * Extension of {@link ThreeSum3Sum}
 */
public class ThreeSum3SumMultiplicity {
    public static void main(String[] args) {

        test(new int[]{1, 1, 2, 2, 3, 3, 4, 4, 5, 5}, 8, 20);
        test(new int[]{1, 1, 2, 2, 3, 3, 4, 4, 4, 4, 5, 5, 5}, 8, 34);

    }

    private static void test(int[] nums, int target, int expected) {
        System.out.println("Input :" + GenericPrinter.toString(nums));

        IThreeSum3SumMultiplicity sorting = new ThreeSum3SumMultiplicitySorting();
        IThreeSum3SumMultiplicity counting = new ThreeSum3SumMultiplicityCounting();
        System.out.println("Sorting: " + sorting.threeSumMulti(nums, target) + " expected :" + expected);
        System.out.println("couting: " + counting.threeSumMulti(nums, target) + " expected :" + expected);
    }
}

interface IThreeSum3SumMultiplicity {
    int threeSumMulti(int[] A, int target);
}

class ThreeSum3SumMultiplicitySorting implements IThreeSum3SumMultiplicity {

    @Override
    public int threeSumMulti(int[] A, int target) {
        return threeSum(A, target);
    }

    /**
     * <p>
     * [1,1,2,2,3,3,4,4,4,4,5,5,5] Target=8 Expected : 34
     * Sorted-> [1,1,2,2,3,3,4,4,4,4,5,5,5]
     * <p>
     * Important observation: As the elements are get sorted, that brings all the duplicate pair together.
     * Essentially those duplicate pair will also contribute in our solution.
     * The one way is to count each of them one by one Or we can simply do some maths.
     * <p>
     * We can count how many of them are duplicate for a 'i'th element.
     * let say from the side of j there are 2 and from k there are 3 then we essentially have 2*3 pairs
     * Example:
     * i=1 [0 index]
     * j = 2 [2 index]
     * k = 5 [12]
     * <p>
     * Hence j has duplicates  till index 4 while k has till 10
     * In total  x-> for j and y->k
     * x = 2 and y = 3
     * then there will be 2*3 pairs with current i, as we can choose all the permutation
     * indices- > [0,2,12] , [0,2,11] , [0,2,10] , [0,3,12] , [0,3,11] [0,3,10] = 6 pairs
     * <p>
     * <p>
     * but there is a special case when both element at j and k are equal
     * [3,4,4,4,4] here i->3 and j->4 [from left] and k-> 4 [from right]
     * Then above equation will fail.
     * So we can simply count how many are them by counting them it self
     * here z = 4 [ 4 count of 4 ]
     * At any moment we can select 2 of as j and k hence there will be 4C2 = 12 pairs
     * <p>
     * https://leetcode.com/articles/3sum-with-multiplicity/
     * <p>
     * Runtime: 48 ms, faster than 52.30% of Java online submissions for 3Sum With Multiplicity.
     * Memory Usage: 37.8 MB, less than 100.00% of Java online submissions for 3Sum With Multiplicity.
     *
     * @param nums
     * @param target
     * @return
     */
    private int threeSum(int[] nums, int target) {


        long solution = 0;

        Arrays.sort(nums);


        for (int i = 0; i < nums.length - 1; i++) {


            int a = nums[i];

            int j = i + 1;
            int k = nums.length - 1;

            while (j < k) {

                int b = nums[j];
                int c = nums[k];

                int sum = a + b + c;

                if (sum > target)
                    k--;
                else if (sum < target)
                    j++;
                else {

                    if (b != c) {

                        int x = 1;
                        int y = 1;

                        while (j < k && b == nums[++j])
                            x++;

                        while (k > j && c == nums[--k])
                            y++;

                        solution += x * y;


                    } else {
                        int count = k - j + 1;

                        solution += (count) * (count - 1) / 2;
                        break; //Since we have counted all of them

                    }

                }
            }
        }
        return (int) (solution % (1e9 + 7));

    }
}

/**
 * In above solution, once we found that sum = target, we need to calculate all the duplicate elements for j and k.
 * That introduce an extra loop [ though that loop minimize the j and k while loop run time. ]
 * <p>
 * But what if we already know how many of them are there after each i'th element.
 * sum = nums[i] + nums[j] + nums[k]
 * <p>
 * If we know how many nums[j] + nums[k] are present then we can directly add those contribution in our solution.
 * <p>
 * This then reduce to {@link TwoSum2Sum} because we need to find all nums[i] + {nums[j] + nums[k]} where {x} denotes the number of times x occurred
 * <p>
 * Algorithm:
 * compute nums[j] + nums[k] count after each i'th element
 */
class ThreeSum3SumMultiplicityCounting implements IThreeSum3SumMultiplicity {


    @Override
    public int threeSumMulti(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        long solution = 0;

        for (int i = 0; i < nums.length; i++) {

            solution += map.getOrDefault(target - nums[i], 0);

            for (int j = 0; j < i; j++) {

                int temp = nums[i] + nums[j];
                map.put(temp, map.getOrDefault(temp, 0) + 1);
            }


        }

        return (int) (solution % (1e9 + 7));
    }
}