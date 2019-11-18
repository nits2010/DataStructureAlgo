package Java.companyWise.phonepe.keyValueStore;

import java.util.Set;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-12
 * Description:
 */
public class KeyValueStore<Key, Value> implements IKeyValueStore<Key, Value> {


    //    private Map<Key, Value> keyValueMap;
    private ICustomMap<Key, Value> keyValueMap;

    public KeyValueStore() {
//        this.keyValueMap = new ConcurrentHashMap<>();
        this.keyValueMap = new CustomConcurrentHashMap<>();
    }


    @Override
    public Value get(Key key) {
        return keyValueMap.get(key);
    }

    @Override
    public void put(Key key, Value value) {
        keyValueMap.put(key, value);
    }

    @Override
    public ICustomList<Value> getL(Key key) {
        return (ICustomList<Value>) keyValueMap.get(key);
    }

    @Override
    public void putL(Key key, Value value) {

        keyValueMap.put(key, value);
    }

    @Override
    public Set<Value> getS(Key key) {
        return (Set<Value>) keyValueMap.get(key);
    }

    @Override
    public void putS(Key key, Value value) {
        keyValueMap.put(key, value);
    }
}
