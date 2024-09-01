package DataStructureAlgo.Java.LeetCode2025.ProblemSet.List.Cache;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * Author: Nitin Gupta
 * Date: 8/3/2024
 * Question Category: 460. LFU Cache [hard]
 * Description: https://leetcode.com/problems/lfu-cache/description/
 *
 * <p>
 * Design and implement a data structure for a Least Frequently Used (LFU) cache.
 * <p>
 * Implement the LFUCache class:
 * <p>
 * LFUCache(int capacity) Initializes the object with the capacity of the data structure.
 * int get(int key) Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
 * void put(int key, int value) Update the value of the key if present, or inserts the key if not already present. When the cache reaches its capacity, it should invalidate and remove the least frequently used key before inserting a new item. For this problem, when there is a tie (i.e., two or more keys with the same frequency), the least recently used key would be invalidated.
 * To determine the least frequently used key, a use counter is maintained for each key in the cache. The key with the smallest use counter is the least frequently used key.
 * <p>
 * When a key is first inserted into the cache, its use counter is set to 1 (due to the put operation). The use counter for a key in the cache is incremented either a get or put operation is called on it.
 * <p>
 * The functions get and put must each run in O(1) average time complexity.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
 * Output
 * [null, null, null, 1, null, -1, 3, null, -1, 3, 4]
 * <p>
 * Explanation
 * // cnt(x) = the use counter for key x
 * // cache=[] will show the last used order for tiebreakers (leftmost element is  most recent)
 * LFUCache lfu = new LFUCache(2);
 * lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
 * lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
 * lfu.get(1);      // return 1
 * // cache=[1,2], cnt(2)=1, cnt(1)=2
 * lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
 * // cache=[3,1], cnt(3)=1, cnt(1)=2
 * lfu.get(2);      // return -1 (not found)
 * lfu.get(3);      // return 3
 * // cache=[3,1], cnt(3)=2, cnt(1)=2
 * lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
 * // cache=[4,3], cnt(4)=1, cnt(3)=2
 * lfu.get(1);      // return -1 (not found)
 * lfu.get(3);      // return 3
 * // cache=[3,4], cnt(4)=1, cnt(3)=3
 * lfu.get(4);      // return 4
 * // cache=[4,3], cnt(4)=2, cnt(3)=3
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= capacity <= 104
 * 0 <= key <= 105
 * 0 <= value <= 109
 * At most 2 * 105 calls will be made to get and put.
 * <p>
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.caches.MyLFUCache}
 * Similar {@link LRUCache_146}
 * extension {@link }
 * <p>
 * Tags
 * -----
 *
 * @hard
 * @HashTable
 * @LinkedList
 * @Design
 * @DoublyLinkedList
 *
 * <p>
 * Company Tags
 * -----
 * @Microsoft
 * @Amazon
 * @Google
 * @Snapchat
 * @LinkedIn
 */
public class LFUCache_460 {

    public static void main(String[] args) {

        System.out.println("Test1 :" + (test1() ? " Passed " : " Failed "));

        System.out.println("Test2 :" + (test2() ? " Passed " : " Failed "));

        System.out.println("Test3 :" + (test3() ? " Passed " : " Failed "));

    }
    static boolean test1() {
        boolean testResultHeap = true;
        LFUCacheUsingHeap.LFUCache cacheHeap = new LFUCacheUsingHeap.LFUCache(2);


        cacheHeap.put(1, 1);
        cacheHeap.put(2, 2);
        testResultHeap &= cacheHeap.get(1) == 1;       // returns 1
        cacheHeap.put(3, 3);    // evicts key 2
        testResultHeap &= cacheHeap.get(2) == -1;       // returns -1 (not found)
        testResultHeap &= cacheHeap.get(3) == 3;    // returns 3.
        cacheHeap.put(4, 4);    // evicts key 1.
        testResultHeap &= cacheHeap.get(1) == -1;      // returns -1 (not found)
        testResultHeap &= cacheHeap.get(3) == 3;    // returns 3
        testResultHeap &= cacheHeap.get(4) == 4;       // returns 4



        boolean testResulTreeMap = true;
        LFUCacheUsingHeap.LFUCache cacheTreeMap = new LFUCacheUsingHeap.LFUCache(2);


        cacheTreeMap.put(1, 1);
        cacheTreeMap.put(2, 2);
        testResulTreeMap &= cacheTreeMap.get(1) == 1;       // returns 1
        cacheTreeMap.put(3, 3);    // evicts key 2
        testResulTreeMap &= cacheTreeMap.get(2) == -1;       // returns -1 (not found)
        testResulTreeMap &= cacheTreeMap.get(3) == 3;    // returns 3.
        cacheTreeMap.put(4, 4);    // evicts key 1.
        testResulTreeMap &= cacheTreeMap.get(1) == -1;      // returns -1 (not found)
        testResulTreeMap &= cacheTreeMap.get(3) == 3;    // returns 3
        testResulTreeMap &= cacheTreeMap.get(4) == 4;       // returns 4

        System.out.println(" Result: Heap | treeMap : "+ testResultHeap + " | " + testResulTreeMap);
        return testResultHeap == testResulTreeMap;


    }

