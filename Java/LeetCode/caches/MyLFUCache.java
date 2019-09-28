package Java.LeetCode.caches;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-26
 * Description: https://leetcode.com/problems/lfu-cache/
 * Design and implement a data structure for Least Frequently Used (LFU) cache.
 * It should support the following operations: get and put.
 * <p>
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.
 * <p>
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 * <p>
 * LFUCache cache = new LFUCache( 2  );
 * <p>
 * cache.put(1,1);
 * cache.put(2,2);
 * cache.get(1);       // returns 1
 * cache.put(3,3);    // evicts key 2
 * cache.get(2);       // returns -1 (not found)
 * cache.get(3);       // returns 3.
 * cache.put(4,4);    // evicts key 1.
 * cache.get(1);       // returns -1 (not found)
 * cache.get(3);       // returns 3
 * cache.get(4);       // returns 4
 */
public class MyLFUCache {


    public static void main(String[] args) {

        System.out.println("Test1 :\n");
        test1();

        System.out.println("Test2 :\n");
        test2();

        System.out.println("Test3 :\n");
        test3();

    }

    static void test2() {

        LFUCacheUsingHeap lfuCacheUsingHeap = new LFUCacheUsingHeap();
        LFUCacheUsingHeap.LFUCache cache = lfuCacheUsingHeap.new LFUCache(0);
        cache.put(0, 0);

        System.out.println("\nTree Map");

        LFUCacheUsingTreeMap lfuCacheUsingTreeMap = new LFUCacheUsingTreeMap();
        LFUCacheUsingTreeMap.LFUCache cacheTreeMap = lfuCacheUsingTreeMap.new LFUCache(0);
        cacheTreeMap.put(0, 0);


        System.out.println("\nConstant time");

        LFUCacheConstantTime lfuCacheConstantTime = new LFUCacheConstantTime();
        LFUCacheConstantTime.LFUCache cacheConstantTime = lfuCacheConstantTime.new LFUCache(0);
        cacheConstantTime.put(0, 0);


    }

    static void test3() {

        LFUCacheUsingHeap lfuCacheUsingHeap = new LFUCacheUsingHeap();
        LFUCacheUsingHeap.LFUCache cache = lfuCacheUsingHeap.new LFUCache(3);

        cache.put(2, 2);
        cache.put(1, 1);
        System.out.println(cache.get(2) + " should be 2");
        System.out.println(cache.get(1) + " should be 1");
        cache.put(3, 3);
        cache.put(4, 4);
        System.out.println(cache.get(3) + " should be -1");
        System.out.println(cache.get(2) + " should be 2");
        System.out.println(cache.get(1) + " should be 1");
        System.out.println(cache.get(4) + " should be 4");


        System.out.println("\nTree Map");

        LFUCacheUsingTreeMap lfuCacheUsingTreeMap = new LFUCacheUsingTreeMap();
        LFUCacheUsingTreeMap.LFUCache cacheTreeMap = lfuCacheUsingTreeMap.new LFUCache(3);


        cacheTreeMap.put(2, 2);
        cacheTreeMap.put(1, 1);
        System.out.println(cacheTreeMap.get(2) + " should be 2");
        System.out.println(cacheTreeMap.get(1) + " should be 1");
        cacheTreeMap.put(3, 3);
        cacheTreeMap.put(4, 4);
        System.out.println(cacheTreeMap.get(3) + " should be -1");
        System.out.println(cacheTreeMap.get(2) + " should be 2");
        System.out.println(cacheTreeMap.get(1) + " should be 1");
        System.out.println(cacheTreeMap.get(4) + " should be 4");


        System.out.println("\nConstant time Map");

        LFUCacheConstantTime lfuCacheConstantTime = new LFUCacheConstantTime();
        LFUCacheConstantTime.LFUCache cacheConstantTime = lfuCacheConstantTime.new LFUCache(3);


        cacheConstantTime.put(2, 2);
        cacheConstantTime.put(1, 1);
        System.out.println(cacheConstantTime.get(2) + " should be 2");
        System.out.println(cacheConstantTime.get(1) + " should be 1");
        cacheConstantTime.put(3, 3);
        cacheConstantTime.put(4, 4);
        System.out.println(cacheConstantTime.get(3) + " should be -1");
        System.out.println(cacheConstantTime.get(2) + " should be 2");
        System.out.println(cacheConstantTime.get(1) + " should be 1");
        System.out.println(cacheConstantTime.get(4) + " should be 4");


    }

