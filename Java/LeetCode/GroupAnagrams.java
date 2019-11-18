package Java.LeetCode;


import Java.helpers.GenericPrinter;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 2019-09-02
 * Description: https://leetcode.com/problems/group-anagrams/
 * 49. Group Anagrams
 * <p>
 * Given an array of strings, group anagrams together.
 * <p>
 * Example:
 * <p>
 * Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Output:
 * [
 * ["ate","eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * Note:
 * <p>
 * All inputs will be in lowercase.
 * The order of your output does not matter.
 *
 * [Amazon] [Visa]
 */
public class GroupAnagrams {

    public static void main(String[] args) {
        test(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"},
                Arrays.asList(Arrays.asList("ate", "eat", "tea"), Arrays.asList("nat", "tan"), Arrays.asList("bat")));
    }

    private static void test(String[] strings, List<List<String>> expected) {
        System.out.println("\n Input :" + GenericPrinter.toString(strings) + " Expected :" + expected);
        GroupAnagramsSortCollapse sortCollapse = new GroupAnagramsSortCollapse();
        GroupAnagramsIndividualSortCollapse individualSortCollapse = new GroupAnagramsIndividualSortCollapse();
        GroupAnagramsIndividualHashCollapse hashCollapse = new GroupAnagramsIndividualHashCollapse();
        GroupAnagramsIndividualImprovedHashCollapse improvedHashCollapse = new GroupAnagramsIndividualImprovedHashCollapse();


        System.out.println(" sortCollapse: " + sortCollapse.groupAnagrams(strings));
        System.out.println(" individualSortCollapse: " + individualSortCollapse.groupAnagrams(strings));
        System.out.println(" hashCollapse: " + hashCollapse.groupAnagrams(strings));
        System.out.println(" improvedHashCollapse: " + improvedHashCollapse.groupAnagrams(strings));
    }
}

/**
 * Brute force
 * Algo:
 * 1. Sort given list alphabetically
 * 2. Sort individual word alphabetically
 * 3. Collapse the indexes
 * <p>
 * Complexity: O(n*L * log(L)) / L is max length of string given in array
 * space: O(n)
 * <p>
 * Runtime: 51 ms, faster than 8.37% of Java online submissions for Group Anagrams.
 * Memory Usage: 44.5 MB, less than 42.10% of Java online submissions for Group Anagrams.
 */
class GroupAnagramsSortCollapse {

    public List<List<String>> groupAnagrams(String[] strs) {

        if (strs == null || strs.length == 0)
            return Collections.EMPTY_LIST;

        class Anagrams {
            String s;
            int index;

            public Anagrams(String s, int index) {
                this.s = s;
                this.index = index;
            }


        }

        //=======Sort given list alphabetically
        List<Anagrams> anagrams = new ArrayList<>(strs.length);

        //O(n) / O(n)
        for (int i = 0; i < strs.length; i++)
            anagrams.add(new Anagrams(strs[i], i));

        //O(n*log(n)) / O(n)
        Collections.sort(anagrams, Comparator.comparing(o -> o.s));

        //=======Sort individual word alphabetically & Collapse the indexes
        Map<String, List<String>> collapse = new HashMap<>();

        //O(n*L * log(L)) / O(n)
        for (Anagrams anagram : anagrams) {
            char an[] = anagram.s.toCharArray();

            //O(L * log(L))
            Arrays.sort(an);

            String ans = new String(an);
            if (!collapse.containsKey(ans))
                collapse.put(ans, new ArrayList<>());

            collapse.get(ans).add(strs[anagram.index]);

        }

        ////O(n) / O(n)
        List<List<String>> response = new ArrayList<>();
        for (List<String> s : collapse.values())
            response.add(s);

        return response;


    }


}


/**
 * Improved Brute force
 *
 * Avoid step 1 of above algo, and do same
 * Algo:
 * 1. Sort individual word alphabetically
 * 2. Collapse the indexes
 * <p>
 * Complexity: O(n * L * log(L)) L is max length of string given in array
 * Space: O(n) ;
 * Runtime: 9 ms, faster than 79.45% of Java online submissions for Group Anagrams.
 * Memory Usage: 41.3 MB, less than 94.74% of Java online submissions for Group Anagrams.
 */
class GroupAnagramsIndividualSortCollapse {


    public List<List<String>> groupAnagrams(String[] anagrams) {

        if (anagrams == null || anagrams.length == 0)
            return Collections.EMPTY_LIST;


        //=======Sort individual word alphabetically & Collapse the indexes
        Map<String, List<String>> collapse = new HashMap<>();

        //O(n*L * log(L)) / O(n)
        for (String anagram : anagrams) {
            char an[] = anagram.toCharArray();

            //O(L * log(L))
            Arrays.sort(an);

            String ans = new String(an);
            if (!collapse.containsKey(ans))
                collapse.put(ans, new ArrayList<>());

            collapse.get(ans).add(anagram);

        }

        return new ArrayList(collapse.values());


    }


}


/**
 * Optimizations : 1
 * Avoid step 1 of above algo, and do same
 * Algo:
 * 1. Hash individual word alphabetically: convert count array to string
 * 2. Collapse the indexes
 * <p>
 * Complexity: O(n * L * 26) L is max length of string given in array
 * Space: O(n) ;
 * Runtime: 13 ms, faster than 33.70% of Java online submissions for Group Anagrams.
 * Memory Usage: 42.2 MB, less than 91.81% of Java online submissions for Group Anagrams.
 */
class GroupAnagramsIndividualHashCollapse {


    public List<List<String>> groupAnagrams(String[] anagrams) {

        if (anagrams == null || anagrams.length == 0)
            return Collections.EMPTY_LIST;


        //=======hash individual word alphabetically & Collapse the indexes
        Map<String, List<String>> collapse = new HashMap<>();

        //O(n*(L+26)) / O(n) => O(n*L) / O(n)
        for (String anagram : anagrams) {


            String hash = hash(anagram);
            if (!collapse.containsKey(hash))
                collapse.put(hash, new ArrayList<>());

            collapse.get(hash).add(anagram);

        }

        return new ArrayList(collapse.values());


    }

    //O(L + 26)
    private String hash(String anagram) {
        int an[] = new int[26];
        for (char c : anagram.toCharArray())
            an[c - 'a']++;

        StringBuilder hash = new StringBuilder();
        for (int i : an)
            hash.append(i + '$');

        return hash.toString();

    }


}


/**
 * Optimizations : 2
 * Improvement of Step 1 in above algo
 * Algo:
 * 1. Hash individual word alphabetically: convert count array to prime key
 * 2. Collapse the indexes
 * <p>
 * Complexity: O(n * L) L is max length of string given in array
 * Space: O(n) ;
 * Runtime: 7 ms, faster than 99.52% of Java online submissions for Group Anagrams.
 * Memory Usage: 41.8 MB, less than 94.15% of Java online submissions for Group Anagrams.
 */
class GroupAnagramsIndividualImprovedHashCollapse {

    //first 26 prime numbers
    final int[] prime = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103};

    public List<List<String>> groupAnagrams(String[] anagrams) {

        if (anagrams == null || anagrams.length == 0)
            return Collections.EMPTY_LIST;


        //=======hash individual word alphabetically & Collapse the indexes
        Map<Integer, List<String>> collapse = new HashMap<>();

        //O(n*(L+26)) / O(n) => O(n*L) / O(n)
        for (String anagram : anagrams) {


            int hash = hash(anagram);
            if (!collapse.containsKey(hash))
                collapse.put(hash, new ArrayList<>());

            collapse.get(hash).add(anagram);

        }

        return new ArrayList(collapse.values());


    }

    //O(L)
    private int hash(String anagram) {
        int hashKey = 1;
        for (char c : anagram.toCharArray())
            hashKey *= prime[c - 'a'];

        return hashKey;

    }


}