package Java.LeetCode;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-25
 * Description:
 * https://leetcode.com/problems/word-break/
 * &
 * https://leetcode.com/problems/word-break-ii/
 */
public class WordBreak {

    public static void main(String args[]) {


        test("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog"));
        test("pineapplepenapple", Arrays.asList("apple", "pen", "applepen", "pine", "pineapple"));
        test("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat"));

    }

    private static void test(String str, List<String> dict) {

        System.out.println("Brute Force\n");
        System.out.println(WordBreakII.WordBreakIIBruteForce.wordBreak(str, dict));

        System.out.println("DFS Force\n");
        System.out.println(WordBreakII.WordBreakIIDPDFS.wordBreak(str, dict));

        System.out.println("DP Force\n");
        System.out.println(WordBreakII.WordBreakIIDP.wordBreak(str, dict));

        System.out.println("DP Optimized\n");
        System.out.println(WordBreakII.WordBreakIIDPOptimized.wordBreak(str, dict));
    }
}


/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-25
 * Description:https://leetcode.com/problems/word-break/
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
 * <p>
 * {@link Java.companyWise.Amazon.WordBreak}
 */
class WordBreakI {
}


/**
 * https://leetcode.com/problems/word-break-ii/
 * <p>
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word.
 * Return all such possible sentences.
 * <p>
 * Note:
 * <p>
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * Example 1:
 * <p>
 * Input:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * Output:
 * [
 * "cats and dog",
 * "cat sand dog"
 * ]
 * Example 2:
 * <p>
 * Input:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * Output:
 * [
 * "pine apple pen apple",
 * "pineapple pen apple",
 * "pine applepen apple"
 * ]
 * Explanation: Note that you are allowed to reuse a dictionary word.
 * Example 3:
 * <p>
 * Input:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output:
 * []
 */
class WordBreakII {

    //O(2^n)
    static class WordBreakIIBruteForce {

        public static List<String> wordBreak(String s, List<String> wordDict) {

            if (s.isEmpty() || wordDict.isEmpty())
                return Collections.EMPTY_LIST;

            Set<String> dic = new HashSet<>(wordDict);
            List<String> solution = new ArrayList<>();

            wordBreak(s, dic, "", solution);

            Collections.reverse(solution);
            return solution;

        }

        private static void wordBreak(String s, Set<String> dic, String temp, List<String> output) {

            //Processed all chars?
            if (s.isEmpty()) {
                output.add(temp);
                return;
            }

            //try every
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {

                sb.append(s.charAt(i));
                if (dic.contains(sb.toString())) {
                    String old = temp;

                    temp = temp.isEmpty() ? sb.toString() : temp + " " + sb.toString();

                    wordBreak(s.substring(i + 1), dic, temp, output);


                    temp = old;

                }
            }
        }

    }

    /**
     * This solution is extension of Above brute force solution. Above brute force solution, keep trying same word multiple times.
     * <p>
     * This solution,
     * 1. First find at which index how "many type" of dict word available [ like "and" and "sand" at the index 7 ].
     * 2. Once we have that list, we need to choose a word from each index and build the final list. DFS
     */
    static class WordBreakIIDPDFS {

        public static List<String> wordBreak(String s, List<String> wordDict) {

            if (s.isEmpty() || wordDict.isEmpty())
                return Collections.EMPTY_LIST;

            List<String> solution = new ArrayList<>();

            /**
             * This will contains the at index [i] what are the possible words made using dict ending at this index [ like "and" and "sand" at the index 7 ]
             */
            List<String>[] wordIndex = build(s, wordDict);

            /**
             * if we can break this "s" in multiple word, then at the last index, there will be at least one word possible.
             */
            if (wordIndex[s.length()] == null)
                return solution;


            dfs(wordIndex, solution, "", s.length());


            Collections.reverse(solution);
            return solution;


        }

