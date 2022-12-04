package DataStructureAlgo.Java.companyWise.facebook;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-18
 * Description: https://aonecode.com/facebook-phone-interview-questions-2019
 * Question: Can you break the given string into words, provided by a given hashmap of frequency of word as <word: n>
 * <p>
 * Example:
 * HashMap -> {"abc":3, "ab":2, "abca":1}
 * String: abcabcabcabca
 * output: Yes; [ abc, abc, abc , abca ]
 * <p>
 * Example:
 * HashMap -> {"abc":3, "ab":2}
 * String: abcabab
 * output: No
 * <p>
 * Example:
 * HashMap -> {"abc":3, "ab":2, "abca":1}
 * String: abcx
 * * output: No
 * <p>
 * [Facebook]
 * <p>
 * https://www.careercup.com/question?id=5158730799251456
 * https://leetcode.com/discuss/interview-question/338192/Any-one-can-help-me
 */
public class BuildStringWordsInDictionary {

    public static void main(String []args) {
        test1();
        test2();
        test3();
        test4();
        test5();


    }

    //false
    private static void test5() {
        String s = "abcabab";
        HashMap<String, Integer> wordCount = new HashMap<>();
        wordCount.put("abc", 3);
        wordCount.put("ab", 1);


        System.out.println("\nGiven String : " + s + " Expected output :" + false);
        System.out.println("SolutionDFSByStringWords ->" + SolutionDFSByStringWords.canBreak(s, new HashMap<>(wordCount)));
        System.out.println("SolutionDFSByMapWords2 -> " + SolutionDFSByStringWords.wordBreak(s, new HashMap<>(wordCount)));
//        System.out.println("SolutionBFSByStringWords -> " + SolutionBFSByStringWords.canBreak(s, new HashMap<>(wordCount)));
        System.out.println("SolutionDFSByMapWords -> " + SolutionDFSByMapWords.canBreak(s, new HashMap<>(wordCount)));

    }

    //true
    private static void test4() {
        String s = "abcabab";
        HashMap<String, Integer> wordCount = new HashMap<>();
        wordCount.put("abc", 3);
        wordCount.put("ab", 2);

        System.out.println("\nGiven String : " + s + " Expected output :" + true);
        System.out.println("SolutionDFSByStringWords ->" + SolutionDFSByStringWords.canBreak(s, new HashMap<>(wordCount)));
        System.out.println("SolutionDFSByMapWords2 -> " + SolutionDFSByStringWords.wordBreak(s, new HashMap<>(wordCount)));
//        System.out.println("SolutionBFSByStringWords -> " + SolutionBFSByStringWords.canBreak(s, new HashMap<>(wordCount)));
        System.out.println("SolutionDFSByMapWords -> " + SolutionDFSByMapWords.canBreak(s, new HashMap<>(wordCount)));
    }

    //false
    private static void test3() {
        String s = "abcx";
        HashMap<String, Integer> wordCount = new HashMap<>();
        wordCount.put("abc", 3);
        wordCount.put("ab", 2);
        wordCount.put("abca", 1);

        System.out.println("\nGiven String : " + s + " Expected output :" + false);
        System.out.println("SolutionDFSByStringWords ->" + SolutionDFSByStringWords.canBreak(s, new HashMap<>(wordCount)));
        System.out.println("SolutionDFSByMapWords2 -> " + SolutionDFSByStringWords.wordBreak(s, new HashMap<>(wordCount)));
//        System.out.println("SolutionBFSByStringWords -> " + SolutionBFSByStringWords.canBreak(s, new HashMap<>(wordCount)));
        System.out.println("SolutionDFSByMapWords -> " + SolutionDFSByMapWords.canBreak(s, new HashMap<>(wordCount)));
    }

    //true
    private static void test1() {

        String s = "abcabcabcabca";
        HashMap<String, Integer> wordCount = new HashMap<>();
        wordCount.put("abc", 3);
        wordCount.put("ab", 2);
        wordCount.put("abca", 1);

        System.out.println("\nGiven String : " + s + " Expected output :" + true);
        System.out.println("SolutionDFSByStringWords ->" + SolutionDFSByStringWords.canBreak(s, new HashMap<>(wordCount)));
        System.out.println("SolutionDFSByMapWords2 -> " + SolutionDFSByStringWords.wordBreak(s, new HashMap<>(wordCount)));
//        System.out.println("SolutionBFSByStringWords -> " + SolutionBFSByStringWords.canBreak(s, new HashMap<>(wordCount)));
        System.out.println("SolutionDFSByMapWords -> " + SolutionDFSByMapWords.canBreak(s, new HashMap<>(wordCount)));
    }


    //true
    private static void test2() {

        String s = "abcabcaabbc";
        HashMap<String, Integer> wordCount = new HashMap<>();
        wordCount.put("abc", 3);
        wordCount.put("ab", 1);
        wordCount.put("abca", 1);

        System.out.println("\nGiven String : " + s + " Expected output :" + true);
        System.out.println("SolutionDFSByStringWords ->" + SolutionDFSByStringWords.canBreak(s, new HashMap<>(wordCount)));
        System.out.println("SolutionDFSByMapWords2 -> " + SolutionDFSByStringWords.wordBreak(s, new HashMap<>(wordCount)));
//        System.out.println("SolutionBFSByStringWords -> " + SolutionBFSByStringWords.canBreak(s, new HashMap<>(wordCount)));
        System.out.println("SolutionDFSByMapWords -> " + SolutionDFSByMapWords.canBreak(s, new HashMap<>(wordCount)));


    }


}

