package DataStructureAlgo.Java.LeetCode.caches;

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
            node.prev = null;
            head = node;

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

/**
 * {@link DataStructureAlgo.Java.MultiLevelCache.LRUCache}
 */
class LRUUsingLinkedHashMap {

}