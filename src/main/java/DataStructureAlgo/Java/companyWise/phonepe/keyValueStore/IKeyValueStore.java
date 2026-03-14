package DataStructureAlgo.Java.companyWise.phonepe.keyValueStore;

import java.util.Set;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-12
 * Question Title: I Key Value Store
 * Link: https://leetcode.com/problems/i-key-value-store/
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

public interface IKeyValueStore<Key, Value> {

    Value get(Key key);

    void put(Key key, Value value);

    ICustomList<Value> getL(Key key);

    void putL(Key key, Value value);


    Set<Value> getS(Key key);

    void putS(Key key, Value value);


}
