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

    /**
     * DLL used to return Max (from Head) or Min(tail)
     */
    class Node {
        int val;
        Set<String> keys;
        Node pre;
        Node next;

        public Node(int a) {
            val = a;
            keys = new HashSet<>();
        }
    }

    // head: used as the handle
    Node head;

    // tail: used as the handle
    Node tail;

    // key -> Node
    Map<String, Node> keyVsNodeMap;

    // count -> Node
    Map<Integer, Node> countVsNodeMap;

    /**
     * Initialize your data structure here.
     */
    public AllOne() {
        keyVsNodeMap = new HashMap<>();
        countVsNodeMap = new HashMap<>();
        head = new Node(-1);
        tail = new Node(-1);
        head.next = tail;
        tail.pre = head;
    }

    /**
     * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
     */
    public void inc(String key) {
        int newCount = !keyVsNodeMap.containsKey(key) ? 1 : keyVsNodeMap.get(key).val + 1;
        Node oldNode = !keyVsNodeMap.containsKey(key) ? tail : keyVsNodeMap.get(key);

        /**
         * If this is a new count, then add to countMap and at the tail.
         */
        if (!countVsNodeMap.containsKey(newCount)) {
            Node newNode = new Node(newCount);
            countVsNodeMap.put(newCount, newNode);
            insertBefore(newNode, oldNode);
        }

        /**
         * update keys for this count
         */
        countVsNodeMap.get(newCount).keys.add(key);
        /**
         * Push the node to key map
         */
        keyVsNodeMap.put(key, countVsNodeMap.get(newCount));
        /**
         * Remove keys from old node, as the count has been changed
         */
        oldNode.keys.remove(key);

        /**
         * Modify DLL accordingly
         */
        refreshNode(oldNode);
    }

    /**
     * Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
     */
    public void dec(String key) {
        if (!keyVsNodeMap.containsKey(key)) return;

        /**
         * If key exist, we need to reduce the count
         */
        Node oldNode = keyVsNodeMap.get(key);

        /**
         * if key has only one count, then remove it completely
         */
        if (oldNode.val == 1) {
            keyVsNodeMap.remove(key);
        } else {
            /**
             * If key has more than 1 count, decrease hte count
             */
            int newCount = oldNode.val - 1;

            /**
             * If this count seen first time, add it and insert after old count node, as hte count has reduced so it will be
             * towards tail
             */
            if (!countVsNodeMap.containsKey(newCount)) {
                Node newNode = new Node(newCount);
                countVsNodeMap.put(newCount, newNode);
                insertAfter(newNode, oldNode);
            }
            /**
             * update keys for this count
             */
            countVsNodeMap.get(newCount).keys.add(key);
            /**
             * Push the node to key map
             */
            keyVsNodeMap.put(key, countVsNodeMap.get(newCount));
        }
        /**
         * Remove keys from old node, as the count has been changed
         */
        oldNode.keys.remove(key);


        /**
         * Modify DLL accordingly
         */
        refreshNode(oldNode);
    }

    /**
     * Returns one of the keys with maximal value.
     */
    public String getMaxKey() {
        return head.next == tail ? "" : head.next.keys.iterator().next();
    }

    /**
     * Returns one of the keys with Minimal value.
     */
    public String getMinKey() {
        return tail.pre == head ? "" : tail.pre.keys.iterator().next();
    }

    // if node.keys is empty, remove this node
    void refreshNode(Node node) {
        if (node == head || node == tail)
            return;
        if (node.keys.size() == 0) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
            countVsNodeMap.remove(node.val);
        }
    }

    void insertBefore(Node newNode, Node ref) {
        newNode.pre = ref.pre;
        newNode.next = ref;
        newNode.pre.next = newNode;
        newNode.next.pre = newNode;
    }

    void insertAfter(Node newNode, Node ref) {
        newNode.next = ref.next;
        newNode.pre = ref;
        newNode.pre.next = newNode;
        newNode.next.pre = newNode;
    }
}

