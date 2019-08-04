package Java.LeetCode;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
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

    public String reverseStr(String s, int k) {

        if (null == s || s.isEmpty() || k <= 1)
            return s;


        int i = 0;
        int j = k - 1;
        int p = k;

        char reverse[] = s.toCharArray();
        if (s.length() <= k) {
            reverseStr(reverse, 0, s.length() - 1);
            return new String(reverse);
        }


        while (true) {


            //Characters are left to reverse

            reverseStr(reverse, i, j);

            //skip k character unchanged;
            j = p;
            int count = k;

            while (j < s.length() && count-- > 0)
                j++;

            if (j >= s.length())
                break;

            i = j;
            j = i + k - 1;
            if (j >= s.length())
                j = s.length() - 1;
            p = i + k;


        }

        return new String(reverse);

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