        //Simply DFS and collect all words as sentences
        private static void dfs(List<String>[] wordIndex, List<String> solution, String current, int index) {


            //Has we consumed whole string ?
            if (index == 0) {
                solution.add(current.trim());
                return;
            }

            for (String s : wordIndex[index]) {
                dfs(wordIndex, solution, s + " " + current, index - s.length()); // build further word, rem we reduce the index by current word length as we have consumed that word length from original string
            }


        }

        /**
         * This will contains the at index [i] what are the possible words made using dict ending at this index [ like "and" and "sand" at the index 7 ]
         */
        private static List<String>[] build(String s, List<String> wordDict) {

            /**
             * Build an array of list which contains the different possible words at which occured at index i
             */
            List<String>[] wordIndex = new List[s.length() + 1];

            //Start with empty list, as there won't be any word possible with empty string
            wordIndex[0] = (new ArrayList<>());


            //Try every character of given word
            for (int i = 0; i < s.length(); i++) {

                //If this is not possible to solve, as during build, we might have solved this already, if not than no meaning of moving ahead
                if (wordIndex[i] == null)
                    continue;

                //Try all the word from dictionary
                for (String word : wordDict) {

                    //find what would be the end index in terms of given "s"
                    int end = i + word.length();

                    //<= because subString exclude the second param
                    if (end <= s.length()) {

                        //take this sub-string of word length from index i [ checking does any word possible from index i to end
                        String temp = s.substring(i, end);

                        //if this is possible
                        if (temp.equals(word)) {

                            //either start a new list or append
                            if (wordIndex[end] == null)
                                wordIndex[end] = (new ArrayList<>());

                            wordIndex[end].add(word);

                        }

                    }

                }
            }

            return wordIndex;
        }
    }

    static class WordBreakIIDP {

        public static List<String> wordBreak(String s, List<String> wordDict) {

            HashSet<String> set = new HashSet<>(wordDict);

            if (s.length() == 0 || !canBreak(s, wordDict))
                return new ArrayList<>();

            char[] chars = s.toCharArray();

            List<String>[] dp = new List[chars.length];

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < chars.length; i++) {

                dp[i] = new ArrayList<>();

                sb.setLength(0);

                for (int j = i; j >= 0; j--) {

                    sb.insert(0, chars[j]);

                    String word = sb.toString();

                    if (set.contains(word)) {

                        if (j == 0) {
                            dp[i].add(word);

                        } else {

                            for (String prev : dp[j - 1]) {
                                dp[i].add(prev + " " + word);
                            }
                        }
                    }
                }
            }
            return dp[chars.length - 1];
        }

        private static boolean canBreak(String s, List<String> wordDict) {
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

    static class WordBreakIIDPOptimized {


        /**
         * This solution is similar to {@link WordBreakIIDPDFS}.
         * In that solution we first build the list of index at which the word available and then we do dfs to collect them
         * WE can collect them while building it self.
         *
         * @param s
         * @param wordDict
         * @return
         */
        public static List<String> wordBreak(String s, List<String> wordDict) {
            HashMap<Integer, List<String>> map = new HashMap<>(); //This is similar to List<String>[] wordIndex,
            return buildAndGather(s, s.length(), wordDict, map);
        }

        public static List<String> buildAndGather(String s, int end, List<String> wordDict, HashMap<Integer, List<String>> map) {

            List<String> res = new ArrayList<>();

            //Just like above dfs, once we consumed whole string, no further string left to attach
            if (end == 0) return new ArrayList<>(Arrays.asList(""));

            //Just to see if we have already solved this index, if so return that list
            if (map.containsKey(end)) {
                return map.get(end);
            }

            //Find all the string from first letter
            for (int start = 0; start < end; start++) {

                //Try to consume this sub-string ; Note we are consume from back to front. end points our end of the string
                String substr = s.substring(start, end);

                //is this word possible ?
                if (wordDict.contains(substr)) {
                    //find other words in remaining string
                    List<String> tmp = buildAndGather(s, start, wordDict, map);

                    //attach them
                    for (String str : tmp) {
                        res.add((str.length() == 0) ? substr : str + " " + substr);
                    }
                }
            }
            //cache them
            map.put(end, res);
            return res;
        }
    }
}