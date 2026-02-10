package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._981;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * {@link TimeBasedKeyValueStore.java}
 */
public class TimeBasedKeyValueStore_981 {
    
    class TimeMap {
        private Map<String, TreeMap<Integer, String>> map;
    
        public TimeMap() {
            map = new HashMap<>();
        }
    
        public void set(String key, String value, int timestamp) {
            map.computeIfAbsent(key, k -> new TreeMap<>()).put(timestamp, value);
        }
    
        public String get(String key, int timestamp) {
            if (!map.containsKey(key)) return "";
            
            // Finds the greatest key less than or equal to the given timestamp
            Map.Entry<Integer, String> entry = map.get(key).floorEntry(timestamp);
            
            return entry == null ? "" : entry.getValue();
        }
    }
}
