package DataStructureAlgo.Java.LeetCode2025.medium.List.Cache;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Author: Nitin Gupta
 * Date: 8/3/2024
 * Question Category: 146. LRU Cache [ medium ]
 * Description: https://leetcode.com/problems/lru-cache
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 * <p>
 * Implement the LRUCache class:
 * <p>
 * LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * int get(int key) Return the value of the key if the key exists, otherwise return -1.
 * void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 * The functions get and put must each run in O(1) average time complexity.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * Output
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 * <p>
 * Explanation
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // cache is {1=1}
 * lRUCache.put(2, 2); // cache is {1=1, 2=2}
 * lRUCache.get(1);    // return 1
 * lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
 * lRUCache.get(2);    // returns -1 (not found)
 * lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
 * lRUCache.get(1);    // return -1 (not found)
 * lRUCache.get(3);    // return 3
 * lRUCache.get(4);    // return 4
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= capacity <= 3000
 * 0 <= key <= 104
 * 0 <= value <= 105
 * At most 2 * 105 calls will be made to get and put.
 * <p>
 *
 * <p>
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.caches.MyLRUCache}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 *
 * @medium
 * @HashTable
 * @LinkedList
 * @Design
 * @DoublyLinkedList
 * <p>
 * Company Tags
 * -----
 * @Amazon
 * @Facebook
 * @Microsoft
 * @Apple
 * @Bloomberg
 */
public class LRUCache_146 {
    public static void main(String[] args) {
        boolean testResult =
                        test()
                        &&
                        test2()
                        &&
                        test3()
                        &&
                        test4()
                        &&
                        test5()
                ;

        System.out.println("\nAll test = " + (testResult ? "Pass" : "Fail"));


    }

    private static boolean test() {
        boolean testResult = true;
        LRUCache cache = new LRUCache(2);

        cache.put(1, 1);
        cache.put(2, 2);
        testResult &= cache.get(1) == 1;
        cache.put(3, 3);
        testResult &= cache.get(2) == -1;
        cache.put(4, 4);
        testResult &= cache.get(1) == -1;
        testResult &= cache.get(3) == 3;
        testResult &= cache.get(4) == 4;

        System.out.println("\nTest result = " + (testResult ? "Pass" : "Fail"));
        return testResult;


    }

    private static boolean test2() {
        boolean testResult = true;
        LRUCache cache = new LRUCache(1);

        cache.put(2, 1);
        testResult = cache.get(2) == 1;
        System.out.println("\nTest result = " + (testResult ? "Pass" : "Fail"));
        return testResult;


    }

    private static boolean test3() {
        boolean testResult;
        LRUCache cache = new LRUCache(2);

        //[[2],[2,1],[1,1],[2,3],[4,1],[1],[2]]
        cache.put(2, 1);
        cache.put(1, 1);
        cache.put(2, 3);
        cache.put(4, 1);

        testResult = cache.get(1) == -1;
        testResult &= cache.get(2) == 3;
        System.out.println("\nTest result = " + (testResult ? "Pass" : "Fail"));

        return testResult;


    }

    private static boolean test5() {

        // Initialize the LRUCache with capacity
        LRUCache cache = new LRUCache(10);
        boolean testResult = true;

        // Execute the commands
        cache.put(10, 13);
        cache.put(3, 17);
        cache.put(6, 11);
        cache.put(10, 5);
        cache.put(9, 10);
        testResult &= cache.get(13) == -1; // Expected output: -1
        cache.put(2, 19);
        testResult &= cache.get(2) == 19; // Expected output: 19
        testResult &= cache.get(3) == 17; // Expected output: 17
        cache.put(5, 25);
        testResult &= cache.get(8) == -1; // Expected output: -1
        cache.put(9, 22);
        cache.put(5, 5);
        cache.put(1, 30);
        testResult &= cache.get(11) == -1; // Expected output: -1
        cache.put(9, 12);
        testResult &= cache.get(7) == -1; // Expected output: -1
        testResult &= cache.get(5) == 5;  // Expected output: 5
        testResult &= cache.get(8) == -1; // Expected output: -1
        testResult &= cache.get(9) == 12; // Expected output: 12
        cache.put(4, 30);
        cache.put(9, 3);
        testResult &= cache.get(9) == 3;  // Expected output: 3
        testResult &= cache.get(10) == 5; // Expected output: 5
        testResult &= cache.get(10) == 5; // Expected output: 5
        cache.put(6, 14);
        cache.put(3, 1);
        testResult &= cache.get(3) == 1;  // Expected output: 1
        cache.put(10, 11);
        testResult &= cache.get(8) == -1; // Expected output: -1
        cache.put(2, 14);
        testResult &= cache.get(1) == 30; // Expected output: 30
        testResult &= cache.get(5) == 5;  // Expected output: 5
        testResult &= cache.get(4) == 30; // Expected output: 30
        cache.put(11, 4);
        cache.put(12, 24);
        testResult &= cache.get(5) == 5;  // Expected output: 5
        cache.put(13, 4);
        cache.put(8, 18);
        testResult &= cache.get(1) == 30; // Expected output: 30
        cache.put(7, 23);
        testResult &= cache.get(8) == 18; // Expected output: 18
        cache.put(12, 17);
        testResult &= cache.get(3) == -1; // Expected output: -1
        cache.put(13, 28);
        cache.put(11, 26);
        testResult &= cache.get(5) == 5;  // Expected output: 5
        cache.put(5, 29);
        cache.put(3, 4);
        testResult &= cache.get(11) == 26; // Expected output: 30
        testResult &= cache.get(4) == 30;  // Expected output: 30
        cache.put(7, 22);
        cache.put(13, 17);
        cache.put(2, 27);
        testResult &= cache.get(11) == 26; // Expected output: 26
        cache.put(2, 2);
        cache.put(7, 4);
        testResult &= cache.get(4) == 30;  // Expected output: 30
        testResult &= cache.get(11) == 26; // Expected output: 26

        System.out.println("\nTest result = " + (testResult ? "Pass" : "Fail"));

        return testResult;
    }

