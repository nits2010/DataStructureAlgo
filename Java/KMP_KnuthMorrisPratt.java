package Java;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-19
 * Description:
 * <p>
 * Pattern matching algorithm; Find pattern in string
 */
public class KMP_KnuthMorrisPratt {
}


/**
 * When String is are equal length, then compare it directly.
 * The important optimization is to compare both from start & middle parallel
 * <p>
 * Runtime: 0 ms, faster than 100% of Java online submissions for Implement strStr().
 * Memory Usage: 36.1 MB, less than 100% of Java online submissions for Implement strStr().
 */
class KMP {

    public int strStr(String haystack, String needle) {
        if ((haystack == null && needle == null) || (haystack.isEmpty() && needle.isEmpty()))
            return 0;

        if (haystack == null || haystack.isEmpty())
            return -1;

        if (needle == null || needle.isEmpty())
            return 0;

        /**
         * When Length of both are same, then its not good to apply KMP as it will take more time then just
         * comparing the string directly
         *
         */
        if (needle.length() == haystack.length()) {
            return preProcess(haystack, needle);
        } else
            return kmpMatch(haystack, needle);
    }

    /**
     * When Length of both are same, then its not good to apply KMP as it will take more time then just
     * comparing the string directly
     * <p>
     * O(n/2)
     *
     * @param haystack
     * @param needle
     * @return
     */
    private int preProcess(String haystack, String needle) {

        /**
         * Since the string are same, we can compare the half part on each side. This will reduce the overall computation.
         * This will like two pointer, one which matching from start and other is matching from middle.
         */
        for (int i = 0, len = needle.length() >> 1; i < len; i++) {

            if (haystack.charAt(i) != needle.charAt(i)
                    || haystack.charAt(needle.length() - 1 - i) != needle.charAt(needle.length() - 1 - i)) {

                return -1;
            }
        }

        return 0;

    }

    private int kmpMatch(String haystack, String needle) {

        int prefixWhichIsAlsoSuffix[] = prefixWhichIsAlsoSuffix(needle);

        int i = 0;
        int j = 0;

        while (i < haystack.length()) {

            if (haystack.charAt(i) == needle.charAt(j)) {

                if (j == needle.length() - 1)
                    return i - j;

                i++;
                j++;
            } else if (j > 0)
                j = prefixWhichIsAlsoSuffix[j - 1];
            else
                i++;
        }

        return -1;
    }


    private int[] prefixWhichIsAlsoSuffix(final String pattern) {

        int prefixWhichIsAlsoSuffix[] = new int[pattern.length()];
        prefixWhichIsAlsoSuffix[0] = 0;
        int i = 1;
        int j = 0;

        while (i < pattern.length()) {

            if (pattern.charAt(i) == pattern.charAt(j))
                prefixWhichIsAlsoSuffix[i++] = ++j;
            else if (j > 0)
                j = prefixWhichIsAlsoSuffix[j - 1];
            else
                prefixWhichIsAlsoSuffix[i++] = 0;

        }

        return prefixWhichIsAlsoSuffix;
    }
}