    static void test1() {
        LFUCacheUsingHeap lfuCacheUsingHeap = new LFUCacheUsingHeap();
        LFUCacheUsingHeap.LFUCache cache = lfuCacheUsingHeap.new LFUCache(2);


        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1) + " should be 1");       // returns 1
        cache.put(3, 3);    // evicts key 2
        System.out.println(cache.get(2) + " should be -1");       // returns -1 (not found)
        System.out.println(cache.get(3) + " should be 3");    // returns 3.
        cache.put(4, 4);    // evicts key 1.
        System.out.println(cache.get(1) + " should be -1");      // returns -1 (not found)
        System.out.println(cache.get(3) + " should be 3");    // returns 3
        System.out.println(cache.get(4) + " should be 4");       // returns 4


        System.out.println("\nTree Map");

        LFUCacheUsingTreeMap lfuCacheUsingTreeMap = new LFUCacheUsingTreeMap();
        LFUCacheUsingTreeMap.LFUCache cacheTreeMap = lfuCacheUsingTreeMap.new LFUCache(2);


        cacheTreeMap.put(1, 1);
        cacheTreeMap.put(2, 2);
        System.out.println(cacheTreeMap.get(1) + " should be 1");       // returns 1
        cacheTreeMap.put(3, 3);    // evicts key 2
        System.out.println(cacheTreeMap.get(2) + " should be -1");       // returns -1 (not found)
        System.out.println(cacheTreeMap.get(3) + " should be 3");    // returns 3.
        cacheTreeMap.put(4, 4);    // evicts key 1.
        System.out.println(cacheTreeMap.get(1) + " should be -1");      // returns -1 (not found)
        System.out.println(cacheTreeMap.get(3) + " should be 3");    // returns 3
        System.out.println(cacheTreeMap.get(4) + " should be 4");       // returns 4


        System.out.println("\nConstant Time Map");

        LFUCacheConstantTime lfuCacheConstantTime = new LFUCacheConstantTime();
        LFUCacheConstantTime.LFUCache cacheConstantTime = lfuCacheConstantTime.new LFUCache(2);


        cacheConstantTime.put(1, 1);
        cacheConstantTime.put(2, 2);
        System.out.println(cacheConstantTime.get(1) + " should be 1");       // returns 1
        cacheConstantTime.put(3, 3);    // evicts key 2
        System.out.println(cacheConstantTime.get(2) + " should be -1");       // returns -1 (not found)
        System.out.println(cacheConstantTime.get(3) + " should be 3");    // returns 3.
        cacheConstantTime.put(4, 4);    // evicts key 1.
        System.out.println(cacheConstantTime.get(1) + " should be -1");      // returns -1 (not found)
        System.out.println(cacheConstantTime.get(3) + " should be 3");    // returns 3
        System.out.println(cacheConstantTime.get(4) + " should be 4");       // returns 4


    }
}


/**
 * Complexity Analysis
 * Get: O(n) Put : O(n)
 * Because remove method in Queue take O(n) time
 * 235 ms/66.3 MB
 */
class LFUCacheUsingHeap {


    class LFUCache {


        private class Cache implements Comparable<Cache> {
            final int key;
            int value;
            int freq;
            final long timeStamp;


            public Cache(int key, int value, int freq, long timeStamp) {
                this.key = key;
                this.value = value;
                this.freq = freq;
                this.timeStamp = timeStamp;
            }

            @Override
            public int compareTo(Cache o) {
                if (this.freq == o.freq)
                    return Long.compare(this.timeStamp, o.timeStamp);

                return Integer.compare(this.freq, o.freq);
            }
        }

        private final PriorityQueue<Cache> lfu;
        private final Map<Integer, Cache> cache;
        private final int capacity;
        private long currentTimeStamp;


        public LFUCache(int capacity) {

            cache = new HashMap<>(capacity);
            this.capacity = capacity;
            lfu = new PriorityQueue<>();
            currentTimeStamp = 0;
        }

