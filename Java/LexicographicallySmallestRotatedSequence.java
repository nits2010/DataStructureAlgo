package Java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-06
 * Description: https://www.geeksforgeeks.org/lexicographically-smallest-rotated-sequence-set-2/
 * <p>
 * Write code to find lexicographic minimum in a circular array, e.g. for the array BCABDADAB, the lexicographic minimum is ABBCABDAD
 * <p>
 * Input Constraint: 1 < n < 1000
 * <p>
 * Examples:
 * <p>
 * Input:  GEEKSQUIZ
 * Output: EEKSQUIZG
 * <p>
 * Input:  GFG
 * Output: FGG
 * <p>
 * Input :  CAPABCQ
 * Output : ABCQCAP
 */
public class LexicographicallySmallestRotatedSequence {

    public static void main(String []args) {
        SolutionLexicographicallySmallestRotatedSequence sol = new SolutionLexicographicallySmallestRotatedSequence();
        System.out.println(sol.lexicographicallySmallestRotatedSequenceBruteForce("GEEKSQUIZ"));
        System.out.println(sol.lexicographicallySmallestRotatedSequenceBruteForce("GFG"));
        System.out.println(sol.lexicographicallySmallestRotatedSequenceBruteForce("CAPABCQ"));


        System.out.println("******");
        System.out.println(sol.lexicographicallySmallestRotatedSequence("GEEKSQUIZ"));
        System.out.println(sol.lexicographicallySmallestRotatedSequence("GFG"));
        System.out.println(sol.lexicographicallySmallestRotatedSequence("CAPABCQ"));

    }
}

class SolutionLexicographicallySmallestRotatedSequence {

    /**
     * O(n^2 * logn^2 ) => O(n^2 * log n )
     *
     * @param str
     * @return
     */
    public String lexicographicallySmallestRotatedSequenceBruteForce(String str) {
        if (str == null || str.isEmpty())
            return str;

        String concate = str + str; //so that we can easily rotate the string by just taking sub-string on len(str) size
        List<String> strings = new ArrayList<>(str.length());

        for (int i = 0; i < str.length(); i++) {
            strings.add(concate.substring(i, i + str.length()));
        }

        Collections.sort(strings);
        return strings.get(0);
    }

    /**
     * O(n^2 )
     *
     * @param str
     * @return
     */
    public String lexicographicallySmallestRotatedSequence(String str) {
        if (str == null || str.isEmpty())
            return str;

        int smallestStringIndex = 0;

        for (int i = 0; i < str.length(); i++) {

            if (isSmallest(str, smallestStringIndex, i)) smallestStringIndex = i;

        }
        if (smallestStringIndex == 0)
            return str;

        return str.substring(smallestStringIndex) + str.substring(0, smallestStringIndex);
    }

    /**
     * check string start from x and string start from y; find the smallest between them
     *
     * @param str
     * @param x
     * @param y
     * @return
     */
    private boolean isSmallest(String str, int x, int y) {

        for (int i = 0; i < str.length(); i++) {

            if (str.charAt(x) < str.charAt(y))
                return false;

            if (str.charAt(x) > str.charAt(y))
                return true;

            x = (x + 1) % str.length();
            y = (y + 1) % str.length();


        }
        return true;
    }
}