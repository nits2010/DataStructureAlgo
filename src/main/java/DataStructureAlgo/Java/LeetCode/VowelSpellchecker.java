package DataStructureAlgo.Java.LeetCode;


import  DataStructureAlgo.Java.helpers.GenericPrinter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Nitin Gupta
 * Date: 15/09/19
 * Description: https://leetcode.com/problems/vowel-spellchecker/
 * 966. Vowel Spellchecker [Medium]
 * Given a wordlist, we want to implement a spellchecker that converts a query word into a correct word.
 * <p>
 * For a given query word, the spell checker handles two categories of spelling mistakes:
 * <p>
 * Capitalization: If the query matches a word in the wordlist (case-insensitive), then the query word is returned with the same case as the case in the wordlist.
 * Example: wordlist = ["yellow"], query = "YellOw": correct = "yellow"
 * Example: wordlist = ["Yellow"], query = "yellow": correct = "Yellow"
 * Example: wordlist = ["yellow"], query = "yellow": correct = "yellow"
 * Vowel Errors: If after replacing the vowels ('a', 'e', 'i', 'o', 'u') of the query word with any vowel individually, it matches a word in the wordlist (case-insensitive), then the query word is returned with the same case as the match in the wordlist.
 * Example: wordlist = ["YellOw"], query = "yollow": correct = "YellOw"
 * Example: wordlist = ["YellOw"], query = "yeellow": correct = "" (no match)
 * Example: wordlist = ["YellOw"], query = "yllw": correct = "" (no match)
 * In addition, the spell checker operates under the following precedence rules:
 * <p>
 * When the query exactly matches a word in the wordlist (case-sensitive), you should return the same word back.
 * When the query matches a word up to capitlization, you should return the first such match in the wordlist.
 * When the query matches a word up to vowel errors, you should return the first such match in the wordlist.
 * If the query has no matches in the wordlist, you should return the empty string.
 * Given some queries, return a list of words answer, where answer[i] is the correct word for query = queries[i].
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: wordlist = ["KiTe","kite","hare","Hare"], queries = ["kite","Kite","KiTe","Hare","HARE","Hear","hear","keti","keet","keto"]
 * Output: ["kite","KiTe","KiTe","Hare","hare","","","KiTe","","KiTe"]
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= wordlist.length <= 5000
 * 1 <= queries.length <= 5000
 * 1 <= wordlist[i].length <= 7
 * 1 <= queries[i].length <= 7
 * All strings in wordlist and queries consist only of english letters.
 *
 * [Amazon]
 */
public class VowelSpellchecker {
    public static void main(String[] args) {
        test(new String[]{"KiTe", "kite", "hare", "Hare"}, new String[]{"kite", "Kite", "KiTe", "Hare", "HARE", "Hear", "hear", "keti", "keet", "keto"}, new String[]{"kite", "KiTe", "KiTe", "Hare", "hare", "", "", "KiTe", "", "KiTe"});
        test(new String[]{"ae", "aa"}, new String[]{"UU"}, new String[]{"ae"});
    }

    private static void test(String[] wordList, String[] queries, String[] expected) {
        System.out.println("\nWordlist :" + GenericPrinter.toString(wordList) + "\nQuries :" + GenericPrinter.toString(queries) + "\nExpected :" + GenericPrinter.toString(expected));
        VowelSpellcheckerUsingMap usingMap = new VowelSpellcheckerUsingMap();
        VowelSpellcheckerUsingMap2 usingMap2 = new VowelSpellcheckerUsingMap2();
        System.out.println("usingMap  :" + GenericPrinter.toString(usingMap.spellchecker(wordList, queries)));
        System.out.println("usingMap2 :" + GenericPrinter.toString(usingMap2.spellchecker(wordList, queries)));
    }
}

/**
 * As we need to match the string to wordlist, we need to find the efficient way to match those string by keeping the precedence of matching in mind
 * There are three rules
 * 1. When the query exactly matches a word in the wordlist (case-sensitive), you should return the same word back. : Means we need to search query in wordlist with exact match
 * 2. When the query matches a word up to capitalization, you should return the first such match in the wordlist.: Means we need to search query in wordlist with canalization match
 * 3. When the query matches a word up to vowel errors, you should return the first such match in the wordlist. : means we need to replace vowel if possible and then match
 * One way would be finding all three kind of string and then match. To keep and search efficiently, we an use hashmaps.
 * <p>
 * Algo:
 * 1. Build all three kind of hashmaps
 * 2. for every query, search in all three maps by above precedence rules and return.
 * <p>
 * To match for vowels error, we need to change the vowel to matched vowel, but keep changing at everytime cost lot of computation. So we hash those string which has vowels.
 * <p>
 * Time complexity: Assuming hashmap search is O(1), w is length of wordList, wl is length of longest word in word list,  q is length of query, ql is length of longest query
 * Step 1:  O(wl*w) ;
 * Step 2: O(ql * q)
 * Overall : O(wl*w) + O(ql*q)
 * <p>
 * Space: O(3*w) => O(w)
 * Runtime: 52 ms, faster than 42.40% of Java online submissions for Vowel Spellchecker.
 * Memory Usage: 45.7 MB, less than 100.00% of Java online submissions for Vowel Spellchecker.
 */
