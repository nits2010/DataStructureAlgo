package Java.Design.MultiLevelCache;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 15/04/19
 * Description:
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> implements Serializable {


    //Since this is lru cache, so if this cache get filed (over capacity), it will kick least recently used item.
    private int capacity;


    public LRUCache(int capacity) {
        // Call constructor of LinkedHashMap with accessOrder set to true to
        // achieve LRU Cache behavior
        super(capacity, 1.0f, true);
        this.capacity = capacity;
    }

    // Remove the eldest element whenever size of cache exceeds the capacity
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return (this.size() > capacity);
    }


}
