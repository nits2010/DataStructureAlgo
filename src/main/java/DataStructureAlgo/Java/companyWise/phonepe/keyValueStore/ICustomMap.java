package DataStructureAlgo.Java.companyWise.phonepe.keyValueStore;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-12
 * Description:
 */
public interface ICustomMap<Key, Value> {


    int size();

    boolean isEmpty();

    boolean containsKey(Object key);

    int hashCode();

    Value put(Key key, Value value);

    Value get(Key key);

}
