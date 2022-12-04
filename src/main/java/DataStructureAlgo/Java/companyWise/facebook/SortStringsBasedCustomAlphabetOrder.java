package DataStructureAlgo.Java.companyWise.facebook;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-18
 * Description: https://aonecode.com/facebook-phone-interview-questions-2019
 * Sort the given sentence order defined by alphabet.
 * <p>
 * Note: if alphabet not found, then sort in there lexicographical manner
 *
 * similar to this {@link WordOrdering}
 */
public class SortStringsBasedCustomAlphabetOrder {

    public static void main(String []args) {

        char alphabet[] = {'b', 'c', 'a'};
        String sentences[] = {"aa", "ab", "bc", "abc", "", "xt", "yz"};

        sentences = sortByOrder(alphabet, sentences);

        for (int i = 0; i < sentences.length; i++)

            System.out.print(" " + sentences[i]);

    }

    //O(m*n*log(n))/O(x)
    private static String[] sortByOrder(char[] alphabet, String[] sentences) {
        Map<Character, Integer> order = new HashMap<>();
        for (int i = 0; i < alphabet.length; i++) {
            order.put(alphabet[i], i + 1);
        }

        //O(m*n*log(n))
        Arrays.sort(sentences,
                (o1, o2) -> { //O(m) where m is the minimum length of given string in sentence

                    if (o1.isEmpty())
                        return -1;
                    if (o2.isEmpty())
                        return 1;

                    if (o1.equals(o2))
                        return 0;


                    int i = 0;
                    int n = Math.min(o1.length(), o2.length());

                    while (i < n) {

                        char c1 = o1.charAt(i);
                        char c2 = o2.charAt(i);

                        int c1Order = order.getOrDefault(c1, Integer.MAX_VALUE);
                        int c2Order = order.getOrDefault(c2, Integer.MAX_VALUE);

                        //lexicographical manner
                        if (c1Order == Integer.MAX_VALUE && c2Order == Integer.MAX_VALUE)
                            return Character.compare(c1, c2);

                            //If one found and other not Or c1 is greater as given in alphabet
                        else if ((c1Order == Integer.MAX_VALUE && c2Order != Integer.MAX_VALUE) || (c1Order > c2Order))
                            return 1;
                            //If one found and other not Or c2 is greater as given in alphabet
                        else if ((c1Order != Integer.MAX_VALUE && c2Order == Integer.MAX_VALUE) || (c1Order < c2Order))
                            return -1;

                        i++;
                    }
                    return 0;
                });


        return sentences;
    }


}
