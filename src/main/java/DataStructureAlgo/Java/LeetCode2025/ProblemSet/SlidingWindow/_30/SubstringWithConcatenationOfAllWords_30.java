package DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow._30;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/2/2025
 * Question Title: 30. Substring with Concatenation of All Words
 * Link:
 * Description:
 * File reference
 * -----------
 * Duplicate {@link }
 * Similar {@link}
 * extension {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow._76.MinimumWindowSubstring_76}
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @hard
 * @HashTable
 * @String
 * @SlidingWindow <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @Oracle
 * @Google
 * @Apple
 * @Microsoft
 * @Adobe
 * @Facebook <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class SubstringWithConcatenationOfAllWords_30 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test("barfoothefoobarman", new String[]{"foo", "bar"}, Arrays.asList(0, 9)));
        tests.add(test("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "word"}, Arrays.asList()));
        tests.add(test("barfoofoobarthefoobarman", new String[]{"bar", "foo", "the"}, Arrays.asList(6, 9, 12)));
        tests.add(test("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                new String[]{"a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a"}
                , Arrays.asList()));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(String string, String[] pattern, List<Integer> expected) {
        //add print here
        CommonMethods.printTest(new String[]{"string", "pattern", "Expected"}, true, string, pattern, expected);

        List<Integer> output;
        boolean pass, finalPass = true;

        SolutionSlidingWindow solutionSlidingWindow = new SolutionSlidingWindow();
        output = solutionSlidingWindow.findSubstring(string, pattern);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"SlidingWindow", "Pass"}, false, output, pass ? "PASS" : "FAIL");


        SolutionSlidingWindowOptimized slidingWindowOptimized = new SolutionSlidingWindowOptimized();
        output = slidingWindowOptimized.findSubstring(string, pattern);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"SlidingWindow-Optimized", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }


    /**
     * Complexity:
     * w - words length
     * wL - each word length
     * n - string length
     * <p>
     * O(w) + O(n - w * wL) * O(w * wL + w * wl + w) = O(w) + o(n) * O(w*wl) = O(n*w*wl)
     */

    static class SolutionSlidingWindow {
        public List<Integer> findSubstring(String s, String[] words) {
            int sLen = s.length();
            int wordsLen = words.length;
            int wordLen = words[0].length();

            final List<Integer> result = new ArrayList<>();

            //this is similar to 76. Minimum Window Substring,
            // in that question, we need to find wrt to each character in a pattern, however,
            //here instead of character, it becomes a word itself.

            //cache the patterns, frequency of each word, just like frequency of each letter
            // O(w)
            Map<String, Integer> wordsMap = new HashMap<>();
            for (String word : words) {
                wordsMap.merge(word, 1, Integer::sum);
            }

            //now search in text, such that instead of a letter, we search for word
            /**
             n  = len / wordLen ;
             n * wordLen - len
             n - len * wordLen
             */

            // O(n)
            for (int i = 0; i < sLen - wordsLen * wordLen + 1; i++) {

                //get substring of words length long, words has "wordsLen" and each is wordLen long, so total characters in words
                // are wordLen * wordsLen
                // O(w * wL )
                String sub = s.substring(i, i + (wordLen * wordsLen));

                //check if its valid
                if (isValid(sub, wordsMap, wordLen)) {
                    result.add(i);
                }
            }

            return result;

        }

        //O(w * wl + w)
        private boolean isValid(String s, Map<String, Integer> map, int wordLen) {

            //get wordLen word from s, and check the frequency match or not
            Map<String, Integer> hasFind = new HashMap<>();

            //O(w * wL  / wordLen * wL) = O(w * wL)
            for (int i = 0; i < s.length(); i += wordLen) {

                //O(wL)
                String word = s.substring(i, i + wordLen);

                hasFind.merge(word, 1, Integer::sum);

            }

            //now all the words in map should be available in hasFind as well with same frequency
            return hasFind.equals(map); // O(w)
        }
    }


    /**
     * Complexity:
     * w - words length
     * wL - each word length
     * n - string length
     * <p>
     * O(w) + O(wL) * O(n) = O(n*wL + w)
     */
    static class SolutionSlidingWindowOptimized {
        public List<Integer> findSubstring(String s, String[] words) {
            int sLen = s.length();
            int wordsLen = words.length;
            int wordLen = words[0].length();
            int windowLength = wordsLen * wordLen;

            final List<Integer> result = new ArrayList<>();

            //this is similar to 76. Minimum Window Substring,
            // in that question, we need to find wrt to each character in pattern, however,
            //here instead of character, it becomes a word it self.

            //cache the patterns, frequencey of each word, just like frequency of each letter
            //O(w)
            Map<String, Integer> wordsMap = new HashMap<>();
            for (String word : words) {
                wordsMap.merge(word, 1, Integer::sum);
            }

            //now search in text, such that instead of letter, we search for word
            //O(wL)
            for (int i = 0; i < wordLen; i++) {
                //slide the window of windowLength
                slidingWindow(i, s, wordsMap, wordLen, result, windowLength);
            }

            return result;

        }

        // O(n)
        private void slidingWindow(int windowStart, String text, Map<String, Integer> shouldFind, int wordLen,
                                   List<Integer> result, int windowLength) {

            int windowEnd = windowStart;
            Map<String, Integer> hasFind = new HashMap<>();

            //O(n/wordLen) = O(n)
            while (windowEnd + wordLen <= text.length()) {
                //O(wL)
                String sub = text.substring(windowEnd, windowEnd + wordLen);
                windowEnd += wordLen;

                if (shouldFind.containsKey(sub)) {
                    hasFind.merge(sub, 1, Integer::sum);

                    // Shrink the window if frequency exceeds
                    while (hasFind.get(sub) > shouldFind.get(sub)) {
                        String leftWord = text.substring(windowStart, windowStart + wordLen);
                        hasFind.merge(leftWord, -1, Integer::sum);
                        if (hasFind.get(leftWord) == 0) {
                            hasFind.remove(leftWord);
                        }
                        windowStart += wordLen;
                    }

                    if (windowEnd - windowStart == windowLength) {
                        result.add(windowStart);
                    }

                } else {
                    // Reset if an invalid word is found
                    hasFind.clear();
                    windowStart = windowEnd;
                }
            }
        }

    }

}
