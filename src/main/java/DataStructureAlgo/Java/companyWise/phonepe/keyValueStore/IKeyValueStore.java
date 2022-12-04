package DataStructureAlgo.Java.companyWise.phonepe.keyValueStore;

import java.util.Set;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-12
 * Description:
 */
public interface IKeyValueStore<Key, Value> {

    Value get(Key key);

    void put(Key key, Value value);

    ICustomList<Value> getL(Key key);

    void putL(Key key, Value value);


    Set<Value> getS(Key key);

    void putS(Key key, Value value);


}
