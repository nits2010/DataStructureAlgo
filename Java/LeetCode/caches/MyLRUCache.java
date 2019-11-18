package Java.LeetCode.caches;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-26
 * Description: https://leetcode.com/problems/lru-cache/
 * <p>
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
 * <p>
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 * <p>
 * The cache is initialized with a positive capacity.
 * <p>
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 * <p>
 * Example:
 * <p>
 * LRUCache cache = new LRUCache( 2 /* capacity
 * <p>
 * <p>
 * cache.put(1,1);
 * cache.put(2,2);
 * cache.get(1);       // returns 1
 * cache.put(3,3);    // evicts key 2
 * cache.get(2);       // returns -1 (not found)
 * cache.put(4,4);    // evicts key 1
 * cache.get(1);       // returns -1 (not found)
 * cache.get(3);       // returns 3
 * cache.get(4);       // returns 4
 *
 * [Amazon]
 */


public class MyLRUCache {

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1) + " should be 1");
        cache.put(3, 3);
        System.out.println(cache.get(2) + " should be -1");
        cache.put(4, 4);
        System.out.println(cache.get(1) + " should be -1");
        System.out.println(cache.get(3) + " should be 3");
        System.out.println(cache.get(4) + " should be 4");


    }

}

class LRUCache {

    private static class Node {
        int key;
        int value;
        Node next;
        Node prev;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
            next = prev = null;
        }

    }

    private static class DLL {

        Node head, tail;

        DLL() {
            head = tail = null;
        }

        public Node remove(Node node) {

            if (node == null)
                return null;

            if (node.prev != null) {
                node.prev.next = node.next;
            } else {
                head = node.next;
            }

            if (node.next != null) {
                node.next.prev = node.prev;
            } else {
                tail = node.prev;
            }

            return node;
        }


        public void addToHead(Node node) {

            if (head == null) {
                head = tail = node;
                return;
            }

            node.next = head;
            node.prev = null;

            head.prev = node;


            head = node;

        }
    }

    private final Map<Integer, Node> cache;
    private final DLL lru;
    private final int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>(capacity);
        lru = new DLL();

    }

    public int get(int key) {
        if (!cache.containsKey(key))
            return -1;

        Node node = cache.get(key);

        lru.remove(node);
        lru.addToHead(node);

        return node.value;

    }

    public void put(int key, int value) {

        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;

            lru.remove(node);
            lru.addToHead(node);
        } else {

            //need to put the new key

            if (cache.size() >= capacity) {
                //cache is full , remove entry
                cache.remove(lru.tail.key);
                lru.remove(lru.tail);

            }

            Node node = new Node(key, value);
            lru.addToHead(node);
            cache.put(key, node);
        }

    }
}

/**
 * {@link Java.MultiLevelCache.LRUCache}
 */
class LRUUsingLinkedHashMap {

}