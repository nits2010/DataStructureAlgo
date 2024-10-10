package DataStructureAlgo.Java.LeetCode;

import DataStructureAlgo.Java.LeetCode2025.ProblemSet.Design.AllOOneDataStructure_432;
import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-01
 * Description:
 * https://leetcode.com/problems/all-oone-data-structure/
 * <p>
 * Implement a data structure supporting the following operations:
 * <p>
 * Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1. Key is guaranteed to be a non-empty string.
 * Dec(Key) - If Key's value is 1, remove it from the data structure. Otherwise decrements an existing key by 1. If the key does not exist, this function does nothing. Key is guaranteed to be a non-empty string.
 * GetMaxKey() - Returns one of the keys with maximal value. If no element exists, return an empty string "".
 * GetMinKey() - Returns one of the keys with minimal value. If no element exists, return an empty string "".
 * Challenge: Perform all these in O(1) time complexity.
 */
public class AllConstantTimeDataStructureInsertDeleteMinMax {


    public static void main(String[] args) {
        boolean test = true;
        test &= test1(new String[]{"AllOne","inc","inc","getMaxKey","getMinKey","dec","dec","getMinKey","inc","getMinKey"},
                new String[]{"hello","hello","hello","hello","hello"},
                new String[]{null,null,null,"hello","hello",null,null,"",null,"hello"});

        test &= test1(new String[]{"AllOne", "inc","inc","inc","inc","inc","inc","inc","inc","inc","inc","inc","inc","getMinKey"},
                new String[]{"a","b" ,"c" ,"d" ,"a" ,"b" ,"c" ,"d" ,"c" ,"d" ,"d" ,"a"},
                new String[]{null,null,null,null,null,null,null,null,null,null,null,null,null,"b"});

        CommonMethods.printResult(test);
    }

    private static boolean test1(String[] operations, String[] keys, String[] expected) {

        System.out.println("-----------------------------------------------------------");
        System.out.println(" Operations : " + Arrays.toString(operations) + " keys : " + Arrays.toString(keys)
                + "\n expected : " + Arrays.toString(expected));

        int resultIndex = 0;
        int keyIndex = 0;
        AllOne allOne = new AllOne();
        String[] result = new String[operations.length];
        result[resultIndex] = null;

        for (String operation : operations) {

            if ("inc".equals(operation)) {

                allOne.inc(keys[keyIndex++]);
                result[resultIndex] = null;

            } else if ("dec".equals(operation)) {

                allOne.dec(keys[keyIndex++]);
                result[resultIndex] = null;

            } else if ("getMaxKey".equals(operation)) {

                String max = allOne.getMaxKey();
                System.out.println(" getMaxKey : " + max);
                result[resultIndex] = max;

            } else if ("getMinKey".equals(operation)) {

                String min = allOne.getMinKey();
                System.out.println(" getMinKey : " + min);
                result[resultIndex] = min;

            }
            resultIndex++;
        }

        boolean pass = Arrays.deepEquals(result, expected);

        System.out.println(" Result : "+ Arrays.toString(result) + " pass : " + (pass ? "Pass" : "Fail"));
        return pass;

    }
}

class AllOne {

    // Node class for doubly linked list (DLL), storing count and associated keys
    static class Node {
        int count;
        Set<String> keys;
        Node prev, next;

        public Node(int count) {
            this.count = count;
            this.keys = new HashSet<>();
        }
    }

    // Head and tail of the doubly linked list
    private final Node head;
    private final Node tail;

    // Maps to track the node corresponding to each key and count
    private final Map<String, Node> keyToNodeMap;
    private final Map<Integer, Node> countToNodeMap;

    // Constructor
    public AllOne() {
        keyToNodeMap = new HashMap<>();
        countToNodeMap = new HashMap<>();
        head = new Node(-1); // Dummy head node
        tail = new Node(-1); // Dummy tail node
        head.next = tail;
        tail.prev = head;
    }

    // Increment a key's value by 1
    public void inc(String key) {
        int newCount = keyToNodeMap.containsKey(key) ? keyToNodeMap.get(key).count + 1 : 1;
        Node currentNode = keyToNodeMap.getOrDefault(key, tail);

        // If new count is not present, create a new node
        if (!countToNodeMap.containsKey(newCount)) {
            Node newNode = new Node(newCount);
            countToNodeMap.put(newCount, newNode);
            insertBefore(newNode, currentNode);
        }

        // Update mappings and node keys
        countToNodeMap.get(newCount).keys.add(key);
        keyToNodeMap.put(key, countToNodeMap.get(newCount));

        // Remove key from old node and refresh its position
        currentNode.keys.remove(key);
        refreshNode(currentNode);
    }

    // Decrement a key's value by 1 or remove it if its value becomes 0
    public void dec(String key) {
        if (!keyToNodeMap.containsKey(key)) return;

        Node currentNode = keyToNodeMap.get(key);
        int newCount = currentNode.count - 1;

        if (newCount == 0) {
            keyToNodeMap.remove(key); // Remove key if count becomes 0
        } else {
            // If new count is not present, create a new node
            if (!countToNodeMap.containsKey(newCount)) {
                Node newNode = new Node(newCount);
                countToNodeMap.put(newCount, newNode);
                insertAfter(newNode, currentNode);
            }

            // Update mappings and node keys
            countToNodeMap.get(newCount).keys.add(key);
            keyToNodeMap.put(key, countToNodeMap.get(newCount));
        }

        // Remove key from old node and refresh its position
        currentNode.keys.remove(key);
        refreshNode(currentNode);
    }

    // Returns one of the keys with the maximal value
    public String getMaxKey() {
        return head.next == tail ? "" : head.next.keys.iterator().next();
    }

    // Returns one of the keys with the minimal value
    public String getMinKey() {
        return tail.prev == head ? "" : tail.prev.keys.iterator().next();
    }

    // Remove node if it becomes empty
    private void refreshNode(Node node) {
        if (node != head && node != tail && node.keys.isEmpty()) {
            //remove this node
            node.prev.next = node.next;
            node.next.prev = node.prev;

            countToNodeMap.remove(node.count);
        }
    }

    // Insert a new node before a reference node
    private void insertBefore(Node newNode, Node refNode) {
        newNode.prev = refNode.prev;
        newNode.next = refNode;
        refNode.prev.next = newNode;
        refNode.prev = newNode;
    }

    // Insert a new node after a reference node
    private void insertAfter(Node newNode, Node refNode) {
        newNode.next = refNode.next;
        newNode.prev = refNode;
        refNode.next.prev = newNode;
        refNode.next = newNode;
    }
}
