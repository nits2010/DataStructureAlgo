package DataStructureAlgo.Java.LeetCode.insertdeleterandom;

/**
 * Author: Nitin Gupta
 * Date: 19/09/19
 * Description:
 */
public interface IRandomizedSet {


    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    boolean insert(int val);

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    boolean remove(int val);

    /**
     * Get a random element from the set.
     */
    int getRandom();
}
