package Java;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 09/04/19
 * Description:
 * https://www.geeksforgeeks.org/longest-possible-chunked-palindrome/
 */
public class LongestPossibleChunkedPalindrome {

    public static void main(String []args) {

        String input1 = "ghiabcdefhelloadamhelloabcdefghi";
        String input2 = "antaprezatepzapreanta";
        String input3 = "merchant";
        String input4 = "geeksforgeeks";
        String input5 = "maymaymaymay";

        System.out.println("input :" + input1 + " longest Possible Chunked Palindromes :" + longestPossibleChunkedPalindrome(input1));
        System.out.println("input :" + input2 + " longest Possible Chunked Palindromes :" + longestPossibleChunkedPalindrome(input2));
        System.out.println("input :" + input3 + " longest Possible Chunked Palindromes :" + longestPossibleChunkedPalindrome(input3));
        System.out.println("input :" + input4 + " longest Possible Chunked Palindromes :" + longestPossibleChunkedPalindrome(input4));
        System.out.println("input :" + input5 + " longest Possible Chunked Palindromes :" + longestPossibleChunkedPalindrome(input5));

        System.out.println("Iterative solutions");

        System.out.println("input :" + input1 + " longest Possible Chunked Palindromes :" + chunkedPalindrome(input1));
        System.out.println("input :" + input2 + " longest Possible Chunked Palindromes :" + chunkedPalindrome(input2));
        System.out.println("input :" + input3 + " longest Possible Chunked Palindromes :" + chunkedPalindrome(input3));
        System.out.println("input :" + input4 + " longest Possible Chunked Palindromes :" + chunkedPalindrome(input4));
        System.out.println("input :" + input5 + " longest Possible Chunked Palindromes :" + chunkedPalindrome(input5));
    }


    /**
     * Recursive solutions
     * @param input
     * @return
     */
    private static int longestPossibleChunkedPalindrome(String input) {

        String current = input;
        return longestPossibleChunkedPalindrome(current, 0);


    }

    private static int longestPossibleChunkedPalindrome(String current, int count) {

        //if the current string is empty of null, we can't chunked it so 0
        if (current == null || current.isEmpty())
            return 0;

        //if current has only one character then it is chunked already
        if (current.length() == 1) {


            return (1);

        }


        int n = current.length();


        //Check for palindrome; only half is sufficient since palindrome is mirror from the middle
        for (int i = 0; i < n / 2; i++) {

            //if current substring is palindrome, first half as (0,i+1) and second half from n-i-1 to n
            if (current.substring(0, i + 1).equals(current.substring(n - i - 1, n))) {

                //if palindrome then make a cut here;
                // take the substring out excluding both end palindrome string, since there will be two palindrome so count+=2
                // the length of that palindrome would be len (of previous) + length of current
                return longestPossibleChunkedPalindrome(current.substring(i + 1, n - i - 1), count + 2);
            }
        }


        return count + 1;
    }

    /**
     * This is iterative solution
     * @param str
     * @return
     */
    private static int chunkedPalindrome(String str) {
        int count = 0;

        int from, too, till, length;
        from = 0;
        too = 1;
        length = str.length();
        till = length - 1;

        while (from <= till) {
            if (from == till) {
                count++;
                break;
            }

            String s1 = str.substring(from, too);
            String s2 = str.substring(till, length);

            //If both of the string are equal then they will form palindrome.
            if (s1.equals(s2)) {
                //increase the count
                count += 2;

                //move left index ahead
                from = too;

                //increse the length of right index of left side
                too++;

                //reduce the length
                length = till;
                till--;

            } else {
                too++;
                till--;
            }
        }
        return count;
    }


}