    private static boolean test4() {
        boolean testResult = true;
        LRUCache cache = new LRUCache(2);

        //[[2],[2],[2,6],[1],[1,5],[1,2],[1],[2]]
        //[null,-1,null,-1,null,null,2,6]
        testResult &= cache.get(2) == -1;
        cache.put(2, 6);
        testResult &= cache.get(1) == -1;
        cache.put(1, 5);
        cache.put(1, 2);
        testResult &= cache.get(1) == 2;
        testResult &= cache.get(2) == 6;


        System.out.println("\nTest result = " + (testResult ? "Pass" : "Fail"));

        return testResult;


    }


}


class LRUCache {

    private class Node {

        int key;
        int value;

        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }

    }

    private class Queue {

        int size;
        final int maxCapacity;
        Node head;
        Node tail;

        Queue(int capacity) {
            head = tail = null;
            this.maxCapacity = capacity;
            this.size = 0;
        }

        void add(Node node) {
            if (size == maxCapacity)
                return;
            if (node == null)
                return;

            size++;
            if (head == null) {
                head = tail = node;
                return;
            }

            node.next = head;
            head.prev = node;

            head = node;

            //cleanup
            node.prev = null;



        }


        private void removeHead(){
            Node next = head.next;

            //detach it
            head.next = null;
            if(next!=null)
                next.prev = null;

            head = next;

        }

        private void removeTail(){
            Node prev = tail.prev;

            if(prev!=null)
                prev.next = null;
            tail.prev = null;

            tail = prev;
        }

        private void removeBetween(Node node){
            Node next = node.next;
            Node prev = node.prev;

            prev.next = next;
            next.prev = prev;

            //cleanup
            node.next = null;
            node.prev = null;

        }
        void remove(Node node) {
            if (node == null)
                return;


            if (node == head){
                removeHead();
            }else if(node == tail){
                removeTail();
            }else {
                removeBetween(node);
            }

           size--;

        }

        boolean isEmpty() {
            return size == 0;
        }

        boolean isVacate() {
            if (isEmpty())
                return true;
            return size < maxCapacity;
        }

    }

    private final Queue queue; //doubly linked list head

    final private Map<Integer, Node> cache;

    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        queue = new Queue(capacity);
        cache = new HashMap<>(capacity);

    }


    public int get(int key) {

        Node item = cache.getOrDefault(key, null);
        if (item == null)
            return -1;

        // remove it from its position and add it to head to update its used frequency
        queue.remove(item);
        queue.add(item);

        return item.value;
    }

    public void put(int key, int value) {
        Node item = cache.get(key);

        //fresh item to be added
        if (item == null) {
            if (!queue.isVacate()) {
                cache.remove(queue.tail.key);
                queue.remove(queue.tail);

            }
            Node node = new Node(key, value);
            queue.add(node);
            cache.put(key, node);

        } else {
            item.value = value;
            queue.remove(item);
            queue.add(item);
        }

    }
}

class LRUUsingLinkedHashMap {

    private final LinkedHashMap<Integer, Integer> cache;
    LRUUsingLinkedHashMap(int capacity){
        cache = new LinkedHashMap<>(capacity, 0.75f, true){

            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }
        };
    }

    public int get(int key) {
        return cache.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        cache.put(key,value);
    }
}