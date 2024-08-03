package DataStructureAlgo.Java.LeetCode2025.easy;



import java.util.Map;
import java.util.Objects;


/**
 * Author: Nitin Gupta
 * Date: 2024-07-15
 * Question Category: 13. Roman to Integer [easy]
 * Description: https://leetcode.com/problems/roman-to-integer/
 * <p>
 Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

 Symbol       Value
 I             1
 V             5
 X             10
 L             50
 C             100
 D             500
 M             1000
 For example, 2 is written as II in Roman numeral, just two ones added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.

 Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

 I can be placed before V (5) and X (10) to make 4 and 9.
 X can be placed before L (50) and C (100) to make 40 and 90.
 C can be placed before D (500) and M (1000) to make 400 and 900.
 Given a roman numeral, convert it to an integer.



 Example 1:

 Input: s = "III"
 Output: 3
 Explanation: III = 3.
 Example 2:

 Input: s = "LVIII"
 Output: 58
 Explanation: L = 50, V= 5, III = 3.
 Example 3:

 Input: s = "MCMXCIV"
 Output: 1994
 Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.


 Constraints:

 1 <= s.length <= 15
 s contains only the characters ('I', 'V', 'X', 'L', 'C', 'D', 'M').
 It is guaranteed that s is a valid roman numeral in the range [1, 3999].
 * File reference
 * -----------
 * Duplicate {@link RomanIntegerConversion_13}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 * @HashTable
 * @Math
 * @String
 *
 *
 * <p>
 * Company Tags
 * -----
 * @Amazon
 * @Adobe
 * @Apple
 * @Google
 * @Bloomberg

 *
 * @Editorial
 */

public class RomanIntegerConversion_13 {

    public static void main(String[] args) {

        testRomanToIntegerSol("MCMXCIV", 1994);
        testRomanToIntegerSol("LVIII",58);
        testRomanToIntegerSol("MMMCMXCVIV",3999);




    }

    private static void testRomanToIntegerSol(String test, Integer expectedValue) {
        RomanToIntegerSolution romanToIntegerSol = new RomanToIntegerSolution();
        Integer outcome = romanToIntegerSol.romanToIntUsingIf(test);
        boolean result = Objects.equals(expectedValue, outcome);
        System.out.println("test: "+test + " expected:"+expectedValue + " outcome:"+outcome + " Result:"+result);

    }


}

class RomanToIntegerSolution {

    private final Map<Character, Integer> romanToIntMap = Map.of(
            'I', 1,
            'V', 5,
            'X', 10,
            'L', 50,
            'C', 100,
            'D', 500,
            'M', 1000
    );

    /**
     * Simple reverse tracking
     * 5 ms
     * Beats 56.83%
     * https://leetcode.com/problems/roman-to-integer/submissions/1322045587/
     * @param s
     * @return
     */
     public int romanToInt(String s) {
        if(s == null || s.isEmpty())
            return 0;

        if(s.length()==1)
            return romanToIntMap.get(s.charAt(0));

        int finalValue = 0;
        int length = s.length();

        for (int i=length-1; i>=0; i--){
            char currentRoman = s.charAt(i);
            Integer currentRomanValue = romanToIntMap.get(currentRoman);

            if(currentRomanValue == null)
                return 0;

            if(i>0){
                Integer previousRomanValue =  romanToIntMap.get(s.charAt(i-1));
                if(currentRomanValue > previousRomanValue){
                    currentRomanValue -= previousRomanValue;
                    i--;
                }
                finalValue +=currentRomanValue;
            }else{
                finalValue += currentRomanValue;
            }

        }
        return finalValue;
    }

    private int romanToValue(char c) {

        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
        }

