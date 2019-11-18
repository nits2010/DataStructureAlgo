package Java.LeetCode;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 14/09/19
 * Description: https://leetcode.com/problems/time-based-key-value-store/
 * 981. Time Based Key-Value Store [Medium]
 * <p>
 * Create a timebased key-value store class TimeMap, that supports two operations.
 * <p>
 * 1. set(string key, string value, int timestamp)
 * <p>
 * Stores the key and value, along with the given timestamp.
 * 2. get(string key, int timestamp)
 * <p>
 * Returns a value such that set(key, value, timestamp_prev) was called previously, with timestamp_prev <= timestamp.
 * If there are multiple such values, it returns the one with the largest timestamp_prev.
 * If there are no values, it returns the empty string ("").
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: inputs = ["TimeMap","set","get","get","set","get","get"], inputs = [[],["foo","bar",1],["foo",1],["foo",3],["foo","bar2",4],["foo",4],["foo",5]]
 * Output: [null,null,"bar","bar",null,"bar2","bar2"]
 * Explanation:
 * TimeMap kv;
 * kv.set("foo", "bar", 1); // store the key "foo" and value "bar" along with timestamp = 1
 * kv.get("foo", 1);  // output "bar"
 * kv.get("foo", 3); // output "bar" since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 ie "bar"
 * kv.set("foo", "bar2", 4);
 * kv.get("foo", 4); // output "bar2"
 * kv.get("foo", 5); //output "bar2"
 * <p>
 * Example 2:
 * <p>
 * Input: inputs = ["TimeMap","set","set","get","get","get","get","get"], inputs = [[],["love","high",10],["love","low",20],["love",5],["love",10],["love",15],["love",20],["love",25]]
 * Output: [null,null,null,"","high","high","low","low"]
 * <p>
 * <p>
 * Note:
 * <p>
 * All key/value strings are lowercase.
 * All key/value strings have length in the range [1, 100]
 * The timestamps for all TimeMap.set operations are strictly increasing.
 * 1 <= timestamp <= 10^7
 * TimeMap.set and TimeMap.get functions will be called a total of 120000 times (combined) per test case.
 * <p>
 * [Amazon]
 * https://leetcode.com/problems/time-based-key-value-store/discuss/381893/3-Solutions-or-Binary-or-Linear-fastest-or-Tree-or-Easy-to-understand-or-95-beat-or-Java
 */
public class TimeBasedKeyValueStore {

    public static void main(String[] args) {

        final ITimeMap timeMapUsingBinarySearch = new TimeMapUsingMapBinarySearch();
        final ITimeMap timeMapUsingTreeMap = new TimeMapUsingTreeMap();
        final ITimeMap timeMapUsingLinkedList = new TimeMapUsingMapBinarySearchLinkedList();

        System.out.println("\n TimeMapUsingMapBinarySearch Test 1");
        test1(timeMapUsingBinarySearch);

        System.out.println("\n TimeMapUsingTreeMap Test 1");
        test1(timeMapUsingTreeMap);

        System.out.println("\n TimeMapUsingMapBinarySearchLinkedList Test 1");
        test1(timeMapUsingLinkedList);

        System.out.println("\n TimeMapUsingMapBinarySearch Test 2");
        test2(timeMapUsingBinarySearch);

        System.out.println("\n TimeMapUsingTreeMap Test 2");
        test2(timeMapUsingTreeMap);

        System.out.println("\n TimeMapUsingMapBinarySearchLinkedList Test 2");
        test2(timeMapUsingLinkedList);


    }

    // * Input: inputs = ["TimeMap","set","set","get","get","get","get","get"],
    // inputs = [[],["love","high",10],["love","low",20],["love",5],["love",10],["love",15],["love",20],["love",25]]
    //      * Output: [null,null,null,"","high","high","low","low"]
    private static void test2(final ITimeMap timeMap) {
        //["love","high",10]
        timeMap.set("love", "high", 10);

        //["love","low",20]
        timeMap.set("love", "low", 20);

        //["love",5] -> ""
        System.out.println(" (love, 1) : " + timeMap.get("love", 5) + " expected : " + "");

        //["love",10] -> high
        System.out.println(" (love, 3) : " + timeMap.get("love", 10) + " expected : " + "high");

        //["love",15] -> high
        System.out.println(" (love, 4) : " + timeMap.get("love", 15) + " expected : " + "high");

        //["love",20] -> low
        System.out.println(" (love, 5) : " + timeMap.get("love", 20) + " expected : " + "low");

        //["love",25] -> low
        System.out.println(" (love, 5) : " + timeMap.get("love", 25) + " expected : " + "low");


    }