    static boolean test2() {
        boolean testResultHeap = true;
        LFUCacheUsingHeap.LFUCache cacheHeap = new LFUCacheUsingHeap.LFUCache(1);
        cacheHeap.put(0, 0);

        testResultHeap &= cacheHeap.get(0) == 0;
        testResultHeap &= cacheHeap.get(1) == -1;


        boolean testResulTreeMap = true;
        LFUCacheUsingHeap.LFUCache cacheTreeMap = new LFUCacheUsingHeap.LFUCache(1);
        cacheTreeMap.put(0, 0);

        testResulTreeMap &= cacheTreeMap.get(0) == 0;
        testResulTreeMap &= cacheTreeMap.get(1) == -1;
        System.out.println(" Result: Heap | treeMap : "+ testResultHeap + " | " + testResulTreeMap);
        return testResultHeap == testResulTreeMap;

    }

    static boolean test3() {
        boolean testResultHeap = true;
        LFUCacheUsingHeap.LFUCache cacheHeap = new LFUCacheUsingHeap.LFUCache(3);

        cacheHeap.put(2, 2);
        cacheHeap.put(1, 1);
        testResultHeap &= cacheHeap.get(2) == 2;
        testResultHeap &= cacheHeap.get(1) == 1;
        cacheHeap.put(3, 3);
        cacheHeap.put(4, 4);
        testResultHeap &= cacheHeap.get(3) == -1;
        testResultHeap &= cacheHeap.get(2) == 2;
        testResultHeap &= cacheHeap.get(1) == 1;
        testResultHeap &= cacheHeap.get(4) == 4;

        boolean testResulTreeMap = true;
        LFUCacheUsingHeap.LFUCache cacheTreeMap = new LFUCacheUsingHeap.LFUCache(3);

        cacheTreeMap.put(2, 2);
        cacheTreeMap.put(1, 1);
        testResulTreeMap &= cacheTreeMap.get(2) == 2;
        testResulTreeMap &= cacheTreeMap.get(1) == 1;
        cacheTreeMap.put(3, 3);
        cacheTreeMap.put(4, 4);
        testResulTreeMap &= cacheTreeMap.get(3) == -1;
        testResulTreeMap &= cacheTreeMap.get(2) == 2;
        testResulTreeMap &= cacheTreeMap.get(1) == 1;
        testResulTreeMap &= cacheTreeMap.get(4) == 4;
        System.out.println(" Result: Heap | treeMap : "+ testResultHeap + " | " + testResulTreeMap);
        return testResultHeap == testResulTreeMap;




    }


}

/**
 * T/S :
 * get -> O(n)
 * put -> O(n)
 */
class LFUCacheUsingHeap {
    static class LFUCache {

        static class CacheNode implements Comparable<CacheNode> {
            int key;
            int value;

            long frequency;

            long timestamp; //if two different key has same frequency, then in order to make, correct decision we can use timestamp when they inserted into cache

            CacheNode(int key, int value, long frequency, long timestamp) {
                this.key = key;
                this.value = value;
                this.frequency = frequency;
                this.timestamp = timestamp;
            }

            @Override
            public int compareTo(CacheNode o) {
                if (this.frequency == o.frequency)
                    return Long.compare(this.timestamp, o.timestamp);

                return Long.compare(this.frequency, o.frequency);
            }
        }

        private final PriorityQueue<CacheNode> lfuQueue;
        private final Map<Integer, CacheNode> cache;

        private int capacity;

        long currentTimeStamp;

