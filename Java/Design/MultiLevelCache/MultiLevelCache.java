package Java.Design.MultiLevelCache;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 15/04/19
 * Description:
 * https://www.careercup.com/question?id=5724980514914304
 */
public final class MultiLevelCache<K extends CacheKey, V> implements IMultiLevelCache<K, V> {

    private int levels = 1;
    private int capacity;
    private Map<Integer, LRUCache<K, V>> multiLevelCache;
    private final int levelStart;
    private final int levelEnd;


    public MultiLevelCache(int levels, int capacity) {
        this.multiLevelCache = new HashMap<>(levels);
        this.levels = levels;
        this.capacity = capacity;
        this.levelStart = 10;
        this.levelEnd = levelStart * levels;
        init();
    }

    public MultiLevelCache(int capacity) {
        this.multiLevelCache = new HashMap<>(levels);
        this.levels = 1;
        this.capacity = capacity;
        this.levelStart = 10;
        this.levelEnd = levelStart * levels;
        init();
    }

    private final void init() {
        //Init all the cache at each level
        for (int i = levelStart; i <= levelEnd; i += levelStart) {
            multiLevelCache.put(i, new LRUCache<>(capacity));
        }

    }


    private final Set<Integer> getDesiredLevels(int ownLevel) {

        Set<Integer> desiredLevels = new HashSet<>();

        int expectedLevel = ownLevel % levelEnd;

        if (expectedLevel < levelStart) {
            desiredLevels.add(levelStart);
            return desiredLevels;
        }

        for (int l = levelStart; l <= levelEnd; l += levelStart) {


            if (expectedLevel > l) {
                desiredLevels.add(l);
            } else if (expectedLevel <= l) {
                desiredLevels.add(l);
                break;
            }

        }

        return desiredLevels;
    }

    @Override
    public void add(K key, V value) {
        int level = key.getLevelId();
        Set<Integer> desiredLevels = getDesiredLevels(level);

        for (Integer levelId : desiredLevels) {
            multiLevelCache.get(levelId).put(key, value);
        }


    }

    @Override
    public Collection<V> remove(K key) {
        List<V> values = new LinkedList<>();
        int level = key.getLevelId();
        Set<Integer> desiredLevels = getDesiredLevels(level);

        for (Integer levelId : desiredLevels) {
            if (multiLevelCache.get(levelId).containsKey(key)) {
                values.add(multiLevelCache.get(levelId).remove(key));
            }
        }

        return values;

    }

    @Override
    public V get(K key) {
        for (Integer levelId : multiLevelCache.keySet()) {
            if (multiLevelCache.get(levelId).containsKey(key)) {
                return multiLevelCache.get(levelId).get(key);
            }
        }

        return null;

    }

    @Override
    public void update(K key, V value) {
        for (Integer levelId : multiLevelCache.keySet()) {
            if (multiLevelCache.get(levelId).containsKey(key)) {
                multiLevelCache.get(levelId).put(key, value);
            }
        }
    }

    @Override
    public void show() {
        for (Integer levelId : multiLevelCache.keySet()) {

            System.out.println("Level:" + levelId + " value: " + multiLevelCache.get(levelId).values().stream().collect(Collectors.toList()));

        }
    }
}