    //Input: inputs = ["TimeMap","set","get","get","set","get","get"],
    // inputs = [[],["foo","bar",1],["foo",1],["foo",3],["foo","bar2",4],["foo",4],["foo",5]]
    // * Output: [null,null,"bar","bar",null,"bar2","bar2"]
    private static void test1(final ITimeMap timeMap) {
        //["foo","bar",1]
        timeMap.set("foo", "bar", 1);

        //["foo",1] -> bar
        System.out.println(" (foo, 1) : " + timeMap.get("foo", 1) + " expected : " + "bar");

        //["foo",3] -> bar
        System.out.println(" (foo, 3) : " + timeMap.get("foo", 3) + " expected : " + "bar");

        //["foo","bar2",4]
        timeMap.set("foo", "bar2", 4);

        //["foo",4] -> bar2
        System.out.println(" (foo, 4) : " + timeMap.get("foo", 4) + " expected : " + "bar2");

        //["foo",5] -> bar2
        System.out.println(" (foo, 5) : " + timeMap.get("foo", 5) + " expected : " + "bar2");


    }
}

/**
 * Intuition
 * <p>
 * As we need to find the values corresponding to a key: HashMap would be the choice
 * We need to find those values whose timestampPrev <= timestamp. i.e. means we need to store all the values of a key of different timestamp.
 * Since we are looking timestampPrev <= timestamp then keeping those values sorted would make sense. Note, we don't need to maintain the sorted order as timestamp is
 * always in increasing order
 * Hence, we need HashMap<Key, List<Values>>.
 */
interface ITimeMap {

    /**
     * Stores the key and value, along with the given timestamp.
     *
     * @param key       key
     * @param value     value
     * @param timestamp timestamp
     */
    void set(String key, String value, int timestamp);

    /**
     * @param key       key
     * @param timestamp timestamp
     * @return * Returns a value such that set(key, value, timestamp_prev) was called previously, with timestamp_prev <= timestamp.
     * * If there are multiple such values, it returns the one with the largest timestamp_prev.
     * * If there are no values, it returns the empty string ("").
     */
    String get(String key, int timestamp);
}

/**
 * Algo 1: Do Binary Search on values
 * Runtime: 204 ms, faster than 82.02% of Java online submissions for Time Based Key-Value Store.
 * Memory Usage: 140.2 MB, less than 27.03% of Java online submissions for Time Based Key-Value Store.
 */
class TimeMapUsingMapBinarySearch implements ITimeMap {

    /**
     * Node holding data
     */
    private static class Node {

        final String value;
        final int timeStamp;

        public Node(String value, int timeStamp) {
            this.value = value;
            this.timeStamp = timeStamp;
        }
    }


    private final Map<String, List<Node>> timeMap;

    public TimeMapUsingMapBinarySearch() {
        this.timeMap = new HashMap<>();
    }

    //O(1)
    public void set(String key, String value, int timestamp) {

        if (!timeMap.containsKey(key))
            timeMap.put(key, new ArrayList<>());

        timeMap.get(key).add(new Node(value, timestamp));

    }

    //O(log(L)) ; L is the length of values in a key
    public String get(String key, int timestamp) {
        final String EMPTY_RESPONSE = "";
        if (!timeMap.containsKey(key))
            return EMPTY_RESPONSE;

        Node returnValue = binarySearch(timeMap.get(key), timestamp);
        return returnValue == null ? EMPTY_RESPONSE : returnValue.value;
    }


