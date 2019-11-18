package Java.companyWise.intuit;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-19
 * Description: Given a string,
 * 1. Sort this string based on frequency of each character (increasing order)
 * 2. if frequency are same, then sort based on lexical order.
 * <p>
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
 * <p>
 * extension of {@link Java.LeetCode.SortCharactersByFrequency}
 * https://leetcode.com/problems/sort-characters-by-frequency/discuss/365179/Blueprint-to-100-or-Optimisation-or-38-ms-to-2ms-or-5-Solution-or-optimisation-step-by-step
 */
public class SortStringBasedOnFrequencyAndLexical {

    public static void main(String[] args) {

        System.out.println(sortStringBasedOnFrequencyAndLexical("helloworld"));
        System.out.println(sortStringBasedOnFrequencyAndLexical("hhhhhhhhh"));
    }


    private static List<Character> sortStringBasedOnFrequencyAndLexical(String str) {

        if (str == null || str.isEmpty())
            return Collections.EMPTY_LIST;


        class FrequencyObj {
            int frequency;
            char c;

            public FrequencyObj(int frequency, char c) {
                this.frequency = frequency;
                this.c = c;
            }
        }

        final FrequencyObj freq[] = new FrequencyObj[256];
        for (int i = 0; i < 256; i++)
            freq[i] = new FrequencyObj(str.length() + 1, ' ');

        for (char c : str.toCharArray()) {
            int x = (int) c;

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

        for (int i = 0; i < 256; i++)
            if (freq[i] != null && freq[i].c != ' ') {
                System.out.println(freq[i].c + " -> " + freq[i].frequency);
                output.add(freq[i].c);
            }

        return output;

    }
}
