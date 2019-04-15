package Java.Design.MultiLevelCache;

import java.util.Collection;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 15/04/19
 * Description:
 */
public interface IMultiLevelCache<K extends CacheKey, V> {

    /**
     * this will add the element at a particular level along with from top to this level
     *
     * @param key
     * @param value
     */
    void add(K key, V value);

    Collection<V> remove(K key);

    V get(K key);

    void update(K key, V value);

    void show();
}