class VowelSpellcheckerUsingMap {

    public String[] spellchecker(String[] wordList, String[] queries) {

        if (wordList == null || wordList.length == 0 || queries == null || queries.length == 0)
            return null;

        //Build hashmaps
        final Map<String, String> exactMatch = new HashMap<>();
        final Map<String, String> capMatch = new HashMap<>();
        final Map<String, String> vowelMatch = new HashMap<>();

        for (String word : wordList) {
            //1. When the query exactly matches a word in the wordlist (case-sensitive), you should return the same word back. : Means we need to search query in wordlist with exact match
            // O(1)
            exactMatch.putIfAbsent(word, word);

            //2. When the query matches a word up to capitalization, you should return the first such match in the wordlist.: Means we need to search query in wordlist with canalization match
            //O(wl); since to lowercase take O(wl) time
            capMatch.putIfAbsent(word.toLowerCase(), word);

            // 3. When the query matches a word up to vowel errors, you should return the first such match in the wordlist. : means we need to replace vowel if possible and then match
            // O(wl); since to hash take O(wl) time
            vowelMatch.putIfAbsent(hash(word.toLowerCase()), word);

        }

        //Search through query
        final String[] result = new String[queries.length];
        int index = 0;
        for (String query : queries)
            result[index++] = search(query, exactMatch, capMatch, vowelMatch);

        return result;


    }

    //O(ql)
    private String search(String query, Map<String, String> exactMatch, Map<String, String> capMatch, Map<String, String> vowelMatch) {
        //1. When the query exactly matches a word in the wordlist (case-sensitive), you should return the same word back.
        //O(1)
        if (exactMatch.containsKey(query))
            return query;

        //2. When the query matches a word up to capitalization, you should return the first such match in the wordlist.
        //O(ql); since to lowercase take O(ql) time
        String temp = query.toLowerCase();
        if (capMatch.containsKey(temp))
            return capMatch.get(temp);

        // 3. When the query matches a word up to vowel errors, you should return the first such match in the wordlist.
        //O(ql); since to hash take O(ql) time
        if ((temp = vowelMatch.get(hash(temp))) != null)
            return temp;


        return "";

    }

    private String hash(String word) {
        return word.replaceAll("[aeiou]", "a");
    }

}


/**
 * Same as above, but collect the words in list instead different map
 * <p>
 * Runtime: 15 ms, faster than 99.16% of Java online submissions for Vowel Spellchecker.
 * Memory Usage: 40.7 MB, less than 100.00% of Java online submissions for Vowel Spellchecker.
 */
class VowelSpellcheckerUsingMap2 {

    final char[] vowels = new char[]{'e', 'i', 'o', 'u'};

    public String[] spellchecker(String[] wordList, String[] queries) {

        if (wordList == null || wordList.length == 0 || queries == null || queries.length == 0)
            return null;

        //Build hashmaps
        final Map<String, List<String>> map = new HashMap<>();

        for (String word : wordList) {
            String hash = hash(word);
            if (!map.containsKey(hash))
                map.put(hash, new ArrayList<>());
            map.get(hash).add(word);

        }

        //Search through query
        final String[] result = new String[queries.length];
        int index = 0;
        for (String query : queries)
            result[index++] = search(query, map);

        return result;


    }

    private String search(String query, Map<String, List<String>> map) {
        if (query == null || query.isEmpty())
            return query;

        String tempQuery = hash(query);

        //If the query has no matches in the wordlist, you should return the empty string.
        if (!map.containsKey(tempQuery))
            return "";

        final List<String> words = map.get(tempQuery);

        //1. When the query exactly matches a word in the wordlist (case-sensitive), you should return the same word back.
        for (String word : words) {
            if (word.equals(query))
                return word;
        }

        ////2. When the query matches a word up to capitalization, you should return the first such match in the wordlist.
        for (String word : words) {
            if (word.toLowerCase().equals(query.toLowerCase()))
                return word;

        }

        // 3. When the query matches a word up to vowel errors, you should return the first such match in the wordlist.
        return words.get(0);
    }

//This is slow
//    private String hash(String word) {
//        word = word.toLowerCase();
//        return word.replaceAll("[aeiou]", "a");
//    }


    String hash(String word) {
        char[] hash = word.toLowerCase().toCharArray();
        for (int i = 0; i < hash.length; i++) {
            for (char vowel : vowels) {
                if (hash[i] == vowel) {
                    hash[i] = 'a';
                    break;
                }
            }
        }
        return new String(hash);
    }

}

