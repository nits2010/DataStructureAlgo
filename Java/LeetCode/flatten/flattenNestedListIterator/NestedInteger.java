package Java.LeetCode.flatten.flattenNestedListIterator;

import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 08/03/19
 * Description:
 */
public interface NestedInteger
{
    /** @return true if this LinkedInt.NestedInteger holds a single integer, rather than a nested list */
    boolean isInteger();

    /** @return the single integer that this LinkedInt.NestedInteger holds, if it holds a single integer
     * Return null if this LinkedInt.NestedInteger holds a nested list */
    Integer getInteger();

    /** @return the nested list that this LinkedInt.NestedInteger holds, if it holds a nested list
     * Return null if this LinkedInt.NestedInteger holds a single integer */
    List<NestedInteger> getList();
}
