package DataStructureAlgo.Java.companyWise.phonepe.keyValueStore;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-12
 * Question Title: I Custom Map
 * Link: https://leetcode.com/problems/i-custom-map/
 * Description:
 * Description:
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public interface ICustomMap<Key, Value> {


    int size();

    boolean isEmpty();

    boolean containsKey(Object key);

    int hashCode();

    Value put(Key key, Value value);

    Value get(Key key);

}
