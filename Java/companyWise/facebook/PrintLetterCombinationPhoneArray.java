package Java.companyWise.facebook;

import Java.helpers.GenericPrinter;
import Java.LeetCode.LetterCombinationsPhoneNumber;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-26
 * Description: https://aonecode.com/aplusplus/interviewctrl/getInterview/602
 * Question: Given an integer array, output all combinations of cell phone text-ing letters with the given numbers.
 */
public class PrintLetterCombinationPhoneArray {
    static String[] map = {"", "", "abc", "def", "ghi", "jkl",
            "mno", "pqrs", "tuv", "wxyz"};

    public static void main(String[] args) {
        testIndividualNumbers(new int[]{23, 43});
        testIndividualNumbersArray(new int[]{2, 3, 4});

        testGivenTextMatchesWithArray(new int[]{2, 3, 4}, "adg");
        testGivenTextMatchesWithArray(new int[]{2, 3, 4}, "cdg");
        testGivenTextMatchesWithArray(new int[]{2, 3, 4}, "cfi");
        testGivenTextMatchesWithArray(new int[]{2, 3, 4}, "abc");
        testGivenTextMatchesWithArray(new int[]{2, 3, 4}, "");
        testGivenTextMatchesWithArray(new int[]{2, 3, 4}, "ceg");
    }

    //Follow-up: Check whether the given text matches with a given integer array.
    private static void testGivenTextMatchesWithArray(int[] nums, String text) {
        GenericPrinter.print(nums);
        System.out.println("Testing text :" + text + " matches with " + match(nums, text, 0, text));
    }

    private static boolean match(int[] nums, String text, int index, String s) {

        if (nums.length != text.length()) {
            return false;
        }

        if (index == nums.length) {
            if (s.isEmpty())
                return true;
            else
                return false;
        }

        int number = nums[index];
        String combination = map[number];
        for (char c : combination.toCharArray()) {
            if (c == s.charAt(0)) {
                s = s.substring(1);
                if (match(nums, text, index + 1, s))
                    return true;

                break;
            }
        }


        return false;
    }


    /**
     * Given an integer array, output all combinations of cell phone texting letters with the given numbers.
     *
     * @param nums
     */
    private static void testIndividualNumbersArray(int[] nums) {
        System.out.println("Testing ");
        GenericPrinter.print(nums);

        List<String> combinations = new ArrayList<>();

        try {
            combinationsByNumbers(nums, 0, combinations, new StringBuilder(), map);
            System.out.println("output\n");
            System.out.println(combinations);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * @param nums
     * @param index
     * @param combinations
     * @param current
     * @param map
     * @throws InvalidArgumentException
     */
    private static void combinationsByNumbers(int[] nums, int index, List<String> combinations, StringBuilder current, String[] map) throws InvalidArgumentException {

        if (index == nums.length) {
            combinations.add(current.toString());
            return;
        }

        int number = nums[index];
        if (number > map.length)
            throw new InvalidArgumentException(new String[]{"Invalid input"});

        String combination = map[number];

        for (char c : combination.toCharArray()) {
            current.append(c);
            combinationsByNumbers(nums, index + 1, combinations, current, map);
            current.deleteCharAt(current.length() - 1);

        }
    }


    private static void testIndividualNumbers(int[] nums) {
        System.out.println(combination(nums));
    }


    public static List<String> combination(int nums[]) {

        return Arrays.stream(nums)
                .mapToObj(i -> String.valueOf(i))
                .map(s -> LetterCombinationsPhoneNumber.letterCombinationsRecursiveStringBuilder(s))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
