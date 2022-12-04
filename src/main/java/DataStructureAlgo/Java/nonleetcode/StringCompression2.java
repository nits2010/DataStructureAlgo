package DataStructureAlgo.Java.nonleetcode;

/**
 * Author: Nitin Gupta
 * Date: 2019-06-30
 * Description:http://javabypatel.blogspot.com/2016/07/string-compression-in-place-run-length-encoding-program.html
 * Compress a given string "aacccddd" to "a2c3d3"
 * Constraint: Inplace compression, no extra space to be used.
 * Assumption: Output size will not exceed input size.
 * Example: input:"acc" output: "a1c2" buffer overflow, because output size length is 4 and input size length is 3. Such inputs will not be given
 * aacccddd ->a2c3d3
 * acc -> a1c2
 *
 * {@link DataStructureAlgo.Java.LeetCode.StringCompressionRunLengthEncoding}
 */
public class StringCompression2 {

    private static final char SPACE = ' ';

    public static void main(String []args) {
        System.out.println(compress("abcde     "));
        System.out.println(compress("abcdeeeeeeeefffffg"));
        System.out.println(compress("abcdeeeeeeeeeeeeeeeeeeefffffg"));

        System.out.println(compress("aacccddd"));
        System.out.println(compress("acc "));
        System.out.println(compress("aaaaaaaa     "));

    }

    static String compress(String toCompress) {

        if (toCompress == null || toCompress.isEmpty())
            return toCompress;

        char[] str = toCompress.toCharArray();
        int n = str.length;


        compressMultiOccurrence(str, n);

        compressSingleOccurrence(str, n);


        return new String(str);

    }

    /**
     * This will compress the string for those chars which are occurring multiple time.
     * It replace those occurrence with " " space
     *
     * @param str
     * @param n
     */
    private static void compressMultiOccurrence(char[] str, int n) {

        for (int i = 0; i < n; i++) {

            if(str[i]==SPACE)
                break;

            /**
             * Find does current char is occurring multiple times
             */
            char c = str[i];
            int j = i + 1;

            //Find the occurrence of this char 'c' and push space in between ' '
            while (j < n && str[j] == c ) {
                str[j] = SPACE;
                j++;

            }

            //if this occurred more than one ?
            if (j != i + 1) {

                //if yes, then transform it to aaa to a3' ' or aaaaaaaaaaa -> a11' '....' '
                String count = "" + (j - i);

                //since the count may be more than single digit
                for (char t : count.toCharArray())
                    str[++i] = t;

                i = j - 1;
            }
        }
    }


    /**
     * This will compress those chars which are occurring only single time.
     *
     * Algo:
     * 1. find if current char is a Character [ not number as it may possible current char is a number due to above compression]
     * 2. for the current char, the next is also char i.e. that the current char occurring only 1's
     *
     * @param str
     * @param n
     */
    private static void compressSingleOccurrence(char[] str, int n) {

        for (int i = 0; i < n; i++) {

            char c = str[i];


            //if next is a character only, means we need to put 1 in between to these char [ remember we already compressed multi chars ]
            if (Character.isLetter(c)) {

                //Since this is a letter, then we need to move this chunk ahead to make a room for '1'

                /**
                 * for example we have
                 * a b c d 6 _ _ _ _ o 3 _
                 * convert it to
                 * a b b c d 6 _ _ _ o 3 _
                 * i will be pointing to 'a' and j will be pointing to 'b' [first b ]
                 */
                if (i < n - 1 && Character.isLetter(str[i + 1])) {
                    int j = i + 1;
                    char toMove = str[j];
                    char backup;
                    j++;

                    //Move current till we hit a space
                    while (toMove != SPACE) {
                        backup = str[j];
                        str[j] = toMove;
                        toMove = backup;
                        j++;

                    }
                    //append the occurrence i.e. is 1
                    str[i + 1] = '1';

                    i++;//push i to next char a b b c d 6 _ _ _ o 3 _ -> a 1 b c d 6 _ _ _ o 3 _ till i will be pointing 'a'

                }else if (i < n-1 && str[i+1] == SPACE){ //if next char is a space, then push 1 at the place of space

                    str[i+1] = '1';
                    i++;
                }
            } else if (c == SPACE) {
                // if its a space
                int j = i + 1;

                /**                i j
                 * a 1 b 1 c 1 d 6 _ _ o 3
                 * then find a first char where its not a space and move those here
                 * a 1 b 1 c 1 d 6 o 3 _ _
                 */

                //find where is the first char after spaces
                while (j < n && str[j] == SPACE)
                    j++;


                /**                i   j
                 * a 1 b 1 c 1 d 6 _ _ o 3
                 * =>
                 *                   i     j=n
                 * a 1 b 1 c 1 d 6 o 3 _ _
                 */
                if (j < n) {
                    int x = i;
                    //Move series of chars start at j to the place where we saw space (that was i), and emptied those with space pointed by j
                    while (j < n && str[j] != SPACE) {
                        str[x++] = str[j];
                        str[j++] = SPACE;
                    }

                    if (str[i + 1] == SPACE && Character.isLetter(str[i])) {
                        str[i + 1] = '1';
                    }

                }


            }
        }

    }


}