        public int get(int key) {

            /**
             * Handle corner case when cache size is zero: Real life No way :P
             */
            if (capacity == 0)
                return -1;

            /**
             * Do we have this key, no. Simply say i don't have -1
             */
            if (!cache.containsKey(key))
                return -1;


            Cache node = cache.get(key);

            //Remove stale data
            lfu.remove(node);
            cache.remove(node.key);


            Cache newNode = getNewNode(node);
            cache.put(key, newNode);
            lfu.offer(newNode);

            return node.value;

        }

        private Cache getNewNode(Cache node) {
            return new Cache(node.key, node.value, node.freq + 1, currentTimeStamp++);
        }


        public void put(int key, int value) {
            /**
             * Handle corner case when cache size is zero: Real life No way :P
             */
            if (capacity == 0)
                return;


            if (cache.containsKey(key)) {

                Cache node = cache.get(key);
                lfu.remove(node);
                cache.remove(node.key);

                Cache newNode = getNewNode(node);
                newNode.value = value;
                lfu.offer(newNode);
                cache.put(key, newNode);

            } else {

                if (cache.size() == capacity) {

                    Cache removed = lfu.poll();
                    cache.remove(removed.key);

//                    System.out.println("Key removed " + removed.key);
                }

                Cache node = new Cache(key, value, 1, currentTimeStamp++);
                lfu.offer(node);
                cache.put(key, node);
            }

        }
    }
}


/**
 * Complexity Analysis
 * Get: O(logn) Put : O(logn)
 * Remove method in TreeMap takes O(logn)
 * <p>
 * 83 ms/57.8 MB
 */
class LFUCacheUsingTreeMap {


    class LFUCache {


        private class Cache implements Comparable<Cache> {
            int key;
            int value;
            int freq;
            int timeStamp;


            public Cache(int key, int value, int freq, int timeStamp) {
                this.key = key;
                this.value = value;
                this.freq = freq;
                this.timeStamp = timeStamp;
            }


            @Override
            public int compareTo(Cache o) {
                if (this.freq == o.freq)
                    return Integer.compare(this.timeStamp, o.timeStamp);

                return Integer.compare(this.freq, o.freq);
            }
        }

        private final int capacity;
        private int timeStamp;
        private final HashMap<Integer, Cache> cache;
        private final TreeMap<Cache, Integer> lfu;

        public LFUCache(int capacity) {
            this.capacity = capacity;
            timeStamp = 0;
            cache = new HashMap<>();
            lfu = new TreeMap<>();
        }

        public int get(int key) {
            if (capacity == 0) {
                return -1;
            }
            if (!cache.containsKey(key)) {
                return -1;
            }

            Cache old = cache.get(key);
            lfu.remove(old);
            cache.remove(old.key);

            Cache newTuple = new Cache(key, old.value, old.freq + 1, timeStamp++);
            lfu.put(newTuple, key);
            cache.put(key, newTuple);

            return old.value;
        }

        public void put(int key, int value) {
            if (capacity == 0) {
                return;
            }
            if (cache.containsKey(key)) {
                Cache old = cache.get(key);
                lfu.remove(old);
                cache.remove(old.key);

                Cache newTuple = new Cache(key, value, old.freq + 1, timeStamp++);
                cache.put(key, newTuple);
                lfu.put(newTuple, key);

            } else {
                if (lfu.size() == capacity) {
                    int endKey = lfu.pollFirstEntry().getValue();
                    cache.remove(endKey);
                }
                Cache newTuple = new Cache(key, value, 1, timeStamp++);
                cache.put(key, newTuple);
                lfu.put(newTuple, key);
            }
        }
    }
}


/**
 * Concept: https://www.javarticles.com/2012/06/lfu-cache.html
 * https://medium.com/algorithm-and-datastructure/lfu-cache-in-o-1-in-java-4bac0892bdb3
 * <p>
 * To put and get data in O(1), We need to use Map or more specifically HashMap.
 * HashMap<K, V>
 * Since we need to find the least frequently used item to remove it for putting new data,
 * we need a counter to keep track number of times a Key(K) has been accessed. Access could get or put. To achieve that we need another Map<K, C>;
 * K is the key of the item to put and C is the counter.
 * HashMap<K, C>
 * From the above two data structure, we can put and get data in O(1). We can also get the counter of an item has been used.
 * Another thing, we need, is a list where we can store the information of count and items key. Lets elaborate that in details,
 * assume A has been used 5times, B also has been used 5times. We need to store that information such a way that will hold the items in a list based on their insertion order.
 * (FIFO). To achieve that we can use HashSet<K> and more precisely LinkedHashSet<K>. But we want to keep track of the counter as well(in our example 5 times or 5). So we need another map.
 * HashMap<K,LinkedHashSet<K>>
 * We need a tag or min variable, it will hold the current min. Whenever a new Item inserts into the cache min=1; It will be increased only when there are no items in the (counter==min).
 */
