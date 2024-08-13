package DataStructureAlgo.Java.LeetCode;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-24
 * Description: https://leetcode.com/problems/distribute-candies/
 * Given an integer array with even length, where different numbers in this array represent different kinds of candies. Each number means one candy of the corresponding kind. You need to distribute these candies equally in number to brother and sister. Return the maximum number of kinds of candies the sister could gain.
 * Example 1:
 * Input: candies = [1,1,2,2,3,3]
 * Output: 3
 * Explanation:
 * There are three different kinds of candies (1, 2 and 3), and two candies for each kind.
 * Optimal distribution: The sister has candies [1,2,3] and the brother has candies [1,2,3], too.
 * The sister has three different kinds of candies.
 * Example 2:
 * Input: candies = [1,1,2,3]
 * Output: 2
 * Explanation: For example, the sister has candies [2,3] and the brother has candies [1,1].
 * The sister has two different kinds of candies, the brother has only one kind of candies.
 * Note:
 * <p>
 * The length of the given array is in range [2, 10,000], and will be even.
 * The number in given array is in range [-100,000, 100,000].
 */
public class DistributeCandies {
    public static void main(String[] args) {
        test(new int[]{1, 1, 2, 3});
        test(new int[]{1, 1, 2, 2, 3, 3});
        test(new int[]{1, 1, 2, 2, 3, 3, 3, 3});
        test(new int[]{1, 1, 2, 2, 2, 3, 3, 3});
    }

    private static void test(int[] candies) {
        System.out.println("Input : " + CommonMethods.toString(candies));
        DistributeCandiesUsingHash hash = new DistributeCandiesUsingHash();
        DistributeCandiesUsingBit bitSet = new DistributeCandiesUsingBit();
        DistributeCandiesUsingBoolean booleanArray = new DistributeCandiesUsingBoolean();
        System.out.println("Hash : " + hash.distributeCandiesFaster(candies));
        System.out.println("bitSet : " + bitSet.distributeCandies(candies));
        System.out.println("booleanArray : " + booleanArray.distributeCandies(candies));

    }
}

/**
 * Important observation is that, if there are n types of candies then sister is more likely to have n types of candies
 * 1. if; distribution of candies > types of candies -> sister will have all kind of candies for sure
 * 2. else distribution of candies < types of candies -> sister will can not have all types of candies as her share is smaller then types
 * <p>
 * O(n) / O (n)
 */
class DistributeCandiesUsingHash {
    public int distributeCandies(int[] candies) {

        if (candies == null || candies.length == 0)
            return 0;

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < candies.length; i++) {
            int c = candies[i];
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int types = map.size();
        int total = candies.length;

        int equal = total / 2;

        if (equal < types)
            return equal;
        else
            return types;


    }


    /**
     * You don't need to count till end of array. Since if 'distribution of candies > types of candies' then for sure she'll get all
     * kind of candies; Since distribution of candies = length / 2
     * <p>
     * <p>
     * Runtime: 37 ms, faster than 83.75% of Java online submissions for Distribute Candies.
     * Memory Usage: 40.4 MB, less than 100.00% of Java online submissions for Distribute Candies.
     *
     * @param candies
     * @return
     */
    public int distributeCandiesFaster(int[] candies) {

        if (candies == null || candies.length == 0)
            return 0;

        Set<Integer> set = new HashSet<>();
        int types = 0;

        for (int i = 0; i < candies.length && types < candies.length / 2; i++) {
            int c = candies[i];
            if (!set.contains(c))
                types++;
            set.add(c);
        }

        return types;


    }
}


/**
 * Important observation is that, if there are n types of candies then sister is more likely to have n types of candies
 * 1. if; distribution of candies > types of candies -> sister will have all kind of candies for sure
 * 2. else distribution of candies < types of candies -> sister will can not have all types of candies as her share is smaller then types
 * <p>
 * Since types of candies are fixed  range [-100,000, 100,000].
 * Then we can use bitSet to count the types
 * <p>
 * Runtime: 11 ms, faster than 96.95% of Java online submissions for Distribute Candies.
 * Memory Usage: 45.1 MB, less than 47.37% of Java online submissions for Distribute Candies.
 */

class DistributeCandiesUsingBit {


    public int distributeCandies(int[] candies) {
        if (candies == null || candies.length == 0)
            return 0;

        int types = 0;
        final int offset = 100000;
        BitSet bitSet = new BitSet(100000 * 2 + 1);

        for (int i = 0; i < candies.length && types < candies.length >> 1; i++) {

            int c = candies[i] + offset;

            if (!bitSet.get(c))
                types++;
            bitSet.set(c);


        }

        return types;


    }
}

/**
 * Instead of bit set, we can use boolean array
 * <p>
 * Runtime: 6 ms, faster than 99.76% of Java online submissions for Distribute Candies.
 * Memory Usage: 40.4 MB, less than 100.00% of Java online submissions for Distribute Candies.
 */
class DistributeCandiesUsingBoolean {


    public int distributeCandies(int[] candies) {
        if (candies == null || candies.length == 0)
            return 0;

        final int offset = 100000;
        boolean[] set = new boolean[2 * offset + 1];
        int types = 0;
        for (int i = 0; i < candies.length && types < candies.length >> 1; i++) {

            if (!set[candies[i] + offset]) {
                types++;
                set[candies[i] + offset] = true;
            }
        }
        return types;


    }


}