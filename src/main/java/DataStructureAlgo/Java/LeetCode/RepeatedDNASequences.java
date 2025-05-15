package DataStructureAlgo.Java.LeetCode;

import  DataStructureAlgo.Java.nonleetcode.KMP_KnuthMorrisPratt;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 2019-09-12
 * Description: https://leetcode.com/problems/repeated-dna-sequences/
 * 187. Repeated DNA Sequences
 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG".
 * When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
 * <p>
 * Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
 * <p>
 * Example:
 * <p>
 * Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * <p>
 * Output: ["AAAAACCCCC", "CCCCCAAAAA"]
 */
public class RepeatedDNASequences {

    public static void main(String[] args) {
        test("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT", Arrays.asList("AAAAACCCCC", "CCCCCAAAAA"));
        test("AAAAAAAAAAA", Arrays.asList("AAAAAAAAAAA"));

    }

    private static void test(String s, List<String> expected) {
        System.out.println("\n Input: " + s + " expected :" + expected);

        RepeatedDNASequencesRollingHashBrute brute = new RepeatedDNASequencesRollingHashBrute();
        RepeatedDNASequencesRollingHash hash = new RepeatedDNASequencesRollingHash();
        RepeatedDNASequencesRollingHashOptimized rollingRollingHash = new RepeatedDNASequencesRollingHashOptimized();

        System.out.println("brute :" + brute.findRepeatedDnaSequences(s));
        System.out.println("hash :" + hash.findRepeatedDnaSequences(s));
        System.out.println("rollingRollingHash :" + rollingRollingHash.findRepeatedDnaSequences(s));


    }
}

/**
 * To find repetition, we need to know 'sub-string' is present anywhere in the whole string.
 * L=10
 * <p>
 * Brute force:
 * Algo:
 * 1. Take the length of substring, search in the remaining string it exists or not.
 * 2. if found, add in your set and move ahead in a string.
 * <p>
 * Complexity: Each time we take L length substring which takes O(L) time, to search in remaining string takes O(N) time {@link KMP_KnuthMorrisPratt}.
 * Hence: O(L*(N))
 * Space:O(1)
 * <p>
 * Time Limit Exceeded
 */
class RepeatedDNASequencesRollingHashBrute {
    public List<String> findRepeatedDnaSequences(String s) {

        if (s == null || s.isEmpty())
            return Collections.EMPTY_LIST;


        return findRepeatedDnaSequences(s, 10);

    }

    public List<String> findRepeatedDnaSequences(String s, int L) {

        if (s == null || s.isEmpty() && s.length() <= L)
            return Collections.EMPTY_LIST;


        Set<String> dna = new HashSet<>();
        int n = s.length();
        for (int start = 0; start < n - L + 1; start++) {
            String sub = s.substring(start, start + L);
            if (s.substring(start + 1).contains(sub))
                dna.add(sub);
        }

        return new ArrayList<>(dna);
    }


}

/**
 * In above, we brute-force search ahead every time. We can avoid by computing hash see those hash occurred already.
 * * Complexity: Each time we take L length substring hash which takes O(L) time, to search in remaining string takes O(N-L) time {@link KMP_KnuthMorrisPratt}.
 * * Hence: O(L*(N-L))
 * * Space:O(N-L)
 * <p>
 * Runtime: 16 ms, faster than 91.12% of Java online submissions for Repeated DNA Sequences.
 * Memory Usage: 47.1 MB, less than 67.74% of Java online submissions for Repeated DNA Sequences.
 */
class RepeatedDNASequencesRollingHash {
    public List<String> findRepeatedDnaSequences(String s) {

        if (s == null || s.isEmpty())
            return Collections.EMPTY_LIST;


        return findRepeatedDnaSequences(s, 10);

    }

    public List<String> findRepeatedDnaSequences(String s, int L) {
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


/**
 * In above, we have to keep computing the hash again n again O(L), even though in every iteration, only one character drop and a new character come.
 * If we can compute the hash in constant time, then we can avoid O(L) to O(1).
 * <p>
 * The idea is similar to Sliding window and robin-Krup {@link ImplementStrStrKMPRobinKarp}.
 * We need to find an efficient way to compute hash in constant time.
 * <p>
 * To achieve it, we can consider A, C, G, and T in a number system. Since we have only character 4, we can use base 4 number system.
 * A->0
 * C -> 1
 * G -> 2
 * T->3
 * That makes
 * AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT -> 00000111110000011111100000222333
 * <p>
 * To compute hash, we can find the value of first L chars in base 4 value. And to compute the hash of the next set, we'll drop the first character from left
 * and add a new character to right and again compute the value of hash in base 4.
 * <p>
 * How to compute value? we'll apply the same as we form numbers,
 * for example, in base 10, we can write as
 * 456 => 4*100 + 5*10 + 6
 * now of 456 → 567 then
 * 567 -> 5*100 + 6*10 + 7
 * Means,
 * <p>
 * 1. Shift : (400 + 50 + 6 )*10 = 4560
 * 2. Drop : 4560 - 4*10^3 = 4560 - 4000 = 560
 * 3. Add : 560 + 7 = 567
 * <p>
 * Similar idea can be implemented for base 4
 * <p>
 * <p>
 * Runtime: 18 ms, faster than 66.15% of Java online submissions for Repeated DNA Sequences.
 * Memory Usage: 43.6 MB, less than 80.65% of Java online submissions for Repeated DNA Sequences.
 */
class RepeatedDNASequencesRollingHashOptimized {
    public List<String> findRepeatedDnaSequences(String s) {

        if (s == null || s.isEmpty())
            return Collections.EMPTY_LIST;
        return findRepeatedDnaSequences(s, 10);

    }

    public List<String> findRepeatedDnaSequences(String s, int L) {
        if (s.length() <= L)
            return Collections.EMPTY_LIST;

        Set<String> dna = new HashSet<>();
        Set<Integer> hashTable = new HashSet<>();
        int n = s.length();

        int base = 4;
        int lDigitPower = (int) Math.pow(base, L);

        int[] dnaSeq = translateToBase4(s);

        //compute hash for first L digits
        int hash = 0;
        for (int i = 0; i < L; i++)
            hash = hash * base + dnaSeq[i];

        hashTable.add(hash);


        for (int start = 1; start < n - L + 1; start++) {

            //Compute hash by including dnaSeq[start + L - 1] and excluding dnaSeq[start -1]

            //shift
            hash *= base;

            //drop
            hash -= dnaSeq[start - 1] * lDigitPower;

            //add
            hash += dnaSeq[start + L - 1];

            if (hashTable.contains(hash))
                dna.add(s.substring(start, start + L));
            else
                hashTable.add(hash);
        }

        return new ArrayList<>(dna);
    }

    private int[] translateToBase4(String s) {
        int base4[] = new int[s.length()];

        int i = 0;
        for (char c : s.toCharArray())
            switch (c) {
                case 'A':
                    base4[i++] = 0;
                    break;
                case 'C':
                    base4[i++] = 1;
                    break;

                case 'G':
                    base4[i++] = 2;
                    break;

                case 'T':
                    base4[i++] = 3;
                    break;
            }
        return base4;
    }


}
