package Java.companyWise.intuit;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-19
 * Description: Given a string,
 * 1. Sort this string based on frequency of each character (increasing order)
 * 2. if frequency are same, then sort based on lexical order.
 *
 * Input: helloworld
 * Output:[d, e, h, r, w, o, l]
 * Explanation:
 * d -> 1
 * e -> 1
 * h -> 1
 * r -> 1
 * w -> 1
 * o -> 2
 * l -> 3
 *
 */
public class SortStringBasedOnFrequencyAndLexical {

    public static void main(String[] args) {

        System.out.println(sort("helloworld"));
        System.out.println(sort("hhhhhhhhh"));
    }

    private static class FrequencyObj {
        int frequency;
        char c;

        public FrequencyObj(int frequency, char c) {
            this.frequency = frequency;
            this.c = c;
        }
    }

    private static List<Character> sort(String str) {

        if (str == null || str.isEmpty())
            return Collections.EMPTY_LIST;


        final FrequencyObj freq[] = new FrequencyObj[26];
        for (int i = 0; i < 26; i++)
            freq[i] = new FrequencyObj(str.length() + 1, ' ');

        for (char c : str.toCharArray()) {
            int x = c - 'a';

            if (freq[x].c == ' ') {
                freq[x].c = c;
                freq[x].frequency = 1;
            } else
                freq[x].frequency++;
        }

        Arrays.sort(freq, new Comparator<FrequencyObj>() {
            @Override
            public int compare(FrequencyObj o1, FrequencyObj o2) {

                if (o1.frequency == o2.frequency)
                    return o1.c - o2.c;

                return o1.frequency - o2.frequency;
            }
        });

        List<Character> output = new ArrayList<>();

        for (int i = 0; i < 26; i++)
            if (freq[i] != null && freq[i].c != ' ') {
                System.out.println(freq[i].c + " -> " + freq[i].frequency);
                output.add(freq[i].c);
            }

        return output;

    }
}