//
///**
// * NOTE: Not working
// */
//class SolutionBFSByStringWords {
//
//
//    public static boolean canBreak(String str, Map<String, Integer> wordCount) {
//
//        if (str.isEmpty())
//            return true;
//
//        return canBreakBFS(str, wordCount);
//
//
//    }
//
//    private static boolean canBreakBFS(String str, Map<String, Integer> wordCount) {
//
//        int n = str.length();
//        final Queue<Integer> queue = new LinkedList<>();
//
//        final Map<String, Integer> visited = new HashMap<>();
//
//        queue.offer(0);
//
//        while (!queue.isEmpty()) {
//
//            int start = queue.poll();
//
//            for (int i = start ; i < str.length(); i++) {
//
//                //Try this word : forward ->
//                String temp = str.substring(start, i + 1);
//
//                if (!visited.containsKey(temp))
//                    visited.put(temp, 0);
//
//                //Check is this possible ?
//                if (wordCount.containsKey(temp) && wordCount.get(temp) > visited.get(temp)) {
//
//                    visited.put(temp, visited.get(temp) + 1);
//
//                    //if possible,the remove this string and recurse for rest of the string;
//                    wordCount.put(temp, wordCount.get(temp) - 1);
//
//                    //recurse for rest of the string;
//                    queue.offer(i);
//
//                    if (i == str.length())
//                        return true;
//
//
//                }
//            }
//
//        }
//
//
//        return false;
//    }
//}

/**
 * This solution won't handle the cases when you can remove the word in-between and form a new word by ends
 * Example:
 * s = "abcabcaabbc";
 * {"abc": 3, "ab": 1, "abca": 1}
 * <p>
 * Your code produce False where it is possible ;
 * abcabcaabbc -> aabbc [remove two "abc"] {"abc": 1, "ab": 1, "abca": 1}
 * aabbc -> abc [remove one "ab" ] {"abc": 1, "ab": 0, "abca": 1} [**** This is not supported ***]
 * abc -> "" [ remove one "abc" ] {"abc": 0, "ab": 1, "abca": 1}
 */
class SolutionDFSByStringWords {


    /**
     * DFS 1:
     * By keeping the index of recursion
     *
     * @param str
     * @param wordCount
     * @return
     */
    public static boolean canBreak(String str, Map<String, Integer> wordCount) {

        if (str.isEmpty())
            return true;

        return canBreakRec(str, wordCount, 0);


    }

    /**
     * Keep taking a sub-string from given string specified by "start" index and keep checking does this is possible break
     * which leads to a solution
     *
     * @param str
     * @param wordCount
     * @param start
     * @return
     */
    private static boolean canBreakRec(String str, Map<String, Integer> wordCount, int start) {

        //If we have remove all the chars, then success
        if (start == str.length())
            return true;

        for (int i = start; i < str.length(); i++) {

            //Try this word : forward ->
            String temp = str.substring(start, i + 1);

            //Check is this possible ?
            if (wordCount.getOrDefault(temp, -1) > 0) {

                //if possible,the remove this string and recurse for rest of the string;
                wordCount.put(temp, wordCount.get(temp) - 1);

                //recurse for rest of the string;
                if (canBreakRec(str, wordCount, i + 1)) {
                    return true;
                }
                //Couldn't find the solution, backtrack
                wordCount.put(temp, wordCount.get(temp) + 1);

            }


        }

        return false;
    }


    /**
     * DFS 2
     * By breaking string into sub-strings
     *
     * @param word
     * @param map
     * @return
     */

    public static boolean wordBreak(String word, Map<String, Integer> map) {
        int len = word.length();
        if (len <= 0) return true;
        for (int i = 1; i < len + 1; i++) {
            String a = word.substring(0, i);
            Integer I = map.get(a);
            if (I == null || I <= 0)
                continue;

            map.put(a, I - 1);

            if (i <= len && wordBreak(word.substring(i, len), map))
                return true;

            map.put(a, I);
        }
        return false;
    }
}


class SolutionDFSByMapWords {


    public static boolean canBreak(String str, Map<String, Integer> wordCount) {

        if (str.isEmpty())
            return true;

        return canBreakRec(str, wordCount);


    }

    /**
     * DFS the tree.
     * check using current count in map of a word, does string can be broken ?
     * if yes, then decrease the number of counts and recurse for further. Try all
     *
     * @param str
     * @param wordCount
     * @return
     */
    private static boolean canBreakRec(String str, Map<String, Integer> wordCount) {
        if (str.isEmpty())
            return true;

        /**
         * find all the keys present in str and remove them as many times as said
         */
        for (String key : wordCount.keySet()) {


            int count = wordCount.get(key);

            //Can we still use this word from dict?
            if (count > 0) {

                int oldCount = count;

                String oldString = str;

                //Find and remove the occurrence
                while (str.contains(key) && count > 0) {
                    str = str.replaceFirst(key, "");
                    count--;
                }

                //if we tried to remove but no occurrence found for given key in string, then continue to next word in map
                if (count == wordCount.get(key))
                    continue;
                else {

                    //decrease the count of occurrence
                    wordCount.put(key, count);

                    //Recurse
                    if (canBreakRec(str, wordCount)) {
                        return true;
                    }

                    //Put back: Backtrack
                    str = oldString;
                    wordCount.put(key, oldCount);

                }


            }

        }
        return false;
    }

}
