package Java;

import Java.HelpersToPrint.GenericPrinter;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-25
 * Description:
 * <p>
 * https://discuss.codechef.com/t/a-tutorial-on-suffix-arrays/2950
 */
public class SuffixArray {

    public static void main(String[] args) {
        test("banana", new int[]{5, 3, 1, 0, 4, 2});
        test("aab", new int[]{0, 1, 2});
        test("abc", new int[]{0, 1, 2});
        test("abasdfaderec", new int[]{0, 6, 2, 1, 11, 7, 4, 10, 8, 5, 9, 3});

    }


    private static void test(String text, int expected[]) {
        System.out.println("\nText :" + text + " Expected " + GenericPrinter.toString(expected));
        System.out.println(GenericPrinter.toString(suffixArrayPolynomial(text)));
        System.out.println(GenericPrinter.toString(suffixArray(text)));
    }

    public static int[] suffixArrayPolynomial(String text) {
        return SuffixArrayPolynomial.suffixArray(text);
    }

    public static int[] suffixArray(String text) {
        return SuffixArrayNLogN.suffixArray(text);
    }


}

//https://www.geeksforgeeks.org/suffix-array-set-2-a-nlognlogn-algorithm/
class SuffixArrayNLogN {

    public static int[] suffixArray(String text) {
        if (null == text || text.isEmpty())
            return new int[0];


        final class Suffix {

            int index = 0;

            /**
             * Contains the first character rank and adjacent character rank
             * First rank is defined by how far the character is from 'a'
             * Second rank is defined by how far the character is from 'a' and if there is no adjacent char then its -1
             */
            int rank[] = new int[2];
        }

        int n = text.length();
        Suffix[] suffixes = new Suffix[n];

        /**
         * Build initial suffix rank
         * Index    Suffix            Rank          Next Rank
         *  0       banana             1              0
         *  1       anana              0              13
         *  2       nana               13             0
         *  3       ana                0              13
         *  4       na                 13             0
         *  5       a                  0             -1
         */
        /**
         * This array will be use to assign the next rank
         */
        int suffixRank[] = new int[n];

        for (int i = 0; i < n; i++) {

            suffixes[i] = new Suffix();
            suffixes[i].index = i;
            suffixes[i].rank[0] = text.charAt(i) - 'a';
            suffixes[i].rank[1] = (i + 1 == n) ? -1 : text.charAt(i + 1) - 'a';

            suffixRank[i] = suffixes[i].rank[0];
        }

        Comparator<Suffix> suffixComparator = (o1, o2) -> {
            if (o1.rank[0] == o2.rank[0])
                return o1.rank[1] - o2.rank[1];
            return o1.rank[0] - o2.rank[0];
        };


        /**
         * We first sort all suffixes according to first character, then according to first 2 characters,
         * then first 4 characters and so on while the number of characters to be considered is smaller than 2n
         */


        /**
         * After this sorting, we have sort the data based on first 2 character.
         *
         * Sort suffixes based on rank;
         * first by rank[0] and then by rank[1]
         *
         * Index    Suffix            Rank          Next Rank
         *  5        a                  0              -1
         *  1        anana              0               13
         *  3        ana                0               13
         *  0        banana             1               0
         *  2        nana               13              0
         *  4        na                 13              0
         *
         *
         */
        Arrays.sort(suffixes, suffixComparator);

        /**
         * Sort data based on 4 char , than 8 char ....
         * Iteratively assign rank and sort of every 2^(i). where 2<=i<=log(n)
         */


        for (int k = 4; k < 2 * n; k *= 2) {

            /**
             * Assign new ranks to all suffixes. To assign new ranks, we consider the sorted suffixes one by one.
             * Assign 0 as new rank to first suffix. For assigning ranks to remaining suffixes, we consider rank pair of suffix
             * just before the current suffix. If previous rank pair of a suffix is same as previous rank of suffix just before it,
             * then assign it same rank. Otherwise assign rank of previous suffix plus one.
             */
            int rank = 0;

            // Assign 0 as new rank to first suffix.
            suffixRank[suffixes[0].index] = 0;

            /**
             * Find rank of next suffixes
             * we consider rank pair of suffix
             * just before the current suffix. If previous rank pair of a suffix is same as previous rank of suffix just before it,
             * then assign it same rank. Otherwise assign rank of previous suffix plus one.
             *
             * Index       Suffix          Rank
             *   5          a               0     [Assign 0 to first]
             *   1          anana           1     (0, 13) is different from previous
             *   3          ana             1     (0, 13) is same as previous
             *   0          banana          2     (1, 0) is different from previous
             *   2          nana            3     (13, 0) is different from previous
             *   4          na              3     (13, 0) is same as previous
             */
            for (int i = 1; i < n; i++) {

                Suffix previousPair = suffixes[i - 1];
                Suffix currentPair = suffixes[i];

                if (previousPair.rank[0] != currentPair.rank[0] || previousPair.rank[1] != currentPair.rank[1])
                    rank++;

                suffixRank[suffixes[i].index] = rank;


            }

            /**
             * For every suffix str[i], also store rank of next suffix at str[i + 2]. If there is no next suffix at i + 2, we store next rank as -1
             * Index       Suffix          Rank        Next Rank
             *   5          a               0             -1
             *   1          anana           1              1
             *   3          ana             1              0
             *   0          banana          2              3
             *   2          nana            3              3
             *   4          na              3              -1
             */


            for (int i = 0; i < n; i++) {
                int nextIndex = suffixes[i].index + k / 2;

                suffixes[i].rank[0] = suffixRank[suffixes[i].index];

                suffixes[i].rank[1] = (nextIndex < n) ? suffixes[suffixRank[nextIndex]].rank[0] : -1;

            }
            /**
             * Sort it
             */
            Arrays.sort(suffixes, suffixComparator);


        }

        int suffixArray[] = new int[n];
        int i = 0;

        for (Suffix s : suffixes)
            suffixArray[i++] = s.index;

        return suffixArray;
    }


}

//https://www.geeksforgeeks.org/suffix-array-set-1-introduction/
class SuffixArrayPolynomial {
    public static int[] suffixArray(String text) {
        if (null == text || text.isEmpty())
            return new int[0];

        final class Suffix {
            int index = 0;
            String suffix;
        }

        int n = text.length();
        Suffix[] suffixes = new Suffix[n];

        for (int i = 0; i < n; i++) {


            suffixes[i] = new Suffix();
            suffixes[i].index = i;
            suffixes[i].suffix = text.substring(i);
        }

        Arrays.sort(suffixes, Comparator.comparing(o -> o.suffix));

        int suffixArray[] = new int[n];
        int i = 0;

        for (Suffix s : suffixes)
            suffixArray[i++] = s.index;

        return suffixArray;
    }
}