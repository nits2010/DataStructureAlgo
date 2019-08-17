package Java.LeetCode;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-04
 * Description: https://leetcode.com/problems/bulls-and-cows/
 * <p>
 * You are playing the following Bulls and Cows game with your friend: You write down a number and ask your friend to guess what the number is. Each time your friend makes a guess, you provide a hint that indicates how many digits in said guess match your secret number exactly in both digit and position (called "bulls") and how many digits match the secret number but locate in the wrong position (called "cows"). Your friend will use successive guesses and hints to eventually derive the secret number.
 * <p>
 * Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and B to indicate the cows.
 * <p>
 * Please note that both secret number and friend's guess may contain duplicate digits.
 * <p>
 * Example 1:
 * <p>
 * Input: secret = "1807", guess = "7810"
 * <p>
 * Output: "1A3B"
 * <p>
 * Explanation: 1 bull and 3 cows. The bull is 8, the cows are 0, 1 and 7.
 * Example 2:
 * <p>
 * Input: secret = "1123", guess = "0111"
 * <p>
 * Output: "1A1B"
 * <p>
 * Explanation: The 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow.
 * Note: You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.
 */
public class BullsCow {
    public static void main(String[] args) {
        BullsCowSolution bullsCowSolution = new BullsCowSolution();
        System.out.println(bullsCowSolution.getHint("1807", "7810"));
        System.out.println(bullsCowSolution.getHint("1123", "0111"));
        System.out.println(bullsCowSolution.getHint("1807", "0817"));
        System.out.println(bullsCowSolution.getHint("11", "11"));
    }
}

/**
 * O(n)/O(1)
 * Runtime: 1 ms, faster than 100.00% of Java online submissions for Bulls and Cows.
 * Memory Usage: 37.7 MB, less than 69.03% of Java online submissions for Bulls and Cows.
 */
class BullsCowSolution {

    /*
    Question asked us two different things
1. Find how many digits that are matched and right position (bulls)
2. . Find how many digits that are matched and but not at right position (cows).

Important observation here is that, each character in given input is only a single "digit" and a digit can be only from "0-9".
We'll use array of 10 to track each item presence from secret and guess.

Now, if at particular index two case happens
1. If they match ; -> increase bulls without any doubt
2. If they don't match; Now since they don't match there could be two posibilites
		a. If the guess character is not present in our secret[till this index]; then we need to ignore it. We reduce its occurrence
		b. if the guess character is present (means has seen in secret already ) then we need to mark as used so decrease it.
		c. keep track of secret character all the time.

But how do we count cows?
Notice, when we decrease a particular digit occurrence there is possibility that it still there ( means its occurrence is 5 so far, so still we have 4 left ) or not there at all (in the case we are ahead in guess i.e. that particular char has not been occurred as of now in secret ).
Lets say 's' represent char in secret and 'g' for guess at this current index.

so whenever we see the digit occurrence at 's' character  as negative (second case) we increase cow
and when we see digit occurrence at 'g' character as positive (first case) we increase cow.
Also keep doing the above.
     */
    public String getHint(String secret, String guess) {

        if (secret == null || secret.isEmpty() || guess == null || guess.isEmpty())
            return "";

        System.out.println("Secret: " + secret + " guess : " + guess);

        int len = secret.length();

        int bulls = 0;
        int cow = 0;

        int digits[] = new int[10]; //0-9

        for (int i = 0; i < len; i++) {
            char s = secret.charAt(i);
            char g = guess.charAt(i);

            if (s == g) // if they match and right location
                bulls++;
            else {
                //if they dont match or are not at right position

                if (digits[s - '0'] < 0) //this number already been occurred and taken care
                    cow++;
                digits[s - '0']++;


                if (digits[g - '0'] > 0) // we have seen this number already
                    cow++;
                digits[g - '0']--;

            }
        }


        return "" + bulls + "A" + cow + "B";

    }
}