    /**
     * O(log(L))
     * Since we need to find timestamp_prev <= timestamp. then whenever we move right, cache the value you have seen as potential solution
     *
     * @param nodes     nodes
     * @param timeStamp timeStamp
     * @return {@code Node} when found otherwise null
     */
    private Node binarySearch(final List<Node> nodes, int timeStamp) {

        if (nodes.isEmpty())
            return null;


        int low = 0, high = nodes.size() - 1;
        Node returnValue = null;

        while (low <= high) {

            int mid = (high + low) >> 1;

            final Node current = nodes.get(mid);

            if (current.timeStamp == timeStamp)
                return returnValue = nodes.get(mid);

            else if (current.timeStamp > timeStamp)
                high = mid - 1;
            else {
                returnValue = current;
                low = mid + 1;
            }
        }

        return returnValue;
    }


    /**
     * Another way
     */
    protected String binarySearch1(List<Node> nodes, int time) {
        int low = 0, high = nodes.size() - 1;
        while (low < high) {
            int mid = (low + high) >> 1;
            final Node current = nodes.get(mid);

            if (current.timeStamp == time)
                return current.value;

            if (current.timeStamp < time) {

                if (nodes.get(mid + 1).timeStamp > time)
                    return current.value;

                low = mid + 1;
            } else
                high = mid - 1;
        }
        return nodes.get(low).timeStamp <= time ? nodes.get(low).value : "";
    }
}


/**
 * Algo 2: Do reverse search on values
 * Run through from end, this will make slight advantage as we may able to break the condition much before as compare to BinarySearch because of timestamp in increasing order
 * <p>
 * Runtime: 187 ms, faster than 93.87% of Java online submissions for Time Based Key-Value Store.
 * Memory Usage: 143.4 MB, less than 8.11% of Java online submissions for Time Based Key-Value Store.
 */
class TimeMapUsingMapBinarySearchLinkedList implements ITimeMap {

    /**
     * Node holding data
     */
    private static class Node {

        final String key;
        final String value;
        final int timeStamp;

        public Node(String key, String value, int timeStamp) {
            this.key = key;
            this.value = value;
            this.timeStamp = timeStamp;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key='" + key + '\'' +
                    ", value='" + value + '\'' +
                    ", timeStamp=" + timeStamp +
                    '}';
        }
    }


    private final Map<String, LinkedList<Node>> timeMap;

    public TimeMapUsingMapBinarySearchLinkedList() {
        this.timeMap = new HashMap<>();
    }

    //O(1)
    public void set(String key, String value, int timestamp) {

        if (!timeMap.containsKey(key))
            timeMap.put(key, new LinkedList<>());

        timeMap.get(key).add(new Node(key, value, timestamp));

    }

    //O(L) ; L is the length of values in a key
    public String get(String key, int timestamp) {

        final String EMPTY_RESPONSE = "";

        if (!timeMap.containsKey(key))
            return EMPTY_RESPONSE;

        //Run through from end, this will make slight advantage as we may able to break the condition much before as compare to BinarySearch because of timestamp in increasing order
        final Iterator<Node> iterator = timeMap.get(key).descendingIterator();

        while (iterator.hasNext()) {
            Node node = iterator.next();
            if (node.timeStamp > timestamp)
                continue;

            return node.value;
        }

        return EMPTY_RESPONSE;
    }


}


//243 ms
//Utilize java inbuilt search functionality using TreeMap
class TimeMapUsingTreeMap implements ITimeMap {


    //Holds the key vs tree map of timestamp vs values
    private final Map<String, TreeMap<Integer, String>> timeMap;

    public TimeMapUsingTreeMap() {
        this.timeMap = new HashMap<>();
    }

    //O(1)
    public void set(String key, String value, int timestamp) {

        if (!timeMap.containsKey(key))
            timeMap.put(key, new TreeMap<>());

        timeMap.get(key).put(timestamp, value);

    }

    //O(log(L)) ; L is the length of values in a key
    public String get(String key, int timestamp) {

        final String EMPTY_RESPONSE = "";

        if (!timeMap.containsKey(key))
            return EMPTY_RESPONSE;

        final Map.Entry<Integer, String> entry = timeMap.get(key).floorEntry(timestamp);
        return entry == null ? EMPTY_RESPONSE : entry.getValue();

    }


}
