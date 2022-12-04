package DataStructureAlgo.Java.companyWise.intuit;

import java.util.HashSet;
import java.util.Set;

/**
 * Author: Nitin Gupta
 * Date: 15/09/19
 * Description: Given a String s, a sub-string is defined as a non-empty string that can be obtained by applying following operation
 * 1. Remove zero or 1 character from left side of s
 * 2. Remove zero or 1 character from right side of s
 * 3. Remove zero or 1 character from left & right side of s
 * Find how many distinct sub-string possible.
 *
 * Characters in string are [a-z]
 * String length can be up to 10^5
 */
public class SubStringCalculator {

    public static void main(String[] args) {
        test("kincenvizh", 53);
        test("abcde", 15);
        test("ghaqjdrmnegmrlrlfpjmnnngpwalzknsencuzwsnhfltwohdgbmvfuwtquosrnyerucntxxkfqehjqygcarxogvcfkljzbzutxphpyykapncjfclnhndzxghelyvzpylazhuutmcquusexzbhsfsmbnlvnlemzvfqbfzwquairhpylnbvyhiyamztlhfchhbwrqddmuzsprfdwuqqchcpeakkexackwwzihkfenwzwckynymgqydvjtovaoezkjjurylqcuonsujycziobnfnmuwnoxcdtahpituykvgpyyshvukrstcbmnsqtjseflwywnslmvnqrtnzkyaddkjamrezprqgoenzsdryygbkeahfiduozpwkrgmatszaxmwodsqiocvagbvxyqotpaujnqvqgjmfxnxhfbwqjpgodlxdrxpjpmzeabpgqrzpxomniknjkdiwtfgyvwvekrnoupwkcbtmpcfamzrghgrznuedkybmfwctdghcfawajlxfkzhdamuygjbcwnyglkjlfmpxfdtovkqbshhrfrnyjrgxgiozsuuncnwofkqzsypwgeikpfbhryhpszegdfajzvqlwwqlnvdtdiuckcvvosrdweohnmawqonjbxyjjhlccuteeshfrxxdhzgakwjqbymnaeudcmibsytyajsgdpfvrutcpglzxdevenevmkgalcrpknuvcrnkuboennhyzirfwvtozzijujsckbxqpocakzrbwgpqgjjmsrtwmvhwyraukbuxfvebeylfpipzwjdzlmgslbtwzataxgqpasrssnfwndldwkdutdqcmcpyanrbdsxrvcvpsywjambtbzlcrvzesuhvyvwwuwwdznigxjxknfajpknqutfvvqynkpvkzgypasevrpxofbymdzcitoqolwqegocuyqsexhumzmckzuuwkamolbltlifongpvkcnrnnuplftqbxpdnegdqlymftqyrxcnzmu", 499011);
        test("ghaqjdrmnegmrlrlfpjmnnngpwalzknsencuzwsnhfltwohdgbmvfuwtquosrnyerucntxxkfqehjqygcarxogvcfkljzbzutxphpyykapncjfclnhndzxghelyvzpylazhuutmcquusexzbhsfsmbnlvnlemzvfqbfzwquairhpylnbvyhiyamztlhfchhbwrqddmuzsprfdwuqqchcpeakkexackwwzihkfenwzwckynymgqydvjtovaoezkjjurylqcuonsujycziobnfnmuwnoxcdtahpituykvgpyyshvukrstcbmnsqtjseflwywnslmvnqrtnzkyaddkjamrezprqgoenzsdryygbkeahfiduozpwkrgmatszaxmwodsqiocvagbvxyqotpaujnqvqgjmfxnxhfbwqjpgodlxdrxpjpmzeabpgqrzpxomniknjkdiwtfgyvwvekrnoupwkcbtmpcfamzrghgrznuedkybmfwctdghcfawajlxfkzhdamuygjbcwnyglkjlfmpxfdtovkqbshhrfrnyjrgxgiozsuuncnwofkqzsypwgeikpfbhryhpszegdfajzvqlwwqlnvdtdiuckcvvosrdweohnmawqonjbxyjjhlccuteeshfrxxdhzgakwjqbymnaeudcmibsytyajsgdpfvrutcpglzxdevenevmkgalcrpknuvcrnkuboennhyzirfwvtozzijujsckbxqpocakzrbwgpqgjjmsrtwmvhwyraukbuxfvebeylfpipzwjdzlmgslbtwzataxgqpasrssnfwndldwkdutdqcmcpyanrbdsxrvcvpsywjambtbzlcrvzesuhvyvwwuwwdznigxjxknfajpknqutfvvqynkpvkzgypasevrpxofbymdzcitoqolwqegocuyqsexhumzmckzuuwkamolbltlifongpvkcnrnnuplftqbxpdnegdqlymftqyrxcnzmughaqjdrmnegmrlrlfpjmnnngpwalzknsencuzwsnhfltwohdgbmvfuwtquosrnyerucntxxkfqehjqygcarxogvcfkljzbzutxphpyykapncjfclnhndzxghelyvzpylazhuutmcquusexzbhsfsmbnlvnlemzvfqbfzwquairhpylnbvyhiyamztlhfchhbwrqddmuzsprfdwuqqchcpeakkexackwwzihkfenwzwckynymgqydvjtovaoezkjjurylqcuonsujycziobnfnmuwnoxcdtahpituykvgpyyshvukrstcbmnsqtjseflwywnslmvnqrtnzkyaddkjamrezprqgoenzsdryygbkeahfiduozpwkrgmatszaxmwodsqiocvagbvxyqotpaujnqvqgjmfxnxhfbwqjpgodlxdrxpjpmzeabpgqrzpxomniknjkdiwtfgyvwvekrnoupwkcbtmpcfamzrghgrznuedkybmfwctdghcfawajlxfkzhdamuygjbcwnyglkjlfmpxfdtovkqbshhrfrnyjrgxgiozsuuncnwofkqzsypwgeikpfbhryhpszegdfajzvqlwwqlnvdtdiuckcvvosrdweohnmawqonjbxyjjhlccuteeshfrxxdhzgakwjqbymnaeudcmibsytyajsgdpfvrutcpglzxdevenevmkgalcrpknuvcrnkuboennhyzirfwvtozzijujsckbxqpocakzrbwgpqgjjmsrtwmvhwyraukbuxfvebeylfpipzwjdzlmgslbtwzataxgqpasrssnfwndldwkdutdqcmcpyanrbdsxrvcvpsywjambtbzlcrvzesuhvyvwwuwwdznigxjxknfajpknqutfvvqynkpvkzgypasevrpxofbymdzcitoqolwqegocuyqsexhumzmckzuuwkamolbltlifongpvkcnrnnuplftqbxpdnegdqlymftqyrxcnzmu", 1499011);

    }

