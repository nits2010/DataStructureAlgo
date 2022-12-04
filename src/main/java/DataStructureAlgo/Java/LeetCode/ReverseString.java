package DataStructureAlgo.Java.LeetCode;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-04
 * Description:
 */
public class ReverseString {

    public static void main(String[] args) {

        ReverseStringII r = new ReverseStringII();

        System.out.println("fdcqkmxwholhytmhafpesaentdvxginrjlyqzyhehybknvdmfrfvtbsovjbdhevlfxpdaovjgunjqllgsqddebemjanqcqnfkjmi".equals(r.reverseStr("hyzqyljrnigxvdtneasepfahmtyhlohwxmkqcdfehybknvdmfrfvtbsovjbdhevlfxpdaovjgunjqlimjkfnqcqnajmebeddqsgl", 39)));
        System.out.println(("gfedcba".equals(r.reverseStr("abcdefg", 8))));
        System.out.println("cba".equals(r.reverseStr("abc", 3)));
        System.out.println("bacdfeg".equals(r.reverseStr("abcdefg", 2)));
        System.out.println("cbadefihgjk".equals(r.reverseStr("abcdefghijk", 3)));
        System.out.println("bacdfeghjik".equals(r.reverseStr("abcdefghijk", 2)));
        System.out.println("".equals(r.reverseStr("", 2)));
        System.out.println("a".equals(r.reverseStr("a", 2)));
        System.out.println("ba".equals(r.reverseStr("ab", 2)));
        System.out.println("ab".equals(r.reverseStr("ab", 1)));
        System.out.println("abc".equals(r.reverseStr("abc", 1)));
        System.out.println("bac".equals(r.reverseStr("abc", 2)));


    }
}

class ReverseStringII {

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Reverse String II.
     * Memory Usage: 36 MB, less than 100.00% of Java online submissions for Reverse String II.
     *
     * @param s
     * @param k
     * @return
     */
    public String reverseStr(String s, int k) {
        char res[] = s.toCharArray();

        for (int i = 0; i < s.length(); i += 2 * k) {

            int low = i;
            int high = Math.min(i + k - 1, s.length() - 1);

            reverseStr(res, low, high);
        }
        return new String(res);
    }


    private void reverseStr(char reverse[], int i, int j) {

        while (i < j) {

            char temp = reverse[i];
            reverse[i] = reverse[j];
            reverse[j] = temp;

            i++;
            j--;

        }
    }

}