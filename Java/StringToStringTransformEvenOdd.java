package Java;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 13/04/19
 * Description:
 * https://www.careercup.com/question?id=6316920860049408
 * <p>
 * Given two string check if they can be made equivalent by performing some operations on one or both string.
 * <p>
 * swapEven:swap a character at an even-numbered index with a character at another even-numbered index
 * <p>
 * swapOdd:swap a character at an odd-numbered index with a character at another odd-numbered index
 * <p>
 * Given : MinStepsInfiniteGrid="cdab" , x="abcd"
 * MinStepsInfiniteGrid -> cdab ->swap a and c ->adcb (swapEven)-> swap b and d (swapOdd) -> MinStepsInfiniteGrid="abcd" = x="abcd"
 * <p>
 * Given: MinStepsInfiniteGrid="dcba" , x="abcd"
 * no amount of operation will move character from an odd index to even index, so the two string will never be equals
 * <p>
 * Given: MinStepsInfiniteGrid="abcd" ,x="abcdcd"
 * x length to big so will never be equals
 */
public class StringToStringTransformEvenOdd {

    public static void main(String args[]) {

        System.out.println(canTransform("abcd", "adcb"));
        System.out.println(canTransform("abcd", "dacb"));
        System.out.println(canTransform("adcba", "abcda"));
        System.out.println(canTransform("adcba", "bacda"));

    }

    // O(s1) or  O(s2) => o(n) they must be of same size if they are equal apparently
    private static boolean canTransform(String s1, String s2) {
        Map<Character, Integer> evenIndex = new HashMap<>(); // This is constant, as different character is at most 256
        Map<Character, Integer> oddIndex = new HashMap<>(); // This is constant, as different character is at most 256

        char s1Chars[] = s1.toCharArray();
        char s2Chars[] = s2.toCharArray();

        for (int i = 0; i < s1.length(); i++) //O(s1)
            if (i % 2 == 0)
                evenIndex.put(s1Chars[i], evenIndex.getOrDefault(s1Chars[i], 0) + 1);
            else
                oddIndex.put(s1Chars[i], oddIndex.getOrDefault(s1Chars[i], 0) + 1);

        for (int i = 0; i < s2.length(); i++) //O(s2)
            if (i % 2 == 0)
                evenIndex.put(s2Chars[i], evenIndex.getOrDefault(s2Chars[i], 0) - 1);
            else
                oddIndex.put(s2Chars[i], oddIndex.getOrDefault(s2Chars[i], 0) - 1);

        //evey value should be zero since they will cut each of them due to above count way
        // This is constant, as different character is at most 256
        return evenIndex.values().stream().filter(x -> x != 0).collect(Collectors.toList()).isEmpty()
                &&
                oddIndex.values().stream().filter(x -> x != 0).collect(Collectors.toList()).isEmpty();
    }
}
