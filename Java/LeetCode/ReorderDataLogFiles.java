package Java.LeetCode;

import Java.helpers.GenericPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 14/09/19
 * Description: https://leetcode.com/problems/reorder-data-in-log-files/
 * 937. Reorder Data in Log Files
 * You have an array of logs.  Each log is a space delimited string of words.
 * <p>
 * For each log, the first word in each log is an alphanumeric identifier.  Then, either:
 * <p>
 * Each word after the identifier will consist only of lowercase letters, or;
 * Each word after the identifier will consist only of digits.
 * We will call these two varieties of logs letter-logs and digit-logs.  It is guaranteed that each log has at least one word after its identifier.
 * <p>
 * Reorder the logs so that all of the letter-logs come before any digit-log.  The letter-logs are ordered lexicographically ignoring identifier,
 * with the identifier used in case of ties.  The digit-logs should be put in their original order.
 * <p>
 * Return the final order of the logs.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
 * Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= logs.length <= 100
 * 3 <= logs[i].length <= 100
 * logs[i] is guaranteed to have an identifier, and a word after the identifier.
 * <p>
 * [Amazon]
 */
public class ReorderDataLogFiles {

    public static void main(String[] args) {
        test(new String[]{"dig1 8 1 5 1", "let1 art can", "dig2 3 6", "let2 own kit dig", "let3 art zero"}, new String[]{"let1 art can", "let3 art zero", "let2 own kit dig", "dig1 8 1 5 1", "dig2 3 6"});
        test(new String[]{"dig1 8 1 5 1", "let1 art can bog", "dig2 3 6", "let2 own kit dig", "let3 art can "}, new String[]{"let3 art can ", "let1 art can bog", "let2 own kit dig", "dig1 8 1 5 1", "dig2 3 6"});
    }

    private static void test(String[] logs, String[] expected) {
        System.out.println("\n Input :" + GenericPrinter.toString(logs));
        System.out.println(" Expected :" + GenericPrinter.toString(expected));
        ReorderDataLogFilesSeparateSortIndexOf separateSortIndexOf = new ReorderDataLogFilesSeparateSortIndexOf();
        ReorderDataLogFilesWithInSortIndexOf withInSortIndexOf = new ReorderDataLogFilesWithInSortIndexOf();
        System.out.println(" separateSortIndexOf    :" + GenericPrinter.toString(separateSortIndexOf.reorderLogFiles(GenericPrinter.copyOf(logs))));
        System.out.println(" withInSortIndexOf      :" + GenericPrinter.toString(withInSortIndexOf.reorderLogFiles(GenericPrinter.copyOf(logs))));

    }
}


/**
 * Separate both the logs and sort letter logs
 * Time Complexity:
 * n -> Length of logs array
 * L -> length of letter logs array
 * D -> length of digit logs array
 * Sorting  L length array is O(L*log(L))
 * <p>
 * Overall : O(n) + O(L*log(L)) + O(n) => O(L*log(L)) > O(n)
 * <p>
 * In worst case: n == L hence O(n*log(n))
 * Runtime: 37 ms, faster than 32.53% of Java online submissions for Reorder Data in Log Files.
 * Memory Usage: 38.6 MB, less than 73.53% of Java online submissions for Reorder Data in Log Files.
 */
class ReorderDataLogFilesSeparateSortIndexOf {
    public String[] reorderLogFiles(String[] logs) {

        if (logs == null || logs.length == 0)
            return logs;

        final List<String> digitLogs = new ArrayList<>();
        final List<String> letterLogs = new ArrayList<>();

        for (String log : logs) {

            int ind = log.indexOf(' ');
            char c = log.charAt(ind + 1);


            //is digit logs
            if (c >= '0' && c <= '9')
                digitLogs.add(log);
            else
                letterLogs.add(log);
        }

        final Comparator<String> com = (o1, o2) -> {
            //if words after identifier are same , the sort based on identifier
            String[] split1 = o1.split(" ", 2);
            String[] split2 = o2.split(" ", 2);

            if (split1[1].compareTo(split2[1]) == 0)
                return split1[0].compareTo(split2[0]);

            return split1[1].compareTo(split2[1]); ////the sort based on words
        };

        letterLogs.sort(com);

        letterLogs.addAll(digitLogs);
        String[] output = new String[letterLogs.size()];
        int i = 0;
        for (String s : letterLogs)
            output[i++] = s;

        return output;
    }
}

/**
 * Runtime: 2 ms, faster than 99.33% of Java online submissions for Reorder Data in Log Files.
 * Memory Usage: 38.8 MB, less than 70.59% of Java online submissions for Reorder Data in Log Files.
 */
class ReorderDataLogFilesWithInSortIndexOf {
    public String[] reorderLogFiles(String[] logs) {

        if (logs == null || logs.length == 0)
            return logs;

        Comparator<String> com = new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                int ind1 = o1.indexOf(' ');
                char c1 = o1.charAt(ind1 + 1);

                int ind2 = o2.indexOf(' ');
                char c2 = o2.charAt(ind2 + 1);


                if (c1 >= '0' && c1 <= '9') {
                    if (c2 >= '0' && c2 <= '9')
                        return 0; //if both are digit, then their should not be any sorting,  hence mark them equal.=> 0
                    else
                        return 1; // Otherwise gives non-digit logs as higher priority
                }
                if (c2 >= '0' && c2 <= '9')
                    return -1; // Otherwise gives non-digit logs as higher priority

                String s1 = o1.substring(ind1 + 1);
                String s2 = o2.substring(ind2 + 1);


                int comp = s1.compareTo(s2);
                if (comp == 0) {
                    String s1ID = o1.substring(0, ind1);
                    String s2ID = o2.substring(0, ind2);
                    return s1ID.compareTo(s2ID);
                }

                return comp; ////the sort based on words
            }
        };


        Arrays.sort(logs, com);

        return logs;
    }
}