class LFUCacheConstantTime {

    class LFUCache {

        private class Cache {
            int key;
            int value;

            public Cache(int key, int value) {
                this.key = key;
                this.value = value;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Cache cache = (Cache) o;
                return key == cache.key;
            }

            @Override
            public int hashCode() {
                return Objects.hash(key);
            }
        }

        /**
         * Defines our actual cache.
         * Key: As cache key and
         * Value: As cache Node
         */
        private final Map<Integer, Cache> cache;

        /**
         * Defines the Frequency Cache
         * Key: As cache key and
         * Value: How many times a key accessed
         */
        private final Map<Integer, Integer> frequencyCache;

        /**
         * This is Frequency to DLL ; i avoid making own dll to simplify the code.
         * Key: frequency the item(key)
         * Value: Caches in First in First out manner (FIFO) to achieve Least Recently Used
         */
        private final Map<Integer, LinkedHashSet<Cache>> lfu;

        /**
         * Actual capacity of cache
         */
        private final int capacity;

        /**
         * This indicates what is the least frequency in the system currently.
         * This keep changing based on new key and old key
         * as new key comes, this becomes 1 otherwise it changes accordingly
         */
        private int leastFrequency;

        public LFUCache(int capacity) {

            cache = new HashMap<>();
            frequencyCache = new HashMap<>();
            lfu = new HashMap<>();
            this.capacity = capacity;
            this.leastFrequency = 0; //as there is no entry in the lfu
        }

        public int get(int key) {

            if (capacity == 0)
                return -1;

            /**
             * Does this item present in our cache?
             */
            if (!cache.containsKey(key))
                return -1;

            //Get the item
            Cache item = cache.get(key);

            //update its frequency
            int oldFrequency = frequencyCache.getOrDefault(item.key, 1);
            frequencyCache.put(item.key, oldFrequency + 1);

            //Update our lfu, remove from head (or in somewhere) and push it to tail
            lfu.get(oldFrequency).remove(item);

            //does any more item left at this frequency? , if not then leastFrequency will be the next one for sure
            if (oldFrequency == leastFrequency && lfu.get(leastFrequency).size() == 0)
                leastFrequency++;

            if (!lfu.containsKey(oldFrequency + 1))
                lfu.put(oldFrequency + 1, new LinkedHashSet<>());

            lfu.get(oldFrequency + 1).add(new Cache(key, item.value));
            return item.value;
        }

        public void put(int key, int value) {

            if (capacity == 0)
                return;

            /**
             * If key exist in our cache
             */
            if (cache.containsKey(key)) {
                //Then update its frequency
                cache.put(key, new Cache(key, value));

                //Use get to update it frequency; No code duplication
                get(key);

                return;
            } else {

                //This is new entry in our system, do we have capacity ?
                if (cache.size() == capacity) {
                    //we don't have capacity, the lfu item need to be evict based on leastFrequentlyUsed, if frequency clash than LRU

                    //find the item
                    Cache item = lfu.get(leastFrequency).iterator().next();

                    //evict it
                    lfu.get(leastFrequency).remove(item);

                    //remove from cache
                    cache.remove(item.key);

                    //remove from frequency list
                    frequencyCache.remove(item.key);

                }

                //We must have space now, push this as new item with frequency 1
                Cache newItem = new Cache(key, value);

                //push in cache
                cache.put(key, newItem);

                //push in frequency cache
                frequencyCache.put(key, 1);

                //Since this will be our least frequency item
                leastFrequency = 1;

                //Add into our lfu
                if (!lfu.containsKey(leastFrequency))
                    lfu.put(leastFrequency, new LinkedHashSet<>());

                lfu.get(leastFrequency).add(newItem);

            }

        }
    }
}