        return 0;

    }

    /**
     * https://leetcode.com/problems/roman-to-integer/submissions/1322049221/
     * 3  ms
     * Beats 80.90%
     * @param s
     * @return
     */
    public int romanToIntUsingSwitch(String s) {
        if(s == null || s.isEmpty())
            return 0;

        if(s.length()==1)
            return romanToValue(s.charAt(0));

        int finalValue = 0;
        int length = s.length();

        for (int i=length-1; i>=0; i--){
            char currentRoman = s.charAt(i);
            int currentRomanValue = romanToValue(currentRoman);

            if(i>0){
                int previousRomanValue =  romanToValue(s.charAt(i-1));
                if(currentRomanValue > previousRomanValue){
                    currentRomanValue -= previousRomanValue;
                    i--;
                }
                finalValue +=currentRomanValue;
            }else{
                finalValue += currentRomanValue;
            }

        }
        return finalValue;
    }


    /**
     * https://leetcode.com/problems/roman-to-integer/submissions/1322066105/
     * 3
     * ms
     * Beats
     * 80.90%
     * @param s
     * @return
     */
    public int romanToIntUsingIf(String s) {
        if(s == null || s.isEmpty())
            return 0;

        if(s.length()==1)
            return romanToValue(s.charAt(0));

        int finalValue = 0;
        int length = s.length();

        for (int i=0; i<length; i++){

            char current = s.charAt(i);
            int romanValue = romanToValue(current);

            if(current == 'M'){
                finalValue += romanValue;
            }

            if(current == 'D'){
                finalValue += romanValue;
            }

            if(current == 'C'){
                if (i+1<length && ( s.charAt(i+1) == 'D' || s.charAt(i+1) == 'M')){
                    int next = romanToValue(s.charAt(i+1));
                    next -= romanValue;
                    finalValue += next;
                    i++;
                }else{
                    finalValue += romanValue;
                }
            }

            if(current == 'L'){
                finalValue += romanValue;
            }

            if(current == 'X'){
                if (i+1<length && ( s.charAt(i+1) == 'L' || s.charAt(i+1) == 'C')){
                    int next = romanToValue(s.charAt(i+1));
                    next -= romanValue;
                    finalValue += next;
                    i++;
                }else{
                    finalValue += romanValue;
                }
            }

            if(current == 'V'){
                finalValue += romanValue;
            }

            if(current == 'I'){
                if (i+1<length && ( s.charAt(i+1) == 'V' || s.charAt(i+1) == 'X')){
                    int next = romanToValue(s.charAt(i+1));
                    next -= romanValue;
                    finalValue += next;
                    i++;
                }else{
                    finalValue += romanValue;
                }
            }
        }
        return finalValue;
    }

    /**
     * https://leetcode.com/problems/roman-to-integer/submissions/1322067805/
     * 2
     * ms
     * Beats
     * 100.00%
     * @param s
     * @return
     */
    public static int romanToIntWithIf(String s) {
        int total = 0;
        if (s == null || s.isEmpty())
            return total;

        char[] numerals = s.toCharArray();

        for (int pos = 0; pos < numerals.length; pos++) {
            if (numerals[pos] == 'M') {
                total += 1000;
            } else if (numerals[pos] == 'D') {
                total += 500;
            } else if (numerals[pos] == 'C') { //C can be apply only on D and M
                if (pos + 1 < numerals.length && numerals[pos + 1] == 'D') {
                    total += 400;
                    pos++;
                } else if (pos + 1 < numerals.length && numerals[pos + 1] == 'M') {
                    total += 900;
                    pos++;
                } else {
                    total += 100;
                }
            } else if (numerals[pos] == 'L') {
                total += 50;
            } else if (numerals[pos] == 'X') { //X can be apply on C and L
                if (pos + 1 < numerals.length && numerals[pos + 1] == 'C') {
                    total += 90;
                    pos++;
                } else if (pos + 1 < numerals.length && numerals[pos + 1] == 'L') {
                    total += 40;
                    pos++;
                } else {
                    total += 10;
                }
            } else if (numerals[pos] == 'V') {
                total += 5;
            } else if (numerals[pos] == 'I') { //I Can be apply on X and V
                if (pos + 1 < numerals.length && numerals[pos + 1] == 'X') {
                    total += 9;
                    pos++;
                } else if (pos + 1 < numerals.length && numerals[pos + 1] == 'V') {
                    total += 4;
                    pos++;
                } else {
                    total += 1;
                }
            }
        }

        return total;
    }

}