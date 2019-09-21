package Java;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 11/04/19
 * Description:
 * https://stackoverflow.com/questions/46832383/algorithm-to-find-how-many-times-a-string-a-needs-to-be-stated-such-that-it-cont
 * <p>
 * Question:
 * <p>
 * Given two strings A and B, return the number of times A needs to be stated such that it contains string B?
 * <p>
 * Example #1:
 * <p>
 * String A : abcd
 * String B : cdabcdab
 * Should return 3 because:
 * <p>
 * abcdabcdabcd ( A repeated 3 times)
 * cdabcdab ( B is contained in now)
 * Example #2:
 * <p>
 * String A = abcd
 * String B = d
 * should return 1 because B is already a substring of A.
 * <p>
 * Example #3:
 * <p>
 * String A = abcd
 * String B = cda
 * Should return 2 because:
 * <p>
 * abcdabcd
 * cda
 * Example #4:
 * <p>
 * String A = abcd
 * String B = cdb
 * Should return -1, it doesn't matter how many times we state A, there is no way we can produce B.
 */
public class ATimesToContainB {

    public static void main(String []args) {

        System.out.println();
        String a = "abcd";
        String b = "cdabcdabcdabcdab";
        System.out.println("Brute Force Multiply A :" + a + " n= " + aTimesBBruteForce(a, b) + " to contain B " + b);
        System.out.println("Optimized Multiply A :" + a + " n= " + ATimesB(a, b) + " to contain B " + b);


        System.out.println();
        a = "abcd";
        b = "cdabcdabcdabcdabab";
        System.out.println("Brute Force Multiply A :" + a + " n= " + aTimesBBruteForce(a, b) + " to contain B " + b);
        System.out.println("Optimized Multiply A :" + a + " n= " + ATimesB(a, b) + " to contain B " + b);


        System.out.println();
        a = "abcd";
        b = "cdabcdab";
        System.out.println("Brute Force Multiply A :" + a + " n= " + aTimesBBruteForce(a, b) + " to contain B " + b);
        System.out.println("Optimized Multiply A :" + a + " n= " + ATimesB(a, b) + " to contain B " + b);

        System.out.println();
        a = "abcd";
        b = "d";
        System.out.println("Brute Force Multiply A :" + a + " n= " + aTimesBBruteForce(a, b) + " to contain B " + b);
        System.out.println("Optimized Multiply A :" + a + " n= " + ATimesB(a, b) + " to contain B " + b);

        System.out.println();
        a = "abcd";
        b = "cda";
        System.out.println("Brute Force Multiply A :" + a + " n= " + aTimesBBruteForce(a, b) + " to contain B " + b);
        System.out.println("Optimized Multiply A :" + a + " n= " + ATimesB(a, b) + " to contain B " + b);

        System.out.println();
        a = "abcd";
        b = "cdb";
        System.out.println("Brute Force Multiply A :" + a + " n= " + aTimesBBruteForce(a, b) + " to contain B " + b);
        System.out.println("Optimized Multiply A :" + a + " n= " + ATimesB(a, b) + " to contain B " + b);


    }


    //O(n*m^2)
    private static int aTimesBBruteForce(String a, String b) {

        int m = b.length();
        StringBuilder tempA = new StringBuilder(a);

        if (tempA.toString().contains(b))
            return 1;

        for (int i = 1; i < m; i++) { // O(m)

            tempA.append(a);
            if (tempA.toString().contains(b)) //O(n*m); since temp A length could be max "m" times 'a' length's, which is "n", for checking contains it take O(textLength); O(m*n)
                return i + 1;
        }
        return Integer.MIN_VALUE;

    }


    //Idea take from above stack overflow

    /**
     * If |B| > 2|A| - 2 and B occurs in A^n, then A occurs in B. Count and remove all complete instances of A in B, and
     * then the solution is this count plus the solution to the same problem with A's removed from B.
     * <p>
     * Otherwise, it is guaranteed that if B appears in A^n, it appears in A^3. Construct A^3 and find the first occurrence of B in it.
     * Find and remove any complete instances of A appearing after the end of B's appearance in A^3. Return 3 minus the number of removed instances.
     * <p>
     * Examples:
     * <p>
     * f(abcd, cdabcdab)
     * |cdabcdab| > 2|abcd| - 2 since 8 > 2*4 - 2
     **   ^^^^
     * first instance of A in B; no more, so return 1 + f(cdab, abcd) = 3
     * f(cdab, abcd)
     * |cdab| < 2|abcd| - 2 since 4 < 2*4 - 2
     * abcdabcdabcd
     **  ^^^^
     * first instance of B in A; one instance of A after B, so return 3 - 1 = 2.
     * <p>
     * f(d, abcd)
     * |d| < 2|abcd| - 2, since 1 < 2*4 - 2
     * abcdabcdabcd
     * ^
     * first instance of B in A; two instances of A after B, so return 3 - 2 = 1.
     * <p>
     * f(cda, abcd)
     * |cda| = 2|abcd| - 2 since 3 = 2*4 - 2
     * abcdabcdabcd
     * ^^^
     * first instance of B in A; one instance of A after B, so return 3 - 1 = 2.
     * <p>
     * f(cdb, abcd)
     * |cbd| = 2|abcd| - 2 since 3 = 2*3 - 2
     * abcdabcdabcd
     * ^ no instances of B in A; return -1.
     * Some minor optimizations include:
     * <p>
     * if |B| = 0, return 0.
     * if |B| = 1, use A^1 instead of A^3.
     * if |B| < |A| + 2, use A^2 instead of A^3.
     *
     * @param a
     * @param b
     * @return O(( m ^ 2)/n)
     */
    private static int ATimesB(String a, String b) {
        int aLength = a.length();
        int bLength = b.length();


        if (bLength == 0)
            return 0;

        if (bLength == 1)
            return (a.contains(b) ? 1 : -1);

        int count;

        if (bLength > 2 * aLength - 2) {
            count = countTimes(b, a);  // O(m/n)

            if (count > 0) {
                return count + ATimesB(a, removeByTimes(b, a)); // O(m)
            } else
                return count;
        } else if (bLength < aLength + 2) {
            a = a + a;
            count = countTimes(a, b); // O(m)

            if (count > 0) {
                return 1 + count;
            } else
                return Integer.MIN_VALUE;
        } else {
            a = a + a + a;
            count = countTimes(a, b); // O(m)
            if (count > 0)
                return 3 - count;
            else return Integer.MIN_VALUE;

        }


    }

    private static String removeByTimes(String b, String a) {
        return b.replaceAll(a, "");
    }

    /**
     * It will count how many times "toCount" is occur in "fromCount" if occurs at all
     *
     * @param fromToCount
     * @param toCount
     * @return How many times, otherwise 0 if not occur
     */
    private static int countTimes(String fromToCount, String toCount) {
        int count = 0;
        while (fromToCount.contains(toCount)) {
            fromToCount = fromToCount.replaceFirst(toCount, "");
            count++;
        }
        return count;
    }
}
