package Java.companyWise.phonepe;

import java.util.List;
import java.util.Set;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
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
    public List<Value> getL(Key key) {
        return (List<Value>) keyValueMap.get(key);
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
