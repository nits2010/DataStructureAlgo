package Java.companyWise.Google;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-05
 * Description: https://leetcode.com/discuss/interview-question/349416/goolge-onsite-guess-pin-code
 * <p>
 * Given 2 strings pin and guess. Write a function to provide a hint that indicates how digits in guess match the pin. Use:
 * * - to indicate a number in the correct position.
 * o - to indicate that a number is present in the pin code but in a different possition.
 * _ - to indicate that there's no such number in the pin code.
 *
 * <p>
 * Example 1:
 * <p>
 * Input: pin = "1432", guess = "1246"
 * Output: "*oo_"
 * Explanation:
 * 1 is in the correct position so `*`
 * 2 and 4 are present in the pin code but in different positions thus `oo`
 * There's no 6 in the pin code thus `_`
 * Example 2:
 * <p>
 * Input: pin = "1234", guess = "1234"
 * Output: "****"
 * Example 3:
 * <p>
 * Input: pin = "1234", guess = "5678"
 * Output: "____"
 * Example 4:
 * <p>
 * Input: pin = "1224", guess = "5242"
 * Output: "_*oo"
 * Example 5:
 * <p>
 * Input: pin = "1234", guess = "1224"
 * Output: "**_*"
 * Example 6:
 * <p>
 * Input: pin = "2124", guess = "1224"
 * Output: "oo**"
 * Constraints:
 * <p>
 * pin.length == input.length
 * The input strings contain only digits 0-9.
 * similar to {@link Java.LeetCode.BullsCow}
 */
public class BullsCowExtended {

    public static void main(String[] args) {
        BullsCowExtendedSolution bullsCowSolution = new BullsCowExtendedSolution();
        System.out.println(bullsCowSolution.getHint("1807", "7810"));
        System.out.println(bullsCowSolution.getHint("1123", "0111"));
        System.out.println(bullsCowSolution.getHint("1807", "0817"));
        System.out.println(bullsCowSolution.getHint("11", "11"));

        System.out.println(bullsCowSolution.getHint("1234", "1234"));
        System.out.println(bullsCowSolution.getHint("1234", "5678"));
        System.out.println(bullsCowSolution.getHint("1224", "5242"));
        System.out.println(bullsCowSolution.getHint("1234", "1224"));
        System.out.println(bullsCowSolution.getHint("2124", "1224"));


    }
}

class BullsCowExtendedSolution {


    /**
     * Idea is same as {@link Java.LeetCode.BullsCow}, But since we need to know does a character occurred or not at all, we need to pre-build
     * the digit cache in order to answer this question.
     *
     * @param secret
     * @param guess
     * @return
     */
    public String getHint(String secret, String guess) {
        if (secret == null || secret.isEmpty() || guess == null || guess.isEmpty())
            return "____";

        System.out.println("Secret: " + secret + " guess : " + guess);

        int len = secret.length();


        int digits[] = new int[10]; //0-9

        for (int i = 0; i < len; i++) {
            char s = secret.charAt(i);

            digits[s - '0']++;

        }

        StringBuilder response = new StringBuilder();

        for (int i = 0; i < len; i++) {
            char s = secret.charAt(i);
            char g = guess.charAt(i);

            if (s == g) {
                response.append('*');
                digits[s - '0']--;
            } else {
                if (digits[g - '0'] == 0) // not match
                    response.append('_');
                else {
                    response.append('o');
                    digits[g - '0']--;
                }

            }

        }
        return response.toString();
    }
}