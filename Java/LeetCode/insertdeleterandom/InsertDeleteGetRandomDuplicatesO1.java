package Java.LeetCode.insertdeleterandom;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 19/09/19
 * Description: https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/
 * 381. Insert Delete GetRandom O(1) - Duplicates allowed [Hard]
 * <p>
 * Design a data structure that supports all following operations in average O(1) time.
 * <p>
 * Note: Duplicate elements are allowed.
 * insert(val): Inserts an item val to the collection.
 * remove(val): Removes an item val from the collection if present.
 * getRandom: Returns a random element from current collection of elements. The probability of each element being returned is linearly related to the number of same value the collection contains.
 * Example:
 * <p>
 * // Init an empty collection.
 * RandomizedCollection collection = new RandomizedCollection();
 * <p>
 * // Inserts 1 to the collection. Returns true as the collection did not contain 1.
 * collection.insert(1);
 * <p>
 * // Inserts another 1 to the collection. Returns false as the collection contained 1. Collection now contains [1,1].
 * collection.insert(1);
 * <p>
 * // Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
 * collection.insert(2);
 * <p>
 * // getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
 * collection.getRandom();
 * <p>
 * // Removes 1 from the collection, returns true. Collection now contains [1,2].
 * collection.remove(1);
 * <p>
 * // getRandom should return 1 and 2 both equally likely.
 * collection.getRandom();
 * <p>
 * Extension of {@link InsertDeleteGetRandomO1}
 */
public class InsertDeleteGetRandomDuplicatesO1 {
    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        IRandomizedSet collection = new RandomizedCollection();

        // Inserts 1 to the collection. Returns true as the collection did not contain 1.
        System.out.println("insert 1:       " + collection.insert(1) + " expected " + true);

        // Inserts another 1 to the collection. Returns false as the collection contained 1. Collection now contains [1,1].
        System.out.println("insert 1:       " + collection.insert(1) + " expected " + false);

        // Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
        System.out.println("Insert 2:       " + collection.insert(2) + " expected " + false);

        // getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
        System.out.println("Random [1,1,2]: " + collection.getRandom() + " expected [1,1,2]");

        // Removes 1 from the collection, returns true. Collection now contains [1,2].
        System.out.println("Remove [1,2]:   " + collection.remove(1) + " expected " + true);

        // getRandom should return 1 and 2 both equally likely.
        System.out.println("Random [1,2]:   " + collection.getRandom() + " expected [1,2] ");


    }
}

/**
 * We'll use same logic we used in {@link InsertDeleteGetRandomO1}.
 * <p>
 * Since in this case, the element can be duplicate and random works based on that. We need to store all the indexes of duplicate values.
 * For this, we'll use Set/List as a value in hashmap.
 * <p>
 * Algo:
 * 1. Insert : Add a new value in the hashmap if not present, otherwise append the index.
 * 2. Get Random: generate a random integer based on list size, and return the value at that index
 * 3. Remove: If element does not exist ignore it otherwise
 * 3.1 find the indexes of this element, replace of the index value with last element in list and update map accordingly
 * <p>
 * <p>
 * Using rand.nextInt()
 * Runtime: 53 ms, faster than 75.36% of Java online submissions for Insert Delete GetRandom O(1).
 * Memory Usage: 46.1 MB, less than 68.00% of Java online submissions for Insert Delete GetRandom O(1).
 * <p>
 * using Math.random
 * Runtime: 52 ms, faster than 94.26% of Java online submissions for Insert Delete GetRandom O(1).
 * Memory Usage: 44 MB, less than 96.00% of Java online submissions for Insert Delete GetRandom O(1).
 */

class RandomizedCollection implements IRandomizedSet {

    private final List<Integer> items;
    private final Map<Integer, LinkedHashSet<Integer>> cache;
    private final Random random;

    public RandomizedCollection() {
        items = new ArrayList<>();
        cache = new HashMap<>();
        random = new Random();
    }

    public boolean insert(int val) {
        boolean response = true;
        if (cache.containsKey(val))
            response = false;

        cache.computeIfAbsent(val, k -> new LinkedHashSet<>()).add(items.size());
        items.add(val);

        return response;

    }

    public boolean remove(int val) {

        if (!cache.containsKey(val) || cache.get(val).isEmpty())
            return false;

        //get the index of this element, this will be the first index when it occurred in cache
        int index = cache.get(val).iterator().next();
        int lastValue = items.get(items.size() - 1);

        //exchange with last element
        items.set(index, lastValue);

        //set the new index of last element in cache
        cache.get(lastValue).add(index);

        //remove the last element index as it has new place to hold
        cache.get(lastValue).remove(items.size() - 1);

        //remove this element index & element
        cache.get(val).remove(index);
        items.remove(items.size() - 1); //O(1) last element deleted
        return true;
    }


    //~O(1)
    public int getRandom() {
        if (items.isEmpty())
            return -1;

        return items.get(random.nextInt(items.size()));
    }

//    public int getRandom() {
//        int rand = (int) (Math.random() * items.size());
//        return items.get(rand);
//    }


}