    private static void test(String s, int expected) {
        System.out.println("\n Given String :" + s + " expected: " + expected);
        System.out.println(" recursive :" + SubStringCalculatorRecursive.substringCalculator(s));
        System.out.println(" Iterative :" + SubStringCalculatorIterative.substringCalculator(s));
        System.out.println(" Iterative2 :" + SubStringCalculatorIterative.substringCalculator2(s));
    }
}

class SubStringCalculatorRecursive {

    static int dup = 0;

    public static long substringCalculator(String s) {
        long start = System.currentTimeMillis();
        if (s.isEmpty())
            return 0;

        dup = 0;
        int x = topDown(s, new HashSet<>());
        System.out.println("Dup->" + dup + "Time " + (System.currentTimeMillis() - start) + "ms");

        return x;
    }

    private static int topDown(String s, Set<String> cache) {
        if (s.isEmpty())
            return 0;


        if (cache.contains(s)) {
            dup++;
            return 0;
        }

        int c;

        c = 1 + topDown(s.substring(1), cache) +
                topDown(s.substring(0, s.length() - 1), cache);
        if (s.length() >= 2)
            c += topDown(s.substring(1, s.length() - 1), cache);

        cache.add(s);
        return c;
    }

}

class SubStringCalculatorIterative {

    public static int substringCalculator(String s) {
        long start = System.currentTimeMillis();
        if (s.isEmpty())
            return 0;

        Set<String> set = new HashSet<>();
        int count = 0;
        int p = 0;


        for (int l = s.length(); l >= 1; l--) {

            for (int i = 0; i < s.length() - l + 1; i++) {

                int j = i + l;
                String sub = s.substring(i, j);

                if (!set.contains(sub)) {
                    count++;
                    set.add(sub);
                } else {
                    p++;
                }

            }
        }

        System.out.println("Dup -> " + p + "Time :" + (System.currentTimeMillis() - start) + "ms");
        return count;
    }

    public static int substringCalculator2(String s) {
        long start = System.currentTimeMillis();
        int answer = 0;
        Set<String> set = new HashSet<String>();
        int dup = 0;
        for (int i = 0; i < s.length(); ++i) {
            String ss = "";

            for (int j = i; j < s.length(); ++j) {
                ss = ss + s.charAt(j);
                if (!set.contains(ss)) {
                    answer++;
                    set.add(ss);
                } else {
                    dup++;
                }
            }
        }
        System.out.println("Dup -> " + dup + "Time :" + (System.currentTimeMillis() - start) + "ms");

        return answer;
    }


}