        public LFUCache(int capacity) {
            this.capacity = capacity;

            lfuQueue = new PriorityQueue<>(capacity);
            cache = new HashMap<>(capacity);
            currentTimeStamp = 0;

        }

        public int get(int key) {
            final CacheNode node = cache.get(key);

            //item not present
            if (node == null)
                return -1;

            //item present, we need to return its value but as well update it frequency

            //remove them from queue first. we can't simply update its frequency as doing that won't update heap
            lfuQueue.remove(node);
            cache.remove(node.key);

            //add back to cache
            CacheNode newNode = new CacheNode(node.key, node.value, node.frequency + 1, currentTimeStamp++);
            lfuQueue.add(newNode);
            cache.put(newNode.key, newNode);

            return node.value;


        }

        public void put(int key, int value) {

            //check if this item exist
            CacheNode node = cache.get(key);

            //fresh node to add
            if (node == null) {

                //check if the cache is full or not
                if (cache.size() == capacity) {

                    //remove one element and insert new element based on frequency
                    CacheNode item = lfuQueue.poll();
                    if (item != null) {
                        cache.remove(item.key);
                    }

                }

                //add new node
                CacheNode item = new CacheNode(key, value, 1, currentTimeStamp++);
                lfuQueue.offer(item);
                cache.put(key, item);

            } else {

                //we need to update its frequency
                //remove them from queue first. we can't simply update its frequency as doing that won't update heap
                lfuQueue.remove(node);
                cache.remove(node.key);

                //add back to cache
                CacheNode newNode = new CacheNode(key, value, node.frequency + 1, currentTimeStamp++);
                lfuQueue.add(newNode);
                cache.put(newNode.key, newNode);
            }

        }
    }

}


class LFUCacheUsingTreeMap {
    static class LFUCache {

        static class CacheNode implements Comparable<CacheNode> {
            int key;
            int value;

            long frequency;

            long timestamp; //if two different key has same frequency, then in order to make, correct decision we can use timestamp when they inserted into cache

            CacheNode(int key, int value, long frequency, long timestamp) {
                this.key = key;
                this.value = value;
                this.frequency = frequency;
                this.timestamp = timestamp;
            }

            @Override
            public int compareTo(CacheNode o) {
                if (this.frequency == o.frequency)
                    return Long.compare(this.timestamp, o.timestamp);

                return Long.compare(this.frequency, o.frequency);
            }
        }

        private final TreeMap<CacheNode,Integer> lfuQueue;
        private final Map<Integer, CacheNode> cache;

        private int capacity;

        long currentTimeStamp;

        public LFUCache(int capacity) {
            this.capacity = capacity;

            lfuQueue = new TreeMap<>();
            cache = new HashMap<>(capacity);
            currentTimeStamp = 0;

        }

        public int get(int key) {
            final CacheNode node = cache.get(key);

            //item not present
            if (node == null)
                return -1;

            //item present, we need to return its value but as well update it frequency

            //remove them from queue first. we can't simply update its frequency as doing that won't update heap
            lfuQueue.remove(node);
            cache.remove(node.key);

            //add back to cache
            CacheNode newNode = new CacheNode(node.key, node.value, node.frequency + 1, currentTimeStamp++);
            lfuQueue.put(newNode, newNode.key);
            cache.put(newNode.key, newNode);

            return node.value;


        }

        public void put(int key, int value) {

            //check if this item exist
            CacheNode node = cache.get(key);

            //fresh node to add
            if (node == null) {

                //check if the cache is full or not
                if (cache.size() == capacity) {

                    //remove one element and insert new element based on frequency
                    Integer item = lfuQueue.pollFirstEntry().getValue();
                    if (item != null) {
                        cache.remove(item);
                    }

                }

                //add new node
                CacheNode item = new CacheNode(key, value, 1, currentTimeStamp++);
                lfuQueue.put(item, item.key);
                cache.put(key, item);

            } else {

                //we need to update its frequency
                //remove them from queue first. we can't simply update its frequency as doing that won't update heap
                lfuQueue.remove(node);
                cache.remove(node.key);

                //add back to cache
                CacheNode newNode = new CacheNode(key, value, node.frequency + 1, currentTimeStamp++);
                lfuQueue.put(newNode, newNode.key);
                cache.put(newNode.key, newNode);
            }

        }
    }

}
