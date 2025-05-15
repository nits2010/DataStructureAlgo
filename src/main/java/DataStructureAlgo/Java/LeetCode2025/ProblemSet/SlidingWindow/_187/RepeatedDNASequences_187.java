package DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow._187;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/14/2025
 * Question Title: 187. Repeated DNA Sequences
 * Link:
 * Description: The DNA sequence is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T'.
 * <p>
 * For example, "ACGAATTCCG" is a DNA sequence.
 * When studying DNA, it is useful to identify repeated sequences within the DNA.
 * <p>
 * Given a string s that represents a DNA sequence, return all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule. You may return the answer in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * Output: ["AAAAACCCCC","CCCCCAAAAA"]
 * Example 2:
 * <p>
 * Input: s = "AAAAAAAAAAAAA"
 * Output: ["AAAAAAAAAA"]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 105
 * s[i] is either 'A', 'C', 'G', or 'T'.
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @HashTable
 * @String
 * @BitManipulation
 * @SlidingWindow
 * @RollingHash
 * @HashFunction <p><p>
 * Company Tags
 * -----
 * @LinkedIn
 * @Amazon <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link SolutionRollingHashWithMask}
 */

public class RepeatedDNASequences_187 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT", Arrays.asList("AAAAACCCCC", "CCCCCAAAAA")));
        tests.add(test("AAAAAAAAAAAAA", Arrays.asList("AAAAAAAAAA")));
        tests.add(test("a", Arrays.asList()));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(String s, List<String> expected) {
        //add print here
        CommonMethods.printTest(new String[]{"s", "Expected"}, true, s, expected);

        List<String> output;
        boolean pass, finalPass = true;

        output = new SolutionSubstring().findRepeatedDnaSequences(s);
        pass = CommonMethods.compareResultOutCome(output, expected, false);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Substring", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = new SolutionRollingHash().findRepeatedDnaSequences(s);
        pass = CommonMethods.compareResultOutCome(output, expected, false);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"RollingHash", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = new SolutionRollingHashWithMask().findRepeatedDnaSequences(s);
        pass = CommonMethods.compareResultOutCome(output, expected, false);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"RollingHashWithMask", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    //O(n*L) / O(n)
    static class SolutionSubstring {
        public List<String> findRepeatedDnaSequences(String s) {
            int L = 10;
            if (s.length() <= L)
                return Collections.EMPTY_LIST;

            Set<String> dna = new HashSet<>();
            Set<String> hash = new HashSet<>();
            int n = s.length();
            for (int start = 0; start < n - L + 1; start++) {
                String sub = s.substring(start, start + L);
                if (!hash.add(sub))
                    dna.add(sub);
            }

            return new ArrayList<>(dna);
        }
    }


    //O(n) / O(n)
    static class SolutionRollingHash {
        public List<String> findRepeatedDnaSequences(String s) {
            int n = s.length();
            if (n <= 10)
                return Collections.EMPTY_LIST;
            Map<Character, Integer> map = Map.of('A', 0, 'C', 1, 'G', 2, 'T', 3);

            int[] dna = build(s, map);

            Set<Integer> seen = new HashSet<>();
            Set<String> repeated = new HashSet<>();
            int base = 4;
            int power = (int) Math.pow(base, 10);

            //build the hash for the first 10 elements
            int hash = 0;
            for (int i = 0; i < 10; i++) {
                hash = hash * base + dna[i]; // just like 4*10 here 10 since base=10
            }
            seen.add(hash);

            for (int i = 10; i < n; i++) {

                //shift, drop and add/compute

                //shift
                hash = hash * base;

                //drop
                hash = hash - dna[i - 10] * power;

                //add
                hash = hash + dna[i];

                if (!seen.add(hash)) {
                    repeated.add(s.substring(i - 9, i + 1));
                }


            }
            return new ArrayList<>(repeated);
        }

        private int[] build(String s, Map<Character, Integer> map) {
            int[] dna = new int[s.length()];
            for (int i = 0; i < s.length(); i++) {
                dna[i] = map.get(s.charAt(i));
            }
            return dna;
        }
    }


    static class SolutionRollingHashWithMask {
        public List<String> findRepeatedDnaSequences(String s) {
            int n = s.length();
            if (n < 10)
                return new ArrayList<>();

            Map<Character, Integer> map = Map.of(
                    'A', 0,
                    'C', 1,
                    'G', 2,
                    'T', 3);

            int mask = (1 << 20) - 1;

            //calculate the rolling hash mask for the first character 10
            char[] dna = s.toCharArray();
            int hash = 0;

            //build the window of character 10
            int start = 0, end;
            for (end = 0; end < 10; end++) {

                // make room for new character (hash << 2) by dropping 2 initial bits
                // add new character hash using |
                hash = (hash << 2) | map.get(dna[end]);
            }

            Set<Integer> seen = new HashSet<>();
            seen.add(hash);

            Set<String> output = new HashSet<>();

            while (end < n) {

                //recompute the hash
                //drop 2 bits (hash << 2)
                // add new character | map.get()
                // mask to get last 20 bits only
                hash = ((hash << 2) | map.get(dna[end])) & mask;
                start++;

                if (!seen.add(hash)) {
                    output.add(s.substring(start, end + 1));
                }

                end++;
            }

            return new ArrayList<>(output);
        }
    }

}
