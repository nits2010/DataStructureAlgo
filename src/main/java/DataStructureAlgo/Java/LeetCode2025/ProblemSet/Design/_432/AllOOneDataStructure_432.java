package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Design._432;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 9/29/2024
 * Question Category: 432. All O`one Data Structure
 * Description: https://leetcode.com/problems/all-oone-data-structure/description/
 * Design a data structure to store the strings' count with the ability to return the strings with minimum and maximum counts.
 *
 * Implement the AllOne class:
 *
 * AllOne() Initializes the object of the data structure.
 * inc(String key) Increments the count of the string key by 1. If key does not exist in the data structure, insert it with count 1.
 * dec(String key) Decrements the count of the string key by 1. If the count of key is 0 after the decrement, remove it from the data structure. It is guaranteed that key exists in the data structure before the decrement.
 * getMaxKey() Returns one of the keys with the maximal count. If no element exists, return an empty string "".
 * getMinKey() Returns one of the keys with the minimum count. If no element exists, return an empty string "".
 * Note that each function must run in O(1) average time complexity.
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"]
 * [[], ["hello"], ["hello"], [], [], ["leet"], [], []]
 * Output
 * [null, null, null, "hello", "hello", null, "hello", "leet"]
 *
 * Explanation
 * AllOne allOne = new AllOne();
 * allOne.inc("hello");
 * allOne.inc("hello");
 * allOne.getMaxKey(); // return "hello"
 * allOne.getMinKey(); // return "hello"
 * allOne.inc("leet");
 * allOne.getMaxKey(); // return "hello"
 * allOne.getMinKey(); // return "leet"
 *
 *
 * Constraints:
 *
 * 1 <= key.length <= 10
 * key consists of lowercase English letters.
 * It is guaranteed that for each call to dec, key is existing in the data structure.
 * At most 5 * 104 calls will be made to inc, dec, getMaxKey, and getMinKey.
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.AllConstantTimeDataStructureInsertDeleteMinMax}
 * Similar {@link}
 * extension {@link }
 * <p><p>
 * Tags
 * -----
 * @HashTable
 * @LinkedList
 * @Design
 * @Doubly-LinkedList
 * @hard
 *
 * <p><p>
 * Company Tags
 * -----
 * @LinkedIn
 * @Amazon
 * @VMware
 * @Facebook
 * @Google
 *
 *
 * @Editorial https://leetcode.com/problems/all-oone-data-structure/solutions/5853466/o-1-solution-two-solution-average-o-1-and-strict-o-1/
 */
public class AllOOneDataStructure_432 {

    public static void main(String[] args) {
        boolean test = true;

        test &= test(new String[]{"AllOne","inc","inc","getMaxKey","getMinKey","dec","dec","getMinKey","inc","getMinKey"},
                new String[]{"hello","hello","hello","hello","hello"},
                new String[]{null,null,null,"hello","hello",null,null,"",null,"hello"});

        test &= test(new String[]{"AllOne","inc","inc","inc","inc","inc","inc","getMaxKey","inc","dec","getMaxKey","dec","inc","getMaxKey","inc","inc","dec","dec","dec","dec","getMaxKey","inc","inc","inc","inc","inc","inc","getMaxKey","getMinKey"},
                new String[]{"hello","world","leet","code","ds","leet","ds","leet","ds","hello","hello","hello","world","leet","code","ds","new","new","new","new","new","new"},
                new String[]{null,null,null,null,null,null,null,"leet",null,null,"ds",null,null,"hello",null,null,null,null,null,null,"hello",null,null,null,null,null,null,"new","hello"});

        test &= test(new String[]{"AllOne","inc","inc","inc","inc","inc","dec","dec","getMaxKey","getMinKey"},
                new String[]{"a", "b", "b", "b", "b", "b", "b"},
                new String[]{null,null,null,null,null,null,null,null,"b","a"});


        test &= test(new String[]{"AllOne","inc","inc","inc","inc","inc","inc","inc","inc","inc","inc","dec","dec","getMaxKey"},
                new String[]{"hello", "l", "l", "l","k", "k", "k", "j", "j", "j", "j","k"},
                new String[]{null,null,null,null,null,null,null,null,null,null,null,null,null,"l"});

        test &= test(new String[]{"AllOne", "inc","inc","inc","inc","inc","inc","inc","inc","inc","inc","inc","inc","getMinKey"},
                new String[]{"a","b" ,"c" ,"d" ,"a" ,"b" ,"c" ,"d" ,"c" ,"d" ,"d" ,"a"},
                new String[]{null,null,null,null,null,null,null,null,null,null,null,null,null,"b"});
        test &= test(new String[]{"AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"},
                new String[]{"hello", "hello", "leet"},
                new String[]{null, null, null, "hello", "hello", null, "hello", "leet"});

        CommonMethods.printResult(test);
    }

    private static boolean test(String[] operations, String[] keys, String[] expected) {

        System.out.println("-----------------------------------------------------------");
        System.out.println(" Operations : " + Arrays.toString(operations) + " keys : " + Arrays.toString(keys)
                + "\n expected : " + Arrays.toString(expected));
        String[] result;
        boolean pass;
        boolean finalPass = true;

        IAllOne allOneAverageConstant = new AllOne_AverageConstant();
        result = test(operations, keys, allOneAverageConstant);
        pass = Arrays.deepEquals(result, expected);
        System.out.println(" Result : "+ Arrays.toString(result) + " pass : " + (pass ? "Pass" : "Fail"));
        finalPass &= pass;


        return finalPass;

    }

    private static String[] test(String[] operations, String[] keys, IAllOne allOne) {
        int resultIndex = 0;
        int keyIndex = 0;

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
                result[resultIndex] = max;

            } else if ("getMinKey".equals(operation)) {

                String min = allOne.getMinKey();
                result[resultIndex] = min;

            }
            resultIndex++;
        }

        return result;

    }


}

interface IAllOne {
    void inc(String key);
    void dec(String key);
    String getMaxKey();
    String getMinKey();
}

class AllOne_AverageConstant implements IAllOne {

    static class ListNode {
        String data;
        ListNode prev, next;
        int count;

        ListNode(String d) {
            this.data = d;
            prev = next = null;
            count = 1;
        }

    }

    final Map<String, ListNode> keyVsNodeMap;
    ListNode head, tail;

    public AllOne_AverageConstant() {
        keyVsNodeMap = new HashMap<>();
        head = tail = null;
    }

    public void inc(String key) {

        ListNode node= keyVsNodeMap.getOrDefault(key, null);

        //new key, add it tail
        if(node == null){
            add(key);
            keyVsNodeMap.put(key, tail);
        }else {

            //update count and refresh node
            node.count++;

            //care head

            if(node !=head && node.count >= head.count){
                remove(node); //remove this node from its position
                addToHead(node); //add it to head
            }

            //care tail

            //if this node were tail, it needs to push back to its right sorted order
            if(node == tail){
                ListNode temp = node.prev;
                while (temp!=null && temp!=head ){
                    if(temp.count <= node.count)
                        temp = temp.prev;
                    else
                        break;

                }
                if (temp != null) {
                    remove(node);
                    insertAfter(temp, node);
                }

            }
        }


    }

    public void dec(String key) {
        ListNode node= keyVsNodeMap.getOrDefault(key, null);

        if(node==null)
            return;

        node.count -= 1;

        if(node.count == 0){
            remove(node);
            keyVsNodeMap.remove(key);
        }else {
            //update list

            //care head
            if(node == head){
                //head may have lost its position, update head and node position
                ListNode temp = head.next;
                while (temp!=null && temp!=tail){
                    if(temp.count >= node.count)
                        temp = temp.next;
                    else
                        break;
                }
                if(temp!=null){
                    remove(node);
                    insertBefore(temp, node);
                }

            }


            //care tail
            //post decreasing count further at tail, won't change anything, hence no update needed.
        }
    }

    public String getMaxKey() {
        if(head!=null)
            return head.data;
        return "";
    }

    public String getMinKey() {
        if(tail!=null)
            return tail.data;
        return "";
    }

    //-----------------------------


    //Add key to tail
    private void add(String key) {

        ListNode node = new ListNode(key);

        if(head == null)
            head = tail = node;
        else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
    }

    //remove key
    private void remove(ListNode node){
        ListNode prev = node.prev;
        ListNode next = node.next;

        if(prev!=null){
            prev.next = next;
        }

        if(next!=null){
            next.prev = prev;
        }

        if(head == node){
            head = head.next;
        }

        if (node == tail) {
            tail = tail.prev;
        }
    }

    private void addToHead(ListNode node){

        //if a list is empty
        if(head == null)
            head = tail = node;
        else {
            node.next = head;
            head.prev = node;
            head = node; //updated head
        }
    }

    private void insertAfter(ListNode prev, ListNode node){
        if(prev!=null){
            ListNode next = prev.next;
            prev.next = node;
            node.prev = prev;
            node.next = next;
            if(next!=null)
                next.prev = node;

            if(prev == tail)
                tail = node;

        }
    }

    private void insertBefore(ListNode next, ListNode node){
        if(next!=null){
            ListNode prev = next.prev;
            next.prev  = node;
            node.next = next;
            node.prev = prev;
            if(prev!=null)
                prev.next = node;

            if(next == head)
                head = node;
        }
    }
}

//TODO: yet to do
class AllOne_StrictConstant implements IAllOne {

    class ListNode {
        int count;

        Set<String> keys;

        ListNode prev, next;

        public ListNode(String key){
            this.count = 1;
            this.keys = new HashSet<>();
            this.keys.add(key);
        }
    }

    final Map<String , ListNode> keyVsNodeMap;
    final Map<Integer, ListNode> countVsNodeMap;

    //head points maxKey and tail points minKey
    ListNode head, tail;

    public AllOne_StrictConstant(){
        head = tail = null;
        keyVsNodeMap = new HashMap<>();
        countVsNodeMap = new HashMap<>();
    }

    @Override
    public void inc(String key) {

        ListNode node = keyVsNodeMap.getOrDefault(key, null);

        if(node == null){
            node = new ListNode(key);
            keyVsNodeMap.put(key, node);
            countVsNodeMap.put(1, node);
//            addToHead(node);
        }else{

            //update map's

            //update count map, remove key from old count
            countVsNodeMap.get(node.count).keys.remove(key);
            if(countVsNodeMap.get(node.count).keys.isEmpty()){
                countVsNodeMap.remove(node.count);
            }

            node.count++;

            //update count map, add key to new count
            countVsNodeMap.putIfAbsent(node.count, node);
            countVsNodeMap.get(node.count).keys.add(key);


            //update list
            if(node !=head && node.count >= head.count){
//                remove(node); //remove this node from its position
//                addToHead(node); //add it to head
            }

        }
    }

    @Override
    public void dec(String key) {

    }

    @Override
    public String getMaxKey() {
        return null;
    }

    @Override
    public String getMinKey() {
        return null;
    }
}