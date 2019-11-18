package Java.LeetCode;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-23
 * Description: https://leetcode.com/problems/sort-characters-by-frequency/
 * Given a string, sort it in decreasing order based on the frequency of characters.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * "tree"
 * <p>
 * Output:
 * "eert"
 * <p>
 * Explanation:
 * 'e' appears twice while 'r' and 't' both appear once.
 * So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
 * Example 2:
 * <p>
 * Input:
 * "cccaaa"
 * <p>
 * Output:
 * "cccaaa"
 * <p>
 * Explanation:
 * Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
 * Note that "cacaca" is incorrect, as the same characters must be together.
 * Example 3:
 * <p>
 * Input:
 * "Aabb"
 * <p>
 * Output:
 * "bbAa"
 * <p>
 * Explanation:
 * "bbaA" is also a valid answer, but "Aabb" is incorrect.
 * Note that 'A' and 'a' are treated as two different characters.
 * <p>
 * https://leetcode.com/problems/sort-characters-by-frequency/discuss/365179/Blueprint-to-100-or-Optimisation-or-38-ms-to-2ms-or-5-Solution-or-optimisation-step-by-step
 * <p>
 * {@link Java.companyWise.intuit.SortStringBasedOnFrequencyAndLexical}
 */
public class SortCharactersByFrequency {

    public static void main(String[] args) {
        test("2a554442f544asfasssffffasss");
        test("tree");
        test("cccaaa");
        test("Aabb");

    }

    private static void test(String s) {
        System.out.println("\n input :" + s);

        ISortCharactersByFrequency mapAndObject = new SortCharactersByFrequencyMapAndObject();
        ISortCharactersByFrequency map = new SortCharactersByFrequencyMap();
        ISortCharactersByFrequency priorityQueue = new SortCharactersByMapPriorityQueue();
        ISortCharactersByFrequency bucketSort = new SortCharactersByBucketSort();
        ISortCharactersByFrequency frequencySearch = new SortCharactersByFrequencySearch();

        System.out.println("mapAndObject: " + mapAndObject.frequencySort(s));
        System.out.println("map: " + map.frequencySort(s));
        System.out.println("priorityQueue: " + priorityQueue.frequencySort(s));
        System.out.println("bucketSort: " + bucketSort.frequencySort(s));
        System.out.println("frequencySearch : " + frequencySearch.frequencySort(s));


    }

}

interface ISortCharactersByFrequency {
    String frequencySort(String s);
}

/**
 * Runtime: 38 ms, faster than 44.29% of Java online submissions for Sort Characters By Frequency.
 * Memory Usage: 39 MB, less than 85.19% of Java online submissions for Sort Characters By Frequency.
 * <p>
 * {@link Java.companyWise.intuit.SortStringBasedOnFrequencyAndLexical}
 * <p>
 * complexity:
 * Time: O(n) + O(m*log(m)) + (n) = O(n) since m = 256 at max so O(n) >>>> O(m*log(m))
 * Space: O(n)
 */
class SortCharactersByFrequencyMapAndObject implements ISortCharactersByFrequency {

    public String frequencySort(String s) {
        if (s == null || s.isEmpty())
            return s;

        class Freq {
            char c;
            int freq;

            public Freq(char c, int freq) {
                this.c = c;
                this.freq = freq;
            }
        }


        int n = s.length();
        Map<Character, Freq> freqMap = new HashMap<>();


        for (char c : s.toCharArray()) {
            if (!freqMap.containsKey(c))
                freqMap.put(c, new Freq(c, 1));
            else
                freqMap.get(c).freq++;
        }

        List<Freq> values = new ArrayList<>(freqMap.values());
        Collections.sort(values, ((o1, o2) -> o2.freq - o1.freq));

        char output[] = new char[n];
        int x = 0;

        for (Freq f : values) {
            int frequency = f.freq;
            while (frequency-- > 0)
                output[x++] = f.c;
        }

        return new String(output);
    }

}

/**
 * Runtime: 38 ms, faster than 44.29% of Java online submissions for Sort Characters By Frequency.
 * Memory Usage: 39 MB, less than 85.19% of Java online submissions for Sort Characters By Frequency.
 * <p>
 * {@link Java.companyWise.intuit.SortStringBasedOnFrequencyAndLexical}
 * <p>
 * Runtime: 45 ms, faster than 41.43% of Java online submissions for Sort Characters By Frequency.
 * Memory Usage: 38.3 MB, less than 96.30% of Java online submissions for Sort Characters By Frequency.
 * <p>
 * * complexity:
 * * Time: O(n) + O(m*log(m)) + (n) = O(n) since m = 256 at max so O(n) >>>> O(m*log(m))
 * * Space: O(n)
 */
class SortCharactersByFrequencyMap implements ISortCharactersByFrequency {


    @Override
    public String frequencySort(String s) {
        if (s == null || s.isEmpty())
            return s;
        int n = s.length();
        Map<Character, Integer> freqMap = new HashMap<>();


        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }


