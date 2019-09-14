package Java.companyWise.intuit;

import java.util.HashSet;
import java.util.Set;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 15/09/19
 * Description: Given a String s, a sub-string is defined as a non-empty string that can be obtained by applying following operation
 * 1. Remove zero or 1 character from left side of s
 * 2. Remove zero or 1 character from right side of s
 * 3. Remove zero or 1 character from left & right side of s
 * <p>
 * Find how many distinct sub-string possible.
 */
public class SubStringCalculator {

    public static void main(String[] args) {
        test("kincenvizh", 53);
        test("abcde", 15);
        test("ghaqjdrmnegmrlrlfpjmnnngpwalzknsencuzwsnhfltwohdgbmvfuwtquosrnyerucntxxkfqehjqygcarxogvcfkljzbzutxphpyykapncjfclnhndzxghelyvzpylazhuutmcquusexzbhsfsmbnlvnlemzvfqbfzwquairhpylnbvyhiyamztlhfchhbwrqddmuzsprfdwuqqchcpeakkexackwwzihkfenwzwckynymgqydvjtovaoezkjjurylqcuonsujycziobnfnmuwnoxcdtahpituykvgpyyshvukrstcbmnsqtjseflwywnslmvnqrtnzkyaddkjamrezprqgoenzsdryygbkeahfiduozpwkrgmatszaxmwodsqiocvagbvxyqotpaujnqvqgjmfxnxhfbwqjpgodlxdrxpjpmzeabpgqrzpxomniknjkdiwtfgyvwvekrnoupwkcbtmpcfamzrghgrznuedkybmfwctdghcfawajlxfkzhdamuygjbcwnyglkjlfmpxfdtovkqbshhrfrnyjrgxgiozsuuncnwofkqzsypwgeikpfbhryhpszegdfajzvqlwwqlnvdtdiuckcvvosrdweohnmawqonjbxyjjhlccuteeshfrxxdhzgakwjqbymnaeudcmibsytyajsgdpfvrutcpglzxdevenevmkgalcrpknuvcrnkuboennhyzirfwvtozzijujsckbxqpocakzrbwgpqgjjmsrtwmvhwyraukbuxfvebeylfpipzwjdzlmgslbtwzataxgqpasrssnfwndldwkdutdqcmcpyanrbdsxrvcvpsywjambtbzlcrvzesuhvyvwwuwwdznigxjxknfajpknqutfvvqynkpvkzgypasevrpxofbymdzcitoqolwqegocuyqsexhumzmckzuuwkamolbltlifongpvkcnrnnuplftqbxpdnegdqlymftqyrxcnzmu", 499011);

    }

    private static void test(String s, int expected) {
        System.out.println("\n Given String :" + s + " expected: " + expected);
        System.out.println(" Top Down :" + SubStringCalculatorTopDown.substringCalculator(s));
        System.out.println(" Bottom Up :" + SubStringCalculatorBottomUp.substringCalculator(s));
    }
}

class SubStringCalculatorTopDown {

    static int dup = 0;

    public static long substringCalculator(String s) {
        long start = System.currentTimeMillis();
        if (s.isEmpty())
            return 0;
        Set<String> dp = new HashSet<>();
        dup = 0;
        int x = topDown(s, dp);
        System.out.println("Dup->" + dup);
        long end = System.currentTimeMillis();
        System.out.println("Time " + (end - start));
        return x;
    }

    private static int topDown(String s, Set<String> dp) {
        if (s.isEmpty())
            return 0;


        if (dp.contains(s)) {
            dup++;
            return 0;
        }

        int c;

        c = 1 + topDown(s.substring(1), dp) +
                topDown(s.substring(0, s.length() - 1), dp);
        if (s.length() >= 2)
            c += topDown(s.substring(1, s.length() - 1), dp);

        dp.add(s);
        return c;
    }

}

class SubStringCalculatorBottomUp {

    public static int substringCalculator(String s) {
        long start = System.currentTimeMillis();
        if (s.isEmpty())
            return 0;

        Set<String> set = new HashSet<>();
        int count = 0;
        int p = 0;


        for (int l = s.length(); l >= 1; l--) {

            for (int i = 0; i < s.length() - l + 1; i++) {

                int j = i + l ;
                String sub = s.substring(i, j);

                if (!set.contains(sub)) {
                    count++;
                    set.add(sub);
                } else {
                    p++;
                }

            }
        }

        System.out.println("Dup -> " + p);
        long end = System.currentTimeMillis();
        System.out.println("Time :" + (end - start));
        return count;
    }
}

