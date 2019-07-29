package Java.companyWise.facebook;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-15
 * Description: https://www.geeksforgeeks.org/facebook-nyc-onsite-interview-experience/
 * there’s some function that can “compress” strings like FACEBOOK -> F6K or
 * FACEBOOK -> F2E2OK or INTERNATIONALIZATION -> I18N. The compression mechanism can delete arbitrarily
 * many characters and replace them with the deleted character count. Write a function that takes
 * a compressed string and a plaintext string and determines if the compressed string is valid for the plaintext string
 * <p>
 * [FACEBOOK]
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
        int ci = 0;
        int oj = 0;

        while (ci < compressed.length() && oj < original.length()) {

            char c = compressed.charAt(ci);
            char o = original.charAt(oj);
            if (Character.isLetter(c)) {
                if (c == o) {
                    ci++;
                    oj++;
                    continue;
                } else
                    return false;  //If this is a letter and did not matched, then this is not possible

            } else if (Character.isDigit(c)) {

                int n = 0;
                //Form the digit ( may be between 0 to 9 or more then 9)
                while (ci < compressed.length() && Character.isDigit(compressed.charAt(ci)))
                    n = n * 10 + compressed.charAt(ci++) - '0';


                int k = oj;
                while (k < original.length() && n > 0) {
                    k++;
                    n--;
                }

                //not sufficient chars
                if (n != 0)
                    return false;

                //all matched and passed but compressed still left then not possible
                if (n == 0 && k == original.length() && ci != compressed.length())
                    return false;

                oj = k;

            } else
                return false;


        }

        return (ci == compressed.length() && oj == original.length());
    }
}
