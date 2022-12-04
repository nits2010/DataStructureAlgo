package DataStructureAlgo.Java.LeetCode.insertdeleterandom;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 19/09/19
 * Description: https://leetcode.com/problems/insert-delete-getrandom-o1/
 * 380. Insert Delete GetRandom O(1)
 * Medium
 * Design a data structure that supports all following operations in average O(1) time.
 * <p>
 * insert(val): Inserts an item val to the set if not already present.
 * remove(val): Removes an item val from the set if present.
 * getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
 * Example:
 * <p>
 * // Init an empty set.
 * RandomizedSet randomSet = new RandomizedSet();
 * <p>
 * // Inserts 1 to the set. Returns true as 1 was inserted successfully.
 * randomSet.insert(1);
 * <p>
 * // Returns false as 2 does not exist in the set.
 * randomSet.remove(2);
 * <p>
 * // Inserts 2 to the set, returns true. Set now contains [1,2].
 * randomSet.insert(2);
 * <p>
 * // getRandom should return either 1 or 2 randomly.
 * randomSet.getRandom();
 * <p>
 * // Removes 1 from the set, returns true. Set now contains [2].
 * randomSet.remove(1);
 * <p>
 * // 2 was already in the set, so return false.
 * randomSet.insert(2);
 * <p>
 * // Since 2 is the only number in the set, getRandom always return 2.
 * randomSet.getRandom();
 */
public class InsertDeleteGetRandomO1 {
    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        IRandomizedSet randomSet = new RandomizedSet();

        // Inserts 1 to the set. Returns true as 1 was inserted successfully.
        System.out.println("insert 1:       " + randomSet.insert(1) + " expected " + true);

        // Returns false as 2 does not exist in the set.
        System.out.println("Remove 2:       " + randomSet.remove(2) + " expected " + false);

        // Inserts 2 to the set, returns true. Set now contains [1,2].
        System.out.println("insert 2:       " + randomSet.insert(2) + " expected " + true);

        // getRandom should return either 1 or 2 randomly.
        System.out.println("Random [1,2]:   " + randomSet.getRandom() + " expected [1,2]");

        // Removes 1 from the set, returns true. Set now contains [2].
        System.out.println("Remove 1:       " + randomSet.remove(1) + " expected " + true);

        // 2 was already in the set, so return false.
        System.out.println("insert 2:       " + randomSet.insert(2) + " expected " + false);

        // Since 2 is the only number in the set, getRandom always return 2.
        System.out.println("Random [2]:     " + randomSet.getRandom() + " expected [2] ");


    }
}

/**
 * Using rand.nextInt()
 * Runtime: 53 ms, faster than 75.36% of Java online submissions for Insert Delete GetRandom O(1).
 * Memory Usage: 46.1 MB, less than 68.00% of Java online submissions for Insert Delete GetRandom O(1).
 * <p>
 * using Math.random
 * Runtime: 52 ms, faster than 94.26% of Java online submissions for Insert Delete GetRandom O(1).
 * Memory Usage: 44 MB, less than 96.00% of Java online submissions for Insert Delete GetRandom O(1).
 */

class RandomizedSet implements IRandomizedSet {

    final List<Integer> items;
    final Map<Integer, Integer> cache;
    final Random random;

    public RandomizedSet() {
        items = new ArrayList<>();
        cache = new HashMap<>();
        random = new Random();
    }

    //O(1)
    public boolean insert(int val) {
        if (cache.containsKey(val))
            return false;

        cache.put(val, items.size());
        items.add(val);

        return true;

    }

    /**
     * To achieve same probability we need to use 'Reservoir sampling' logic. {https://www.geeksforgeeks.org/reservoir-sampling/}
     * Reservoir sampling: Whenever you remove the element from the container, swap this element with last element of container
     * <p>
     * Proof: if you swap the element {means put last element value at the current index from where we need to remove}.
     * This way each element can be pick up by 1/(n-1) probablity
     *
     * @param val
     * @return //O(1)
     */
    public boolean remove(int val) {
        if (!cache.containsKey(val))
            return false;

        int index = cache.get(val);

        //swap the value at this index with the value at last index { to remove last value }
        items.set(index, items.get(items.size() - 1));

        //update the index
        cache.put(items.get(items.size() - 1), index);

//            //if this element is not last one, then swap it with last one
//            if (index < items.size() - 1) {
//
//                //get value of last one
//                int lastValue = items.get(items.size() - 1);
//
//                //update value at this index { value at this index is going to remove }
//                items.set(index, lastValue);
//
//                //update the cache
//                cache.put(lastValue, index);
//            }

        //now remove the last element
        items.remove(items.size() - 1);
        cache.remove(val);

        return true;
    }

    //~O(1)
//        public int getRandom() {
//            if (items.isEmpty())
//                return -1;
//
//            return items.get(random.nextInt(items.size()));
//        }

    public int getRandom() {
        int rand = (int) (Math.random() * items.size());
        return items.get(rand);
    }
}