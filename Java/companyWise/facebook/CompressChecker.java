package Java.companyWise.facebook;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-15
 * Description: https://www.geeksforgeeks.org/facebook-nyc-onsite-interview-experience/
 * there’s some function that can “compress” strings like FACEBOOK -> F6K or
 * FACEBOOK -> F2E2OK or INTERNATIONALIZATION -> I18N. The compression mechanism can delete arbitrarily
 * many characters and replace them with the deleted character count. Write a function that takes
 * a compressed string and a plaintext string and determines if the compressed string is valid for the plaintext string
 */
public class CompressChecker {

    public static void main(String args[]) {

        System.out.println(isCompressed("I18NN", "INTERNATIONALIZATION"));
        System.out.println(isCompressed("8NN", "INTERNATIONALIZATION"));
        System.out.println(isCompressed(" ", "INTERNATIONALIZATION"));
        System.out.println(isCompressed("INTERNATIONALIZATION", "INTERNATIONALIZATION"));
        System.out.println(isCompressed("F6K", "FACEBOOK"));
        System.out.println(isCompressed("F2E2OK", "FACEBOOK"));
        System.out.println(isCompressed("I18N", "INTERNATIONALIZATION"));

    }

    private static boolean isCompressed(String compressed, String original) {
        int i = 0;
        int j = 0;

        while (i < compressed.length() && j < original.length()) {

            char c1 = compressed.charAt(i);
            char c2 = original.charAt(j);

            if (c1 == c2) {
                i++;
                j++;
                continue;
            }

            //If this is a letter and did not matched, then this is not possible
            if (Character.isLetter(c1))
                return false;
            else if (Character.isDigit(c1)) {

                int n = 0;

                while (i < compressed.length() && Character.isDigit(compressed.charAt(i)))
                    n = n * 10 + compressed.charAt(i++) - '0';


                int k = j;
                while (k < original.length() && n > 0) {
                    k++;
                    n--;
                }

                //not sufficient chars
                if (n != 0)
                    return false;

                //all matched and passed
                if (n == 0 && k == original.length() && i != compressed.length())
                    return false;

                j = k;

            } else
                return false;


        }

        return (i == compressed.length() && j == original.length());
    }
}
