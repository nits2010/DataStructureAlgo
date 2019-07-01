package Java.LeetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
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

    public static void main(String args[]) {
        AllOne allOne = new AllOne();
        allOne.inc("");
        allOne.dec("");
        allOne.getMaxKey();
        allOne.getMinKey();
    }
}

class AllOne {

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

    // head: used as handle
    Node head;

    // tail: used as handle
    Node tail;

    // key -> Node
    Map<String, Node> keyMap;

    // count -> Node
    Map<Integer, Node> indexMap;

    /**
     * Initialize your data structure here.
     */
    public AllOne() {
        keyMap = new HashMap<>();
        indexMap = new HashMap<>();
        head = new Node(-1);
        tail = new Node(-1);
        head.next = tail;
        tail.pre = head;
    }

    /**
     * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
     */
    public void inc(String key) {
        int newCount = !keyMap.containsKey(key) ? 1 : keyMap.get(key).val + 1;
        Node oldNode = !keyMap.containsKey(key) ? tail : keyMap.get(key);

        if (!indexMap.containsKey(newCount)) {
            Node newNode = new Node(newCount);
            indexMap.put(newCount, newNode);
            insertBefore(newNode, oldNode);
        }

        indexMap.get(newCount).keys.add(key);
        keyMap.put(key, indexMap.get(newCount));
        oldNode.keys.remove(key);
        refreshNode(oldNode);
    }

    /**
     * Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
     */
    public void dec(String key) {
        if (!keyMap.containsKey(key)) return;

        Node oldNode = keyMap.get(key);
        if (oldNode.val == 1) {
            keyMap.remove(key);
        } else {
            int newCount = oldNode.val - 1;
            if (!indexMap.containsKey(newCount)) {
                Node newNode = new Node(newCount);
                indexMap.put(newCount, newNode);
                insertAfter(newNode, oldNode);
            }
            indexMap.get(newCount).keys.add(key);
            keyMap.put(key, indexMap.get(newCount));
        }
        oldNode.keys.remove(key);
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
            indexMap.remove(node.val);
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