        char output[] = new char[n];
        int x = 0;

        final ArrayList<Map.Entry<Character, Integer>> entries = new ArrayList<>(freqMap.entrySet());
        Collections.sort(entries, ((o1, o2) -> o2.getValue() - o1.getValue()));

        for (Map.Entry<Character, Integer> entry : entries) {

            char c = entry.getKey();
            int freq = entry.getValue();

            while (freq-- > 0)
                output[x++] = c;
        }

        return new String(output);
    }

}

/**
 * Runtime: 38 ms, faster than 44.29% of Java online submissions for Sort Characters By Frequency.
 * Memory Usage: 39 MB, less than 85.19% of Java online submissions for Sort Characters By Frequency.
 * <p>
 * {@link Java.companyWise.intuit.SortStringBasedOnFrequencyAndLexical}
 * <p>
 * <p>
 * Details
 * Runtime: 45 ms, faster than 41.43% of Java online submissions for Sort Characters By Frequency.
 * Memory Usage: 38.7 MB, less than 88.89% of Java online submissions for Sort Characters By Frequency
 * <p>
 * * complexity:
 * * Time: O(n) + O(m*log(m)) + (n) = O(n) since m = 256 at max so O(n) >>>> O(m*log(m))
 * * Space: O(n)
 */
class SortCharactersByMapPriorityQueue implements ISortCharactersByFrequency {


    @Override
    public String frequencySort(String s) {
        if (s == null || s.isEmpty())
            return s;
        int n = s.length();
        Map<Character, Integer> freqMap = new HashMap<>();


        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }


        char output[] = new char[n];
        int x = 0;

        final PriorityQueue<Map.Entry<Character, Integer>> entries = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        entries.addAll(freqMap.entrySet());

        while (!entries.isEmpty()) {
            Map.Entry<Character, Integer> entry = entries.poll();

            char c = entry.getKey();
            int freq = entry.getValue();

            while (freq-- > 0)
                output[x++] = c;
        }

        return new String(output);
    }
}

/**
 * Runtime: 13 ms, faster than 88.95% of Java online submissions for Sort Characters By Frequency.
 * Memory Usage: 37.2 MB, less than 100.00% of Java online submissions for Sort Characters By Frequency.
 * <p>
 * * complexity:
 * * Time: O(n) + O(m) + (n) = O(n) since O(n) >>>> O(m) as m is 256 at max
 * * Space: O(n)
 */
class SortCharactersByBucketSort implements ISortCharactersByFrequency {
    @Override
    public String frequencySort(String s) {
        if (s == null || s.isEmpty())
            return s;
        int n = s.length();
        Map<Character, Integer> freqMap = new HashMap<>();


        //O(n)
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }


        /**
         * Create buckets of each char; Each index will represent how many times a char occurred.
         * for index [i], is tell the length of the character at it. Since many character can have same frequency
         * so we need a list
         * O(n * 256 ) => O(n)
         */

        List<Character>[] buckets = new List[s.length() + 1]; //as there will be a character whose frequency is 1

        /**
         * Fill the buckets
         * O(n)
         */
        for (Character c : freqMap.keySet()) {
            int freq = freqMap.get(c);

            if (buckets[freq] == null)
                buckets[freq] = new ArrayList<>();

            buckets[freq].add(c);
        }

        char output[] = new char[n];
        int x = 0;
        int freq = 0;
        /**
         * Fill the output
         * O(n * 256 ) => O(n)
         */
        for (int i = buckets.length - 1; i >= 0; i--) {

            if (buckets[i] != null) {

                for (char c : buckets[i]) {
                    freq = freqMap.get(c);

                    while (freq-- > 0)
                        output[x++] = c;
                }
            }
        }


        return new String(output);
    }
}

/**
 * Notice that total range of character is 256 only, that makes us to not to use sorting rather than simply finding using linear search
 * <p>
 * Runtime: 2 ms, faster than 100.00% of Java online submissions for Sort Characters By Frequency.
 * Memory Usage: 38.6 MB, less than 92.59% of Java online submissions for Sort Characters By Frequency.
 */
class SortCharactersByFrequencySearch implements ISortCharactersByFrequency {

    @Override
    public String frequencySort(String s) {
        if (s == null || s.isEmpty())
            return s;
        int n = s.length();

        int freq[] = new int[256];

        for (char c : s.toCharArray())
            freq[(int) c]++;


        char output[] = new char[n];
        int x = 0;

        int max;
        int index;
        while (x < n) {
            max = 0;
            index = -1;
            for (int i = 0; i < freq.length; i++) {

                if (max < freq[i]) {
                    max = freq[i];
                    index = i;
                }
            }
            //all elements are over
            if (max == 0) {
                return new String(output);
            }
            int temp = max;
            while (temp-- > 0)
                output[x++] = (char) index;

            freq[index] = 0; //nullify this character as it used.
        }


        return new String(output);
    }
}




