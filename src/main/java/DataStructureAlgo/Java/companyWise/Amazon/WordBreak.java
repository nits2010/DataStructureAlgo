package DataStructureAlgo.Java.companyWise.Amazon;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-25
 * Description: 139. Word Break
 * https://www.geeksforgeeks.org/minimum-word-break/
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 * <p>
 * Note:
 * <p>
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * Example 1:
 * <p>
 * Input: s = "leetcode", wordDict = ["leet", "code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * Example 2:
 * <p>
 * Input: s = "applepenapple", wordDict = ["apple", "pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 * Note that you are allowed to reuse a dictionary word.
 * Example 3:
 * <p>
 * Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output: false
 */
public class WordBreak {


    public static void main(String []args) {

        Set<String> dict = new HashSet<>();
        dict.add("I");
        dict.add("A");
        dict.add("AM");
        dict.add("Interview");
        dict.add("Interviewing");
        dict.add("Amazon");
        dict.add("In");

        test("IAMAmazon", dict);
        test("IAMInterviewAmazon", dict);
        test("IAMInterviewInAmazon", dict);
        test("Amazon", dict);
        test("IAMInterviewingInAmazon", dict);


    }

    static void test(String input, Set<String> dict) {
        System.out.println("Backtracking");
        System.out.println(BackTracking.wordBreakBack(input, dict));

        System.out.println("DP 1 is possible ");
        System.out.println(DP1.wordBreak(input, dict));

        System.out.println("DP 2 is possible ");
        System.out.println(DP1.wordBreakOptimized(input, dict));

        System.out.println("DP 3 is possible ");
        System.out.println(DP1.wordBreakUsingDict(input, new ArrayList<>(dict)));

        System.out.println("Trie Solution  is possible ");
        System.out.println(TrieSolution.wordBreak(input, dict));

        System.out.println();
    }

}

class BackTracking {

    public static List<String> wordBreakBack(String input, Set<String> dict) {

        if (input == null || input.length() == 0)
            return Collections.EMPTY_LIST;

        LinkedList<String> vw = new LinkedList<>();

        wordBreakBack(input, vw, dict);

        return vw;
    }

    //IAM
    static boolean wordBreakBack(String input, LinkedList<String> vw, Set<String> dict) {

        if (input.isEmpty())
            return true;

        for (int i = 1; i <= input.length(); i++) {

            String currentPrefix = input.substring(0, i);

            if (dict.contains(currentPrefix)) {

                vw.add(currentPrefix);

                String remaining = input.substring(i);

                if (wordBreakBack(remaining, vw, dict)) {
                    return true;
                } else {
                    vw.removeLast();
                }
            }

        }
        return false;


    }

}

class DP1 {


    public boolean wordBreak(String s, List<String> wordDict) {

        Set<String> dict = new HashSet<>(wordDict);
        return wordBreak(s, dict);
    }


    /**
     * Find out if it can break
     *
     * @param input
     * @param dict
     * @return
     */
    public static boolean wordBreak(String input, Set<String> dict) {

        if (input == null || input.length() == 0)
            return true;


        boolean w[] = new boolean[input.length() + 1];

        /**
         * Empty string can always be break
         */
        w[0] = true;

        for (int i = 1; i <= input.length(); i++) {

            // if wb[i] is false, then check if current prefix can make it true.
            // Current prefix is "str.substr(0, i)"
            if (w[i] == false && dict.contains(input.substring(0, i)))
                w[i] = true;

            // wb[i] is true, then check for all substrings starting from
            // (i+1)th character and store their results.
            if (w[i] == true) {

                // If we reached the last prefix
                if (i == input.length())
                    return true;

                for (int j = i + 1; j <= input.length(); j++) {

                    String sub = input.substring(i, j);

                    // Update wb[j] if it is false and can be updated
                    // Note the parameter passed to dictionaryContains() is
                    // substring starting from index 'i' and length 'j-i'
                    if (w[j] == false && dict.contains(sub))
                        w[j] = true;

                    // If we reached the last character
                    if (j == input.length() && w[j] == true)
                        return true;
                }
            }
        }


        return w[input.length()];
    }

    public static boolean wordBreakOptimized(String s, Set<String> wordDict) {

        if (s.isEmpty())
            return true;

        if (wordDict.isEmpty())
            return false;

        boolean dp[] = new boolean[s.length() + 1];

        dp[0] = true;


        //Start from second letter
        for (int i = 1; i <= s.length(); i++) {


            //sub-string till i
            for (int j = 0; j < i; j++) {

                String sub = s.substring(j, i);

                //if the first sub-part is true dp[j] and check for other sub-part
                if (dp[j] && wordDict.contains(sub)) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }

    public static boolean wordBreakUsingDict(String s, List<String> wordDict) {
        Map<String, Boolean> dp = new HashMap<>();
        dp.put("", true);
        return attemptBreak(s, wordDict, dp);
    }

    private static boolean attemptBreak(String s, List<String> wordDict, Map<String, Boolean> dp) {
        if (dp.containsKey(s)) {
            return dp.get(s);
        } else {
            for (String word : wordDict) {
                if (s.startsWith(word)) {
                    String nextS = s.substring(word.length());
                    if (attemptBreak(nextS, wordDict, dp)) {
                        dp.put(s, true);
                        return true;
                    }
                }
            }
            dp.put(s, false);
            return false;
        }
    }

}


class TrieSolution {

    static class TrieNode {

        char value;
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isLeaf = false;

        public TrieNode() {
        }

        public TrieNode(char value) {
            this.value = value;
        }
    }

    static class Trie {

        TrieNode root;

        public void insert(String toInsert) {

            if (root == null)
                root = new TrieNode();

            TrieNode pCrawl = root;

            for (int i = 0; i < toInsert.length(); i++) {
                char current = toInsert.charAt(i);

                if (!pCrawl.children.containsKey(current))
                    pCrawl.children.put(current, new TrieNode(current));

                pCrawl = pCrawl.children.get(current);
            }

            pCrawl.isLeaf = true;
        }

        public boolean search(String str) {

            TrieNode pCrawl = root;

            for (int i = 0; i < str.length(); i++) {

                if (pCrawl != null && !pCrawl.children.containsKey(str.charAt(i)))
                    return false;

                pCrawl = pCrawl.children.get(str.charAt(i));
            }

            return pCrawl != null && pCrawl.isLeaf;
        }
    }

    public static boolean wordBreak(String input, Set<String> dict) {

        if (input.isEmpty())
            return true;

        if (dict.isEmpty())
            return false;

        Trie trie = new Trie();
        dict.stream().forEach(d -> trie.insert(d));

        return wordBreak(input, dict, trie);
    }

    private static boolean wordBreak(String input, Set<String> dict, Trie trie) {

        if (input.isEmpty())
            return true;

        for (int i = 1; i <= input.length(); i++) {

            String current = input.substring(0, i);

            String next = (i == input.length()) ? "" : input.substring(i);

            if (trie.search(current) && wordBreak(next, dict)) {
                return true;
            }
        }

        return false;
    }


}
