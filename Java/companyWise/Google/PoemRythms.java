package Java.companyWise.Google;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date: 11/05/19
 * Description: Given n number of lines in a poem, return all possible rhythm in a poem.
 * <p>
 * Twinkle, twinkle, little star, [A]
 * How I wonder what you are! [A]
 * Up above the world so high, [B]
 * Like a diamond in the sky. [B]
 * <p>
 * In the above poem, we see the first two line (ending with "star" and "are") is in the rhythm that's why they are given as character "A" and similarly last two lines (ending with "high" and "sky") is in the rhythm that's why they are given as character "B".
 * <p>
 * Output should be in lexicographical order only.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 1 (possible chars are A)
 * Output: [A]
 * Example 2:
 * <p>
 * Input: n = 2 (possible chars are A, B)
 * Output: [AA, AB]
 * Explanation:
 * AA
 * AB
 * BA <- This is not possible as it collide with AB. How?
 * Look at the pattern AB says first line has different rhythm and second line has different rhythm then first line.
 * Similarly BA is also shows the same: first line has different rhythm and second line has different rhythm then first line. Hence discard.
 * Example 3:
 * <p>
 * Input: n = 3 (possible chars are A, B, C)
 * Output: [AAA, AAB, ABA, ABB, ABC]
 * Explanation: We can't have AAC as it collides with AAB (first two are same and last is different in both).
 * Similarly other BCA/BAC etc we'll discard them because of collide and lexicographical ordering.
 * Example 4:
 * <p>
 * Input: n = 4 (possible chars are A, B, C, D)
 * Output: 15 (we need to output all of them too)
 */
public class PoemRythms {

    static int count = 0;

    public static void main(String []args) {
        int n = 3;
        char[] str = new char[n];

        Arrays.fill(str, 'A');

        printRhythms(str, n, 'A', 0);
        System.out.println(count);
    }

    /**
     * google-interview-questions0
     * of 0 votes
     * 8
     * Answers
     * Count number of possible rhythm in a poem.
     * Explanation:
     * Twinkle, twinkle, little star, [ A ]
     * How I wonder what you are! [A]
     * Up above the world so high, [B]
     * Like a diamond in the sky. [B]
     * <p>
     * In the above poem, we see the first two line ( ending with "star" and "are" ) is in the rhythm that's why they are given as character "A" and similarly last two lines (ending with "high" and "sky" ] s in the rhythm that's why they are given as character "B".
     * <p>
     * Questions: Given "n" number of lines in a poem, Count number of possible rhythm in a poem.
     * P.S. output should be in lexicographical order only
     * Example:
     * n=1
     * only one possible "A"
     * Answer: A, count =1
     * <p>
     * n=2 [ possible chars are A,B]
     * AA
     * AB
     * BA <- This is not possible as it collide with AB. How? Look at the pattern
     * AB says first line has different rhythm and second line has different rhythm then first line.
     * Similarly BA is also shows the same ; first line has different rhythm and second line has different rhythm then first line.
     * Hence discard
     * <p>
     * n=2
     * AA
     * AB , count=2
     * <p>
     * n=3 [ possible chars are A,B, C]
     * AAA
     * AAB
     * ABA
     * ABB
     * ABC, count=5
     * <p>
     * Look we can't have AAC as it collide with AAB (first two are same and last is different in both)
     * Similarly other BCA/ BAC etc we'll discard them because of collide and lexicographical ordering.
     * <p>
     * <p>
     * n=4, there will be 15 [ we need to print all of them too ]
     * <p>
     * My Finding;
     * 1. I found out that, this is just a bell number ( see the pattern 1,2,5,15.... )
     * 2. I found, this is Dynamic programming question, we can generate the next n+1 from n; How
     * <p>
     * n=2 has
     * AA
     * AB
     * <p>
     * n=3
     * Take AA; there are three possiblilties to append a character is A,B,C
     * But since the last character is A; so lexicographically A and B can be appended at the most, since appending C could conflict with B.
     * AA(A)
     * AA(B)
     * <p>
     * Take AB; there are three possibilities to append a character is A,B,C
     * But since the last character is B; which is lexicographically smaller than A, so lexicographically A, B,C can be appended easily,
     * <p>
     * AB(A)
     * AB(B)
     * AB(C) <- this is possible since its not colliding with any thing
     * <p>
     * So ans; 5
     * AA(A)
     * AA(B)
     * AB(A)
     * AB(B)
     * AB(C)
     * <p>
     * Now lets try n=4 [ now its become complicated ;A,B,C,D]
     * Take AAA; possibilty A,B, not possible C/D conflict with B
     * AAA(A)
     * AAA(B)
     * <p>
     * Take AAB, Possibility is A, B C but what about D; is it possible ? Yes/No
     * AAB(A)
     * AAB(B)
     * AAB(C)
     * AAB(D) <- it collide with last C so discard ;
     * Hence with AAB
     * AAB(A)
     * AAB(B)
     * AAB(C)
     * <p>
     * <p>
     * Take ;
     * ABA(A)
     * ABA(B)
     * ABA(C)
     * ABA(D) Not possible ; collide with C
     * <p>
     * If you observe there is a pattern with last character to first character from right to left;
     *
     * @param str
     * @param n
     * @param maxChar
     * @param index
     */
    private static void printRhythms(char[] str, int n, char maxChar, int index) {

        if (index == n) {
            count++;
            System.out.println(new String(str));
            return;
        }

        for (char c = 'A'; c <= maxChar; c++) {
            str[index] = c;
            printRhythms(str, n, (char) Math.max(maxChar, (char) (c + 1)), index + 1);
        }

    }
}
